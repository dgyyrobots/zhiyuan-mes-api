package com.dofast.module.channel.dal.mysql.shop;

import com.alipay.api.domain.Shop;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.channel.controller.admin.shop.vo.ShopExportReqVO;
import com.dofast.module.channel.controller.admin.shop.vo.ShopPageReqVO;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import java.util.List;

import com.dofast.module.member.dal.dataobject.user.MemberUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 店铺授权 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ShopMapper extends BaseMapperX<ShopDO> {

    default PageResult<ShopDO> selectPage(ShopPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopDO>()
                .likeIfPresent(ShopDO::getDisplayName, reqVO.getDisplayName())
                .likeIfPresent(ShopDO::getName, reqVO.getName())
                .eqIfPresent(ShopDO::getShopNick, reqVO.getShopNick())
                .eqIfPresent(ShopDO::getShopUrl, reqVO.getShopUrl())
                .eqIfPresent(ShopDO::getShopId, reqVO.getShopId())
                .eqIfPresent(ShopDO::getShopCode, reqVO.getShopCode())
                .eqIfPresent(ShopDO::getChannel, reqVO.getChannel())
                .betweenIfPresent(ShopDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ShopDO::getLastTime, reqVO.getLastTime())
                .eqIfPresent(ShopDO::getRemark, reqVO.getRemark())
                .orderByDesc(ShopDO::getId));
    }

    default List<ShopDO> selectList(ShopExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopDO>()
                .likeIfPresent(ShopDO::getDisplayName, reqVO.getDisplayName())
                .likeIfPresent(ShopDO::getName, reqVO.getName())
                .eqIfPresent(ShopDO::getShopNick, reqVO.getShopNick())
                .eqIfPresent(ShopDO::getShopUrl, reqVO.getShopUrl())
                .eqIfPresent(ShopDO::getShopId, reqVO.getShopId())
                .eqIfPresent(ShopDO::getShopCode, reqVO.getShopCode())
                .eqIfPresent(ShopDO::getChannel, reqVO.getChannel())
                .betweenIfPresent(ShopDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ShopDO::getLastTime, reqVO.getLastTime())
                .eqIfPresent(ShopDO::getRemark, reqVO.getRemark())
                .orderByDesc(ShopDO::getId));
    }

    default ShopDO selectByPosCode(String posCode) {
        return selectOne(ShopDO::getShopCode, posCode);
    }

}
