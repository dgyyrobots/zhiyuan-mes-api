package com.dofast.module.mes.service.mditemtype;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mditemtype.vo.*;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物料产品分类 Service 接口
 *
 * @author 芋道源码
 */
public interface MdItemTypeService {
    public List<MdItemTreeTypeListVO> buildTreeSelect(List<MdItemTypeDO> list);
    public String checkItemTypeCodeUnique(MdItemTypeBaseVO itemType);

    public String checkItemTypeNameUnique(MdItemTypeBaseVO itemType);
    public boolean checkHasChild(Long itemTypeId);

    public boolean checkHasItem(Long itemTypeId);
    List<MdItemTypeDO> selectALl();
    /**
     * 创建物料产品分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdItemType(@Valid MdItemTypeCreateReqVO createReqVO);

    /**
     * 更新物料产品分类
     *
     * @param updateReqVO 更新信息
     */
    void updateMdItemType(@Valid MdItemTypeUpdateReqVO updateReqVO);

    /**
     * 删除物料产品分类
     *
     * @param id 编号
     */
    void deleteMdItemType(Long id);

    /**
     * 获得物料产品分类
     *
     * @param id 编号
     * @return 物料产品分类
     */
    MdItemTypeDO getMdItemType(Long id);

    /**
     * 获得物料产品分类列表
     *
     * @param ids 编号
     * @return 物料产品分类列表
     */
    List<MdItemTypeDO> getMdItemTypeList(Collection<Long> ids);

    /**
     * 获得物料产品分类分页
     *
     * @param pageReqVO 分页查询
     * @return 物料产品分类分页
     */
    PageResult<MdItemTypeDO> getMdItemTypePage(MdItemTypePageReqVO pageReqVO);

    /**
     * 获得物料产品分类列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料产品分类列表
     */
    List<MdItemTypeDO> getMdItemTypeList(MdItemTypeExportReqVO exportReqVO);
    List<MdItemTypeDO> getMdItemTypeList(MdItemTypeListVO exportReqVO);

}
