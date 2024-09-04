package com.dofast.module.wiki.service.category;

import com.dofast.module.wiki.dal.mysql.category.WikiCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wiki.controller.admin.category.vo.*;
import com.dofast.module.wiki.dal.dataobject.category.WikiCategoryDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wiki.convert.category.WikiCategoryConvert;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wiki.enums.ErrorCodeConstants.*;

/**
 * 首页的分类 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class WikiCategoryServiceImpl implements WikiCategoryService {

    @Autowired
    private WikiCategoryMapper categoryMapper;

    @Override
    public Long createCategory(WikiCategoryCreateReqVO createReqVO) {
        // 插入
        WikiCategoryDO category = WikiCategoryConvert.INSTANCE.convert(createReqVO);
        categoryMapper.insert(category);
        // 返回
        return category.getId();
    }

    @Override
    public void updateCategory(WikiCategoryUpdateReqVO updateReqVO) {
        // 校验存在
        validateCategoryExists(updateReqVO.getId());
        // 更新
        WikiCategoryDO updateObj = WikiCategoryConvert.INSTANCE.convert(updateReqVO);
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
    public WikiCategoryDO getCategory(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<WikiCategoryDO> getCategoryList(Collection<Long> ids) {
        return categoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WikiCategoryDO> getCategoryPage(WikiCategoryPageReqVO pageReqVO) {
        return categoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WikiCategoryDO> getCategoryList(WikiCategoryExportReqVO exportReqVO) {
        return categoryMapper.selectList(exportReqVO);
    }

}
