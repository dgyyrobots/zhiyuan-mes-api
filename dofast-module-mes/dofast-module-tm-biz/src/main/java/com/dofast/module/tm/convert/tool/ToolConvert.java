package com.dofast.module.tm.convert.tool;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.tm.controller.admin.tool.vo.*;
import com.dofast.module.tm.dal.dataobject.tool.ToolDO;

/**
 * 工装夹具清单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ToolConvert {

    ToolConvert INSTANCE = Mappers.getMapper(ToolConvert.class);

    ToolDO convert(ToolCreateReqVO bean);

    ToolDO convert(ToolUpdateReqVO bean);

    ToolRespVO convert(ToolDO bean);

    ToolUpdateReqVO convert01(ToolDO bean);

    List<ToolRespVO> convertList(List<ToolDO> list);

    PageResult<ToolRespVO> convertPage(PageResult<ToolDO> page);

    List<ToolExcelVO> convertList02(List<ToolDO> list);

}
