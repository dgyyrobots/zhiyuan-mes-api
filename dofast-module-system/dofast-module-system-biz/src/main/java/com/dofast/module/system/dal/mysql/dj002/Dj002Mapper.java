package com.dofast.module.system.dal.mysql.dj002;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.system.dal.dataobject.dj002.Dj002DO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.system.controller.admin.dj002.vo.*;

/**
 * 系统地址信息 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface Dj002Mapper extends BaseMapperX<Dj002DO> {

    default PageResult<Dj002DO> selectPage(Dj002PageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Dj002DO>()
                .likeIfPresent(Dj002DO::getDeptName, reqVO.getDeptName())
                .likeIfPresent(Dj002DO::getSysName, reqVO.getSysName())
                .eqIfPresent(Dj002DO::getSysUrl, reqVO.getSysUrl())
                .eqIfPresent(Dj002DO::getSysUrlNet, reqVO.getSysUrlNet())
                .eqIfPresent(Dj002DO::getSysLogUrl, reqVO.getSysLogUrl())
                .betweenIfPresent(Dj002DO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(Dj002DO::getCreateUser, reqVO.getCreateUser())
                .eqIfPresent(Dj002DO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(Dj002DO::getPg, reqVO.getPg())
                .orderByDesc(Dj002DO::getId));
    }

    default List<Dj002DO> selectList(Dj002ExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<Dj002DO>()
                .likeIfPresent(Dj002DO::getDeptName, reqVO.getDeptName())
                .likeIfPresent(Dj002DO::getSysName, reqVO.getSysName())
                .eqIfPresent(Dj002DO::getSysUrl, reqVO.getSysUrl())
                .eqIfPresent(Dj002DO::getSysUrlNet, reqVO.getSysUrlNet())
                .eqIfPresent(Dj002DO::getSysLogUrl, reqVO.getSysLogUrl())
                .betweenIfPresent(Dj002DO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(Dj002DO::getCreateUser, reqVO.getCreateUser())
                .eqIfPresent(Dj002DO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(Dj002DO::getPg, reqVO.getPg())
                .orderByDesc(Dj002DO::getId));
    }

}
