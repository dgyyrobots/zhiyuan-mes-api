package com.dofast.module.iot.controller.admin.thingsmodeltemplate;

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

import com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodeltemplate.ThingsModelTemplateDO;
import com.dofast.module.iot.convert.thingsmodeltemplate.ThingsModelTemplateConvert;
import com.dofast.module.iot.service.thingsmodeltemplate.ThingsModelTemplateService;

@Tag(name = "管理后台 - 物模型模板")
@RestController
@RequestMapping("/iot/things-model-template")
@Validated
public class ThingsModelTemplateController {

    @Resource
    private ThingsModelTemplateService thingsModelTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建物模型模板")
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:create')")
    public CommonResult<Long> createThingsModelTemplate(@Valid @RequestBody ThingsModelTemplateCreateReqVO createReqVO) {
        return success(thingsModelTemplateService.createThingsModelTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物模型模板")
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:update')")
    public CommonResult<Boolean> updateThingsModelTemplate(@Valid @RequestBody ThingsModelTemplateUpdateReqVO updateReqVO) {
        thingsModelTemplateService.updateThingsModelTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物模型模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:delete')")
    public CommonResult<Boolean> deleteThingsModelTemplate(@RequestParam("id") Long id) {
        thingsModelTemplateService.deleteThingsModelTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物模型模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:query')")
    public CommonResult<ThingsModelTemplateRespVO> getThingsModelTemplate(@RequestParam("id") Long id) {
        ThingsModelTemplateDO thingsModelTemplate = thingsModelTemplateService.getThingsModelTemplate(id);
        return success(ThingsModelTemplateConvert.INSTANCE.convert(thingsModelTemplate));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物模型模板列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:query')")
    public CommonResult<List<ThingsModelTemplateRespVO>> getThingsModelTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<ThingsModelTemplateDO> list = thingsModelTemplateService.getThingsModelTemplateList(ids);
        return success(ThingsModelTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物模型模板分页")
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:query')")
    public CommonResult<PageResult<ThingsModelTemplateRespVO>> getThingsModelTemplatePage(@Valid ThingsModelTemplatePageReqVO pageVO) {
        PageResult<ThingsModelTemplateDO> pageResult = thingsModelTemplateService.getThingsModelTemplatePage(pageVO);
        return success(ThingsModelTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物模型模板 Excel")
    @PreAuthorize("@ss.hasPermission('iot:things-model-template:export')")
    @OperateLog(type = EXPORT)
    public void exportThingsModelTemplateExcel(@Valid ThingsModelTemplateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ThingsModelTemplateDO> list = thingsModelTemplateService.getThingsModelTemplateList(exportReqVO);
        // 导出 Excel
        List<ThingsModelTemplateExcelVO> datas = ThingsModelTemplateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物模型模板.xls", "数据", ThingsModelTemplateExcelVO.class, datas);
    }

}
