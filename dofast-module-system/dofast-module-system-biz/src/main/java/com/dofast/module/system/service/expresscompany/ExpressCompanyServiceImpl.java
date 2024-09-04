package com.dofast.module.system.service.expresscompany;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyCreateReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyExportReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyPageReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyUpdateReqVO;
import com.dofast.module.system.convert.expresscompany.ExpressCompanyConvert;
import com.dofast.module.system.dal.dataobject.expresscompany.ExpressCompanyDO;
import com.dofast.module.system.dal.mysql.expresscompany.ExpressCompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.EXPRESS_COMPANY_NOT_EXISTS;

/**
 * 租户物流公司 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

    @Resource
    private ExpressCompanyMapper expressCompanyMapper;

    @Override
    public Integer createExpressCompany(ExpressCompanyCreateReqVO createReqVO) {
        // 插入
        ExpressCompanyDO expressCompany = ExpressCompanyConvert.INSTANCE.convert(createReqVO);
        expressCompanyMapper.insert(expressCompany);
        // 返回
        return expressCompany.getId();
    }

    @Override
    public void updateExpressCompany(ExpressCompanyUpdateReqVO updateReqVO) {
        // 校验存在
        validateExpressCompanyExists(updateReqVO.getId());
        // 更新
        ExpressCompanyDO updateObj = ExpressCompanyConvert.INSTANCE.convert(updateReqVO);
        expressCompanyMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpressCompany(Integer id) {
        // 校验存在
        validateExpressCompanyExists(id);
        // 删除
        expressCompanyMapper.deleteById(id);
    }

    private void validateExpressCompanyExists(Integer id) {
        if (expressCompanyMapper.selectById(id) == null) {
            throw exception(EXPRESS_COMPANY_NOT_EXISTS);
        }
    }

    @Override
    public ExpressCompanyDO getExpressCompany(Integer id) {
        return expressCompanyMapper.selectById(id);
    }

    @Override
    public List<ExpressCompanyDO> getExpressCompanyList(Collection<Integer> ids) {
        return expressCompanyMapper.selectBatchIds(ids);
    }

    @Override
    public List<ExpressCompanyDO> getExpressCompanyList() {
        return expressCompanyMapper.selectList();
    }

    @Override
    public PageResult<ExpressCompanyDO> getExpressCompanyPage(ExpressCompanyPageReqVO pageReqVO) {
        return expressCompanyMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ExpressCompanyDO> getExpressCompanyList(ExpressCompanyExportReqVO exportReqVO) {
        return expressCompanyMapper.selectList(exportReqVO);
    }

    @Override
    public ExpressCompanyDO getExpressCompanyByCode(String code) {
        return expressCompanyMapper.selectOne(code);
    }

}
