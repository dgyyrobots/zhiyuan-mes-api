package com.dofast.module.pro.controller.admin.feedbackdefect;

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

import com.dofast.module.pro.controller.admin.feedbackdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.feedbackdefect.FeedbackDefectDO;
import com.dofast.module.pro.convert.feedbackdefect.FeedbackDefectConvert;
import com.dofast.module.pro.service.feedbackdefect.FeedbackDefectService;

@Tag(name = "管理后台 - 报工缺陷")
@RestController
@RequestMapping("/pro/feedback-defect")
@Validated
public class FeedbackDefectController {

    @Resource
    private FeedbackDefectService feedbackDefectService;

    @PostMapping("/create")
    @Operation(summary = "创建报工缺陷")
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:create')")
    public CommonResult<Long> createFeedbackDefect(@Valid @RequestBody FeedbackDefectCreateReqVO createReqVO) {
        return success(feedbackDefectService.createFeedbackDefect(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报工缺陷")
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:update')")
    public CommonResult<Boolean> updateFeedbackDefect(@Valid @RequestBody FeedbackDefectUpdateReqVO updateReqVO) {
        feedbackDefectService.updateFeedbackDefect(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报工缺陷")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:delete')")
    public CommonResult<Boolean> deleteFeedbackDefect(@RequestParam("id") Long id) {
        feedbackDefectService.deleteFeedbackDefect(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报工缺陷")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:query')")
    public CommonResult<FeedbackDefectRespVO> getFeedbackDefect(@RequestParam("id") Long id) {
        FeedbackDefectDO feedbackDefect = feedbackDefectService.getFeedbackDefect(id);
        return success(FeedbackDefectConvert.INSTANCE.convert(feedbackDefect));
    }

    @GetMapping("/list")
    @Operation(summary = "获得报工缺陷列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:query')")
    public CommonResult<List<FeedbackDefectRespVO>> getFeedbackDefectList(@RequestParam("ids") Collection<Long> ids) {
        List<FeedbackDefectDO> list = feedbackDefectService.getFeedbackDefectList(ids);
        return success(FeedbackDefectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报工缺陷分页")
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:query')")
    public CommonResult<PageResult<FeedbackDefectRespVO>> getFeedbackDefectPage(@Valid FeedbackDefectPageReqVO pageVO) {
        PageResult<FeedbackDefectDO> pageResult = feedbackDefectService.getFeedbackDefectPage(pageVO);
        return success(FeedbackDefectConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报工缺陷 Excel")
    @PreAuthorize("@ss.hasPermission('pro:feedback-defect:export')")
    @OperateLog(type = EXPORT)
    public void exportFeedbackDefectExcel(@Valid FeedbackDefectExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FeedbackDefectDO> list = feedbackDefectService.getFeedbackDefectList(exportReqVO);
        // 导出 Excel
        List<FeedbackDefectExcelVO> datas = FeedbackDefectConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "报工缺陷.xls", "数据", FeedbackDefectExcelVO.class, datas);
    }

}
