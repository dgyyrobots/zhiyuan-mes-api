package com.dofast.module.hr.convert.salarycommission;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.salarycommission.vo.*;
import com.dofast.module.hr.dal.dataobject.salarycommission.SalarycommissionDO;

/**
 * 绩效工资 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface SalarycommissionConvert {

    SalarycommissionConvert INSTANCE = Mappers.getMapper(SalarycommissionConvert.class);

    SalarycommissionDO convert(SalarycommissionCreateReqVO bean);

    SalarycommissionDO convert(SalarycommissionUpdateReqVO bean);

    SalarycommissionRespVO convert(SalarycommissionDO bean);

    List<SalarycommissionRespVO> convertList(List<SalarycommissionDO> list);

    PageResult<SalarycommissionRespVO> convertPage(PageResult<SalarycommissionDO> page);

    List<SalarycommissionExcelVO> convertList02(List<SalarycommissionDO> list);

}
