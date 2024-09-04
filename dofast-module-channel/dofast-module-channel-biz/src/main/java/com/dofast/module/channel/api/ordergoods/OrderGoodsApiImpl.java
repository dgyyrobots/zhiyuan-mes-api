package com.dofast.module.channel.api.ordergoods;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dofast.module.channel.api.ordergoods.dto.OrderGoodsGetDTO;
import com.dofast.module.channel.convert.ordergoods.OrderGoodsConvert;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import com.dofast.module.channel.dal.mysql.ordergoods.OrderGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;


@Service
@Validated
public class OrderGoodsApiImpl implements OrderGoodsApi{

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    public int updateAddressIdByRefOid(String refOid, Integer customerId) {
        return orderGoodsMapper.update(null, new LambdaUpdateWrapper<OrderGoodsDO>()
                .eq(OrderGoodsDO::getRefOid, refOid)
                .set(OrderGoodsDO::getCustomerId, customerId));
    }

    @Override
    public List<OrderGoodsGetDTO> getOrderGoodsByRefOid(String refOid) {
        List<OrderGoodsDO> list = orderGoodsMapper.selectListByRefOid(refOid);
        return OrderGoodsConvert.INSTANCE.convertDTOList(list);
    }
}
