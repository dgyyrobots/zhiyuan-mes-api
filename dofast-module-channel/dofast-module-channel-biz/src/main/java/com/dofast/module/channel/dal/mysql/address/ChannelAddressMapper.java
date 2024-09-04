package com.dofast.module.channel.dal.mysql.address;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.channel.controller.admin.address.vo.*;

/**
 * 交易客户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ChannelAddressMapper extends BaseMapperX<ChannelAddressDO> {

    default PageResult<ChannelAddressDO> selectPage(AddressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChannelAddressDO>()
                .eqIfPresent(ChannelAddressDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(ChannelAddressDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(ChannelAddressDO::getMobile, reqVO.getMobile())
                .eqIfPresent(ChannelAddressDO::getRefType, reqVO.getRefType())
                .eqIfPresent(ChannelAddressDO::getReceiverCountry, reqVO.getReceiverCountry())
                .eqIfPresent(ChannelAddressDO::getReceiverState, reqVO.getReceiverState())
                .eqIfPresent(ChannelAddressDO::getReceiverCity, reqVO.getReceiverCity())
                .eqIfPresent(ChannelAddressDO::getReceiverDistrict, reqVO.getReceiverDistrict())
                .eqIfPresent(ChannelAddressDO::getReceiverTown, reqVO.getReceiverTown())
                .eqIfPresent(ChannelAddressDO::getReceiverId, reqVO.getReceiverId())
                .eqIfPresent(ChannelAddressDO::getOpenBuyerNick, reqVO.getOpenBuyerNick())
                .eqIfPresent(ChannelAddressDO::getOpenBuyerId, reqVO.getOpenBuyerId())
                .betweenIfPresent(ChannelAddressDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ChannelAddressDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ChannelAddressDO::getAddressId, reqVO.getAddressId())
                .eqIfPresent(ChannelAddressDO::getUserId, reqVO.getUserId())
                .orderByDesc(ChannelAddressDO::getId));
    }

    default List<ChannelAddressDO> selectList(AddressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ChannelAddressDO>()
                .eqIfPresent(ChannelAddressDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(ChannelAddressDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(ChannelAddressDO::getMobile, reqVO.getMobile())
                .eqIfPresent(ChannelAddressDO::getRefType, reqVO.getRefType())
                .eqIfPresent(ChannelAddressDO::getReceiverCountry, reqVO.getReceiverCountry())
                .eqIfPresent(ChannelAddressDO::getReceiverState, reqVO.getReceiverState())
                .eqIfPresent(ChannelAddressDO::getReceiverCity, reqVO.getReceiverCity())
                .eqIfPresent(ChannelAddressDO::getReceiverDistrict, reqVO.getReceiverDistrict())
                .eqIfPresent(ChannelAddressDO::getReceiverTown, reqVO.getReceiverTown())
                .eqIfPresent(ChannelAddressDO::getReceiverId, reqVO.getReceiverId())
                .eqIfPresent(ChannelAddressDO::getOpenBuyerNick, reqVO.getOpenBuyerNick())
                .eqIfPresent(ChannelAddressDO::getOpenBuyerId, reqVO.getOpenBuyerId())
                .betweenIfPresent(ChannelAddressDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ChannelAddressDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ChannelAddressDO::getAddressId, reqVO.getAddressId())
                .eqIfPresent(ChannelAddressDO::getUserId, reqVO.getUserId())
                .orderByDesc(ChannelAddressDO::getId));
    }

}
