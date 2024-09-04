package com.dofast.module.channel.controller.admin.ordergoods;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.channel.controller.admin.ordergoods.vo.*;
import com.dofast.module.channel.convert.ordergoods.OrderGoodsConvert;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import com.dofast.module.channel.service.ordergoods.OrderGoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 子订单")
@RestController
@RequestMapping("/channel/order-goods")
@Validated
public class OrderGoodsController {

    @Autowired
    private OrderGoodsService orderGoodsService;

    @PostMapping("/create")
    @Operation(summary = "创建子订单")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:create')")
    public CommonResult<Integer> createOrderGoods(@Valid @RequestBody OrderGoodsCreateReqVO createReqVO) {
        return success(orderGoodsService.createOrderGoods(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新子订单")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:update')")
    public CommonResult<Boolean> updateOrderGoods(@Valid @RequestBody OrderGoodsUpdateReqVO updateReqVO) {
        orderGoodsService.updateOrderGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除子订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('channel:order-goods:delete')")
    public CommonResult<Boolean> deleteOrderGoods(@RequestParam("id") Integer id) {
        orderGoodsService.deleteOrderGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得子订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:query')")
    public CommonResult<OrderGoodsRespVO> getOrderGoods(@RequestParam("id") Integer id) {
        OrderGoodsDO orderGoods = orderGoodsService.getOrderGoods(id);
        return success(OrderGoodsConvert.INSTANCE.convert(orderGoods));
    }

    @GetMapping("/list")
    @Operation(summary = "获得子订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:query')")
    public CommonResult<List<OrderGoodsRespVO>> getOrderGoodsList(@RequestParam("ids") Collection<Integer> ids) {
        List<OrderGoodsDO> list = orderGoodsService.getOrderGoodsList(ids);
        return success(OrderGoodsConvert.INSTANCE.convertList(list));
    }

    @PostMapping("/page")
    @Operation(summary = "获得子订单分页")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:query')")
    public CommonResult<PageResult<OrderGoodsRespVO>> getOrderGoodsPage(@Valid @RequestBody OrderGoodsPageReqVO pageVO) {
        PageResult<OrderGoodsDO> pageResult = orderGoodsService.getOrderGoodsPage(pageVO);
        return success(OrderGoodsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出子订单 Excel")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderGoodsExcel(@Valid OrderGoodsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderGoodsDO> list = orderGoodsService.getOrderGoodsList(exportReqVO);
        // 导出 Excel
        List<OrderGoodsExcelVO> datas = OrderGoodsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "子订单.xls", "数据", OrderGoodsExcelVO.class, datas);
    }

    @GetMapping("/get-list")
    @Operation(summary = "子订单列表")
    @Parameter(name = "ref_oid", description = "编号", required = true, example = "1094459426000494380")
    @PreAuthorize("@ss.hasPermission('channel:order-goods:query')")
    public CommonResult<List<OrderGoodsRespVO>> getOrderGoodsListAccordingRefOid(@RequestParam("ref_oid") String refOid) {
        List<OrderGoodsDO> list = orderGoodsService.getPageOrderGoods(refOid);
        return success(OrderGoodsConvert.INSTANCE.convertList(list));
    }

}
