package com.dofast.module.hr.dal.mysql.employeefile;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;
import com.dofast.module.hr.dal.dataobject.employeefile.EmployeeFileDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.employeefile.vo.*;

/**
 * 员工信息文件 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeFileMapper extends BaseMapperX<EmployeeFileDO> {

    default PageResult<EmployeeFileDO> selectPage(EmployeeFilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeFileDO>()
                .betweenIfPresent(EmployeeFileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeFileDO::getId));
    }

    default List<EmployeeFileDO> selectList(EmployeeFileExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeFileDO>()
                .betweenIfPresent(EmployeeFileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeFileDO::getId));
    }

    default List<EmployeeFileDO> selectValidateExistList(EmployeeFileCreateReqVO reqVO){
        return selectList(new LambdaQueryWrapperX<EmployeeFileDO>()
                .eq(EmployeeFileDO::getIdCardFront, reqVO.getIdCardFront())
                .eq(EmployeeFileDO::getIdCardBack, reqVO.getIdCardBack())
                .eqIfPresent(EmployeeFileDO::getBankCard, reqVO.getBankCard())
                .orderByDesc(EmployeeFileDO::getId));
    }

    default EmployeeFileDO selectOneInfo(String creator){
        return selectOne(new LambdaQueryWrapperX<EmployeeFileDO>()
                .eq(EmployeeFileDO::getCreator,creator));
    }
}
