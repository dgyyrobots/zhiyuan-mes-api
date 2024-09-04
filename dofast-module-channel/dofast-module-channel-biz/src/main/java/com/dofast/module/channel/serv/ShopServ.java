package com.dofast.module.channel.serv;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.module.channel.api.shop.dto.ShopGetDTO;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.controller.admin.shop.vo.*;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import com.dofast.module.channel.service.order.ChannelOrderService;
import com.dofast.module.channel.service.ordergoods.OrderGoodsService;
import com.dofast.module.channel.service.shop.ShopService;
import com.dofast.module.event.event.ChannelShopEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;


@Component
public class ShopServ {

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

    public void processContent(JSONArray shops) {
        shops.forEach( o -> {
            processOne(o);
        });
    }

    //解析Content信息，对店铺的内容进行更新
    @Transactional
    public void processOne(Object o) {
        JSONObject jsonObj = (JSONObject) o;
        ShopGetDTO shopGetDTO = new ShopGetDTO();
        //数据初始化及处理
        int id = 0;
        String code = "";
        String name = "";
        String title = "";
        String platform = "";
        String platformType = "";
        String type = "";
        long createTime = 0;
        String status = "";
        String refUserId = "";
        String refUserNick = "";
        String refAuthStatus = "";
        String refAuthExpiresTime = "";
        String nick = "";
        String contactName = "";
        String mobile = "";
        String phone = "";
        String remark = "";
        int areaId = 0;
        String country = "";
        String province = "";
        String city = "";
        String district = "";
        String detailAddress = "";

        if (jsonObj != null) {
            if (jsonObj.containsKey("id")) {
                id = jsonObj.getInteger("id");
            }
            if (jsonObj.containsKey("code")) {
                code = jsonObj.getString("code");
            }
            if (jsonObj.containsKey("name")) {
                name = jsonObj.getString("name");
            }
            if (jsonObj.containsKey("title")) {
                title = jsonObj.getString("title");
            }
            if (jsonObj.containsKey("platform")) {
                platform = jsonObj.getString("platform");
            }
            if (jsonObj.containsKey("platformType")) {
                platformType = jsonObj.getString("platformType");
            }
            if (jsonObj.containsKey("type")) {
                type = jsonObj.getString("type");
            }
            if (jsonObj.containsKey("createTime")) {
                createTime = jsonObj.getLong("createTime");
            }
            if (jsonObj.containsKey("status")) {
                status = jsonObj.getString("status");
            }
            if (jsonObj.containsKey("refUserId")) {
                refUserId = jsonObj.getString("refUserId");
            }
            if (jsonObj.containsKey("refUserNick")) {
                refUserNick = jsonObj.getString("refUserNick");
            }
            if (jsonObj.containsKey("refAuthStatus")) {
                refAuthStatus = jsonObj.getString("refAuthStatus");
            }
            if (jsonObj.containsKey("refAuthExpiresTime")) {
                refAuthExpiresTime = jsonObj.getString("refAuthExpiresTime");
            }
            if (jsonObj.containsKey("contact")) {
                JSONObject contactObj = jsonObj.getJSONObject("contact");
                if (contactObj != null) {
                    if (contactObj.containsKey("nick")) {
                        nick = contactObj.getString("nick");
                    }
                    if (contactObj.containsKey("name")) {
                        contactName = contactObj.getString("name");
                    }
                    if (contactObj.containsKey("mobile")) {
                        mobile = contactObj.getString("mobile");
                    }
                    if (contactObj.containsKey("phone")) {
                        phone = contactObj.getString("phone");
                    }
                    if (contactObj.containsKey("remark")) {
                        remark = contactObj.getString("remark");
                    }
                    if (contactObj.containsKey("address")) {
                        JSONObject addressObj = contactObj.getJSONObject("address");
                        if (addressObj != null) {
                            if (addressObj.containsKey("areaId")) {
                                areaId = addressObj.getInteger("areaId");
                            }
                            if (addressObj.containsKey("country")) {
                                country = addressObj.getString("country");
                            }
                            if (addressObj.containsKey("province")) {
                                province = addressObj.getString("province");
                            }
                            if (addressObj.containsKey("city")) {
                                city = addressObj.getString("city");
                            }
                            if (addressObj.containsKey("district")) {
                                district = addressObj.getString("district");
                            }
                            if (addressObj.containsKey("detailAddress")) {
                                detailAddress = addressObj.getString("detailAddress");
                            }
                        }
                    }
                }
            }
        }
        Integer intStatus;
        switch (status){
            case "VALID":
                intStatus = 0;
                break;
            case "DELETED":
                intStatus = 2;
                break;
            default:
                intStatus = 1;
                break;
        }
        if (checkExist(code)){
            ShopDO shopDO  = shopService.getShopByShopCode(code);
            //数据库中存在该店铺
            ShopUpdateReqVO shopUpdateReqVO = new ShopUpdateReqVO();
            shopUpdateReqVO.setId(shopDO.getId());
            shopUpdateReqVO.setDisplayName(name);
            shopUpdateReqVO.setName(title);
            shopUpdateReqVO.setShopNick(nick);
            shopUpdateReqVO.setShopUrl(detailAddress);
            shopUpdateReqVO.setShopId("");
            shopUpdateReqVO.setShopCode(code);
            shopUpdateReqVO.setChannel(platform);
            shopUpdateReqVO.setStatus(intStatus);
            shopUpdateReqVO.setLastTime(LocalDateTime.now());
            shopUpdateReqVO.setRemark(remark);
            shopUpdateReqVO.setSortCode(20);
            shopService.updateShop(shopUpdateReqVO);
            shopGetDTO = BeanUtil.toBean(shopUpdateReqVO, ShopGetDTO.class);
        }else {
            //数据库中不存在
            ShopCreateReqVO shopCreateReqVO = new ShopCreateReqVO();
            shopCreateReqVO.setShopId("");
            shopCreateReqVO.setShopCode(code);
            shopCreateReqVO.setShopNick(nick);
            shopCreateReqVO.setShopUrl(detailAddress);
            shopCreateReqVO.setChannel(platform);
            shopCreateReqVO.setSortCode(20);
            shopCreateReqVO.setDisplayName(name);
            shopCreateReqVO.setLastTime(LocalDateTime.now());
            shopCreateReqVO.setName(title);
            shopCreateReqVO.setRemark(remark);
            shopCreateReqVO.setStatus(intStatus);
            shopService.createShop(shopCreateReqVO);
            shopGetDTO = BeanUtil.toBean(shopCreateReqVO, ShopGetDTO.class);
        }
        ChannelShopEvent event  = new ChannelShopEvent(this, "CHANNEL_SHOP_UPDATE_GET", shopGetDTO);
        publisher.publishEvent(event);
    }

    @Transactional
    public void syncShop(HashMap params) {
        String ret = dian3.init("ds.omni.erp.pos.query", params)
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
            JSONArray shops = data.getJSONArray("content");
            //获取到点3传回的信息对其进行解析
            if(shops == null){
                throw exception(new ErrorCode(3049006, "没有店铺信息"));
            }else {
                processContent(shops);
            }
            if (hasNext) {
                params.put("pageNo", Integer.valueOf(params.get("pageNo").toString()) + 1);
                this.syncShop(params);
            }
        }
    }

    public boolean checkExist(String shopCode) {
        ShopDO shopDO  = shopService.getShopByShopCode(shopCode);
        //查不到
        if (shopDO == null) return false;
        //查得到
        return true;
    }

}
