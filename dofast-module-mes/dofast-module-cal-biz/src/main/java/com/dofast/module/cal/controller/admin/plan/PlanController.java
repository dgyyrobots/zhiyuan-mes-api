package com.dofast.module.cal.controller.admin.plan;

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

import com.dofast.module.cal.controller.admin.plan.vo.*;
import com.dofast.module.cal.dal.dataobject.plan.PlanDO;
import com.dofast.module.cal.convert.plan.PlanConvert;
import com.dofast.module.cal.service.plan.PlanService;

@Tag(name = "管理后台 - 排班计划")
@RestController
@RequestMapping("/cal/plan")
@Validated
public class PlanController {

    @Resource
    private PlanService planService;

    @PostMapping("/create")
    @Operation(summary = "创建排班计划")
    @PreAuthorize("@ss.hasPermission('cal:plan:create')")
    public CommonResult<Long> createPlan(@Valid @RequestBody PlanCreateReqVO createReqVO) {
        return success(planService.createPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排班计划")
    @PreAuthorize("@ss.hasPermission('cal:plan:update')")
    public CommonResult<Boolean> updatePlan(@Valid @RequestBody PlanUpdateReqVO updateReqVO) {
        planService.updatePlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排班计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cal:plan:delete')")
    public CommonResult<Boolean> deletePlan(@RequestParam("id") Long id) {
        planService.deletePlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排班计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cal:plan:query')")
    public CommonResult<PlanRespVO> getPlan(@RequestParam("id") Long id) {
        PlanDO plan = planService.getPlan(id);
        return success(PlanConvert.INSTANCE.convert(plan));
    }

    @GetMapping("/list")
    @Operation(summary = "获得排班计划列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cal:plan:query')")
    public CommonResult<List<PlanRespVO>> getPlanList(@RequestParam("ids") Collection<Long> ids) {
        List<PlanDO> list = planService.getPlanList(ids);
        return success(PlanConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排班计划分页")
    @PreAuthorize("@ss.hasPermission('cal:plan:query')")
    public CommonResult<PageResult<PlanRespVO>> getPlanPage(@Valid PlanPageReqVO pageVO) {
        PageResult<PlanDO> pageResult = planService.getPlanPage(pageVO);
        return success(PlanConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排班计划 Excel")
    @PreAuthorize("@ss.hasPermission('cal:plan:export')")
    @OperateLog(type = EXPORT)
    public void exportPlanExcel(@Valid PlanExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PlanDO> list = planService.getPlanList(exportReqVO);
        // 导出 Excel
        List<PlanExcelVO> datas = PlanConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "排班计划.xls", "数据", PlanExcelVO.class, datas);
    }

}
