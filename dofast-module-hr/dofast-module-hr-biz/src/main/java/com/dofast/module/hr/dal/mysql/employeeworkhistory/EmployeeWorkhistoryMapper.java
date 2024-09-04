package com.dofast.module.hr.dal.mysql.employeeworkhistory;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.employeeworkhistory.EmployeeWorkhistoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.employeeworkhistory.vo.*;

/**
 * 员工工作经历 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeWorkhistoryMapper extends BaseMapperX<EmployeeWorkhistoryDO> {

    default PageResult<EmployeeWorkhistoryDO> selectPage(EmployeeWorkhistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeWorkhistoryDO>()
                .likeIfPresent(EmployeeWorkhistoryDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(EmployeeWorkhistoryDO::getTreatment, reqVO.getTreatment())
                .eqIfPresent(EmployeeWorkhistoryDO::getTreatmentPost, reqVO.getTreatmentPost())
                .eqIfPresent(EmployeeWorkhistoryDO::getReasonForLeave, reqVO.getReasonForLeave())
                .eqIfPresent(EmployeeWorkhistoryDO::getEmployeeId, reqVO.getEmployeeId())
                .betweenIfPresent(EmployeeWorkhistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeWorkhistoryDO::getId));
    }

    default List<EmployeeWorkhistoryDO> selectList(EmployeeWorkhistoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeWorkhistoryDO>()
                .likeIfPresent(EmployeeWorkhistoryDO::getCompanyName, reqVO.getCompanyName())
                .eqIfPresent(EmployeeWorkhistoryDO::getTreatment, reqVO.getTreatment())
                .eqIfPresent(EmployeeWorkhistoryDO::getTreatmentPost, reqVO.getTreatmentPost())
                .eqIfPresent(EmployeeWorkhistoryDO::getReasonForLeave, reqVO.getReasonForLeave())
                .eqIfPresent(EmployeeWorkhistoryDO::getEmployeeId, reqVO.getEmployeeId())
                .betweenIfPresent(EmployeeWorkhistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeWorkhistoryDO::getId));
    }

    default List<EmployeeWorkhistoryDO>  selectOneInfo(String creator){
        return selectList(new LambdaQueryWrapperX<EmployeeWorkhistoryDO>()
                .eq(EmployeeWorkhistoryDO::getCreator,creator));
    }

}
