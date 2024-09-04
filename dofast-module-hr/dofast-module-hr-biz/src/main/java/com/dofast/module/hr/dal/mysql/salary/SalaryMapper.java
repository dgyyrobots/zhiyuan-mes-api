package com.dofast.module.hr.dal.mysql.salary;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.salary.SalaryDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.salary.vo.*;

/**
 * 工资总 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface SalaryMapper extends BaseMapperX<SalaryDO> {

    default PageResult<SalaryDO> selectPage(SalaryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SalaryDO>()
                .eqIfPresent(SalaryDO::getOrderFlowNo, reqVO.getOrderFlowNo())
                .eqIfPresent(SalaryDO::getDetailNo, reqVO.getDetailNo())
                .eqIfPresent(SalaryDO::getSn, reqVO.getSn())
                .eqIfPresent(SalaryDO::getMonth, reqVO.getMonth())
                .eqIfPresent(SalaryDO::getAccount, reqVO.getAccount())
                .eqIfPresent(SalaryDO::getCompany, reqVO.getCompany())
                .eqIfPresent(SalaryDO::getDept, reqVO.getDept())
                .eqIfPresent(SalaryDO::getBasic, reqVO.getBasic())
                .eqIfPresent(SalaryDO::getPerformance, reqVO.getPerformance())
                .eqIfPresent(SalaryDO::getBonus, reqVO.getBonus())
                .eqIfPresent(SalaryDO::getAllowance, reqVO.getAllowance())
                .eqIfPresent(SalaryDO::getExemption, reqVO.getExemption())
                .eqIfPresent(SalaryDO::getDeduction, reqVO.getDeduction())
                .eqIfPresent(SalaryDO::getDeserved, reqVO.getDeserved())
                .eqIfPresent(SalaryDO::getActual, reqVO.getActual())
                .eqIfPresent(SalaryDO::getCompanySsf, reqVO.getCompanySsf())
                .eqIfPresent(SalaryDO::getCompanyHpf, reqVO.getCompanyHpf())
                .eqIfPresent(SalaryDO::getCurTaxableIncome, reqVO.getCurTaxableIncome())
                .eqIfPresent(SalaryDO::getTaxableIncome, reqVO.getTaxableIncome())
                .eqIfPresent(SalaryDO::getTaxPayable, reqVO.getTaxPayable())
                .eqIfPresent(SalaryDO::getTaxPaid, reqVO.getTaxPaid())
                .eqIfPresent(SalaryDO::getTax, reqVO.getTax())
                .eqIfPresent(SalaryDO::getConfirmer, reqVO.getConfirmer())
                .betweenIfPresent(SalaryDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(SalaryDO::getGranter, reqVO.getGranter())
                .betweenIfPresent(SalaryDO::getGrantTime, reqVO.getGrantTime())
                .eqIfPresent(SalaryDO::getDesc, reqVO.getDesc())
                .eqIfPresent(SalaryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SalaryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalaryDO::getId));
    }

    default List<SalaryDO> selectList(SalaryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SalaryDO>()
                .eqIfPresent(SalaryDO::getOrderFlowNo, reqVO.getOrderFlowNo())
                .eqIfPresent(SalaryDO::getDetailNo, reqVO.getDetailNo())
                .eqIfPresent(SalaryDO::getSn, reqVO.getSn())
                .eqIfPresent(SalaryDO::getMonth, reqVO.getMonth())
                .eqIfPresent(SalaryDO::getAccount, reqVO.getAccount())
                .eqIfPresent(SalaryDO::getCompany, reqVO.getCompany())
                .eqIfPresent(SalaryDO::getDept, reqVO.getDept())
                .eqIfPresent(SalaryDO::getBasic, reqVO.getBasic())
                .eqIfPresent(SalaryDO::getPerformance, reqVO.getPerformance())
                .eqIfPresent(SalaryDO::getBonus, reqVO.getBonus())
                .eqIfPresent(SalaryDO::getAllowance, reqVO.getAllowance())
                .eqIfPresent(SalaryDO::getExemption, reqVO.getExemption())
                .eqIfPresent(SalaryDO::getDeduction, reqVO.getDeduction())
                .eqIfPresent(SalaryDO::getDeserved, reqVO.getDeserved())
                .eqIfPresent(SalaryDO::getActual, reqVO.getActual())
                .eqIfPresent(SalaryDO::getCompanySsf, reqVO.getCompanySsf())
                .eqIfPresent(SalaryDO::getCompanyHpf, reqVO.getCompanyHpf())
                .eqIfPresent(SalaryDO::getCurTaxableIncome, reqVO.getCurTaxableIncome())
                .eqIfPresent(SalaryDO::getTaxableIncome, reqVO.getTaxableIncome())
                .eqIfPresent(SalaryDO::getTaxPayable, reqVO.getTaxPayable())
                .eqIfPresent(SalaryDO::getTaxPaid, reqVO.getTaxPaid())
                .eqIfPresent(SalaryDO::getTax, reqVO.getTax())
                .eqIfPresent(SalaryDO::getConfirmer, reqVO.getConfirmer())
                .betweenIfPresent(SalaryDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(SalaryDO::getGranter, reqVO.getGranter())
                .betweenIfPresent(SalaryDO::getGrantTime, reqVO.getGrantTime())
                .eqIfPresent(SalaryDO::getDesc, reqVO.getDesc())
                .eqIfPresent(SalaryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SalaryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalaryDO::getId));
    }

}
