package com.dofast.module.pro.service.routeproductbom;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.routeproductbom.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproductbom.RouteProductBomDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品制程物料BOM Service 接口
 *
 * @author 芋道源码
 */
public interface RouteProductBomService {
    List<RouteProductBomDO> selectList(RouteProductBomListVO listVO);
    /**
     * 根据工艺路线ID删除所有产品BOM设置
     * @param routeId
     * @return
     */
    public int deleteByRouteId(Long routeId);
    /**
     * 检测是否重复配置
     * @param baseVO
     * @return
     */
    public String checkUnique(RouteProductBomBaseVO baseVO);

    /**
     * 根据工艺路线ID和产品ID删除BOM设置
     * @param baseVO
     * @return
     */
    public int deleteByRouteIdAndProductId(Long routeId,Long productId);
    /**
     * 创建产品制程物料BOM
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRouteProductBom(@Valid RouteProductBomCreateReqVO createReqVO);

    /**
     * 更新产品制程物料BOM
     *
     * @param updateReqVO 更新信息
     */
    void updateRouteProductBom(@Valid RouteProductBomUpdateReqVO updateReqVO);

    /**
     * 删除产品制程物料BOM
     *
     * @param id 编号
     */
    void deleteRouteProductBom(Long id);

    /**
     * 获得产品制程物料BOM
     *
     * @param id 编号
     * @return 产品制程物料BOM
     */
    RouteProductBomDO getRouteProductBom(Long id);

    /**
     * 获得产品制程物料BOM列表
     *
     * @param ids 编号
     * @return 产品制程物料BOM列表
     */
    List<RouteProductBomDO> getRouteProductBomList(Collection<Long> ids);

    /**
     * 获得产品制程物料BOM分页
     *
     * @param pageReqVO 分页查询
     * @return 产品制程物料BOM分页
     */
    PageResult<RouteProductBomDO> getRouteProductBomPage(RouteProductBomPageReqVO pageReqVO);

    /**
     * 获得产品制程物料BOM列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品制程物料BOM列表
     */
    List<RouteProductBomDO> getRouteProductBomList(RouteProductBomExportReqVO exportReqVO);

}
