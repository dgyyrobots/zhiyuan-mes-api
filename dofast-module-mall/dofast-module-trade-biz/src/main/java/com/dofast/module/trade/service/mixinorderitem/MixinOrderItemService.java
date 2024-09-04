package com.dofast.module.trade.service.mixinorderitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemCreateReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemExportReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemPageReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.mixinorderitem.MixinOrderItemDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 销售的物料明细 Service 接口
 *
 * @author 惠智造
 */
public interface MixinOrderItemService {

    /**
     * 创建销售的物料明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMixinOrderItem(@Valid MixinOrderItemCreateReqVO createReqVO);

    /**
     * 更新销售的物料明细
     *
     * @param updateReqVO 更新信息
     */
    void updateMixinOrderItem(@Valid MixinOrderItemUpdateReqVO updateReqVO);

    /**
     * 删除销售的物料明细
     *
     * @param id 编号
     */
    void deleteMixinOrderItem(Long id);

    /**
     * 获得销售的物料明细
     *
     * @param id 编号
     * @return 销售的物料明细
     */
    MixinOrderItemDO getMixinOrderItem(Long id);

    /**
     * 获得销售的物料明细列表
     *
     * @param ids 编号
     * @return 销售的物料明细列表
     */
    List<MixinOrderItemDO> getMixinOrderItemList(Collection<Long> ids);

    /**
     * 获得销售的物料明细分页
     *
     * @param pageReqVO 分页查询
     * @return 销售的物料明细分页
     */
    PageResult<MixinOrderItemDO> getMixinOrderItemPage(MixinOrderItemPageReqVO pageReqVO);

    /**
     * 获得销售的物料明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 销售的物料明细列表
     */
    List<MixinOrderItemDO> getMixinOrderItemList(MixinOrderItemExportReqVO exportReqVO);

    void generateMixinOrderItem(MdItemDTO mdItemDTO, MixinOrderDO mixinOrderDO);

}
