package com.dofast.module.iot.service.thingsmodeltemplate;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodeltemplate.ThingsModelTemplateDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.thingsmodeltemplate.ThingsModelTemplateConvert;
import com.dofast.module.iot.dal.mysql.thingsmodeltemplate.ThingsModelTemplateMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 物模型模板 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ThingsModelTemplateServiceImpl implements ThingsModelTemplateService {

    @Resource
    private ThingsModelTemplateMapper thingsModelTemplateMapper;

    @Override
    public Long createThingsModelTemplate(ThingsModelTemplateCreateReqVO createReqVO) {
        // 插入
        ThingsModelTemplateDO thingsModelTemplate = ThingsModelTemplateConvert.INSTANCE.convert(createReqVO);
        thingsModelTemplateMapper.insert(thingsModelTemplate);
        // 返回
        return thingsModelTemplate.getId();
    }

    @Override
    public void updateThingsModelTemplate(ThingsModelTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateThingsModelTemplateExists(updateReqVO.getId());
        // 更新
        ThingsModelTemplateDO updateObj = ThingsModelTemplateConvert.INSTANCE.convert(updateReqVO);
        thingsModelTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteThingsModelTemplate(Long id) {
        // 校验存在
        validateThingsModelTemplateExists(id);
        // 删除
        thingsModelTemplateMapper.deleteById(id);
    }

    private void validateThingsModelTemplateExists(Long id) {
        if (thingsModelTemplateMapper.selectById(id) == null) {
            throw exception(THINGS_MODEL_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public ThingsModelTemplateDO getThingsModelTemplate(Long id) {
        return thingsModelTemplateMapper.selectById(id);
    }

    @Override
    public List<ThingsModelTemplateDO> getThingsModelTemplateList(Collection<Long> ids) {
        return thingsModelTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ThingsModelTemplateDO> getThingsModelTemplatePage(ThingsModelTemplatePageReqVO pageReqVO) {
        return thingsModelTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ThingsModelTemplateDO> getThingsModelTemplateList(ThingsModelTemplateExportReqVO exportReqVO) {
        return thingsModelTemplateMapper.selectList(exportReqVO);
    }

}
