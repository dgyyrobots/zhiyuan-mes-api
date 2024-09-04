package com.dofast.module.iot.controller.admin.alert;

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

import com.dofast.module.iot.controller.admin.alert.vo.*;
import com.dofast.module.iot.dal.dataobject.alert.AlertDO;
import com.dofast.module.iot.convert.alert.AlertConvert;
import com.dofast.module.iot.service.alert.AlertService;

@Tag(name = "管理后台 - 设备告警")
@RestController
@RequestMapping("/iot/alert")
@Validated
public class AlertController {

    @Resource
    private AlertService alertService;

    @PostMapping("/create")
    @Operation(summary = "创建设备告警")
    @PreAuthorize("@ss.hasPermission('iot:alert:create')")
    public CommonResult<Long> createAlert(@Valid @RequestBody AlertCreateReqVO createReqVO) {
        return success(alertService.createAlert(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备告警")
    @PreAuthorize("@ss.hasPermission('iot:alert:update')")
    public CommonResult<Boolean> updateAlert(@Valid @RequestBody AlertUpdateReqVO updateReqVO) {
        alertService.updateAlert(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备告警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:alert:delete')")
    public CommonResult<Boolean> deleteAlert(@RequestParam("id") Long id) {
        alertService.deleteAlert(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备告警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:alert:query')")
    public CommonResult<AlertRespVO> getAlert(@RequestParam("id") Long id) {
        AlertDO alert = alertService.getAlert(id);
        return success(AlertConvert.INSTANCE.convert(alert));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备告警列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:alert:query')")
    public CommonResult<List<AlertRespVO>> getAlertList(@RequestParam("ids") Collection<Long> ids) {
        List<AlertDO> list = alertService.getAlertList(ids);
        return success(AlertConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备告警分页")
    @PreAuthorize("@ss.hasPermission('iot:alert:query')")
    public CommonResult<PageResult<AlertRespVO>> getAlertPage(@Valid AlertPageReqVO pageVO) {
        PageResult<AlertDO> pageResult = alertService.getAlertPage(pageVO);
        return success(AlertConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备告警 Excel")
    @PreAuthorize("@ss.hasPermission('iot:alert:export')")
    @OperateLog(type = EXPORT)
    public void exportAlertExcel(@Valid AlertExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AlertDO> list = alertService.getAlertList(exportReqVO);
        // 导出 Excel
        List<AlertExcelVO> datas = AlertConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备告警.xls", "数据", AlertExcelVO.class, datas);
    }

}
