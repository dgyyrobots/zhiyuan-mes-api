package com.dofast.module.channel.controller.admin;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.controller.admin.order.vo.OrderPushReqVO;
import com.dofast.module.channel.controller.admin.order.vo.OrderReceiveVO;
import com.dofast.module.channel.dian3logisticspojo.logistics.Dian3LogisticsReq;
import com.dofast.module.channel.dian3logisticspojo.logistics.Dian3LogisticsRes;
import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillReq;
import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillRes;
import com.dofast.module.channel.serv.LogisticsServ;
import com.dofast.module.channel.serv.OrderServ;
import com.dofast.module.channel.serv.WayBillServ;
import com.dofast.module.channel.service.order.ChannelOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.CHANNEL_ORDER_GET;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.CHANNEL_ORDER_RECEIVE;
import static com.dofast.module.channel.enums.ErrorCodeConstants.DIAN3_SIGN_ERROR;
import static com.dofast.module.channel.enums.ErrorCodeConstants.DIAN3_SIGN_YAN_ERROR;

@Tag(name = "管理后台 - 点三科技")
@RestController
@RequestMapping("/dian3/order")
public class Dian3Controller {

    @Autowired
    private ChannelOrderService channelOrderService;

    @Autowired
    private OrderServ orderServ;

    @Autowired
    private LogisticsServ logisticsServ;

    @Autowired
    private WayBillServ wayBillServ;

    @Autowired
    Dian3 dian3;

    @PostMapping("/receive")
    @Operation(summary = "接受推送订单")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_RECEIVE)
    public com.dofast.module.channel.pojo.CommonResult<HashMap> receiveOrders (
            @Valid @RequestBody String content,
            HttpServletRequest http
    ) {
       List<String> list=new ArrayList<>();
        String sign = "";
        Map<String, String[]> parameterMap = http.getParameterMap();
        for (String key : parameterMap.keySet()) {
            String[] strings = parameterMap.get(key);
            if ("sign".equals(key)) {
                sign = strings[0];
                continue;
            }
            String st=key+strings[0];
            list.add(st);
        }
        String[] urlVar =list.toArray(new String[list.size()]);
        Arrays.sort(urlVar);
        HashMap params = new HashMap();
//        String content=JSON.toJSONString(pushReqVO);


//        try {
//            StringBuffer data = new StringBuffer();
//            String line = null;
//            BufferedReader reader = null;
//            reader = http.getReader();
//            while (null != (line = reader.readLine())) {
//                data.append(line);
//            }
//            content=data.toString();
//        }catch (Exception ex){
//
//        }


        String var = "";
        for (String string : urlVar) {
            var+=string;
        }
        var+=content;
        Boolean ok = dian3.init("ds.omni.erp.third.order.push", var, params).buildSign().checkSign(sign);
        ArrayList<String> refOidList = new ArrayList<>();
        if (ok) {
            List<OrderReceiveVO> orderReceiveVOS = new ArrayList<>();
            String contentTemp = JSON.parseObject(content).get("content").toString();
            orderReceiveVOS.add(JSON.parseObject(contentTemp, OrderReceiveVO.class));
            refOidList = orderServ.receiveOrders(orderReceiveVOS);
        }else {
            throw exception(DIAN3_SIGN_YAN_ERROR);
        }
        HashMap ret = new HashMap();
        ret.put("content", refOidList);
        return com.dofast.module.channel.pojo.CommonResult.succ(ret);
    }


    @GetMapping("/get")
    @Operation(summary = "获取订单")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public CommonResult getOrders(@RequestParam(value="shopCode", defaultValue = "") String shopCode,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime)

    {
        HashMap params = new HashMap();
        if (!shopCode.equals("")) {
            params.put("posCode", shopCode);
        }
        params.put("pageNo", 1);
        params.put("pageSize", 100);
        params.put("startTime", startTime);
        /*DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateStr = localDateTime.format(fmt);*/
        params.put("endTime", endTime);
        orderServ.getOrders(params);
        return success();
    }

    @PostMapping("/get-logistics-list")
    @Operation(summary = "获取物流列表")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public CommonResult<List<Dian3LogisticsRes>> getLogisticsList(@RequestBody @Valid Dian3LogisticsReq logisticsReq)
    {
        HashMap<String, Object> params = logisticsServ.toHashMap(logisticsReq);
        List<Dian3LogisticsRes> list = new ArrayList<>();
        list = logisticsServ.getLogistics(params, list);
        return success(list);
    }

    @PostMapping("/get-way-bill")
    @Operation(summary = "电子面单")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public CommonResult<List<Dian3WayBillRes>> getWayBill(@RequestBody @Valid Dian3WayBillReq dian3WayBillReq)
    {
        HashMap<String, Object> params = wayBillServ.toHashMap(dian3WayBillReq);
        List<Dian3WayBillRes> list = new ArrayList<>();
        list = wayBillServ.getWillBill(params);
        return success(list);
    }

    @PostMapping("/cancel-way-bill")
    @Operation(summary = "取消电子面单")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public CommonResult<Boolean> cancelWayBill(@RequestParam(value="waybillCode") String waybillCode)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("waybillCode", waybillCode);
        wayBillServ.cancelWillBill(params);
        return success(true);
    }

    @PostMapping("/cloud-print-info")
    @Operation(summary = "获取电子面单云打印信息")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET, userId = -100)
    public String getCloudPrintInfo(@RequestParam("waybillCodes") List<String> waybillCodes) {
        HashMap<String, List<String>> params = new HashMap<>();
        params.put("waybillCodes", waybillCodes);
        HashMap<String, HashMap<String, List<String>>> body = new HashMap<>();
        body.put("request", params);
        long timestamp = 123456789;
        String sign = "AAABBBCCC";
        String appKey = "123456";
//        dian3.init();
//        String appKey = dian3.appKey;
//        long timestamp =  System.currentTimeMillis() / 1000;
//        String timestampStr = String.valueOf(timestamp);
//        String sign = dian3.buildSign("omni.print.open.appOpenPrintData.getExpressList", timestampStr, body );
        String url =
                String.format("http://d3.diansan.com/app-web/open/router/rest.json?"+
                        "method=omni.print.open.appOpenPrintData.getExpressList"+
                        "&appKey=%s&timestamp=%d&sign=%s", appKey, timestamp, sign);
        return HttpUtil.post(url,
                JSONUtil.toJsonStr(body)
        );
    }
}
