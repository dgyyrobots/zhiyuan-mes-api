package com.dofast.module.trade.controller.app.order;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.convert.order.OrderCollectionConvert;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionDO;
import com.dofast.module.trade.service.order.OrderCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 采集任务")
@RestController
@RequestMapping("/trade/order-collection")
@Validated
public class AppOrderCollectionController {

    @Resource
    private OrderCollectionService orderCollectionService;

    @PostMapping("/create")
    @Operation(summary = "创建采集任务")
    @PreAuthenticated
    public CommonResult<Integer> createOrderCollection(@RequestBody OrderCollectionCreateReqVO createReqVO) {
        return success(orderCollectionService.createOrderCollection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采集任务")
    @PreAuthenticated
    public CommonResult<Boolean> updateOrderCollection(@RequestBody OrderCollectionUpdateReqVO updateReqVO) {
        orderCollectionService.updateOrderCollection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采集任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthenticated
    public CommonResult<Boolean> deleteOrderCollection(@RequestParam("id") Integer id) {
        orderCollectionService.deleteOrderCollection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采集任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthenticated
    public CommonResult<OrderCollectionRespVO> getOrderCollection(@RequestParam("id") Integer id) {
        OrderCollectionDO orderCollection = orderCollectionService.getOrderCollection(id);
        return success(OrderCollectionConvert.INSTANCE.convert(orderCollection));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采集任务列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthenticated
    public CommonResult<List<OrderCollectionRespVO>> getOrderCollectionList(@RequestParam("ids") Collection<Integer> ids) {
        List<OrderCollectionDO> list = orderCollectionService.getOrderCollectionList(ids);
        return success(OrderCollectionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采集任务分页")
    @PreAuthenticated
    public CommonResult<PageResult<OrderCollectionRespVO>> getOrderCollectionPage(OrderCollectionPageReqVO pageVO) {
        PageResult<OrderCollectionDO> pageResult = orderCollectionService.getOrderCollectionPage(pageVO);
        return success(OrderCollectionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采集任务 Excel")
    @PreAuthenticated
    @OperateLog(type = EXPORT)
    public void exportOrderCollectionExcel(OrderCollectionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderCollectionDO> list = orderCollectionService.getOrderCollectionList(exportReqVO);
        // 导出 Excel
        List<OrderCollectionExcelVO> datas = OrderCollectionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采集任务.xls", "数据", OrderCollectionExcelVO.class, datas);
    }

}
