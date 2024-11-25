package com.dofast.module.wms.api.MaterialStockApi;

import com.alibaba.excel.util.DateUtils;
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
public class MaterialStockERPAPI {


    @Resource
    private InterfaceLogService interfaceLogService;


    /**
     * 采购收货/入库/仓退单生成接口
     */
    public String purchaseDeliveryCreate(Map<String, Object> params) {
        Map<String, Object> request = createBaseRequest();

        String pmds000 = (String) params.get("pmds000"); // 单据类型 1：一般采购收货；2：无采购收货；3：采购收货入库单；6：一般采购入库；7：一般采购仓退；8：委外采购收货；12：委外采购入库；14：委外采购仓退；20：委外采购收货入库
        //List<Map<String, Object>> details = (List<Map<String, Object>>) params.get("details");
        // 后续数据根据需求补充参数信息
        List<Map<String, Object>> details = new ArrayList<>();
        Map<String, Object> detail = new HashMap<>();
        detail.put("pmdt001", ""); // 采购单号
        detail.put("pmdt002", ""); // 采购项次
        detail.put("pmdt003", ""); // 采购单号
        detail.put("pmdt004", ""); // 采购单号
        detail.put("pmdt027", "L421-2106090014"); // 收货单
        detail.put("pmdt028", "1"); // 收获项次
        detail.put("pmdt006", "DING00002"); // 料件
        detail.put("pmdt007", " "); // 特性
        detail.put("pmdt019", "PCS"); // 单位
        detail.put("pmdt020", "2000"); // 数量
        detail.put("pmdt025", "1"); // 紧急标识
        detail.put("pmdt026", "0"); // 检验标识
        detail.put("pmdt016", "0001"); // 仓库
        detail.put("pmdt017", ""); // 库位
        detail.put("pmdt018", "210609001"); // 批号
        detail.put("pmdt036", ""); // 单价
        detail.put("pmdt051", ""); // 理由码
        detail.put("source_seq", "1");
        //request.put("detail", detail);
        details.add(detail);

        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> stdData = new HashMap<>();
        Map<String, Object> parameter = new HashMap<>();

        // 根据 pmds000 的值动态构建 master 或 pmds_info
        if (Arrays.asList("1", "2", "8").contains(pmds000)) { // 收货单
            parameter.put("master", createMaster(pmds000, details));
        } else if (Arrays.asList("3", "6", "12", "20").contains(pmds000)) { // 入库单
            parameter.put("pmds_info", createPmdsInfo(pmds000, details));
        } else if (Arrays.asList("7", "14").contains(pmds000)) { // 仓退单
            parameter.put("master", createMaster(pmds000, details));
        }

        stdData.put("parameter", parameter);
        payload.put("std_data", stdData);
        request.put("payload", payload);
        String result = HttpUtils.doPost("http://192.168.127.7/wstopprd/ws/r/awsp920", JSONObject.toJSONString(request));
        // 记录操作日志
        InterfaceLogCreateReqVO log = new InterfaceLogCreateReqVO();
        log.setInterfaceName("采购入库接口");
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
        hostInfo.put("ip", "MES IP地址"); // 替换为实际的IP地址
        hostInfo.put("lang", "zh_CN");
        hostInfo.put("acct", "tiptop");
        hostInfo.put("timestamp", String.valueOf(System.currentTimeMillis()));
        request.put("host", hostInfo);

        Map<String, Object> serviceInfo = new HashMap<>();
        serviceInfo.put("prod", "T100");
        serviceInfo.put("name", "PurchaseDeliveryCreate");
        serviceInfo.put("ip", "192.168.127.7");
        serviceInfo.put("id", "topprd"); // 或者 "toptst"，根据环境选择
        request.put("service", serviceInfo);

        Map<String, Object> dataKey = new HashMap<>();
        dataKey.put("EntId", "70");
        dataKey.put("CompanyId", "LSJL");
        request.put("datakey", dataKey);

        return request;
    }

    private Map<String, Object> createMaster(String pmds000, List<Map<String, Object>> details) {
        Map<String, Object> master = new HashMap<>();
        master.put("source_no", "20210915"); // 后续换成单号
        master.put("pmdsdocno", "431");
        master.put("pmdsdocdt", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        master.put("pmds000", pmds000);
        master.put("pmds006", "L421-2106090014");
        master.put("pmds002", "00000");
        master.put("pmds007", "LS00001");
        master.put("detail", details);
        return master;
    }

    /**
     * 用于入库单
     *
     * @param pmds000
     * @param details
     * @return
     */
    private Map<String, Object> createPmdsInfo(String pmds000, List<Map<String, Object>> details) {
        Map<String, Object> pmdsInfo = new HashMap<>();
        pmdsInfo.put("pmdsdocno", "431");
        pmdsInfo.put("pmdsdocdt", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pmdsInfo.put("pmds000", pmds000);
        pmdsInfo.put("pmds006", "L421-2106090014");
        pmdsInfo.put("pmds002", "00000");
        pmdsInfo.put("pmds007", "LS00001");
        pmdsInfo.put("pmdt_info", details);
        return pmdsInfo;
    }


}
