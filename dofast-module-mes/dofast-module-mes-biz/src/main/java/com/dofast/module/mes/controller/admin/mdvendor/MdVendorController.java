package com.dofast.module.mes.controller.admin.mdvendor;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.convert.mdvendor.PadMdVendorConvert;
import com.dofast.module.mes.enums.ErrorCodeConstants;
import com.dofast.module.wms.api.BarcodeApi.BarCodeUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import com.dofast.module.mes.controller.admin.mdvendor.vo.*;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;
import com.dofast.module.mes.service.mdvendor.PadMdVendorService;

@Tag(name = "管理后台 - 供应商")
@RestController
@RequestMapping("/mes/md-vendor")
@Validated
public class MdVendorController {

    @Resource
    private PadMdVendorService padMdVendorService;
    @Resource
    private BarCodeUtil barCodeUtil;

    @PostMapping("/create")
    @Operation(summary = "创建供应商")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:create')")
    public CommonResult<Long> createMdVendor(@Valid @RequestBody MdVendorCreateReqVO createReqVO)throws IOException {
        if(Constant.NOT_UNIQUE.equals(padMdVendorService.checkVendorCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_VENDOR_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(padMdVendorService.checkVendorNameUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_VENDOR_NAME_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(padMdVendorService.checkVendorNickUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_VENDOR_NICK_NOT_UNIQUE);
        }
        Long id = padMdVendorService.createMdVendor(createReqVO);
        barCodeUtil.generateBarCode(Constant.BARCODE_TYPE_VENDOR,id,createReqVO.getVendorCode(),createReqVO.getVendorName());
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新供应商")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:update')")
    public CommonResult<Boolean> updateMdVendor(@Valid @RequestBody MdVendorUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(padMdVendorService.checkVendorCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_VENDOR_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(padMdVendorService.checkVendorNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_VENDOR_NAME_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(padMdVendorService.checkVendorNickUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_VENDOR_NICK_NOT_UNIQUE);
        }
        padMdVendorService.updateMdVendor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除供应商")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:delete')")
    public CommonResult<Boolean> deleteMdVendor(@RequestParam("id") Long id) {
        padMdVendorService.deleteMdVendor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得供应商")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:query')")
    public CommonResult<MdVendorRespVO> getMdVendor(@RequestParam("id") Long id) {
        MdVendorDO mdVendor = padMdVendorService.getMdVendor(id);
        return success(PadMdVendorConvert.INSTANCE.convert(mdVendor));
    }

    @GetMapping("/list-by-ids")
    @Operation(summary = "获得供应商列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:query')")
    public CommonResult<List<MdVendorRespVO>> getMdVendorListByIds(@RequestParam("ids") Collection<Long> ids) {
        List<MdVendorDO> list = padMdVendorService.getMdVendorList(ids);
        return success(PadMdVendorConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得供应商分页")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:query')")
    public CommonResult<PageResult<MdVendorRespVO>> getMdVendorPage(@Valid MdVendorPageReqVO pageVO) {
        PageResult<MdVendorDO> pageResult = padMdVendorService.getMdVendorPage(pageVO);
        return success(PadMdVendorConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出供应商 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:export')")
    @OperateLog(type = EXPORT)
    public void exportMdVendorExcel(@Valid MdVendorExportReqVO exportReqVO,
                                    HttpServletResponse response) throws IOException {
        List<MdVendorDO> list = padMdVendorService.getMdVendorList(exportReqVO);
        // 导出 Excel
        List<MdVendorExcelVO> datas = PadMdVendorConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "供应商.xls", "数据", MdVendorExcelVO.class, datas);
    }

    @GetMapping("/count-all")
    @Operation(summary = "获得供应商总数")
    @PreAuthorize("@ss.hasPermission('mes:md-vendor:query')")
    public CommonResult<Integer> CountAll() {
        MdVendorExportReqVO mdVendorExportReqVO = new MdVendorExportReqVO();
        List<MdVendorDO> list = padMdVendorService.getMdVendorList(mdVendorExportReqVO);
        Integer result = list == null?0: list.size();
        return success(result);
    }
}
