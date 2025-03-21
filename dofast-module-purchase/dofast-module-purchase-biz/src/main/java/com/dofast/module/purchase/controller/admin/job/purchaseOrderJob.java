package com.dofast.module.purchase.controller.admin.job;

import com.dofast.framework.common.core.LoginUser;
import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.framework.security.core.util.SecurityFrameworkUtils;
import com.dofast.module.purchase.controller.admin.goods.vo.GoodsExportReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderBaseVO;
import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.module.purchase.dal.mysql.goods.GoodsMapper;
import com.dofast.module.purchase.dal.mysql.order.PurchaseOrderMapper;
import com.dofast.module.purchase.service.order.OrderOracleService;
import com.dofast.module.purchase.service.order.OrderService;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorDO;
import com.dofast.module.wms.service.rtvendor.RtVendorService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Component
public class purchaseOrderJob implements JobHandler {

    @Resource
    private OrderOracleService orderOracleService;

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private RtVendorService rtVendorService;

    @Override
    public String execute(String param) throws Exception {
        // 开始追加采购入库信息
        List<Map<String, Object>> purchaseOrderList = orderOracleService.initPurchaseOrder();
        if (purchaseOrderList.isEmpty()) {
            return "未获取到已审核的入库单信息";
        }
        // 采购单信息
        List<OrderDO> addOrder = new ArrayList<>();
        List<OrderDO> editOrder = new ArrayList<>();
        // 采购单详情
        List<GoodsDO> addGoods = new ArrayList<>();
        List<GoodsDO> editGoods = new ArrayList<>();
        // 校验当前仓偶入库但内是否存在
        for (int i = 0; i < purchaseOrderList.size(); i++) {
            Map<String, Object> purchaseOrder = purchaseOrderList.get(i);
            // 校验当前的入库单号是否存在
            String orderNo = Optional.ofNullable((String) purchaseOrder.get("PO_NO")).orElse(""); // 采购单号
            String supplierCode = Optional.ofNullable((String) purchaseOrder.get("SUPPLIER_CODE")).orElse(""); // 供应商编码
            // 根据当前的供应商编码获取详情
            RtVendorDO vendor = rtVendorService.getRtVendor(supplierCode);
            OrderDO orderDO = purchaseOrderMapper.selectOne(OrderDO::getPoNo, orderNo);
            if (orderDO == null) {
                // 追加入库单信息
                OrderDO order = new OrderDO();
                order.setPoNo(orderNo);
                order.setProcessType(0); // 采购单状态：已审核
                order.setSupplierCode(supplierCode);
                if(vendor!= null){
                    order.setSupplierId(vendor.getId().intValue()); // 供应商Id
                }
                addOrder.add(order);
                // 追加入库单详情信息
            }else{
                orderDO.setProcessType(0); // 采购单状态：已审核
                orderDO.setSupplierCode(supplierCode);
                if(vendor!= null){
                    orderDO.setSupplierId(vendor.getId().intValue()); // 供应商Id
                }
                editOrder.add(orderDO);
            }
        }
        if(!addOrder.isEmpty()){
            purchaseOrderMapper.insertBatch(addOrder);
        }
        if(!editOrder.isEmpty()){
            purchaseOrderMapper.updateBatch(editOrder);
        }

        List<Map<String, Object>> purchasGoodList = orderOracleService.initPurchaseGoods();
        if (purchasGoodList.isEmpty()) {
            return "未获取到采购单详情信息";
        }
        for (int i = 0; i < purchasGoodList.size(); i++) {
            Map<String, Object> purchaseGoods = purchasGoodList.get(i);
            // 校验当前是否存在采购单信息
            // 校验当前的入库单号是否存在
            String orderNo = Optional.ofNullable((String) purchaseGoods.get("PO_NO")).orElse(""); // 采购单号
            OrderDO orderDO = purchaseOrderMapper.selectOne(OrderDO::getPoNo, orderNo);
            if (orderDO != null) {
                // 修改审批的入库单信息
                // 获取当前的入库单详情
                Integer orderId = orderDO.getId(); // 采购单Id
                String itemCode = Optional.ofNullable((String) purchaseGoods.get("GOODS_NUMBER")).orElse("");
                String itemName = Optional.ofNullable((String) purchaseGoods.get("GOODS_NAME")).orElse("");
                String itemSpec = Optional.ofNullable((String) purchaseGoods.get("GOODS_SPECS")).orElse("");
                String itemUnit = Optional.ofNullable((String) purchaseGoods.get("COMPANY")).orElse("");
                String supplierCode = Optional.ofNullable((String) purchaseGoods.get("SUPPLIER_CODE")).orElse("");
                // BigDecimal consequence = new BigDecimal(String.valueOf(purchaseGoods.get("CONSEQUENCE")));
                BigDecimal consequence = new BigDecimal(purchaseGoods.get("CONSEQUENCE") != null ? String.valueOf(purchaseGoods.get("CONSEQUENCE")) : "-1");

                BigDecimal itemNum = purchaseGoods.get("QUANTITY").equals(null)? new BigDecimal(0) : (BigDecimal) purchaseGoods.get("QUANTITY");
                String conStr = consequence.equals(-1)? "" : String.valueOf(consequence);
                GoodsExportReqVO exr = new   GoodsExportReqVO();
                exr.setGoodsNumber(itemCode);
                exr.setPoNo(orderDO.getPoNo());
                exr.setConsequence(conStr);
                List<GoodsDO> goodsDO = goodsMapper.selectList(exr);
                if (!goodsDO.isEmpty()) {
                    // 更新入库单详情信息
                    /*goodsDO.setGoodsName(itemName);
                    goodsDO.setGoodsSpecs(itemSpec);
                    goodsDO.setCompany(itemUnit);
                    goodsDO.setQuantity(itemNum);
                    goodsDO.setErpNum(itemNum);
                    goodsDO.setPoNo(orderDO.getPoNo());
                    if(!consequence.equals(-1)){
                        goodsDO.setConsequence(String.valueOf(consequence));
                    }
                    editGoods.add(goodsDO);*/
                    continue;
                } else {
                    // 新增入库单详情信息
                    GoodsDO goods = new GoodsDO();
                    goods.setPurchaseId(orderId);
                    goods.setGoodsName(itemName);
                    goods.setErpNum(itemNum);
                    goods.setStatus(0); // 入库单状态：未打印
                    goods.setGoodsSpecs(itemSpec);
                    goods.setCompany(itemUnit);
                    goods.setQuantity(itemNum);
                    goods.setGoodsNumber(itemCode);
                    goods.setPoNo(orderDO.getPoNo());
                    //goodsDO.setConsequence(consequence.toString());
                    if(!consequence.equals(-1)){
                        goods.setConsequence(String.valueOf(consequence));
                    }
                    addGoods.add(goods);
                }
            }
        }

        if(!addGoods.isEmpty()){
            goodsMapper.insertBatch(addGoods);
        }
        if(!editGoods.isEmpty()){
            goodsMapper.updateBatch(editGoods);
        }
        System.out.println("入库单定时器执行完成!");
        return "success";
    }
}