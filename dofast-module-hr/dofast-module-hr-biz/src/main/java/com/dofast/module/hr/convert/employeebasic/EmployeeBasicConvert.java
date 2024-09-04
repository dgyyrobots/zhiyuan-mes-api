package com.dofast.module.hr.convert.employeebasic;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.employeebasic.vo.*;
import com.dofast.module.hr.dal.dataobject.employeebasic.EmployeeBasicDO;

/**
 * 员工基本信息 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeBasicConvert {

    EmployeeBasicConvert INSTANCE = Mappers.getMapper(EmployeeBasicConvert.class);

    EmployeeBasicDO convert(EmployeeBasicCreateReqVO bean);

    EmployeeBasicDO convert(EmployeeBasicUpdateReqVO bean);

    EmployeeBasicRespVO convert(EmployeeBasicDO bean);

    List<EmployeeBasicRespVO> convertList(List<EmployeeBasicDO> list);

    PageResult<EmployeeBasicRespVO> convertPage(PageResult<EmployeeBasicDO> page);

    List<EmployeeBasicExcelVO> convertList02(List<EmployeeBasicDO> list);

}
