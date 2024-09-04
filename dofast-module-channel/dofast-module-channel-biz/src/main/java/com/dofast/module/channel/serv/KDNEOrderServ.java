package com.dofast.module.channel.serv;

import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.kndpojo.eorder.KDNEOrderReciver;
import com.dofast.module.channel.kndpojo.eorder.KDNEOrderReq;
import com.dofast.module.channel.kndpojo.eorder.KDNEOrderRes;
import com.dofast.module.channel.kndpojo.eorder.KDNEOrderSender;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageCreateReqVO;
import com.dofast.module.trade.service.electronicsheetpackage.ElectronicsheetPackageService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

@Component
public class KDNEOrderServ {
    // 从配置文件中读取快递鸟API的AppKey和EBusinessID
    @Value("${kdniao.appKey}")
    private String apiKey;

    @Value("${kdniao.eBusinessID}")
    private String eBusinessID;

    // 注入OkHttpClient对象，用于发起HTTP请求
    @Autowired
    private OkHttpClient okHttpClient;

    /**
     * MD5加密
     * str 内容
     * charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str,String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * str 内容
     * charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException{
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * content 内容
     * keyValue ApiKey
     * charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    private  String encrypt (String content,String keyValue,String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }


    public KDNEOrderRes getKDNEOrder(KDNEOrderReq orderReq) throws Exception {
        String url = "https://api.kdniao.com/api/EOrderService";
        // 创建Gson对象
        Gson gson = new Gson();
        // 将params对象转换为JSON字符串
        Map<String, Object> param = toHashMap(orderReq);
        param.put("IsReturnPrintTemplate","1");
        String requestData = gson.toJson(param);
        // 组装系统级参数
        Map<String,String> params = new HashMap<String,String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", eBusinessID);
        params.put("RequestType", "1007");
        String dataSign=encrypt(requestData, apiKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");
        // 以form表单形式提交post请求，post请求体中包含了应用级参数和系统级参数
        KDNEOrderRes data = sendPost(url, params);
        return data;
    }


    @Resource
    private ElectronicsheetPackageService electronicsheetPackageService;

    /**
     * 向指定 URL 发送POST方法的请求
     * url 发送请求的 URL
     * params 请求的参数集合
     * @return 远程资源的响应结果
     */
    private KDNEOrderRes sendPost(String url, Map<String,String> params) throws IOException {
        // 创建OkHttpClient对象并构建请求
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formBuilder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        // 发送请求并获取响应
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();
        Map<String, Object> result = new Gson().fromJson(responseBody, new TypeToken<Map<String, Object>>() {}.getType());
        if ("true".equalsIgnoreCase(result.get("Success").toString())) {
            // 请求成功，返回信息
            KDNEOrderRes data = new Gson().fromJson(responseBody, KDNEOrderRes.class);

            //存入DB电子面单
            ElectronicsheetPackageCreateReqVO bean=new ElectronicsheetPackageCreateReqVO();
            bean.setOrderNo(data.getResultCode());
            bean.setParentWaybillCode(data.getOrder().getSignWaybillCode());
            bean.setWaybillCode(data.getOrder().getLogisticCode());
            bean.setPrintTemplate(data.getPrintTemplate());
            electronicsheetPackageService.createElectronicsheetPackage(bean);

            return data;
        } else {
            Object resultCode = result.get("ResultCode");
            if (resultCode == null) {
                resultCode = new Integer(-1);
            }
            // 请求失败，返回错误信息
            throw exception(new ErrorCode(Integer.valueOf((String)resultCode) , result.get("Reason").toString()));
        }
    }

