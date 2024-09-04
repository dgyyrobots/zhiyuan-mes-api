package com.dofast.module.pro.convert.routeprocess;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.routeprocess.vo.*;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;

/**
 * 工艺组成 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RouteProcessConvert {

    RouteProcessConvert INSTANCE = Mappers.getMapper(RouteProcessConvert.class);

    RouteProcessDO convert(RouteProcessCreateReqVO bean);

    RouteProcessDO convert(RouteProcessUpdateReqVO bean);

    RouteProcessRespVO convert(RouteProcessDO bean);
    RouteProcessUpdateReqVO convert01(RouteProcessDO bean);

    List<RouteProcessRespVO> convertList(List<RouteProcessDO> list);

    PageResult<RouteProcessRespVO> convertPage(PageResult<RouteProcessDO> page);

    List<RouteProcessExcelVO> convertList02(List<RouteProcessDO> list);

}
