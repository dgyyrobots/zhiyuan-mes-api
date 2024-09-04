package com.dofast.module.hr.dal.mysql.employeefamiles;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.employeefamiles.vo.*;

/**
 * 员工家庭成员 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeFamilesMapper extends BaseMapperX<EmployeeFamilesDO> {

    default PageResult<EmployeeFamilesDO> selectPage(EmployeeFamilesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeFamilesDO>()
                .likeIfPresent(EmployeeFamilesDO::getFamilesName, reqVO.getFamilesName())
                .eqIfPresent(EmployeeFamilesDO::getFamilesRealtion, reqVO.getFamilesRealtion())
                .eqIfPresent(EmployeeFamilesDO::getFamilesWorkunit, reqVO.getFamilesWorkunit())
                .eqIfPresent(EmployeeFamilesDO::getFamilesWorkplace, reqVO.getFamilesWorkplace())
                .eqIfPresent(EmployeeFamilesDO::getFamilesPhone, reqVO.getFamilesPhone())
                .eqIfPresent(EmployeeFamilesDO::getEmployeeId, reqVO.getEmployeeId())
                .betweenIfPresent(EmployeeFamilesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeFamilesDO::getId));
    }

    default List<EmployeeFamilesDO> selectList(EmployeeFamilesExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeFamilesDO>()
                .likeIfPresent(EmployeeFamilesDO::getFamilesName, reqVO.getFamilesName())
                .eqIfPresent(EmployeeFamilesDO::getFamilesRealtion, reqVO.getFamilesRealtion())
                .eqIfPresent(EmployeeFamilesDO::getFamilesWorkunit, reqVO.getFamilesWorkunit())
                .eqIfPresent(EmployeeFamilesDO::getFamilesWorkplace, reqVO.getFamilesWorkplace())
                .eqIfPresent(EmployeeFamilesDO::getFamilesPhone, reqVO.getFamilesPhone())
                .eqIfPresent(EmployeeFamilesDO::getEmployeeId, reqVO.getEmployeeId())
                .betweenIfPresent(EmployeeFamilesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeFamilesDO::getId));
    }

    default List<EmployeeFamilesDO> selectOneInfo(String creator){
        return selectList(new LambdaQueryWrapperX<EmployeeFamilesDO>()
                .eq(BaseDO::getCreator,creator));
    }
}
