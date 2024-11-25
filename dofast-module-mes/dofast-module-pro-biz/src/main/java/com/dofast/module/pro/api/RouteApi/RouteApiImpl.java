package com.dofast.module.pro.api.RouteApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductBaseVO;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductListVO;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.module.pro.dal.mysql.route.RouteMapper;
import com.dofast.module.pro.dal.mysql.routeproduct.RouteProductMapper;
import com.dofast.module.pro.service.route.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RouteApiImpl implements RouteApi{
    @Resource
    private RouteMapper routeMapper;

    @Resource
    private RouteProductMapper routeProductMapper;

    @Override
    public RouteDTO getRoute(Long id, String routeCode) {
//        RouteProductDO routeProductDO = routeProductMapper.checkItemUnique(new RouteProductBaseVO().setItemId(id));
        // 基于工艺编码获取工艺信息
        System.out.println("id:" + id + " routeCode:" + routeCode);
        RouteDO routeDO = routeMapper.selectOne(RouteDO::getRouteCode, routeCode);
        System.out.println("routeDO:" + routeDO);
        System.out.println("routeDO.getId():" + routeDO.getId());
        RouteProductDO routeProductDO = routeProductMapper.selectOne(RouteProductDO::getItemId, id ,RouteProductDO::getRouteId, routeDO.getId());
        System.out.println("routeProductDO:" + routeProductDO);
        //RouteDO routeDO = routeMapper.selectById(routeProductDO.getRouteId());
        RouteDTO routeDTO = BeanUtil.toBean(routeDO, RouteDTO.class);
        return routeDTO;
    }
}
