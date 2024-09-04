package com.dofast.module.wms.controller.admin.itemrecptline;

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

import com.dofast.module.wms.controller.admin.itemrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.module.wms.convert.itemrecptline.ItemRecptLineConvert;
import com.dofast.module.wms.service.itemrecptline.ItemRecptLineService;

@Tag(name = "仓储管理 - 物料入库单行")
@RestController
@RequestMapping("/mes/wms/item-recpt-line")
@Validated
public class ItemRecptLineController {

    @Resource
    private ItemRecptLineService itemRecptLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建物料入库单行")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:create')")
    public CommonResult<Long> createItemRecptLine(@Valid @RequestBody ItemRecptLineCreateReqVO createReqVO) {
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
        return success(itemRecptLineService.createItemRecptLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料入库单行")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:update')")
    public CommonResult<Boolean> updateItemRecptLine(@Valid @RequestBody ItemRecptLineUpdateReqVO updateReqVO) {
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
        itemRecptLineService.updateItemRecptLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料入库单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:delete')")
    public CommonResult<Boolean> deleteItemRecptLine(@RequestParam("id") Long id) {
        itemRecptLineService.deleteItemRecptLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料入库单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:query')")
    public CommonResult<ItemRecptLineRespVO> getItemRecptLine(@RequestParam("id") Long id) {
        ItemRecptLineDO itemRecptLine = itemRecptLineService.getItemRecptLine(id);
        return success(ItemRecptLineConvert.INSTANCE.convert(itemRecptLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料入库单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:query')")
    public CommonResult<List<ItemRecptLineRespVO>> getItemRecptLineList(@RequestParam("ids") Collection<Long> ids) {
        List<ItemRecptLineDO> list = itemRecptLineService.getItemRecptLineList(ids);
        return success(ItemRecptLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料入库单行分页")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:query')")
    public CommonResult<PageResult<ItemRecptLineRespVO>> getItemRecptLinePage(@Valid ItemRecptLinePageReqVO pageVO) {
        PageResult<ItemRecptLineDO> pageResult = itemRecptLineService.getItemRecptLinePage(pageVO);
        return success(ItemRecptLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料入库单行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:item-recpt-line:export')")
    @OperateLog(type = EXPORT)
    public void exportItemRecptLineExcel(@Valid ItemRecptLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ItemRecptLineDO> list = itemRecptLineService.getItemRecptLineList(exportReqVO);
        // 导出 Excel
        List<ItemRecptLineExcelVO> datas = ItemRecptLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料入库单行.xls", "数据", ItemRecptLineExcelVO.class, datas);
    }

}
