package com.dofast.module.wms.service.itemrecptline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物料入库单行 Service 接口
 *
 * @author 芋道源码
 */
public interface ItemRecptLineService {
    /**
     * 删除物料入库单下所有行信息
     * @param recptId
     * @return
     */
    public int deleteByRecptId(Long recptId);
    List<ItemRecptLineDO> getItemRecptLineList(ItemRecptLineListVO lineListVO);
    /**
     * 创建物料入库单行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createItemRecptLine(@Valid ItemRecptLineCreateReqVO createReqVO);

    /**
     * 更新物料入库单行
     *
     * @param updateReqVO 更新信息
     */
    void updateItemRecptLine(@Valid ItemRecptLineUpdateReqVO updateReqVO);

    /**
     * 删除物料入库单行
     *
     * @param id 编号
     */
    void deleteItemRecptLine(Long id);

    /**
     * 获得物料入库单行
     *
     * @param id 编号
     * @return 物料入库单行
     */
    ItemRecptLineDO getItemRecptLine(Long id);

    /**
     * 获得物料入库单行列表
     *
     * @param ids 编号
     * @return 物料入库单行列表
     */
    List<ItemRecptLineDO> getItemRecptLineList(Collection<Long> ids);

    /**
     * 获得物料入库单行分页
     *
     * @param pageReqVO 分页查询
     * @return 物料入库单行分页
     */
    PageResult<ItemRecptLineDO> getItemRecptLinePage(ItemRecptLinePageReqVO pageReqVO);

    /**
     * 获得物料入库单行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料入库单行列表
     */
    List<ItemRecptLineDO> getItemRecptLineList(ItemRecptLineExportReqVO exportReqVO);

}
