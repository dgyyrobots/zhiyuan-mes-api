package com.dofast.module.cal.service.plan;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cal.controller.admin.plan.vo.*;
import com.dofast.module.cal.dal.dataobject.plan.PlanDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cal.convert.plan.PlanConvert;
import com.dofast.module.cal.dal.mysql.plan.PlanMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;

/**
 * 排班计划 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class PlanServiceImpl implements PlanService {

    @Resource
    private PlanMapper planMapper;

    @Override
    public Long createPlan(PlanCreateReqVO createReqVO) {
        // 插入
        PlanDO plan = PlanConvert.INSTANCE.convert(createReqVO);
        planMapper.insert(plan);
        // 返回
        return plan.getId();
    }

    @Override
    public void updatePlan(PlanUpdateReqVO updateReqVO) {
        // 校验存在
        validatePlanExists(updateReqVO.getId());
        // 更新
        PlanDO updateObj = PlanConvert.INSTANCE.convert(updateReqVO);
        planMapper.updateById(updateObj);
    }

    @Override
    public void deletePlan(Long id) {
        // 校验存在
        validatePlanExists(id);
        // 删除
        planMapper.deleteById(id);
    }

    private void validatePlanExists(Long id) {
        if (planMapper.selectById(id) == null) {
            throw exception(PLAN_NOT_EXISTS);
        }
    }

    @Override
    public PlanDO getPlan(Long id) {
        return planMapper.selectById(id);
    }

    @Override
    public List<PlanDO> getPlanList(Collection<Long> ids) {
        return planMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PlanDO> getPlanPage(PlanPageReqVO pageReqVO) {
        return planMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PlanDO> getPlanList(PlanExportReqVO exportReqVO) {
        return planMapper.selectList(exportReqVO);
    }

}
