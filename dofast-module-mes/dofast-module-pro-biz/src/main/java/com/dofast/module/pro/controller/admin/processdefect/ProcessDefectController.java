package com.dofast.module.pro.controller.admin.processdefect;

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

import com.dofast.module.pro.controller.admin.processdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.processdefect.ProcessDefectDO;
import com.dofast.module.pro.convert.processdefect.ProcessDefectConvert;
import com.dofast.module.pro.service.processdefect.ProcessDefectService;

@Tag(name = "管理后台 - 工序异常缺陷名称")
@RestController
@RequestMapping("/pro/cess-defect")
@Validated
public class ProcessDefectController {

    @Resource
    private ProcessDefectService cessDefectService;

    @PostMapping("/create")
    @Operation(summary = "创建工序异常缺陷名称")
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:create')")
    public CommonResult<Long> createcessDefect(@Valid @RequestBody ProcessDefectCreateReqVO createReqVO) {
        return success(cessDefectService.createcessDefect(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工序异常缺陷名称")
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:update')")
    public CommonResult<Boolean> updatecessDefect(@Valid @RequestBody ProcessDefectUpdateReqVO updateReqVO) {
        cessDefectService.updatecessDefect(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工序异常缺陷名称")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:delete')")
    public CommonResult<Boolean> deletecessDefect(@RequestParam("id") Long id) {
        cessDefectService.deletecessDefect(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工序异常缺陷名称")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:query')")
    public CommonResult<ProcessDefectRespVO> getcessDefect(@RequestParam("id") Long id) {
        ProcessDefectDO cessDefect = cessDefectService.getcessDefect(id);
        return success(ProcessDefectConvert.INSTANCE.convert(cessDefect));
    }

    @GetMapping("/getByCode")
    @Operation(summary = "获得工序异常缺陷名称")
    @Parameter(name = "processCode", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('pro:cess-defect:query')")
    public CommonResult<List<ProcessDefectRespVO>> getcessDefect(@RequestParam("processCode") String processCode) {
        List<ProcessDefectDO> list = cessDefectService.getcessDefectByCode(processCode);
        return success(ProcessDefectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工序异常缺陷名称列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:query')")
    public CommonResult<List<ProcessDefectRespVO>> getcessDefectList(@RequestParam("ids") Collection<Long> ids) {
        List<ProcessDefectDO> list = cessDefectService.getcessDefectList(ids);
        return success(ProcessDefectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工序异常缺陷名称分页")
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:query')")
    public CommonResult<PageResult<ProcessDefectRespVO>> getcessDefectPage(@Valid ProcessDefectPageReqVO pageVO) {
        PageResult<ProcessDefectDO> pageResult = cessDefectService.getcessDefectPage(pageVO);
        return success(ProcessDefectConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工序异常缺陷名称 Excel")
    @PreAuthorize("@ss.hasPermission('pro:cess-defect:export')")
    @OperateLog(type = EXPORT)
    public void exportcessDefectExcel(@Valid ProcessDefectExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProcessDefectDO> list = cessDefectService.getcessDefectList(exportReqVO);
        // 导出 Excel
        List<ProcessDefectExcelVO> datas = ProcessDefectConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工序异常缺陷名称.xls", "数据", ProcessDefectExcelVO.class, datas);
    }

}
