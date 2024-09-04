package com.dofast.module.trade.service.calculatecategory;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryPageReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.calculatecategory.CalculateCategoryDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 计价分类 Service 接口
 *
 * @author 惠智造
 */
public interface CalculateCategoryService {

    /**
     * 创建计价分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createCalculateCategory(@Valid CalculateCategoryCreateReqVO createReqVO);

    /**
     * 更新计价分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCalculateCategory(@Valid CalculateCategoryUpdateReqVO updateReqVO);

    /**
     * 删除计价分类
     *
     * @param id 编号
     */
    void deleteCalculateCategory(Integer id);

    /**
     * 获得计价分类
     *
     * @param id 编号
     * @return 计价分类
     */
    CalculateCategoryDO getCalculateCategory(Integer id);

    /**
     * 获得计价分类列表
     *
     * @param ids 编号
     * @return 计价分类列表
     */
    List<CalculateCategoryDO> getCalculateCategoryList(Collection<Integer> ids);

    /**
     * 获得计价分类分页
     *
     * @param pageReqVO 分页查询
     * @return 计价分类分页
     */
    PageResult<CalculateCategoryDO> getCalculateCategoryPage(CalculateCategoryPageReqVO pageReqVO);

    /**
     * 获得计价分类列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 计价分类列表
     */
    List<CalculateCategoryDO> getCalculateCategoryList(CalculateCategoryExportReqVO exportReqVO);

}
