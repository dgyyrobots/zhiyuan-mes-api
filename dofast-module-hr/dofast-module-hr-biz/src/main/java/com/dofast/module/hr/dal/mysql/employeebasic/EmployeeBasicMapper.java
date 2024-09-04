package com.dofast.module.hr.dal.mysql.employeebasic;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.employeebasic.EmployeeBasicDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.employeebasic.vo.*;

/**
 * 员工基本信息 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface EmployeeBasicMapper extends BaseMapperX<EmployeeBasicDO> {

    default PageResult<EmployeeBasicDO> selectPage(EmployeeBasicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeBasicDO>()
                .eqIfPresent(EmployeeBasicDO::getEmployeePhoto, reqVO.getEmployeePhoto())
                .likeIfPresent(EmployeeBasicDO::getEmployeeRealname, reqVO.getEmployeeRealname())
                .eqIfPresent(EmployeeBasicDO::getEmployeeSex, reqVO.getEmployeeSex())
                .eqIfPresent(EmployeeBasicDO::getEmployeeMarried, reqVO.getEmployeeMarried())
                .eqIfPresent(EmployeeBasicDO::getEmployeePolitics, reqVO.getEmployeePolitics())
                .eqIfPresent(EmployeeBasicDO::getEmployeeNativeplace, reqVO.getEmployeeNativeplace())
                //.eqIfPresent(EmployeeBasicDO::getEmployeeBirthday, reqVO.getEmployeeBirthday())
                .eqIfPresent(EmployeeBasicDO::getEmployeeHeight, reqVO.getEmployeeHeight())
                .eqIfPresent(EmployeeBasicDO::getHouseholdProvinceId, reqVO.getHouseholdProvinceId())
                .likeIfPresent(EmployeeBasicDO::getHouseholdProvinceName, reqVO.getHouseholdProvinceName())
                .eqIfPresent(EmployeeBasicDO::getHouseholdNature, reqVO.getHouseholdNature())
                .eqIfPresent(EmployeeBasicDO::getHouseholdCityId, reqVO.getHouseholdCityId())
                .eqIfPresent(EmployeeBasicDO::getHouseholdAddress, reqVO.getHouseholdAddress())
                .likeIfPresent(EmployeeBasicDO::getHouseholdCityName, reqVO.getHouseholdCityName())
                .eqIfPresent(EmployeeBasicDO::getEmployeeWeight, reqVO.getEmployeeWeight())
                .eqIfPresent(EmployeeBasicDO::getBloodtype, reqVO.getBloodtype())
                .likeIfPresent(EmployeeBasicDO::getEducationName, reqVO.getEducationName())
                .eqIfPresent(EmployeeBasicDO::getEducation, reqVO.getEducation())
                .eqIfPresent(EmployeeBasicDO::getEmails, reqVO.getEmails())
                .eqIfPresent(EmployeeBasicDO::getQq, reqVO.getQq())
                .eqIfPresent(EmployeeBasicDO::getIdNo, reqVO.getIdNo())
                .eqIfPresent(EmployeeBasicDO::getPhone, reqVO.getPhone())
                .likeIfPresent(EmployeeBasicDO::getCardName, reqVO.getCardName())
                .eqIfPresent(EmployeeBasicDO::getCardNo, reqVO.getCardNo())
                .eqIfPresent(EmployeeBasicDO::getCardAddress, reqVO.getCardAddress())
                .eqIfPresent(EmployeeBasicDO::getNowProvinceId, reqVO.getNowProvinceId())
                .likeIfPresent(EmployeeBasicDO::getNowProvinceName, reqVO.getNowProvinceName())
                .eqIfPresent(EmployeeBasicDO::getNowProvinceAddress, reqVO.getNowProvinceAddress())
                .eqIfPresent(EmployeeBasicDO::getNowCityId, reqVO.getNowCityId())
                .likeIfPresent(EmployeeBasicDO::getNowCityName, reqVO.getNowCityName())
                .eqIfPresent(EmployeeBasicDO::getUrgentNameOne, reqVO.getUrgentNameOne())
                .eqIfPresent(EmployeeBasicDO::getUrgentNameOnePhone, reqVO.getUrgentNameOnePhone())
                .eqIfPresent(EmployeeBasicDO::getSpecialty, reqVO.getSpecialty())
                .eqIfPresent(EmployeeBasicDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EmployeeBasicDO::getDepId, reqVO.getDepId())
                .likeIfPresent(EmployeeBasicDO::getDepName, reqVO.getDepName())
                .eqIfPresent(EmployeeBasicDO::getPositionId, reqVO.getPositionId())
                .likeIfPresent(EmployeeBasicDO::getPositionName, reqVO.getPositionName())
                .likeIfPresent(EmployeeBasicDO::getNickName, reqVO.getNickName())
                .betweenIfPresent(EmployeeBasicDO::getSocialPaymentTime, reqVO.getSocialPaymentTime())
                .betweenIfPresent(EmployeeBasicDO::getDateOfBirthTime, reqVO.getDateOfBirthTime())
                .eqIfPresent(EmployeeBasicDO::getWorkAge, reqVO.getWorkAge())
                .eqIfPresent(EmployeeBasicDO::getWorkLevel, reqVO.getWorkLevel())
                .betweenIfPresent(EmployeeBasicDO::getEntryTime, reqVO.getEntryTime())
                .betweenIfPresent(EmployeeBasicDO::getWorkerTime, reqVO.getWorkerTime())
                .likeIfPresent(EmployeeBasicDO::getWorkName, reqVO.getWorkName())
                .eqIfPresent(EmployeeBasicDO::getEthnic, reqVO.getEthnic())
                .eqIfPresent(EmployeeBasicDO::getChara, reqVO.getChara())
                .eqIfPresent(EmployeeBasicDO::getStus, reqVO.getStus())
                .betweenIfPresent(EmployeeBasicDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeBasicDO::getId));
    }

    default List<EmployeeBasicDO> selectList(EmployeeBasicExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeBasicDO>()
                .eqIfPresent(EmployeeBasicDO::getEmployeePhoto, reqVO.getEmployeePhoto())
                .likeIfPresent(EmployeeBasicDO::getEmployeeRealname, reqVO.getEmployeeRealname())
                .eqIfPresent(EmployeeBasicDO::getEmployeeSex, reqVO.getEmployeeSex())
                .eqIfPresent(EmployeeBasicDO::getEmployeeMarried, reqVO.getEmployeeMarried())
                .eqIfPresent(EmployeeBasicDO::getEmployeePolitics, reqVO.getEmployeePolitics())
                .eqIfPresent(EmployeeBasicDO::getEmployeeNativeplace, reqVO.getEmployeeNativeplace())
                .eqIfPresent(EmployeeBasicDO::getEmployeeBirthday, reqVO.getEmployeeBirthday())
                .eqIfPresent(EmployeeBasicDO::getEmployeeHeight, reqVO.getEmployeeHeight())
                .eqIfPresent(EmployeeBasicDO::getHouseholdProvinceId, reqVO.getHouseholdProvinceId())
                .likeIfPresent(EmployeeBasicDO::getHouseholdProvinceName, reqVO.getHouseholdProvinceName())
                .eqIfPresent(EmployeeBasicDO::getHouseholdNature, reqVO.getHouseholdNature())
                .eqIfPresent(EmployeeBasicDO::getHouseholdCityId, reqVO.getHouseholdCityId())
                .eqIfPresent(EmployeeBasicDO::getHouseholdAddress, reqVO.getHouseholdAddress())
                .likeIfPresent(EmployeeBasicDO::getHouseholdCityName, reqVO.getHouseholdCityName())
                .eqIfPresent(EmployeeBasicDO::getEmployeeWeight, reqVO.getEmployeeWeight())
                .eqIfPresent(EmployeeBasicDO::getBloodtype, reqVO.getBloodtype())
                .likeIfPresent(EmployeeBasicDO::getEducationName, reqVO.getEducationName())
                .eqIfPresent(EmployeeBasicDO::getEducation, reqVO.getEducation())
                .eqIfPresent(EmployeeBasicDO::getEmails, reqVO.getEmails())
                .eqIfPresent(EmployeeBasicDO::getQq, reqVO.getQq())
                .eqIfPresent(EmployeeBasicDO::getIdNo, reqVO.getIdNo())
                .eqIfPresent(EmployeeBasicDO::getPhone, reqVO.getPhone())
                .likeIfPresent(EmployeeBasicDO::getCardName, reqVO.getCardName())
                .eqIfPresent(EmployeeBasicDO::getCardNo, reqVO.getCardNo())
                .eqIfPresent(EmployeeBasicDO::getCardAddress, reqVO.getCardAddress())
                .eqIfPresent(EmployeeBasicDO::getNowProvinceId, reqVO.getNowProvinceId())
                .likeIfPresent(EmployeeBasicDO::getNowProvinceName, reqVO.getNowProvinceName())
                .eqIfPresent(EmployeeBasicDO::getNowProvinceAddress, reqVO.getNowProvinceAddress())
                .eqIfPresent(EmployeeBasicDO::getNowCityId, reqVO.getNowCityId())
                .likeIfPresent(EmployeeBasicDO::getNowCityName, reqVO.getNowCityName())
                .eqIfPresent(EmployeeBasicDO::getUrgentNameOne, reqVO.getUrgentNameOne())
                .eqIfPresent(EmployeeBasicDO::getUrgentNameOnePhone, reqVO.getUrgentNameOnePhone())
                .eqIfPresent(EmployeeBasicDO::getSpecialty, reqVO.getSpecialty())
                .eqIfPresent(EmployeeBasicDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EmployeeBasicDO::getDepId, reqVO.getDepId())
                .likeIfPresent(EmployeeBasicDO::getDepName, reqVO.getDepName())
                .eqIfPresent(EmployeeBasicDO::getPositionId, reqVO.getPositionId())
                .likeIfPresent(EmployeeBasicDO::getPositionName, reqVO.getPositionName())
                .likeIfPresent(EmployeeBasicDO::getNickName, reqVO.getNickName())
                .betweenIfPresent(EmployeeBasicDO::getSocialPaymentTime, reqVO.getSocialPaymentTime())
                .betweenIfPresent(EmployeeBasicDO::getDateOfBirthTime, reqVO.getDateOfBirthTime())
                .eqIfPresent(EmployeeBasicDO::getWorkAge, reqVO.getWorkAge())
                .eqIfPresent(EmployeeBasicDO::getWorkLevel, reqVO.getWorkLevel())
                .betweenIfPresent(EmployeeBasicDO::getEntryTime, reqVO.getEntryTime())
                .betweenIfPresent(EmployeeBasicDO::getWorkerTime, reqVO.getWorkerTime())
                .likeIfPresent(EmployeeBasicDO::getWorkName, reqVO.getWorkName())
                .eqIfPresent(EmployeeBasicDO::getEthnic, reqVO.getEthnic())
                .eqIfPresent(EmployeeBasicDO::getChara, reqVO.getChara())
                .eqIfPresent(EmployeeBasicDO::getStus, reqVO.getStus())
                .betweenIfPresent(EmployeeBasicDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EmployeeBasicDO::getId));
    }

    default List<EmployeeBasicDO> selectValidateExistList(EmployeeBasicCreateReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeBasicDO>()
                .eq(EmployeeBasicDO::getIdNo, reqVO.getIdNo())
                .eqIfPresent(EmployeeBasicDO::getPhone, reqVO.getPhone())
                .orderByDesc(EmployeeBasicDO::getId));
    }

    default EmployeeBasicDO selectOneInfo(String creator){
        return selectOne(new LambdaQueryWrapperX<EmployeeBasicDO>()
                .eq(BaseDO::getCreator,creator));
    }

}
