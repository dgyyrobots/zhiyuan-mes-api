package com.dofast.module.trade.controller.admin.mixin;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.report.api.PrintLog.PrintLogApi;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.trade.controller.admin.mixin.vo.*;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderListReqVO;
import com.dofast.module.trade.convert.mixin.MixinOrderConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.service.mixin.MixinOrderService;
import com.dofast.module.trade.service.order.TradeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import liquibase.pro.packaged.B;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 销售订单")
@RestController
@RequestMapping("/trade/mixin-order")
@Validated
public class MixinOrderController {

    @Resource
    private MixinOrderService mixinOrderService;

    @Resource
    private TradeOrderService tradeOrderService;
    @Resource
    private AdminUserApi adminUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建销售订单")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:create')")
    public CommonResult<Long> createMixinOrder(@Valid @RequestBody MixinOrderCreateReqVO createReqVO) {
        Integer salerId = createReqVO.getSaler();
        if (salerId != null) {
            AdminUserRespDTO user = adminUserApi.getUser(salerId.longValue());
            createReqVO.setSalerName(user.getNickname());
        }
        return success(mixinOrderService.createMixinOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新销售订单")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:update')")
    public CommonResult<Boolean> updateMixinOrder(@Valid @RequestBody MixinOrderUpdateReqVO updateReqVO) {
        mixinOrderService.updateMixinOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除销售订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:delete')")
    public CommonResult<Boolean> deleteMixinOrder(@RequestParam("id") Long id) {
        mixinOrderService.deleteMixinOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得销售订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:query')")
    public CommonResult<MixinOrderRespVO> getMixinOrder(@RequestParam("id") Long id) {
        MixinOrderDO mixinOrder = mixinOrderService.getMixinOrder(id);
        return success(MixinOrderConvert.INSTANCE.convert(mixinOrder));
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得销售订单详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:query')")
    public CommonResult<MixinOrderDetailRespVO> getMixinOrderDetail(@RequestParam("id") Long id) {
        MixinOrderDetailRespVO mixinOrderDetailRespVO = mixinOrderService.getMixinOrderDetail(id);
        return success(mixinOrderDetailRespVO);
    }

    @GetMapping("/list")
        @Operation(summary = "获得销售订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:query')")
    public CommonResult<List<MixinOrderRespVO>> getMixinOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<MixinOrderDO> list = mixinOrderService.getMixinOrderList(ids);
        return success(MixinOrderConvert.INSTANCE.convertList(list));
    }

    @Resource
    private PrintLogApi printLogApi;

    @GetMapping("/page")
    @Operation(summary = "获得销售订单分页")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:query')")
    public CommonResult<PageResult<MixinOrderRespVO>> getMixinOrderPage(@Valid MixinOrderPageReqVO pageVO, @RequestParam("sort") Integer sort) {
        List<Long> ids;
        List<TradeOrderDO> tradeOrders;
        if (pageVO.getTradeOrderNo() != null) {
            tradeOrders = tradeOrderService.getOrderList(new TradeOrderListReqVO().setOrderNo(pageVO.getTradeOrderNo()));
            if (tradeOrders.isEmpty()) {
                return success(new PageResult<>());
            }else{
                ids = new ArrayList<Long>(CollectionUtils.convertSet(tradeOrders, TradeOrderDO::getMixinOrderId));
                pageVO.setIds(ids);
            }

        }
        PageResult<MixinOrderDO> pageResult;
        if(sort == 1){
            pageResult = mixinOrderService.getMixinOrderPage(pageVO);
        }else {
            pageResult = mixinOrderService.getMixinOrderPageDesc(pageVO);
        }


        ids = new ArrayList<Long>(CollectionUtils.convertSet(pageResult.getList(), MixinOrderDO::getId));
        tradeOrders = tradeOrderService.getOrderList(new TradeOrderListReqVO().setMixinOrderIds(ids));
        PageResult<MixinOrderRespVO> mixinOrderRespVOPageResult = MixinOrderConvert.INSTANCE.convertPage(pageResult, tradeOrders);
        mixinOrderRespVOPageResult.getList().stream().forEach(v->{
            if (printLogApi.selectAllByPrintLogAndType(v.getSaleNo(),"销售订单详情-工单编码").size()>0) {
                v.setProductSalseCount(1);
            }else {
                v.setProductSalseCount(0);
            }
            if (printLogApi.selectAllByPrintLogAndType(v.getSaleNo(),"销售订单详情-订单号").size()>0){
                v.setOutboundLabelCount(1);
            }else {
                v.setOutboundLabelCount(0);
            }
        });

        /*if (pageVO.getProductSalseCount()){
            List<MixinOrderRespVO> collect = mixinOrderRespVOPageResult.getList().stream().map(m -> {
                if (printLogApi.selectAllByPrintLog(m.getSaleNo()).size() <= 0) {
                    return m;
                }
                return null;
            }).collect(Collectors.toList());
            collect.removeAll(Collections.singleton(null));
            mixinOrderRespVOPageResult.setList(collect);
        }*/
        return success(mixinOrderRespVOPageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出销售订单 Excel")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:export')")
    @OperateLog(type = EXPORT)
    public void exportMixinOrderExcel(@Valid MixinOrderExportReqVO exportReqVO,
                                      HttpServletResponse response) throws IOException {
        List<MixinOrderDO> list = mixinOrderService.getMixinOrderList(exportReqVO);
        // 导出 Excel
        List<MixinOrderExcelVO> datas = MixinOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "销售订单.xls", "数据", MixinOrderExcelVO.class, datas);
    }

    @GetMapping("/count-all")
    @Operation(summary = "获得销售订单总数")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:query')")
    public CommonResult<Integer> CountAll() {
        MixinOrderExportReqVO mixinOrderExportReqVO = new MixinOrderExportReqVO();
        List<MixinOrderDO> list = mixinOrderService.getMixinOrderList(mixinOrderExportReqVO);
        Integer result = list == null ? 0 : list.size();
        return success(result);
    }

    @GetMapping("/day-total-money")
    @Operation(summary = "获得日订单所有的金额数量")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:query')")
    public CommonResult<BigDecimal> CountShopDayOrderMoney() {
        BigDecimal result = mixinOrderService.getDayOrderMoney();
        return success(result);
    }

    @PutMapping("/update-state")
    @Operation(summary = "更新销售订单状态")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:update')")
    public CommonResult<Boolean> updateMixinOrderState(@RequestParam("id") Long id, @RequestParam("state") Integer state) {
        MixinOrderDO mixinOrderDO = mixinOrderService.getMixinOrder(id);
        MixinOrderUpdateReqVO mixinOrderUpdateReqVO = BeanUtil.toBean(mixinOrderDO, MixinOrderUpdateReqVO.class);
        if (state == 0){
            mixinOrderUpdateReqVO.setStatus("CREATED");
        }else {
            mixinOrderUpdateReqVO.setStatus("PASSED");
        }
        mixinOrderService.updateMixinOrder(mixinOrderUpdateReqVO);
        return success(true);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "更新打印状态")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order:update')")
    public CommonResult<Boolean> updateIsPrintById(@Valid @PathVariable(value = "id",required = true)Long id){
        Boolean b = mixinOrderService.updatebyIsPrint(id);
        return success(b);
    }
}
