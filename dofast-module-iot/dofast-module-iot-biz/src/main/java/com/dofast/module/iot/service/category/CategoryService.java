package com.dofast.module.iot.service.category;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.category.vo.*;
import com.dofast.module.iot.dal.dataobject.category.CategoryDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品分类 Service 接口
 *
 * @author 惠智造
 */
public interface CategoryService {

    /**
     * 创建产品分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid CategoryCreateReqVO createReqVO);

    /**
     * 更新产品分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid CategoryUpdateReqVO updateReqVO);

    /**
     * 删除产品分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 获得产品分类
     *
     * @param id 编号
     * @return 产品分类
     */
    CategoryDO getCategory(Long id);

    /**
     * 获得产品分类列表
     *
     * @param ids 编号
     * @return 产品分类列表
     */
    List<CategoryDO> getCategoryList(Collection<Long> ids);

    /**
     * 获得产品分类分页
     *
     * @param pageReqVO 分页查询
     * @return 产品分类分页
     */
    PageResult<CategoryDO> getCategoryPage(CategoryPageReqVO pageReqVO);

    /**
     * 获得产品分类列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品分类列表
     */
    List<CategoryDO> getCategoryList(CategoryExportReqVO exportReqVO);

}
