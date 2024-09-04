package com.dofast.module.trade.service.calculatemodel;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelCreateReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelExportReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelPageReqVO;
import com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo.CalculateModelUpdateReqVO;
import com.dofast.module.trade.convert.calculatemodel.CalculateModelConvert;
import com.dofast.module.trade.dal.dataobject.calculatemodel.CalculateModelDO;
import com.dofast.module.trade.dal.mysql.calculatemodel.CalculateModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.CALCULATE_MODEL_NOT_EXISTS;

/**
 * 计价模型 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CalculateModelServiceImpl implements CalculateModelService {

    @Resource
    private CalculateModelMapper calculateModelMapper;

    @Override
    public Integer createCalculateModel(CalculateModelCreateReqVO createReqVO) {
        // 插入
        CalculateModelDO calculateModel = CalculateModelConvert.INSTANCE.convert(createReqVO);
        calculateModelMapper.insert(calculateModel);
        // 返回
        return calculateModel.getId();
    }

    @Override
    public void updateCalculateModel(CalculateModelUpdateReqVO updateReqVO) {
        // 校验存在
        validateCalculateModelExists(updateReqVO.getId());
        // 更新
        CalculateModelDO updateObj = CalculateModelConvert.INSTANCE.convert(updateReqVO);
        calculateModelMapper.updateById(updateObj);
    }

    @Override
    public void deleteCalculateModel(Integer id) {
        // 校验存在
        validateCalculateModelExists(id);
        // 删除
        calculateModelMapper.deleteById(id);
    }

    private void validateCalculateModelExists(Integer id) {
        if (calculateModelMapper.selectById(id) == null) {
            throw exception(CALCULATE_MODEL_NOT_EXISTS);
        }
    }

    @Override
    public CalculateModelDO getCalculateModel(Integer id) {
        return calculateModelMapper.selectById(id);
    }

    @Override
    public List<CalculateModelDO> getCalculateModelList(Collection<Integer> ids) {
        return calculateModelMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CalculateModelDO> getCalculateModelPage(CalculateModelPageReqVO pageReqVO) {
        return calculateModelMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CalculateModelDO> getCalculateModelList(CalculateModelExportReqVO exportReqVO) {
        return calculateModelMapper.selectList(exportReqVO);
    }

}
