package com.dofast.module.wiki.service.coursewarefile;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wiki.controller.admin.coursewarefile.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewarefile.CoursewareFileDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wiki.convert.coursewarefile.CoursewareFileConvert;
import com.dofast.module.wiki.dal.mysql.coursewarefile.CoursewareFileMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wiki.enums.ErrorCodeConstants.*;

/**
 * 课件文件的保存地址 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CoursewareFileServiceImpl implements CoursewareFileService {

    @Resource
    private CoursewareFileMapper coursewareFileMapper;

    @Override
    public Long createCoursewareFile(CoursewareFileCreateReqVO createReqVO) {
        // 插入
        CoursewareFileDO coursewareFile = CoursewareFileConvert.INSTANCE.convert(createReqVO);
        coursewareFileMapper.insert(coursewareFile);
        // 返回
        return coursewareFile.getId();
    }

    @Override
    public void updateCoursewareFile(CoursewareFileUpdateReqVO updateReqVO) {
        // 校验存在
        validateCoursewareFileExists(updateReqVO.getId());
        // 更新
        CoursewareFileDO updateObj = CoursewareFileConvert.INSTANCE.convert(updateReqVO);
        coursewareFileMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoursewareFile(Long id) {
        // 校验存在
        validateCoursewareFileExists(id);
        // 删除
        coursewareFileMapper.deleteById(id);
    }

    private void validateCoursewareFileExists(Long id) {
        if (coursewareFileMapper.selectById(id) == null) {
            throw exception(COURSEWARE_FILE_NOT_EXISTS);
        }
    }

    @Override
    public CoursewareFileDO getCoursewareFile(Long id) {
        return coursewareFileMapper.selectById(id);
    }

    @Override
    public List<CoursewareFileDO> getCoursewareFileList(Collection<Long> ids) {
        return coursewareFileMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CoursewareFileDO> getCoursewareFilePage(CoursewareFilePageReqVO pageReqVO) {
        return coursewareFileMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CoursewareFileDO> getCoursewareFileList(CoursewareFileExportReqVO exportReqVO) {
        return coursewareFileMapper.selectList(exportReqVO);
    }

}
