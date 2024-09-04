package com.dofast.module.wms.controller.admin.barcodeconfig;

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
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.barcodeconfig.vo.*;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;
import com.dofast.module.wms.convert.barcodeconfig.BarcodeConfigConvert;
import com.dofast.module.wms.service.barcodeconfig.BarcodeConfigService;

@Tag(name = "仓储管理 - 条码配置")
@RestController
@RequestMapping("/mes/wms/barcode-config")
@Validated
public class BarcodeConfigController {

    @Resource
    private BarcodeConfigService barcodeConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建条码配置")
    @PreAuthorize("@ss.hasPermission('wms:barcode-config:create')")
    public CommonResult<Long> createBarcodeConfig(@Valid @RequestBody BarcodeConfigCreateReqVO createReqVO) {
        return success(barcodeConfigService.createBarcodeConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新条码配置")
    @PreAuthorize("@ss.hasPermission('wms:barcode-config:update')")
    public CommonResult<Boolean> updateBarcodeConfig(@Valid @RequestBody BarcodeConfigUpdateReqVO updateReqVO) {
        barcodeConfigService.updateBarcodeConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除条码配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:barcode-config:delete')")
    public CommonResult<Boolean> deleteBarcodeConfig(@RequestParam("id") Long id) {
        barcodeConfigService.deleteBarcodeConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得条码配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<BarcodeConfigRespVO> getBarcodeConfig(@RequestParam("id") Long id) {
        BarcodeConfigDO barcodeConfig = barcodeConfigService.getBarcodeConfig(id);
        return success(BarcodeConfigConvert.INSTANCE.convert(barcodeConfig));
    }

    @GetMapping("/list")
    @Operation(summary = "获得条码配置列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public CommonResult<List<BarcodeConfigRespVO>> getBarcodeConfigList(@RequestParam("ids") Collection<Long> ids) {
        List<BarcodeConfigDO> list = barcodeConfigService.getBarcodeConfigList(ids);
        return success(BarcodeConfigConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得条码配置分页")
    public CommonResult<PageResult<BarcodeConfigRespVO>> getBarcodeConfigPage(@Valid BarcodeConfigPageReqVO pageVO) {
        PageResult<BarcodeConfigDO> pageResult = barcodeConfigService.getBarcodeConfigPage(pageVO);
        return success(BarcodeConfigConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出条码配置 Excel")
    @PreAuthorize("@ss.hasPermission('wms:barcode-config:export')")
    @OperateLog(type = EXPORT)
    public void exportBarcodeConfigExcel(@Valid BarcodeConfigExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BarcodeConfigDO> list = barcodeConfigService.getBarcodeConfigList(exportReqVO);
        // 导出 Excel
        List<BarcodeConfigExcelVO> datas = BarcodeConfigConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "条码配置.xls", "数据", BarcodeConfigExcelVO.class, datas);
    }

}
