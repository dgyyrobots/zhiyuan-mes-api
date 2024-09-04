package com.dofast.module.wms.controller.admin.itemrecpt;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.ItemRecptLineListVO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.itemrecptline.ItemRecptLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
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

import com.dofast.module.wms.controller.admin.itemrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.module.wms.convert.itemrecpt.ItemRecptConvert;
import com.dofast.module.wms.service.itemrecpt.ItemRecptService;

@Tag(name = "仓储管理 - 物料入库单")
@RestController
@RequestMapping("/mes/wms/item-recpt")
@Validated
public class ItemRecptController {

    @Resource
    private ItemRecptService itemRecptService;

    @Resource
    private ItemRecptLineService itemRecptLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private StorageCoreService storageCoreServicel;

    @PostMapping("/create")
    @Operation(summary = "创建物料入库单")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:create')")
    public CommonResult<Long> createItemRecpt(@Valid @RequestBody ItemRecptCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(itemRecptService.checkRecptCodeUnique(createReqVO))){
            return  error(ErrorCodeConstants.ITEM_RECPT_CODE_EXISTS);
        }
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
        return success(itemRecptService.createItemRecpt(createReqVO));
    }

    /**
     * 确认入库单
     */
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:update')")
    @PutMapping("/confirm")
    public CommonResult confirm(@RequestBody ItemRecptUpdateReqVO wmItemRecpt){
        //检查有没有入库单行
        ItemRecptLineListVO param = new ItemRecptLineListVO();
        param.setRecptId(wmItemRecpt.getId());
        List<ItemRecptLineDO> lines = itemRecptLineService.getItemRecptLineList(param);
        if(CollUtil.isEmpty(lines)){
            return error(ErrorCodeConstants.ITEM_RECPT_LINE_INSERT);
        }

        wmItemRecpt.setStatus(Constant.ORDER_STATUS_CONFIRMED);

        if(StrUtils.isNotNull(wmItemRecpt.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(wmItemRecpt.getWarehouseId());
            wmItemRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(wmItemRecpt.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(wmItemRecpt.getLocationId());
            wmItemRecpt.setLocationCode(location.getLocationCode());
            wmItemRecpt.setLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(wmItemRecpt.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(wmItemRecpt.getAreaId());
            wmItemRecpt.setAreaCode(area.getAreaCode());
            wmItemRecpt.setAreaName(area.getAreaName());
        }
        itemRecptService.updateItemRecpt(wmItemRecpt);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料入库单")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:update')")
    public CommonResult<Boolean> updateItemRecpt(@Valid @RequestBody ItemRecptUpdateReqVO updateReqVO) {
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
        itemRecptService.updateItemRecpt(updateReqVO);
        return success(true);
    }

    /**
     * 执行入库
     * @return
     */
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:update')")
    @Transactional
    @Operation(summary = "物料执行入库")
    @PutMapping("/execute/{recptId}")
    public CommonResult execute(@PathVariable Long recptId){

        ItemRecptDO recpt = itemRecptService.getItemRecpt(recptId);

        //构造Transaction事务，并执行库存更新逻辑
        List<ItemRecptTxBean> beans = itemRecptService.getTxBeans(recptId);

        //调用库存核心
        storageCoreServicel.processItemRecpt(beans);

        //更新单据状态
        recpt.setStatus(Constant.ORDER_STATUS_FINISHED);

        ItemRecptUpdateReqVO itemRecptUpdateReqVO = ItemRecptConvert.INSTANCE.convert01(recpt);
        itemRecptService.updateItemRecpt(itemRecptUpdateReqVO);
        return success(true);
    }



    @DeleteMapping("/delete")
    @Operation(summary = "删除物料入库单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:delete')")
    public CommonResult<Boolean> deleteItemRecpt(@RequestParam("id") Long id) {
        ItemRecptDO itemRecpt = itemRecptService.getItemRecpt(id);
        if(Constant.ORDER_STATUS_PREPARE.equals(itemRecpt.getStatus())){
            return error(ErrorCodeConstants.ORDER_STATUS_PREPARE_NOT_DELETE);
        }
        itemRecptLineService.deleteByRecptId(id);
        itemRecptService.deleteItemRecpt(id);
        return success(true);
    }



    @GetMapping("/get")
    @Operation(summary = "获得物料入库单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:query')")
    public CommonResult<ItemRecptRespVO> getItemRecpt(@RequestParam("id") Long id) {
        ItemRecptDO itemRecpt = itemRecptService.getItemRecpt(id);
        return success(ItemRecptConvert.INSTANCE.convert(itemRecpt));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料入库单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:query')")
    public CommonResult<List<ItemRecptRespVO>> getItemRecptList(@RequestParam("ids") Collection<Long> ids) {
        List<ItemRecptDO> list = itemRecptService.getItemRecptList(ids);
        return success(ItemRecptConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料入库单分页")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:query')")
    public CommonResult<PageResult<ItemRecptRespVO>> getItemRecptPage(@Valid ItemRecptPageReqVO pageVO) {
        PageResult<ItemRecptDO> pageResult = itemRecptService.getItemRecptPage(pageVO);
        return success(ItemRecptConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料入库单 Excel")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt:export')")
    @OperateLog(type = EXPORT)
    public void exportItemRecptExcel(@Valid ItemRecptExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ItemRecptDO> list = itemRecptService.getItemRecptList(exportReqVO);
        // 导出 Excel
        List<ItemRecptExcelVO> datas = ItemRecptConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料入库单.xls", "数据", ItemRecptExcelVO.class, datas);
    }

}
