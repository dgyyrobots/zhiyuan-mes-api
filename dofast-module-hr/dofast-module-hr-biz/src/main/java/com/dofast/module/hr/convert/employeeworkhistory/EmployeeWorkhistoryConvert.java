package com.dofast.module.hr.convert.employeeworkhistory;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.employeeworkhistory.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeworkhistory.EmployeeWorkhistoryDO;

/**
 * 员工工作经历 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeWorkhistoryConvert {

    EmployeeWorkhistoryConvert INSTANCE = Mappers.getMapper(EmployeeWorkhistoryConvert.class);

    EmployeeWorkhistoryDO convert(EmployeeWorkhistoryCreateReqVO bean);

    EmployeeWorkhistoryDO convert(EmployeeWorkhistoryUpdateReqVO bean);

    EmployeeWorkhistoryRespVO convert(EmployeeWorkhistoryDO bean);

    List<EmployeeWorkhistoryRespVO> convertList(List<EmployeeWorkhistoryDO> list);

    PageResult<EmployeeWorkhistoryRespVO> convertPage(PageResult<EmployeeWorkhistoryDO> page);

    List<EmployeeWorkhistoryExcelVO> convertList02(List<EmployeeWorkhistoryDO> list);

}
