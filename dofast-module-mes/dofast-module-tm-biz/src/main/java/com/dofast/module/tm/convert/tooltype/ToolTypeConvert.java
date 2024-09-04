package com.dofast.module.tm.convert.tooltype;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.tm.controller.admin.tooltype.vo.*;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;

/**
 * 工装夹具类型 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ToolTypeConvert {

    ToolTypeConvert INSTANCE = Mappers.getMapper(ToolTypeConvert.class);

    ToolTypeDO convert(ToolTypeCreateReqVO bean);

    ToolTypeDO convert(ToolTypeUpdateReqVO bean);

    ToolTypeRespVO convert(ToolTypeDO bean);

    List<ToolTypeRespVO> convertList(List<ToolTypeDO> list);

    PageResult<ToolTypeRespVO> convertPage(PageResult<ToolTypeDO> page);

    List<ToolTypeExcelVO> convertList02(List<ToolTypeDO> list);

}
