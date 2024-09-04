package com.dofast.module.cmms.service.dvchecksubject;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cmms.controller.admin.dvchecksubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvchecksubject.DvCheckSubjectDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 点检项目 Service 接口
 *
 * @author 芋道源码
 */
public interface DvCheckSubjectService {
    /**
     * 检查当前计划下，点检项目是否唯一
     * @param dvCheckSubject
     * @return
     */
    public String checkSubjectUnique(DvCheckSubjectBaseVO dvCheckSubject);
    /**
     * 创建点检项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDvCheckSubject(@Valid DvCheckSubjectCreateReqVO createReqVO);

    /**
     * 更新点检项目
     *
     * @param updateReqVO 更新信息
     */
    void updateDvCheckSubject(@Valid DvCheckSubjectUpdateReqVO updateReqVO);

    /**
     * 删除点检项目
     *
     * @param id 编号
     */
    void deleteDvCheckSubject(Long id);

    /**
     * 获得点检项目
     *
     * @param id 编号
     * @return 点检项目
     */
    DvCheckSubjectDO getDvCheckSubject(Long id);

    /**
     * 获得点检项目列表
     *
     * @param ids 编号
     * @return 点检项目列表
     */
    List<DvCheckSubjectDO> getDvCheckSubjectList(Collection<Long> ids);

    /**
     * 获得点检项目分页
     *
     * @param pageReqVO 分页查询
     * @return 点检项目分页
     */
    PageResult<DvCheckSubjectDO> getDvCheckSubjectPage(DvCheckSubjectPageReqVO pageReqVO);

    /**
     * 获得点检项目列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 点检项目列表
     */
    List<DvCheckSubjectDO> getDvCheckSubjectList(DvCheckSubjectExportReqVO exportReqVO);

}
