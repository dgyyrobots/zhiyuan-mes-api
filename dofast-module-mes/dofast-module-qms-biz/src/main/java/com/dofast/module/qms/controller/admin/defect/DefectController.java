package com.dofast.module.qms.controller.admin.defect;

import com.dofast.module.mes.api.autocode.AutoCodeApi;
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
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.qms.controller.admin.defect.vo.*;
import com.dofast.module.qms.dal.dataobject.defect.DefectDO;
import com.dofast.module.qms.convert.defect.DefectConvert;
import com.dofast.module.qms.service.defect.DefectService;

@Tag(name = "质量管理 - 常见缺陷")
@RestController
@RequestMapping("/mes/qms/defect")
@Validated
public class DefectController {

    @Resource
    private DefectService defectService;
    @Resource
    private AutoCodeApi autoCodeApi;

    @PostMapping("/create")
    @Operation(summary = "创建常见缺陷")
    @PreAuthorize("@ss.hasPermission('qms:defect:create')")
    public CommonResult<Long> createDefect(@Valid @RequestBody DefectCreateReqVO createReqVO) {
        createReqVO.setDefectCode(autoCodeApi.genSerialCode(Constant.DEFECT_CODE,null));
        return success(defectService.createDefect(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新常见缺陷")
    @PreAuthorize("@ss.hasPermission('qms:defect:update')")
    public CommonResult<Boolean> updateDefect(@Valid @RequestBody DefectUpdateReqVO updateReqVO) {
        defectService.updateDefect(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除常见缺陷")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:defect:delete')")
    public CommonResult<Boolean> deleteDefect(@RequestParam("id") Long id) {
        defectService.deleteDefect(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得常见缺陷")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:defect:query')")
    public CommonResult<DefectRespVO> getDefect(@RequestParam("id") Long id) {
        DefectDO defect = defectService.getDefect(id);
        return success(DefectConvert.INSTANCE.convert(defect));
    }

    @GetMapping("/list")
    @Operation(summary = "获得常见缺陷列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:defect:query')")
    public CommonResult<List<DefectRespVO>> getDefectList(@RequestParam("ids") Collection<Long> ids) {
        List<DefectDO> list = defectService.getDefectList(ids);
        return success(DefectConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得常见缺陷分页")
    @PreAuthorize("@ss.hasPermission('qms:defect:query')")
    public CommonResult<PageResult<DefectRespVO>> getDefectPage(@Valid DefectPageReqVO pageVO) {
        PageResult<DefectDO> pageResult = defectService.getDefectPage(pageVO);
        return success(DefectConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出常见缺陷 Excel")
    @PreAuthorize("@ss.hasPermission('qms:defect:export')")
    @OperateLog(type = EXPORT)
    public void exportDefectExcel(@Valid DefectExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DefectDO> list = defectService.getDefectList(exportReqVO);
        // 导出 Excel
        List<DefectExcelVO> datas = DefectConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "常见缺陷.xls", "数据", DefectExcelVO.class, datas);
    }

}
