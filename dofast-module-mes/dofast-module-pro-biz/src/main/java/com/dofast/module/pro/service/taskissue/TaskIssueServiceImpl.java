package com.dofast.module.pro.service.taskissue;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.taskissue.vo.*;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.taskissue.TaskIssueConvert;
import com.dofast.module.pro.dal.mysql.taskissue.TaskIssueMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 生产任务投料 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TaskIssueServiceImpl implements TaskIssueService {

    @Resource
    private TaskIssueMapper taskIssueMapper;

    @Override
    public String checkUnique(TaskIssueBaseVO proTaskIssue) {
        TaskIssueDO taskIssue = taskIssueMapper.checkUnique(proTaskIssue);
        if(StrUtils.isNotNull(taskIssue)){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createTaskIssue(TaskIssueCreateReqVO createReqVO) {
        // 插入
        TaskIssueDO taskIssue = TaskIssueConvert.INSTANCE.convert(createReqVO);
        taskIssueMapper.insert(taskIssue);
        // 返回
        return taskIssue.getId();
    }

    @Override
    public void updateTaskIssue(TaskIssueUpdateReqVO updateReqVO) {
        // 校验存在
        validateTaskIssueExists(updateReqVO.getId());
        // 更新
        TaskIssueDO updateObj = TaskIssueConvert.INSTANCE.convert(updateReqVO);
        taskIssueMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskIssue(Long id) {
        // 校验存在
        validateTaskIssueExists(id);
        // 删除
        taskIssueMapper.deleteById(id);
    }

    private void validateTaskIssueExists(Long id) {
        if (taskIssueMapper.selectById(id) == null) {
            throw exception(TASK_ISSUE_NOT_EXISTS);
        }
    }

    @Override
    public TaskIssueDO getTaskIssue(Long id) {
        return taskIssueMapper.selectById(id);
    }

    @Override
    public List<TaskIssueDO> getTaskIssueList(Collection<Long> ids) {
        return taskIssueMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TaskIssueDO> getTaskIssuePage(TaskIssuePageReqVO pageReqVO) {
        return taskIssueMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TaskIssueDO> getTaskIssueList(TaskIssueExportReqVO exportReqVO) {
        return taskIssueMapper.selectList(exportReqVO);
    }

}
