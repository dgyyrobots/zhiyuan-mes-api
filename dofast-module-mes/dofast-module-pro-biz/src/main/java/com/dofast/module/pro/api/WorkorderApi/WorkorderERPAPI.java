package com.dofast.module.pro.api.WorkorderApi;

import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.util.http.HttpUtils;
import com.dofast.module.mes.controller.admin.interfacelog.vo.InterfaceLogCreateReqVO;
import com.dofast.module.mes.service.interfacelog.InterfaceLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkorderERPAPI {

    @Resource
    private InterfaceLogService interfaceLogService;

    /**
     * 工单领/退料单生成接口
     */
    public String workOrderIssueCreate(Map<String, Object> params) {
        Map<String, Object> request = createBaseRequest();

        // 构建details
        List<Map<String, Object>> details = new ArrayList<>();
        List<Map<String, Object>> goodsList = (List<Map<String, Object>>) params.get("goodsList"); // 商品详情
        for (Map<String, Object> good : goodsList) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("sfdc001", good.get("sfdc001")); // 工单单号
            detail.put("sfdc002", good.get("sfdc002")); // 工单项次
            detail.put("sfdc003", good.get("sfdc003")); // 工单项序
            detail.put("sfdc007", good.get("sfdc007")); // 申请数量
            detail.put("sfdc012", good.get("sfdc012")); // 仓库
            detail.put("sfdc013", good.get("sfdc013")); // 储位
            detail.put("sfdc014", good.get("sfdc014")); // 批号
            detail.put("sfdc015", good.get("sfdc015")); // 理由码
            detail.put("sfdc016", good.get("sfdc016")); // 库存管理特征
            detail.put("source_seq", good.get("source_seq")); // MES项次
            details.add(detail);
        }

        // 构建master
        Map<String, Object> master = new HashMap<>();
        master.put("sfdadocno", params.get("sfdadocno")); // 单别
        master.put("sfdadocdt", params.get("sfdadocdt")); // 单据日期
        master.put("sfda001", params.get("sfda001")); // 过账日期
        master.put("sfda002", params.get("sfda002")); // 发退料类别
        master.put("source_no", params.get("source_no")); // MES单号
        master.put("detail", details); // 将details放入master

        // 构建parameter
        Map<String, Object> parameter = new HashMap<>();
        List<Map<String, Object>> masterList = new ArrayList<>();
        masterList.add(master);
        parameter.put("master", masterList);

        // 构建payload
        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> stdData = new HashMap<>();
        stdData.put("parameter", parameter);
        payload.put("std_data", stdData);

        request.put("payload", payload);
        System.out.println(JSONObject.toJSONString(request));
        String result = HttpUtils.doPost("http://192.168.127.7/wstopprd/ws/r/awsp920", JSONObject.toJSONString(request));

        // 记录操作日志
        InterfaceLogCreateReqVO log = new InterfaceLogCreateReqVO();
        log.setInterfaceName("工单领/退料单生成接口");
        log.setReceiver("ERP");
        log.setRequester("MES");
        log.setRequestType("POST");
        log.setRequestMap(JSONObject.toJSONString(request));
        log.setResultMap(result);
        interfaceLogService.createInterfaceLog(log);
        return result;
    }

    /**
     * 完工入库单生成接口
     */
    public String workOrderFinishCreate(Map<String, Object> params) {
        Map<String, Object> request = createBaseRequest();

        // 构建details
        List<Map<String, Object>> details = new ArrayList<>();
        List<Map<String, Object>> goodsList = (List<Map<String, Object>>) params.get("goodsList"); // 商品详情
        for (Map<String, Object> good : goodsList) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("sfeb001", good.get("sfeb001")); // 工单单号
            detail.put("sfeb003", good.get("sfeb003")); // 入库类型
            detail.put("sfeb004", good.get("sfeb004")); // 料号
            detail.put("sfeb005", good.get("sfeb005")); // 产品特征
            detail.put("sfeb008", good.get("sfeb008")); // 申请数量
            detail.put("sfeb013", good.get("sfeb013")); // 库位
            detail.put("sfeb014", good.get("sfeb014")); // 储位
            detail.put("sfeb015", good.get("sfeb015")); // 批号
            detail.put("source_seq", good.get("source_seq")); // MES项次
            details.add(detail);
        }

        // 构建master
        Map<String, Object> master = new HashMap<>();
        master.put("source_no", params.get("source_no")); // MES单号
        master.put("sfeadocno", params.get("sfeadocno")); // 单别
        master.put("sfeadocdt", params.get("sfeadocdt")); // 单据日期
        master.put("sfea001", params.get("sfea001")); // 过账日期
        master.put("sfea002", params.get("sfea002")); // 申请人员
        master.put("detail", details); // 将details放入master

        // 构建parameter
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("master", Collections.singletonList(master)); // 使用master

        // 构建payload
        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> stdData = new HashMap<>();
        stdData.put("parameter", parameter);
        payload.put("std_data", stdData);

        request.put("payload", payload);
        System.out.println(JSONObject.toJSONString(request));
        String result = HttpUtils.doPost("http://192.168.127.7/wstopprd/ws/r/awsp920", JSONObject.toJSONString(request));

        // 记录操作日志
        InterfaceLogCreateReqVO log = new InterfaceLogCreateReqVO();
        log.setInterfaceName("完工入库单生成接口");
        log.setReceiver("ERP");
        log.setRequester("MES");
        log.setRequestType("POST");
        log.setRequestMap(JSONObject.toJSONString(request));
        log.setResultMap(result);
        interfaceLogService.createInterfaceLog(log);
        return result;
    }


    private Map<String, Object> createBaseRequest() {
        Map<String, Object> request = new HashMap<>();
        request.put("key", "f5458f5c0f9022db743a7c0710145903");
        request.put("type", "sync");

        Map<String, Object> hostInfo = new HashMap<>();
        hostInfo.put("prod", "MES");
        hostInfo.put("ip", getHostIp()); // 获取实际的IP地址
        hostInfo.put("lang", "zh_CN");
        hostInfo.put("acct", "tiptop");
        hostInfo.put("timestamp", String.valueOf(System.currentTimeMillis()));
        request.put("host", hostInfo);

        Map<String, Object> serviceInfo = new HashMap<>();
        serviceInfo.put("prod", "T100");
        serviceInfo.put("name", "WorkOrderIssueCreate");
        serviceInfo.put("ip", "192.168.127.7");
        serviceInfo.put("id", "topprd"); // 或者 "toptst"，根据环境选择
        request.put("service", serviceInfo);

        Map<String, Object> dataKey = new HashMap<>();
        dataKey.put("EntId", "101"); // 根据文档要求修改
        dataKey.put("CompanyId", "BM2500"); // 根据文档要求修改
        request.put("datakey", dataKey);

        return request;
    }

    private String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "未知";
        }
    }
}