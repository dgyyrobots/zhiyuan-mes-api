package com.dofast.module.tm.controller.admin.tooltype;

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

import com.dofast.module.tm.controller.admin.tooltype.vo.*;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;
import com.dofast.module.tm.convert.tooltype.ToolTypeConvert;
import com.dofast.module.tm.service.tooltype.ToolTypeService;

@Tag(name = "管理后台 - 工装夹具类型")
@RestController
@RequestMapping("/tm/tool-type")
@Validated
public class ToolTypeController {

    @Resource
    private ToolTypeService toolTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建工装夹具类型")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:create')")
    public CommonResult<Long> createToolType(@Valid @RequestBody ToolTypeCreateReqVO createReqVO) {
        return success(toolTypeService.createToolType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工装夹具类型")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:update')")
    public CommonResult<Boolean> updateToolType(@Valid @RequestBody ToolTypeUpdateReqVO updateReqVO) {
        toolTypeService.updateToolType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工装夹具类型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('tm:tool-type:delete')")
    public CommonResult<Boolean> deleteToolType(@RequestParam("id") Long id) {
        toolTypeService.deleteToolType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工装夹具类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:query')")
    public CommonResult<ToolTypeRespVO> getToolType(@RequestParam("id") Long id) {
        ToolTypeDO toolType = toolTypeService.getToolType(id);
        return success(ToolTypeConvert.INSTANCE.convert(toolType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工装夹具类型列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:query')")
    public CommonResult<List<ToolTypeRespVO>> getToolTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<ToolTypeDO> list = toolTypeService.getToolTypeList(ids);
        return success(ToolTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获得工装夹具类型简单列表")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:query')")
    public CommonResult<List<ToolTypeSimpleVO>> getToolTypeList() {
        List<ToolTypeSimpleVO> list = toolTypeService.getToolTypeSimpleList();
        return success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "获得工装夹具类型分页")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:query')")
    public CommonResult<PageResult<ToolTypeRespVO>> getToolTypePage(@Valid ToolTypePageReqVO pageVO) {
        PageResult<ToolTypeDO> pageResult = toolTypeService.getToolTypePage(pageVO);
        return success(ToolTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工装夹具类型 Excel")
    @PreAuthorize("@ss.hasPermission('tm:tool-type:export')")
    @OperateLog(type = EXPORT)
    public void exportToolTypeExcel(@Valid ToolTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ToolTypeDO> list = toolTypeService.getToolTypeList(exportReqVO);
        // 导出 Excel
        List<ToolTypeExcelVO> datas = ToolTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工装夹具类型.xls", "数据", ToolTypeExcelVO.class, datas);
    }

}
