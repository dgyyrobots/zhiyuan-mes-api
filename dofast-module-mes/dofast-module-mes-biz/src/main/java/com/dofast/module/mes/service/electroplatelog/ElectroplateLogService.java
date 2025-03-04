package com.dofast.module.mes.service.electroplatelog;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.electroplatelog.vo.*;
import com.dofast.module.mes.dal.dataobject.electroplatelog.ElectroplateLogDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 制版房记录 Service 接口
 *
 * @author 惠智造
 */
public interface ElectroplateLogService {

    /**
     * 创建制版房记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createElectroplateLog(@Valid ElectroplateLogCreateReqVO createReqVO);

    /**
     * 更新制版房记录
     *
     * @param updateReqVO 更新信息
     */
    void updateElectroplateLog(@Valid ElectroplateLogUpdateReqVO updateReqVO);

    /**
     * 删除制版房记录
     *
     * @param id 编号
     */
    void deleteElectroplateLog(Long id);

    /**
     * 获得制版房记录
     *
     * @param id 编号
     * @return 制版房记录
     */
    ElectroplateLogDO getElectroplateLog(Long id);

    /**
     * 获得制版房记录列表
     *
     * @param ids 编号
     * @return 制版房记录列表
     */
    List<ElectroplateLogDO> getElectroplateLogList(Collection<Long> ids);

    /**
     * 获得制版房记录分页
     *
     * @param pageReqVO 分页查询
     * @return 制版房记录分页
     */
    PageResult<ElectroplateLogDO> getElectroplateLogPage(ElectroplateLogPageReqVO pageReqVO);

    /**
     * 获得制版房记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 制版房记录列表
     */
    List<ElectroplateLogDO> getElectroplateLogList(ElectroplateLogExportReqVO exportReqVO);

}
