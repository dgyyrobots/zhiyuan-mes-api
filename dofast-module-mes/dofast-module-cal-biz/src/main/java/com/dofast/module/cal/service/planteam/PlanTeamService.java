package com.dofast.module.cal.service.planteam;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cal.controller.admin.planteam.vo.*;
import com.dofast.module.cal.dal.dataobject.planteam.PlanTeamDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 计划班组 Service 接口
 *
 * @author 惠智造
 */
public interface PlanTeamService {

    /**
     * 创建计划班组
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlanTeam(@Valid PlanTeamCreateReqVO createReqVO);

    /**
     * 更新计划班组
     *
     * @param updateReqVO 更新信息
     */
    void updatePlanTeam(@Valid PlanTeamUpdateReqVO updateReqVO);

    /**
     * 删除计划班组
     *
     * @param id 编号
     */
    void deletePlanTeam(Long id);

    /**
     * 获得计划班组
     *
     * @param id 编号
     * @return 计划班组
     */
    PlanTeamDO getPlanTeam(Long id);

    /**
     * 获得计划班组列表
     *
     * @param ids 编号
     * @return 计划班组列表
     */
    List<PlanTeamDO> getPlanTeamList(Collection<Long> ids);

    /**
     * 获得计划班组分页
     *
     * @param pageReqVO 分页查询
     * @return 计划班组分页
     */
    PageResult<PlanTeamDO> getPlanTeamPage(PlanTeamPageReqVO pageReqVO);

    /**
     * 获得计划班组列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 计划班组列表
     */
    List<PlanTeamDO> getPlanTeamList(PlanTeamExportReqVO exportReqVO);

}
