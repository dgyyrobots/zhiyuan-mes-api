package com.dofast.module.channel.dal.mysql.ordergoods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.channel.controller.admin.order.vo.OrderGoodsVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsExportReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsPageReqVO;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 子订单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface OrderGoodsMapper extends BaseMapperX<OrderGoodsDO> {

    default PageResult<OrderGoodsDO> selectPage(OrderGoodsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderGoodsDO>()
                .eqIfPresent(OrderGoodsDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(OrderGoodsDO::getRefOlId, reqVO.getRefOlId())
                .eqIfPresent(OrderGoodsDO::getRefSpuId, reqVO.getRefSpuId())
                .eqIfPresent(OrderGoodsDO::getRefSkuId, reqVO.getRefSkuId())
                .eqIfPresent(OrderGoodsDO::getOuterId, reqVO.getOuterId())
                .eqIfPresent(OrderGoodsDO::getTitle, reqVO.getTitle())
                .eqIfPresent(OrderGoodsDO::getStandards, reqVO.getStandards())
//                .eqIfPresent(OrderGoodsDO::getTotalSellPrice, reqVO.getTotalSellPrice())
//                .eqIfPresent(OrderGoodsDO::getTotalPrice, reqVO.getTotalPrice())
//                .eqIfPresent(OrderGoodsDO::getPrice, reqVO.getPrice())
//                .eqIfPresent(OrderGoodsDO::getSellPrice, reqVO.getSellPrice())
//                .eqIfPresent(OrderGoodsDO::getTotalFee, reqVO.getTotalFee())
//                .eqIfPresent(OrderGoodsDO::getSingleFee, reqVO.getSingleFee())
                .eqIfPresent(OrderGoodsDO::getNum, reqVO.getNum())
                .eqIfPresent(OrderGoodsDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderGoodsDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderGoodsDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(OrderGoodsDO::getIsFreeGift, reqVO.getIsFreeGift())
                .betweenIfPresent(OrderGoodsDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderGoodsDO::getUserId, reqVO.getUserId())
                .orderByDesc(OrderGoodsDO::getId));
    }

    default List<OrderGoodsDO> selectList(OrderGoodsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderGoodsDO>()
                .eqIfPresent(OrderGoodsDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(OrderGoodsDO::getRefOlId, reqVO.getRefOlId())
                .eqIfPresent(OrderGoodsDO::getRefSpuId, reqVO.getRefSpuId())
                .eqIfPresent(OrderGoodsDO::getRefSkuId, reqVO.getRefSkuId())
                .eqIfPresent(OrderGoodsDO::getOuterId, reqVO.getOuterId())
                .eqIfPresent(OrderGoodsDO::getTitle, reqVO.getTitle())
                .eqIfPresent(OrderGoodsDO::getStandards, reqVO.getStandards())
//                .eqIfPresent(OrderGoodsDO::getTotalSellPrice, reqVO.getTotalSellPrice())
//                .eqIfPresent(OrderGoodsDO::getTotalPrice, reqVO.getTotalPrice())
//                .eqIfPresent(OrderGoodsDO::getPrice, reqVO.getPrice())
//                .eqIfPresent(OrderGoodsDO::getSellPrice, reqVO.getSellPrice())
//                .eqIfPresent(OrderGoodsDO::getTotalFee, reqVO.getTotalFee())
//                .eqIfPresent(OrderGoodsDO::getSingleFee, reqVO.getSingleFee())
                .eqIfPresent(OrderGoodsDO::getNum, reqVO.getNum())
                .eqIfPresent(OrderGoodsDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderGoodsDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderGoodsDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(OrderGoodsDO::getIsFreeGift, reqVO.getIsFreeGift())
                .betweenIfPresent(OrderGoodsDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderGoodsDO::getUserId, reqVO.getUserId())
                .orderByDesc(OrderGoodsDO::getId));
    }

    default List<OrderGoodsDO> selectListByRefOid(String refOid) {
        QueryWrapper<OrderGoodsDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_oid", refOid);
        List<OrderGoodsDO> dataList = selectList(queryWrapper);
        return dataList;
    }

    default List<OrderGoodsDO> selectListByRefOid(String refOid, long tenantId) {
        QueryWrapper<OrderGoodsDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_oid", refOid);
        queryWrapper.eq("tenant_id", tenantId);
        List<OrderGoodsDO> dataList = selectList(queryWrapper);
        return dataList;
    }

    default List<OrderGoodsDO> selectPageOrderGoods(String name, String refOid){
        QueryWrapper<OrderGoodsDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_oid", refOid);
        queryWrapper.like("title", name);
        List<OrderGoodsDO> dataList = selectList(queryWrapper);
        return dataList;
    }

    default  List<OrderGoodsDO> selectListAccordingRefOid(String refOid) {
        QueryWrapper<OrderGoodsDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_oid", refOid);
        List<OrderGoodsDO> dataList = selectList(queryWrapper);
        return dataList;
    }
}
