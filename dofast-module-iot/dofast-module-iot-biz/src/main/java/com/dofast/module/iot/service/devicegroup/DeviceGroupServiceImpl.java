package com.dofast.module.iot.service.devicegroup;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.devicegroup.vo.*;
import com.dofast.module.iot.dal.dataobject.devicegroup.DeviceGroupDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.devicegroup.DeviceGroupConvert;
import com.dofast.module.iot.dal.mysql.devicegroup.DeviceGroupMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 设备分组 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class DeviceGroupServiceImpl implements DeviceGroupService {

    @Resource
    private DeviceGroupMapper deviceGroupMapper;

    @Override
    public Long createDeviceGroup(DeviceGroupCreateReqVO createReqVO) {
        // 插入
        DeviceGroupDO deviceGroup = DeviceGroupConvert.INSTANCE.convert(createReqVO);
        deviceGroupMapper.insert(deviceGroup);
        // 返回
        return deviceGroup.getId();
    }

    @Override
    public void updateDeviceGroup(DeviceGroupUpdateReqVO updateReqVO) {
        // 校验存在
        validateDeviceGroupExists(updateReqVO.getId());
        // 更新
        DeviceGroupDO updateObj = DeviceGroupConvert.INSTANCE.convert(updateReqVO);
        deviceGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceGroup(Long id) {
        // 校验存在
        validateDeviceGroupExists(id);
        // 删除
        deviceGroupMapper.deleteById(id);
    }

    private void validateDeviceGroupExists(Long id) {
        if (deviceGroupMapper.selectById(id) == null) {
            throw exception(DEVICE_GROUP_NOT_EXISTS);
        }
    }

    @Override
    public DeviceGroupDO getDeviceGroup(Long id) {
        return deviceGroupMapper.selectById(id);
    }

    @Override
    public List<DeviceGroupDO> getDeviceGroupList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return deviceGroupMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DeviceGroupDO> getDeviceGroupPage(DeviceGroupPageReqVO pageReqVO) {
        return deviceGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DeviceGroupDO> getDeviceGroupList(DeviceGroupExportReqVO exportReqVO) {
        return deviceGroupMapper.selectList(exportReqVO);
    }

}
