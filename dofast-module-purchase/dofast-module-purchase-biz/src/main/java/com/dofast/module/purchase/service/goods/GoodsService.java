package com.dofast.module.purchase.service.goods;

import java.util.*;
import javax.validation.*;
import com.dofast.module.purchase.controller.admin.goods.vo.*;
import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 采购商品明细 Service 接口
 *
 * @author 惠智造
 */
public interface GoodsService {

    /**
     * 创建采购商品明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createGoods(@Valid GoodsCreateReqVO createReqVO);

    /**
     * 更新采购商品明细
     *
     * @param updateReqVO 更新信息
     */
    void updateGoods(@Valid GoodsUpdateReqVO updateReqVO);

    /**
     * 删除采购商品明细
     *
     * @param id 编号
     */
    void deleteGoods(Integer id);

    /**
     * 获得采购商品明细
     *
     * @param id 编号
     * @return 采购商品明细
     */
    GoodsDO getGoods(Integer id);

    /**
     * 获得采购商品明细列表
     *
     * @param ids 编号
     * @return 采购商品明细列表
     */
    List<GoodsDO> getGoodsList(Collection<Integer> ids);

    /**
     * 获得采购商品明细分页
     *
     * @param pageReqVO 分页查询
     * @return 采购商品明细分页
     */
    PageResult<GoodsDO> getGoodsPage(GoodsPageReqVO pageReqVO);

    /**
     * 获得采购商品明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采购商品明细列表
     */
    List<GoodsDO> getGoodsList(GoodsExportReqVO exportReqVO);

    void updateBatch(List<GoodsDO> goodsList);

}
