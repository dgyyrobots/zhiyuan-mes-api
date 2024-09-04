package com.dofast.module.report.service.PrintLog;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogCreateReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogExportReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogPageReqVO;
import com.dofast.module.report.controller.admin.PrintLog.vo.PrintLogUpdateReqVO;
import com.dofast.module.report.dal.dataobject.PrintLog.PrintLogDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 打印日志 Service 接口
 *
 * @author a1
 */
public interface PrintLogService {

    /**
     * 创建打印日志
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPrintLog(@Valid PrintLogCreateReqVO createReqVO);

    /**
     * 更新打印日志
     *
     * @param updateReqVO 更新信息
     */
    void updatePrintLog(@Valid PrintLogUpdateReqVO updateReqVO);

    /**
     * 删除打印日志
     *
     * @param id 编号
     */
    void deletePrintLog(Long id);

    /**
     * 获得打印日志
     *
     * @param id 编号
     * @return 打印日志
     */
    PrintLogDO getPrintLog(Long id);

    /**
     * 获得打印日志列表
     *
     * @param ids 编号
     * @return 打印日志列表
     */
    List<PrintLogDO> getPrintLogList(Collection<Long> ids);

    /**
     * 获得打印日志分页
     *
     * @param pageReqVO 分页查询
     * @return 打印日志分页
     */
    PageResult<PrintLogDO> getPrintLogPage(PrintLogPageReqVO pageReqVO);

    /**
     * 获得打印日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 打印日志列表
     */
    List<PrintLogDO> getPrintLogList(PrintLogExportReqVO exportReqVO);

    List<PrintLogDO> getPrintLogListByPrintCode(String printCode);

    List<PrintLogDO> getPrintLogListByPrintCodeAndType(String printCode,String type);



}
