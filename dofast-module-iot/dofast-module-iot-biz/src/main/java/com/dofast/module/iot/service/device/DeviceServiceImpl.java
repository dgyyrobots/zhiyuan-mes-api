package com.dofast.module.iot.service.device;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.device.vo.*;
import com.dofast.module.iot.dal.dataobject.device.DeviceDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.device.DeviceConvert;
import com.dofast.module.iot.dal.mysql.device.DeviceMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 设备 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public Long createDevice(DeviceCreateReqVO createReqVO) {
        // 插入
        DeviceDO device = DeviceConvert.INSTANCE.convert(createReqVO);
        deviceMapper.insert(device);
        // 返回
        return device.getId();
    }

    @Override
    public void updateDevice(DeviceUpdateReqVO updateReqVO) {
        // 校验存在
        validateDeviceExists(updateReqVO.getId());
        // 更新
        DeviceDO updateObj = DeviceConvert.INSTANCE.convert(updateReqVO);
        deviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteDevice(Long id) {
        // 校验存在
        validateDeviceExists(id);
        // 删除
        deviceMapper.deleteById(id);
    }

    private void validateDeviceExists(Long id) {
        if (deviceMapper.selectById(id) == null) {
            throw exception(DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceDO getDevice(Long id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public List<DeviceDO> getDeviceList(Collection<Long> ids) {
        return deviceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DeviceDO> getDevicePage(DevicePageReqVO pageReqVO) {
        return deviceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DeviceDO> getDeviceList(DeviceExportReqVO exportReqVO) {
        return deviceMapper.selectList(exportReqVO);
    }

}
