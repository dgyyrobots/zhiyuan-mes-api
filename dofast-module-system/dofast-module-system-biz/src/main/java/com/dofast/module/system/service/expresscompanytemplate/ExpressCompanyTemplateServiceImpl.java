package com.dofast.module.system.service.expresscompanytemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateExportReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplatePageReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateUpdateReqVO;
import com.dofast.module.system.convert.expresscompanytemplate.ExpressCompanyTemplateConvert;
import com.dofast.module.system.dal.dataobject.expresscompanytemplate.ExpressCompanyTemplateDO;
import com.dofast.module.system.dal.mysql.expresscompanytemplate.ExpressCompanyTemplateMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.EXPRESS_COMPANY_TEMPLATE_NOT_EXISTS;

/**
 * 系统物流公司 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ExpressCompanyTemplateServiceImpl implements ExpressCompanyTemplateService {

    @Resource
    private ExpressCompanyTemplateMapper expressCompanyTemplateMapper;

    @Override
    public Integer createExpressCompanyTemplate(ExpressCompanyTemplateCreateReqVO createReqVO) {
        // 插入
        ExpressCompanyTemplateDO expressCompanyTemplate = ExpressCompanyTemplateConvert.INSTANCE.convert(createReqVO);
        expressCompanyTemplateMapper.insert(expressCompanyTemplate);
        // 返回
        return expressCompanyTemplate.getCompanyId();
    }

    @Override
    public void updateExpressCompanyTemplate(ExpressCompanyTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateExpressCompanyTemplateExists(updateReqVO.getCompanyId());
        // 更新
        ExpressCompanyTemplateDO updateObj = ExpressCompanyTemplateConvert.INSTANCE.convert(updateReqVO);
        expressCompanyTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpressCompanyTemplate(Integer id) {
        // 校验存在
        validateExpressCompanyTemplateExists(id);
        // 删除
        expressCompanyTemplateMapper.deleteById(id);
    }

    private void validateExpressCompanyTemplateExists(Integer id) {
        if (expressCompanyTemplateMapper.selectById(id) == null) {
            throw exception(EXPRESS_COMPANY_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public ExpressCompanyTemplateDO getExpressCompanyTemplate(Integer id) {
        return expressCompanyTemplateMapper.selectById(id);
    }

    @Override
    public List<ExpressCompanyTemplateDO> getExpressCompanyTemplateList(Collection<Integer> ids) {
        return expressCompanyTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ExpressCompanyTemplateDO> getExpressCompanyTemplatePage(ExpressCompanyTemplatePageReqVO pageReqVO) {
        return expressCompanyTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ExpressCompanyTemplateDO> getExpressCompanyTemplateList(ExpressCompanyTemplateExportReqVO exportReqVO) {
        return expressCompanyTemplateMapper.selectList(exportReqVO);
    }

}
