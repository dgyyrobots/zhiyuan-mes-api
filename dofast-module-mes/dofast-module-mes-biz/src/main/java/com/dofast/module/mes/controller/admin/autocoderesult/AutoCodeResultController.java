package com.dofast.module.mes.controller.admin.autocoderesult;

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

import com.dofast.module.mes.controller.admin.autocoderesult.vo.*;
import com.dofast.module.mes.dal.dataobject.autocoderesult.AutoCodeResultDO;
import com.dofast.module.mes.convert.autocoderesult.AutoCodeResultConvert;
import com.dofast.module.mes.service.autocoderesult.AutoCodeResultService;

@Tag(name = "管理后台 - 编码生成记录")
@RestController
@RequestMapping("/mes/auto-code-result")
@Validated
public class AutoCodeResultController {

    @Resource
    private AutoCodeResultService autoCodeResultService;

    @PostMapping("/create")
    @Operation(summary = "创建编码生成记录")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:create')")
    public CommonResult<Long> createAutoCodeResult(@Valid @RequestBody AutoCodeResultCreateReqVO createReqVO) {
        return success(autoCodeResultService.createAutoCodeResult(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新编码生成记录")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:update')")
    public CommonResult<Boolean> updateAutoCodeResult(@Valid @RequestBody AutoCodeResultUpdateReqVO updateReqVO) {
        autoCodeResultService.updateAutoCodeResult(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除编码生成记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:delete')")
    public CommonResult<Boolean> deleteAutoCodeResult(@RequestParam("id") Long id) {
        autoCodeResultService.deleteAutoCodeResult(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得编码生成记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:query')")
    public CommonResult<AutoCodeResultRespVO> getAutoCodeResult(@RequestParam("id") Long id) {
        AutoCodeResultDO autoCodeResult = autoCodeResultService.getAutoCodeResult(id);
        return success(AutoCodeResultConvert.INSTANCE.convert(autoCodeResult));
    }

    @GetMapping("/list")
    @Operation(summary = "获得编码生成记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:query')")
    public CommonResult<List<AutoCodeResultRespVO>> getAutoCodeResultList(@RequestParam("ids") Collection<Long> ids) {
        List<AutoCodeResultDO> list = autoCodeResultService.getAutoCodeResultList(ids);
        return success(AutoCodeResultConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得编码生成记录分页")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:query')")
    public CommonResult<PageResult<AutoCodeResultRespVO>> getAutoCodeResultPage(@Valid AutoCodeResultPageReqVO pageVO) {
        PageResult<AutoCodeResultDO> pageResult = autoCodeResultService.getAutoCodeResultPage(pageVO);
        return success(AutoCodeResultConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出编码生成记录 Excel")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-result:export')")
    @OperateLog(type = EXPORT)
    public void exportAutoCodeResultExcel(@Valid AutoCodeResultExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AutoCodeResultDO> list = autoCodeResultService.getAutoCodeResultList(exportReqVO);
        // 导出 Excel
        List<AutoCodeResultExcelVO> datas = AutoCodeResultConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "编码生成记录.xls", "数据", AutoCodeResultExcelVO.class, datas);
    }

}
