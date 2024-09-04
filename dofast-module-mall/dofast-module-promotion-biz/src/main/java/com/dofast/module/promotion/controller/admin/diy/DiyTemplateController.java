package com.dofast.module.promotion.controller.admin.diy;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.promotion.controller.admin.diy.vo.template.*;
import com.dofast.module.promotion.convert.diy.DiyTemplateConvert;
import com.dofast.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.dofast.module.promotion.dal.dataobject.diy.DiyTemplatesDO;
import com.dofast.module.promotion.service.diy.DiyPageService;
import com.dofast.module.promotion.service.diy.DiyTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 装修模板")
@RestController("DiyTemplateControllerA")
@RequestMapping("/promotion/diy-template")
@Validated
public class DiyTemplateController {

    @Resource
    private DiyTemplateService diyTemplatesService;
    @Resource
    private DiyPageService diyPageService;

    @PostMapping("/create")
    @Operation(summary = "创建装修模板")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:create')")
    public CommonResult<Long> createDiyTemplate(@Valid @RequestBody DiyTemplateCreateReqVO createReqVO) {
        return success(diyTemplatesService.createDiyTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新装修模板")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:update')")
    public CommonResult<Boolean> updateDiyTemplate(@Valid @RequestBody DiyTemplateUpdateReqVO updateReqVO) {
        diyTemplatesService.updateDiyTemplate(updateReqVO);
        return success(true);
    }

    @PutMapping("/use")
    @Operation(summary = "使用装修模板")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:use')")
    public CommonResult<Boolean> useDiyTemplate(@RequestParam("id") Long id) {
        diyTemplatesService.useDiyTemplate(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除装修模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:delete')")
    public CommonResult<Boolean> deleteDiyTemplate(@RequestParam("id") Long id) {
        diyTemplatesService.deleteDiyTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得装修模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<DiyTemplateRespVO> getDiyTemplate(@RequestParam("id") Long id) {
        DiyTemplatesDO diyTemplate = diyTemplatesService.getDiyTemplate(id);
        return success(DiyTemplateConvert.INSTANCE.convert(diyTemplate));
    }

    @GetMapping("/page")
    @Operation(summary = "获得装修模板分页")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<PageResult<DiyTemplateRespVO>> getDiyTemplatePage(@Valid DiyTemplatePageReqVO pageVO) {
        PageResult<DiyTemplatesDO> pageResult = diyTemplatesService.getDiyTemplatePage(pageVO);
        return success(DiyTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/get-property")
    @Operation(summary = "获得装修模板属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<DiyTemplatePropertyRespVO> getDiyTemplateProperty(@RequestParam("id") Long id) {
        DiyTemplatesDO diyTemplate = diyTemplatesService.getDiyTemplate(id);
        List<DiyPageDO> pages = diyPageService.getDiyPageByTemplateId(id);
        return success(DiyTemplateConvert.INSTANCE.convertPropertyVo(diyTemplate, pages));
    }

    @PutMapping("/update-property")
    @Operation(summary = "更新装修模板属性")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:update')")
    public CommonResult<Boolean> updateDiyTemplateProperty(@Valid @RequestBody DiyTemplatePropertyUpdateRequestVO updateReqVO) {
        diyTemplatesService.updateDiyTemplateProperty(updateReqVO);
        return success(true);
    }

}
