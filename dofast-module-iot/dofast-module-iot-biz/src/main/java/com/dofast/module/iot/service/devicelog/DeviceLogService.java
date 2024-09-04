package com.dofast.module.iot.service.devicelog;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.devicelog.vo.*;
import com.dofast.module.iot.dal.dataobject.devicelog.DeviceLogDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备日志 Service 接口
 *
 * @author 惠智造
 */
public interface DeviceLogService {

    /**
     * 创建设备日志
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceLog(@Valid DeviceLogCreateReqVO createReqVO);

    /**
     * 更新设备日志
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceLog(@Valid DeviceLogUpdateReqVO updateReqVO);

    /**
     * 删除设备日志
     *
     * @param id 编号
     */
    void deleteDeviceLog(Long id);

    /**
     * 获得设备日志
     *
     * @param id 编号
     * @return 设备日志
     */
    DeviceLogDO getDeviceLog(Long id);

    /**
     * 获得设备日志列表
     *
     * @param ids 编号
     * @return 设备日志列表
     */
    List<DeviceLogDO> getDeviceLogList(Collection<Long> ids);

    /**
     * 获得设备日志分页
     *
     * @param pageReqVO 分页查询
     * @return 设备日志分页
     */
    PageResult<DeviceLogDO> getDeviceLogPage(DeviceLogPageReqVO pageReqVO);

    /**
     * 获得设备日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备日志列表
     */
    List<DeviceLogDO> getDeviceLogList(DeviceLogExportReqVO exportReqVO);

}
