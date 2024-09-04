package com.dofast.module.hr.dal.mysql.salarycommission;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.salarycommission.SalarycommissionDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.salarycommission.vo.*;

/**
 * 绩效工资 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface SalarycommissionMapper extends BaseMapperX<SalarycommissionDO> {

    default PageResult<SalarycommissionDO> selectPage(SalarycommissionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SalarycommissionDO>()
                .eqIfPresent(SalarycommissionDO::getSalary, reqVO.getSalary())
                .eqIfPresent(SalarycommissionDO::getType, reqVO.getType())
                .eqIfPresent(SalarycommissionDO::getLine, reqVO.getLine())
                .eqIfPresent(SalarycommissionDO::getAmount, reqVO.getAmount())
                .eqIfPresent(SalarycommissionDO::getRate, reqVO.getRate())
                .eqIfPresent(SalarycommissionDO::getCommission, reqVO.getCommission())
                .eqIfPresent(SalarycommissionDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(SalarycommissionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalarycommissionDO::getId));
    }

    default List<SalarycommissionDO> selectList(SalarycommissionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SalarycommissionDO>()
                .eqIfPresent(SalarycommissionDO::getSalary, reqVO.getSalary())
                .eqIfPresent(SalarycommissionDO::getType, reqVO.getType())
                .eqIfPresent(SalarycommissionDO::getLine, reqVO.getLine())
                .eqIfPresent(SalarycommissionDO::getAmount, reqVO.getAmount())
                .eqIfPresent(SalarycommissionDO::getRate, reqVO.getRate())
                .eqIfPresent(SalarycommissionDO::getCommission, reqVO.getCommission())
                .eqIfPresent(SalarycommissionDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(SalarycommissionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalarycommissionDO::getId));
    }

}
