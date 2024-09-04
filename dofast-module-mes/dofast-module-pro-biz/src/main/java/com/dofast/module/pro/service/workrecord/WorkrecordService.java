package com.dofast.module.pro.service.workrecord;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.workrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.workrecord.WorkrecordDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 上下工记录 Service 接口
 *
 * @author 惠智造
 */
public interface WorkrecordService {

    /**
     * 创建上下工记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkrecord(@Valid WorkrecordCreateReqVO createReqVO);

    /**
     * 更新上下工记录
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkrecord(@Valid WorkrecordUpdateReqVO updateReqVO);

    /**
     * 删除上下工记录
     *
     * @param id 编号
     */
    void deleteWorkrecord(Long id);

    /**
     * 获得上下工记录
     *
     * @param id 编号
     * @return 上下工记录
     */
    WorkrecordDO getWorkrecord(Long id);

    /**
     * 获得上下工记录列表
     *
     * @param ids 编号
     * @return 上下工记录列表
     */
    List<WorkrecordDO> getWorkrecordList(Collection<Long> ids);

    /**
     * 获得上下工记录分页
     *
     * @param pageReqVO 分页查询
     * @return 上下工记录分页
     */
    PageResult<WorkrecordDO> getWorkrecordPage(WorkrecordPageReqVO pageReqVO);

    /**
     * 获得上下工记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 上下工记录列表
     */
    List<WorkrecordDO> getWorkrecordList(WorkrecordExportReqVO exportReqVO);

}
