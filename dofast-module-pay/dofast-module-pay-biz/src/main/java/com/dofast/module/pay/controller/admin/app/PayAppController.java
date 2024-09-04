package com.dofast.module.pay.controller.admin.app;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.pay.core.enums.PayChannelEnum;
import com.dofast.module.pay.controller.admin.app.vo.*;

import com.dofast.module.pay.controller.admin.merchant.vo.app.PayAppExcelVO;
import com.dofast.module.pay.controller.admin.merchant.vo.app.PayAppExportReqVO;


import com.dofast.module.pay.convert.app.PayAppConvert;
import com.dofast.module.pay.dal.dataobject.app.PayAppDO;
import com.dofast.module.pay.dal.dataobject.channel.PayChannelDO;
import com.dofast.module.pay.dal.dataobject.merchant.PayMerchantDO;
import com.dofast.module.pay.service.app.PayAppService;
import com.dofast.module.pay.service.channel.PayChannelService;
import com.dofast.module.pay.service.merchant.PayMerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertList;

import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;



@Slf4j
@Tag(name = "管理后台 - 支付应用信息")
@RestController
@RequestMapping("/pay/app")
@Validated
public class PayAppController {

    @Resource
    private PayAppService appService;
    @Resource
    private PayChannelService channelService;




    @Resource
    private PayMerchantService merchantService;


