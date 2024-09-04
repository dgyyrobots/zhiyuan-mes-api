package com.dofast.module.wms.service.itemconsume;

import java.util.*;
import javax.validation.*;

import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.wms.controller.admin.itemconsume.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeTxBean;

/**
 * 物料消耗记录 Service 接口
 *
 * @author 惠智造
 */
public interface ItemConsumeService {

    /**
     * 创建物料消耗记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createItemConsume(@Valid ItemConsumeCreateReqVO createReqVO);

    /**
     * 更新物料消耗记录
     *
     * @param updateReqVO 更新信息
     */
    void updateItemConsume(@Valid ItemConsumeUpdateReqVO updateReqVO);

    /**
     * 删除物料消耗记录
     *
     * @param id 编号
     */
    void deleteItemConsume(Long id);

    /**
     * 获得物料消耗记录
     *
     * @param id 编号
     * @return 物料消耗记录
     */
    ItemConsumeDO getItemConsume(Long id);

    /**
     * 获得物料消耗记录列表
     *
     * @param ids 编号
     * @return 物料消耗记录列表
     */
    List<ItemConsumeDO> getItemConsumeList(Collection<Long> ids);

    /**
     * 获得物料消耗记录分页
     *
     * @param pageReqVO 分页查询
     * @return 物料消耗记录分页
     */
    PageResult<ItemConsumeDO> getItemConsumePage(ItemConsumePageReqVO pageReqVO);

    /**
     * 获得物料消耗记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料消耗记录列表
     */
    List<ItemConsumeDO> getItemConsumeList(ItemConsumeExportReqVO exportReqVO);

    public ItemConsumeDO generateItemConsume(FeedbackDTO feedback);


    List<ItemConsumeTxBean> getTxBeans(Long recordId);
}
