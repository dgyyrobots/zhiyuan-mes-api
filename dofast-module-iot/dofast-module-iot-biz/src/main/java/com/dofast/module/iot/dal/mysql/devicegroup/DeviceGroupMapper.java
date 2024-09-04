package com.dofast.module.iot.dal.mysql.devicegroup;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.devicegroup.DeviceGroupDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.devicegroup.vo.*;

/**
 * 设备分组 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceGroupMapper extends BaseMapperX<DeviceGroupDO> {

    default PageResult<DeviceGroupDO> selectPage(DeviceGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceGroupDO>()
                .betweenIfPresent(DeviceGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceGroupDO::getId));
    }

    default List<DeviceGroupDO> selectList(DeviceGroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeviceGroupDO>()
                .betweenIfPresent(DeviceGroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceGroupDO::getId));
    }

}
