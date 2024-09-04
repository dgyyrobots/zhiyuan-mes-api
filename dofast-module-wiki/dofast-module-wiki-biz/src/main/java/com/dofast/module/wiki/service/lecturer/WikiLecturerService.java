package com.dofast.module.wiki.service.lecturer;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wiki.controller.admin.lecturer.vo.*;
import com.dofast.module.wiki.dal.dataobject.lecturer.WikiLecturerDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 讲师的信息	 Service 接口
 *
 * @author 惠智造
 */
public interface WikiLecturerService {

    /**
     * 创建讲师的信息	
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLecturer(@Valid WikiLecturerCreateReqVO createReqVO);

    /**
     * 更新讲师的信息	
     *
     * @param updateReqVO 更新信息
     */
    void updateLecturer(@Valid WikiLecturerUpdateReqVO updateReqVO);

    /**
     * 删除讲师的信息	
     *
     * @param id 编号
     */
    void deleteLecturer(Long id);

    /**
     * 获得讲师的信息	
     *
     * @param id 编号
     * @return 讲师的信息	
     */
    WikiLecturerDO getLecturer(Long id);

    /**
     * 获得讲师的信息	列表
     *
     * @param ids 编号
     * @return 讲师的信息	列表
     */
    List<WikiLecturerDO> getLecturerList(Collection<Long> ids);

    /**
     * 获得讲师的信息	分页
     *
     * @param pageReqVO 分页查询
     * @return 讲师的信息	分页
     */
    PageResult<WikiLecturerDO> getLecturerPage(WikiLecturerPageReqVO pageReqVO);

    /**
     * 获得讲师的信息	列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 讲师的信息	列表
     */
    List<WikiLecturerDO> getLecturerList(WikiLecturerExportReqVO exportReqVO);

}
