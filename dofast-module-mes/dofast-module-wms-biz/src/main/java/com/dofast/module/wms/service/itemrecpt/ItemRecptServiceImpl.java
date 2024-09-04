package com.dofast.module.wms.service.itemrecpt;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.api.WorkStationAPi.WorkStationApi;
import com.dofast.module.mes.api.WorkStationAPi.dto.WorkStationDTO;
import com.dofast.module.mes.api.autocode.AutoCodeApi;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.mditem.vo.MdItemUpdateReqVO;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.service.mditem.MdItemService;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;
import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;
import com.dofast.module.pro.api.WorkorderApi.WorkorderApi;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.itemrecptline.ItemRecptLineMapper;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.itemrecpt.ItemRecptConvert;
import com.dofast.module.wms.dal.mysql.itemrecpt.ItemRecptMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 物料入库单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ItemRecptServiceImpl implements ItemRecptService {

    @Resource
    private ItemRecptMapper itemRecptMapper;

    @Resource
    private AutoCodeApi autoCodeApi;

    @Resource
    private WorkorderApi workorderApi;

    @Resource
    private WorkStationApi workStationApi;
    
    @Resource
    private WarehouseService warehouseService;
    
    @Resource
    private StorageAreaService storageAreaService;
    
    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private ItemRecptLineMapper itemRecptLineMapper;

    @Resource
    private MdItemService mdItemService;

    @Override
    public List<ItemRecptTxBean> getTxBeans(Long receptId) {
        return itemRecptMapper.getTxBeans(receptId);
    }

    @Override
    public String checkRecptCodeUnique(ItemRecptBaseVO wmItemRecpt) {
        ItemRecptDO itemRecpt = itemRecptMapper.checkRecptCodeUnique(wmItemRecpt);
        Long recptId = wmItemRecpt.getId()==null?-1L:wmItemRecpt.getId();
        if(StrUtils.isNotNull(itemRecpt) && itemRecpt.getId().longValue() != recptId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createItemRecpt(ItemRecptCreateReqVO createReqVO) {
        // 插入
        ItemRecptDO itemRecpt = ItemRecptConvert.INSTANCE.convert(createReqVO);
        itemRecptMapper.insert(itemRecpt);
        // 返回
        return itemRecpt.getId();
    }

    @Override
    public void updateItemRecpt(ItemRecptUpdateReqVO updateReqVO) {
        // 校验存在
        validateItemRecptExists(updateReqVO.getId());
        // 更新
        ItemRecptDO updateObj = ItemRecptConvert.INSTANCE.convert(updateReqVO);
        int i = itemRecptMapper.updateById(updateObj);
        if (i<=0){
            throw exception(UODATE_COME);
        }
    }

    @Override
    public void deleteItemRecpt(Long id) {
        // 校验存在
        validateItemRecptExists(id);
        // 删除
        itemRecptMapper.deleteById(id);
    }

    private void validateItemRecptExists(Long id) {
        if (itemRecptMapper.selectById(id) == null) {
            throw exception(ITEM_RECPT_NOT_EXISTS);
        }
    }

    @Override
    public ItemRecptDO getItemRecpt(Long id) {
        return itemRecptMapper.selectById(id);
    }

    @Override
    public List<ItemRecptDO> getItemRecptList(Collection<Long> ids) {
        return itemRecptMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ItemRecptDO> getItemRecptPage(ItemRecptPageReqVO pageReqVO) {
        return itemRecptMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ItemRecptDO> getItemRecptList(ItemRecptExportReqVO exportReqVO) {
        return itemRecptMapper.selectList(exportReqVO);
    }

    @Override
    public ItemRecptDO generateItemRecpt(FeedbackDTO feedback) {
        WorkorderDTO workorder = workorderApi.getWorkorder(feedback.getWorkorderId());
        WorkStationDTO workstation = workStationApi.getWorkstation(feedback.getWorkstationId());
        MdItemDO itemDO = mdItemService.getMdItem(workorder.getProductId());

        //生成单据头信息
        ItemRecptDO itemRecptDO = new ItemRecptDO();
        itemRecptDO.setRecptCode(autoCodeApi.genSerialCode("ITEMRECPT_CODE",null));
        itemRecptDO.setRecptName(workorder.getWorkorderName());

        WarehouseDO warehouse = warehouseService.getWarehouse(workstation.getWarehouseId());
        itemRecptDO.setWarehouseId(warehouse.getId());
        itemRecptDO.setWarehouseCode(warehouse.getWarehouseCode());
        itemRecptDO.setWarehouseName(warehouse.getWarehouseName());
        StorageLocationDO location = storageLocationService.getStorageLocation(workstation.getLocationId());
        itemRecptDO.setLocationId(location.getId());
        itemRecptDO.setLocationCode(location.getLocationCode());
        itemRecptDO.setLocationName(location.getLocationName());
        StorageAreaDO area = storageAreaService.getStorageArea(workstation.getAreaId());
        itemRecptDO.setAreaId(area.getId());
        itemRecptDO.setAreaCode(area.getAreaCode());
        itemRecptDO.setAreaName(area.getAreaName());
        int insert = itemRecptMapper.insert(itemRecptDO);
        if (insert<=0){
            throw exception(GET_WORKORER_HEAD);
        }


        //生成单据行信息; 以后如果是在生产过程中产生多种副产品可以在这里添加更多的行信息进行支持
        ItemRecptLineDO line = new ItemRecptLineDO();
        line.setRecptId(itemRecptDO.getId());
        line.setItemId(feedback.getItemId());
        line.setItemCode(feedback.getItemCode());
        line.setItemName(feedback.getItemName());
        line.setSpecification(feedback.getSpecification());
        line.setUnitOfMeasure(feedback.getUnitOfMeasure());
        line.setBatchCode(workorder.getBatchCode());
        line.setWarehouseId(warehouse.getId());
        line.setWarehouseCode(warehouse.getWarehouseCode());
        line.setWarehouseName(warehouse.getWarehouseName());
        line.setLocationId(location.getId());
        line.setLocationCode(location.getLocationCode());
        line.setLocationName(location.getLocationName());
        line.setAreaId(area.getId());
        line.setAreaCode(area.getAreaCode());
        line.setAreaName(area.getAreaName());
        int insert1 = itemRecptLineMapper.insert(line);
        if (insert1<=0){
            throw exception(GET_WORKORER_ROW);
        }

        //更新物料的信息
        itemDO.setWarehouseId(warehouse.getId());
        itemDO.setWarehouseCode(warehouse.getWarehouseCode());
        itemDO.setWarehouseName(warehouse.getWarehouseName());
        itemDO.setLocationId(location.getId());
        itemDO.setLocationCode(location.getLocationCode());
        itemDO.setLocationName(location.getLocationName());
        itemDO.setAreaId(area.getId());
        itemDO.setAreaCode(area.getAreaCode());
        itemDO.setAreaName(area.getAreaName());
        MdItemUpdateReqVO mdItemUpdateReqVO = BeanUtil.toBean(itemDO, MdItemUpdateReqVO.class);
        mdItemUpdateReqVO.setRecptDate(LocalDateTime.now());
        mdItemService.updateMdItem(mdItemUpdateReqVO);
        return itemRecptDO;
    }
}
