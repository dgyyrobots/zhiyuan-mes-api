package com.dofast.module.tm.controller.admin.tool;

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

import com.dofast.module.tm.controller.admin.tool.vo.*;
import com.dofast.module.tm.dal.dataobject.tool.ToolDO;
import com.dofast.module.tm.convert.tool.ToolConvert;
import com.dofast.module.tm.service.tool.ToolService;

@Tag(name = "管理后台 - 工装夹具清单")
@RestController
@RequestMapping("/tm/tool")
@Validated
public class ToolController {

    @Resource
    private ToolService toolService;

    @PostMapping("/create")
    @Operation(summary = "创建工装夹具清单")
    @PreAuthorize("@ss.hasPermission('tm:tool:create')")
    public CommonResult<Long> createTool(@Valid @RequestBody ToolCreateReqVO createReqVO) {
        return success(toolService.createTool(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工装夹具清单")
    @PreAuthorize("@ss.hasPermission('tm:tool:update')")
    public CommonResult<Boolean> updateTool(@Valid @RequestBody ToolUpdateReqVO updateReqVO) {
        toolService.updateTool(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工装夹具清单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('tm:tool:delete')")
    public CommonResult<Boolean> deleteTool(@RequestParam("id") Long id) {
        toolService.deleteTool(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工装夹具清单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('tm:tool:query')")
    public CommonResult<ToolRespVO> getTool(@RequestParam("id") Long id) {
        ToolDO tool = toolService.getTool(id);
        return success(ToolConvert.INSTANCE.convert(tool));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工装夹具清单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('tm:tool:query')")
    public CommonResult<List<ToolRespVO>> getToolList(@RequestParam("ids") Collection<Long> ids) {
        List<ToolDO> list = toolService.getToolList(ids);
        return success(ToolConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工装夹具清单分页")
    @PreAuthorize("@ss.hasPermission('tm:tool:query')")
    public CommonResult<PageResult<ToolRespVO>> getToolPage(@Valid ToolPageReqVO pageVO) {
        PageResult<ToolDO> pageResult = toolService.getToolPage(pageVO);
        return success(ToolConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工装夹具清单 Excel")
    @PreAuthorize("@ss.hasPermission('tm:tool:export')")
    @OperateLog(type = EXPORT)
    public void exportToolExcel(@Valid ToolExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ToolDO> list = toolService.getToolList(exportReqVO);
        // 导出 Excel
        List<ToolExcelVO> datas = ToolConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工装夹具清单.xls", "数据", ToolExcelVO.class, datas);
    }

}
