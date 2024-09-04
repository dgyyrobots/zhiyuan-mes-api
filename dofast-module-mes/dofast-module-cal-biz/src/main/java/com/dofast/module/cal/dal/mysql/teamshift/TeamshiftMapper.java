package com.dofast.module.cal.dal.mysql.teamshift;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cal.dal.dataobject.teamshift.TeamshiftDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cal.controller.admin.teamshift.vo.*;

/**
 * 班组排班 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface TeamshiftMapper extends BaseMapperX<TeamshiftDO> {

    default PageResult<TeamshiftDO> selectPage(TeamshiftPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TeamshiftDO>()
                .eqIfPresent(TeamshiftDO::getTheDay, reqVO.getTheDay())
                .eqIfPresent(TeamshiftDO::getTeamId, reqVO.getTeamId())
                .likeIfPresent(TeamshiftDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(TeamshiftDO::getShiftId, reqVO.getShiftId())
                .likeIfPresent(TeamshiftDO::getShiftName, reqVO.getShiftName())
                .eqIfPresent(TeamshiftDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(TeamshiftDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(TeamshiftDO::getCalendarType, reqVO.getCalendarType())
                .eqIfPresent(TeamshiftDO::getShiftType, reqVO.getShiftType())
                .eqIfPresent(TeamshiftDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TeamshiftDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TeamshiftDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TeamshiftDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TeamshiftDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TeamshiftDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TeamshiftDO::getId));
    }

    default List<TeamshiftDO> selectList(TeamshiftExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TeamshiftDO>()
                .eqIfPresent(TeamshiftDO::getTheDay, reqVO.getTheDay())
                .eqIfPresent(TeamshiftDO::getTeamId, reqVO.getTeamId())
                .likeIfPresent(TeamshiftDO::getTeamName, reqVO.getTeamName())
                .eqIfPresent(TeamshiftDO::getShiftId, reqVO.getShiftId())
                .likeIfPresent(TeamshiftDO::getShiftName, reqVO.getShiftName())
                .eqIfPresent(TeamshiftDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(TeamshiftDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(TeamshiftDO::getCalendarType, reqVO.getCalendarType())
                .eqIfPresent(TeamshiftDO::getShiftType, reqVO.getShiftType())
                .eqIfPresent(TeamshiftDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TeamshiftDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TeamshiftDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TeamshiftDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TeamshiftDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TeamshiftDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TeamshiftDO::getId));
    }

}
