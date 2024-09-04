package com.dofast.module.channel.api.address;


import com.dofast.module.channel.api.address.dto.AddressGetDTO;
import com.dofast.module.channel.api.address.dto.AddressReceiveDTO;
import com.dofast.module.channel.controller.admin.address.vo.AddressCreateReqVO;
import com.dofast.module.channel.controller.admin.address.vo.AddressExportReqVO;
import com.dofast.module.channel.convert.address.AddressConvert;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;
import com.dofast.module.channel.service.address.ChannelAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


/**
 * 渠道交易客户的 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ChannelAddressApiImpl implements AddressApi {

    @Autowired
    ChannelAddressService channelAddressService;

    @Override
    public Integer createAddress(AddressGetDTO customerGetDTO) {
        AddressCreateReqVO addressCreateReqVO = AddressConvert.INSTANCE.convert(customerGetDTO);
        AddressExportReqVO addressExportReqVO = new AddressExportReqVO();
        addressExportReqVO.setRefType(addressCreateReqVO.getRefType());
        addressExportReqVO.setOpenBuyerId(addressCreateReqVO.getOpenBuyerId());

        ChannelAddressDO channelAddressDO = channelAddressService.getAddress("ref_type", addressCreateReqVO.getRefType(), "open_buyer_id", addressCreateReqVO.getOpenBuyerId() );
        if (channelAddressDO != null) return channelAddressDO.getId();
        return channelAddressService.createAddress(addressCreateReqVO);
    }

    @Override
    public Integer createAddress(AddressReceiveDTO customerReceiveDTO) {
        AddressCreateReqVO addressCreateReqVO = AddressConvert.INSTANCE.convert(customerReceiveDTO);
        AddressExportReqVO addressExportReqVO = new AddressExportReqVO();
        addressExportReqVO.setRefType(addressCreateReqVO.getRefType());
        addressExportReqVO.setOpenBuyerId(addressCreateReqVO.getOpenBuyerId());

        ChannelAddressDO channelAddressDO = channelAddressService.getAddress("ref_type", addressCreateReqVO.getRefType(), "open_buyer_id", addressCreateReqVO.getOpenBuyerId() );
        if (channelAddressDO != null) return channelAddressDO.getId();
        return channelAddressService.createAddress(addressCreateReqVO);
    }
}
