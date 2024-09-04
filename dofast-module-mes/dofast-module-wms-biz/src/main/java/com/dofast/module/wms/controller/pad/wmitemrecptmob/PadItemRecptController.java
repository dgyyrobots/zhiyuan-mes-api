package com.dofast.module.wms.controller.pad.wmitemrecptmob;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.util.PadSecurityUtils;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.Autocode.AutoCodeUtil;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.ItemRecptBaseVO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.ItemRecptCreateReqVO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.ItemRecptUpdateReqVO;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.ItemRecptLineExportReqVO;
import com.dofast.module.wms.convert.itemrecpt.ItemRecptConvert;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.service.itemrecpt.ItemRecptService;
import com.dofast.module.wms.service.itemrecptline.ItemRecptLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "PAD仓储管理 - 物料入库单")
@RestController
@RequestMapping("/mobile/wm/itemrecpt")
@Validated
public class PadItemRecptController extends PadBaseController {

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

    @Resource
    private AutoCodeUtil autoCodeUtil;

    @Resource
    private AdminUserApi adminUserApi;

    /**
     * 新增物料入库单
     */
    @Operation(summary = "新增采购入库单基本信息接口")
    @PreAuthenticated
    @PostMapping
    public AjaxResult add(@RequestBody ItemRecptDO wmItemRecpt)
    {
        if(PadStringUtils.isNotNull(wmItemRecpt.getRecptCode())){
            ItemRecptBaseVO itemRecptBaseVO = BeanUtil.toBean(wmItemRecpt, ItemRecptBaseVO.class);
            if(UserConstants.NOT_UNIQUE.equals(itemRecptService.checkRecptCodeUnique(itemRecptBaseVO))){
                return  AjaxResult.error("单据编号已存在！");
            }
        }else {
            wmItemRecpt.setRecptCode(autoCodeUtil.genSerialCode(UserConstants.ITEMRECPT_CODE,""));
        }


        if(PadStringUtils.isNotNull(wmItemRecpt.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(wmItemRecpt.getWarehouseId());
            wmItemRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(PadStringUtils.isNotNull(wmItemRecpt.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(wmItemRecpt.getLocationId());
            wmItemRecpt.setLocationCode(location.getLocationCode());
            wmItemRecpt.setLocationName(location.getLocationName());
        }
        if(PadStringUtils.isNotNull(wmItemRecpt.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(wmItemRecpt.getAreaId());
            wmItemRecpt.setAreaCode(area.getAreaCode());
            wmItemRecpt.setAreaName(area.getAreaName());
        }
        Long userId = PadSecurityUtils.getUserId();

        AdminUserRespDTO user = adminUserApi.getUser(userId);
        wmItemRecpt.setCreator(user.getUsername());
        ItemRecptCreateReqVO itemRecptCreateReqVO = BeanUtil.toBean(wmItemRecpt, ItemRecptCreateReqVO.class);
        itemRecptService.createItemRecpt(itemRecptCreateReqVO);
        return AjaxResult.success(wmItemRecpt);
    }

    /**
     * 修改物料入库单
     */
    @Operation(summary = "修改采购入库单基本信息接口")
    @PreAuthenticated
    @PutMapping
    public AjaxResult edit(@RequestBody ItemRecptDO wmItemRecpt)
    {
        if(PadStringUtils.isNotNull(wmItemRecpt.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(wmItemRecpt.getWarehouseId());
            wmItemRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(PadStringUtils.isNotNull(wmItemRecpt.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(wmItemRecpt.getLocationId());
            wmItemRecpt.setLocationCode(location.getLocationCode());
            wmItemRecpt.setLocationName(location.getLocationName());
        }
        if(PadStringUtils.isNotNull(wmItemRecpt.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(wmItemRecpt.getAreaId());
            wmItemRecpt.setAreaCode(area.getAreaCode());
            wmItemRecpt.setAreaName(area.getAreaName());
        }
        ItemRecptUpdateReqVO itemRecptUpdateReqVO = BeanUtil.toBean(wmItemRecpt, ItemRecptUpdateReqVO.class);
        itemRecptService.updateItemRecpt(itemRecptUpdateReqVO);
        return toAjax(true);
    }

    /**
     * 获取物料入库单详细信息
     */
    @Operation(summary = "获取物料入库单详细信息接口")
    @PreAuthenticated
    @GetMapping(value = "/{recptId}")
    public AjaxResult getInfo(@PathVariable("recptId") Long recptId)
    {
        return AjaxResult.success(itemRecptService.getItemRecpt(recptId));
    }


    /**
     * 删除物料入库单
     */
    @Operation(summary = "删除采购入库单基本信息接口")
    @PreAuthenticated
    @Transactional
    @DeleteMapping("/{recptIds}")
    public AjaxResult remove(@PathVariable Long[] recptIds)
    {
        for (Long id:
                recptIds
        ) {
            ItemRecptDO itemRecpt = itemRecptService.getItemRecpt(id);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(itemRecpt.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }

            itemRecptLineService.deleteByRecptId(id);
        }
        for (Long id:
                recptIds
        ) {
            itemRecptService.deleteItemRecpt(id);
        }
        return toAjax(true);
    }

    /**
     * 执行入库
     * @return
     */
    @Operation(summary = "执行入库接口")
    @PreAuthenticated
    @Transactional
    @PutMapping("/{recptId}")
    public AjaxResult execute(@PathVariable Long recptId){

        ItemRecptDO recpt = itemRecptService.getItemRecpt(recptId);

        //单据有效性
        if(!PadStringUtils.isNotNull(recpt)){
            return AjaxResult.error("无效单据");
        }

        //先检查单据状态
        if(UserConstants.ORDER_STATUS_FINISHED.equals(recpt.getStatus())){
            return AjaxResult.error("当前单据已提交!");
        }

        //检查行数量
        ItemRecptLineDO param =  new ItemRecptLineDO();
        param.setRecptId(recptId);
        ItemRecptLineExportReqVO itemRecptLineExportReqVO = BeanUtil.toBean(param, ItemRecptLineExportReqVO.class);
        List<ItemRecptLineDO> lines = itemRecptLineService.getItemRecptLineList(itemRecptLineExportReqVO);
        if(CollectionUtil.isEmpty(lines)){
            return AjaxResult.error("请添加明细信息！");
        }


        //构造Transaction事务，并执行库存更新逻辑
        List<ItemRecptTxBean> beans = itemRecptService.getTxBeans(recptId);

        //调用库存核心
        storageCoreServicel.processItemRecpt(beans);

        //更新单据状态
        recpt.setStatus(Constant.ORDER_STATUS_FINISHED);

        ItemRecptUpdateReqVO itemRecptUpdateReqVO = ItemRecptConvert.INSTANCE.convert01(recpt);
        itemRecptService.updateItemRecpt(itemRecptUpdateReqVO);

        return AjaxResult.success();
    }
}
