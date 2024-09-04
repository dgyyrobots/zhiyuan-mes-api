package com.dofast.module.pay.convert.app;



import cn.hutool.core.bean.BeanUtil;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.module.pay.controller.admin.app.vo.PayAppCreateReqVO;
import com.dofast.module.pay.controller.admin.app.vo.PayAppPageItemRespVO;
import com.dofast.module.pay.controller.admin.app.vo.PayAppRespVO;
import com.dofast.module.pay.controller.admin.app.vo.PayAppUpdateReqVO;

 


import com.dofast.module.pay.controller.admin.merchant.vo.app.PayAppExcelVO;
import com.dofast.module.pay.dal.dataobject.app.PayAppDO;
import com.dofast.module.pay.dal.dataobject.channel.PayChannelDO;
import com.dofast.module.pay.dal.dataobject.merchant.PayMerchantDO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import java.beans.Beans;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * 支付应用信息 Convert
 *
 * @author 芋艿
 */
@Mapper
public interface PayAppConvert {

    PayAppConvert INSTANCE = Mappers.getMapper(PayAppConvert.class);

    PayAppPageItemRespVO pageConvert (PayAppDO bean);

    PayAppDO convert(PayAppCreateReqVO bean);



     PayAppRespVO convert(PayAppDO bean);


    PayAppDO convert(PayAppUpdateReqVO bean);





    List<PayAppRespVO> convertList(List<PayAppDO> list);

    PageResult<PayAppPageItemRespVO> convertPage(PageResult<PayAppDO> page);


 





    default PageResult<PayAppPageItemRespVO> convertPage(PageResult<PayAppDO> pageResult, List<PayChannelDO> channels) {
        PageResult<PayAppPageItemRespVO> voPageResult = convertPage(pageResult);
        // 处理 channel 关系
        Map<Long, Set<String>> appIdChannelMap = CollectionUtils.convertMultiMap2(channels, PayChannelDO::getAppId, PayChannelDO::getCode);
        voPageResult.getList().forEach(app -> app.setChannelCodes(appIdChannelMap.get(app.getId())));
        return voPageResult;
    }


    List<PayAppRespVO> convertList1(List<PayAppDO> list);

    default PayAppPageItemRespVO.PayMerchant convert(PayMerchantDO bean){
        PayAppPageItemRespVO.PayMerchant bean1 = BeanUtil.toBean(bean, PayAppPageItemRespVO.PayMerchant.class);
        bean1.setMerchantId(bean.getId());
        return bean1;
    }

    List<PayAppExcelVO> convertList30(List<PayAppDO> list);

    List<PayAppRespVO> convertList25(List<PayAppDO> list);

    PayAppDO convert(com.dofast.module.pay.controller.admin.merchant.vo.app.PayAppCreateReqVO bean);


}

