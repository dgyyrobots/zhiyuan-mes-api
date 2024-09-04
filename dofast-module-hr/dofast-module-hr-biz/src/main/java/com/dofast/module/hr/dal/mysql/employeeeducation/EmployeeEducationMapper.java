package com.dofast.module.hr.dal.mysql.employeeeducation;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.employeeeducation.EmployeeEducationDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.employeeeducation.vo.*;

/**
 * 员工教育培训经历 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeEducationMapper extends BaseMapperX<EmployeeEducationDO> {

    default PageResult<EmployeeEducationDO> selectPage(EmployeeEducationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeEducationDO>()
                .eqIfPresent(EmployeeEducationDO::getEducationSchool, reqVO.getEducationSchool())
                .eqIfPresent(EmployeeEducationDO::getEducationSpecialty, reqVO.getEducationSpecialty())
                .eqIfPresent(EmployeeEducationDO::getEducationHonor, reqVO.getEducationHonor())
                .betweenIfPresent(EmployeeEducationDO::getEducationStarttime, reqVO.getEducationStarttime())
                .betweenIfPresent(EmployeeEducationDO::getEducationEndtime, reqVO.getEducationEndtime())
                .eqIfPresent(EmployeeEducationDO::getEmployeeId, reqVO.getEmployeeId())
                .betweenIfPresent(EmployeeEducationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeEducationDO::getId));
    }

    default List<EmployeeEducationDO> selectList(EmployeeEducationExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeEducationDO>()
                .eqIfPresent(EmployeeEducationDO::getEducationSchool, reqVO.getEducationSchool())
                .eqIfPresent(EmployeeEducationDO::getEducationSpecialty, reqVO.getEducationSpecialty())
                .eqIfPresent(EmployeeEducationDO::getEducationHonor, reqVO.getEducationHonor())
                .betweenIfPresent(EmployeeEducationDO::getEducationStarttime, reqVO.getEducationStarttime())
                .betweenIfPresent(EmployeeEducationDO::getEducationEndtime, reqVO.getEducationEndtime())
                .eqIfPresent(EmployeeEducationDO::getEmployeeId, reqVO.getEmployeeId())
                .betweenIfPresent(EmployeeEducationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeEducationDO::getId));
    }


    default List<EmployeeEducationDO> selectOneInfo(String creator){
        return selectList(new LambdaQueryWrapperX<EmployeeEducationDO>()
                .eq(BaseDO::getCreator,creator));
    }

}
