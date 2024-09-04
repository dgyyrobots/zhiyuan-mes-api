package com.dofast.module.cmms.controller.admin.dvrepairline;

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

import com.dofast.module.cmms.controller.admin.dvrepairline.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvrepairline.DvRepairLineDO;
import com.dofast.module.cmms.convert.dvrepairline.DvRepairLineConvert;
import com.dofast.module.cmms.service.dvrepairline.DvRepairLineService;

@Tag(name = "设备管理 - 设备维修单行")
@RestController
@RequestMapping("/mes/cmms/dv-repair-line")
@Validated
public class DvRepairLineController {

    @Resource
    private DvRepairLineService dvRepairLineService;

    @PostMapping("/create")
    @Operation(summary = "创建设备维修单行")
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:create')")
    public CommonResult<Long> createDvRepairLine(@Valid @RequestBody DvRepairLineCreateReqVO createReqVO) {
        return success(dvRepairLineService.createDvRepairLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备维修单行")
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:update')")
    public CommonResult<Boolean> updateDvRepairLine(@Valid @RequestBody DvRepairLineUpdateReqVO updateReqVO) {
        dvRepairLineService.updateDvRepairLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备维修单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:delete')")
    public CommonResult<Boolean> deleteDvRepairLine(@RequestParam("id") Long id) {
        dvRepairLineService.deleteDvRepairLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备维修单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:query')")
    public CommonResult<DvRepairLineRespVO> getDvRepairLine(@RequestParam("id") Long id) {
        DvRepairLineDO dvRepairLine = dvRepairLineService.getDvRepairLine(id);
        return success(DvRepairLineConvert.INSTANCE.convert(dvRepairLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备维修单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:query')")
    public CommonResult<List<DvRepairLineRespVO>> getDvRepairLineList(@RequestParam("ids") Collection<Long> ids) {
        List<DvRepairLineDO> list = dvRepairLineService.getDvRepairLineList(ids);
        return success(DvRepairLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备维修单行分页")
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:query')")
    public CommonResult<PageResult<DvRepairLineRespVO>> getDvRepairLinePage(@Valid DvRepairLinePageReqVO pageVO) {
        PageResult<DvRepairLineDO> pageResult = dvRepairLineService.getDvRepairLinePage(pageVO);
        return success(DvRepairLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备维修单行 Excel")
    @PreAuthorize("@ss.hasPermission('cmms:dv-repair-line:export')")
    @OperateLog(type = EXPORT)
    public void exportDvRepairLineExcel(@Valid DvRepairLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DvRepairLineDO> list = dvRepairLineService.getDvRepairLineList(exportReqVO);
        // 导出 Excel
        List<DvRepairLineExcelVO> datas = DvRepairLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备维修单行.xls", "数据", DvRepairLineExcelVO.class, datas);
    }

}
