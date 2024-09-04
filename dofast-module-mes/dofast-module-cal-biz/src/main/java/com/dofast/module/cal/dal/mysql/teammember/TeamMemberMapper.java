package com.dofast.module.cal.dal.mysql.teammember;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cal.dal.dataobject.teammember.TeamMemberDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cal.controller.admin.teammember.vo.*;

/**
 * 班组成员 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface TeamMemberMapper extends BaseMapperX<TeamMemberDO> {

    default PageResult<TeamMemberDO> selectPage(TeamMemberPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TeamMemberDO>()
                .eqIfPresent(TeamMemberDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(TeamMemberDO::getUserId, reqVO.getUserId())
                .likeIfPresent(TeamMemberDO::getUserName, reqVO.getUserName())
                .likeIfPresent(TeamMemberDO::getNickName, reqVO.getNickName())
                .eqIfPresent(TeamMemberDO::getTel, reqVO.getTel())
                .eqIfPresent(TeamMemberDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TeamMemberDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TeamMemberDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TeamMemberDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TeamMemberDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TeamMemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TeamMemberDO::getId));
    }

    default List<TeamMemberDO> selectList(TeamMemberExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TeamMemberDO>()
                .eqIfPresent(TeamMemberDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(TeamMemberDO::getUserId, reqVO.getUserId())
                .likeIfPresent(TeamMemberDO::getUserName, reqVO.getUserName())
                .likeIfPresent(TeamMemberDO::getNickName, reqVO.getNickName())
                .eqIfPresent(TeamMemberDO::getTel, reqVO.getTel())
                .eqIfPresent(TeamMemberDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TeamMemberDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TeamMemberDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TeamMemberDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TeamMemberDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TeamMemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TeamMemberDO::getId));
    }

}
