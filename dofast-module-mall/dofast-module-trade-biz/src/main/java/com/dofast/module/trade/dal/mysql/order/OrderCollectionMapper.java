package com.dofast.module.trade.dal.mysql.order;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionExportReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionPageReqVO;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 采集任务 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface OrderCollectionMapper extends BaseMapperX<OrderCollectionDO> {

    default PageResult<OrderCollectionDO> selectPage(OrderCollectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderCollectionDO>()
                .likeIfPresent(OrderCollectionDO::getName, reqVO.getName())
                .eqIfPresent(OrderCollectionDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(OrderCollectionDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderCollectionDO::getRecordId, reqVO.getRecordId())
                .eqIfPresent(OrderCollectionDO::getRecorder, reqVO.getRecorder())
                .betweenIfPresent(OrderCollectionDO::getRecordTime, reqVO.getRecordTime())
                .eqIfPresent(OrderCollectionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(OrderCollectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderCollectionDO::getId));
    }

    default List<OrderCollectionDO> selectList(OrderCollectionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderCollectionDO>()
                .likeIfPresent(OrderCollectionDO::getName, reqVO.getName())
                .eqIfPresent(OrderCollectionDO::getTypeId, reqVO.getTypeId())
                .inIfPresent(OrderCollectionDO::getTypeId, reqVO.getTypeIds())
                .eqIfPresent(OrderCollectionDO::getOrderId, reqVO.getOrderId())
                .inIfPresent(OrderCollectionDO::getOrderId, reqVO.getOrderIds())
                .eqIfPresent(OrderCollectionDO::getRecordId, reqVO.getRecordId())
                .eqIfPresent(OrderCollectionDO::getRecorder, reqVO.getRecorder())
                .betweenIfPresent(OrderCollectionDO::getRecordTime, reqVO.getRecordTime())
                .eqIfPresent(OrderCollectionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(OrderCollectionDO::getCreateTime, reqVO.getCreateTime())
                .and(!CollUtil.isEmpty(reqVO.getTasks()), qw -> {
                    List<String> tasks = reqVO.getTasks();
                    for (String task: tasks) {
                        String[] parsed= task.split(":");
                        Long typeId = Long.parseLong(parsed[0]);
                        String status = parsed[1];
                        qw = qw.eq(OrderCollectionDO::getTypeId, typeId).eq(OrderCollectionDO::getStatus, status).or();
                    }
                }).orderByDesc(OrderCollectionDO::getId));
    }

    default void updateStatusById(String status, Integer id) {
        OrderCollectionDO update = new OrderCollectionDO();
        update.setStatus(status);
        update(update, new LambdaQueryWrapperX<OrderCollectionDO>().eq(OrderCollectionDO::getId, id));
    }

}
