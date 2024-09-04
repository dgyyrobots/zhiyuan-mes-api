package com.dofast.module.wiki.dal.mysql.coursewave;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wiki.dal.dataobject.coursewave.WikiCoursewaveDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wiki.controller.admin.coursewave.vo.*;

/**
 * 首页知识列表的信息	 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface WikiCoursewaveMapper extends BaseMapperX<WikiCoursewaveDO> {

    default PageResult<WikiCoursewaveDO> selectPage(WikiCoursewavePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WikiCoursewaveDO>()
                .eqIfPresent(WikiCoursewaveDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(WikiCoursewaveDO::getLecturerId,reqVO.getLecturerId())
                .likeIfPresent(WikiCoursewaveDO::getCoursewareName, reqVO.getCoursewareName())
                .betweenIfPresent(WikiCoursewaveDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WikiCoursewaveDO::getId));
    }

    default List<WikiCoursewaveDO> selectList(WikiCoursewaveExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WikiCoursewaveDO>()
                .eqIfPresent(WikiCoursewaveDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(WikiCoursewaveDO::getCoursewareName, reqVO.getCoursewareName())
                .betweenIfPresent(WikiCoursewaveDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WikiCoursewaveDO::getId));
    }

}
