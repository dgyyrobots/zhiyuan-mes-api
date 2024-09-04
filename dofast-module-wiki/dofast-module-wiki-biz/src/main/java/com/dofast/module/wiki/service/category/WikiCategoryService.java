package com.dofast.module.wiki.service.category;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wiki.controller.admin.category.vo.*;
import com.dofast.module.wiki.dal.dataobject.category.WikiCategoryDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 首页的分类 Service 接口
 *
 * @author 惠智造
 */
public interface WikiCategoryService {

    /**
     * 创建首页的分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid WikiCategoryCreateReqVO createReqVO);

    /**
     * 更新首页的分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid WikiCategoryUpdateReqVO updateReqVO);

    /**
     * 删除首页的分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 获得首页的分类
     *
     * @param id 编号
     * @return 首页的分类
     */
    WikiCategoryDO getCategory(Long id);

    /**
     * 获得首页的分类列表
     *
     * @param ids 编号
     * @return 首页的分类列表
     */
    List<WikiCategoryDO> getCategoryList(Collection<Long> ids);

    /**
     * 获得首页的分类分页
     *
     * @param pageReqVO 分页查询
     * @return 首页的分类分页
     */
    PageResult<WikiCategoryDO> getCategoryPage(WikiCategoryPageReqVO pageReqVO);

    /**
     * 获得首页的分类列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 首页的分类列表
     */
    List<WikiCategoryDO> getCategoryList(WikiCategoryExportReqVO exportReqVO);

}
