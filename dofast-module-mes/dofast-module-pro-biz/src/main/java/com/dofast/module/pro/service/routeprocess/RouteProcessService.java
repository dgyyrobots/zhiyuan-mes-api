package com.dofast.module.pro.service.routeprocess;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.routeprocess.vo.*;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工艺组成 Service 接口
 *
 * @author 芋道源码
 */
public interface RouteProcessService {
    public int deleteByRouteId(Long routeId);
    /**
     * 查找下一个工序
     * @param baseVO
     * @return
     */
    public RouteProcessDO findNextProcess(RouteProcessBaseVO baseVO);
    /**
     * 查找上一个工序
     * @param baseVO
     * @return
     */
    public RouteProcessDO findPreProcess(RouteProcessBaseVO baseVO);
    /**
     * 检查某个报工单对应的工序是否是关键工序
     * @param feedback
     * @return
     */
    public boolean checkKeyProcess(FeedbackDO feedback);
    /**
     * 检查序号是否已经存在
     * @param proRouteProcess
     * @return
     */
    public String checkOrderNumExists(RouteProcessBaseVO proRouteProcess);

    /**
     * 检查工序是否已经存在
     * @param proRouteProcess
     * @return
     */
    public String checkProcessExists(RouteProcessBaseVO proRouteProcess);

    /**
     * 检查当前工艺路线中是否已经有某个工序配置了update_flag=Y
     * @param proRouteProcess
     * @return
     */
    public String checkUpdateFlagUnique(RouteProcessBaseVO proRouteProcess);
    List<RouteProcessDO> selectList(RouteProcessListVO listVO);
    /**
     * 创建工艺组成
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRouteProcess(@Valid RouteProcessCreateReqVO createReqVO);

    /**
     * 更新工艺组成
     *
     * @param updateReqVO 更新信息
     */
    void updateRouteProcess(@Valid RouteProcessUpdateReqVO updateReqVO);

    /**
     * 删除工艺组成
     *
     * @param id 编号
     */
    void deleteRouteProcess(Long id);

    /**
     * 获得工艺组成
     *
     * @param id 编号
     * @return 工艺组成
     */
    RouteProcessDO getRouteProcess(Long id);

    /**
     * 获得工艺组成列表
     *
     * @param ids 编号
     * @return 工艺组成列表
     */
    List<RouteProcessDO> getRouteProcessList(Collection<Long> ids);

    /**
     * 获得工艺组成分页
     *
     * @param pageReqVO 分页查询
     * @return 工艺组成分页
     */
    PageResult<RouteProcessDO> getRouteProcessPage(RouteProcessPageReqVO pageReqVO);

    /**
     * 获得工艺组成列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工艺组成列表
     */
    List<RouteProcessDO> getRouteProcessList(RouteProcessExportReqVO exportReqVO);

}
