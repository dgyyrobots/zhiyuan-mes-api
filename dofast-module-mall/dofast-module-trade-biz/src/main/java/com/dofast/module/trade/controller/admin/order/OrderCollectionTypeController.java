package com.dofast.module.trade.controller.admin.order;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.convert.order.OrderCollectionTypeConvert;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import com.dofast.module.trade.service.order.OrderCollectionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 采集任务类型")
@RestController
@RequestMapping("/trade/order-collection-type")
@Validated
public class OrderCollectionTypeController {

    @Resource
    private OrderCollectionTypeService orderCollectionTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建采集任务类型")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:create')")
    public CommonResult<Long> createOrderCollectionType(@Valid @RequestBody OrderCollectionTypeCreateReqVO createReqVO) {
        return success(orderCollectionTypeService.createOrderCollectionType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采集任务类型")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:update')")
    public CommonResult<Boolean> updateOrderCollectionType(@Valid @RequestBody OrderCollectionTypeUpdateReqVO updateReqVO) {
        orderCollectionTypeService.updateOrderCollectionType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采集任务类型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:delete')")
    public CommonResult<Boolean> deleteOrderCollectionType(@RequestParam("id") Long id) {
        orderCollectionTypeService.deleteOrderCollectionType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采集任务类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:query')")
    public CommonResult<OrderCollectionTypeRespVO> getOrderCollectionType(@RequestParam("id") Long id) {
        OrderCollectionTypeDO orderCollectionType = orderCollectionTypeService.getOrderCollectionType(id);
        return success(OrderCollectionTypeConvert.INSTANCE.convert(orderCollectionType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采集任务类型列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:query')")
    public CommonResult<List<OrderCollectionTypeRespVO>> getOrderCollectionTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderCollectionTypeDO> list = orderCollectionTypeService.getOrderCollectionTypeList(ids);
        return success(OrderCollectionTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采集任务类型分页")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:query')")
    public CommonResult<PageResult<OrderCollectionTypeRespVO>> getOrderCollectionTypePage(@Valid OrderCollectionTypePageReqVO pageVO) {
        PageResult<OrderCollectionTypeDO> pageResult = orderCollectionTypeService.getOrderCollectionTypePage(pageVO);
        return success(OrderCollectionTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采集任务类型 Excel")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderCollectionTypeExcel(@Valid OrderCollectionTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderCollectionTypeDO> list = orderCollectionTypeService.getOrderCollectionTypeList(exportReqVO);
        // 导出 Excel
        List<OrderCollectionTypeExcelVO> datas = OrderCollectionTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采集任务类型.xls", "数据", OrderCollectionTypeExcelVO.class, datas);
    }

    @GetMapping("/list-simple")
    @Operation(summary = "简单的订单任务类型列表")
    @PreAuthorize("@ss.hasPermission('trade:order-collection-type:query')")
    public CommonResult<List<OrderCollectionTypeSimpleRespVO>> getOrderCollectionTypeSimpleList(@Valid OrderCollectionTypeExportReqVO exportReqVO) {
        List<OrderCollectionTypeDO> list = orderCollectionTypeService.getOrderCollectionTypeList(exportReqVO);
        // 导出 Excel
        List<OrderCollectionTypeSimpleRespVO> datas = OrderCollectionTypeConvert.INSTANCE.convertList03(list);
        return success(datas);
    }
}
