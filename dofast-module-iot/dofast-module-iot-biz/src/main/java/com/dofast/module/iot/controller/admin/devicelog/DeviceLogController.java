package com.dofast.module.iot.controller.admin.devicelog;

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

import com.dofast.module.iot.controller.admin.devicelog.vo.*;
import com.dofast.module.iot.dal.dataobject.devicelog.DeviceLogDO;
import com.dofast.module.iot.convert.devicelog.DeviceLogConvert;
import com.dofast.module.iot.service.devicelog.DeviceLogService;

@Tag(name = "管理后台 - 设备日志")
@RestController
@RequestMapping("/iot/device-log")
@Validated
public class DeviceLogController {

    @Resource
    private DeviceLogService deviceLogService;

    @PostMapping("/create")
    @Operation(summary = "创建设备日志")
    @PreAuthorize("@ss.hasPermission('iot:device-log:create')")
    public CommonResult<Long> createDeviceLog(@Valid @RequestBody DeviceLogCreateReqVO createReqVO) {
        return success(deviceLogService.createDeviceLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备日志")
    @PreAuthorize("@ss.hasPermission('iot:device-log:update')")
    public CommonResult<Boolean> updateDeviceLog(@Valid @RequestBody DeviceLogUpdateReqVO updateReqVO) {
        deviceLogService.updateDeviceLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:device-log:delete')")
    public CommonResult<Boolean> deleteDeviceLog(@RequestParam("id") Long id) {
        deviceLogService.deleteDeviceLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:device-log:query')")
    public CommonResult<DeviceLogRespVO> getDeviceLog(@RequestParam("id") Long id) {
        DeviceLogDO deviceLog = deviceLogService.getDeviceLog(id);
        return success(DeviceLogConvert.INSTANCE.convert(deviceLog));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备日志列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:device-log:query')")
    public CommonResult<List<DeviceLogRespVO>> getDeviceLogList(@RequestParam("ids") Collection<Long> ids) {
        List<DeviceLogDO> list = deviceLogService.getDeviceLogList(ids);
        return success(DeviceLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备日志分页")
    @PreAuthorize("@ss.hasPermission('iot:device-log:query')")
    public CommonResult<PageResult<DeviceLogRespVO>> getDeviceLogPage(@Valid DeviceLogPageReqVO pageVO) {
        PageResult<DeviceLogDO> pageResult = deviceLogService.getDeviceLogPage(pageVO);
        return success(DeviceLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备日志 Excel")
    @PreAuthorize("@ss.hasPermission('iot:device-log:export')")
    @OperateLog(type = EXPORT)
    public void exportDeviceLogExcel(@Valid DeviceLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DeviceLogDO> list = deviceLogService.getDeviceLogList(exportReqVO);
        // 导出 Excel
        List<DeviceLogExcelVO> datas = DeviceLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备日志.xls", "数据", DeviceLogExcelVO.class, datas);
    }

}
