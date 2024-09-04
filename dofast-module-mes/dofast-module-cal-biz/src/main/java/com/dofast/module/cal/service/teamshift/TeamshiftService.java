package com.dofast.module.cal.service.teamshift;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cal.controller.admin.teamshift.vo.*;
import com.dofast.module.cal.dal.dataobject.teamshift.TeamshiftDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 班组排班 Service 接口
 *
 * @author 惠智造
 */
public interface TeamshiftService {

    /**
     * 创建班组排班
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTeamshift(@Valid TeamshiftCreateReqVO createReqVO);

    /**
     * 更新班组排班
     *
     * @param updateReqVO 更新信息
     */
    void updateTeamshift(@Valid TeamshiftUpdateReqVO updateReqVO);

    /**
     * 删除班组排班
     *
     * @param id 编号
     */
    void deleteTeamshift(Long id);

    /**
     * 获得班组排班
     *
     * @param id 编号
     * @return 班组排班
     */
    TeamshiftDO getTeamshift(Long id);

    /**
     * 获得班组排班列表
     *
     * @param ids 编号
     * @return 班组排班列表
     */
    List<TeamshiftDO> getTeamshiftList(Collection<Long> ids);

    /**
     * 获得班组排班分页
     *
     * @param pageReqVO 分页查询
     * @return 班组排班分页
     */
    PageResult<TeamshiftDO> getTeamshiftPage(TeamshiftPageReqVO pageReqVO);

    /**
     * 获得班组排班列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 班组排班列表
     */
    List<TeamshiftDO> getTeamshiftList(TeamshiftExportReqVO exportReqVO);

}
