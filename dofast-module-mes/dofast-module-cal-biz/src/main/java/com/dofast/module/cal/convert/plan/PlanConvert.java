package com.dofast.module.cal.convert.plan;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cal.controller.admin.plan.vo.*;
import com.dofast.module.cal.dal.dataobject.plan.PlanDO;

/**
 * 排班计划 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface PlanConvert {

    PlanConvert INSTANCE = Mappers.getMapper(PlanConvert.class);

    PlanDO convert(PlanCreateReqVO bean);

    PlanDO convert(PlanUpdateReqVO bean);

    PlanRespVO convert(PlanDO bean);

    List<PlanRespVO> convertList(List<PlanDO> list);

    PageResult<PlanRespVO> convertPage(PageResult<PlanDO> page);

    List<PlanExcelVO> convertList02(List<PlanDO> list);

}
