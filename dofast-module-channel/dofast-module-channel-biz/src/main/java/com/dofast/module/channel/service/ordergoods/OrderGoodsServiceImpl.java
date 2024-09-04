package com.dofast.module.channel.service.ordergoods;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsExportReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsPageReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsUpdateReqVO;
import com.dofast.module.channel.convert.ordergoods.OrderGoodsConvert;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import com.dofast.module.channel.dal.mysql.ordergoods.OrderGoodsMapper;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.channel.enums.ErrorCodeConstants.ORDER_GOODS_NOT_EXISTS;

/**
 * 子订单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public Integer createOrderGoods(OrderGoodsCreateReqVO createReqVO) {
        // 插入
        OrderGoodsDO orderGoods = OrderGoodsConvert.INSTANCE.convert(createReqVO);
        orderGoodsMapper.insert(orderGoods);
        // 返回
        return orderGoods.getId();
    }

    @Override
    public void updateOrderGoods(OrderGoodsUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderGoodsExists(updateReqVO.getId());
        // 更新
        OrderGoodsDO updateObj = OrderGoodsConvert.INSTANCE.convert(updateReqVO);
        orderGoodsMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderGoods(Integer id) {
        // 校验存在
        validateOrderGoodsExists(id);
        // 删除
        orderGoodsMapper.deleteById(id);
    }

    private void validateOrderGoodsExists(Integer id) {
        if (orderGoodsMapper.selectById(id) == null) {
            throw exception(ORDER_GOODS_NOT_EXISTS);
        }
    }

    @Override
    public OrderGoodsDO getOrderGoods(Integer id) {
        return orderGoodsMapper.selectById(id);
    }

    @Override
    public List<OrderGoodsDO> getOrderGoodsList(Collection<Integer> ids) {
        return orderGoodsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderGoodsDO> getOrderGoodsPage(OrderGoodsPageReqVO pageReqVO) {
        return orderGoodsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderGoodsDO> getOrderGoodsList(OrderGoodsExportReqVO exportReqVO) {
        return orderGoodsMapper.selectList(exportReqVO);
    }

    @Override
    public List<OrderGoodsDO> getPageOrderGoods(String name, String refOid) {
        List<OrderGoodsDO> list = orderGoodsMapper.selectPageOrderGoods(name, refOid);
        return list;
    }

    @Override
    public List<OrderGoodsDO> getPageOrderGoods(String refOid) {
        List<OrderGoodsDO> list = orderGoodsMapper.selectListAccordingRefOid(refOid);
        return list;
    }
}
