package com.dofast.module.iot.service.devicejob;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.devicejob.vo.*;
import com.dofast.module.iot.dal.dataobject.devicejob.DeviceJobDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备定时 Service 接口
 *
 * @author 惠智造
 */
public interface DeviceJobService {

    /**
     * 创建设备定时
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceJob(@Valid DeviceJobCreateReqVO createReqVO);

    /**
     * 更新设备定时
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceJob(@Valid DeviceJobUpdateReqVO updateReqVO);

    /**
     * 删除设备定时
     *
     * @param id 编号
     */
    void deleteDeviceJob(Long id);

    /**
     * 获得设备定时
     *
     * @param id 编号
     * @return 设备定时
     */
    DeviceJobDO getDeviceJob(Long id);

    /**
     * 获得设备定时列表
     *
     * @param ids 编号
     * @return 设备定时列表
     */
    List<DeviceJobDO> getDeviceJobList(Collection<Long> ids);

    /**
     * 获得设备定时分页
     *
     * @param pageReqVO 分页查询
     * @return 设备定时分页
     */
    PageResult<DeviceJobDO> getDeviceJobPage(DeviceJobPageReqVO pageReqVO);

    /**
     * 获得设备定时列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备定时列表
     */
    List<DeviceJobDO> getDeviceJobList(DeviceJobExportReqVO exportReqVO);

}
