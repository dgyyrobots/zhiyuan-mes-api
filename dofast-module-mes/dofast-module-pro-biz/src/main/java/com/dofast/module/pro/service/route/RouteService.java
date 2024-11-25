package com.dofast.module.pro.service.route;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.route.vo.*;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工艺路线 Service 接口
 *
 * @author 芋道源码
 */
public interface RouteService {
    public String checkRouteCodeUnique(RouteBaseVO baseVO);
    /**
     * 创建工艺路线
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoute(@Valid RouteCreateReqVO createReqVO);

    /**
     * 更新工艺路线
     *
     * @param updateReqVO 更新信息
     */
    void updateRoute(@Valid RouteUpdateReqVO updateReqVO);

    /**
     * 删除工艺路线
     *
     * @param id 编号
     */
    void deleteRoute(Long id);

    /**
     * 获得工艺路线
     *
     * @param id 编号
     * @return 工艺路线
     */
    RouteDO getRoute(Long id);

    /**
     * 获得工艺路线
     *
     * @param routeCode 工序编号
     * @return 工艺路线
     */
    RouteDO getRoute(String routeCode);


    /**
     * 获得工艺路线列表
     *
     * @param ids 编号
     * @return 工艺路线列表
     */
    List<RouteDO> getRouteList(Collection<Long> ids);

    /**
     * 获得工艺路线分页
     *
     * @param pageReqVO 分页查询
     * @return 工艺路线分页
     */
    PageResult<RouteDO> getRoutePage(RoutePageReqVO pageReqVO);

    /**
     * 获得工艺路线列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工艺路线列表
     */
    List<RouteDO> getRouteList(RouteExportReqVO exportReqVO);

    /**
     * 根据工艺路线编码获取工艺路线
     * @param code
     * @return
     */
    RouteDO getRouteByCode(String code);

}
