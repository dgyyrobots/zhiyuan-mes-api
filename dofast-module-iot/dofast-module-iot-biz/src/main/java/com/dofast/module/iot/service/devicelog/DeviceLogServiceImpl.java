package com.dofast.module.iot.service.devicelog;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.devicelog.vo.*;
import com.dofast.module.iot.dal.dataobject.devicelog.DeviceLogDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.devicelog.DeviceLogConvert;
import com.dofast.module.iot.dal.mysql.devicelog.DeviceLogMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 设备日志 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class DeviceLogServiceImpl implements DeviceLogService {

    @Resource
    private DeviceLogMapper deviceLogMapper;

    @Override
    public Long createDeviceLog(DeviceLogCreateReqVO createReqVO) {
        // 插入
        DeviceLogDO deviceLog = DeviceLogConvert.INSTANCE.convert(createReqVO);
        deviceLogMapper.insert(deviceLog);
        // 返回
        return deviceLog.getId();
    }

    @Override
    public void updateDeviceLog(DeviceLogUpdateReqVO updateReqVO) {
        // 校验存在
        validateDeviceLogExists(updateReqVO.getId());
        // 更新
        DeviceLogDO updateObj = DeviceLogConvert.INSTANCE.convert(updateReqVO);
        deviceLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceLog(Long id) {
        // 校验存在
        validateDeviceLogExists(id);
        // 删除
        deviceLogMapper.deleteById(id);
    }

    private void validateDeviceLogExists(Long id) {
        if (deviceLogMapper.selectById(id) == null) {
            throw exception(DEVICE_LOG_NOT_EXISTS);
        }
    }

    @Override
    public DeviceLogDO getDeviceLog(Long id) {
        return deviceLogMapper.selectById(id);
    }

    @Override
    public List<DeviceLogDO> getDeviceLogList(Collection<Long> ids) {
        return deviceLogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DeviceLogDO> getDeviceLogPage(DeviceLogPageReqVO pageReqVO) {
        return deviceLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DeviceLogDO> getDeviceLogList(DeviceLogExportReqVO exportReqVO) {
        return deviceLogMapper.selectList(exportReqVO);
    }

}
