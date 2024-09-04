package com.dofast.module.wms.controller.admin.barcode;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.enums.ErrorCodeConstants;
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

import com.dofast.module.wms.controller.admin.barcode.vo.*;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;
import com.dofast.module.wms.convert.barcode.BarcodeConvert;
import com.dofast.module.wms.service.barcode.BarcodeService;

@Tag(name = "仓储管理 - 条码清单")
@RestController
@RequestMapping("/mes/wms/barcode")
@Validated
public class BarcodeController {

    @Resource
    private BarcodeService barcodeService;

    @PostMapping("/create")
    @Operation(summary = "创建条码清单")
    @PreAuthorize("@ss.hasPermission('wms:barcode:create')")
    public CommonResult<Long> createBarcode(@Valid @RequestBody BarcodeCreateReqVO createReqVO) throws IOException{
        if(Constant.NOT_UNIQUE.equals(barcodeService.checkBarcodeUnique(createReqVO))){
            return error(ErrorCodeConstants.BARCODE_NOT_UNIQUE);
        }
        String path = barcodeService.generateBarcode(createReqVO);
        createReqVO.setBarcodeUrl(path);
        return success(barcodeService.createBarcode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新条码清单")
    @PreAuthorize("@ss.hasPermission('wms:barcode:update')")
    public CommonResult<Boolean> updateBarcode(@Valid @RequestBody BarcodeUpdateReqVO updateReqVO) throws IOException{
        if(Constant.NOT_UNIQUE.equals(barcodeService.checkBarcodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.BARCODE_NOT_UNIQUE);
        }
        String path =barcodeService.generateBarcode(updateReqVO);
        updateReqVO.setBarcodeUrl(path);
        barcodeService.updateBarcode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除条码清单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:barcode:delete')")
    public CommonResult<Boolean> deleteBarcode(@RequestParam("id") Long id) {
        barcodeService.deleteBarcode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得条码清单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:barcode:query')")
    public CommonResult<BarcodeRespVO> getBarcode(@RequestParam("id") Long id) {
        BarcodeDO barcode = barcodeService.getBarcode(id);
        return success(BarcodeConvert.INSTANCE.convert(barcode));
    }

    @GetMapping("/list")
    @Operation(summary = "获得条码清单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:barcode:query')")
    public CommonResult<List<BarcodeRespVO>> getBarcodeList(@RequestParam("ids") Collection<Long> ids) {
        List<BarcodeDO> list = barcodeService.getBarcodeList(ids);
        return success(BarcodeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得条码清单分页")
    @PreAuthorize("@ss.hasPermission('wms:barcode:query')")
    public CommonResult<PageResult<BarcodeRespVO>> getBarcodePage(@Valid BarcodePageReqVO pageVO) {
        PageResult<BarcodeDO> pageResult = barcodeService.getBarcodePage(pageVO);
        return success(BarcodeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出条码清单 Excel")
    @PreAuthorize("@ss.hasPermission('wms:barcode:export')")
    @OperateLog(type = EXPORT)
    public void exportBarcodeExcel(@Valid BarcodeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BarcodeDO> list = barcodeService.getBarcodeList(exportReqVO);
        // 导出 Excel
        List<BarcodeExcelVO> datas = BarcodeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "条码清单.xls", "数据", BarcodeExcelVO.class, datas);
    }

}
