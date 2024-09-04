package com.dofast.module.pro.convert.routeproduct;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.routeproduct.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;

/**
 * 产品制程 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RouteProductConvert {

    RouteProductConvert INSTANCE = Mappers.getMapper(RouteProductConvert.class);

    RouteProductDO convert(RouteProductCreateReqVO bean);

    RouteProductDO convert(RouteProductUpdateReqVO bean);

    RouteProductRespVO convert(RouteProductDO bean);
    RouteProductUpdateReqVO convert01(RouteProductDO bean);

    List<RouteProductRespVO> convertList(List<RouteProductDO> list);

    PageResult<RouteProductRespVO> convertPage(PageResult<RouteProductDO> page);

    List<RouteProductExcelVO> convertList02(List<RouteProductDO> list);

}
