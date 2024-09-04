package com.dofast.module.iot.controller.admin.alertlog;

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

import com.dofast.module.iot.controller.admin.alertlog.vo.*;
import com.dofast.module.iot.dal.dataobject.alertlog.AlertLogDO;
import com.dofast.module.iot.convert.alertlog.AlertLogConvert;
import com.dofast.module.iot.service.alertlog.AlertLogService;

@Tag(name = "管理后台 - 设备告警日志")
@RestController
@RequestMapping("/iot/alert-log")
@Validated
public class AlertLogController {

    @Resource
    private AlertLogService alertLogService;

    @PostMapping("/create")
    @Operation(summary = "创建设备告警日志")
    @PreAuthorize("@ss.hasPermission('iot:alert-log:create')")
    public CommonResult<Long> createAlertLog(@Valid @RequestBody AlertLogCreateReqVO createReqVO) {
        return success(alertLogService.createAlertLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备告警日志")
    @PreAuthorize("@ss.hasPermission('iot:alert-log:update')")
    public CommonResult<Boolean> updateAlertLog(@Valid @RequestBody AlertLogUpdateReqVO updateReqVO) {
        alertLogService.updateAlertLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备告警日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:alert-log:delete')")
    public CommonResult<Boolean> deleteAlertLog(@RequestParam("id") Long id) {
        alertLogService.deleteAlertLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备告警日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:alert-log:query')")
    public CommonResult<AlertLogRespVO> getAlertLog(@RequestParam("id") Long id) {
        AlertLogDO alertLog = alertLogService.getAlertLog(id);
        return success(AlertLogConvert.INSTANCE.convert(alertLog));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备告警日志列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:alert-log:query')")
    public CommonResult<List<AlertLogRespVO>> getAlertLogList(@RequestParam("ids") Collection<Long> ids) {
        List<AlertLogDO> list = alertLogService.getAlertLogList(ids);
        return success(AlertLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备告警日志分页")
    @PreAuthorize("@ss.hasPermission('iot:alert-log:query')")
    public CommonResult<PageResult<AlertLogRespVO>> getAlertLogPage(@Valid AlertLogPageReqVO pageVO) {
        PageResult<AlertLogDO> pageResult = alertLogService.getAlertLogPage(pageVO);
        return success(AlertLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备告警日志 Excel")
    @PreAuthorize("@ss.hasPermission('iot:alert-log:export')")
    @OperateLog(type = EXPORT)
    public void exportAlertLogExcel(@Valid AlertLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AlertLogDO> list = alertLogService.getAlertLogList(exportReqVO);
        // 导出 Excel
        List<AlertLogExcelVO> datas = AlertLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备告警日志.xls", "数据", AlertLogExcelVO.class, datas);
    }

}
