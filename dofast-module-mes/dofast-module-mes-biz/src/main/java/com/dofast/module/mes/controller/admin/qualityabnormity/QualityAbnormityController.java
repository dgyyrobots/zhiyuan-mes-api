package com.dofast.module.mes.controller.admin.qualityabnormity;

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

import com.dofast.module.mes.controller.admin.qualityabnormity.vo.*;
import com.dofast.module.mes.dal.dataobject.qualityabnormity.QualityAbnormityDO;
import com.dofast.module.mes.convert.qualityabnormity.QualityAbnormityConvert;
import com.dofast.module.mes.service.qualityabnormity.QualityAbnormityService;

@Tag(name = "管理后台 - 品质异常信息")
@RestController
@RequestMapping("/mes/quality-abnormity")
@Validated
public class QualityAbnormityController {

    @Resource
    private QualityAbnormityService qualityAbnormityService;

    @PostMapping("/create")
    @Operation(summary = "创建品质异常信息")
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:create')")
    public CommonResult<Long> createQualityAbnormity(@Valid @RequestBody QualityAbnormityCreateReqVO createReqVO) {
        return success(qualityAbnormityService.createQualityAbnormity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新品质异常信息")
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:update')")
    public CommonResult<Boolean> updateQualityAbnormity(@Valid @RequestBody QualityAbnormityUpdateReqVO updateReqVO) {
        qualityAbnormityService.updateQualityAbnormity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除品质异常信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:delete')")
    public CommonResult<Boolean> deleteQualityAbnormity(@RequestParam("id") Long id) {
        qualityAbnormityService.deleteQualityAbnormity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得品质异常信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:query')")
    public CommonResult<QualityAbnormityRespVO> getQualityAbnormity(@RequestParam("id") Long id) {
        QualityAbnormityDO qualityAbnormity = qualityAbnormityService.getQualityAbnormity(id);
        return success(QualityAbnormityConvert.INSTANCE.convert(qualityAbnormity));
    }

    @GetMapping("/list")
    @Operation(summary = "获得品质异常信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:query')")
    public CommonResult<List<QualityAbnormityRespVO>> getQualityAbnormityList(@RequestParam("ids") Collection<Long> ids) {
        List<QualityAbnormityDO> list = qualityAbnormityService.getQualityAbnormityList(ids);
        return success(QualityAbnormityConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得品质异常信息分页")
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:query')")
    public CommonResult<PageResult<QualityAbnormityRespVO>> getQualityAbnormityPage(@Valid QualityAbnormityPageReqVO pageVO) {
        PageResult<QualityAbnormityDO> pageResult = qualityAbnormityService.getQualityAbnormityPage(pageVO);
        return success(QualityAbnormityConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出品质异常信息 Excel")
    @PreAuthorize("@ss.hasPermission('mes:quality-abnormity:export')")
    @OperateLog(type = EXPORT)
    public void exportQualityAbnormityExcel(@Valid QualityAbnormityExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<QualityAbnormityDO> list = qualityAbnormityService.getQualityAbnormityList(exportReqVO);
        // 导出 Excel
        List<QualityAbnormityExcelVO> datas = QualityAbnormityConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "品质异常信息.xls", "数据", QualityAbnormityExcelVO.class, datas);
    }

}
