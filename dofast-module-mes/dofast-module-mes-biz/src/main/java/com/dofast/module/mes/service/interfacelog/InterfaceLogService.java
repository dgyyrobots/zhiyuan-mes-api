package com.dofast.module.mes.service.interfacelog;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.interfacelog.vo.*;
import com.dofast.module.mes.dal.dataobject.interfacelog.InterfaceLogDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 接口操作日志 Service 接口
 *
 * @author 惠智造
 */
public interface InterfaceLogService {

    /**
     * 创建接口操作日志
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInterfaceLog(@Valid InterfaceLogCreateReqVO createReqVO);

    /**
     * 更新接口操作日志
     *
     * @param updateReqVO 更新信息
     */
    void updateInterfaceLog(@Valid InterfaceLogUpdateReqVO updateReqVO);

    /**
     * 删除接口操作日志
     *
     * @param id 编号
     */
    void deleteInterfaceLog(Long id);

    /**
     * 获得接口操作日志
     *
     * @param id 编号
     * @return 接口操作日志
     */
    InterfaceLogDO getInterfaceLog(Long id);

    /**
     * 获得接口操作日志列表
     *
     * @param ids 编号
     * @return 接口操作日志列表
     */
    List<InterfaceLogDO> getInterfaceLogList(Collection<Long> ids);

    /**
     * 获得接口操作日志分页
     *
     * @param pageReqVO 分页查询
     * @return 接口操作日志分页
     */
    PageResult<InterfaceLogDO> getInterfaceLogPage(InterfaceLogPageReqVO pageReqVO);

    /**
     * 获得接口操作日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 接口操作日志列表
     */
    List<InterfaceLogDO> getInterfaceLogList(InterfaceLogExportReqVO exportReqVO);

}
