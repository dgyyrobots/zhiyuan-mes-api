package com.dofast.module.infra.service.job;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.quartz.core.service.JobLogFrameworkService;
import com.dofast.module.infra.controller.admin.job.vo.log.JobLogExportReqVO;
import com.dofast.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import com.dofast.module.infra.dal.dataobject.job.JobLogDO;

import java.util.Collection;
import java.util.List;

/**
 * Job 日志 Service 接口
 *
 * @author 芋道源码
 */
public interface JobLogService extends JobLogFrameworkService {

    /**
     * 获得定时任务
     *
     * @param id 编号
     * @return 定时任务
     */
    JobLogDO getJobLog(Long id);

    /**
     * 获得定时任务列表
     *
     * @param ids 编号
     * @return 定时任务列表
     */
    List<JobLogDO> getJobLogList(Collection<Long> ids);

    /**
     * 获得定时任务分页
     *
     * @param pageReqVO 分页查询
     * @return 定时任务分页
     */
    PageResult<JobLogDO> getJobLogPage(JobLogPageReqVO pageReqVO);

    /**
     * 获得定时任务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 定时任务分页
     */
    List<JobLogDO> getJobLogList(JobLogExportReqVO exportReqVO);


    /**
     * 清理 exceedDay 天前的任务日志
     *
     * @param exceedDay 超过多少天就进行清理
     * @param deleteLimit 清理的间隔条数
     */
    Integer cleanJobLog(Integer exceedDay, Integer deleteLimit);


}
