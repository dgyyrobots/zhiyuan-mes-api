package com.dofast.module.cmms.service.dvsubject;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cmms.controller.admin.dvsubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvsubject.DvSubjectDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备点检保养项目 Service 接口
 *
 * @author 芋道源码
 */
public interface DvSubjectService {
    String checkSubjectCodeUnique(DvSubjectBaseVO  baseVO);
    /**
     * 创建设备点检保养项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDvSubject(@Valid DvSubjectCreateReqVO createReqVO);

    /**
     * 更新设备点检保养项目
     *
     * @param updateReqVO 更新信息
     */
    void updateDvSubject(@Valid DvSubjectUpdateReqVO updateReqVO);

    /**
     * 删除设备点检保养项目
     *
     * @param id 编号
     */
    void deleteDvSubject(Long id);

    /**
     * 获得设备点检保养项目
     *
     * @param id 编号
     * @return 设备点检保养项目
     */
    DvSubjectDO getDvSubject(Long id);

    /**
     * 获得设备点检保养项目列表
     *
     * @param ids 编号
     * @return 设备点检保养项目列表
     */
    List<DvSubjectDO> getDvSubjectList(Collection<Long> ids);

    /**
     * 获得设备点检保养项目分页
     *
     * @param pageReqVO 分页查询
     * @return 设备点检保养项目分页
     */
    PageResult<DvSubjectDO> getDvSubjectPage(DvSubjectPageReqVO pageReqVO);

    /**
     * 获得设备点检保养项目列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备点检保养项目列表
     */
    List<DvSubjectDO> getDvSubjectList(DvSubjectExportReqVO exportReqVO);

}
