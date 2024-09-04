package com.dofast.module.channel.controller.admin.salesorder;

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

import com.dofast.module.channel.controller.admin.salesorder.vo.*;
import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;
import com.dofast.module.channel.convert.salesorder.SalesOrderConvert;
import com.dofast.module.channel.service.salesorder.SalesOrderService;

@Tag(name = "管理后台 - 销售订单")
@RestController
@RequestMapping("/channel/sales-order")
@Validated
public class SalesOrderController {

    @Resource
    private SalesOrderService salesOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建销售订单")
    @PreAuthorize("@ss.hasPermission('channel:sales-order:create')")
    public CommonResult<Long> createSalesOrder(@Valid @RequestBody SalesOrderCreateReqVO createReqVO) {
        return success(salesOrderService.createSalesOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新销售订单")
    @PreAuthorize("@ss.hasPermission('channel:sales-order:update')")
    public CommonResult<Boolean> updateSalesOrder(@Valid @RequestBody SalesOrderUpdateReqVO updateReqVO) {
        salesOrderService.updateSalesOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除销售订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('channel:sales-order:delete')")
    public CommonResult<Boolean> deleteSalesOrder(@RequestParam("id") Long id) {
        salesOrderService.deleteSalesOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得销售订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:sales-order:query')")
    public CommonResult<SalesOrderRespVO> getSalesOrder(@RequestParam("id") Long id) {
        SalesOrderDO salesOrder = salesOrderService.getSalesOrder(id);
        return success(SalesOrderConvert.INSTANCE.convert(salesOrder));
    }

    @GetMapping("/list")
    @Operation(summary = "获得销售订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:sales-order:query')")
    public CommonResult<List<SalesOrderRespVO>> getSalesOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<SalesOrderDO> list = salesOrderService.getSalesOrderList(ids);
        return success(SalesOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得销售订单分页")
    @PreAuthorize("@ss.hasPermission('channel:sales-order:query')")
    public CommonResult<PageResult<SalesOrderRespVO>> getSalesOrderPage(@Valid SalesOrderPageReqVO pageVO) {
        PageResult<SalesOrderDO> pageResult = salesOrderService.getSalesOrderPage(pageVO);
        return success(SalesOrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出销售订单 Excel")
    @PreAuthorize("@ss.hasPermission('channel:sales-order:export')")
    @OperateLog(type = EXPORT)
    public void exportSalesOrderExcel(@Valid SalesOrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SalesOrderDO> list = salesOrderService.getSalesOrderList(exportReqVO);
        // 导出 Excel
        List<SalesOrderExcelVO> datas = SalesOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "销售订单.xls", "数据", SalesOrderExcelVO.class, datas);
    }

}
