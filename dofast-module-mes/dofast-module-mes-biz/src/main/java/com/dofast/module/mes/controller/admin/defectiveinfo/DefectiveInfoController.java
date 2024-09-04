package com.dofast.module.mes.controller.admin.defectiveinfo;

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

import com.dofast.module.mes.controller.admin.defectiveinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.defectiveinfo.DefectiveInfoDO;
import com.dofast.module.mes.convert.defectiveinfo.DefectiveInfoConvert;
import com.dofast.module.mes.service.defectiveinfo.DefectiveInfoService;

@Tag(name = "管理后台 - 不良品信息管理")
@RestController
@RequestMapping("/mes/defective-info")
@Validated
public class DefectiveInfoController {

    @Resource
    private DefectiveInfoService defectiveInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建不良品信息管理")
    @PreAuthorize("@ss.hasPermission('mes:defective-info:create')")
    public CommonResult<Long> createDefectiveInfo(@Valid @RequestBody DefectiveInfoCreateReqVO createReqVO) {
        return success(defectiveInfoService.createDefectiveInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新不良品信息管理")
    @PreAuthorize("@ss.hasPermission('mes:defective-info:update')")
    public CommonResult<Boolean> updateDefectiveInfo(@Valid @RequestBody DefectiveInfoUpdateReqVO updateReqVO) {
        defectiveInfoService.updateDefectiveInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除不良品信息管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:defective-info:delete')")
    public CommonResult<Boolean> deleteDefectiveInfo(@RequestParam("id") Long id) {
        defectiveInfoService.deleteDefectiveInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得不良品信息管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:defective-info:query')")
    public CommonResult<DefectiveInfoRespVO> getDefectiveInfo(@RequestParam("id") Long id) {
        DefectiveInfoDO defectiveInfo = defectiveInfoService.getDefectiveInfo(id);
        return success(DefectiveInfoConvert.INSTANCE.convert(defectiveInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得不良品信息管理列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:defective-info:query')")
    public CommonResult<List<DefectiveInfoRespVO>> getDefectiveInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<DefectiveInfoDO> list = defectiveInfoService.getDefectiveInfoList(ids);
        return success(DefectiveInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得不良品信息管理分页")
    @PreAuthorize("@ss.hasPermission('mes:defective-info:query')")
    public CommonResult<PageResult<DefectiveInfoRespVO>> getDefectiveInfoPage(@Valid DefectiveInfoPageReqVO pageVO) {
        PageResult<DefectiveInfoDO> pageResult = defectiveInfoService.getDefectiveInfoPage(pageVO);
        return success(DefectiveInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出不良品信息管理 Excel")
    @PreAuthorize("@ss.hasPermission('mes:defective-info:export')")
    @OperateLog(type = EXPORT)
    public void exportDefectiveInfoExcel(@Valid DefectiveInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DefectiveInfoDO> list = defectiveInfoService.getDefectiveInfoList(exportReqVO);
        // 导出 Excel
        List<DefectiveInfoExcelVO> datas = DefectiveInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "不良品信息管理.xls", "数据", DefectiveInfoExcelVO.class, datas);
    }

}
