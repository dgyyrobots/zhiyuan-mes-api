package com.dofast.module.channel.serv;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.dian3logisticspojo.waybill.*;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.ElectronicsheetPackageCreateReqVO;
import com.dofast.module.trade.service.electronicsheetpackage.ElectronicsheetPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

@Component
public class WayBillServ {
    @Autowired
    Dian3 dian3;

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    ElectronicsheetPackageService electronicsheetPackageService;

    public List<Dian3WayBillRes> getWillBill(HashMap params) {
        String ret = dian3.init("ds.omni.erp.waybill.third.get", params)
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
            JSONArray contentArray = data.getJSONArray("content");
            List<Dian3WayBillRes> list = new ArrayList<>();
            for (int i = 0; i < contentArray.size(); i++) {
                JSONObject wayBillRes = contentArray.getJSONObject(i);
                Dian3WayBillRes res = new Dian3WayBillRes();
                res.setOuterCode(wayBillRes.getString("outerCode"));
                res.setResult(wayBillRes.getBoolean("result"));
                res.setWaybillCode(wayBillRes.getString("waybillCode"));
                res.setErrorMsg(wayBillRes.getString("errorMsg"));
                res.setParentWaybillCode(wayBillRes.getString("parentWaybillCode"));
                if (res.getResult()){
                    list.add(res);
                    saveDataToLocal(res);
                }else {
                    throw exception(new ErrorCode(3049006, res.getErrorMsg()));
                }
            }
            return list;
        }else {
            throw exception(3049007, "电子面单创建失败");
        }
    }

    public void cancelWillBill(HashMap params) {
        String ret = dian3.init("ds.omni.erp.waybill.third.cancel", params)
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
            Boolean result = data.getBoolean("result");
            if (result){
                return;
            }else {
                throw exception(new ErrorCode(data.getInteger("code"), data.getString("msg")));
            }
        }
    }

    public void saveDataToLocal(Dian3WayBillRes res){
        ElectronicsheetPackageCreateReqVO electronicsheetPackageCreateReqVO = new ElectronicsheetPackageCreateReqVO();
        electronicsheetPackageCreateReqVO.setOrderNo(res.getOuterCode());
        electronicsheetPackageCreateReqVO.setWaybillCode(res.getWaybillCode());
        electronicsheetPackageCreateReqVO.setParentWaybillCode(res.getParentWaybillCode());
        electronicsheetPackageService.createElectronicsheetPackage(electronicsheetPackageCreateReqVO);
    }

    public HashMap<String, Object> toHashMap(Dian3WayBillReq dian3WayBillReq){
        HashMap<String, Object> map = new HashMap<>();
        map.put("cpCode", dian3WayBillReq.getCpCode());

        Dian3WayBillReqSend sendObject = dian3WayBillReq.getSend();
        if (sendObject != null) {
            Map<String, String> sendMap = new HashMap<>();
            sendMap.put("detail", sendObject.getDetail());
            sendMap.put("mobile", sendObject.getMobile());
            sendMap.put("name", sendObject.getName());
            sendMap.put("phone", sendObject.getPhone());
            sendMap.put("province", sendObject.getProvince());
            sendMap.put("city", sendObject.getCity());
            sendMap.put("district", sendObject.getDistrict());
            sendMap.put("town", sendObject.getTown());
            map.put("send", sendMap);
        }

        List<Dian3WayBillReqPackages> packagesList = dian3WayBillReq.getPackages();
        if (packagesList != null && !packagesList.isEmpty()) {
            List<Map<String, Object>> packagesMapList = new ArrayList<>();
            for (Dian3WayBillReqPackages packageObject : packagesList) {
                Map<String, Object> packageMap = new HashMap<>();
                packageMap.put("outerCode", packageObject.getOuterCode());
                packageMap.put("posCode", packageObject.getPosCode());
                packageMap.put("refOid", packageObject.getRefOid());
                packageMap.put("channelType", packageObject.getChannelType());
                packageMap.put("totalPackagesCount", packageObject.getTotalPackagesCount());
                packageMap.put("declaredValue", packageObject.getDeclaredValue());
                packageMap.put("volume", packageObject.getVolume());
                packageMap.put("weight", packageObject.getWeight());
                packageMap.put("packageId", packageObject.getPackageId());

                Dian3WayBillReqPackagesReceipt receiptObject = packageObject.getReceipt();
                if (receiptObject != null) {
                    Map<String, String> receiptMap = new HashMap<>();
                    receiptMap.put("detail", receiptObject.getDetail());
                    receiptMap.put("mobile", receiptObject.getMobile());
                    receiptMap.put("name", receiptObject.getName());
                    receiptMap.put("phone", receiptObject.getPhone());
                    receiptMap.put("province", receiptObject.getProvince());
                    receiptMap.put("city", receiptObject.getCity());
                    receiptMap.put("district", receiptObject.getDistrict());
                    receiptMap.put("town", receiptObject.getTown());
                    packageMap.put("receipt", receiptMap);
                }

                List<Dian3WayBillReqPackagesItems> itemsList = packageObject.getItems();
                if (itemsList != null && !itemsList.isEmpty()) {
                    List<Map<String, Object>> itemsMapList = new ArrayList<>();
                    for (Dian3WayBillReqPackagesItems itemObject : itemsList) {
                        Map<String, Object> itemMap = new HashMap<>();
                        itemMap.put("name", itemObject.getName());
                        itemMap.put("num", itemObject.getNum());
                        itemsMapList.add(itemMap);
                    }
                    packageMap.put("items", itemsMapList);
                }

                packagesMapList.add(packageMap);
            }
            map.put("packages", packagesMapList);
        }
        map.put("templateUrl", dian3WayBillReq.getTemplateUrl());
        return map;
    }
}
