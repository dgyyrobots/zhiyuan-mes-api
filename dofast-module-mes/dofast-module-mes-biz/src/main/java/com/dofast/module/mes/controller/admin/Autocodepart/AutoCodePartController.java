package com.dofast.module.mes.controller.admin.Autocodepart;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.enums.ErrorCodeConstants;
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

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.mes.controller.admin.Autocodepart.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import com.dofast.module.mes.convert.Autocodepart.AutoCodePartConvert;
import com.dofast.module.mes.service.Autocodepart.AutoCodePartService;

@Tag(name = "管理后台 - 编码生成规则组成")
@RestController
@RequestMapping("/mes/auto-code-part")
@Validated
public class AutoCodePartController {

    @Resource
    private AutoCodePartService autoCodePartService;

    @PostMapping("/create")
    @Operation(summary = "创建编码生成规则组成")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:create')")
    public CommonResult<Long> createAutoCodePart(@Valid @RequestBody AutoCodePartCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(autoCodePartService.checkPartUnique(createReqVO))){
            return error(ErrorCodeConstants.CODE_RULE_PART_NOT_UNIQUE);
        }
        return success(autoCodePartService.createAutoCodePart(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新编码生成规则组成")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:update')")
    public CommonResult<Boolean> updateAutoCodePart(@Valid @RequestBody AutoCodePartUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(autoCodePartService.checkPartUnique(updateReqVO))){
            return error(ErrorCodeConstants.CODE_RULE_PART_NOT_UNIQUE);
        }
        autoCodePartService.updateAutoCodePart(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除编码生成规则组成")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:delete')")
    public CommonResult<Boolean> deleteAutoCodePart(@RequestParam("id") Long id) {
        autoCodePartService.deleteAutoCodePart(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得编码生成规则组成")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:query')")
    public CommonResult<AutoCodePartRespVO> getAutoCodePart(@RequestParam("id") Long id) {
        AutoCodePartDO autoCodePart = autoCodePartService.getAutoCodePart(id);
        return success(AutoCodePartConvert.INSTANCE.convert(autoCodePart));
    }

    @GetMapping("/list")
    @Operation(summary = "获得编码生成规则组成列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:query')")
    public CommonResult<List<AutoCodePartRespVO>> getAutoCodePartList(@RequestParam("ids") Collection<Long> ids) {
        List<AutoCodePartDO> list = autoCodePartService.getAutoCodePartList(ids);
        return success(AutoCodePartConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得编码生成规则组成分页")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:query')")
    public CommonResult<PageResult<AutoCodePartRespVO>> getAutoCodePartPage(@Valid AutoCodePartPageReqVO pageVO) {
        PageResult<AutoCodePartDO> pageResult = autoCodePartService.getAutoCodePartPage(pageVO);
        return success(AutoCodePartConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出编码生成规则组成 Excel")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-part:export')")
    @OperateLog(type = EXPORT)
    public void exportAutoCodePartExcel(@Valid AutoCodePartExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AutoCodePartDO> list = autoCodePartService.getAutoCodePartList(exportReqVO);
        // 导出 Excel
        List<AutoCodePartExcelVO> datas = AutoCodePartConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "编码生成规则组成.xls", "数据", AutoCodePartExcelVO.class, datas);
    }

}
