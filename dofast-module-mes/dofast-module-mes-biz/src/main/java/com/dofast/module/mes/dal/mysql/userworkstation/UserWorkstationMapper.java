package com.dofast.module.mes.dal.mysql.userworkstation;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.mes.controller.admin.userworkstation.vo.*;

/**
 * 用户工作站绑定关系 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface UserWorkstationMapper extends BaseMapperX<UserWorkstationDO> {

    default PageResult<UserWorkstationDO> selectPage(UserWorkstationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserWorkstationDO>()
                .eqIfPresent(UserWorkstationDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserWorkstationDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserWorkstationDO::getNickName, reqVO.getNickName())
                .eqIfPresent(UserWorkstationDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(UserWorkstationDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(UserWorkstationDO::getWorkstationName, reqVO.getWorkstationName())
                .betweenIfPresent(UserWorkstationDO::getOperationTime, reqVO.getOperationTime())
                .eqIfPresent(UserWorkstationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UserWorkstationDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(UserWorkstationDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(UserWorkstationDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(UserWorkstationDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(UserWorkstationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserWorkstationDO::getId));
    }

    default List<UserWorkstationDO> selectList(UserWorkstationExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserWorkstationDO>()
                .eqIfPresent(UserWorkstationDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserWorkstationDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserWorkstationDO::getNickName, reqVO.getNickName())
                .eqIfPresent(UserWorkstationDO::getWorkstationId, reqVO.getWorkstationId())
                .eqIfPresent(UserWorkstationDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(UserWorkstationDO::getWorkstationName, reqVO.getWorkstationName())
                .betweenIfPresent(UserWorkstationDO::getOperationTime, reqVO.getOperationTime())
                .eqIfPresent(UserWorkstationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UserWorkstationDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(UserWorkstationDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(UserWorkstationDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(UserWorkstationDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(UserWorkstationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserWorkstationDO::getId));
    }

}
