package com.dofast.module.system.controller.admin.expresstemplate;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.system.controller.admin.expresstemplate.vo.*;
import com.dofast.module.system.convert.expresstemplate.ExpressTemplateConvert;
import com.dofast.module.system.dal.dataobject.expresstemplate.ExpressTemplateDO;
import com.dofast.module.system.service.expresstemplate.ExpressTemplateService;
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

@Tag(name = "管理后台 - 运费模板")
@RestController
@RequestMapping("/system/express-template")
@Validated
public class ExpressTemplateController {

    @Resource
    private ExpressTemplateService expressTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建运费模板")
    @PreAuthorize("@ss.hasPermission('system:express-template:create')")
    public CommonResult<Long> createExpressTemplate(@Valid @RequestBody ExpressTemplateCreateReqVO createReqVO) {
        return success(expressTemplateService.createExpressTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运费模板")
    @PreAuthorize("@ss.hasPermission('system:express-template:update')")
    public CommonResult<Boolean> updateExpressTemplate(@Valid @RequestBody ExpressTemplateUpdateReqVO updateReqVO) {
        expressTemplateService.updateExpressTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运费模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:express-template:delete')")
    public CommonResult<Boolean> deleteExpressTemplate(@RequestParam("id") Long id) {
        expressTemplateService.deleteExpressTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运费模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:express-template:query')")
    public CommonResult<ExpressTemplateRespVO> getExpressTemplate(@RequestParam("id") Long id) {
        ExpressTemplateDO expressTemplate = expressTemplateService.getExpressTemplate(id);
        return success(ExpressTemplateConvert.INSTANCE.convert(expressTemplate));
    }

    @GetMapping("/list")
    @Operation(summary = "获得运费模板列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:express-template:query')")
    public CommonResult<List<ExpressTemplateRespVO>> getExpressTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<ExpressTemplateDO> list = expressTemplateService.getExpressTemplateList(ids);
        return success(ExpressTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运费模板分页")
    @PreAuthorize("@ss.hasPermission('system:express-template:query')")
    public CommonResult<PageResult<ExpressTemplateRespVO>> getExpressTemplatePage(@Valid ExpressTemplatePageReqVO pageVO) {
        PageResult<ExpressTemplateDO> pageResult = expressTemplateService.getExpressTemplatePage(pageVO);
        return success(ExpressTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运费模板 Excel")
    @PreAuthorize("@ss.hasPermission('system:express-template:export')")
    @OperateLog(type = EXPORT)
    public void exportExpressTemplateExcel(@Valid ExpressTemplateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ExpressTemplateDO> list = expressTemplateService.getExpressTemplateList(exportReqVO);
        // 导出 Excel
        List<ExpressTemplateExcelVO> datas = ExpressTemplateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "运费模板.xls", "数据", ExpressTemplateExcelVO.class, datas);
    }

}
