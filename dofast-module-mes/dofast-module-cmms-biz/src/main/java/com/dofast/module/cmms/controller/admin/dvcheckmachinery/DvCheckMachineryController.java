package com.dofast.module.cmms.controller.admin.dvcheckmachinery;

import com.dofast.module.cmms.enums.ErrorCodeConstants;
import com.dofast.module.mes.constant.Constant;
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

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvcheckmachinery.DvCheckMachineryDO;
import com.dofast.module.cmms.convert.dvcheckmachinery.DvCheckMachineryConvert;
import com.dofast.module.cmms.service.dvcheckmachinery.DvCheckMachineryService;

@Tag(name = "设备管理 - 点检设备")
@RestController
@RequestMapping("/mes/cmms/dv-check-machinery")
@Validated
public class DvCheckMachineryController {

    @Resource
    private DvCheckMachineryService dvCheckMachineryService;

    @PostMapping("/create")
    @Operation(summary = "创建点检设备")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:create')")
    public CommonResult<Long> createDvCheckMachinery(@Valid @RequestBody DvCheckMachineryCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(dvCheckMachineryService.checkMachineryUnique(createReqVO))){
            return error(ErrorCodeConstants.DV_CHECK_MACHINERY_HAS_EXISTS);
        }
        return success(dvCheckMachineryService.createDvCheckMachinery(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新点检设备")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:update')")
    public CommonResult<Boolean> updateDvCheckMachinery(@Valid @RequestBody DvCheckMachineryUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(dvCheckMachineryService.checkMachineryUnique(updateReqVO))){
            return error(ErrorCodeConstants.DV_CHECK_MACHINERY_HAS_EXISTS);
        }
        dvCheckMachineryService.updateDvCheckMachinery(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除点检设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:delete')")
    public CommonResult<Boolean> deleteDvCheckMachinery(@RequestParam("id") Long id) {
        dvCheckMachineryService.deleteDvCheckMachinery(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得点检设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:query')")
    public CommonResult<DvCheckMachineryRespVO> getDvCheckMachinery(@RequestParam("id") Long id) {
        DvCheckMachineryDO dvCheckMachinery = dvCheckMachineryService.getDvCheckMachinery(id);
        return success(DvCheckMachineryConvert.INSTANCE.convert(dvCheckMachinery));
    }

    @GetMapping("/list")
    @Operation(summary = "获得点检设备列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:query')")
    public CommonResult<List<DvCheckMachineryRespVO>> getDvCheckMachineryList(@RequestParam("ids") Collection<Long> ids) {
        List<DvCheckMachineryDO> list = dvCheckMachineryService.getDvCheckMachineryList(ids);
        return success(DvCheckMachineryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得点检设备分页")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:query')")
    public CommonResult<PageResult<DvCheckMachineryRespVO>> getDvCheckMachineryPage(@Valid DvCheckMachineryPageReqVO pageVO) {
        PageResult<DvCheckMachineryDO> pageResult = dvCheckMachineryService.getDvCheckMachineryPage(pageVO);
        return success(DvCheckMachineryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出点检设备 Excel")
    @PreAuthorize("@ss.hasPermission('cmms:dv-check-machinery:export')")
    @OperateLog(type = EXPORT)
    public void exportDvCheckMachineryExcel(@Valid DvCheckMachineryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DvCheckMachineryDO> list = dvCheckMachineryService.getDvCheckMachineryList(exportReqVO);
        // 导出 Excel
        List<DvCheckMachineryExcelVO> datas = DvCheckMachineryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "点检设备.xls", "数据", DvCheckMachineryExcelVO.class, datas);
    }

}
