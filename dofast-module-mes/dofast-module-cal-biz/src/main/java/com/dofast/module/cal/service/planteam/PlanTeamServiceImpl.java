package com.dofast.module.cal.service.planteam;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cal.controller.admin.planteam.vo.*;
import com.dofast.module.cal.dal.dataobject.planteam.PlanTeamDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cal.convert.planteam.PlanTeamConvert;
import com.dofast.module.cal.dal.mysql.planteam.PlanTeamMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;

/**
 * 计划班组 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class PlanTeamServiceImpl implements PlanTeamService {

    @Resource
    private PlanTeamMapper planTeamMapper;

    @Override
    public Long createPlanTeam(PlanTeamCreateReqVO createReqVO) {
        // 插入
        PlanTeamDO planTeam = PlanTeamConvert.INSTANCE.convert(createReqVO);
        planTeamMapper.insert(planTeam);
        // 返回
        return planTeam.getId();
    }

    @Override
    public void updatePlanTeam(PlanTeamUpdateReqVO updateReqVO) {
        // 校验存在
        validatePlanTeamExists(updateReqVO.getId());
        // 更新
        PlanTeamDO updateObj = PlanTeamConvert.INSTANCE.convert(updateReqVO);
        planTeamMapper.updateById(updateObj);
    }

    @Override
    public void deletePlanTeam(Long id) {
        // 校验存在
        validatePlanTeamExists(id);
        // 删除
        planTeamMapper.deleteById(id);
    }

    private void validatePlanTeamExists(Long id) {
        if (planTeamMapper.selectById(id) == null) {
            throw exception(PLAN_TEAM_NOT_EXISTS);
        }
    }

    @Override
    public PlanTeamDO getPlanTeam(Long id) {
        return planTeamMapper.selectById(id);
    }

    @Override
    public List<PlanTeamDO> getPlanTeamList(Collection<Long> ids) {
        return planTeamMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PlanTeamDO> getPlanTeamPage(PlanTeamPageReqVO pageReqVO) {
        return planTeamMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PlanTeamDO> getPlanTeamList(PlanTeamExportReqVO exportReqVO) {
        return planTeamMapper.selectList(exportReqVO);
    }

}
