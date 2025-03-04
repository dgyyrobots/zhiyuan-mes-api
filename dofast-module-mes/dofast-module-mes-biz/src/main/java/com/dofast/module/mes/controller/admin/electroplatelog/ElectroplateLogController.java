package com.dofast.module.mes.controller.admin.electroplatelog;

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

import com.dofast.module.mes.controller.admin.electroplatelog.vo.*;
import com.dofast.module.mes.dal.dataobject.electroplatelog.ElectroplateLogDO;
import com.dofast.module.mes.convert.electroplatelog.ElectroplateLogConvert;
import com.dofast.module.mes.service.electroplatelog.ElectroplateLogService;

@Tag(name = "管理后台 - 制版房记录")
@RestController
@RequestMapping("/mes/electroplate-log")
@Validated
public class ElectroplateLogController {

    @Resource
    private ElectroplateLogService electroplateLogService;

    @PostMapping("/create")
    @Operation(summary = "创建制版房记录")
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:create')")
    public CommonResult<Long> createElectroplateLog(@Valid @RequestBody ElectroplateLogCreateReqVO createReqVO) {
        return success(electroplateLogService.createElectroplateLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新制版房记录")
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:update')")
    public CommonResult<Boolean> updateElectroplateLog(@Valid @RequestBody ElectroplateLogUpdateReqVO updateReqVO) {
        electroplateLogService.updateElectroplateLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除制版房记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:delete')")
    public CommonResult<Boolean> deleteElectroplateLog(@RequestParam("id") Long id) {
        electroplateLogService.deleteElectroplateLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得制版房记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:query')")
    public CommonResult<ElectroplateLogRespVO> getElectroplateLog(@RequestParam("id") Long id) {
        ElectroplateLogDO electroplateLog = electroplateLogService.getElectroplateLog(id);
        return success(ElectroplateLogConvert.INSTANCE.convert(electroplateLog));
    }

    @GetMapping("/list")
    @Operation(summary = "获得制版房记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:query')")
    public CommonResult<List<ElectroplateLogRespVO>> getElectroplateLogList(@RequestParam("ids") Collection<Long> ids) {
        List<ElectroplateLogDO> list = electroplateLogService.getElectroplateLogList(ids);
        return success(ElectroplateLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得制版房记录分页")
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:query')")
    public CommonResult<PageResult<ElectroplateLogRespVO>> getElectroplateLogPage(@Valid ElectroplateLogPageReqVO pageVO) {
        PageResult<ElectroplateLogDO> pageResult = electroplateLogService.getElectroplateLogPage(pageVO);
        return success(ElectroplateLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出制版房记录 Excel")
    @PreAuthorize("@ss.hasPermission('mes:electroplate-log:export')")
    @OperateLog(type = EXPORT)
    public void exportElectroplateLogExcel(@Valid ElectroplateLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ElectroplateLogDO> list = electroplateLogService.getElectroplateLogList(exportReqVO);
        // 导出 Excel
        List<ElectroplateLogExcelVO> datas = ElectroplateLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "制版房记录.xls", "数据", ElectroplateLogExcelVO.class, datas);
    }

}
