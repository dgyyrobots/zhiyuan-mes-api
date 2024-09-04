package com.dofast.module.pro.service.routeproduct;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.routeproduct.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品制程 Service 接口
 *
 * @author 芋道源码
 */
public interface RouteProductService {
    List<RouteProductDO> selectList(RouteProductListVO listVO);
    public int deleteByRouteId(Long routeId);
    public String checkItemUnique(RouteProductBaseVO baseVO);
    /**
     * 创建产品制程
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRouteProduct(@Valid RouteProductCreateReqVO createReqVO);

    /**
     * 更新产品制程
     *
     * @param updateReqVO 更新信息
     */
    int updateRouteProduct(@Valid RouteProductUpdateReqVO updateReqVO);

    /**
     * 删除产品制程
     *
     * @param id 编号
     */
    void deleteRouteProduct(Long id);

    /**
     * 获得产品制程
     *
     * @param id 编号
     * @return 产品制程
     */
    RouteProductDO getRouteProduct(Long id);

    /**
     * 获得产品制程列表
     *
     * @param ids 编号
     * @return 产品制程列表
     */
    List<RouteProductDO> getRouteProductList(Collection<Long> ids);

    /**
     * 获得产品制程分页
     *
     * @param pageReqVO 分页查询
     * @return 产品制程分页
     */
    PageResult<RouteProductDO> getRouteProductPage(RouteProductPageReqVO pageReqVO);

    /**
     * 获得产品制程列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品制程列表
     */
    List<RouteProductDO> getRouteProductList(RouteProductExportReqVO exportReqVO);

}
