package com.dofast.module.trade.dal.mysql.mixinorderitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemExportReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemPageReqVO;
import com.dofast.module.trade.dal.dataobject.mixinorderitem.MixinOrderItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 销售的物料明细 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface MixinOrderItemMapper extends BaseMapperX<MixinOrderItemDO> {

    default PageResult<MixinOrderItemDO> selectPage(MixinOrderItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MixinOrderItemDO>()
                .eqIfPresent(MixinOrderItemDO::getItemId, reqVO.getItemId())
                .eqIfPresent(MixinOrderItemDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(MixinOrderItemDO::getItemName, reqVO.getItemName())
                .eqIfPresent(MixinOrderItemDO::getSaleId, reqVO.getSaleId())
                .eqIfPresent(MixinOrderItemDO::getSaleNo, reqVO.getSaleNo())
                .eqIfPresent(MixinOrderItemDO::getTitle, reqVO.getTitle())
                .eqIfPresent(MixinOrderItemDO::getSinglePrice, reqVO.getSinglePrice())
                .eqIfPresent(MixinOrderItemDO::getNum, reqVO.getNum())
                .eqIfPresent(MixinOrderItemDO::getTotalPrice, reqVO.getTotalPrice())
                .betweenIfPresent(MixinOrderItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MixinOrderItemDO::getId));
    }

    default List<MixinOrderItemDO> selectList(MixinOrderItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MixinOrderItemDO>()
                .eqIfPresent(MixinOrderItemDO::getItemId, reqVO.getItemId())
                .eqIfPresent(MixinOrderItemDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(MixinOrderItemDO::getItemName, reqVO.getItemName())
                .eqIfPresent(MixinOrderItemDO::getSaleId, reqVO.getSaleId())
                .eqIfPresent(MixinOrderItemDO::getSaleNo, reqVO.getSaleNo())
                .eqIfPresent(MixinOrderItemDO::getTitle, reqVO.getTitle())
                .eqIfPresent(MixinOrderItemDO::getSinglePrice, reqVO.getSinglePrice())
                .eqIfPresent(MixinOrderItemDO::getNum, reqVO.getNum())
                .eqIfPresent(MixinOrderItemDO::getTotalPrice, reqVO.getTotalPrice())
                .betweenIfPresent(MixinOrderItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MixinOrderItemDO::getId));
    }

}
