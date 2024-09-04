package com.dofast.module.system.service.expresstemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateExportReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplatePageReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateUpdateReqVO;
import com.dofast.module.system.convert.expresstemplate.ExpressTemplateConvert;
import com.dofast.module.system.dal.dataobject.expresstemplate.ExpressTemplateDO;
import com.dofast.module.system.dal.mysql.expresstemplate.ExpressTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.EXPRESS_TEMPLATE_NOT_EXISTS;

/**
 * 运费模板 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ExpressTemplateServiceImpl implements ExpressTemplateService {

    @Autowired
    private ExpressTemplateMapper expressTemplateMapper;

    @Override
    public Long createExpressTemplate(ExpressTemplateCreateReqVO createReqVO) {
        // 插入
        ExpressTemplateDO expressTemplate = ExpressTemplateConvert.INSTANCE.convert(createReqVO);
        expressTemplateMapper.insert(expressTemplate);
        // 返回
        return expressTemplate.getId();
    }

    @Override
    public void updateExpressTemplate(ExpressTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateExpressTemplateExists(updateReqVO.getId());
        // 更新
        ExpressTemplateDO updateObj = ExpressTemplateConvert.INSTANCE.convert(updateReqVO);
        expressTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpressTemplate(Long id) {
        // 校验存在
        validateExpressTemplateExists(id);
        // 删除
        expressTemplateMapper.deleteById(id);
    }

    private void validateExpressTemplateExists(Long id) {
        if (expressTemplateMapper.selectById(id) == null) {
            throw exception(EXPRESS_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public ExpressTemplateDO getExpressTemplate(Long id) {
        return expressTemplateMapper.selectById(id);
    }

    @Override
    public List<ExpressTemplateDO> getExpressTemplateList(Collection<Long> ids) {
        return expressTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ExpressTemplateDO> getExpressTemplatePage(ExpressTemplatePageReqVO pageReqVO) {
        return expressTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ExpressTemplateDO> getExpressTemplateList(ExpressTemplateExportReqVO exportReqVO) {
        return expressTemplateMapper.selectList(exportReqVO);
    }

}
