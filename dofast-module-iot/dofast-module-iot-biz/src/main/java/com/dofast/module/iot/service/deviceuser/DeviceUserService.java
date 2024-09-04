package com.dofast.module.iot.service.deviceuser;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.deviceuser.vo.*;
import com.dofast.module.iot.dal.dataobject.deviceuser.DeviceUserDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备用户 Service 接口
 *
 * @author 惠智造
 */
public interface DeviceUserService {

    /**
     * 创建设备用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceUser(@Valid DeviceUserCreateReqVO createReqVO);

    /**
     * 更新设备用户
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceUser(@Valid DeviceUserUpdateReqVO updateReqVO);

    /**
     * 删除设备用户
     *
     * @param id 编号
     */
    void deleteDeviceUser(Long id);

    /**
     * 获得设备用户
     *
     * @param id 编号
     * @return 设备用户
     */
    DeviceUserDO getDeviceUser(Long id);

    /**
     * 获得设备用户列表
     *
     * @param ids 编号
     * @return 设备用户列表
     */
    List<DeviceUserDO> getDeviceUserList(Collection<Long> ids);

    /**
     * 获得设备用户分页
     *
     * @param pageReqVO 分页查询
     * @return 设备用户分页
     */
    PageResult<DeviceUserDO> getDeviceUserPage(DeviceUserPageReqVO pageReqVO);

    /**
     * 获得设备用户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备用户列表
     */
    List<DeviceUserDO> getDeviceUserList(DeviceUserExportReqVO exportReqVO);

}
