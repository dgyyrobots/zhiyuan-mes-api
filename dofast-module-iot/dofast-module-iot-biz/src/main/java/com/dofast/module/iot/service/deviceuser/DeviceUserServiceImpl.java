package com.dofast.module.iot.service.deviceuser;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.deviceuser.vo.*;
import com.dofast.module.iot.dal.dataobject.deviceuser.DeviceUserDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.deviceuser.DeviceUserConvert;
import com.dofast.module.iot.dal.mysql.deviceuser.DeviceUserMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 设备用户 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class DeviceUserServiceImpl implements DeviceUserService {

    @Resource
    private DeviceUserMapper deviceUserMapper;

    @Override
    public Long createDeviceUser(DeviceUserCreateReqVO createReqVO) {
        // 插入
        DeviceUserDO deviceUser = DeviceUserConvert.INSTANCE.convert(createReqVO);
        deviceUserMapper.insert(deviceUser);
        // 返回
        return deviceUser.getId();
    }

    @Override
    public void updateDeviceUser(DeviceUserUpdateReqVO updateReqVO) {
        // 校验存在
        validateDeviceUserExists(updateReqVO.getId());
        // 更新
        DeviceUserDO updateObj = DeviceUserConvert.INSTANCE.convert(updateReqVO);
        deviceUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceUser(Long id) {
        // 校验存在
        validateDeviceUserExists(id);
        // 删除
        deviceUserMapper.deleteById(id);
    }

    private void validateDeviceUserExists(Long id) {
        if (deviceUserMapper.selectById(id) == null) {
            throw exception(DEVICE_USER_NOT_EXISTS);
        }
    }

    @Override
    public DeviceUserDO getDeviceUser(Long id) {
        return deviceUserMapper.selectById(id);
    }

    @Override
    public List<DeviceUserDO> getDeviceUserList(Collection<Long> ids) {
        return deviceUserMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DeviceUserDO> getDeviceUserPage(DeviceUserPageReqVO pageReqVO) {
        return deviceUserMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DeviceUserDO> getDeviceUserList(DeviceUserExportReqVO exportReqVO) {
        return deviceUserMapper.selectList(exportReqVO);
    }

}
