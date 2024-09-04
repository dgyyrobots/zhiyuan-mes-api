package com.dofast.module.cal.dal.mysql.holiday;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.cal.dal.dataobject.holiday.HolidayDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.cal.controller.admin.holiday.vo.*;

/**
 * 节假日设置 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface HolidayMapper extends BaseMapperX<HolidayDO> {

    default PageResult<HolidayDO> selectPage(HolidayPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HolidayDO>()
                .eqIfPresent(HolidayDO::getTheDay, reqVO.getTheDay())
                .eqIfPresent(HolidayDO::getHolidayType, reqVO.getHolidayType())
                .betweenIfPresent(HolidayDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(HolidayDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(HolidayDO::getRemark, reqVO.getRemark())
                .eqIfPresent(HolidayDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(HolidayDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(HolidayDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(HolidayDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(HolidayDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HolidayDO::getId));
    }

    default List<HolidayDO> selectList(HolidayExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<HolidayDO>()
                .eqIfPresent(HolidayDO::getTheDay, reqVO.getTheDay())
                .eqIfPresent(HolidayDO::getHolidayType, reqVO.getHolidayType())
                .betweenIfPresent(HolidayDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(HolidayDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(HolidayDO::getRemark, reqVO.getRemark())
                .eqIfPresent(HolidayDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(HolidayDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(HolidayDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(HolidayDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(HolidayDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HolidayDO::getId));
    }

}
