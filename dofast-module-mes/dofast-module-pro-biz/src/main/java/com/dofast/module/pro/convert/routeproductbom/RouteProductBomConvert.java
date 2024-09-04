package com.dofast.module.pro.convert.routeproductbom;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.routeproductbom.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproductbom.RouteProductBomDO;

/**
 * 产品制程物料BOM Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RouteProductBomConvert {

    RouteProductBomConvert INSTANCE = Mappers.getMapper(RouteProductBomConvert.class);

    RouteProductBomDO convert(RouteProductBomCreateReqVO bean);

    RouteProductBomDO convert(RouteProductBomUpdateReqVO bean);

    RouteProductBomRespVO convert(RouteProductBomDO bean);

    List<RouteProductBomRespVO> convertList(List<RouteProductBomDO> list);

    PageResult<RouteProductBomRespVO> convertPage(PageResult<RouteProductBomDO> page);

    List<RouteProductBomExcelVO> convertList02(List<RouteProductBomDO> list);

}
