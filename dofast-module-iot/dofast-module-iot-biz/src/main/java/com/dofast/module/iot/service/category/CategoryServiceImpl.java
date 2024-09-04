package com.dofast.module.iot.service.category;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.category.vo.*;
import com.dofast.module.iot.dal.dataobject.category.CategoryDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.category.CategoryConvert;
import com.dofast.module.iot.dal.mysql.category.CategoryMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 产品分类 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Long createCategory(CategoryCreateReqVO createReqVO) {
        // 插入
        CategoryDO category = CategoryConvert.INSTANCE.convert(createReqVO);
        categoryMapper.insert(category);
        // 返回
        return category.getCategoryId();
    }

    @Override
    public void updateCategory(CategoryUpdateReqVO updateReqVO) {
        // 校验存在
        validateCategoryExists(updateReqVO.getCategoryId());
        // 更新
        CategoryDO updateObj = CategoryConvert.INSTANCE.convert(updateReqVO);
        categoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCategory(Long id) {
        // 校验存在
        validateCategoryExists(id);
        // 删除
        categoryMapper.deleteById(id);
    }

    private void validateCategoryExists(Long id) {
        if (categoryMapper.selectById(id) == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public CategoryDO getCategory(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<CategoryDO> getCategoryList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return categoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CategoryDO> getCategoryPage(CategoryPageReqVO pageReqVO) {
        return categoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CategoryDO> getCategoryList(CategoryExportReqVO exportReqVO) {
        return categoryMapper.selectList(exportReqVO);
    }

}
