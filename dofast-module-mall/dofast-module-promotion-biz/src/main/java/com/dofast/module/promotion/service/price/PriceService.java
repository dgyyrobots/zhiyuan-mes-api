package com.dofast.module.promotion.service.price;

import com.dofast.module.promotion.api.price.dto.CouponMeetRespDTO;
import com.dofast.module.promotion.api.price.dto.PriceCalculateReqDTO;

import com.dofast.module.promotion.api.price.dto.PriceCalculateRespDTO;


import java.util.List;

/**
 * 价格计算 Service 接口
 *
 * @author 芋道源码
 */
public interface PriceService {

    /**
     * 获得优惠劵的匹配信息列表
     *
     * @param calculateReqDTO 价格请求
     * @return 价格响应
     */
    List<CouponMeetRespDTO> getMeetCouponList(PriceCalculateReqDTO calculateReqDTO);





    /**
     * 计算商品的价格
     *
     * @param calculateReqDTO 价格请求
     * @return 价格响应
     */
    PriceCalculateRespDTO calculatePrice(PriceCalculateReqDTO calculateReqDTO);

}
