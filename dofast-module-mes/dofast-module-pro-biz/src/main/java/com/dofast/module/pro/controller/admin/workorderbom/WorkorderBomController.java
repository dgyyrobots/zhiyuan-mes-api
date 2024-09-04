package com.dofast.module.pro.controller.admin.workorderbom;

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

import com.dofast.module.pro.controller.admin.workorderbom.vo.*;
import com.dofast.module.pro.dal.dataobject.workorderbom.WorkorderBomDO;
import com.dofast.module.pro.convert.workorderbom.WorkorderBomConvert;
import com.dofast.module.pro.service.workorderbom.WorkorderBomService;

@Tag(name = "生产管理 - 生产工单BOM组成")
@RestController
@RequestMapping("/mes/pro/workorder-bom")
@Validated
public class WorkorderBomController {

    @Resource
    private WorkorderBomService workorderBomService;

    @PostMapping("/create")
    @Operation(summary = "创建生产工单BOM组成")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:create')")
    public CommonResult<Long> createWorkorderBom(@Valid @RequestBody WorkorderBomCreateReqVO createReqVO) {
        return success(workorderBomService.createWorkorderBom(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产工单BOM组成")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:update')")
    public CommonResult<Boolean> updateWorkorderBom(@Valid @RequestBody WorkorderBomUpdateReqVO updateReqVO) {
        workorderBomService.updateWorkorderBom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产工单BOM组成")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:delete')")
    public CommonResult<Boolean> deleteWorkorderBom(@RequestParam("id") Long id) {
        workorderBomService.deleteWorkorderBom(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产工单BOM组成")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:query')")
    public CommonResult<WorkorderBomRespVO> getWorkorderBom(@RequestParam("id") Long id) {
        WorkorderBomDO workorderBom = workorderBomService.getWorkorderBom(id);
        return success(WorkorderBomConvert.INSTANCE.convert(workorderBom));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产工单BOM组成列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:query')")
    public CommonResult<List<WorkorderBomRespVO>> getWorkorderBomList(@RequestParam("ids") Collection<Long> ids) {
        List<WorkorderBomDO> list = workorderBomService.getWorkorderBomList(ids);
        return success(WorkorderBomConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产工单BOM组成分页")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:query')")
    public CommonResult<PageResult<WorkorderBomRespVO>> getWorkorderBomPage(@Valid WorkorderBomPageReqVO pageVO) {
        PageResult<WorkorderBomDO> pageResult = workorderBomService.getWorkorderBomPage(pageVO);
        return success(WorkorderBomConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产工单BOM组成 Excel")
    @PreAuthorize("@ss.hasPermission('pro:workorder-bom:export')")
    @OperateLog(type = EXPORT)
    public void exportWorkorderBomExcel(@Valid WorkorderBomExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WorkorderBomDO> list = workorderBomService.getWorkorderBomList(exportReqVO);
        // 导出 Excel
        List<WorkorderBomExcelVO> datas = WorkorderBomConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产工单BOM组成.xls", "数据", WorkorderBomExcelVO.class, datas);
    }

}
