package com.dofast.module.trade.controller.admin.calculate.calculatecategory;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.*;
import com.dofast.module.trade.convert.calculatecategory.CalculateCategoryConvert;
import com.dofast.module.trade.dal.dataobject.calculatecategory.CalculateCategoryDO;
import com.dofast.module.trade.service.calculatecategory.CalculateCategoryService;
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

@Tag(name = "管理后台 - 计价分类")
@RestController
@RequestMapping("/trade/calculate-category")
@Validated
public class CalculateCategoryController {

    @Resource
    private CalculateCategoryService calculateCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建计价分类")
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:create')")
    public CommonResult<Integer> createCalculateCategory(@Valid @RequestBody CalculateCategoryCreateReqVO createReqVO) {
        return success(calculateCategoryService.createCalculateCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新计价分类")
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:update')")
    public CommonResult<Boolean> updateCalculateCategory(@Valid @RequestBody CalculateCategoryUpdateReqVO updateReqVO) {
        calculateCategoryService.updateCalculateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除计价分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:delete')")
    public CommonResult<Boolean> deleteCalculateCategory(@RequestParam("id") Integer id) {
        calculateCategoryService.deleteCalculateCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得计价分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:query')")
    public CommonResult<CalculateCategoryRespVO> getCalculateCategory(@RequestParam("id") Integer id) {
        CalculateCategoryDO calculateCategory = calculateCategoryService.getCalculateCategory(id);
        return success(CalculateCategoryConvert.INSTANCE.convert(calculateCategory));
    }

    @GetMapping("/list")
    @Operation(summary = "获得计价分类列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:query')")
    public CommonResult<List<CalculateCategoryRespVO>> getCalculateCategoryList(@RequestParam("ids") Collection<Integer> ids) {
        List<CalculateCategoryDO> list = calculateCategoryService.getCalculateCategoryList(ids);
        return success(CalculateCategoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得计价分类分页")
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:query')")
    public CommonResult<PageResult<CalculateCategoryRespVO>> getCalculateCategoryPage(@Valid CalculateCategoryPageReqVO pageVO) {
        PageResult<CalculateCategoryDO> pageResult = calculateCategoryService.getCalculateCategoryPage(pageVO);
        return success(CalculateCategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出计价分类 Excel")
    @PreAuthorize("@ss.hasPermission('trade:calculate-category:export')")
    @OperateLog(type = EXPORT)
    public void exportCalculateCategoryExcel(@Valid CalculateCategoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CalculateCategoryDO> list = calculateCategoryService.getCalculateCategoryList(exportReqVO);
        // 导出 Excel
        List<CalculateCategoryExcelVO> datas = CalculateCategoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "计价分类.xls", "数据", CalculateCategoryExcelVO.class, datas);
    }

}
