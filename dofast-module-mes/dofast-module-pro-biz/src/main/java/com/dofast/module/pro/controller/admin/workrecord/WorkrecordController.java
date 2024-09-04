package com.dofast.module.pro.controller.admin.workrecord;

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

import com.dofast.module.pro.controller.admin.workrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.workrecord.WorkrecordDO;
import com.dofast.module.pro.convert.workrecord.WorkrecordConvert;
import com.dofast.module.pro.service.workrecord.WorkrecordService;

@Tag(name = "管理后台 - 上下工记录")
@RestController
@RequestMapping("/pro/workrecord")
@Validated
public class WorkrecordController {

    @Resource
    private WorkrecordService workrecordService;

    @PostMapping("/create")
    @Operation(summary = "创建上下工记录")
    @PreAuthorize("@ss.hasPermission('pro:workrecord:create')")
    public CommonResult<Long> createWorkrecord(@Valid @RequestBody WorkrecordCreateReqVO createReqVO) {
        return success(workrecordService.createWorkrecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新上下工记录")
    @PreAuthorize("@ss.hasPermission('pro:workrecord:update')")
    public CommonResult<Boolean> updateWorkrecord(@Valid @RequestBody WorkrecordUpdateReqVO updateReqVO) {
        workrecordService.updateWorkrecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除上下工记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:workrecord:delete')")
    public CommonResult<Boolean> deleteWorkrecord(@RequestParam("id") Long id) {
        workrecordService.deleteWorkrecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得上下工记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:workrecord:query')")
    public CommonResult<WorkrecordRespVO> getWorkrecord(@RequestParam("id") Long id) {
        WorkrecordDO workrecord = workrecordService.getWorkrecord(id);
        return success(WorkrecordConvert.INSTANCE.convert(workrecord));
    }

    @GetMapping("/list")
    @Operation(summary = "获得上下工记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:workrecord:query')")
    public CommonResult<List<WorkrecordRespVO>> getWorkrecordList(@RequestParam("ids") Collection<Long> ids) {
        List<WorkrecordDO> list = workrecordService.getWorkrecordList(ids);
        return success(WorkrecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得上下工记录分页")
    @PreAuthorize("@ss.hasPermission('pro:workrecord:query')")
    public CommonResult<PageResult<WorkrecordRespVO>> getWorkrecordPage(@Valid WorkrecordPageReqVO pageVO) {
        PageResult<WorkrecordDO> pageResult = workrecordService.getWorkrecordPage(pageVO);
        return success(WorkrecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出上下工记录 Excel")
    @PreAuthorize("@ss.hasPermission('pro:workrecord:export')")
    @OperateLog(type = EXPORT)
    public void exportWorkrecordExcel(@Valid WorkrecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WorkrecordDO> list = workrecordService.getWorkrecordList(exportReqVO);
        // 导出 Excel
        List<WorkrecordExcelVO> datas = WorkrecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "上下工记录.xls", "数据", WorkrecordExcelVO.class, datas);
    }

}
