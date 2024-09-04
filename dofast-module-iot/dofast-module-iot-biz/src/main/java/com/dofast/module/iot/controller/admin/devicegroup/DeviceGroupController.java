package com.dofast.module.iot.controller.admin.devicegroup;

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

import com.dofast.module.iot.controller.admin.devicegroup.vo.*;
import com.dofast.module.iot.dal.dataobject.devicegroup.DeviceGroupDO;
import com.dofast.module.iot.convert.devicegroup.DeviceGroupConvert;
import com.dofast.module.iot.service.devicegroup.DeviceGroupService;

@Tag(name = "管理后台 - 设备分组")
@RestController
@RequestMapping("/iot/device-group")
@Validated
public class DeviceGroupController {

    @Resource
    private DeviceGroupService deviceGroupService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分组")
    @PreAuthorize("@ss.hasPermission('iot:device-group:create')")
    public CommonResult<Long> createDeviceGroup(@Valid @RequestBody DeviceGroupCreateReqVO createReqVO) {
        return success(deviceGroupService.createDeviceGroup(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分组")
    @PreAuthorize("@ss.hasPermission('iot:device-group:update')")
    public CommonResult<Boolean> updateDeviceGroup(@Valid @RequestBody DeviceGroupUpdateReqVO updateReqVO) {
        deviceGroupService.updateDeviceGroup(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分组")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:device-group:delete')")
    public CommonResult<Boolean> deleteDeviceGroup(@RequestParam("id") Long id) {
        deviceGroupService.deleteDeviceGroup(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分组")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:device-group:query')")
    public CommonResult<DeviceGroupRespVO> getDeviceGroup(@RequestParam("id") Long id) {
        DeviceGroupDO deviceGroup = deviceGroupService.getDeviceGroup(id);
        return success(DeviceGroupConvert.INSTANCE.convert(deviceGroup));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备分组列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:device-group:query')")
    public CommonResult<List<DeviceGroupRespVO>> getDeviceGroupList(@RequestParam("ids") Collection<Long> ids) {
        List<DeviceGroupDO> list = deviceGroupService.getDeviceGroupList(ids);
        return success(DeviceGroupConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分组分页")
    @PreAuthorize("@ss.hasPermission('iot:device-group:query')")
    public CommonResult<PageResult<DeviceGroupRespVO>> getDeviceGroupPage(@Valid DeviceGroupPageReqVO pageVO) {
        PageResult<DeviceGroupDO> pageResult = deviceGroupService.getDeviceGroupPage(pageVO);
        return success(DeviceGroupConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分组 Excel")
    @PreAuthorize("@ss.hasPermission('iot:device-group:export')")
    @OperateLog(type = EXPORT)
    public void exportDeviceGroupExcel(@Valid DeviceGroupExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DeviceGroupDO> list = deviceGroupService.getDeviceGroupList(exportReqVO);
        // 导出 Excel
        List<DeviceGroupExcelVO> datas = DeviceGroupConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备分组.xls", "数据", DeviceGroupExcelVO.class, datas);
    }

}
