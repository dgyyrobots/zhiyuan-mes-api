package com.dofast.module.mes.controller.admin.mdunitconverse;

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

import com.dofast.module.mes.controller.admin.mdunitconverse.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;
import com.dofast.module.mes.convert.mdunitconverse.MdUnitConverseConvert;
import com.dofast.module.mes.service.mdunitconverse.MdUnitConverseService;

@Tag(name = "管理后台 - 单位换算")
@RestController
@RequestMapping("/mes/md-unit-converse")
@Validated
public class MdUnitConverseController {

    @Resource
    private MdUnitConverseService mdUnitConverseService;

    @PostMapping("/create")
    @Operation(summary = "创建单位换算")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:create')")
    public CommonResult<Long> createMdUnitConverse(@Valid @RequestBody MdUnitConverseCreateReqVO createReqVO) {
        return success(mdUnitConverseService.createMdUnitConverse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单位换算")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:update')")
    public CommonResult<Boolean> updateMdUnitConverse(@Valid @RequestBody MdUnitConverseUpdateReqVO updateReqVO) {
        mdUnitConverseService.updateMdUnitConverse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单位换算")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:delete')")
    public CommonResult<Boolean> deleteMdUnitConverse(@RequestParam("id") Long id) {
        mdUnitConverseService.deleteMdUnitConverse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单位换算")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:query')")
    public CommonResult<MdUnitConverseRespVO> getMdUnitConverse(@RequestParam("id") Long id) {
        MdUnitConverseDO mdUnitConverse = mdUnitConverseService.getMdUnitConverse(id);
        return success(MdUnitConverseConvert.INSTANCE.convert(mdUnitConverse));
    }

    @GetMapping("/list")
    @Operation(summary = "获得单位换算列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:query')")
    public CommonResult<List<MdUnitConverseRespVO>> getMdUnitConverseList(@RequestParam("ids") Collection<Long> ids) {
        List<MdUnitConverseDO> list = mdUnitConverseService.getMdUnitConverseList(ids);
        return success(MdUnitConverseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单位换算分页")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:query')")
    public CommonResult<PageResult<MdUnitConverseRespVO>> getMdUnitConversePage(@Valid MdUnitConversePageReqVO pageVO) {
        PageResult<MdUnitConverseDO> pageResult = mdUnitConverseService.getMdUnitConversePage(pageVO);
        return success(MdUnitConverseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单位换算 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-unit-converse:export')")
    @OperateLog(type = EXPORT)
    public void exportMdUnitConverseExcel(@Valid MdUnitConverseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdUnitConverseDO> list = mdUnitConverseService.getMdUnitConverseList(exportReqVO);
        // 导出 Excel
        List<MdUnitConverseExcelVO> datas = MdUnitConverseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "单位换算.xls", "数据", MdUnitConverseExcelVO.class, datas);
    }

}
