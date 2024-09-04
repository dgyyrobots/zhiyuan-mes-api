package com.dofast.module.wms.service.itemrecpt;

import java.util.*;
import javax.validation.*;

import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;

/**
 * 物料入库单 Service 接口
 *
 * @author 芋道源码
 */
public interface ItemRecptService {

    /**
     * 组装当前入库单对应的库存事务传入bean
     * @param receptId
     * @return
     */
    public List<ItemRecptTxBean> getTxBeans(Long receptId);
    public String checkRecptCodeUnique(ItemRecptBaseVO wmItemRecpt);
    /**
     * 创建物料入库单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createItemRecpt(@Valid ItemRecptCreateReqVO createReqVO);

    /**
     * 更新物料入库单
     *
     * @param updateReqVO 更新信息
     */
    void updateItemRecpt(@Valid ItemRecptUpdateReqVO updateReqVO);

    /**
     * 删除物料入库单
     *
     * @param id 编号
     */
    void deleteItemRecpt(Long id);

    /**
     * 获得物料入库单
     *
     * @param id 编号
     * @return 物料入库单
     */
    ItemRecptDO getItemRecpt(Long id);

    /**
     * 获得物料入库单列表
     *
     * @param ids 编号
     * @return 物料入库单列表
     */
    List<ItemRecptDO> getItemRecptList(Collection<Long> ids);

    /**
     * 获得物料入库单分页
     *
     * @param pageReqVO 分页查询
     * @return 物料入库单分页
     */
    PageResult<ItemRecptDO> getItemRecptPage(ItemRecptPageReqVO pageReqVO);

    /**
     * 获得物料入库单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料入库单列表
     */
    List<ItemRecptDO> getItemRecptList(ItemRecptExportReqVO exportReqVO);

    ItemRecptDO generateItemRecpt(FeedbackDTO feedback);
}
