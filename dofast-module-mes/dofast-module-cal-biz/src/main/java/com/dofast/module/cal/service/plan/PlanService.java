package com.dofast.module.cal.service.plan;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cal.controller.admin.plan.vo.*;
import com.dofast.module.cal.dal.dataobject.plan.PlanDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 排班计划 Service 接口
 *
 * @author 惠智造
 */
public interface PlanService {

    /**
     * 创建排班计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlan(@Valid PlanCreateReqVO createReqVO);

    /**
     * 更新排班计划
     *
     * @param updateReqVO 更新信息
     */
    void updatePlan(@Valid PlanUpdateReqVO updateReqVO);

    /**
     * 删除排班计划
     *
     * @param id 编号
     */
    void deletePlan(Long id);

    /**
     * 获得排班计划
     *
     * @param id 编号
     * @return 排班计划
     */
    PlanDO getPlan(Long id);

    /**
     * 获得排班计划列表
     *
     * @param ids 编号
     * @return 排班计划列表
     */
    List<PlanDO> getPlanList(Collection<Long> ids);

    /**
     * 获得排班计划分页
     *
     * @param pageReqVO 分页查询
     * @return 排班计划分页
     */
    PageResult<PlanDO> getPlanPage(PlanPageReqVO pageReqVO);

    /**
     * 获得排班计划列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 排班计划列表
     */
    List<PlanDO> getPlanList(PlanExportReqVO exportReqVO);

}