    @PostMapping("/create")
    @Operation(summary = "创建支付应用信息")
    @PreAuthorize("@ss.hasPermission('pay:app:create')")
    public CommonResult<Long> createApp(@Valid @RequestBody PayAppCreateReqVO createReqVO) {
        return success(appService.createApp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新支付应用信息")
    @PreAuthorize("@ss.hasPermission('pay:app:update')")
    public CommonResult<Boolean> updateApp(@Valid @RequestBody PayAppUpdateReqVO updateReqVO) {
        appService.updateApp(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新支付应用状态")
    @PreAuthorize("@ss.hasPermission('pay:app:update')")
    public CommonResult<Boolean> updateAppStatus(@Valid @RequestBody PayAppUpdateStatusReqVO updateReqVO) {
        appService.updateAppStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除支付应用信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pay:app:delete')")
    public CommonResult<Boolean> deleteApp(@RequestParam("id") Long id) {
        appService.deleteApp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得支付应用信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PayAppRespVO> getApp(@RequestParam("id") Long id) {
        PayAppDO app = appService.getApp(id);
        return success(PayAppConvert.INSTANCE.convert(app));
    }


    @GetMapping("/page")
    @Operation(summary = "获得支付应用信息分页")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PageResult<PayAppPageItemRespVO>> getAppPage(@Valid PayAppPageReqVO pageVO) {
        // 得到应用分页列表
        PageResult<PayAppDO> pageResult = appService.getAppPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 得到所有的应用编号，查出所有的渠道
        Collection<Long> appIds = convertList(pageResult.getList(), PayAppDO::getId);
        List<PayChannelDO> channels = channelService.getChannelListByAppIds(appIds);

        // 拼接后返回
        return success(PayAppConvert.INSTANCE.convertPage(pageResult, channels));
    }

    @GetMapping("/list")
    @Operation(summary = "获得应用列表")
    @PreAuthorize("@ss.hasPermission('pay:merchant:query')")
    public CommonResult<List<PayAppRespVO>> getAppList() {
        List<PayAppDO> appListDO = appService.getAppList();
        return success(PayAppConvert.INSTANCE.convertList(appListDO));
    }


    @GetMapping("/list-NEW")
    @Operation(summary = "获得支付应用信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<List<PayAppRespVO>> getAppList(@RequestParam("ids") Collection<Long> ids) {
        List<PayAppDO> list = appService.getAppList(ids);
        return success(PayAppConvert.INSTANCE.convertList1(list));
    }

    /*@GetMapping("/page")
    @Operation(summary = "获得支付应用信息分页")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PageResult<PayAppPageItemRespVO>> getAppPage(@Valid PayAppPageReqVO pageVO) {
        // 得到应用分页列表
        PageResult<PayAppDO> pageResult = appService.getAppPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 得到所有的应用编号，查出所有的渠道
        Collection<Long> appIds = convertList(pageResult.getList(), PayAppDO::getId);
        List<PayChannelDO> channels = channelService.getChannelListByAppIds(appIds);

        // 拼接后返回
        return success(PayAppConvert.INSTANCE.convertPage(pageResult, channels));
    }*/


    /*@GetMapping("/page")
    @Operation(summary = "获得支付应用信息分页")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PageResult<PayAppPageItemRespVO>> getAppPage(@Valid PayAppPageReqVO pageVO) {
        // 得到应用分页列表
        PageResult<PayAppDO> pageResult = appService.getAppPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        // 得到所有的应用编号，查出所有的渠道
        Collection<Long> payAppIds = CollectionUtils.convertList(pageResult.getList(), PayAppDO::getId);
        List<PayChannelDO> channels = channelService.getChannelListByAppIds(payAppIds);
        // TODO @aquan：可以基于 appId 简历一个 multiMap。这样下面，直接 get 到之后，CollUtil buildSet 即可
        Iterator<PayChannelDO> iterator = channels.iterator();

        // 得到所有的商户信息
        Collection<Long> merchantIds = CollectionUtils.convertList(pageResult.getList(), PayAppDO::getMerchantId);
        Map<Long, PayMerchantDO> deptMap = merchantService.getMerchantMap(merchantIds);

        // 利用反射将渠道数据复制到返回的数据结构中去
        List<PayAppPageItemRespVO> appList = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(app -> {
            // 写入应用信息的数据
            PayAppPageItemRespVO respVO = PayAppConvert.INSTANCE.pageConvert(app);
            // 写入商户的数据
            Long merchantId = app.getMerchantId();
            deptMap.get(merchantId);
            respVO.setPayMerchant(PayAppConvert.INSTANCE.convert(deptMap.get(app.getMerchantId())));
            // 写入支付渠道信息的数据
            Set<String> channelCodes = new HashSet<>(PayChannelEnum.values().length);
            while (iterator.hasNext()) {
                PayChannelDO channelDO = iterator.next();
                if (channelDO.getAppId().equals(app.getId())) {
                    channelCodes.add(channelDO.getCode());
                    iterator.remove();
                }
            }
            respVO.setChannelCodes(channelCodes);

            appList.add(respVO);
        });


        return success(new PageResult<>(appList, pageResult.getTotal()));
    }*/

    /*@GetMapping("/export-excel")
    @Operation(summary = "导出支付应用信息 Excel")
    @PreAuthorize("@ss.hasPermission('pay:app:export')")
    @OperateLog(type = EXPORT)
    public void exportAppExcel(@Valid PayAppExportReqVO exportReqVO,
                               HttpServletResponse response) throws IOException {
        List<PayAppDO> list = appService.getAppList(exportReqVO);
        // 导出 Excel
        List<PayAppExcelVO> datas = PayAppConvert.INSTANCE.convertList30(list);
        ExcelUtils.write(response, "支付应用信息.xls", "数据", PayAppExcelVO.class, datas);
    }*/

    @GetMapping("/list-merchant-id")
    @Operation(summary = "根据商户 ID 查询支付应用信息")
    @Parameter(name = "merchantId", description = "商户ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('pay:merchant:query')")
    public CommonResult<List<PayAppRespVO>> getMerchantListByName(@RequestParam Long merchantId) {
        List<PayAppDO> appListDO = appService.getListByMerchantId(merchantId);
        return success(PayAppConvert.INSTANCE.convertList25(appListDO));
    }




    @PostMapping("/create-NEW")
    @Operation(summary = "创建支付应用信息")
    @PreAuthorize("@ss.hasPermission('pay:app:create')")
    public CommonResult<Long> createApp1(@Valid @RequestBody PayAppCreateReqVO createReqVO) {
        return success(appService.createApp(createReqVO));
    }

    @PutMapping("/update-NEW")
    @Operation(summary = "更新支付应用信息")
    @PreAuthorize("@ss.hasPermission('pay:app:update')")
    public CommonResult<Boolean> updateApp1(@Valid @RequestBody PayAppUpdateReqVO updateReqVO) {
        appService.updateApp(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status-NEW")
    @Operation(summary = "更新支付应用状态")
    @PreAuthorize("@ss.hasPermission('pay:app:update')")
    public CommonResult<Boolean> updateAppStatus1(@Valid @RequestBody PayAppUpdateStatusReqVO updateReqVO) {
        appService.updateAppStatus(updateReqVO.getId(), updateReqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete-NEW")
    @Operation(summary = "删除支付应用信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pay:app:delete')")
    public CommonResult<Boolean> deleteApp1(@RequestParam("id") Long id) {
        appService.deleteApp(id);
        return success(true);
    }

    @GetMapping("/get-NEW")
    @Operation(summary = "获得支付应用信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PayAppRespVO> getApp1(@RequestParam("id") Long id) {
        PayAppDO app = appService.getApp(id);
        return success(PayAppConvert.INSTANCE.convert(app));
    }

    @GetMapping("/page-NEW")
    @Operation(summary = "获得支付应用信息分页")
    @PreAuthorize("@ss.hasPermission('pay:app:query')")
    public CommonResult<PageResult<PayAppPageItemRespVO>> getAppPage1(@Valid PayAppPageReqVO pageVO) {
        // 得到应用分页列表
        PageResult<PayAppDO> pageResult = appService.getAppPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 得到所有的应用编号，查出所有的渠道
        Collection<Long> appIds = convertList(pageResult.getList(), PayAppDO::getId);
        List<PayChannelDO> channels = channelService.getChannelListByAppIds(appIds);

        // 拼接后返回
        return success(PayAppConvert.INSTANCE.convertPage(pageResult, channels));
    }

    /*@GetMapping("/list-NEW")
    @Operation(summary = "获得应用列表")
    @PreAuthorize("@ss.hasPermission('pay:merchant:query')")
    public CommonResult<List<PayAppRespVO>> getAppList1() {


        PageResult<PayAppPageItemRespVO> payAppPageItemRespVOPageResult = new PageResult<>(appList, pageResult.getTotal());
        return success(payAppPageItemRespVOPageResult);
    }*/

}
