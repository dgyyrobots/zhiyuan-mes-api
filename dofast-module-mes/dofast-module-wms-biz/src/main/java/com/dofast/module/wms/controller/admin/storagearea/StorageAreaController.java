package com.dofast.module.wms.controller.admin.storagearea;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.utils.WmsBarCodeUtil;
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
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.storagearea.vo.*;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.convert.storagearea.StorageAreaConvert;
import com.dofast.module.wms.service.storagearea.StorageAreaService;

@Tag(name = "仓储管理 - 库位")
@RestController
@RequestMapping("/mes/wms/storage-area")
@Validated
public class StorageAreaController {

    @Resource
    private StorageAreaService storageAreaService;
    @Resource
    private WmsBarCodeUtil barCodeUtil;

    @PostMapping("/create")
    @Operation(summary = "创建库位")
    @PreAuthorize("@ss.hasPermission('wms:storage-area:create')")
    public CommonResult<Long> createStorageArea(@Valid @RequestBody StorageAreaCreateReqVO createReqVO) throws IOException{
        storageAreaService.createStorageArea(createReqVO);
        barCodeUtil.generateBarCode(Constant.BARCODE_TYPE_STORAGEAREA,createReqVO.getId(),createReqVO.getAreaCode(), createReqVO.getAreaName());
        return success(createReqVO.getId());
    }

    @PutMapping("/update")
    @Operation(summary = "更新库位")
    @PreAuthorize("@ss.hasPermission('wms:storage-area:update')")
    public CommonResult<Boolean> updateStorageArea(@Valid @RequestBody StorageAreaUpdateReqVO updateReqVO) {
        storageAreaService.updateStorageArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除库位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:storage-area:delete')")
    public CommonResult<Boolean> deleteStorageArea(@RequestParam("id") Long id) {
        storageAreaService.deleteStorageArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:storage-area:query')")
    public CommonResult<StorageAreaRespVO> getStorageArea(@RequestParam("id") Long id) {
        StorageAreaDO storageArea = storageAreaService.getStorageArea(id);
        return success(StorageAreaConvert.INSTANCE.convert(storageArea));
    }

    @GetMapping("/list")
    @Operation(summary = "获得库位列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:storage-area:query')")
    public CommonResult<List<StorageAreaRespVO>> getStorageAreaList(@RequestParam("ids") Collection<Long> ids) {
        List<StorageAreaDO> list = storageAreaService.getStorageAreaList(ids);
        return success(StorageAreaConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得库位分页")
    @PreAuthorize("@ss.hasPermission('wms:storage-area:query')")
    public CommonResult<PageResult<StorageAreaRespVO>> getStorageAreaPage(@Valid StorageAreaPageReqVO pageVO) {
        PageResult<StorageAreaDO> pageResult = storageAreaService.getStorageAreaPage(pageVO);
        return success(StorageAreaConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出库位 Excel")
    @PreAuthorize("@ss.hasPermission('wms:storage-area:export')")
    @OperateLog(type = EXPORT)
    public void exportStorageAreaExcel(@Valid StorageAreaExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<StorageAreaDO> list = storageAreaService.getStorageAreaList(exportReqVO);
        // 导出 Excel
        List<StorageAreaExcelVO> datas = StorageAreaConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "库位.xls", "数据", StorageAreaExcelVO.class, datas);
    }

}
