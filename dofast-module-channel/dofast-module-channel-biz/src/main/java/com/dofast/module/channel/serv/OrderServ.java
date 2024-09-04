package com.dofast.module.channel.serv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.api.order.dto.OrderGetDTO;
import com.dofast.module.channel.api.order.dto.OrderReceiveDTO;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.controller.admin.order.vo.*;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsBaseVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsExportReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import com.dofast.module.channel.dal.mysql.order.ChannelOrderMapper;
import com.dofast.module.channel.dal.mysql.ordergoods.OrderGoodsMapper;
import com.dofast.module.channel.service.order.ChannelOrderService;
import com.dofast.module.channel.service.ordergoods.OrderGoodsService;
import com.dofast.module.channel.service.shop.ShopService;
import com.dofast.module.event.event.ChannelOrderEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dofast.module.product.api.spu.ProductSpuApi;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.trade.dal.dataobject.aftersale.TradeAfterSaleDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.dofast.module.trade.dal.mysql.aftersale.TradeAfterSaleMapper;
import com.dofast.module.trade.dal.mysql.order.TradeOrderItemMapper;
import com.dofast.module.trade.dal.mysql.order.TradeOrderMapper;
import com.dofast.module.trade.enums.order.TradeOrderAfterSaleStatusEnum;
import com.dofast.module.trade.enums.order.TradeOrderStatusEnum;
import com.dofast.module.trade.service.aftersale.TradeAfterSaleService;
import com.dofast.module.trade.service.order.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;


@Component
public class OrderServ {

    @Autowired
    ChannelOrderService channelOrderService;

    @Autowired
    ShopService shopService;

    @Autowired
    OrderGoodsService orderGoodsService;

    @Autowired
    Dian3 dian3;

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    TradeOrderService tradeOrderService;

    @Autowired
    TradeOrderMapper tradeOrderMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    ChannelOrderMapper orderedMap;

    @Autowired
    ProductSpuApi productSpuApi;

    @Autowired
    TradeOrderItemMapper tradeOrderItemMapper;

    @Autowired
    TradeAfterSaleService tradeAfterSaleService;

    @Autowired
    TradeAfterSaleMapper tradeAfterSaleMapper;


    public void processContent(JSONArray orders) {
        orders.forEach( o -> {
            processOne(o);
        });
    }

    @Transactional
    public void processOne(Object o) {
        JSONObject ox = (JSONObject) o;
        JSONArray lines = ox.getJSONArray("lines");
        OrderCreateReqVO order = JSONObject.parseObject(JSONUtil.toJsonStr(o), OrderCreateReqVO.class);
        order.setUserId(0);
        order.setAddressId(0);
        order.setCustomerId(0);
        if (checkExist(order.getRefOid())) return;
        Integer tenantId = shopService.getTenantIdByShopCode(order.getPosCode());
        //order中填加
        order.setTenantId(tenantId);
        channelOrderService.createOrder(order);
        lines.forEach( so -> {
            JSONObject sox = (JSONObject) so;
            OrderGoodsCreateReqVO subOrder = JSONObject.parseObject(JSONUtil.toJsonStr(so), OrderGoodsCreateReqVO.class);
            subOrder.setRefOid(order.getRefOid());
            subOrder.setUserId(0);
            subOrder.setCustomerId(0);
            subOrder.setTenantId(tenantId);
            orderGoodsService.createOrderGoods(subOrder);
        });
        OrderGetDTO orderGetDTO = BeanUtil.toBean(order, OrderGetDTO.class);
        ChannelOrderEvent event  = new ChannelOrderEvent(this, "CHANNEL_ORDER_CREATE_GET", orderGetDTO);
        publisher.publishEvent(event);
    }


