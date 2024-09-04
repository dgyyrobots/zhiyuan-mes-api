package com.dofast.module.iot.controller.admin.thingsmodel;

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

import com.dofast.module.iot.controller.admin.thingsmodel.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodel.ThingsModelDO;
import com.dofast.module.iot.convert.thingsmodel.ThingsModelConvert;
import com.dofast.module.iot.service.thingsmodel.ThingsModelService;

@Tag(name = "管理后台 - 物模型")
@RestController
@RequestMapping("/iot/things-model")
@Validated
public class ThingsModelController {

    @Resource
    private ThingsModelService thingsModelService;

    @PostMapping("/create")
    @Operation(summary = "创建物模型")
    @PreAuthorize("@ss.hasPermission('iot:things-model:create')")
    public CommonResult<Long> createThingsModel(@Valid @RequestBody ThingsModelCreateReqVO createReqVO) {
        return success(thingsModelService.createThingsModel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物模型")
    @PreAuthorize("@ss.hasPermission('iot:things-model:update')")
    public CommonResult<Boolean> updateThingsModel(@Valid @RequestBody ThingsModelUpdateReqVO updateReqVO) {
        thingsModelService.updateThingsModel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物模型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:things-model:delete')")
    public CommonResult<Boolean> deleteThingsModel(@RequestParam("id") Long id) {
        thingsModelService.deleteThingsModel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:things-model:query')")
    public CommonResult<ThingsModelRespVO> getThingsModel(@RequestParam("id") Long id) {
        ThingsModelDO thingsModel = thingsModelService.getThingsModel(id);
        return success(ThingsModelConvert.INSTANCE.convert(thingsModel));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物模型列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:things-model:query')")
    public CommonResult<List<ThingsModelRespVO>> getThingsModelList(@RequestParam("ids") Collection<Long> ids) {
        List<ThingsModelDO> list = thingsModelService.getThingsModelList(ids);
        return success(ThingsModelConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物模型分页")
    @PreAuthorize("@ss.hasPermission('iot:things-model:query')")
    public CommonResult<PageResult<ThingsModelRespVO>> getThingsModelPage(@Valid ThingsModelPageReqVO pageVO) {
        PageResult<ThingsModelDO> pageResult = thingsModelService.getThingsModelPage(pageVO);
        return success(ThingsModelConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物模型 Excel")
    @PreAuthorize("@ss.hasPermission('iot:things-model:export')")
    @OperateLog(type = EXPORT)
    public void exportThingsModelExcel(@Valid ThingsModelExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ThingsModelDO> list = thingsModelService.getThingsModelList(exportReqVO);
        // 导出 Excel
        List<ThingsModelExcelVO> datas = ThingsModelConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物模型.xls", "数据", ThingsModelExcelVO.class, datas);
    }

}
