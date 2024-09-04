package com.dofast.module.member.controller.admin.point;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.module.member.controller.admin.point.vo.config.MemberPointConfigRespVO;
import com.dofast.module.member.controller.admin.point.vo.config.MemberPointConfigSaveReqVO;
import com.dofast.module.member.convert.point.MemberPointConfigConvert;
import com.dofast.module.member.dal.dataobject.point.MemberPointConfigDO;
import com.dofast.module.member.service.point.MemberPointConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 会员积分设置")
@RestController
@RequestMapping("/member/point/config")
@Validated
public class MemberPointConfigController {

    @Resource
    private MemberPointConfigService memberPointConfigService;

    @PutMapping("/save")
    @Operation(summary = "保存会员积分配置")
    @PreAuthorize("@ss.hasPermission('point:config:save')")
    public CommonResult<Boolean> savePointConfig(@Valid @RequestBody MemberPointConfigSaveReqVO saveReqVO) {
        memberPointConfigService.savePointConfig(saveReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员积分配置")
    @PreAuthorize("@ss.hasPermission('point:config:query')")
    public CommonResult<MemberPointConfigRespVO> getPointConfig() {
        MemberPointConfigDO config = memberPointConfigService.getPointConfig();
        return success(MemberPointConfigConvert.INSTANCE.convert(config));
    }

}
