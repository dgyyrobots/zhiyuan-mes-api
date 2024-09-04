package com.dofast.module.trade.service.calculate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypePageReqVO;
import com.dofast.module.trade.controller.admin.calculate.vo.CalculateTypeUpdateReqVO;
import com.dofast.module.trade.convert.calculate.CalculateTypeConvert;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateTypeDO;
import com.dofast.module.trade.dal.mysql.calculate.CalculateTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.CALCULATE_TYPE_NOT_EXISTS;

/**
 * 计价类型 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CalculateTypeServiceImpl implements CalculateTypeService {

    @Resource
    private CalculateTypeMapper calculateTypeMapper;

    @Override
    public Integer createCalculateType(CalculateTypeCreateReqVO createReqVO) {
        // 插入
        CalculateTypeDO calculateType = CalculateTypeConvert.INSTANCE.convert(createReqVO);
        calculateTypeMapper.insert(calculateType);
        // 返回
        return calculateType.getId();
    }

    @Override
    public void updateCalculateType(CalculateTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateCalculateTypeExists(updateReqVO.getId());
        // 更新
        CalculateTypeDO updateObj = CalculateTypeConvert.INSTANCE.convert(updateReqVO);
        calculateTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCalculateType(Integer id) {
        // 校验存在
        validateCalculateTypeExists(id);
        // 删除
        calculateTypeMapper.deleteById(id);
    }

    private void validateCalculateTypeExists(Integer id) {
        if (calculateTypeMapper.selectById(id) == null) {
            throw exception(CALCULATE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public CalculateTypeDO getCalculateType(Integer id) {
        return calculateTypeMapper.selectById(id);
    }

    @Override
    public List<CalculateTypeDO> getCalculateTypeList(Collection<Integer> ids) {
        return calculateTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CalculateTypeDO> getCalculateTypePage(CalculateTypePageReqVO pageReqVO) {
        return calculateTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CalculateTypeDO> getCalculateTypeList(CalculateTypeExportReqVO exportReqVO) {
        return calculateTypeMapper.selectList(exportReqVO);
    }

}
