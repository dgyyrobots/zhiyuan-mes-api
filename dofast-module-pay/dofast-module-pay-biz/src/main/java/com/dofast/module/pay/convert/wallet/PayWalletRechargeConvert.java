package com.dofast.module.pay.convert.wallet;



import com.dofast.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateReqVO;



import com.dofast.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateReqVO;

import com.dofast.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateRespVO;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



/**
 * @author jason
 */


/**
 * @author jason
 */

@Mapper
public interface PayWalletRechargeConvert {

    PayWalletRechargeConvert INSTANCE = Mappers.getMapper(PayWalletRechargeConvert.class);


    @Mapping(target = "totalPrice", expression = "java( payPrice + bonusPrice)")
    PayWalletRechargeDO convert(Long walletId, Integer payPrice, Integer bonusPrice, Long packageId);



    @Mapping(target = "totalPrice", expression = "java(vo.getPayPrice() + vo.getBonusPrice() )")
    PayWalletRechargeDO convert(Long walletId, AppPayWalletRechargeCreateReqVO vo);


    AppPayWalletRechargeCreateRespVO convert(PayWalletRechargeDO bean);

}
