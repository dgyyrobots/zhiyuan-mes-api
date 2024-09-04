package com.dofast.module.iot.service.alertlog;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.alertlog.vo.*;
import com.dofast.module.iot.dal.dataobject.alertlog.AlertLogDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备告警日志 Service 接口
 *
 * @author 惠智造
 */
public interface AlertLogService {

    /**
     * 创建设备告警日志
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlertLog(@Valid AlertLogCreateReqVO createReqVO);

    /**
     * 更新设备告警日志
     *
     * @param updateReqVO 更新信息
     */
    void updateAlertLog(@Valid AlertLogUpdateReqVO updateReqVO);

    /**
     * 删除设备告警日志
     *
     * @param id 编号
     */
    void deleteAlertLog(Long id);

    /**
     * 获得设备告警日志
     *
     * @param id 编号
     * @return 设备告警日志
     */
    AlertLogDO getAlertLog(Long id);

    /**
     * 获得设备告警日志列表
     *
     * @param ids 编号
     * @return 设备告警日志列表
     */
    List<AlertLogDO> getAlertLogList(Collection<Long> ids);

    /**
     * 获得设备告警日志分页
     *
     * @param pageReqVO 分页查询
     * @return 设备告警日志分页
     */
    PageResult<AlertLogDO> getAlertLogPage(AlertLogPageReqVO pageReqVO);

    /**
     * 获得设备告警日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备告警日志列表
     */
    List<AlertLogDO> getAlertLogList(AlertLogExportReqVO exportReqVO);

}
