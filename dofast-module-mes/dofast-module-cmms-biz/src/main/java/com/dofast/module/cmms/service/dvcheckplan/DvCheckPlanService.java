package com.dofast.module.cmms.service.dvcheckplan;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cmms.controller.admin.dvcheckplan.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvcheckplan.DvCheckPlanDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 设备点检保养计划头 Service 接口
 *
 * @author 芋道源码
 */
public interface DvCheckPlanService {
    String  checkPlanCodeUnique (DvCheckPlanBaseVO baseVO );
    /**
     * 创建设备点检保养计划头
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDvCheckPlan(@Valid DvCheckPlanCreateReqVO createReqVO);

    /**
     * 更新设备点检保养计划头
     *
     * @param updateReqVO 更新信息
     */
    void updateDvCheckPlan(@Valid DvCheckPlanUpdateReqVO updateReqVO);

    /**
     * 删除设备点检保养计划头
     *
     * @param id 编号
     */
    void deleteDvCheckPlan(Long id);

    /**
     * 获得设备点检保养计划头
     *
     * @param id 编号
     * @return 设备点检保养计划头
     */
    DvCheckPlanDO getDvCheckPlan(Long id);

    /**
     * 获得设备点检保养计划头列表
     *
     * @param ids 编号
     * @return 设备点检保养计划头列表
     */
    List<DvCheckPlanDO> getDvCheckPlanList(Collection<Long> ids);

    /**
     * 获得设备点检保养计划头分页
     *
     * @param pageReqVO 分页查询
     * @return 设备点检保养计划头分页
     */
    PageResult<DvCheckPlanDO> getDvCheckPlanPage(DvCheckPlanPageReqVO pageReqVO);

    /**
     * 获得设备点检保养计划头列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 设备点检保养计划头列表
     */
    List<DvCheckPlanDO> getDvCheckPlanList(DvCheckPlanExportReqVO exportReqVO);

}
