package com.dofast.module.hr.convert.salary;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.salary.vo.*;
import com.dofast.module.hr.dal.dataobject.salary.SalaryDO;

/**
 * 工资总 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface SalaryConvert {

    SalaryConvert INSTANCE = Mappers.getMapper(SalaryConvert.class);

    SalaryDO convert(SalaryCreateReqVO bean);

    SalaryDO convert(SalaryUpdateReqVO bean);

    SalaryRespVO convert(SalaryDO bean);

    List<SalaryRespVO> convertList(List<SalaryDO> list);

    PageResult<SalaryRespVO> convertPage(PageResult<SalaryDO> page);

    List<SalaryExcelVO> convertList02(List<SalaryDO> list);

}
