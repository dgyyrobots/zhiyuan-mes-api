package com.dofast.module.purchase.service.retreatGoods;

import java.util.*;
import javax.validation.*;
import com.dofast.module.purchase.controller.admin.retreatGoods.vo.*;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.dal.dataobject.retreatGoods.RetreatGoodsDO;

/**
 * ERP仓退单单身 Service 接口
 *
 * @author 惠智造
 */
public interface RetreatGoodsService {

    /**
     * 创建ERP仓退单单身
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createGoods(@Valid RetreatGoodsCreateReqVO createReqVO);

    /**
     * 更新ERP仓退单单身
     *
     * @param updateReqVO 更新信息
     */
    void updateGoods(@Valid RetreatGoodsUpdateReqVO updateReqVO);

    /**
     * 删除ERP仓退单单身
     *
     * @param id 编号
     */
    void deleteGoods(Integer id);

    /**
     * 获得ERP仓退单单身
     *
     * @param id 编号
     * @return ERP仓退单单身
     */
    RetreatGoodsDO getGoods(Integer id);

    /**
     * 获得ERP仓退单单身列表
     *
     * @param ids 编号
     * @return ERP仓退单单身列表
     */
    List<RetreatGoodsDO> getGoodsList(Collection<Integer> ids);

    /**
     * 获得ERP仓退单单身分页
     *
     * @param pageReqVO 分页查询
     * @return ERP仓退单单身分页
     */
    PageResult<RetreatGoodsDO> getGoodsPage(RetreatGoodsPageReqVO pageReqVO);

    /**
     * 获得ERP仓退单单身列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return ERP仓退单单身列表
     */
    List<RetreatGoodsDO> getGoodsList(RetreatGoodsExportReqVO exportReqVO);

    void updateBatch(List<RetreatGoodsDO> retreatGoodsDO);

    void insertBatch(List<RetreatGoodsDO> retreatGoodsDO);

}