    //类转化为参数
    public Map<String, Object> toHashMap(KDNEOrderReq orderReq){
        // 设置KDNEOrderReq对象的属性值
        Map<String, Object> paramMap = new HashMap<>();
        Gson gson = new Gson();
        paramMap.put("ShipperCode", orderReq.getShipperCode());
        if (orderReq.getCustomerName() != null) {
            paramMap.put("CustomerName", orderReq.getCustomerName());
        }
        if (orderReq.getCustomerPwd() != null) {
            paramMap.put("CustomerPwd", orderReq.getCustomerPwd());
        }
        if (orderReq.getSendSite() != null) {
            paramMap.put("SendSite", orderReq.getSendSite());
        }
        if (orderReq.getSendStaff() != null) {
            paramMap.put("SendStaff", orderReq.getSendStaff());
        }
        if (orderReq.getMonthCode() != null) {
            paramMap.put("MonthCode", orderReq.getMonthCode());
        }
        paramMap.put("OrderCode", orderReq.getOrderCode());
        paramMap.put("PayType", orderReq.getPayType().toString());
        paramMap.put("ExpType", orderReq.getExpType());
        if (orderReq.getReceiver().getMobile() == null && orderReq.getReceiver().getTel() == null){
            throw exception(new ErrorCode(3049100, "电话和手机不能同时为空"));}
        KDNEOrderReciver receiver = orderReq.getReceiver();
        HashMap<String, String> receiverMap = new HashMap<String, String>();
        receiverMap.put("Name", receiver.getName());
        receiverMap.put("ProvinceName", receiver.getProvinceName());
        receiverMap.put("CityName", receiver.getCityName());
        receiverMap.put("ExpAreaName", receiver.getExpAreaName());
        receiverMap.put("Address", receiver.getAddress());
        if (receiver.getCompany() != null && !receiver.getCompany().isEmpty()) {
            receiverMap.put("Company", receiver.getCompany());
        }
        if (receiver.getMobile() != null && !receiver.getMobile().isEmpty()) {
            receiverMap.put("Mobile", receiver.getMobile());
        }
        if (receiver.getTel() != null && !receiver.getTel().isEmpty()) {
            receiverMap.put("Tel", receiver.getTel());
        }
        if (receiver.getPostCode() != null && !receiver.getPostCode().isEmpty()) {
            receiverMap.put("PostCode", receiver.getPostCode());
        }
        paramMap.put("Receiver", orderReq.getReceiver());
        KDNEOrderSender sender = orderReq.getSender();
        if (orderReq.getSender().getMobile() == null && orderReq.getSender().getTel() == null){
            throw exception(new ErrorCode(3049100, "电话和手机不能同时为空"));}
        HashMap<String, Object> senderMap = new HashMap<String, Object>();
        senderMap.put("Name", sender.getName());
        senderMap.put("CityName", sender.getCityName());
        senderMap.put("ExpAreaName", sender.getExpAreaName());
        senderMap.put("Address", sender.getAddress());
        senderMap.put("ProvinceName", sender.getProvinceName());
        if (sender.getMobile() != null && !sender.getMobile().isEmpty()) {
            senderMap.put("Mobile", sender.getMobile());
        }
        if (sender.getTel() != null && !sender.getTel().isEmpty()) {
            senderMap.put("Tel", sender.getTel());
        }
        if (sender.getCompany() != null && !sender.getCompany().isEmpty()) {
            senderMap.put("Company", sender.getCompany());
        }
        if (sender.getPostCode() != null && !sender.getPostCode().isEmpty()) {
            senderMap.put("PostCode", sender.getPostCode());
        }
        paramMap.put("Sender", orderReq.getSender());
        paramMap.put("Quantity", orderReq.getQuantity().toString());
        if (orderReq.getWeight() != null) {
            paramMap.put("Weight", orderReq.getWeight().toString());
        }
        if (orderReq.getVolume() != null) {
            paramMap.put("Volume", orderReq.getVolume().toString());
        }
        if (orderReq.getCost() != null) {
            paramMap.put("Cost", orderReq.getCost().toString());
        }
        if (orderReq.getOtherCost() != null) {
            paramMap.put("OtherCost", orderReq.getOtherCost().toString());
        }
        if (orderReq.getAddService() != null) {
            paramMap.put("AddService", orderReq.getAddService());
        }
        if (orderReq.getRemark() != null) {
            paramMap.put("Remark", orderReq.getRemark());
        }
        if (orderReq.getCommodity() != null) {
            paramMap.put("Commodity", orderReq.getCommodity());
        }
        if (orderReq.getIsReturnPrintTemplate() != null) {
            paramMap.put("IsReturnPrintTemplate", orderReq.getIsReturnPrintTemplate());
        }
        if (orderReq.getTemplateSize() != null) {
            paramMap.put("TemplateSize", orderReq.getTemplateSize());
        }
        if (orderReq.getCustomArea() != null) {
            paramMap.put("CustomArea", orderReq.getCustomArea());
        }
        if (orderReq.getTransType() != null) {
            paramMap.put("TransType", orderReq.getTransType());
        }
        if (orderReq.getPackingType() != null) {
            paramMap.put("PackingType", orderReq.getPackingType().toString());
        }
        if (orderReq.getDeliveryMethod() != null) {
            paramMap.put("DeliveryMethod", orderReq.getDeliveryMethod().toString());
        }
        if (orderReq.getIsSubscribe() != null) {
            paramMap.put("IsSubscribe", orderReq.getIsSubscribe());
        }
        if (orderReq.getCallback() != null) {
            paramMap.put("Callback", orderReq.getCallback());
        }
        if (orderReq.getWareHouseID() != null) {
            paramMap.put("WareHouseID", orderReq.getWareHouseID());
        }
        if (orderReq.getTheOrderCode() != null) {
            paramMap.put("TheOrderCode", orderReq.getTheOrderCode());
        }
        if (orderReq.getIsNotice() != null) {
            paramMap.put("IsNotice", orderReq.getIsNotice().toString());
        }
        if (orderReq.getStartDate() != null) {
            paramMap.put("StartDate", orderReq.getStartDate());
        }
        if (orderReq.getEndDate() != null) {
            paramMap.put("EndDate", orderReq.getEndDate());
        }
        if (orderReq.getMemberID() != null) {
            paramMap.put("MemberID", orderReq.getMemberID());
        }
        if (orderReq.getIsReturnSignBill() != null) {
            paramMap.put("IsReturnSignBill", orderReq.getIsReturnSignBill());
        }
        if (orderReq.getOperateRequire() != null) {
            paramMap.put("OperateRequire", orderReq.getOperateRequire());
        }
        if (orderReq.getIsSendMessage() != null) {
            paramMap.put("IsSendMessage", orderReq.getIsSendMessage());
        }
        if (orderReq.getCurrencyCode() != null) {
            paramMap.put("CurrencyCode", orderReq.getCurrencyCode());
        }
        if (orderReq.getDeclaredValue() != null) {
            paramMap.put("Dutiable.DeclaredValue", orderReq.getDeclaredValue().toString());
        }
        if (orderReq.getLogisticCode() != null) {
            paramMap.put("LogisticCode", orderReq.getLogisticCode());
        }
        return paramMap;
    }
}
