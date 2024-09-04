package com.dofast.module.channel.convert.address;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.api.address.dto.AddressGetDTO;
import com.dofast.module.channel.api.address.dto.AddressReceiveDTO;
import com.dofast.module.channel.controller.admin.address.vo.*;
import com.dofast.module.channel.controller.admin.address.vo.AddressCreateReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 交易客户 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AddressConvert {

    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

    ChannelAddressDO convert(AddressCreateReqVO bean);

    ChannelAddressDO convert(AddressUpdateReqVO bean);

    AddressRespVO convert(ChannelAddressDO bean);

    List<AddressRespVO> convertList(List<ChannelAddressDO> list);

    PageResult<AddressRespVO> convertPage(PageResult<ChannelAddressDO> page);

    List<AddressExcelVO> convertList02(List<ChannelAddressDO> list);



    AddressCreateReqVO convert(AddressReceiveDTO bean);

    AddressCreateReqVO convert(AddressGetDTO bean);
}
//6666666
