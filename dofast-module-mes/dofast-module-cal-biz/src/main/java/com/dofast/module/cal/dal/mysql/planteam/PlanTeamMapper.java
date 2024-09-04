package com.dofast.module.cal.dal.mysql.planteam;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cal.dal.dataobject.planteam.PlanTeamDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cal.controller.admin.planteam.vo.*;

/**
 * 计划班组 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface PlanTeamMapper extends BaseMapperX<PlanTeamDO> {

    default PageResult<PlanTeamDO> selectPage(PlanTeamPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlanTeamDO>()
                .eqIfPresent(PlanTeamDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(PlanTeamDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(PlanTeamDO::getTeamCode, reqVO.getTeamCode())
                .likeIfPresent(PlanTeamDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(PlanTeamDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PlanTeamDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PlanTeamDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PlanTeamDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PlanTeamDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PlanTeamDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanTeamDO::getId));
    }

    default List<PlanTeamDO> selectList(PlanTeamExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PlanTeamDO>()
                .eqIfPresent(PlanTeamDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(PlanTeamDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(PlanTeamDO::getTeamCode, reqVO.getTeamCode())
                .likeIfPresent(PlanTeamDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(PlanTeamDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PlanTeamDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PlanTeamDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PlanTeamDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PlanTeamDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PlanTeamDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanTeamDO::getId));
    }

}
