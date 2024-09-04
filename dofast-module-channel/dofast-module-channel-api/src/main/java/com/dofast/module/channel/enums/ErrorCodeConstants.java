package com.dofast.module.channel.enums;

import com.dofast.framework.common.exception.ErrorCode;


public interface ErrorCodeConstants {
    //channel-shop
    ErrorCode SHOP_NOT_EXISTS = new ErrorCode(5258001, "店铺授权不存在");

    //channel-order
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(5242001, "主订单不存在");
    ErrorCode ORDER_ID_NULL = new ErrorCode(5242002, "订单ID不能为NULL");
    ErrorCode USER_ID_AND_INFORMATION_NULL = new ErrorCode(5242003, "用户ID和用户的手机号不能同时为空");
    ErrorCode RECEIVER_NAME_NULL = new ErrorCode(5242004, "收货人姓名不能为空");
    ErrorCode RECEIVER_MOBILE_NULL = new ErrorCode(5242005, "收货人手机不能为空");
    ErrorCode RECEIVER_AREA_ID_NULL = new ErrorCode(5242006, "收货人地区编号不能为空");
    ErrorCode RECEIVER_POST_CODE_NULL = new ErrorCode(5242007, "收件人邮编不能为空");
    ErrorCode RECEIVER_DETAIL_ADDRESS_NULL = new ErrorCode(5242008, "收件人详细地址不能为空");
    ErrorCode INFORMATION_NULL = new ErrorCode(5242009, "不能传入空信息");
    ErrorCode USER_PHONE_EXIST = new ErrorCode(5242010, "用户已存在，请换种方式添加");
    ErrorCode ORDER_HAS_TRANSLATED = new ErrorCode(5242011, "渠道订单已转化为商城订单");
    ErrorCode USER_NOT_EXIST = new ErrorCode(5242012, "用户不存在");
    ErrorCode TRADE_ORDER_INSERT_FAIL = new ErrorCode(5242013, "渠道订单转商城订单失败");
    ErrorCode SYSTEM_USER_ID_NOT_EXIST = new ErrorCode(5242013, "业务员ID为空");
    ErrorCode SYSTEM_USER_NAME_NOT_EXIST = new ErrorCode(5242014, "业务员姓名为空");


    //channel-order-goods
    ErrorCode ORDER_GOODS_NOT_EXISTS = new ErrorCode(5242101, "子订单不存在");

    // remark
    ErrorCode REMARK_NOT_EXISTS = new ErrorCode(5242201, "订单备注不存在");

    //sales
    ErrorCode SALES_ORDER_NOT_EXISTS = new ErrorCode(5242301, "销售订单不存在");

    //channel-address
    ErrorCode ADDRESS_NOT_EXISTS = new ErrorCode(52114001 , "交易客户不存在");

    //dian3-ret
    ErrorCode DIAN3_SIGN_ERROR = new ErrorCode(3049011, "签名错误");

    ErrorCode DIAN3_SIGN_YAN_ERROR = new ErrorCode(3049012, "验签失败");

    ErrorCode BANKCARD_IS_NOT_EXIES=new ErrorCode(3049012,"银行卡号不存在");

}