package com.dofast.module.wms.controller.admin.transferline;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
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

import com.dofast.module.wms.controller.admin.transferline.vo.*;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;
import com.dofast.module.wms.convert.transferline.TransferLineConvert;
import com.dofast.module.wms.service.transferline.TransferLineService;

@Tag(name = "仓储管理 - 转移单行")
@RestController
@RequestMapping("/mes/wms/transfer-line")
@Validated
public class TransferLineController {

    @Resource
    private TransferLineService transferLineService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private StorageLocationService storageLocationService;
    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建转移单行")
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:create')")
    public CommonResult<Long> createTransferLine(@Valid @RequestBody TransferLineCreateReqVO createReqVO) {
        if(StrUtils.isNotNull(createReqVO.getFromWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(createReqVO.getFromWarehouseId());
            createReqVO.setFromWarehouseCode(warehouse.getWarehouseCode());
            createReqVO.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getFromLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(createReqVO.getFromLocationId());
            createReqVO.setFromLocationCode(location.getLocationCode());
            createReqVO.setFromLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(createReqVO.getFromAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(createReqVO.getFromAreaId());
            createReqVO.setFromAreaCode(area.getAreaCode());
            createReqVO.setFromAreaName(area.getAreaName());
        }
        if(StrUtils.isNotNull(createReqVO.getToWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(createReqVO.getToWarehouseId());
            createReqVO.setToWarehouseCode(warehouse.getWarehouseCode());
            createReqVO.setToWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getFromLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(createReqVO.getToLocationId());
            createReqVO.setToLocationCode(location.getLocationCode());
            createReqVO.setToLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(createReqVO.getFromAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(createReqVO.getToAreaId());
            createReqVO.setToAreaCode(area.getAreaCode());
            createReqVO.setToAreaName(area.getAreaName());
        }
        return success(transferLineService.createTransferLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新转移单行")
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:update')")
    public CommonResult<Boolean> updateTransferLine(@Valid @RequestBody TransferLineUpdateReqVO updateReqVO) {
        if(StrUtils.isNotNull(updateReqVO.getFromWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getFromWarehouseId());
            updateReqVO.setFromWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(updateReqVO.getFromLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(updateReqVO.getFromLocationId());
            updateReqVO.setFromLocationCode(location.getLocationCode());
            updateReqVO.setFromLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(updateReqVO.getFromAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(updateReqVO.getFromAreaId());
            updateReqVO.setFromAreaCode(area.getAreaCode());
            updateReqVO.setFromAreaName(area.getAreaName());
        }
        if(StrUtils.isNotNull(updateReqVO.getToWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getToWarehouseId());
            updateReqVO.setToWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setToWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(updateReqVO.getFromLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(updateReqVO.getToLocationId());
            updateReqVO.setToLocationCode(location.getLocationCode());
            updateReqVO.setToLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(updateReqVO.getFromAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(updateReqVO.getToAreaId());
            updateReqVO.setToAreaCode(area.getAreaCode());
            updateReqVO.setToAreaName(area.getAreaName());
        }
        transferLineService.updateTransferLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除转移单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:delete')")
    public CommonResult<Boolean> deleteTransferLine(@RequestParam("id") Long id) {
        transferLineService.deleteTransferLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得转移单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:query')")
    public CommonResult<TransferLineRespVO> getTransferLine(@RequestParam("id") Long id) {
        TransferLineDO transferLine = transferLineService.getTransferLine(id);
        return success(TransferLineConvert.INSTANCE.convert(transferLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得转移单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:query')")
    public CommonResult<List<TransferLineRespVO>> getTransferLineList(@RequestParam("ids") Collection<Long> ids) {
        List<TransferLineDO> list = transferLineService.getTransferLineList(ids);
        return success(TransferLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得转移单行分页")
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:query')")
    public CommonResult<PageResult<TransferLineRespVO>> getTransferLinePage(@Valid TransferLinePageReqVO pageVO) {
        PageResult<TransferLineDO> pageResult = transferLineService.getTransferLinePage(pageVO);
        return success(TransferLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出转移单行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:transfer-line:export')")
    @OperateLog(type = EXPORT)
    public void exportTransferLineExcel(@Valid TransferLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TransferLineDO> list = transferLineService.getTransferLineList(exportReqVO);
        // 导出 Excel
        List<TransferLineExcelVO> datas = TransferLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "转移单行.xls", "数据", TransferLineExcelVO.class, datas);
    }

}
