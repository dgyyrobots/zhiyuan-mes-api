package com.dofast.module.hr.service.commissionrule;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.commissionrule.vo.*;
import com.dofast.module.hr.dal.dataobject.commissionrule.CommissionruleDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.commissionrule.CommissionruleConvert;
import com.dofast.module.hr.dal.mysql.commissionrule.CommissionruleMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 提成规则 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CommissionruleServiceImpl implements CommissionruleService {

    @Resource
    private CommissionruleMapper commissionruleMapper;

    @Override
    public Integer createCommissionrule(CommissionruleCreateReqVO createReqVO) {
        // 插入
        CommissionruleDO commissionrule = CommissionruleConvert.INSTANCE.convert(createReqVO);
        commissionruleMapper.insert(commissionrule);
        // 返回
        return commissionrule.getId();
    }

    @Override
    public void updateCommissionrule(CommissionruleUpdateReqVO updateReqVO) {
        // 校验存在
        validateCommissionruleExists(updateReqVO.getId());
        // 更新
        CommissionruleDO updateObj = CommissionruleConvert.INSTANCE.convert(updateReqVO);
        commissionruleMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommissionrule(Integer id) {
        // 校验存在
        validateCommissionruleExists(id);
        // 删除
        commissionruleMapper.deleteById(id);
    }

    private void validateCommissionruleExists(Integer id) {
        if (commissionruleMapper.selectById(id) == null) {
            throw exception(COMMISSIONRULE_NOT_EXISTS);
        }
    }

    @Override
    public CommissionruleDO getCommissionrule(Integer id) {
        return commissionruleMapper.selectById(id);
    }

    @Override
    public List<CommissionruleDO> getCommissionruleList(Collection<Integer> ids) {
        return commissionruleMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CommissionruleDO> getCommissionrulePage(CommissionrulePageReqVO pageReqVO) {
        return commissionruleMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CommissionruleDO> getCommissionruleList(CommissionruleExportReqVO exportReqVO) {
        return commissionruleMapper.selectList(exportReqVO);
    }

}
