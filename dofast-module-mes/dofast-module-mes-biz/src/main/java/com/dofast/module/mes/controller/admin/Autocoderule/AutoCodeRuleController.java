package com.dofast.module.mes.controller.admin.Autocoderule;

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

import com.dofast.module.mes.controller.admin.Autocoderule.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocoderule.AutoCodeRuleDO;
import com.dofast.module.mes.convert.Autocoderule.AutoCodeRuleConvert;
import com.dofast.module.mes.service.Autocoderule.AutoCodeRuleService;

@Tag(name = "管理后台 - 编码生成规则")
@RestController
@RequestMapping("/mes/auto-code-rule")
@Validated
public class AutoCodeRuleController {

    @Resource
    private AutoCodeRuleService autoCodeRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建编码生成规则")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:create')")
    public CommonResult<Long> createAutoCodeRule(@Valid @RequestBody AutoCodeRuleCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(autoCodeRuleService.checkRuleCodeUnique(createReqVO.getRuleCode(),null))){
            return error(ErrorCodeConstants.RULE_CODE_EXITS);
        }
        if(Constant.NOT_UNIQUE.equals(autoCodeRuleService.checkRuleNameUnique(createReqVO.getRuleName(),null))){
            return error(ErrorCodeConstants.RULE_NAME_EXITS);
        }

        if("N".equals(createReqVO.getIsPadded())){
            createReqVO.setPaddedChar(null);
            createReqVO.setPaddedMethod(null);
        }
        return success(autoCodeRuleService.createAutoCodeRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新编码生成规则")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:update')")
    public CommonResult<Boolean> updateAutoCodeRule(@Valid @RequestBody AutoCodeRuleUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(autoCodeRuleService.checkRuleCodeUnique(updateReqVO.getRuleCode(),updateReqVO.getId()))){
            return error(ErrorCodeConstants.RULE_CODE_EXITS);
        }
        if(Constant.NOT_UNIQUE.equals(autoCodeRuleService.checkRuleNameUnique(updateReqVO.getRuleName(),updateReqVO.getId()))){
            return error(ErrorCodeConstants.RULE_NAME_EXITS);
        }
        autoCodeRuleService.updateAutoCodeRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除编码生成规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:delete')")
    public CommonResult<Boolean> deleteAutoCodeRule(@RequestParam("id") Long id) {
        autoCodeRuleService.deleteAutoCodeRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得编码生成规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:query')")
    public CommonResult<AutoCodeRuleRespVO> getAutoCodeRule(@RequestParam("id") Long id) {
        AutoCodeRuleDO autoCodeRule = autoCodeRuleService.getAutoCodeRule(id);
        return success(AutoCodeRuleConvert.INSTANCE.convert(autoCodeRule));
    }

    @GetMapping("/list")
    @Operation(summary = "获得编码生成规则列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:query')")
    public CommonResult<List<AutoCodeRuleRespVO>> getAutoCodeRuleList(@RequestParam("ids") Collection<Long> ids) {
        List<AutoCodeRuleDO> list = autoCodeRuleService.getAutoCodeRuleList(ids);
        return success(AutoCodeRuleConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得编码生成规则分页")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:query')")
    public CommonResult<PageResult<AutoCodeRuleRespVO>> getAutoCodeRulePage(@Valid AutoCodeRulePageReqVO pageVO) {
        PageResult<AutoCodeRuleDO> pageResult = autoCodeRuleService.getAutoCodeRulePage(pageVO);
        return success(AutoCodeRuleConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出编码生成规则 Excel")
    @PreAuthorize("@ss.hasPermission('mes:auto-code-rule:export')")
    @OperateLog(type = EXPORT)
    public void exportAutoCodeRuleExcel(@Valid AutoCodeRuleExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AutoCodeRuleDO> list = autoCodeRuleService.getAutoCodeRuleList(exportReqVO);
        // 导出 Excel
        List<AutoCodeRuleExcelVO> datas = AutoCodeRuleConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "编码生成规则.xls", "数据", AutoCodeRuleExcelVO.class, datas);
    }

}
