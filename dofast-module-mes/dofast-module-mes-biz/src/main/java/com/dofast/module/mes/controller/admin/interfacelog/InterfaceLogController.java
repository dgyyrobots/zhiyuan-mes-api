package com.dofast.module.mes.controller.admin.interfacelog;

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

import com.dofast.module.mes.controller.admin.interfacelog.vo.*;
import com.dofast.module.mes.dal.dataobject.interfacelog.InterfaceLogDO;
import com.dofast.module.mes.convert.interfacelog.InterfaceLogConvert;
import com.dofast.module.mes.service.interfacelog.InterfaceLogService;

@Tag(name = "管理后台 - 接口操作日志")
@RestController
@RequestMapping("/mes/interface-log")
@Validated
public class InterfaceLogController {

    @Resource
    private InterfaceLogService interfaceLogService;

    @PostMapping("/create")
    @Operation(summary = "创建接口操作日志")
    @PreAuthorize("@ss.hasPermission('mes:interface-log:create')")
    public CommonResult<Long> createInterfaceLog(@Valid @RequestBody InterfaceLogCreateReqVO createReqVO) {
        return success(interfaceLogService.createInterfaceLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新接口操作日志")
    @PreAuthorize("@ss.hasPermission('mes:interface-log:update')")
    public CommonResult<Boolean> updateInterfaceLog(@Valid @RequestBody InterfaceLogUpdateReqVO updateReqVO) {
        interfaceLogService.updateInterfaceLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除接口操作日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:interface-log:delete')")
    public CommonResult<Boolean> deleteInterfaceLog(@RequestParam("id") Long id) {
        interfaceLogService.deleteInterfaceLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得接口操作日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:interface-log:query')")
    public CommonResult<InterfaceLogRespVO> getInterfaceLog(@RequestParam("id") Long id) {
        InterfaceLogDO interfaceLog = interfaceLogService.getInterfaceLog(id);
        return success(InterfaceLogConvert.INSTANCE.convert(interfaceLog));
    }

    @GetMapping("/list")
    @Operation(summary = "获得接口操作日志列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:interface-log:query')")
    public CommonResult<List<InterfaceLogRespVO>> getInterfaceLogList(@RequestParam("ids") Collection<Long> ids) {
        List<InterfaceLogDO> list = interfaceLogService.getInterfaceLogList(ids);
        return success(InterfaceLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得接口操作日志分页")
    @PreAuthorize("@ss.hasPermission('mes:interface-log:query')")
    public CommonResult<PageResult<InterfaceLogRespVO>> getInterfaceLogPage(@Valid InterfaceLogPageReqVO pageVO) {
        PageResult<InterfaceLogDO> pageResult = interfaceLogService.getInterfaceLogPage(pageVO);
        return success(InterfaceLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出接口操作日志 Excel")
    @PreAuthorize("@ss.hasPermission('mes:interface-log:export')")
    @OperateLog(type = EXPORT)
    public void exportInterfaceLogExcel(@Valid InterfaceLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<InterfaceLogDO> list = interfaceLogService.getInterfaceLogList(exportReqVO);
        // 导出 Excel
        List<InterfaceLogExcelVO> datas = InterfaceLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "接口操作日志.xls", "数据", InterfaceLogExcelVO.class, datas);
    }

}
