package com.dofast.module.system.dal.mysql.user;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.system.dal.dataobject.user.UsersFaceDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.system.controller.admin.user.vo.usersFace.*;

/**
 * 用户人脸数据 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface UsersFaceMapper extends BaseMapperX<UsersFaceDO> {

    default PageResult<UsersFaceDO> selectPage(UsersFacePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UsersFaceDO>()
                .eqIfPresent(UsersFaceDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(UsersFaceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UsersFaceDO::getFaceImg, reqVO.getFaceImg())
                .orderByDesc(UsersFaceDO::getId));
    }

    default List<UsersFaceDO> selectList(UsersFaceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UsersFaceDO>()
                .eqIfPresent(UsersFaceDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(UsersFaceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UsersFaceDO::getFaceImg, reqVO.getFaceImg())
                .orderByDesc(UsersFaceDO::getId));
    }

}
