package com.dofast.module.mes.service.mditem;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mditem.vo.*;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物料产品 Service 接口
 *
 * @author 芋道源码
 */
public interface MdItemService {

    /**
     * 检查物料编码是否唯一
     * @param createReqVO
     * @return
     */
     String checkItemCodeUnique(@Valid MdItemBaseVO createReqVO);

    /**
     * 检查物料名称是否唯一
     * @param mdItem
     * @return
     */
     String checkItemNameUnique(@Valid MdItemBaseVO mdItem);

    /**
     * 创建物料产品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdItem(@Valid MdItemCreateReqVO createReqVO);

    /**
     * 更新物料产品
     *
     * @param updateReqVO 更新信息
     */
    void updateMdItem(@Valid MdItemUpdateReqVO updateReqVO);

    /**
     * 删除物料产品
     *
     * @param id 编号
     */
    void deleteMdItem(Long id);

    /**
     * 获得物料产品
     *
     * @param id 编号
     * @return 物料产品
     */
    MdItemDO getMdItem(Long id);

    /**
     * 获得物料产品
     *
     * @param itemCode 料号
     * @return 物料产品
     */
    MdItemDO getMdItem(String itemCode);


    /**
     * 获得物料产品列表
     *
     * @param ids 编号
     * @return 物料产品列表
     */
    List<MdItemDO> getMdItemList(Collection<Long> ids);

    /**
     * 获得物料产品分页
     *
     * @param pageReqVO 分页查询
     * @return 物料产品分页
     */
    PageResult<MdItemDO> getMdItemPage(MdItemPageReqVO pageReqVO);

    /**
     * 获得物料产品列表, 用于 Excel 导出
     *
     * @return 物料产品列表
     */
    List<MdItemDO> getMdItemList(MdItemExportReqVO mdItemExportReqVO);

    /**
     *
     * @param ids 前端传来的产品id
     * @return
     */
    List<MdItemRequestVO> getMdItemRequest(Collection<Long> ids);

    /**
     * 根据物料料号获取物料信息
     * @param code
     * @return
     */
    MdItemDO getMdItemByItemCode(String code);

    /**
     * 从ERP获取物料信息
     * @return
     */
    String initMdItemInfo();

}
