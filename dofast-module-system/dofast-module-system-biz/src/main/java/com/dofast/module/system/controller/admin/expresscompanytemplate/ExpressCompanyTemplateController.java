package com.dofast.module.system.controller.admin.expresscompanytemplate;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.*;
import com.dofast.module.system.convert.expresscompanytemplate.ExpressCompanyTemplateConvert;
import com.dofast.module.system.dal.dataobject.expresscompanytemplate.ExpressCompanyTemplateDO;
import com.dofast.module.system.service.expresscompanytemplate.ExpressCompanyTemplateService;
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

@Tag(name = "管理后台 - 系统物流公司")
@RestController
@RequestMapping("/system/express-company-template")
@Validated
public class ExpressCompanyTemplateController {

    @Resource
    private ExpressCompanyTemplateService expressCompanyTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建系统物流公司")
    @PreAuthorize("@ss.hasPermission('system:express-company-template:create')")
    public CommonResult<Integer> createExpressCompanyTemplate(@Valid @RequestBody ExpressCompanyTemplateCreateReqVO createReqVO) {
        return success(expressCompanyTemplateService.createExpressCompanyTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统物流公司")
    @PreAuthorize("@ss.hasPermission('system:express-company-template:update')")
    public CommonResult<Boolean> updateExpressCompanyTemplate(@Valid @RequestBody ExpressCompanyTemplateUpdateReqVO updateReqVO) {
        expressCompanyTemplateService.updateExpressCompanyTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统物流公司")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:express-company-template:delete')")
    public CommonResult<Boolean> deleteExpressCompanyTemplate(@RequestParam("id") Integer id) {
        expressCompanyTemplateService.deleteExpressCompanyTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统物流公司")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:express-company-template:query')")
    public CommonResult<ExpressCompanyTemplateRespVO> getExpressCompanyTemplate(@RequestParam("id") Integer id) {
        ExpressCompanyTemplateDO expressCompanyTemplate = expressCompanyTemplateService.getExpressCompanyTemplate(id);
        return success(ExpressCompanyTemplateConvert.INSTANCE.convert(expressCompanyTemplate));
    }

    @GetMapping("/list")
    @Operation(summary = "获得系统物流公司列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:express-company-template:query')")
    public CommonResult<List<ExpressCompanyTemplateRespVO>> getExpressCompanyTemplateList(@RequestParam("ids") Collection<Integer> ids) {
        List<ExpressCompanyTemplateDO> list = expressCompanyTemplateService.getExpressCompanyTemplateList(ids);
        return success(ExpressCompanyTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统物流公司分页")
    @PreAuthorize("@ss.hasPermission('system:express-company-template:query')")
    public CommonResult<PageResult<ExpressCompanyTemplateRespVO>> getExpressCompanyTemplatePage(@Valid ExpressCompanyTemplatePageReqVO pageVO) {
        PageResult<ExpressCompanyTemplateDO> pageResult = expressCompanyTemplateService.getExpressCompanyTemplatePage(pageVO);
        return success(ExpressCompanyTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统物流公司 Excel")
    @PreAuthorize("@ss.hasPermission('system:express-company-template:export')")
    @OperateLog(type = EXPORT)
    public void exportExpressCompanyTemplateExcel(@Valid ExpressCompanyTemplateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ExpressCompanyTemplateDO> list = expressCompanyTemplateService.getExpressCompanyTemplateList(exportReqVO);
        // 导出 Excel
        List<ExpressCompanyTemplateExcelVO> datas = ExpressCompanyTemplateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "系统物流公司.xls", "数据", ExpressCompanyTemplateExcelVO.class, datas);
    }

}
