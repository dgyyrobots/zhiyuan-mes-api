package com.dofast.module.hr.convert.employeeeducation;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.employeeeducation.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeeducation.EmployeeEducationDO;

/**
 * 员工教育培训经历 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeEducationConvert {

    EmployeeEducationConvert INSTANCE = Mappers.getMapper(EmployeeEducationConvert.class);

    EmployeeEducationDO convert(EmployeeEducationCreateReqVO bean);

    EmployeeEducationDO convert(EmployeeEducationUpdateReqVO bean);

    EmployeeEducationRespVO convert(EmployeeEducationDO bean);

    List<EmployeeEducationRespVO> convertList(List<EmployeeEducationDO> list);

    PageResult<EmployeeEducationRespVO> convertPage(PageResult<EmployeeEducationDO> page);

    List<EmployeeEducationExcelVO> convertList02(List<EmployeeEducationDO> list);

}
