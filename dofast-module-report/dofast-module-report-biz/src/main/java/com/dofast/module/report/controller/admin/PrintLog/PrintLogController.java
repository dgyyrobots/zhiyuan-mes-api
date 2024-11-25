package com.dofast.module.report.controller.admin.PrintLog;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.report.controller.admin.PrintLog.vo.*;
import com.dofast.module.report.convert.PrintLog.PrintLogConvert;
import com.dofast.module.report.dal.dataobject.PrintLog.PrintLogDO;
import com.dofast.module.report.service.PrintLog.PrintLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 打印日志")
@RestController
@RequestMapping("/report/print-log")
@Validated
public class PrintLogController {

    @Resource
    private PrintLogService printLogService;

    @PostMapping("/create")
    @Operation(summary = "创建打印日志")
    //@PreAuthorize("@ss.hasPermission('report:print-log:create')")
    public CommonResult<Long> createPrintLog(@Valid @RequestBody PrintLogCreateReqVO createReqVO) {
        return success(printLogService.createPrintLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新打印日志")
    //@PreAuthorize("@ss.hasPermission('report:print-log:update')")
    public CommonResult<Boolean> updatePrintLog(@Valid @RequestBody PrintLogUpdateReqVO updateReqVO) {
        printLogService.updatePrintLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除打印日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('report:print-log:delete')")
    public CommonResult<Boolean> deletePrintLog(@RequestParam("id") Long id) {
        printLogService.deletePrintLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得打印日志")
    //@Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('report:print-log:query')")
    public CommonResult<PrintLogRespVO> getPrintLog(@RequestParam("id") Long id) {
        PrintLogDO printLog = printLogService.getPrintLog(id);
        return success(PrintLogConvert.INSTANCE.convert(printLog));
    }

    @GetMapping("/list")
    @Operation(summary = "获得打印日志列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('report:print-log:query')")
    public CommonResult<List<PrintLogRespVO>> getPrintLogList(@RequestParam("ids") Collection<Long> ids) {
        List<PrintLogDO> list = printLogService.getPrintLogList(ids);
        return success(PrintLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得打印日志分页")
    @PreAuthorize("@ss.hasPermission('report:print-log:query')")
    public CommonResult<PageResult<PrintLogRespVO>> getPrintLogPage(@Valid PrintLogPageReqVO pageVO) {
        PageResult<PrintLogDO> pageResult = printLogService.getPrintLogPage(pageVO);
        return success(PrintLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出打印日志 Excel")
    @PreAuthorize("@ss.hasPermission('report:print-log:export')")
    @OperateLog(type = EXPORT)
    public void exportPrintLogExcel(@Valid PrintLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PrintLogDO> list = printLogService.getPrintLogList(exportReqVO);
        // 导出 Excel
        List<PrintLogExcelVO> datas = PrintLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "打印日志.xls", "数据", PrintLogExcelVO.class, datas);
    }

}
