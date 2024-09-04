package com.dofast.module.pro.service.taskissue;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.taskissue.vo.*;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 生产任务投料 Service 接口
 *
 * @author 芋道源码
 */
public interface TaskIssueService {
    public String checkUnique(TaskIssueBaseVO proTaskIssue);
    /**
     * 创建生产任务投料
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskIssue(@Valid TaskIssueCreateReqVO createReqVO);

    /**
     * 更新生产任务投料
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskIssue(@Valid TaskIssueUpdateReqVO updateReqVO);

    /**
     * 删除生产任务投料
     *
     * @param id 编号
     */
    void deleteTaskIssue(Long id);

    /**
     * 获得生产任务投料
     *
     * @param id 编号
     * @return 生产任务投料
     */
    TaskIssueDO getTaskIssue(Long id);

    /**
     * 获得生产任务投料列表
     *
     * @param ids 编号
     * @return 生产任务投料列表
     */
    List<TaskIssueDO> getTaskIssueList(Collection<Long> ids);

    /**
     * 获得生产任务投料分页
     *
     * @param pageReqVO 分页查询
     * @return 生产任务投料分页
     */
    PageResult<TaskIssueDO> getTaskIssuePage(TaskIssuePageReqVO pageReqVO);

    /**
     * 获得生产任务投料列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产任务投料列表
     */
    List<TaskIssueDO> getTaskIssueList(TaskIssueExportReqVO exportReqVO);

}
