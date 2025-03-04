package com.dofast.module.wms.controller.admin.electroformline;

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

import com.dofast.module.wms.controller.admin.electroformline.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformline.ElectroformLineDO;
import com.dofast.module.wms.convert.electroformline.ElectroformLineConvert;
import com.dofast.module.wms.service.electroformline.ElectroformLineService;

@Tag(name = "管理后台 - 制版房领料单身体")
@RestController
@RequestMapping("/wms/electroform-line")
@Validated
public class ElectroformLineController {

    @Resource
    private ElectroformLineService electroformLineService;

    @PostMapping("/create")
    @Operation(summary = "创建制版房领料单身体")
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:create')")
    public CommonResult<Long> createElectroformLine(@Valid @RequestBody ElectroformLineCreateReqVO createReqVO) {
        return success(electroformLineService.createElectroformLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新制版房领料单身体")
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:update')")
    public CommonResult<Boolean> updateElectroformLine(@Valid @RequestBody ElectroformLineUpdateReqVO updateReqVO) {
        electroformLineService.updateElectroformLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除制版房领料单身体")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:delete')")
    public CommonResult<Boolean> deleteElectroformLine(@RequestParam("id") Long id) {
        electroformLineService.deleteElectroformLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得制版房领料单身体")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:query')")
    public CommonResult<ElectroformLineRespVO> getElectroformLine(@RequestParam("id") Long id) {
        ElectroformLineDO electroformLine = electroformLineService.getElectroformLine(id);
        return success(ElectroformLineConvert.INSTANCE.convert(electroformLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得制版房领料单身体列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:query')")
    public CommonResult<List<ElectroformLineRespVO>> getElectroformLineList(@RequestParam("ids") Collection<Long> ids) {
        List<ElectroformLineDO> list = electroformLineService.getElectroformLineList(ids);
        return success(ElectroformLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得制版房领料单身体分页")
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:query')")
    public CommonResult<PageResult<ElectroformLineRespVO>> getElectroformLinePage(@Valid ElectroformLinePageReqVO pageVO) {
        PageResult<ElectroformLineDO> pageResult = electroformLineService.getElectroformLinePage(pageVO);
        return success(ElectroformLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出制版房领料单身体 Excel")
    @PreAuthorize("@ss.hasPermission('wms:electroform-line:export')")
    @OperateLog(type = EXPORT)
    public void exportElectroformLineExcel(@Valid ElectroformLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ElectroformLineDO> list = electroformLineService.getElectroformLineList(exportReqVO);
        // 导出 Excel
        List<ElectroformLineExcelVO> datas = ElectroformLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "制版房领料单身体.xls", "数据", ElectroformLineExcelVO.class, datas);
    }

}
