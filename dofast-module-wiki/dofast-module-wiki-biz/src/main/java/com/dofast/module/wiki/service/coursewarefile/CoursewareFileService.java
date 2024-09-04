package com.dofast.module.wiki.service.coursewarefile;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wiki.controller.admin.coursewarefile.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewarefile.CoursewareFileDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 课件文件的保存地址 Service 接口
 *
 * @author 惠智造
 */
public interface CoursewareFileService {

    /**
     * 创建课件文件的保存地址
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoursewareFile(@Valid CoursewareFileCreateReqVO createReqVO);

    /**
     * 更新课件文件的保存地址
     *
     * @param updateReqVO 更新信息
     */
    void updateCoursewareFile(@Valid CoursewareFileUpdateReqVO updateReqVO);

    /**
     * 删除课件文件的保存地址
     *
     * @param id 编号
     */
    void deleteCoursewareFile(Long id);

    /**
     * 获得课件文件的保存地址
     *
     * @param id 编号
     * @return 课件文件的保存地址
     */
    CoursewareFileDO getCoursewareFile(Long id);

    /**
     * 获得课件文件的保存地址列表
     *
     * @param ids 编号
     * @return 课件文件的保存地址列表
     */
    List<CoursewareFileDO> getCoursewareFileList(Collection<Long> ids);

    /**
     * 获得课件文件的保存地址分页
     *
     * @param pageReqVO 分页查询
     * @return 课件文件的保存地址分页
     */
    PageResult<CoursewareFileDO> getCoursewareFilePage(CoursewareFilePageReqVO pageReqVO);

    /**
     * 获得课件文件的保存地址列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 课件文件的保存地址列表
     */
    List<CoursewareFileDO> getCoursewareFileList(CoursewareFileExportReqVO exportReqVO);

}
