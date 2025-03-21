package com.dofast.module.report.controller.admin.goview;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.tenant.core.aop.TenantIgnore;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeCreateReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeRespVO;
import com.dofast.module.report.controller.admin.goview.vo.data.GoViewDataGetBySqlReqVO;
import com.dofast.module.report.controller.admin.goview.vo.data.GoViewDataRespVO;
import com.dofast.module.report.convert.goviewcode.GoViewCodeConvert;
import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;
import com.dofast.module.report.enums.ErrorCodeConstants;
import com.dofast.module.report.service.goview.GoViewCodeService;
import com.dofast.module.report.service.goview.GoViewDataService;
import com.dofast.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.dofast.module.system.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.*;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - GoView 数据", description = "提供 SQL、HTTP 等数据查询的能力")
@RestController
@RequestMapping("/report/go-view/data")
@Validated
public class GoViewDataController {

    @Resource
    private GoViewDataService goViewDataService;

    @Resource
    private GoViewCodeService goViewCodeService;

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @RequestMapping("/get-by-sql")
    @Operation(summary = "使用 SQL 查询数据")
    @PreAuthorize("@ss.hasPermission('report:go-view-data:get-by-sql')")
    @OperateLog(enable = false) // 不记录操作日志，因为不需要
    public CommonResult<GoViewDataRespVO> getDataBySQL(@Valid @RequestBody GoViewDataGetBySqlReqVO reqVO) {
        return success(goViewDataService.getDataBySQL(reqVO.getSql()));
    }

    /**
     * 生成随机码给大屏
     * @return
     */
    @GetMapping("/generate")
    @PermitAll
    // 忽略登录
    public CommonResult generateQrCode() {
        // 生成UUID随机码
        String code = UUID.randomUUID().toString().replace("-", "");
        // 保存到数据库
        GoViewCodeCreateReqVO goViewCode = new GoViewCodeCreateReqVO();
        goViewCode.setCode(code);
        goViewCode.setExpireTime(LocalDateTime.now().plusMinutes(5)); // 5分钟有效期A
        goViewCodeService.createGoViewCode(goViewCode);
        return success(Collections.singletonMap("code", code));
    }


    /**
     * 大屏周期性确认二维码状态
     * @param code
     * @return
     */
    @GetMapping("/check/{code}")
    @PermitAll
    public CommonResult checkStatus(@PathVariable String code) {
        GoViewCodeDO goViewCode = goViewCodeService.getGoViewCode(code);
        if (goViewCode == null) {
            return error(ErrorCodeConstants.GO_VIEW_CODE_NOT_EXISTS);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("status", goViewCode.getStatus());

        if (goViewCode.getStatus() == 1) {
            result.put("goviewToken", goViewCode.getGoviewToken());
            goViewCode.setStatus(2L); // 标记已确认
            goViewCodeService.updateGoViewCode(GoViewCodeConvert.INSTANCE.convert01(goViewCode));
        }
        return success(result);
    }

    /**
     * PDA扫码
     * @param request
     * @return
     */
    @PostMapping("/scan")
    @PermitAll
    public CommonResult scanQrCode(@RequestBody GoViewCodeRespVO request) {
        // 验证随机码有效性
        GoViewCodeDO goViewCode = goViewCodeService.getGoViewCode(request.getCode());
        if (goViewCode == null || LocalDateTime.now().isAfter(goViewCode.getExpireTime())) {
            return error(ErrorCodeConstants.GO_VIEW_CODE_EXPIRED);
        }

        String token = request.getGoviewToken();

        // 验证PDA token有效性
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.checkAccessToken(token);
        if(accessTokenDO == null){
            return error(ErrorCodeConstants.GO_VIEW_TOKEN_EXISTS);
        }
        Long userId = accessTokenDO.getUserId();
        System.out.println("userId: " + userId);


        return success("扫码成功");
    }



    @RequestMapping("/get-by-http")
    @Operation(summary = "使用 HTTP 查询数据", description = "这个只是示例接口，实际应该每个查询，都要写一个接口")
    @PreAuthorize("@ss.hasPermission('report:go-view-data:get-by-http')")
    @OperateLog(enable = false) // 不记录操作日志，因为不需要
    public CommonResult<GoViewDataRespVO> getDataByHttp(
            @RequestParam(required = false) Map<String, String> params,
            @RequestBody(required = false) String body) { // params、body 按照需要去接收，这里仅仅是示例
        GoViewDataRespVO respVO = new GoViewDataRespVO();
        // 1. 数据维度
        respVO.setDimensions(Arrays.asList("日期", "PV", "UV")); // PV 是每天访问次数；UV 是每天访问人数
        // 2. 明细数据列表
        // 目前通过随机的方式生成。一般来说，这里你可以写逻辑来实现数据的返回
        respVO.setSource(new LinkedList<>());
        for (int i = 1; i <= 12; i++) {
            String date = "2021-" + (i < 10 ? "0" + i : i);
            Integer pv = RandomUtil.randomInt(1000, 10000);
            Integer uv = RandomUtil.randomInt(100, 1000);
            respVO.getSource().add(MapUtil.<String, Object>builder().put("日期", date)
                    .put("PV", pv).put("UV", uv).build());
        }
        return success(respVO);
    }

}
