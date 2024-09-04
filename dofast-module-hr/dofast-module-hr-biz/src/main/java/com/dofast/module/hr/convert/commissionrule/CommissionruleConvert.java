package com.dofast.module.hr.convert.commissionrule;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.hr.controller.admin.commissionrule.vo.*;
import com.dofast.module.hr.dal.dataobject.commissionrule.CommissionruleDO;

/**
 * 提成规则 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CommissionruleConvert {

    CommissionruleConvert INSTANCE = Mappers.getMapper(CommissionruleConvert.class);

    CommissionruleDO convert(CommissionruleCreateReqVO bean);

    CommissionruleDO convert(CommissionruleUpdateReqVO bean);

    CommissionruleRespVO convert(CommissionruleDO bean);

    List<CommissionruleRespVO> convertList(List<CommissionruleDO> list);

    PageResult<CommissionruleRespVO> convertPage(PageResult<CommissionruleDO> page);

    List<CommissionruleExcelVO> convertList02(List<CommissionruleDO> list);

}
