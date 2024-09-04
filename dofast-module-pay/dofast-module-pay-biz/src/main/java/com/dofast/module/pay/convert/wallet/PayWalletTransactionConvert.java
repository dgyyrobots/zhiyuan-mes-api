package com.dofast.module.pay.convert.wallet;

import com.dofast.framework.common.pojo.PageResult;


import com.dofast.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;



import com.dofast.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import com.dofast.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<AppPayWalletTransactionRespVO> convertPage(PageResult<PayWalletTransactionDO> page);



    PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page);




    PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean);

}
