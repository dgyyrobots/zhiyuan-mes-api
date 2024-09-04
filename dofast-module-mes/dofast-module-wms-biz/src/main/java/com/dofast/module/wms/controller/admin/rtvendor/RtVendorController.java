package com.dofast.module.wms.controller.admin.rtvendor;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorTxBean;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.rtvendorline.RtVendorLineService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.rtvendor.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorDO;
import com.dofast.module.wms.convert.rtvendor.RtVendorConvert;
import com.dofast.module.wms.service.rtvendor.RtVendorService;

@Tag(name = "仓储管理 - 供应商退货")
@RestController
@RequestMapping("/mes/wms/rt-vendor")
@Validated
public class RtVendorController {

    @Resource
    private RtVendorService rtVendorService;

    @Resource
    private RtVendorLineService rtVendorLineService;

    @Resource
    private StorageCoreService storageCoreService;

    @PostMapping("/create")
    @Operation(summary = "创建供应商退货")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:create')")
    public CommonResult<Long> createRtVendor(@Valid @RequestBody RtVendorCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtVendorService.checkCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.RT_VENDOR_CODE_EXISTS);
        }
        return success(rtVendorService.createRtVendor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新供应商退货")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:update')")
    public CommonResult<Boolean> updateRtVendor(@Valid @RequestBody RtVendorUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtVendorService.checkCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.RT_VENDOR_CODE_EXISTS);
        }
        rtVendorService.updateRtVendor(updateReqVO);
        return success(true);
    }

    /**
     * 执行退货
     */
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:update')")
    @Transactional
    @PutMapping("/{rtId}")
    @Operation(summary = "执行退货")
    public CommonResult execute(@PathVariable Long rtId){
        //判断单据状态
        RtVendorDO wmRtVendor = rtVendorService.getRtVendor(rtId);

        //构造事务Bean
        List<RtVendorTxBean> beans = rtVendorService.getTxBeans(rtId);

        //调用库存核心
        storageCoreService.processRtVendor(beans);

        //更新单据状态
        RtVendorDO rtVendor = rtVendorService.getRtVendor(rtId);
        rtVendor.setStatus(Constant.ORDER_STATUS_FINISHED);
        RtVendorUpdateReqVO updateReqVO = RtVendorConvert.INSTANCE.convert01(rtVendor);
        rtVendorService.updateRtVendor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除供应商退货")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:delete')")
    public CommonResult<Boolean> deleteRtVendor(@RequestParam("id") Long id) {
        RtVendorDO rtVendor = rtVendorService.getRtVendor(id);
        if(!Constant.ORDER_STATUS_PREPARE.equals(rtVendor.getStatus())){
            return error(ErrorCodeConstants.ORDER_STATUS_PREPARE_NOT_DELETE);
        }
        rtVendorLineService.deleteByRtId(id);
        rtVendorService.deleteRtVendor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得供应商退货")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:query')")
    public CommonResult<RtVendorRespVO> getRtVendor(@RequestParam("id") Long id) {
        RtVendorDO rtVendor = rtVendorService.getRtVendor(id);
        return success(RtVendorConvert.INSTANCE.convert(rtVendor));
    }

    @GetMapping("/list")
    @Operation(summary = "获得供应商退货列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:query')")
    public CommonResult<List<RtVendorRespVO>> getRtVendorList(@RequestParam("ids") Collection<Long> ids) {
        List<RtVendorDO> list = rtVendorService.getRtVendorList(ids);
        return success(RtVendorConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得供应商退货分页")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:query')")
    public CommonResult<PageResult<RtVendorRespVO>> getRtVendorPage(@Valid RtVendorPageReqVO pageVO) {
        PageResult<RtVendorDO> pageResult = rtVendorService.getRtVendorPage(pageVO);
        return success(RtVendorConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出供应商退货 Excel")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor:export')")
    @OperateLog(type = EXPORT)
    public void exportRtVendorExcel(@Valid RtVendorExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RtVendorDO> list = rtVendorService.getRtVendorList(exportReqVO);
        // 导出 Excel
        List<RtVendorExcelVO> datas = RtVendorConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "供应商退货.xls", "数据", RtVendorExcelVO.class, datas);
    }

}
