package com.dofast.module.iot.service.thingsmodel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.thingsmodel.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodel.ThingsModelDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.thingsmodel.ThingsModelConvert;
import com.dofast.module.iot.dal.mysql.thingsmodel.ThingsModelMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 物模型 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ThingsModelServiceImpl implements ThingsModelService {

    @Resource
    private ThingsModelMapper thingsModelMapper;

    @Override
    public Long createThingsModel(ThingsModelCreateReqVO createReqVO) {
        // 插入
        ThingsModelDO thingsModel = ThingsModelConvert.INSTANCE.convert(createReqVO);
        thingsModelMapper.insert(thingsModel);
        // 返回
        return thingsModel.getId();
    }

    @Override
    public void updateThingsModel(ThingsModelUpdateReqVO updateReqVO) {
        // 校验存在
        validateThingsModelExists(updateReqVO.getId());
        // 更新
        ThingsModelDO updateObj = ThingsModelConvert.INSTANCE.convert(updateReqVO);
        thingsModelMapper.updateById(updateObj);
    }

    @Override
    public void deleteThingsModel(Long id) {
        // 校验存在
        validateThingsModelExists(id);
        // 删除
        thingsModelMapper.deleteById(id);
    }

    private void validateThingsModelExists(Long id) {
        if (thingsModelMapper.selectById(id) == null) {
            throw exception(THINGS_MODEL_NOT_EXISTS);
        }
    }

    @Override
    public ThingsModelDO getThingsModel(Long id) {
        return thingsModelMapper.selectById(id);
    }

    @Override
    public List<ThingsModelDO> getThingsModelList(Collection<Long> ids) {
        return thingsModelMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ThingsModelDO> getThingsModelPage(ThingsModelPageReqVO pageReqVO) {
        return thingsModelMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ThingsModelDO> getThingsModelList(ThingsModelExportReqVO exportReqVO) {
        return thingsModelMapper.selectList(exportReqVO);
    }

}
