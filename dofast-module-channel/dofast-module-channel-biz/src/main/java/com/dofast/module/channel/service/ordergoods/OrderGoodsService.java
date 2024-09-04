package com.dofast.module.channel.service.ordergoods;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsExportReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsPageReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;

/**
 * 子订单 Service 接口
 *
 * @author 惠智造
 */
public interface OrderGoodsService {

    /**
     * 创建子订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createOrderGoods(@Valid OrderGoodsCreateReqVO createReqVO);

    /**
     * 更新子订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderGoods(@Valid OrderGoodsUpdateReqVO updateReqVO);

    /**
     * 删除子订单
     *
     * @param id 编号
     */
    void deleteOrderGoods(Integer id);

    /**
     * 获得子订单
     *
     * @param id 编号
     * @return 子订单
     */
    OrderGoodsDO getOrderGoods(Integer id);

    /**
     * 获得子订单列表
     *
     * @param ids 编号
     * @return 子订单列表
     */
    List<OrderGoodsDO> getOrderGoodsList(Collection<Integer> ids);

    /**
     * 获得子订单分页
     *
     * @param pageReqVO 分页查询
     * @return 子订单分页
     */
    PageResult<OrderGoodsDO> getOrderGoodsPage(OrderGoodsPageReqVO pageReqVO);

    /**
     * 获得子订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 子订单列表
     */
    List<OrderGoodsDO> getOrderGoodsList(OrderGoodsExportReqVO exportReqVO);

    /**
     * 获取分页商品
     *
     * @param name 商品名称
     * @param refOid 商品单号
     * @return
     */
    List<OrderGoodsDO> getPageOrderGoods(String name, String refOid);

    List<OrderGoodsDO> getPageOrderGoods(String refOid);
}
