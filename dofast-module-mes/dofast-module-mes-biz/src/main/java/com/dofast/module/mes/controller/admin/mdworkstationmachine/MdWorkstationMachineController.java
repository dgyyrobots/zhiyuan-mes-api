package com.dofast.module.mes.controller.admin.mdworkstationmachine;

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

import com.dofast.module.mes.controller.admin.mdworkstationmachine.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationmachine.MdWorkstationMachineDO;
import com.dofast.module.mes.convert.mdworkstationmachine.MdWorkstationMachineConvert;
import com.dofast.module.mes.service.mdworkstationmachine.MdWorkstationMachineService;

@Tag(name = "管理后台 - 设备资源")
@RestController
@RequestMapping("/mes/md-workstation-machine")
@Validated
public class MdWorkstationMachineController {

    @Resource
    private MdWorkstationMachineService mdWorkstationMachineService;

    @PostMapping("/create")
    @Operation(summary = "创建设备资源")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:create')")
    public CommonResult<Long> createMdWorkstationMachine(@Valid @RequestBody MdWorkstationMachineCreateReqVO createReqVO) {
        return success(mdWorkstationMachineService.createMdWorkstationMachine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备资源")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:update')")
    public CommonResult<Boolean> updateMdWorkstationMachine(@Valid @RequestBody MdWorkstationMachineUpdateReqVO updateReqVO) {
        mdWorkstationMachineService.updateMdWorkstationMachine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备资源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:delete')")
    public CommonResult<Boolean> deleteMdWorkstationMachine(@RequestParam("id") Long id) {
        mdWorkstationMachineService.deleteMdWorkstationMachine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:query')")
    public CommonResult<MdWorkstationMachineRespVO> getMdWorkstationMachine(@RequestParam("id") Long id) {
        MdWorkstationMachineDO mdWorkstationMachine = mdWorkstationMachineService.getMdWorkstationMachine(id);
        return success(MdWorkstationMachineConvert.INSTANCE.convert(mdWorkstationMachine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得设备资源列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:query')")
    public CommonResult<List<MdWorkstationMachineRespVO>> getMdWorkstationMachineList(@RequestParam("ids") Collection<Long> ids) {
        List<MdWorkstationMachineDO> list = mdWorkstationMachineService.getMdWorkstationMachineList(ids);
        return success(MdWorkstationMachineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备资源分页")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:query')")
    public CommonResult<PageResult<MdWorkstationMachineRespVO>> getMdWorkstationMachinePage(@Valid MdWorkstationMachinePageReqVO pageVO) {
        PageResult<MdWorkstationMachineDO> pageResult = mdWorkstationMachineService.getMdWorkstationMachinePage(pageVO);
        return success(MdWorkstationMachineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备资源 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-machine:export')")
    @OperateLog(type = EXPORT)
    public void exportMdWorkstationMachineExcel(@Valid MdWorkstationMachineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdWorkstationMachineDO> list = mdWorkstationMachineService.getMdWorkstationMachineList(exportReqVO);
        // 导出 Excel
        List<MdWorkstationMachineExcelVO> datas = MdWorkstationMachineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "设备资源.xls", "数据", MdWorkstationMachineExcelVO.class, datas);
    }

}
