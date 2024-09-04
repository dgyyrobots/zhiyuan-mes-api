package com.dofast.module.qms.service.template;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.template.vo.*;
import com.dofast.module.qms.dal.dataobject.template.TemplateDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.template.TemplateConvert;
import com.dofast.module.qms.dal.mysql.template.TemplateMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 检测模板 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateMapper templateMapper;

    @Override
    public TemplateDO selectQcTemplateByProductAndQcType(TemplateBaseVO qcTemplate) {
        return templateMapper.selectQcTemplateByProductAndQcType(qcTemplate);
    }

    @Override
    public TemplateDO findTemplateByProductIdAndQcType(TemplateBaseVO param) {
        return templateMapper.findTemplateByProductIdAndQcType(param);
    }

    @Override
    public String checkTemplateCodeUnique(TemplateBaseVO qcTemplate) {
        TemplateDO template = templateMapper.checkTemplateCodeUnique(qcTemplate);
        Long templateId = qcTemplate.getId()==null?-1L:qcTemplate.getId();
        if(StrUtils.isNotNull(template) && template.getId().longValue()!=templateId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createTemplate(TemplateCreateReqVO createReqVO) {
        // 插入
        TemplateDO template = TemplateConvert.INSTANCE.convert(createReqVO);
        templateMapper.insert(template);
        // 返回
        return template.getId();
    }

    @Override
    public void updateTemplate(TemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateTemplateExists(updateReqVO.getId());
        // 更新
        TemplateDO updateObj = TemplateConvert.INSTANCE.convert(updateReqVO);
        templateMapper.updateById(updateObj);
    }

    @Override
    public void deleteTemplate(Long id) {
        // 校验存在
        validateTemplateExists(id);
        // 删除
        templateMapper.deleteById(id);
    }

    private void validateTemplateExists(Long id) {
        if (templateMapper.selectById(id) == null) {
            throw exception(TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public TemplateDO getTemplate(Long id) {
        return templateMapper.selectById(id);
    }

    @Override
    public List<TemplateDO> getTemplateList(Collection<Long> ids) {
        return templateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TemplateDO> getTemplatePage(TemplatePageReqVO pageReqVO) {
        return templateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TemplateDO> getTemplateList(TemplateExportReqVO exportReqVO) {
        return templateMapper.selectList(exportReqVO);
    }

}
