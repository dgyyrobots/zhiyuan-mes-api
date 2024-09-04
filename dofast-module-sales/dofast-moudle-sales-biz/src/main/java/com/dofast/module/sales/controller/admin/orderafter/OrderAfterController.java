package com.dofast.module.sales.controller.admin.orderafter;

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

import com.dofast.module.sales.controller.admin.orderafter.vo.*;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import com.dofast.module.sales.convert.orderafter.OrderAfterConvert;
import com.dofast.module.sales.service.orderafter.OrderAfterService;

@Tag(name = "管理后台 - 售后流程表单")
@RestController
@RequestMapping("/sales/order-after")
@Validated
public class OrderAfterController {

    @Resource
    private OrderAfterService orderAfterService;

    @PostMapping("/create")
    @Operation(summary = "创建售后流程表单")
    @PreAuthorize("@ss.hasPermission('sales:order-after:create')")
    public CommonResult<Long> createOrderAfter(@Valid @RequestBody OrderAfterCreateReqVO createReqVO) {
        return success(orderAfterService.createOrderAfter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新售后流程表单")
    @PreAuthorize("@ss.hasPermission('sales:order-after:update')")
    public CommonResult<Boolean> updateOrderAfter(@Valid @RequestBody OrderAfterUpdateReqVO updateReqVO) {
        orderAfterService.updateOrderAfter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除售后流程表单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('sales:order-after:delete')")
    public CommonResult<Boolean> deleteOrderAfter(@RequestParam("id") Long id) {
        orderAfterService.deleteOrderAfter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得售后流程表单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('sales:order-after:query')")
    public CommonResult<OrderAfterRespVO> getOrderAfter(@RequestParam("id") Long id) {
        OrderAfterDO orderAfter = orderAfterService.getOrderAfter(id);
        return success(OrderAfterConvert.INSTANCE.convert(orderAfter));
    }

    @GetMapping("/list")
    @Operation(summary = "获得售后流程表单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('sales:order-after:query')")
    public CommonResult<List<OrderAfterRespVO>> getOrderAfterList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderAfterDO> list = orderAfterService.getOrderAfterList(ids);
        return success(OrderAfterConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得售后流程表单分页")
    @PreAuthorize("@ss.hasPermission('sales:order-after:query')")
    public CommonResult<PageResult<OrderAfterRespVO>> getOrderAfterPage(@Valid OrderAfterPageReqVO pageVO) {
        PageResult<OrderAfterDO> pageResult = orderAfterService.getOrderAfterPage(pageVO);
        return success(OrderAfterConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出售后流程表单 Excel")
    @PreAuthorize("@ss.hasPermission('sales:order-after:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderAfterExcel(@Valid OrderAfterExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderAfterDO> list = orderAfterService.getOrderAfterList(exportReqVO);
        // 导出 Excel
        List<OrderAfterExcelVO> datas = OrderAfterConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "售后流程表单.xls", "数据", OrderAfterExcelVO.class, datas);
    }

}
