package com.dofast.module.trade.controller.admin.calculate.calculatemodel;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.*;
import com.dofast.module.trade.convert.calculatemodel.CalculateModelConvert;
import com.dofast.module.trade.dal.dataobject.calculatemodel.CalculateModelDO;
import com.dofast.module.trade.service.calculatemodel.CalculateModelService;
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

@Tag(name = "管理后台 - 计价模型")
@RestController
@RequestMapping("/trade/calculate-model")
@Validated
public class CalculateModelController {

    @Resource
    private CalculateModelService calculateModelService;

    @PostMapping("/create")
    @Operation(summary = "创建计价模型")
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:create')")
    public CommonResult<Integer> createCalculateModel(@Valid @RequestBody CalculateModelCreateReqVO createReqVO) {
        return success(calculateModelService.createCalculateModel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新计价模型")
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:update')")
    public CommonResult<Boolean> updateCalculateModel(@Valid @RequestBody CalculateModelUpdateReqVO updateReqVO) {
        calculateModelService.updateCalculateModel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除计价模型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:delete')")
    public CommonResult<Boolean> deleteCalculateModel(@RequestParam("id") Integer id) {
        calculateModelService.deleteCalculateModel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得计价模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:query')")
    public CommonResult<CalculateModelRespVO> getCalculateModel(@RequestParam("id") Integer id) {
        CalculateModelDO calculateModel = calculateModelService.getCalculateModel(id);
        return success(CalculateModelConvert.INSTANCE.convert(calculateModel));
    }

    @GetMapping("/list")
    @Operation(summary = "获得计价模型列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:query')")
    public CommonResult<List<CalculateModelRespVO>> getCalculateModelList(@RequestParam("ids") Collection<Integer> ids) {
        List<CalculateModelDO> list = calculateModelService.getCalculateModelList(ids);
        return success(CalculateModelConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得计价模型分页")
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:query')")
    public CommonResult<PageResult<CalculateModelRespVO>> getCalculateModelPage(@Valid CalculateModelPageReqVO pageVO) {
        PageResult<CalculateModelDO> pageResult = calculateModelService.getCalculateModelPage(pageVO);
        return success(CalculateModelConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出计价模型 Excel")
    @PreAuthorize("@ss.hasPermission('trade:calculate-model:export')")
    @OperateLog(type = EXPORT)
    public void exportCalculateModelExcel(@Valid CalculateModelExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CalculateModelDO> list = calculateModelService.getCalculateModelList(exportReqVO);
        // 导出 Excel
        List<CalculateModelExcelVO> datas = CalculateModelConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "计价模型.xls", "数据", CalculateModelExcelVO.class, datas);
    }

}
