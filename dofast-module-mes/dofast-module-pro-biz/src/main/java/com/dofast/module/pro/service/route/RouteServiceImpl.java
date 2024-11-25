package com.dofast.module.pro.service.route;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.route.vo.*;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.route.RouteConvert;
import com.dofast.module.pro.dal.mysql.route.RouteMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 工艺路线 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RouteServiceImpl implements RouteService {

    @Resource
    private RouteMapper routeMapper;

    @Override
    public String checkRouteCodeUnique(RouteBaseVO baseVO) {
        RouteDO route = routeMapper.checkRouteCodeUnique(baseVO);
        Long routeId = baseVO.getId()==null?-1L:baseVO.getId();
        if(StrUtils.isNotNull(route) && route.getId().longValue() != routeId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createRoute(RouteCreateReqVO createReqVO) {
        // 插入
        RouteDO route = RouteConvert.INSTANCE.convert(createReqVO);
        routeMapper.insert(route);
        // 返回
        return route.getId();
    }

    @Override
    public void updateRoute(RouteUpdateReqVO updateReqVO) {
        // 校验存在
        validateRouteExists(updateReqVO.getId());
        // 更新
        RouteDO updateObj = RouteConvert.INSTANCE.convert(updateReqVO);
        routeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoute(Long id) {
        // 校验存在
        validateRouteExists(id);
        // 删除
        routeMapper.deleteById(id);
    }

    private void validateRouteExists(Long id) {
        if (routeMapper.selectById(id) == null) {
            throw exception(ROUTE_NOT_EXISTS);
        }
    }

    @Override
    public RouteDO getRoute(Long id) {
        return routeMapper.selectById(id);
    }

    @Override
    public RouteDO getRoute(String routeCode) {
        return routeMapper.selectOne(RouteDO::getRouteCode, routeCode);
    }


    @Override
    public List<RouteDO> getRouteList(Collection<Long> ids) {
        return routeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RouteDO> getRoutePage(RoutePageReqVO pageReqVO) {
        return routeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RouteDO> getRouteList(RouteExportReqVO exportReqVO) {
        return routeMapper.selectList(exportReqVO);
    }

    /**
     * 根据工艺路线编码获取工艺路线
     * @param code
     * @return
     */
    @Override
    public  RouteDO getRouteByCode(String code){
        return routeMapper.selectOne(RouteDO::getRouteCode, code);
    }



}
