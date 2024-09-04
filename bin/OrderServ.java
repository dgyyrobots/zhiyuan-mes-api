package com.dofast.module.channel.serv;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.controller.admin.order.vo.OrderCreateReqVO;
import com.dofast.module.channel.controller.admin.order.vo.OrderExportReqVO;
import com.dofast.module.channel.controller.admin.order.vo.OrderReceiveVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.service.order.OrderService;
import com.dofast.module.channel.service.ordergoods.OrderGoodsService;
import com.dofast.module.channel.service.shop.ShopService;
import com.dofast.module.event.event.ChannelOrderEvent;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;


@Component
public class OrderServ {

    @Autowired
    OrderService orderService;

    @Autowired
    ShopService shopService;

    @Autowired
    OrderGoodsService orderGoodsService;

    @Autowired
    Dian3 dian3;

    @Autowired
    ApplicationEventPublisher publisher;


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
        orderService.createOrder(order);
        lines.forEach( so -> {
            JSONObject sox = (JSONObject) so;
            OrderGoodsCreateReqVO subOrder = JSONObject.parseObject(JSONUtil.toJsonStr(so), OrderGoodsCreateReqVO.class);
            subOrder.setRefOid(order.getRefOid());
            subOrder.setUserId(0);
            subOrder.setCustomerId(0);
            orderGoodsService.createOrderGoods(subOrder);
        });

        ChannelOrderEvent event  = new ChannelOrderEvent(this, "CHANNEL_ORDER_CREATE", o);
        publisher.publishEvent(event);
    }

    public boolean checkExist(String refOid) {
        OrderExportReqVO orderExportReqVO = new OrderExportReqVO();
        orderExportReqVO.setRefOid(refOid);
        List<OrderDO> orderList  = orderService.getOrderList(orderExportReqVO);
        if (orderList.size() == 0) return false;
        else return true;
    }


    public void receiveOrders(List<OrderReceiveVO> orders) {
        orders.forEach( i -> {

        });
    }




    public void getOrders(HashMap params) {
//        String ret = dian3.init("ds.omni.erp.third.order.query", params)
//                .buildSign()
//                .doPost();
        String ret = "{\"response\":{\"data\":{\"hasNext\":false,\"content\": [\n" +
                "        {\n" +
                "            \"refOid\": \"1094459426000494380\",\n" +
                "            \"posCode\": \"052205\",\n" +
                "            \"refType\": \"JD\",\n" +
                "            \"totalFee\": 0.3,\n" +
                "            \"payment\": 0.3,\n" +
                "            \"receivedPayment\": 0,\n" +
                "            \"totalPrice\": 0.3,\n" +
                "            \"totalSellPrice\": 0.3,\n" +
                "            \"postFee\": 0,\n" +
                "            \"serviceFee\": 0,\n" +
                "            \"discountFee\": 0,\n" +
                "            \"orderTime\": \"1681485195000\",\n" +
                "            \"modifyTime\": \"1681485195000\",\n" +
                "            \"payTime\": \"1681485195000\",\n" +
                "            \"shippingTime\": \"1681529074000\",\n" +
                "            \"finishTime\": \"1681529074000\",\n" +
                "            \"receiverCountry\": \"中国\",\n" +
                "            \"receiverState\": \"广东省\",\n" +
                "            \"receiverCity\": \"东莞市\",\n" +
                "            \"receiverDistrict\": \"\",\n" +
                "            \"receiverTown\": \"\",\n" +
                "            \"receiverId\": \"acdeed\",\n" +
                "            \"status\": \"SHIPPED\",\n" +
                "            \"type\": \"SALE\",\n" +
                "            \"refundStatus\": \"NO_REFUND\",\n" +
                "            \"flag\": \"RED\",\n" +
                "            \"sellerMemo\": \"\",\n" +
                "            \"buyerMemo\": \"\",\n" +
                "            \"openSellerNick\": \"淘宝旗舰店\",\n" +
                "            \"openBuyerNick\": \"酒可否醉心　#C°\",\n" +
                "            \"openBuyerId\": \"\",\n" +
                "            \"createTime\": \"1681485247000\",\n" +
                "            \"logisticsPartnerCode\": \"YT\",\n" +
                "            \"logisticsOrderNo\": \"YT123456\",\n" +
                "            \"refWhsCode\": \"6251\",\n" +
                "            \"deliveryTime\": \"1681529074000\",\n" +
                "            \"latestDeliveryTime\": \"1681657995000\",\n" +
                "            \"props\": {\n" +
                "                \"authorId\": {\n" +
                "                    \"authorName\": \"张三\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"mark2\": [\n" +
                "                \"JING_PEI\"\n" +
                "            ],\n" +
                "            \"businessType\": \"GUARANTEE\",\n" +
                "            \"lines\": [\n" +
                "                {\n" +
                "                    \"refOlId\": \"1094459426000494380\",\n" +
                "                    \"refSpuId\": \"4475500459521\",\n" +
                "                    \"refSkuId\": \"611593415690\",\n" +
                "                    \"outerId\": \"0630001\",\n" +
                "                    \"title\": \"测试商品不发货 家居装饰客厅摇尾巴猫咪儿童房创意挂钟\",\n" +
                "                    \"standards\": \"18英寸,墨绿色\",\n" +
                "                    \"totalSellPrice\": 0.3,\n" +
                "                    \"totalPrice\": 0.3,\n" +
                "                    \"price\": 0.3,\n" +
                "                    \"sellPrice\": 0.3,\n" +
                "                    \"totalFee\": \"34\",\n" +
                "                    \"singleFee\": \"3\",\n" +
                "                    \"num\": 1,\n" +
                "                    \"refundStatus\": \"NO_REFUND\",\n" +
                "                    \"status\": \"SHIPPED\",\n" +
                "                    \"picUrl\": \"http://www.baidu.com\",\n" +
                "                    \"isFreeGift\": \"false\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]},\"success\":true},\"_req_id\":\"VswRRSBZNQ\"}";

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
}
