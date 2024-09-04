package com.dofast.module.pay.dal.mysql.notify;



import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pay.dal.dataobject.notify.PayNotifyLogDO;


import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.pay.dal.dataobject.notify.PayNotifyLogDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {

    default List<PayNotifyLogDO> selectListByTaskId(Long taskId) {
        return selectList(PayNotifyLogDO::getTaskId, taskId);
    }

}
