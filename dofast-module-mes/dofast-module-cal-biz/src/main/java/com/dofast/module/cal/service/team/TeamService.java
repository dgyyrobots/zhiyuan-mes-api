package com.dofast.module.cal.service.team;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cal.controller.admin.team.vo.*;
import com.dofast.module.cal.dal.dataobject.team.TeamDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 班组 Service 接口
 *
 * @author 惠智造
 */
public interface TeamService {

    /**
     * 创建班组
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTeam(@Valid TeamCreateReqVO createReqVO);

    /**
     * 更新班组
     *
     * @param updateReqVO 更新信息
     */
    void updateTeam(@Valid TeamUpdateReqVO updateReqVO);

    /**
     * 删除班组
     *
     * @param id 编号
     */
    void deleteTeam(Long id);

    /**
     * 获得班组
     *
     * @param id 编号
     * @return 班组
     */
    TeamDO getTeam(Long id);

    /**
     * 获得班组
     *
     * @param teamCode 编号
     * @return 班组
     */
    TeamDO getTeam(String teamCode);


    /**
     * 获得班组列表
     *
     * @param ids 编号
     * @return 班组列表
     */
    List<TeamDO> getTeamList(Collection<Long> ids);

    /**
     * 获得班组分页
     *
     * @param pageReqVO 分页查询
     * @return 班组分页
     */
    PageResult<TeamDO> getTeamPage(TeamPageReqVO pageReqVO);

    /**
     * 获得班组列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 班组列表
     */
    List<TeamDO> getTeamList(TeamExportReqVO exportReqVO);

}
