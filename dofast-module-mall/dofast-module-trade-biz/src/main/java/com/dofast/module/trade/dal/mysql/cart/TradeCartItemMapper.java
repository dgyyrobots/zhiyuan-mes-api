package com.dofast.module.trade.dal.mysql.cart;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.dal.dataobject.cart.CartDO;
import com.dofast.module.trade.dal.dataobject.cart.TradeCartItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper
public interface TradeCartItemMapper extends BaseMapperX<TradeCartItemDO> {

    default TradeCartItemDO selectByUserIdAndSkuId(Long userId, Long skuId) {
        return selectOne(TradeCartItemDO::getUserId, userId,
                TradeCartItemDO::getSkuId, skuId);
    }

    default List<TradeCartItemDO> selectListByUserIdAndSkuIds(Long userId, Collection<Long> skuIds) {
        return selectList(new LambdaQueryWrapper<TradeCartItemDO>().eq(TradeCartItemDO::getUserId, userId)
                .in(TradeCartItemDO::getSkuId, skuIds));
    }

   default void updateByIds(Collection<Long> ids, TradeCartItemDO updateObject) {
       update(updateObject, new LambdaQueryWrapper<TradeCartItemDO>().in(TradeCartItemDO::getId, ids));
   }

    default Integer selectSumByUserId(Long userId) {
        // SQL sum 查询
        /*List<Map<String, Object>> result = selectMaps(new QueryWrapper<TradeCartItemDO>()
                .select("SUM(count) AS sumCount")
                .eq("user_id", userId));
        // 获得数量
        return CollUtil.isNotEmpty(result) ? MapUtil.getInt(result.get(0), "sumCount") : 0;*/
        return Math.toIntExact(selectCount(new LambdaQueryWrapperX<TradeCartItemDO>()
                .eq(TradeCartItemDO::getUserId, userId)));
    }

    default List<TradeCartItemDO> selectListByUserId(Long userId, Boolean selected) {
        return selectList(new LambdaQueryWrapperX<TradeCartItemDO>().eq(TradeCartItemDO::getUserId, userId)
                .eqIfPresent(TradeCartItemDO::getSelected, selected));
    }


}
