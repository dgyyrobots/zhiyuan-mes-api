package com.dofast.module.channel.serv;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.dian3logisticspojo.stdTpl.Dian3StdTplReq;
import com.dofast.module.channel.dian3logisticspojo.stdTpl.Dian3StdTplRes;
import com.dofast.module.event.event.Dian3LogisticsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

@Component
public class StdTplServ {
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

    public void getStdTpl(HashMap params) {
        String ret = dian3.init("ds.omni.erp.print.third.stdTpl.query", params)
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
            JSONArray stdTpls = data.getJSONArray("content");
            processContent(stdTpls);
            if (hasNext) {
                params.put("pageNo", Integer.valueOf(params.get("pageNo").toString()) + 1);
                this.getStdTpl(params);
            }
        }
    }

    private HashMap<String, Object> toHashMap(Dian3StdTplReq dian3StdTplReq){
        HashMap<String, Object> map = new HashMap<>();
        map.put("templateType", dian3StdTplReq.getTemplateType());
        map.put("refPlatformType", dian3StdTplReq.getRefPlatformType());
        map.put("pageNo", dian3StdTplReq.getPageNo());
        map.put("pageSize", dian3StdTplReq.getPageSize());
        return map;
    }
}
