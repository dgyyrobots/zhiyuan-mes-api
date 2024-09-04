package com.dofast.module.wms.controller.admin.transaction;

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

import com.dofast.module.wms.controller.admin.transaction.vo.*;
import com.dofast.module.wms.dal.dataobject.transaction.TransactionDO;
import com.dofast.module.wms.convert.transaction.TransactionConvert;
import com.dofast.module.wms.service.transaction.TransactionService;

@Tag(name = "仓储管理 - 库存事务")
@RestController
@RequestMapping("/mes/wms/transaction")
@Validated
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建库存事务")
    @PreAuthorize("@ss.hasPermission('wms:transaction:create')")
    public CommonResult<Long> createTransaction(@Valid @RequestBody TransactionCreateReqVO createReqVO) {
        if(StrUtils.isNotNull(createReqVO.getWarehouseId())){
            WarehouseDO warehouseDO = warehouseService.getWarehouse(createReqVO.getWarehouseId());
            createReqVO.setWarehouseCode(warehouseDO.getWarehouseCode());
            createReqVO.setWarehouseName(warehouseDO.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getLocationId())){
            StorageLocationDO storageLocationDO = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            createReqVO.setLocationCode(storageLocationDO.getLocationCode());
            createReqVO.setLocationName(storageLocationDO.getLocationName());
        }
        if(StrUtils.isNotNull(createReqVO.getAreaId())){
            StorageAreaDO storageAreaDO = storageAreaService.getStorageArea(createReqVO.getAreaId());
            createReqVO.setAreaCode(storageAreaDO.getAreaCode());
            createReqVO.setAreaName(storageAreaDO.getAreaName());
        }
        return success(transactionService.createTransaction(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新库存事务")
    @PreAuthorize("@ss.hasPermission('wms:transaction:update')")
    public CommonResult<Boolean> updateTransaction(@Valid @RequestBody TransactionUpdateReqVO updateReqVO) {
        if(StrUtils.isNotNull(updateReqVO.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getWarehouseId());
            updateReqVO.setWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(updateReqVO.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(updateReqVO.getLocationId());
            updateReqVO.setLocationCode(location.getLocationCode());
            updateReqVO.setLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(updateReqVO.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(updateReqVO.getAreaId());
            updateReqVO.setAreaCode(area.getAreaCode());
            updateReqVO.setAreaName(area.getAreaName());
        }
        transactionService.updateTransaction(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除库存事务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:transaction:delete')")
    public CommonResult<Boolean> deleteTransaction(@RequestParam("id") Long id) {
        transactionService.deleteTransaction(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库存事务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:transaction:query')")
    public CommonResult<TransactionRespVO> getTransaction(@RequestParam("id") Long id) {
        TransactionDO transaction = transactionService.getTransaction(id);
        return success(TransactionConvert.INSTANCE.convert(transaction));
    }

    @GetMapping("/list")
    @Operation(summary = "获得库存事务列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:transaction:query')")
    public CommonResult<List<TransactionRespVO>> getTransactionList(@RequestParam("ids") Collection<Long> ids) {
        List<TransactionDO> list = transactionService.getTransactionList(ids);
        return success(TransactionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得库存事务分页")
    @PreAuthorize("@ss.hasPermission('wms:transaction:query')")
    public CommonResult<PageResult<TransactionRespVO>> getTransactionPage(@Valid TransactionPageReqVO pageVO) {
        PageResult<TransactionDO> pageResult = transactionService.getTransactionPage(pageVO);
        return success(TransactionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出库存事务 Excel")
    @PreAuthorize("@ss.hasPermission('wms:transaction:export')")
    @OperateLog(type = EXPORT)
    public void exportTransactionExcel(@Valid TransactionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TransactionDO> list = transactionService.getTransactionList(exportReqVO);
        // 导出 Excel
        List<TransactionExcelVO> datas = TransactionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "库存事务.xls", "数据", TransactionExcelVO.class, datas);
    }

}
