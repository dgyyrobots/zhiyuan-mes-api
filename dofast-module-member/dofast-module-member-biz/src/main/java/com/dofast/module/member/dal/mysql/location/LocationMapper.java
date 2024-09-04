package com.dofast.module.member.dal.mysql.location;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.member.controller.admin.loction.vo.LocationExportReqVO;
import com.dofast.module.member.controller.admin.loction.vo.LocationPageReqVO;
import com.dofast.module.member.dal.dataobject.location.LocationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 三级位置信息 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface LocationMapper extends BaseMapperX<LocationDO> {

    default PageResult<LocationDO> selectPage(LocationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LocationDO>()
                .likeIfPresent(LocationDO::getName, reqVO.getName())
                .eqIfPresent(LocationDO::getParentid, reqVO.getParentid())
                .likeIfPresent(LocationDO::getShortname, reqVO.getShortname())
                .eqIfPresent(LocationDO::getLeveltype, reqVO.getLeveltype())
                .eqIfPresent(LocationDO::getCitycode, reqVO.getCitycode())
                .eqIfPresent(LocationDO::getZipcode, reqVO.getZipcode())
                .eqIfPresent(LocationDO::getLng, reqVO.getLng())
                .eqIfPresent(LocationDO::getLat, reqVO.getLat())
                .eqIfPresent(LocationDO::getPinyin, reqVO.getPinyin())
                .eqIfPresent(LocationDO::getStatus, reqVO.getStatus())
                .orderByDesc(LocationDO::getId));
    }

    default List<LocationDO> selectList(LocationExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<LocationDO>()
                .likeIfPresent(LocationDO::getName, reqVO.getName())
                .eqIfPresent(LocationDO::getParentid, reqVO.getParentid())
                .likeIfPresent(LocationDO::getShortname, reqVO.getShortname())
                .eqIfPresent(LocationDO::getLeveltype, reqVO.getLeveltype())
                .eqIfPresent(LocationDO::getCitycode, reqVO.getCitycode())
                .eqIfPresent(LocationDO::getZipcode, reqVO.getZipcode())
                .eqIfPresent(LocationDO::getLng, reqVO.getLng())
                .eqIfPresent(LocationDO::getLat, reqVO.getLat())
                .eqIfPresent(LocationDO::getPinyin, reqVO.getPinyin())
                .eqIfPresent(LocationDO::getStatus, reqVO.getStatus())
                .orderByDesc(LocationDO::getId));
    }

    default List<LocationDO> getLocationList(Integer id){
        return selectList(new LambdaQueryWrapperX<LocationDO>()
                .eqIfPresent(LocationDO::getParentid, id)
                .orderByDesc(LocationDO::getId));
    }
}
