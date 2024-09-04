package com.dofast.module.mes.service.mditem;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.mditem.vo.*;
import com.dofast.module.mes.controller.admin.mdproductbom.vo.MdProductBomExportReqVO;
import com.dofast.module.mes.convert.mditem.MdItemConvert;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.module.mes.dal.dataobject.mdproductbom.MdProductBomDO;
import com.dofast.module.mes.dal.mysql.mditem.MdItemMapper;
import com.dofast.module.mes.dal.mysql.mditemtype.MdItemTypeMapper;
import com.dofast.module.mes.dal.mysql.mdproductbom.MdProductBomMapper;
import com.dofast.module.wms.api.StorageAreaApi.StorageAreaApi;
import com.dofast.module.wms.api.StorageAreaApi.dto.StorageAreaDTO;
import com.dofast.module.wms.api.StorageLocationApi.StorageLocationApi;
import com.dofast.module.wms.api.StorageLocationApi.dto.StorageLocationDTO;
import com.dofast.module.wms.api.WarehosueApi.WarehouseApi;
import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.MD_ITEM_NOT_EXISTS;
import static com.dofast.module.pro.enums.ErrorCodeConstants.UPDATE_INFO;

/**
 * 物料产品 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdItemServiceImpl implements MdItemService {

    @Resource
    private MdItemMapper mdItemMapper;

    @Resource
    private MdItemTypeMapper mdItemTypeMapper;

    @Resource
    private MdProductBomMapper mdProductBomMapper;

    @Override
    public String checkItemCodeUnique(@Valid MdItemBaseVO createReqVO) {
        MdItemDO item = mdItemMapper.checkItemCodeUnique(createReqVO);
        Long itemId = createReqVO.getId() == null? -1L:createReqVO.getId();
        if(item!=null && item.getId().longValue() != itemId.longValue()){
            return Constant.NOT_UNIQUE;
        }else{
            return Constant.UNIQUE;
        }
    }
    @Override
    public String checkItemNameUnique(@Valid MdItemBaseVO mdItem) {
        MdItemDO item = mdItemMapper.checkItemNameUnique(mdItem);
        Long itemId = mdItem.getId() == null? -1L:mdItem.getId();
        if(item !=null && item.getId().longValue() != itemId.longValue()){
            return Constant.NOT_UNIQUE;
        }else{
            return Constant.UNIQUE;
        }
    }

    @Resource
    private WarehouseApi warehouseApi;

    @Resource
    private StorageLocationApi storageLocationApi;

    @Resource
    private StorageAreaApi storageAreaApi;

    @Override
    public Long createMdItem(MdItemCreateReqVO createReqVO) {
        // 插入
        MdItemDO mdItem = MdItemConvert.INSTANCE.convert(createReqVO);
        //仓库
        WarehouseDTO warehouse = warehouseApi.getWarehouse(createReqVO.getWarehouseId());
        mdItem.setWarehouseCode(warehouse.getWarehouseCode());
        mdItem.setWarehouseName(warehouse.getWarehouseName());
        //库区
        StorageLocationDTO location = storageLocationApi.getLocation(createReqVO.getLocationId());
        mdItem.setLocationCode(location.getLocationCode());
        mdItem.setLocationName(location.getLocationName());
        //库位
        StorageAreaDTO area = storageAreaApi.getArea(createReqVO.getAreaId());
        mdItem.setAreaCode(area.getAreaCode());
        mdItem.setAreaName(area.getAreaName());
        mdItemMapper.insert(mdItem);
        // 返回
        return mdItem.getId();
    }

    @Override
    public void updateMdItem(MdItemUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdItemExists(updateReqVO.getId());
        // 更新
        MdItemDO updateObj = MdItemConvert.INSTANCE.convert(updateReqVO);
        int i = mdItemMapper.updateById(updateObj);
        if (i<=0){
            throw exception(UPDATE_INFO);
        }
    }

    @Override
    public void deleteMdItem(Long id) {
        // 校验存在
        validateMdItemExists(id);
        // 删除
        mdItemMapper.deleteById(id);
    }


    private void validateMdItemExists(Long id) {
        if (mdItemMapper.selectById(id) == null) {
            throw exception(MD_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public MdItemDO getMdItem(Long id) {
        return mdItemMapper.selectById(id);
    }

    @Override
    public List<MdItemDO> getMdItemList(Collection<Long> ids) {
        return mdItemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdItemDO> getMdItemPage(MdItemPageReqVO pageReqVO) {
        return mdItemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdItemDO> getMdItemList(MdItemExportReqVO mdItemExportReqVO) {
        //因为查询语句是orMapper里没法关联两个表，所以查两次，并过滤，最后组装到一起
        List<MdItemDO> mdItemDOS = mdItemMapper.selectListByItemReq(mdItemExportReqVO);
        if (!StrUtils.isNotNull(mdItemExportReqVO.getItemTypeId())){
            return mdItemDOS;
        }
        QueryWrapper<MdItemTypeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id")
                .like("ancestors", "%" + mdItemExportReqVO.getItemTypeId() + "%");
        List<MdItemTypeDO> resultList = mdItemTypeMapper.selectList(queryWrapper);
        mdItemExportReqVO.setItemTypeId(null);
        List<MdItemDO> list2 = mdItemMapper.selectListByItemReq(mdItemExportReqVO);
        List<MdItemDO> filteredList = list2.stream()
                .filter(item -> resultList.stream()
                        .anyMatch(type -> Objects.equals(item.getItemTypeId(), type.getId())))
                .collect(Collectors.toList());
        //拼接数据
        List<MdItemDO> mergedResultList = Stream.concat(filteredList.stream(), mdItemDOS.stream())
                .distinct()
                .collect(Collectors.toList());
        return mergedResultList;
    }

    @Override
    public List<MdItemRequestVO> getMdItemRequest(Collection<Long> ids) {
        // 查询商品
        List<MdItemDO> mdItemDOS = mdItemMapper.selectBatchIds(ids);
        List<MdItemRequestVO> result = new ArrayList<>();
        for (MdItemDO mdItemDO: mdItemDOS){
            MdItemRequestVO start = translateClass(mdItemDO);
            result.add(start);
            retrieveItems(start);
        }
        return result;
    }

    public MdItemRequestVO translateClass(MdItemDO mdItemDO){
        MdItemRequestVO mdItemRequestVO = BeanUtil.copyProperties(mdItemDO, MdItemRequestVO.class);
        return mdItemRequestVO;
    }

    public void retrieveItems(MdItemRequestVO start){
        MdProductBomExportReqVO mdProductBomExportReqVO = new MdProductBomExportReqVO();
        mdProductBomExportReqVO.setItemId(start.getId());
        List<MdProductBomDO> list = mdProductBomMapper.selectList(mdProductBomExportReqVO);
        if (list == null || list.isEmpty()) {
            start.setNode(null);
            return;
        }
        for (MdProductBomDO mdProductBomDO : list){
            MdItemDO mdItemDO = mdItemMapper.selectById(mdProductBomDO.getBomItemId());
            MdItemRequestVO node = translateClass(mdItemDO);
            start.addNode(node);
            retrieveItems(node);
        }
    }
}
