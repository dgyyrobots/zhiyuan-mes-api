package com.dofast.module.trade.controller.admin.calculate;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.calculate.vo.*;
import com.dofast.module.trade.convert.calculate.CalculateTypeConvert;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateTypeDO;
import com.dofast.module.trade.service.calculate.CalculateTypeService;
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

@Tag(name = "管理后台 - 计价类型")
@RestController
@RequestMapping("/trade/calculate-type")
@Validated
public class CalculateTypeController {

    @Resource
    private CalculateTypeService calculateTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建计价类型")
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:create')")
    public CommonResult<Integer> createCalculateType(@Valid @RequestBody CalculateTypeCreateReqVO createReqVO) {
        return success(calculateTypeService.createCalculateType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新计价类型")
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:update')")
    public CommonResult<Boolean> updateCalculateType(@Valid @RequestBody CalculateTypeUpdateReqVO updateReqVO) {
        calculateTypeService.updateCalculateType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除计价类型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:delete')")
    public CommonResult<Boolean> deleteCalculateType(@RequestParam("id") Integer id) {
        calculateTypeService.deleteCalculateType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得计价类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:query')")
    public CommonResult<CalculateTypeRespVO> getCalculateType(@RequestParam("id") Integer id) {
        CalculateTypeDO calculateType = calculateTypeService.getCalculateType(id);
        return success(CalculateTypeConvert.INSTANCE.convert(calculateType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得计价类型列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:query')")
    public CommonResult<List<CalculateTypeRespVO>> getCalculateTypeList(@RequestParam("ids") Collection<Integer> ids) {
        List<CalculateTypeDO> list = calculateTypeService.getCalculateTypeList(ids);
        return success(CalculateTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得计价类型分页")
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:query')")
    public CommonResult<PageResult<CalculateTypeRespVO>> getCalculateTypePage(@Valid CalculateTypePageReqVO pageVO) {
        PageResult<CalculateTypeDO> pageResult = calculateTypeService.getCalculateTypePage(pageVO);
        return success(CalculateTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出计价类型 Excel")
    @PreAuthorize("@ss.hasPermission('trade:calculate-type:export')")
    @OperateLog(type = EXPORT)
    public void exportCalculateTypeExcel(@Valid CalculateTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CalculateTypeDO> list = calculateTypeService.getCalculateTypeList(exportReqVO);
        // 导出 Excel
        List<CalculateTypeExcelVO> datas = CalculateTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "计价类型.xls", "数据", CalculateTypeExcelVO.class, datas);
    }

}
