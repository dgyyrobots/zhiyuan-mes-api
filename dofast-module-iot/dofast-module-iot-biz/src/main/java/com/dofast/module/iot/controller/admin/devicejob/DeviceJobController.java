package com.dofast.module.iot.controller.admin.devicejob;

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

import com.dofast.module.iot.controller.admin.devicejob.vo.*;
import com.dofast.module.iot.dal.dataobject.devicejob.DeviceJobDO;
import com.dofast.module.iot.convert.devicejob.DeviceJobConvert;
import com.dofast.module.iot.service.devicejob.DeviceJobService;

@Tag(name = "管理后台 - 设备定时")
@RestController
@RequestMapping("/iot/device-job")
@Validated
public class DeviceJobController {

    @Resource
    private DeviceJobService deviceJobService;

    @PostMapping("/create")
    @Operation(summary = "创建设备定时")
    @PreAuthorize("@ss.hasPermission('iot:device-job:create')")
    public CommonResult<Long> createDeviceJob(@Valid @RequestBody DeviceJobCreateReqVO createReqVO) {
        return success(deviceJobService.createDeviceJob(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备定时")
    @PreAuthorize("@ss.hasPermission('iot:device-job:update')")
    public CommonResult<Boolean> updateDeviceJob(@Valid @RequestBody DeviceJobUpdateReqVO updateReqVO) {
        deviceJobService.updateDeviceJob(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备定时")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:device-job:delete')")
    public CommonResult<Boolean> deleteDeviceJob(@RequestParam("id") Long id) {
        deviceJobService.deleteDeviceJob(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备定时")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:device-job:query')")
    public CommonResult<DeviceJobRespVO> getDeviceJob(@RequestParam("id") Long id) {
        DeviceJobDO deviceJob = deviceJobService.getDeviceJob(id);
        return success(DeviceJobConvert.INSTANCE.convert(deviceJob));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备定时列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:device-job:query')")
    public CommonResult<List<DeviceJobRespVO>> getDeviceJobList(@RequestParam("ids") Collection<Long> ids) {
        List<DeviceJobDO> list = deviceJobService.getDeviceJobList(ids);
        return success(DeviceJobConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备定时分页")
    @PreAuthorize("@ss.hasPermission('iot:device-job:query')")
    public CommonResult<PageResult<DeviceJobRespVO>> getDeviceJobPage(@Valid DeviceJobPageReqVO pageVO) {
        PageResult<DeviceJobDO> pageResult = deviceJobService.getDeviceJobPage(pageVO);
        return success(DeviceJobConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备定时 Excel")
    @PreAuthorize("@ss.hasPermission('iot:device-job:export')")
    @OperateLog(type = EXPORT)
    public void exportDeviceJobExcel(@Valid DeviceJobExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DeviceJobDO> list = deviceJobService.getDeviceJobList(exportReqVO);
        // 导出 Excel
        List<DeviceJobExcelVO> datas = DeviceJobConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备定时.xls", "数据", DeviceJobExcelVO.class, datas);
    }

}