    @Transactional
    public void receiveOne(OrderReceiveVO orderReceiveVO) {
        List<OrderGoodsBaseVO> lines = orderReceiveVO.getLines();
        OrderCreateReqVO order = BeanUtil.toBean(orderReceiveVO, OrderCreateReqVO.class);
        order.setUserId(0);
        order.setAddressId(0);
        order.setCustomerId(0);
        if (checkExist(order.getRefOid())) return;
        Integer tenantId = shopService.getTenantIdByShopCode(order.getPosCode());
        //order中填加
        order.setTenantId(tenantId);
        channelOrderService.createOrder(order);
        lines.forEach( so -> {
            OrderGoodsCreateReqVO subOrder =  BeanUtil.toBean(so, OrderGoodsCreateReqVO.class);
            subOrder.setRefOid(order.getRefOid());
            subOrder.setUserId(0);
            subOrder.setCustomerId(0);
            subOrder.setTenantId(tenantId);
            orderGoodsService.createOrderGoods(subOrder);
        });
        OrderReceiveDTO orderReceiveDTO = BeanUtil.toBean(order, OrderReceiveDTO.class);
        ChannelOrderEvent event  = new ChannelOrderEvent(this, "CHANNEL_ORDER_CREATE_RECEIVE" , orderReceiveDTO);
        publisher.publishEvent(event);
    }

    public boolean checkExist(String refOid) {
        OrderExportReqVO orderExportReqVO = new OrderExportReqVO();
        orderExportReqVO.setRefOid(refOid);
        List<OrderDO> orderList  = channelOrderService.getOrderList(orderExportReqVO);
        if (orderList.size() == 0) return false;
        else return true;
    }


    public ArrayList<String> receiveOrders(List<OrderReceiveVO> orders) {
        ArrayList<String> refOidList = new ArrayList<>();
        orders.forEach( orderReceiveVO -> {
            receiveOne(orderReceiveVO);
            refOidList.add(orderReceiveVO.getRefOid());
        });
        return refOidList;
    }




