package com.dofast.module.pro.service.routeproduct;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.routeproduct.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.routeproduct.RouteProductConvert;
import com.dofast.module.pro.dal.mysql.routeproduct.RouteProductMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 产品制程 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RouteProductServiceImpl implements RouteProductService {

    @Resource
    private RouteProductMapper routeProductMapper;

    @Override
    public List<RouteProductDO> selectList(RouteProductListVO listVO) {
        return routeProductMapper.selectList(listVO);
    }

    @Override
    public int deleteByRouteId(Long routeId) {
        return routeProductMapper.deleteByRouteId(routeId);
    }

    @Override
    public String checkItemUnique(RouteProductBaseVO baseVO) {
        RouteProductDO product = routeProductMapper.checkItemUnique(baseVO);
        Long productId = baseVO.getId()==null?-1L:baseVO.getId();
        if(StrUtils.isNotNull(product) && product.getId().longValue() != productId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createRouteProduct(RouteProductCreateReqVO createReqVO) {
        // 插入
        RouteProductDO routeProduct = RouteProductConvert.INSTANCE.convert(createReqVO);
        routeProductMapper.insert(routeProduct);
        // 返回
        return routeProduct.getId();
    }

    @Override
    public int updateRouteProduct(RouteProductUpdateReqVO updateReqVO) {
        // 校验存在
        validateRouteProductExists(updateReqVO.getId());
        // 更新
        RouteProductDO updateObj = RouteProductConvert.INSTANCE.convert(updateReqVO);
       return routeProductMapper.updateById(updateObj);
    }

    @Override
    public void deleteRouteProduct(Long id) {
        // 校验存在
        validateRouteProductExists(id);
        // 删除
        routeProductMapper.deleteById(id);
    }

    private void validateRouteProductExists(Long id) {
        if (routeProductMapper.selectById(id) == null) {
            throw exception(ROUTE_PRODUCT_NOT_EXISTS);
        }
    }

    @Override
    public RouteProductDO getRouteProduct(Long id) {
        return routeProductMapper.selectById(id);
    }

    @Override
    public List<RouteProductDO> getRouteProductList(Collection<Long> ids) {
        return routeProductMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RouteProductDO> getRouteProductPage(RouteProductPageReqVO pageReqVO) {
        return routeProductMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RouteProductDO> getRouteProductList(RouteProductExportReqVO exportReqVO) {
        return routeProductMapper.selectList(exportReqVO);
    }

}
