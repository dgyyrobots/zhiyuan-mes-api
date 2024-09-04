package com.dofast.module.trade.service.calculatecategory;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryPageReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo.CalculateCategoryUpdateReqVO;
import com.dofast.module.trade.convert.calculatecategory.CalculateCategoryConvert;
import com.dofast.module.trade.dal.dataobject.calculatecategory.CalculateCategoryDO;
import com.dofast.module.trade.dal.mysql.calculatecategory.CalculateCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.CALCULATE_CATEGORY_NOT_EXISTS;

/**
 * 计价分类 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CalculateCategoryServiceImpl implements CalculateCategoryService {

    @Resource
    private CalculateCategoryMapper calculateCategoryMapper;

    @Override
    public Integer createCalculateCategory(CalculateCategoryCreateReqVO createReqVO) {
        // 插入
        CalculateCategoryDO calculateCategory = CalculateCategoryConvert.INSTANCE.convert(createReqVO);
        calculateCategoryMapper.insert(calculateCategory);
        // 返回
        return calculateCategory.getId();
    }

    @Override
    public void updateCalculateCategory(CalculateCategoryUpdateReqVO updateReqVO) {
        // 校验存在
        validateCalculateCategoryExists(updateReqVO.getId());
        // 更新
        CalculateCategoryDO updateObj = CalculateCategoryConvert.INSTANCE.convert(updateReqVO);
        calculateCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCalculateCategory(Integer id) {
        // 校验存在
        validateCalculateCategoryExists(id);
        // 删除
        calculateCategoryMapper.deleteById(id);
    }

    private void validateCalculateCategoryExists(Integer id) {
        if (calculateCategoryMapper.selectById(id) == null) {
            throw exception(CALCULATE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public CalculateCategoryDO getCalculateCategory(Integer id) {
        return calculateCategoryMapper.selectById(id);
    }

    @Override
    public List<CalculateCategoryDO> getCalculateCategoryList(Collection<Integer> ids) {
        return calculateCategoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CalculateCategoryDO> getCalculateCategoryPage(CalculateCategoryPageReqVO pageReqVO) {
        return calculateCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CalculateCategoryDO> getCalculateCategoryList(CalculateCategoryExportReqVO exportReqVO) {
        return calculateCategoryMapper.selectList(exportReqVO);
    }

}
