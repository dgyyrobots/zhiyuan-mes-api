package com.dofast.module.wms.controller.admin.electroformheader;

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

import com.dofast.module.wms.controller.admin.electroformheader.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformheader.ElectroformHeaderDO;
import com.dofast.module.wms.convert.electroformheader.ElectroformHeaderConvert;
import com.dofast.module.wms.service.electroformheader.ElectroformHeaderService;

@Tag(name = "管理后台 - 制版房领料单头")
@RestController
@RequestMapping("/wms/electroform-header")
@Validated
public class ElectroformHeaderController {

    @Resource
    private ElectroformHeaderService electroformHeaderService;

    @PostMapping("/create")
    @Operation(summary = "创建制版房领料单头")
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:create')")
    public CommonResult<Long> createElectroformHeader(@Valid @RequestBody ElectroformHeaderCreateReqVO createReqVO) {
        return success(electroformHeaderService.createElectroformHeader(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新制版房领料单头")
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:update')")
    public CommonResult<Boolean> updateElectroformHeader(@Valid @RequestBody ElectroformHeaderUpdateReqVO updateReqVO) {
        electroformHeaderService.updateElectroformHeader(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除制版房领料单头")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:delete')")
    public CommonResult<Boolean> deleteElectroformHeader(@RequestParam("id") Long id) {
        electroformHeaderService.deleteElectroformHeader(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得制版房领料单头")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:query')")
    public CommonResult<ElectroformHeaderRespVO> getElectroformHeader(@RequestParam("id") Long id) {
        ElectroformHeaderDO electroformHeader = electroformHeaderService.getElectroformHeader(id);
        return success(ElectroformHeaderConvert.INSTANCE.convert(electroformHeader));
    }

    @GetMapping("/list")
    @Operation(summary = "获得制版房领料单头列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:query')")
    public CommonResult<List<ElectroformHeaderRespVO>> getElectroformHeaderList(@RequestParam("ids") Collection<Long> ids) {
        List<ElectroformHeaderDO> list = electroformHeaderService.getElectroformHeaderList(ids);
        return success(ElectroformHeaderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得制版房领料单头分页")
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:query')")
    public CommonResult<PageResult<ElectroformHeaderRespVO>> getElectroformHeaderPage(@Valid ElectroformHeaderPageReqVO pageVO) {
        PageResult<ElectroformHeaderDO> pageResult = electroformHeaderService.getElectroformHeaderPage(pageVO);
        return success(ElectroformHeaderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出制版房领料单头 Excel")
    @PreAuthorize("@ss.hasPermission('wms:electroform-header:export')")
    @OperateLog(type = EXPORT)
    public void exportElectroformHeaderExcel(@Valid ElectroformHeaderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ElectroformHeaderDO> list = electroformHeaderService.getElectroformHeaderList(exportReqVO);
        // 导出 Excel
        List<ElectroformHeaderExcelVO> datas = ElectroformHeaderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "制版房领料单头.xls", "数据", ElectroformHeaderExcelVO.class, datas);
    }

}
