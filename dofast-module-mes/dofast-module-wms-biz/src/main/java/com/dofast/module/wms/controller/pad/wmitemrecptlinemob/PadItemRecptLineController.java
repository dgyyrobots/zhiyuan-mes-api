package com.dofast.module.wms.controller.pad.wmitemrecptlinemob;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.page.PadTableDataInfo;
import com.dofast.framework.common.pad.util.PadSecurityUtils;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.ItemRecptLineCreateReqVO;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.ItemRecptLineExportReqVO;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.ItemRecptLineUpdateReqVO;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.service.itemrecptline.ItemRecptLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "PAD仓储管理 - 物料入库单行")
@RestController
@RequestMapping("/mobile/wm/itemrecptline")
@Validated
public class PadItemRecptLineController extends PadBaseController {

    @Resource
    private ItemRecptLineService itemRecptLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;
    
    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private AdminUserApi adminUserApi;

    /**
     * 查询物料入库单行列表
     */
    @Operation(summary = "查询采购入库单明细信息接口")
    @PreAuthenticated
    @GetMapping("/list")
    public PadTableDataInfo list(ItemRecptLineDO wmItemRecptLine)
    {
        startPage();
        ItemRecptLineExportReqVO itemRecptLineExportReqVO = BeanUtil.toBean(wmItemRecptLine, ItemRecptLineExportReqVO.class);
        List<ItemRecptLineDO> list = itemRecptLineService.getItemRecptLineList(itemRecptLineExportReqVO);
        return getDataTable(list);
    }

    /**
     * 获取物料入库单行详细信息
     */
    @Operation(summary = "查看采购入库单明细信息接口")
    @PreAuthenticated
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(itemRecptLineService.getItemRecptLine(lineId));
    }

    /**
     * 新增物料入库单行
     */
    @Operation(summary = "新增采购入库单明细信息接口")
    @PreAuthenticated
    @PostMapping
    public AjaxResult add(@RequestBody ItemRecptLineDO wmItemRecptLine)
    {
        if(PadStringUtils.isNotNull(wmItemRecptLine.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(wmItemRecptLine.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }else if(PadStringUtils.isNotNull(wmItemRecptLine.getWarehouseCode())){
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(wmItemRecptLine.getWarehouseCode());
            wmItemRecptLine.setWarehouseId(warehouse.getId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }

        if(PadStringUtils.isNotNull(wmItemRecptLine.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(wmItemRecptLine.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        } else if(PadStringUtils.isNotNull(wmItemRecptLine.getLocationCode())){
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(wmItemRecptLine.getLocationCode());
            wmItemRecptLine.setLocationId(location.getId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        }

        if(PadStringUtils.isNotNull(wmItemRecptLine.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(wmItemRecptLine.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        } else if(PadStringUtils.isNotNull(wmItemRecptLine.getAreaCode())){
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(wmItemRecptLine.getAreaCode());
            wmItemRecptLine.setAreaId(area.getId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        }
        Long userId = PadSecurityUtils.getUserId();

        AdminUserRespDTO user = adminUserApi.getUser(userId);

        wmItemRecptLine.setCreator(user.getUsername());
        ItemRecptLineCreateReqVO itemRecptLineCreateReqVO = BeanUtil.toBean(wmItemRecptLine, ItemRecptLineCreateReqVO.class);
        itemRecptLineService.createItemRecptLine(itemRecptLineCreateReqVO);
        return AjaxResult.success(wmItemRecptLine);
    }

    /**
     * 修改物料入库单行
     */
    @Operation(summary = "修改采购入库单明细信息接口")
    @PreAuthenticated
    @PutMapping
    public AjaxResult edit(@RequestBody ItemRecptLineDO wmItemRecptLine)
    {
        if(PadStringUtils.isNotNull(wmItemRecptLine.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(wmItemRecptLine.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }else if(PadStringUtils.isNotNull(wmItemRecptLine.getWarehouseCode())){
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(wmItemRecptLine.getWarehouseCode());
            wmItemRecptLine.setWarehouseId(warehouse.getId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }

        if(PadStringUtils.isNotNull(wmItemRecptLine.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(wmItemRecptLine.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        } else if(PadStringUtils.isNotNull(wmItemRecptLine.getLocationCode())){
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(wmItemRecptLine.getLocationCode());
            wmItemRecptLine.setLocationId(location.getId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        }

        if(PadStringUtils.isNotNull(wmItemRecptLine.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(wmItemRecptLine.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        } else if(PadStringUtils.isNotNull(wmItemRecptLine.getAreaCode())){
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(wmItemRecptLine.getAreaCode());
            wmItemRecptLine.setAreaId(area.getId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        }
        ItemRecptLineUpdateReqVO itemRecptLineUpdateReqVO = BeanUtil.toBean(wmItemRecptLine, ItemRecptLineUpdateReqVO.class);
        itemRecptLineService.updateItemRecptLine(itemRecptLineUpdateReqVO);
        return toAjax(true);
    }


    /**
     * 删除物料入库单行
     */
    @Operation(summary = "删除采购入库单明细信息接口")
    @PreAuthenticated
    @DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        if (lineIds != null){
            for(Long id : lineIds){
                itemRecptLineService.deleteItemRecptLine(id);
            }
        }
        return toAjax(true);
    }
}
