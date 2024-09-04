package com.dofast.module.cal.convert.shift;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cal.controller.admin.shift.vo.*;
import com.dofast.module.cal.dal.dataobject.shift.ShiftDO;

/**
 * 计划班次 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ShiftConvert {

    ShiftConvert INSTANCE = Mappers.getMapper(ShiftConvert.class);

    ShiftDO convert(ShiftCreateReqVO bean);

    ShiftDO convert(ShiftUpdateReqVO bean);

    ShiftRespVO convert(ShiftDO bean);

    List<ShiftRespVO> convertList(List<ShiftDO> list);

    PageResult<ShiftRespVO> convertPage(PageResult<ShiftDO> page);

    List<ShiftExcelVO> convertList02(List<ShiftDO> list);

}
