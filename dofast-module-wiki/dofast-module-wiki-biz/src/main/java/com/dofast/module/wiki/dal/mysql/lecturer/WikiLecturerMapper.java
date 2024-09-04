package com.dofast.module.wiki.dal.mysql.lecturer;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wiki.dal.dataobject.lecturer.WikiLecturerDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wiki.controller.admin.lecturer.vo.*;

/**
 * 讲师的信息	 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface WikiLecturerMapper extends BaseMapperX<WikiLecturerDO> {

    default PageResult<WikiLecturerDO> selectPage(WikiLecturerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WikiLecturerDO>()
                .likeIfPresent(WikiLecturerDO::getName, reqVO.getName())
                .eqIfPresent(WikiLecturerDO::getDirection, reqVO.getDirection())
                .eqIfPresent(WikiLecturerDO::getPicture, reqVO.getPicture())
                .betweenIfPresent(WikiLecturerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WikiLecturerDO::getId));
    }

    default List<WikiLecturerDO> selectList(WikiLecturerExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WikiLecturerDO>()
                .likeIfPresent(WikiLecturerDO::getName, reqVO.getName())
                .eqIfPresent(WikiLecturerDO::getDirection, reqVO.getDirection())
                .eqIfPresent(WikiLecturerDO::getPicture, reqVO.getPicture())
                .betweenIfPresent(WikiLecturerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WikiLecturerDO::getId));
    }

}
