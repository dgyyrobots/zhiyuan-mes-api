package com.dofast.module.iot.service.devicejob;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.devicejob.vo.*;
import com.dofast.module.iot.dal.dataobject.devicejob.DeviceJobDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.devicejob.DeviceJobConvert;
import com.dofast.module.iot.dal.mysql.devicejob.DeviceJobMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 设备定时 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class DeviceJobServiceImpl implements DeviceJobService {

    @Resource
    private DeviceJobMapper deviceJobMapper;

    @Override
    public Long createDeviceJob(DeviceJobCreateReqVO createReqVO) {
        // 插入
        DeviceJobDO deviceJob = DeviceJobConvert.INSTANCE.convert(createReqVO);
        deviceJobMapper.insert(deviceJob);
        // 返回
        return deviceJob.getId();
    }

    @Override
    public void updateDeviceJob(DeviceJobUpdateReqVO updateReqVO) {
        // 校验存在
        validateDeviceJobExists(updateReqVO.getId());
        // 更新
        DeviceJobDO updateObj = DeviceJobConvert.INSTANCE.convert(updateReqVO);
        deviceJobMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceJob(Long id) {
        // 校验存在
        validateDeviceJobExists(id);
        // 删除
        deviceJobMapper.deleteById(id);
    }

    private void validateDeviceJobExists(Long id) {
        if (deviceJobMapper.selectById(id) == null) {
            throw exception(DEVICE_JOB_NOT_EXISTS);
        }
    }

    @Override
    public DeviceJobDO getDeviceJob(Long id) {
        return deviceJobMapper.selectById(id);
    }

    @Override
    public List<DeviceJobDO> getDeviceJobList(Collection<Long> ids) {
        return deviceJobMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DeviceJobDO> getDeviceJobPage(DeviceJobPageReqVO pageReqVO) {
        return deviceJobMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DeviceJobDO> getDeviceJobList(DeviceJobExportReqVO exportReqVO) {
        return deviceJobMapper.selectList(exportReqVO);
    }

}
