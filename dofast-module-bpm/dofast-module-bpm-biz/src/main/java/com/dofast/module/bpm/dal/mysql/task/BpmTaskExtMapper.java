package com.dofast.module.bpm.dal.mysql.task;

import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.bpm.dal.dataobject.task.BpmTaskExtDO;
import com.dofast.module.bpm.enums.task.BpmProcessInstanceResultEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface BpmTaskExtMapper extends BaseMapperX<BpmTaskExtDO> {

    default void updateByTaskId(BpmTaskExtDO entity) {
        update(entity, new LambdaQueryWrapper<BpmTaskExtDO>().eq(BpmTaskExtDO::getTaskId, entity.getTaskId()));
    }

    default List<BpmTaskExtDO> selectListByTaskIds(Collection<String> taskIds) {
        return selectList(BpmTaskExtDO::getTaskId, taskIds);
    }

    default List<BpmTaskExtDO> selectProcessListByTaskIds(Collection<String> taskIds) {
        return selectList(new LambdaQueryWrapperX<BpmTaskExtDO>()
                .in(BpmTaskExtDO::getTaskId, taskIds)
                .in(BpmTaskExtDO::getResult, BpmProcessInstanceResultEnum.CAN_SUB_SIGN_STATUS_LIST));
    }


    default BpmTaskExtDO selectByTaskId(String taskId) {
        return selectOne(BpmTaskExtDO::getTaskId, taskId);
    }

    default void updateBatchByTaskIdList(List<String> taskIdList, BpmTaskExtDO updateObj) {
        update(updateObj, new LambdaQueryWrapper<BpmTaskExtDO>().in(BpmTaskExtDO::getTaskId, taskIdList));
    }

}
