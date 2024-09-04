package com.dofast.module.trade.dal.mysql.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeExportReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypePageReqVO;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 采集任务类型 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface OrderCollectionTypeMapper extends BaseMapperX<OrderCollectionTypeDO> {

    default PageResult<OrderCollectionTypeDO> selectPage(OrderCollectionTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderCollectionTypeDO>()
                .likeIfPresent(OrderCollectionTypeDO::getName, reqVO.getName())
                .eqIfPresent(OrderCollectionTypeDO::getFormId, reqVO.getFormId())
                .eqIfPresent(OrderCollectionTypeDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(OrderCollectionTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderCollectionTypeDO::getSortCode));
    }

    default List<OrderCollectionTypeDO> selectList(OrderCollectionTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderCollectionTypeDO>()
                .likeIfPresent(OrderCollectionTypeDO::getName, reqVO.getName())
                .eqIfPresent(OrderCollectionTypeDO::getFormId, reqVO.getFormId())
                .eqIfPresent(OrderCollectionTypeDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(OrderCollectionTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderCollectionTypeDO::getSortCode));
    }

}
