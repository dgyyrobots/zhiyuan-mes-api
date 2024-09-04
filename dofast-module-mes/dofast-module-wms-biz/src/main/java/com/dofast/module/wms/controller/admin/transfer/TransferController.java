package com.dofast.module.wms.controller.admin.transfer;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.transferline.vo.TransferLineExportReqVO;
import com.dofast.module.wms.controller.admin.transferline.vo.TransferLineListVO;
import com.dofast.module.wms.dal.dataobject.transfer.TransferTxBean;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.transferline.TransferLineService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
import javax.xml.crypto.dsig.Transform;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.transfer.vo.*;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;
import com.dofast.module.wms.convert.transfer.TransferConvert;
import com.dofast.module.wms.service.transfer.TransferService;

@Tag(name = "仓储管理 - 转移单")
@RestController
@RequestMapping("/mes/wms/transfer")
@Validated
public class TransferController {

    @Resource
    private TransferService transferService;
    @Resource
    private TransferLineService transferLineService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private StorageCoreService storageCoreService;

    @PostMapping("/create")
    @Operation(summary = "创建转移单")
    @PreAuthorize("@ss.hasPermission('wms:transfer:create')")
    public CommonResult<Long> createTransfer(@Valid @RequestBody TransferCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(transferService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.TRANSFER_CODE_EXISTS);
        }
        if(StrUtils.isNotNull(createReqVO.getFromWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(createReqVO.getFromWarehouseId());
            createReqVO.setFromWarehouseCode(warehouse.getWarehouseCode());
            createReqVO.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getToWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(createReqVO.getToWarehouseId());
            createReqVO.setToWarehouseCode(warehouse.getWarehouseCode());
            createReqVO.setToWarehouseName(warehouse.getWarehouseName());
        }
        return success(transferService.createTransfer(createReqVO));
    }

    /**
     * 执行退货
     */
    @PreAuthorize("@ss.hasPermission('wms:transfer:update')")
    @Transactional
    @PutMapping("/{transferId}")
    public CommonResult execute(@PathVariable Long transferId){
        TransferDO transfer = transferService.getTransfer(transferId);

        TransferLineExportReqVO transferLineExportReqVO = new TransferLineExportReqVO();
        transferLineExportReqVO.setTransferId(transferId);
        List<TransferLineDO> lines = transferLineService.getTransferLineList(transferLineExportReqVO);
        if(CollectionUtils.isEmpty(lines)){
            return error(ErrorCodeConstants.TRANSFER_NEED_ITEM);
        }

        List<TransferTxBean> beans = transferService.getTxBeans(transferId);

        if(CollectionUtils.isEmpty(beans)){
            return error(ErrorCodeConstants.TRANSFER_NEED_LINE);
        }

        storageCoreService.processTransfer(beans);


        transfer.setStatus(Constant.ORDER_STATUS_FINISHED);

        TransferUpdateReqVO updateReqVO = TransferConvert.INSTANCE.convert01(transfer);
        transferService.updateTransfer(updateReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新转移单")
    @PreAuthorize("@ss.hasPermission('wms:transfer:update')")
    public CommonResult<Boolean> updateTransfer(@Valid @RequestBody TransferUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(transferService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.TRANSFER_CODE_EXISTS);
        }
        if(StrUtils.isNotNull(updateReqVO.getFromWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getFromWarehouseId());
            updateReqVO.setFromWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(updateReqVO.getToWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getToWarehouseId());
            updateReqVO.setToWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setToWarehouseName(warehouse.getWarehouseName());
        }
        transferService.updateTransfer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除转移单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:transfer:delete')")
    public CommonResult<Boolean> deleteTransfer(@RequestParam("id") Long id) {
        transferLineService.deleteByTransferId(id);
        transferService.deleteTransfer(id);
        return success(true);
    }



    @GetMapping("/get")
    @Operation(summary = "获得转移单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:transfer:query')")
    public CommonResult<TransferRespVO> getTransfer(@RequestParam("id") Long id) {
        TransferDO transfer = transferService.getTransfer(id);
        return success(TransferConvert.INSTANCE.convert(transfer));
    }

    @GetMapping("/list")
    @Operation(summary = "获得转移单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:transfer:query')")
    public CommonResult<List<TransferRespVO>> getTransferList(@RequestParam("ids") Collection<Long> ids) {
        List<TransferDO> list = transferService.getTransferList(ids);
        return success(TransferConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得转移单分页")
    @PreAuthorize("@ss.hasPermission('wms:transfer:query')")
    public CommonResult<PageResult<TransferRespVO>> getTransferPage(@Valid TransferPageReqVO pageVO) {
        PageResult<TransferDO> pageResult = transferService.getTransferPage(pageVO);
        return success(TransferConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出转移单 Excel")
    @PreAuthorize("@ss.hasPermission('wms:transfer:export')")
    @OperateLog(type = EXPORT)
    public void exportTransferExcel(@Valid TransferExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TransferDO> list = transferService.getTransferList(exportReqVO);
        // 导出 Excel
        List<TransferExcelVO> datas = TransferConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "转移单.xls", "数据", TransferExcelVO.class, datas);
    }

}
