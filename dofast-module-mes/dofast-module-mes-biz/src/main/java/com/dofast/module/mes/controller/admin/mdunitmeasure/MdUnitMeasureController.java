package com.dofast.module.mes.controller.admin.mdunitmeasure;

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

import com.dofast.module.mes.controller.admin.mdunitmeasure.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitmeasure.MdUnitMeasureDO;
import com.dofast.module.mes.convert.mdunitmeasure.MdUnitMeasureConvert;
import com.dofast.module.mes.service.mdunitmeasure.MdUnitMeasureService;

@Tag(name = "管理后台 - 单位")
@RestController
@RequestMapping("/mes/md-unit-measure")
@Validated
public class MdUnitMeasureController {

    @Resource
    private MdUnitMeasureService mdUnitMeasureService;

    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:query')")
    @GetMapping("/listprimary")
    public CommonResult listPrimary(){
        MdUnitMeasureListVO mdUnitMeasure = new MdUnitMeasureListVO();
        mdUnitMeasure.setPrimaryFlag("Y");
        List<MdUnitMeasureDO> list = mdUnitMeasureService.getMdUnitMeasureList(mdUnitMeasure);
        return success(MdUnitMeasureConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/selectall")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:query')")
    @Operation(summary = "获得列表select-all")
    public CommonResult selectAll(){
        MdUnitMeasureListVO mdUnitMeasure = new MdUnitMeasureListVO();
        mdUnitMeasure.setEnableFlag("Y");
        List<MdUnitMeasureDO> list = mdUnitMeasureService.getMdUnitMeasureList(mdUnitMeasure);
        return success(MdUnitMeasureConvert.INSTANCE.convertList(list));
    }

    @PostMapping("/create")
    @Operation(summary = "创建单位")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:create')")
    public CommonResult<Long> createMdUnitMeasure(@Valid @RequestBody MdUnitMeasureCreateReqVO createReqVO) {
        return success(mdUnitMeasureService.createMdUnitMeasure(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单位")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:update')")
    public CommonResult<Boolean> updateMdUnitMeasure(@Valid @RequestBody MdUnitMeasureUpdateReqVO updateReqVO) {
        mdUnitMeasureService.updateMdUnitMeasure(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:delete')")
    public CommonResult<Boolean> deleteMdUnitMeasure(@RequestParam("id") Long id) {
        mdUnitMeasureService.deleteMdUnitMeasure(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:query')")
    public CommonResult<MdUnitMeasureRespVO> getMdUnitMeasure(@RequestParam("id") Long id) {
        MdUnitMeasureDO mdUnitMeasure = mdUnitMeasureService.getMdUnitMeasure(id);
        return success(MdUnitMeasureConvert.INSTANCE.convert(mdUnitMeasure));
    }

    @GetMapping("/list")
    @Operation(summary = "获得单位列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:query')")
    public CommonResult<List<MdUnitMeasureRespVO>> getMdUnitMeasureList(@RequestParam("ids") Collection<Long> ids) {
        List<MdUnitMeasureDO> list = mdUnitMeasureService.getMdUnitMeasureList(ids);
        return success(MdUnitMeasureConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单位分页")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:query')")
    public CommonResult<PageResult<MdUnitMeasureRespVO>> getMdUnitMeasurePage(@Valid MdUnitMeasurePageReqVO pageVO) {
        PageResult<MdUnitMeasureDO> pageResult = mdUnitMeasureService.getMdUnitMeasurePage(pageVO);
        return success(MdUnitMeasureConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单位 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-measure:export')")
    @OperateLog(type = EXPORT)
    public void exportMdUnitMeasureExcel(@Valid MdUnitMeasureExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdUnitMeasureDO> list = mdUnitMeasureService.getMdUnitMeasureList(exportReqVO);
        // 导出 Excel
        List<MdUnitMeasureExcelVO> datas = MdUnitMeasureConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "单位.xls", "数据", MdUnitMeasureExcelVO.class, datas);
    }

}
