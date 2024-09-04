package com.dofast.module.hr.convert.employeefamiles;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.employeefamiles.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;

/**
 * 员工家庭成员 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeFamilesConvert {

    EmployeeFamilesConvert INSTANCE = Mappers.getMapper(EmployeeFamilesConvert.class);

    EmployeeFamilesDO convert(EmployeeFamilesCreateReqVO bean);

    EmployeeFamilesDO convert(EmployeeFamilesUpdateReqVO bean);

    EmployeeFamilesRespVO convert(EmployeeFamilesDO bean);

    List<EmployeeFamilesRespVO> convertList(List<EmployeeFamilesDO> list);

    PageResult<EmployeeFamilesRespVO> convertPage(PageResult<EmployeeFamilesDO> page);

    List<EmployeeFamilesExcelVO> convertList02(List<EmployeeFamilesDO> list);

}
