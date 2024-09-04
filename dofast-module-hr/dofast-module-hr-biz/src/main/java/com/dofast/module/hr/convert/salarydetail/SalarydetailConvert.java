package com.dofast.module.hr.convert.salarydetail;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.salarydetail.vo.*;
import com.dofast.module.hr.dal.dataobject.salarydetail.SalarydetailDO;

/**
 * 工资明细 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface SalarydetailConvert {

    SalarydetailConvert INSTANCE = Mappers.getMapper(SalarydetailConvert.class);

    SalarydetailDO convert(SalarydetailCreateReqVO bean);

    SalarydetailDO convert(SalarydetailUpdateReqVO bean);

    SalarydetailRespVO convert(SalarydetailDO bean);

    List<SalarydetailRespVO> convertList(List<SalarydetailDO> list);

    PageResult<SalarydetailRespVO> convertPage(PageResult<SalarydetailDO> page);

    List<SalarydetailExcelVO> convertList02(List<SalarydetailDO> list);

}
