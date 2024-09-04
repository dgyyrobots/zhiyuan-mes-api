package com.dofast.module.iot.service.device;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.device.vo.*;
import com.dofast.module.iot.dal.dataobject.device.DeviceDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备 Service 接口
 *
 * @author 惠智造
 */
public interface DeviceService {

    /**
     * 创建设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDevice(@Valid DeviceCreateReqVO createReqVO);

    /**
     * 更新设备
     *
     * @param updateReqVO 更新信息
     */
    void updateDevice(@Valid DeviceUpdateReqVO updateReqVO);

    /**
     * 删除设备
     *
     * @param id 编号
     */
    void deleteDevice(Long id);

    /**
     * 获得设备
     *
     * @param id 编号
     * @return 设备
     */
    DeviceDO getDevice(Long id);

    /**
     * 获得设备列表
     *
     * @param ids 编号
     * @return 设备列表
     */
    List<DeviceDO> getDeviceList(Collection<Long> ids);

    /**
     * 获得设备分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分页
     */
    PageResult<DeviceDO> getDevicePage(DevicePageReqVO pageReqVO);

    /**
     * 获得设备列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备列表
     */
    List<DeviceDO> getDeviceList(DeviceExportReqVO exportReqVO);

}
