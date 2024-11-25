package com.dofast.module.pro.controller.admin.feedbackmember;

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

import com.dofast.module.pro.controller.admin.feedbackmember.vo.*;
import com.dofast.module.pro.dal.dataobject.feedbackmember.FeedbackMemberDO;
import com.dofast.module.pro.convert.feedbackmember.FeedbackMemberConvert;
import com.dofast.module.pro.service.feedbackmember.FeedbackMemberService;

@Tag(name = "管理后台 - 报工班组人员")
@RestController
@RequestMapping("/pro/feedback-member")
@Validated
public class FeedbackMemberController {

    @Resource
    private FeedbackMemberService feedbackMemberService;

    @PostMapping("/create")
    @Operation(summary = "创建报工班组人员")
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:create')")
    public CommonResult<Long> createFeedbackMember(@Valid @RequestBody FeedbackMemberCreateReqVO createReqVO) {
        return success(feedbackMemberService.createFeedbackMember(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报工班组人员")
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:update')")
    public CommonResult<Boolean> updateFeedbackMember(@Valid @RequestBody FeedbackMemberUpdateReqVO updateReqVO) {
        feedbackMemberService.updateFeedbackMember(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报工班组人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:delete')")
    public CommonResult<Boolean> deleteFeedbackMember(@RequestParam("id") Long id) {
        feedbackMemberService.deleteFeedbackMember(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报工班组人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:query')")
    public CommonResult<FeedbackMemberRespVO> getFeedbackMember(@RequestParam("id") Long id) {
        FeedbackMemberDO feedbackMember = feedbackMemberService.getFeedbackMember(id);
        return success(FeedbackMemberConvert.INSTANCE.convert(feedbackMember));
    }

    @GetMapping("/list")
    @Operation(summary = "获得报工班组人员列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:query')")
    public CommonResult<List<FeedbackMemberRespVO>> getFeedbackMemberList(@RequestParam("ids") Collection<Long> ids) {
        List<FeedbackMemberDO> list = feedbackMemberService.getFeedbackMemberList(ids);
        return success(FeedbackMemberConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报工班组人员分页")
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:query')")
    public CommonResult<PageResult<FeedbackMemberRespVO>> getFeedbackMemberPage(@Valid FeedbackMemberPageReqVO pageVO) {
        PageResult<FeedbackMemberDO> pageResult = feedbackMemberService.getFeedbackMemberPage(pageVO);
        return success(FeedbackMemberConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报工班组人员 Excel")
    @PreAuthorize("@ss.hasPermission('pro:feedback-member:export')")
    @OperateLog(type = EXPORT)
    public void exportFeedbackMemberExcel(@Valid FeedbackMemberExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FeedbackMemberDO> list = feedbackMemberService.getFeedbackMemberList(exportReqVO);
        // 导出 Excel
        List<FeedbackMemberExcelVO> datas = FeedbackMemberConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "报工班组人员.xls", "数据", FeedbackMemberExcelVO.class, datas);
    }

}
