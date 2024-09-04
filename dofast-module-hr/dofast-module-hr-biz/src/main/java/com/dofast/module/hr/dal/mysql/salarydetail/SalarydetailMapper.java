package com.dofast.module.hr.dal.mysql.salarydetail;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.salarydetail.SalarydetailDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.salarydetail.vo.*;

/**
 * 工资明细 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface SalarydetailMapper extends BaseMapperX<SalarydetailDO> {

    default PageResult<SalarydetailDO> selectPage(SalarydetailPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SalarydetailDO>()
                .eqIfPresent(SalarydetailDO::getSalary, reqVO.getSalary())
                .eqIfPresent(SalarydetailDO::getItem, reqVO.getItem())
                .eqIfPresent(SalarydetailDO::getType, reqVO.getType())
                .eqIfPresent(SalarydetailDO::getAmount, reqVO.getAmount())
                .eqIfPresent(SalarydetailDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(SalarydetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalarydetailDO::getId));
    }

    default List<SalarydetailDO> selectList(SalarydetailExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SalarydetailDO>()
                .eqIfPresent(SalarydetailDO::getSalary, reqVO.getSalary())
                .eqIfPresent(SalarydetailDO::getItem, reqVO.getItem())
                .eqIfPresent(SalarydetailDO::getType, reqVO.getType())
                .eqIfPresent(SalarydetailDO::getAmount, reqVO.getAmount())
                .eqIfPresent(SalarydetailDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(SalarydetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SalarydetailDO::getId));
    }

}
