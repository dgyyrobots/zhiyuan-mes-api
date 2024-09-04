package com.dofast.module.pro.controller.admin.taskissue;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.pro.controller.admin.taskissue.vo.*;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;
import com.dofast.module.pro.convert.taskissue.TaskIssueConvert;
import com.dofast.module.pro.service.taskissue.TaskIssueService;

@Tag(name = "生产管理 - 生产任务投料")
@RestController
@RequestMapping("/mes/pro/task-issue")
@Validated
public class TaskIssueController {

    @Resource
    private TaskIssueService taskIssueService;

    @PostMapping("/create")
    @Operation(summary = "创建生产任务投料")
    @PreAuthorize("@ss.hasPermission('pro:task-issue:create')")
    public CommonResult<Long> createTaskIssue(@Valid @RequestBody TaskIssueCreateReqVO createReqVO) {
        return success(taskIssueService.createTaskIssue(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产任务投料")
    @PreAuthorize("@ss.hasPermission('pro:task-issue:update')")
    public CommonResult<Boolean> updateTaskIssue(@Valid @RequestBody TaskIssueUpdateReqVO updateReqVO) {
        taskIssueService.updateTaskIssue(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产任务投料")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:task-issue:delete')")
    public CommonResult<Boolean> deleteTaskIssue(@RequestParam("id") Long id) {
        taskIssueService.deleteTaskIssue(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产任务投料")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:task-issue:query')")
    public CommonResult<TaskIssueRespVO> getTaskIssue(@RequestParam("id") Long id) {
        TaskIssueDO taskIssue = taskIssueService.getTaskIssue(id);
        return success(TaskIssueConvert.INSTANCE.convert(taskIssue));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产任务投料列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:task-issue:query')")
    public CommonResult<List<TaskIssueRespVO>> getTaskIssueList(@RequestParam("ids") Collection<Long> ids) {
        List<TaskIssueDO> list = taskIssueService.getTaskIssueList(ids);
        return success(TaskIssueConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产任务投料分页")
    @PreAuthorize("@ss.hasPermission('pro:task-issue:query')")
    public CommonResult<PageResult<TaskIssueRespVO>> getTaskIssuePage(@Valid TaskIssuePageReqVO pageVO) {
        PageResult<TaskIssueDO> pageResult = taskIssueService.getTaskIssuePage(pageVO);
        return success(TaskIssueConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产任务投料 Excel")
    @PreAuthorize("@ss.hasPermission('pro:task-issue:export')")
    @OperateLog(type = EXPORT)
    public void exportTaskIssueExcel(@Valid TaskIssueExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TaskIssueDO> list = taskIssueService.getTaskIssueList(exportReqVO);
        // 导出 Excel
        List<TaskIssueExcelVO> datas = TaskIssueConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产任务投料.xls", "数据", TaskIssueExcelVO.class, datas);
    }

}
