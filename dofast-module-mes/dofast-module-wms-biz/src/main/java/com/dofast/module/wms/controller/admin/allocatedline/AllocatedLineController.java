package com.dofast.module.wms.controller.admin.allocatedline;

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

import com.dofast.module.wms.controller.admin.allocatedline.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedline.AllocatedLineDO;
import com.dofast.module.wms.convert.allocatedline.AllocatedLineConvert;
import com.dofast.module.wms.service.allocatedline.AllocatedLineService;

@Tag(name = "管理后台 - 调拨单身")
@RestController
@RequestMapping("/wms/allocated-line")
@Validated
public class AllocatedLineController {

    @Resource
    private AllocatedLineService allocatedLineService;

    @PostMapping("/create")
    @Operation(summary = "创建调拨单身")
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:create')")
    public CommonResult<Long> createAllocatedLine(@Valid @RequestBody AllocatedLineCreateReqVO createReqVO) {
        return success(allocatedLineService.createAllocatedLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新调拨单身")
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:update')")
    public CommonResult<Boolean> updateAllocatedLine(@Valid @RequestBody AllocatedLineUpdateReqVO updateReqVO) {
        allocatedLineService.updateAllocatedLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除调拨单身")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:delete')")
    public CommonResult<Boolean> deleteAllocatedLine(@RequestParam("id") Long id) {
        allocatedLineService.deleteAllocatedLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得调拨单身")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:query')")
    public CommonResult<AllocatedLineRespVO> getAllocatedLine(@RequestParam("id") Long id) {
        AllocatedLineDO allocatedLine = allocatedLineService.getAllocatedLine(id);
        return success(AllocatedLineConvert.INSTANCE.convert(allocatedLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得调拨单身列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:query')")
    public CommonResult<List<AllocatedLineRespVO>> getAllocatedLineList(@RequestParam("ids") Collection<Long> ids) {
        List<AllocatedLineDO> list = allocatedLineService.getAllocatedLineList(ids);
        return success(AllocatedLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得调拨单身分页")
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:query')")
    public CommonResult<PageResult<AllocatedLineRespVO>> getAllocatedLinePage(@Valid AllocatedLinePageReqVO pageVO) {
        PageResult<AllocatedLineDO> pageResult = allocatedLineService.getAllocatedLinePage(pageVO);
        return success(AllocatedLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出调拨单身 Excel")
    @PreAuthorize("@ss.hasPermission('wms:allocated-line:export')")
    @OperateLog(type = EXPORT)
    public void exportAllocatedLineExcel(@Valid AllocatedLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AllocatedLineDO> list = allocatedLineService.getAllocatedLineList(exportReqVO);
        // 导出 Excel
        List<AllocatedLineExcelVO> datas = AllocatedLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "调拨单身.xls", "数据", AllocatedLineExcelVO.class, datas);
    }

}
