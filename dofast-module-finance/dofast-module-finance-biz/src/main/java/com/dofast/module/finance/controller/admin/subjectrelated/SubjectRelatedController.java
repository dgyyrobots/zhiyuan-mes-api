package com.dofast.module.finance.controller.admin.subjectrelated;

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

import com.dofast.module.finance.controller.admin.subjectrelated.vo.*;
import com.dofast.module.finance.dal.dataobject.subjectrelated.SubjectRelatedDO;
import com.dofast.module.finance.convert.subjectrelated.SubjectRelatedConvert;
import com.dofast.module.finance.service.subjectrelated.SubjectRelatedService;

@Tag(name = "管理后台 - 收支科目")
@RestController
@RequestMapping("/finance/subject-related")
@Validated
public class SubjectRelatedController {

    @Resource
    private SubjectRelatedService subjectRelatedService;

    @PostMapping("/create")
    @Operation(summary = "创建收支科目")
    @PreAuthorize("@ss.hasPermission('finance:subject-related:create')")
    public CommonResult<Integer> createSubjectRelated(@Valid @RequestBody SubjectRelatedCreateReqVO createReqVO) {
        return success(subjectRelatedService.createSubjectRelated(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收支科目")
    @PreAuthorize("@ss.hasPermission('finance:subject-related:update')")
    public CommonResult<Boolean> updateSubjectRelated(@Valid @RequestBody SubjectRelatedUpdateReqVO updateReqVO) {
        subjectRelatedService.updateSubjectRelated(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收支科目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:subject-related:delete')")
    public CommonResult<Boolean> deleteSubjectRelated(@RequestParam("id") Integer id) {
        subjectRelatedService.deleteSubjectRelated(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收支科目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:subject-related:query')")
    public CommonResult<SubjectRelatedRespVO> getSubjectRelated(@RequestParam("id") Integer id) {
        SubjectRelatedDO subjectRelated = subjectRelatedService.getSubjectRelated(id);
        return success(SubjectRelatedConvert.INSTANCE.convert(subjectRelated));
    }

    @GetMapping("/list")
    @Operation(summary = "获得收支科目列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:subject-related:query')")
    public CommonResult<List<SubjectRelatedRespVO>> getSubjectRelatedList(@RequestParam("ids") Collection<Integer> ids) {
        List<SubjectRelatedDO> list = subjectRelatedService.getSubjectRelatedList(ids);
        return success(SubjectRelatedConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收支科目分页")
    @PreAuthorize("@ss.hasPermission('finance:subject-related:query')")
    public CommonResult<PageResult<SubjectRelatedRespVO>> getSubjectRelatedPage(@Valid SubjectRelatedPageReqVO pageVO) {
        PageResult<SubjectRelatedDO> pageResult = subjectRelatedService.getSubjectRelatedPage(pageVO);
        return success(SubjectRelatedConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收支科目 Excel")
    @PreAuthorize("@ss.hasPermission('finance:subject-related:export')")
    @OperateLog(type = EXPORT)
    public void exportSubjectRelatedExcel(@Valid SubjectRelatedExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SubjectRelatedDO> list = subjectRelatedService.getSubjectRelatedList(exportReqVO);
        // 导出 Excel
        List<SubjectRelatedExcelVO> datas = SubjectRelatedConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "收支科目.xls", "数据", SubjectRelatedExcelVO.class, datas);
    }

}
