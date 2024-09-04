package com.dofast.module.pay.convert.channel;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import com.dofast.module.pay.controller.admin.channel.vo.PayChannelRespVO;
import com.dofast.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;


import com.dofast.module.pay.controller.admin.merchant.vo.channel.PayChannelExcelVO;


import com.dofast.module.pay.controller.admin.merchant.vo.channel.PayChannelExcelVO;

import com.dofast.module.pay.dal.dataobject.channel.PayChannelDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



import java.util.List;



import java.util.List;


@Mapper
public interface PayChannelConvert {

    PayChannelConvert INSTANCE = Mappers.getMapper(PayChannelConvert.class);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelCreateReqVO bean);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelUpdateReqVO bean);

    @Mapping(target = "config",expression = "java(com.dofast.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")
    PayChannelRespVO convert(PayChannelDO bean);

    PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page);





    @Mapping(target = "config",expression = "java(com.dofast.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")

    List<PayChannelRespVO> convertList(List<PayChannelDO> list);



    PageResult<PayChannelRespVO> convertPage11(PageResult<PayChannelDO> page);
    List<PayChannelExcelVO> convertList02(List<PayChannelDO> list);


}
