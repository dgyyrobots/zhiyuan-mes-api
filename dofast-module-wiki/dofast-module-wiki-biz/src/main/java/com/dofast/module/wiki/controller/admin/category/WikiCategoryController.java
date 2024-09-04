package com.dofast.module.wiki.controller.admin.category;

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

import com.dofast.module.wiki.controller.admin.category.vo.*;
import com.dofast.module.wiki.dal.dataobject.category.WikiCategoryDO;
import com.dofast.module.wiki.convert.category.WikiCategoryConvert;
import com.dofast.module.wiki.service.category.WikiCategoryService;

@Tag(name = "管理后台 - 首页的分类")
@RestController
@RequestMapping("/wiki/category")
@Validated
public class WikiCategoryController {

    @Resource
    private WikiCategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "创建首页的分类")
    @PreAuthorize("@ss.hasPermission('wiki:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody WikiCategoryCreateReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新首页的分类")
    @PreAuthorize("@ss.hasPermission('wiki:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody WikiCategoryUpdateReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除首页的分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wiki:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得首页的分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wiki:category:query')")
    public CommonResult<WikiCategoryRespVO> getCategory(@RequestParam("id") Long id) {
        WikiCategoryDO category = categoryService.getCategory(id);
        return success(WikiCategoryConvert.INSTANCE.convert(category));
    }

    @GetMapping("/list")
    @Operation(summary = "获得首页的分类列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wiki:category:query')")
    public CommonResult<List<WikiCategoryRespVO>> getCategoryList(@RequestParam("ids") Collection<Long> ids) {
        List<WikiCategoryDO> list = categoryService.getCategoryList(ids);
        return success(WikiCategoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得首页的分类分页")
    @PreAuthorize("@ss.hasPermission('wiki:category:query')")
    public CommonResult<PageResult<WikiCategoryRespVO>> getCategoryPage(@Valid WikiCategoryPageReqVO pageVO) {
        PageResult<WikiCategoryDO> pageResult = categoryService.getCategoryPage(pageVO);
        return success(WikiCategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出首页的分类 Excel")
    @PreAuthorize("@ss.hasPermission('wiki:category:export')")
    @OperateLog(type = EXPORT)
    public void exportCategoryExcel(@Valid WikiCategoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WikiCategoryDO> list = categoryService.getCategoryList(exportReqVO);
        // 导出 Excel
        List<WikiCategoryExcelVO> datas = WikiCategoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "首页的分类.xls", "数据", WikiCategoryExcelVO.class, datas);
    }

}
