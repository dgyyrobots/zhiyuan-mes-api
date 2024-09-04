package com.dofast.module.channel.serv;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.dian3logisticspojo.offline.*;
import com.dofast.module.channel.dian3logisticspojo.stdTpl.Dian3StdTplReq;
import com.dofast.module.channel.dian3logisticspojo.stdTpl.Dian3StdTplRes;
import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillReqPackagesItems;
import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillReqSend;
import com.dofast.module.event.event.Dian3LogisticsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

@Component
public class OffLineServ {
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
        Dian3StdTplRes dian3StdTplRes = JSONObject.parseObject(JSONUtil.toJsonStr(o), Dian3StdTplRes.class);
        Dian3LogisticsEvent event  = new Dian3LogisticsEvent(this, "DIAN3_STDTPL_QUERY_GET", dian3StdTplRes);
        publisher.publishEvent(event);
    }

    public void getOffLine(HashMap params) {
        String ret = dian3.init("ds.omni.erp.waybill.offline.get", params)
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
            // 创建Dian3OrderReq对象并设置属性
            Dian3OffLineRes orderRes = new Dian3OffLineRes();
            orderRes.setCpCode(jsonObject.getString("cpCode"));
            orderRes.setTemplateUrl(jsonObject.getString("templateUrl"));

            // 设置send对象
            JSONObject sendObject = jsonObject.getJSONObject("send");
            Dian3WayBillReqSend shippingInfo = new Dian3WayBillReqSend();
            shippingInfo.setProvince(sendObject.getString("province"));
            shippingInfo.setCity(sendObject.getString("city"));
            shippingInfo.setDistrict(sendObject.getString("district"));
            shippingInfo.setTown(sendObject.getString("town"));
            shippingInfo.setDetail(sendObject.getString("detail"));
            shippingInfo.setMobile(sendObject.getString("mobile"));
            shippingInfo.setPhone(sendObject.getString("phone"));
            shippingInfo.setName(sendObject.getString("name"));
            orderRes.setSend(shippingInfo);

            // 设置Order对象
            JSONArray ordersArray = jsonObject.getJSONArray("orders");
            List<Dian3OffLineResOrder> orders = new ArrayList<>();
            for (int i = 0; i < ordersArray.size(); i++) {
                JSONObject orderObject = ordersArray.getJSONObject(i);
                Dian3OffLineResOrder order = new Dian3OffLineResOrder();

                // 设置OrderInfo对象
                JSONObject orderInfoObject = orderObject.getJSONObject("orderInfo");
                Dian3OffLineResOrderInfo orderInfo = new Dian3OffLineResOrderInfo();
                orderInfo.setPosCode(orderInfoObject.getString("posCode"));
                orderInfo.setRefOid(orderInfoObject.getString("refOid"));
                orderInfo.setPayNo(orderInfoObject.getString("payNo"));
                orderInfo.setPayment(orderInfoObject.getString("payment"));
                orderInfo.setReceivedPayment(orderInfoObject.getString("receivedPayment"));
                orderInfo.setPostFee(orderInfoObject.getString("postFee"));
                orderInfo.setServiceFee(orderInfoObject.getString("serviceFee"));
                orderInfo.setReceiverName(orderInfoObject.getString("receiverName"));
                orderInfo.setReceiverCountry(orderInfoObject.getString("receiverCountry"));
                orderInfo.setReceiverState(orderInfoObject.getString("receiverState"));
                orderInfo.setReceiverCity(orderInfoObject.getString("receiverCity"));
                orderInfo.setReceiverDistrict(orderInfoObject.getString("receiverDistrict"));
                orderInfo.setReceiverTown(orderInfoObject.getString("receiverTown"));
                orderInfo.setReceiverAddress(orderInfoObject.getString("receiverAddress"));
                orderInfo.setReceiverMobile(orderInfoObject.getString("receiverMobile"));
                orderInfo.setReceiverPhone(orderInfoObject.getString("receiverPhone"));
                orderInfo.setReceiverZip(orderInfoObject.getString("receiverZip"));
                orderInfo.setOrderTime(orderInfoObject.getString("orderTime"));
                orderInfo.setModifyTime(orderInfoObject.getString("modifyTime"));
                orderInfo.setOpenBuyerId(orderInfoObject.getString("openBuyerId"));
                orderInfo.setOpenBuyerNick(orderInfoObject.getString("openBuyerNick"));
                orderInfo.setPayTime(orderInfoObject.getString("payTime"));
                orderInfo.setShippingTime(orderInfoObject.getString("shippingTime"));
                orderInfo.setStatus(orderInfoObject.getString("status"));
                orderInfo.setRefundStatus(orderInfoObject.getString("refundStatus"));
                orderInfo.setFlag(orderInfoObject.getString("flag"));
                orderInfo.setSellerMemo(orderInfoObject.getString("sellerMemo"));
                orderInfo.setBuyerMemo(orderInfoObject.getString("buyerMemo"));

                // 设置OrderLine对象
                JSONArray itemArray = jsonObject.getJSONArray("lines");
                List<Dian3OffLineResOrderItem> items = new ArrayList<>();
                for (int j = 0; j < itemArray.size(); j++) {
                    JSONObject linesObject = ordersArray.getJSONObject(i);
                    Dian3OffLineResOrderItem lines = new Dian3OffLineResOrderItem();
                    lines.setRefSpuId(linesObject.getString("refSpuId"));
                    lines.setRefSkuId(linesObject.getString("refSkuId"));
                    lines.setSellPrice(linesObject.getString("sellPrice"));
                    lines.setPrice(linesObject.getString("price"));
                    lines.setTotalSellPrice(linesObject.getString("totalSellPrice"));
                    lines.setTotalPrice(linesObject.getString("totalPrice"));
                    lines.setNum(linesObject.getString("num"));
                    lines.setTitle(linesObject.getString("title"));
                    lines.setOuterId(linesObject.getString("outerId"));
                    lines.setStandards(linesObject.getString("standards"));
                    lines.setRefOlId(linesObject.getString("refOlId"));
                    lines.setRefundStatus(linesObject.getString("refundStatus"));
                    lines.setDiscountFee(linesObject.getString("discountFee"));
                    lines.setStatus(linesObject.getString("status"));
                    items.add(lines);
                }
                orderInfo.setLines(items);
                order.setOrderInfo(orderInfo);
                // 解析包裹信息
                JSONArray jsonPackages = jsonObject.getJSONArray("packages");
                List<Dian3OffLineResPackages> packages = new ArrayList<>();
                for (int j = 0; j < jsonPackages.size(); i++) {
                    JSONObject jsonPackage = jsonPackages.getJSONObject(i);
                    Dian3OffLineResPackages packageObj = new Dian3OffLineResPackages();
                    packageObj.setPackageId(jsonPackage.getString("packageId"));
                    packageObj.setChannelType(jsonPackage.getString("channelType"));
                    packageObj.setTotalPackagesCount(Integer.valueOf(jsonPackage.getString("totalPackagesCount")));
                    packageObj.setDeclaredValue(Double.valueOf(jsonPackage.getString("declaredValue")));
                    packageObj.setVolume(Integer.valueOf(jsonPackage.getString("volume")));
                    packageObj.setWeight(Integer.valueOf(jsonPackage.getString("weight")));
                    JSONArray jsonItems = jsonPackage.getJSONArray("items");
                    List<Dian3WayBillReqPackagesItems> items1 = new ArrayList<>();
                    for (int z = 0; z < jsonItems.size(); j++) {
                        JSONObject jsonItem = jsonItems.getJSONObject(j);
                        Dian3WayBillReqPackagesItems item = new Dian3WayBillReqPackagesItems();
                        item.setName(jsonItem.getString("name"));
                        item.setNum(Integer.valueOf(jsonItem.getString("num")));
                        items1.add(item);
                    }
                    packageObj.setItems(items1);
                    packages.add(packageObj);
                }
                order.setPackages(packages);
                orders.add(order);
            }
            orderRes.setOrders(orders);
            //可在此处返回数据
        }
    }

    private HashMap<String, Object> toHashMap(Dian3OffLineReq req){
        HashMap<String, Object> map = new HashMap<>();
        map.put("appKey", req.getAppKey());
        map.put("method", req.getMethod());
        map.put("sign", req.getSign());
        map.put("timestamp", req.getTimestamp());
        return map;
    }
}