    public void getOrders(HashMap params) {
        String ret = dian3.init("ds.omni.erp.third.order.query", params)
                .buildSign()
                .doPost();
//        String ret = "{\"response\":{\"data\":{\"hasNext\":false,\"content\": [\n" +
//                "        {\n" +
//                "            \"refOid\": \"1094459426000494380\",\n" +
//                "            \"posCode\": \"052205\",\n" +
//                "            \"refType\": \"JD\",\n" +
//                "            \"totalFee\": 0.3,\n" +
//                "            \"payment\": 0.3,\n" +
//                "            \"receivedPayment\": 0,\n" +
//                "            \"totalPrice\": 0.3,\n" +
//                "            \"totalSellPrice\": 0.3,\n" +
//                "            \"postFee\": 0,\n" +
//                "            \"serviceFee\": 0,\n" +
//                "            \"discountFee\": 0,\n" +
//                "            \"orderTime\": \"1681485195000\",\n" +
//                "            \"modifyTime\": \"1681485195000\",\n" +
//                "            \"payTime\": \"1681485195000\",\n" +
//                "            \"shippingTime\": \"1681529074000\",\n" +
//                "            \"finishTime\": \"1681529074000\",\n" +
//                "            \"receiverCountry\": \"中国\",\n" +
//                "            \"receiverState\": \"广东省\",\n" +
//                "            \"receiverCity\": \"东莞市\",\n" +
//                "            \"receiverDistrict\": \"\",\n" +
//                "            \"receiverTown\": \"\",\n" +
//                "            \"receiverId\": \"acdeed\",\n" +
//                "            \"status\": \"SHIPPED\",\n" +
//                "            \"type\": \"SALE\",\n" +
//                "            \"refundStatus\": \"NO_REFUND\",\n" +
//                "            \"flag\": \"RED\",\n" +
//                "            \"sellerMemo\": \"\",\n" +
//                "            \"buyerMemo\": \"\",\n" +
//                "            \"openSellerNick\": \"淘宝旗舰店\",\n" +
//                "            \"openBuyerNick\": \"酒可否醉心　#C°\",\n" +
//                "            \"openBuyerId\": \"\",\n" +
//                "            \"createTime\": \"1681485247000\",\n" +
//                "            \"logisticsPartnerCode\": \"YT\",\n" +
//                "            \"logisticsOrderNo\": \"YT123456\",\n" +
//                "            \"refWhsCode\": \"6251\",\n" +
//                "            \"deliveryTime\": \"1681529074000\",\n" +
//                "            \"latestDeliveryTime\": \"1681657995000\",\n" +
//                "            \"props\": {\n" +
//                "                \"authorId\": {\n" +
//                "                    \"authorName\": \"张三\"\n" +
//                "                }\n" +
//                "            },\n" +
//                "            \"mark2\": [\n" +
//                "                \"JING_PEI\"\n" +
//                "            ],\n" +
//                "            \"businessType\": \"GUARANTEE\",\n" +
//                "            \"lines\": [\n" +
//                "                {\n" +
//                "                    \"refOlId\": \"1094459426000494380\",\n" +
//                "                    \"refSpuId\": \"4475500459521\",\n" +
//                "                    \"refSkuId\": \"611593415690\",\n" +
//                "                    \"outerId\": \"0630001\",\n" +
//                "                    \"title\": \"测试商品不发货 家居装饰客厅摇尾巴猫咪儿童房创意挂钟\",\n" +
//                "                    \"standards\": \"18英寸,墨绿色\",\n" +
//                "                    \"totalSellPrice\": 0.3,\n" +
//                "                    \"totalPrice\": 0.3,\n" +
//                "                    \"price\": 0.3,\n" +
//                "                    \"sellPrice\": 0.3,\n" +
//                "                    \"totalFee\": \"34\",\n" +
//                "                    \"singleFee\": \"3\",\n" +
//                "                    \"num\": 1,\n" +
//                "                    \"refundStatus\": \"NO_REFUND\",\n" +
//                "                    \"status\": \"SHIPPED\",\n" +
//                "                    \"picUrl\": \"http://www.baidu.com\",\n" +
//                "                    \"isFreeGift\": \"false\"\n" +
//                "                }\n" +
//                "            ]\n" +
//                "        }\n" +
//                "    ]},\"success\":true},\"_req_id\":\"VswRRSBZNQ\"}";

        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            JSONObject data = response.getJSONObject("data");
            Boolean hasNext = data.getBoolean("hasNext");
            JSONArray orders = data.getJSONArray("content");
            processContent(orders);
            if (hasNext) {
                params.put("pageNo", Integer.valueOf(params.get("pageNo").toString()) + 1);
                this.getOrders(params);
            }
        }
    }

    public void orderShopShipment(HashMap<String, Object> params){
        //判断订单是否在点三系统中存在
        HashMap<String, Object> param = new HashMap<>();
        param.put("refOid", params.get("refOid"));
        String ret = dian3.init("ds.omni.erp.third.order.query", param)
                .buildSign()
                .doPost();
        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            JSONObject data = response.getJSONObject("data");
            JSONArray orders = data.getJSONArray("content");
            if (orders == null){
                throw exception(new ErrorCode(3049002, "点三系统不存在该订单"));
            }
        }
        //店铺订单发货
        String shipRet = dian3.init("ds.omni.erp.third.order.send", params)
                .buildSign()
                .doPost();
        JSONObject shipJson = JSON.parseObject(shipRet);
        String shipFlag = shipJson.getString("flag");
        if (shipFlag != null && shipFlag.equals("failure")) {
            String msg = shipJson.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject shipResponse = shipJson.getJSONObject("response");
        shipFlag = shipResponse.getString("flag");
        if (shipFlag != null && shipFlag.equals("failure")) {
            String msg = shipResponse.getString("message") + ":" +  shipResponse.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean shipSuccess = shipResponse.getBoolean("success");
        if (shipSuccess) {
            JSONObject shipData = shipResponse.getJSONObject("data");
            JSONObject shipInformation = shipData.getJSONObject("response");
            //解析数据
            String shipInformationFlag = shipInformation.getString("flag");
            String shipInformationSubCode = shipInformation.getString("sub_code");
            String shipInformationSubMessage = shipInformation.getString("sub_message");
            String shipInformationSuccess = shipInformation.getString("success");
            if (shipInformationSuccess.equals("true"))
            {
                OrderExportReqVO orderExportReqVO = new OrderExportReqVO();
                orderExportReqVO.setRefOid((String) params.get("refOid"));
                orderExportReqVO.setPosCode((String) params.get("posCode"));
                List<OrderDO> orderList = channelOrderService.getOrderList(orderExportReqVO);
                //更新本地数据库数据
                if (orderList != null){
                    OrderDO orderDO = orderList.get(0);
                    List<OrderGoodsDO> orderGoodsVOS = orderGoodsService.getPageOrderGoods(orderDO.getRefOid());
                    OrderShipmentPakReq orderShipmentPakReq = (OrderShipmentPakReq) params.get("package");
                    if (orderShipmentPakReq != null)
                    {
                        List<OrderShipmentItemReq> orderShipmentItemReqList = orderShipmentPakReq.getLines();
                        if (orderShipmentItemReqList != null){
                            String orderDOStatus = (orderShipmentItemReqList.size() == orderGoodsVOS.size())? "SHIPPED" : "PART_SHIPPED";
                            orderDO.setStatus(orderDOStatus);
                            for (OrderShipmentItemReq orderShipmentItemReq : orderShipmentItemReqList){
                                OrderGoodsExportReqVO orderGoodsExportReqVO = new OrderGoodsExportReqVO();
                                orderGoodsExportReqVO.setRefOlId(orderShipmentItemReq.getRefOlId());
                                orderGoodsExportReqVO.setRefSkuId(orderShipmentItemReq.getRefSkuId());
                                List<OrderGoodsDO> orderGoodsDOList = orderGoodsService.getOrderGoodsList(orderGoodsExportReqVO);
                                if (orderGoodsDOList != null){
                                    OrderGoodsDO orderGoodsDO = orderGoodsDOList.get(0);
                                    String orderGoodsStatue = (orderGoodsDO.getNum() == orderShipmentItemReq.getNum())? "SHIPPED" : "PART_SHIPPED";
                                    orderGoodsDO.setStatus(orderGoodsStatue);
                                    OrderGoodsUpdateReqVO orderGoodsUpdateReqVO = BeanUtil.toBean(orderDO, OrderGoodsUpdateReqVO.class);
                                    orderGoodsService.updateOrderGoods(orderGoodsUpdateReqVO);
                                }
                            }
                            OrderUpdateReqVO orderUpdateReqVO = BeanUtil.toBean(orderDO, OrderUpdateReqVO.class);
                            channelOrderService.updateOrder(orderUpdateReqVO);
                        }
                    }
                }
                return;
            }
            if (shipInformationFlag.equals("failure")){
                throw exception(new ErrorCode(Integer.valueOf(shipInformationSubCode), shipInformationSubMessage));
            }
        }
    }

    public void modifyOrderNote(HashMap<String, Object> params){
        String ret = dian3.init("ds.omni.erp.third.order.memo.update", params)
                .buildSign()
                .doPost();
        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            JSONObject jsonObject = response.getJSONObject("data");
            // 解析数据
            Integer code = jsonObject.getInteger("code");
            boolean result = jsonObject.getBoolean("result");
            String msg = jsonObject.getString("msg");
            if(code == 200 && result){
                return;
            } else {
                throw exception(new ErrorCode(code, msg));
            }
        }
    }

    public void shopOrderShipped(HashMap<String, Object> params){
        String ret = dian3.init("ds.omni.erp.third.order.outbound", params)
                .buildSign()
                .doPost();
        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            JSONObject jsonData = response.getJSONObject("data");
            // 解析数据
            JSONObject dataResponse = jsonData.getJSONObject("response");
            String subCode = dataResponse.getString("sub_code");
            String subMessage = dataResponse.getString("sub_message");
            String dataSuccess = dataResponse.getString("success");
            if (dataSuccess.equals("true")) return;
            if(dataResponse.equals("failure")) throw exception(new ErrorCode(Integer.valueOf(subCode), subMessage));
        }
    }

    public void virtualDelivery(HashMap<String, Object> params){
        String ret = dian3.init("ds.omni.erp.third.order.dummy.send", params)
                .buildSign()
                .doPost();
        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            JSONObject data = response.getJSONObject("data");
            // 解析数据
            String content = data.getString("content");
            if (content.equals("虚拟发货成功")){
                return;
            }else {
                throw exception(new ErrorCode(3049004, "虚拟发货失败"));
            }

        }
    }

    public void getShopAfterSale(HashMap<String, Object> params){
        String ret = dian3.init("ds.omni.erp.third.order.after.refund.query", params)
                .buildSign()
                .doPost();
        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            JSONObject data = response.getJSONObject("data");
            Boolean hasNext = data.getBoolean("hasNext");
            JSONArray orders = data.getJSONArray("content");
            processAfterSaleContent(orders);
            if (hasNext) {
                params.put("pageNo", Integer.valueOf(params.get("pageNo").toString()) + 1);
                this.getOrders(params);
            }
        }
    }
    public boolean checkExistShopAfterSale(String refOid) {
        OrderExportReqVO orderExportReqVO = new OrderExportReqVO();
        orderExportReqVO.setRefOid(refOid);
        List<OrderDO> orderList  = channelOrderService.getOrderList(orderExportReqVO);
        if (orderList.size() == 0) return false;
        else return true;
    }

    public void processAfterSaleContent(JSONArray orders) {
        orders.forEach( o -> {
            processAfterSaleOne(o);
        });
    }

    @Transactional
    public void processAfterSaleOne(Object o) {
        //需要对数据处理然后写入到本地数据库中
        JSONObject ox = (JSONObject) o;
        ShopAfterSaleRes shopAfterSaleRes = JSONObject.parseObject(JSONUtil.toJsonStr(o), ShopAfterSaleRes.class);
        if (checkExistShopAfterSale(shopAfterSaleRes.getRefOid())) return;
        Integer tenantId = shopService.getTenantIdByShopCode(shopAfterSaleRes.getPosCode());
        //更新ChannelOrder
        updateChannelOrder(shopAfterSaleRes);
        //更新TradeOrder和TradeAfterSale
        updateTradeOrder(shopAfterSaleRes);
    }
    /**
     *店铺售后推送
     *
     * @param params 请求参数
     */
    public void shopAfterSalePush(HashMap<String, Object> params){
        String ret = dian3.init("ds.omni.erp.third.refund.push", params)
                .buildSign()
                .doPost();
        JSONObject json = JSON.parseObject(ret);
        String flag = json.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = json.getString("message");
            throw exception(new ErrorCode(3049001, msg));
        }
        JSONObject response = json.getJSONObject("response");
        flag = response.getString("flag");
        if (flag != null && flag.equals("failure")) {
            String msg = response.getString("message") + ":" +  response.getString("sub_message");
            throw exception(new  ErrorCode(3049001, msg));  //dian3_order
        }
        Boolean success = response.getBoolean("success");
        if (success) {
            return;
        }else{
            throw exception(new ErrorCode(response.getInteger("code"), response.getString("msg")));
        }
    }

    public void updateChannelOrder(ShopAfterSaleRes shopAfterSaleRes){
        OrderExportReqVO orderExportReqVO = new OrderExportReqVO();
        orderExportReqVO.setRefOid(shopAfterSaleRes.getRefOid());
        List<OrderDO> orderList  = channelOrderService.getOrderList(orderExportReqVO);
        if (orderList == null && orderList.isEmpty()) return;
        //更新ChannelOrder
        OrderUpdateReqVO orderUpdateReqVO = BeanUtil.toBean(orderList.get(0), OrderUpdateReqVO.class);
        orderUpdateReqVO.setStatus(shopAfterSaleRes.getStatus());
        orderUpdateReqVO.setRefundStatus(shopAfterSaleRes.getRefundPhase());
        channelOrderService.updateOrder(orderUpdateReqVO);
        OrderGoodsExportReqVO orderGoodsExportReqVO = new OrderGoodsExportReqVO();
        orderGoodsExportReqVO.setRefOid(shopAfterSaleRes.getRefOid());
        List<OrderGoodsDO> orderGoodsDOList = orderGoodsService.getOrderGoodsList(orderGoodsExportReqVO);
        List<ShopAfterSaleItemRes> shopAfterSaleItemRes = shopAfterSaleRes.getLines();
        //更新ChannelOrderGoods
        List<OrderGoodsDO> updatedOrderGoodsDOList = orderGoodsDOList.stream()
                .map(orderGoods -> {
                    List<ShopAfterSaleItemRes> matchingShopAfterSaleItems = shopAfterSaleItemRes.stream()
                            .filter(shopAfterSaleItem -> shopAfterSaleItem.getRefOlId().equals(orderGoods.getRefOlId()))
                            .collect(Collectors.toList());

                    if (!matchingShopAfterSaleItems.isEmpty()) {
                        ShopAfterSaleItemRes matchingShopAfterSaleItem = matchingShopAfterSaleItems.get(0);
                        // 根据需要，将matchingShopAfterSaleItem中的属性值赋给orderGoods
                        orderGoods.setRefundStatus(matchingShopAfterSaleItem.getRefundStatus());
                        orderGoods.setRefSkuId(matchingShopAfterSaleItem.getRefSkuId());
                        orderGoods.setRefSpuId(matchingShopAfterSaleItem.getRefSpuId());
                    }

                    return orderGoods;
                })
                .collect(Collectors.toList());
        for(OrderGoodsDO orderGoodsDO : updatedOrderGoodsDOList){
            OrderGoodsUpdateReqVO orderGoodsUpdateReqVO = BeanUtil.toBean(orderGoodsDO, OrderGoodsUpdateReqVO.class);
            orderGoodsService.updateOrderGoods(orderGoodsUpdateReqVO);
        }
    }

    public void updateTradeOrder(ShopAfterSaleRes shopAfterSaleRes){
        OrderExportReqVO orderExportReqVO = new OrderExportReqVO();
        orderExportReqVO.setRefOid(shopAfterSaleRes.getRefOid());
        List<OrderDO> orderList  = channelOrderService.getOrderList(orderExportReqVO);
        if (orderList == null && orderList.isEmpty()) return;
        TradeOrderDO tradeOrderDO = tradeOrderMapper.selectOne(TradeOrderDO::getChannelOrderId, orderList.get(0).getId());
        if (tradeOrderDO == null) return;
        tradeOrderDO.setStatus(TradeOrderStatusEnum.getStatusByName(shopAfterSaleRes.getStatus()));
        tradeOrderDO.setAfterSaleStatus(TradeOrderAfterSaleStatusEnum.getStatusByName(shopAfterSaleRes.getRefundPhase()));
        tradeOrderDO.setRefundPrice(Integer.parseInt(shopAfterSaleRes.getRefundFee()));
        tradeOrderMapper.updateById(tradeOrderDO);
        List<TradeOrderItemDO> tradeOrderItemDOS = tradeOrderItemMapper.selectListByOrderId(tradeOrderDO.getId());
        OrderGoodsExportReqVO orderGoodsExportReqVO = new OrderGoodsExportReqVO();
        orderGoodsExportReqVO.setRefOid(shopAfterSaleRes.getRefOid());
        List<OrderGoodsDO> orderGoodsDOList = orderGoodsService.getOrderGoodsList(orderGoodsExportReqVO);
        List<ShopAfterSaleItemRes> shopAfterSaleItemRes = shopAfterSaleRes.getLines();
        List<TradeAfterSaleDO> tradeAfterSaleDOS = tradeAfterSaleMapper.selectList(TradeAfterSaleDO::getOrderId, tradeOrderDO.getId());
        //根据三表之间的关联，对TradeOrderItemDO的值进行修改
        tradeOrderItemDOS.forEach(tradeOrderItem -> {
            // 找到匹配的OrderGoodsDO对象
            Optional<OrderGoodsDO> matchingOrderGoods = orderGoodsDOList.stream()
                    .filter(orderGoods -> orderGoods.getId().equals(tradeOrderItem.getChannelOrderItemId()))
                    .findFirst();

            // 找到匹配的ShopAfterSaleItemRes对象
            Optional<ShopAfterSaleItemRes> matchingShopAfterSaleItem = shopAfterSaleItemRes.stream()
                    .filter(shopAfterSaleItem -> shopAfterSaleItem.getRefOlId().equals(matchingOrderGoods.map(OrderGoodsDO::getRefOlId).orElse(null)))
                    .findFirst();

            // 如果都存在匹配的对象，则进行属性赋值
            matchingOrderGoods.ifPresent(orderGoods -> {
                matchingShopAfterSaleItem.ifPresent(shopAfterSaleItem -> {
                    // 根据需要，将ShopAfterSaleItemRes的部分属性值赋给TradeOrderItemDO
                    tradeOrderItem.setAfterSaleStatus(TradeOrderAfterSaleStatusEnum.getStatusByName(shopAfterSaleItem.getRefundStatus()));
                    tradeOrderItem.setSkuId(Long.valueOf(shopAfterSaleItem.getRefSkuId()));
                    tradeOrderItem.setSpuId(Long.valueOf(shopAfterSaleItem.getRefSpuId()));
                    ProductSpuRespDTO productSpuRespDTO = productSpuApi.getSpu(Long.valueOf(shopAfterSaleItem.getRefSpuId()));
                    tradeOrderItem.setSpuName(productSpuRespDTO.getName());
                    // 根据需要，赋值其他属性
                });
            });
        });
        for (TradeOrderItemDO tradeOrderItemDO : tradeOrderItemDOS){
            tradeOrderItemMapper.updateById(tradeOrderItemDO);
        }
        //更新TradeAfterSale表
        tradeAfterSaleDOS.forEach(tradeAfterSale -> {
            // 找到匹配的TradeOrderItemDO对象
            Optional<TradeOrderItemDO> matchingTradeOrderItem = tradeOrderItemDOS.stream()
                    .filter(tradeOrderItem -> tradeOrderItem.getId().equals(tradeAfterSale.getOrderItemId()))
                    .findFirst();

            // 找到匹配的OrderGoodsDO对象
            Optional<OrderGoodsDO> matchingOrderGoods = matchingTradeOrderItem.flatMap(tradeOrderItem ->
                    orderGoodsDOList.stream()
                            .filter(orderGoods -> orderGoods.getId().equals(tradeOrderItem.getChannelOrderItemId()))
                            .findFirst()
            );

            // 找到匹配的ShopAfterSaleItemRes对象
            Optional<ShopAfterSaleItemRes> matchingShopAfterSaleItem = matchingOrderGoods.flatMap(orderGoods ->
                    shopAfterSaleItemRes.stream()
                            .filter(shopAfterSaleItem -> shopAfterSaleItem.getRefOlId().equals(orderGoods.getRefOlId()))
                            .findFirst()
            );

            // 如果都存在匹配的对象，则进行属性赋值
            matchingTradeOrderItem.ifPresent(tradeOrderItem -> {
                matchingOrderGoods.ifPresent(orderGoods -> {
                    matchingShopAfterSaleItem.ifPresent(shopAfterSaleItem -> {
                        // 根据需要，将ShopAfterSaleItemRes的部分属性值赋给TradeAfterSaleDO
                        tradeAfterSale.setStatus(TradeOrderAfterSaleStatusEnum.getStatusByName(shopAfterSaleItem.getRefundStatus()));
                        tradeAfterSale.setSkuId(Long.valueOf(shopAfterSaleItem.getRefSkuId()));
                        tradeAfterSale.setSpuId(Long.valueOf(shopAfterSaleItem.getRefSpuId()));
                        ProductSpuRespDTO productSpuRespDTO = productSpuApi.getSpu(Long.valueOf(shopAfterSaleItem.getRefSpuId()));
                        tradeAfterSale.setSpuName(productSpuRespDTO.getName());
                        // 根据需要，赋值其他属性
                    });
                });
            });
        });
    }
}
