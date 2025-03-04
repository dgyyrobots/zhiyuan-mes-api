package com.dofast.module.pro.convert.route;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.route.vo.*;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;

/**
 * 工艺路线 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RouteConvert {

    RouteConvert INSTANCE = Mappers.getMapper(RouteConvert.class);

    RouteDO convert(RouteCreateReqVO bean);

    RouteDO convert(RouteUpdateReqVO bean);

    RouteUpdateReqVO convert01 (RouteDO bean);

    RouteRespVO convert(RouteDO bean);

    List<RouteRespVO> convertList(List<RouteDO> list);

    PageResult<RouteRespVO> convertPage(PageResult<RouteDO> page);

    List<RouteExcelVO> convertList02(List<RouteDO> list);

}
