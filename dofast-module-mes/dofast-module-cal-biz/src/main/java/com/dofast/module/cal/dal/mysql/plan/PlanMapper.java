package com.dofast.module.cal.dal.mysql.plan;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cal.dal.dataobject.plan.PlanDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cal.controller.admin.plan.vo.*;

/**
 * 排班计划 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface PlanMapper extends BaseMapperX<PlanDO> {

    default PageResult<PlanDO> selectPage(PlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlanDO>()
                .eqIfPresent(PlanDO::getPlanCode, reqVO.getPlanCode())
                .likeIfPresent(PlanDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(PlanDO::getCalendarType, reqVO.getCalendarType())
                .betweenIfPresent(PlanDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(PlanDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(PlanDO::getShiftType, reqVO.getShiftType())
                .eqIfPresent(PlanDO::getShiftMethod, reqVO.getShiftMethod())
                .eqIfPresent(PlanDO::getShiftCount, reqVO.getShiftCount())
                .eqIfPresent(PlanDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PlanDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PlanDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PlanDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PlanDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PlanDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanDO::getId));
    }

    default List<PlanDO> selectList(PlanExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PlanDO>()
                .eqIfPresent(PlanDO::getPlanCode, reqVO.getPlanCode())
                .likeIfPresent(PlanDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(PlanDO::getCalendarType, reqVO.getCalendarType())
                .betweenIfPresent(PlanDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(PlanDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(PlanDO::getShiftType, reqVO.getShiftType())
                .eqIfPresent(PlanDO::getShiftMethod, reqVO.getShiftMethod())
                .eqIfPresent(PlanDO::getShiftCount, reqVO.getShiftCount())
                .eqIfPresent(PlanDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PlanDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PlanDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PlanDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PlanDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PlanDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanDO::getId));
    }

}
