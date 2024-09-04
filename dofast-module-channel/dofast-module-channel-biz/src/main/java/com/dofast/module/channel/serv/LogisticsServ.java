package com.dofast.module.channel.serv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.api.order.dto.OrderGetDTO;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.controller.admin.order.vo.OrderCreateReqVO;
import com.dofast.module.channel.controller.admin.order.vo.OrderExportReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dian3logisticspojo.logistics.Dian3LogisticsReq;
import com.dofast.module.channel.dian3logisticspojo.logistics.Dian3LogisticsRes;
import com.dofast.module.event.event.ChannelOrderEvent;
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
public class LogisticsServ {
    @Autowired
    Dian3 dian3;

    @Autowired
    ApplicationEventPublisher publisher;

    public List<Dian3LogisticsRes> processContent(JSONArray orders) {
        List<Dian3LogisticsRes> list = new ArrayList<>();
        orders.forEach( o -> {
            Dian3LogisticsRes dian3LogisticsRes = processOne(o);
            list.add(dian3LogisticsRes);
        });
        return list;
    }

    @Transactional
    public Dian3LogisticsRes processOne(Object o) {
        JSONObject ox = (JSONObject) o;
        Dian3LogisticsRes logisticsRes = JSONObject.parseObject(JSONUtil.toJsonStr(o), Dian3LogisticsRes.class);
        Dian3LogisticsEvent event  = new Dian3LogisticsEvent(this, "DIAN3_LOGISTICS_QUERY_GET", logisticsRes);
        publisher.publishEvent(event);
        return logisticsRes;
    }

    public List<Dian3LogisticsRes> getLogistics(HashMap params, List<Dian3LogisticsRes> list) {
        String ret = dian3.init("ds.omni.erp.logistics.partner.query", params)
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
            JSONArray logistics = data.getJSONArray("content");
            List<Dian3LogisticsRes> logisticsResList = processContent(logistics);
            list.addAll(logisticsResList);
            if (hasNext) {
                params.put("pageNo", Integer.valueOf(params.get("pageNo").toString()) + 1);
                this.getLogistics(params, list);
            }
        }
        return list;
    }

    public HashMap<String, Object> toHashMap(Dian3LogisticsReq dian3LogisticsReq){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", dian3LogisticsReq.getName());
        params.put("code", dian3LogisticsReq.getCode());
        params.put("status", dian3LogisticsReq.getStatus());
        params.put("pageNo", dian3LogisticsReq.getPageNo());
        params.put("pageSize", dian3LogisticsReq.getPageSize());
        params.put("posCode", dian3LogisticsReq.getPosCode());
        return params;
    }
}
