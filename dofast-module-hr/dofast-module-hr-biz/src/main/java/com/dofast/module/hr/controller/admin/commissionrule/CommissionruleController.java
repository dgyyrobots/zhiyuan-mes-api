package com.dofast.module.hr.controller.admin.commissionrule;

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

import com.dofast.module.hr.controller.admin.commissionrule.vo.*;
import com.dofast.module.hr.dal.dataobject.commissionrule.CommissionruleDO;
import com.dofast.module.hr.convert.commissionrule.CommissionruleConvert;
import com.dofast.module.hr.service.commissionrule.CommissionruleService;

@Tag(name = "管理后台 - 提成规则")
@RestController
@RequestMapping("/hr/commissionrule")
@Validated
public class CommissionruleController {

    @Resource
    private CommissionruleService commissionruleService;

    @PostMapping("/create")
    @Operation(summary = "创建提成规则")
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:create')")
    public CommonResult<Integer> createCommissionrule(@Valid @RequestBody CommissionruleCreateReqVO createReqVO) {
        return success(commissionruleService.createCommissionrule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新提成规则")
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:update')")
    public CommonResult<Boolean> updateCommissionrule(@Valid @RequestBody CommissionruleUpdateReqVO updateReqVO) {
        commissionruleService.updateCommissionrule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除提成规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:delete')")
    public CommonResult<Boolean> deleteCommissionrule(@RequestParam("id") Integer id) {
        commissionruleService.deleteCommissionrule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得提成规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:query')")
    public CommonResult<CommissionruleRespVO> getCommissionrule(@RequestParam("id") Integer id) {
        CommissionruleDO commissionrule = commissionruleService.getCommissionrule(id);
        return success(CommissionruleConvert.INSTANCE.convert(commissionrule));
    }

    @GetMapping("/list")
    @Operation(summary = "获得提成规则列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:query')")
    public CommonResult<List<CommissionruleRespVO>> getCommissionruleList(@RequestParam("ids") Collection<Integer> ids) {
        List<CommissionruleDO> list = commissionruleService.getCommissionruleList(ids);
        return success(CommissionruleConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得提成规则分页")
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:query')")
    public CommonResult<PageResult<CommissionruleRespVO>> getCommissionrulePage(@Valid CommissionrulePageReqVO pageVO) {
        PageResult<CommissionruleDO> pageResult = commissionruleService.getCommissionrulePage(pageVO);
        return success(CommissionruleConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出提成规则 Excel")
    @PreAuthorize("@ss.hasPermission('hr:commissionrule:export')")
    @OperateLog(type = EXPORT)
    public void exportCommissionruleExcel(@Valid CommissionruleExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CommissionruleDO> list = commissionruleService.getCommissionruleList(exportReqVO);
        // 导出 Excel
        List<CommissionruleExcelVO> datas = CommissionruleConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "提成规则.xls", "数据", CommissionruleExcelVO.class, datas);
    }

}
