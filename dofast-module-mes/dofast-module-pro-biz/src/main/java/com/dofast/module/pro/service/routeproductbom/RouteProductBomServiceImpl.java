package com.dofast.module.pro.service.routeproductbom;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.routeproductbom.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproductbom.RouteProductBomDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.routeproductbom.RouteProductBomConvert;
import com.dofast.module.pro.dal.mysql.routeproductbom.RouteProductBomMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 产品制程物料BOM Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RouteProductBomServiceImpl implements RouteProductBomService {

    @Resource
    private RouteProductBomMapper routeProductBomMapper;

    @Override
    public List<RouteProductBomDO> selectList(RouteProductBomListVO listVO) {
        return routeProductBomMapper.selectList(listVO);
    }

    @Override
    public int deleteByRouteId(Long routeId) {
        return routeProductBomMapper.deleteByRouteId(routeId);
    }

    @Override
    public String checkUnique(RouteProductBomBaseVO baseVO) {
        RouteProductBomDO bom = routeProductBomMapper.checkUnique(baseVO);
        Long recordId = baseVO.getId() == null? -1L: baseVO.getId();
        if(StrUtils.isNotNull(bom) && bom.getId().longValue() != recordId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public int deleteByRouteIdAndProductId(Long a,Long b) {
        return routeProductBomMapper.deleteByRouteIdAndProductId(a,b);
    }

    @Override
    public Long createRouteProductBom(RouteProductBomCreateReqVO createReqVO) {
        // 插入
        RouteProductBomDO routeProductBom = RouteProductBomConvert.INSTANCE.convert(createReqVO);
        routeProductBomMapper.insert(routeProductBom);
        // 返回
        return routeProductBom.getId();
    }

    @Override
    public void updateRouteProductBom(RouteProductBomUpdateReqVO updateReqVO) {
        // 校验存在
        validateRouteProductBomExists(updateReqVO.getId());
        // 更新
        RouteProductBomDO updateObj = RouteProductBomConvert.INSTANCE.convert(updateReqVO);
        routeProductBomMapper.updateById(updateObj);
    }

    @Override
    public void deleteRouteProductBom(Long id) {
        // 校验存在
        validateRouteProductBomExists(id);
        // 删除
        routeProductBomMapper.deleteById(id);
    }

    private void validateRouteProductBomExists(Long id) {
        if (routeProductBomMapper.selectById(id) == null) {
            throw exception(ROUTE_PRODUCT_BOM_NOT_EXISTS);
        }
    }

    @Override
    public RouteProductBomDO getRouteProductBom(Long id) {
        return routeProductBomMapper.selectById(id);
    }

    @Override
    public List<RouteProductBomDO> getRouteProductBomList(Collection<Long> ids) {
        return routeProductBomMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RouteProductBomDO> getRouteProductBomPage(RouteProductBomPageReqVO pageReqVO) {
        return routeProductBomMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RouteProductBomDO> getRouteProductBomList(RouteProductBomExportReqVO exportReqVO) {
        return routeProductBomMapper.selectList(exportReqVO);
    }

}
