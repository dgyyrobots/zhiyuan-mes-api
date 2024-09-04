package com.dofast.module.iot.service.alert;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.alert.vo.*;
import com.dofast.module.iot.dal.dataobject.alert.AlertDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备告警 Service 接口
 *
 * @author 惠智造
 */
public interface AlertService {

    /**
     * 创建设备告警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlert(@Valid AlertCreateReqVO createReqVO);

    /**
     * 更新设备告警
     *
     * @param updateReqVO 更新信息
     */
    void updateAlert(@Valid AlertUpdateReqVO updateReqVO);

    /**
     * 删除设备告警
     *
     * @param id 编号
     */
    void deleteAlert(Long id);

    /**
     * 获得设备告警
     *
     * @param id 编号
     * @return 设备告警
     */
    AlertDO getAlert(Long id);

    /**
     * 获得设备告警列表
     *
     * @param ids 编号
     * @return 设备告警列表
     */
    List<AlertDO> getAlertList(Collection<Long> ids);

    /**
     * 获得设备告警分页
     *
     * @param pageReqVO 分页查询
     * @return 设备告警分页
     */
    PageResult<AlertDO> getAlertPage(AlertPageReqVO pageReqVO);

    /**
     * 获得设备告警列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备告警列表
     */
    List<AlertDO> getAlertList(AlertExportReqVO exportReqVO);

}
