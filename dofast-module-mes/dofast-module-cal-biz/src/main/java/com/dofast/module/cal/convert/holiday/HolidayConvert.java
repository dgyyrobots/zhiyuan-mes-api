package com.dofast.module.cal.convert.holiday;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cal.controller.admin.holiday.vo.*;
import com.dofast.module.cal.dal.dataobject.holiday.HolidayDO;

/**
 * 节假日设置 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface HolidayConvert {

    HolidayConvert INSTANCE = Mappers.getMapper(HolidayConvert.class);

    HolidayDO convert(HolidayCreateReqVO bean);

    HolidayDO convert(HolidayUpdateReqVO bean);

    HolidayRespVO convert(HolidayDO bean);

    List<HolidayRespVO> convertList(List<HolidayDO> list);

    PageResult<HolidayRespVO> convertPage(PageResult<HolidayDO> page);

    List<HolidayExcelVO> convertList02(List<HolidayDO> list);

}
