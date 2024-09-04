package com.dofast.module.wiki.service.coursewave;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wiki.controller.admin.coursewave.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewave.WikiCoursewaveDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 首页知识列表的信息	 Service 接口
 *
 * @author 惠智造
 */
public interface WikiCoursewaveService {

    /**
     * 创建首页知识列表的信息	
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoursewave(@Valid WikiCoursewaveCreateReqVO createReqVO);

    /**
     * 更新首页知识列表的信息	
     *
     * @param updateReqVO 更新信息
     */
    void updateCoursewave(@Valid WikiCoursewaveUpdateReqVO updateReqVO);

    /**
     * 删除首页知识列表的信息	
     *
     * @param id 编号
     */
    void deleteCoursewave(Long id);

    /**
     * 获得首页知识列表的信息	
     *
     * @param id 编号
     * @return 首页知识列表的信息	
     */
    WikiCoursewaveDO getCoursewave(Long id);

    /**
     * 获得首页知识列表的信息	列表
     *
     * @param ids 编号
     * @return 首页知识列表的信息	列表
     */
    List<WikiCoursewaveDO> getCoursewaveList(Collection<Long> ids);

    /**
     * 获得首页知识列表的信息	分页
     *
     * @param pageReqVO 分页查询
     * @return 首页知识列表的信息	分页
     */
    PageResult<WikiCoursewaveDO> getCoursewavePage(WikiCoursewavePageReqVO pageReqVO);

    /**
     * 获得首页知识列表的信息	列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 首页知识列表的信息	列表
     */
    List<WikiCoursewaveDO> getCoursewaveList(WikiCoursewaveExportReqVO exportReqVO);

}
