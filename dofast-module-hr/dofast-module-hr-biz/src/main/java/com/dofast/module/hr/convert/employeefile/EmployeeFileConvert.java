package com.dofast.module.hr.convert.employeefile;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.employeefile.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefile.EmployeeFileDO;

/**
 * 员工信息文件 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeFileConvert {

    EmployeeFileConvert INSTANCE = Mappers.getMapper(EmployeeFileConvert.class);

    EmployeeFileDO convert(EmployeeFileCreateReqVO bean);

    EmployeeFileDO convert(EmployeeFileUpdateReqVO bean);

    EmployeeFileRespVO convert(EmployeeFileDO bean);

    List<EmployeeFileRespVO> convertList(List<EmployeeFileDO> list);

    PageResult<EmployeeFileRespVO> convertPage(PageResult<EmployeeFileDO> page);

    List<EmployeeFileExcelVO> convertList02(List<EmployeeFileDO> list);

}
