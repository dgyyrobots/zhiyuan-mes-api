package com.dofast.module.wiki.dal.mysql.coursewarefile;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wiki.dal.dataobject.coursewarefile.CoursewareFileDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wiki.controller.admin.coursewarefile.vo.*;

/**
 * 课件文件的保存地址 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CoursewareFileMapper extends BaseMapperX<CoursewareFileDO> {

    default PageResult<CoursewareFileDO> selectPage(CoursewareFilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CoursewareFileDO>()
                .eqIfPresent(CoursewareFileDO::getCoursewareId, reqVO.getCoursewareId())
                .likeIfPresent(CoursewareFileDO::getFilename, reqVO.getFilename())
                .eqIfPresent(CoursewareFileDO::getFilepath, reqVO.getFilepath())
                .eqIfPresent(CoursewareFileDO::getFileSize, reqVO.getFileSize())
                .betweenIfPresent(CoursewareFileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CoursewareFileDO::getId));
    }

    default List<CoursewareFileDO> selectList(CoursewareFileExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CoursewareFileDO>()
                .eqIfPresent(CoursewareFileDO::getCoursewareId, reqVO.getCoursewareId())
                .likeIfPresent(CoursewareFileDO::getFilename, reqVO.getFilename())
                .eqIfPresent(CoursewareFileDO::getFilepath, reqVO.getFilepath())
                .eqIfPresent(CoursewareFileDO::getFileSize, reqVO.getFileSize())
                .betweenIfPresent(CoursewareFileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CoursewareFileDO::getId));
    }

}
