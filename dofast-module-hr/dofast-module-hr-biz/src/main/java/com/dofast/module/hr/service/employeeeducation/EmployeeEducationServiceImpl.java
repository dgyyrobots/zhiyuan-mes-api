package com.dofast.module.hr.service.employeeeducation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.employeeeducation.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeeducation.EmployeeEducationDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.employeeeducation.EmployeeEducationConvert;
import com.dofast.module.hr.dal.mysql.employeeeducation.EmployeeEducationMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 员工教育培训经历 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class EmployeeEducationServiceImpl implements EmployeeEducationService {

    @Resource
    private EmployeeEducationMapper employeeEducationMapper;

    @Override
    public Long createEmployeeEducation(EmployeeEducationCreateReqVO createReqVO) {
        // 插入
        EmployeeEducationDO employeeEducation = EmployeeEducationConvert.INSTANCE.convert(createReqVO);
        employeeEducationMapper.insert(employeeEducation);
        // 返回
        return employeeEducation.getId();
    }


    @Override
    public Long createEmployeeEducationMy(EmployeeEducationCreateReqVO createReqVO) {
        // 插入
        EmployeeEducationDO employeeEducation = EmployeeEducationConvert.INSTANCE.convert(createReqVO);
        employeeEducation.setCreator(String.valueOf(getLoginUserId()));
        employeeEducationMapper.insert(employeeEducation);
        // 返回
        return employeeEducation.getId();
    }

    @Override
    public void updateEmployeeEducation(EmployeeEducationUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeEducationExists(updateReqVO.getId());
        EmployeeEducationDO employeeEducationDO = employeeEducationMapper.selectById(updateReqVO.getId());
        if(updateReqVO.getUserId() != employeeEducationDO.getEmployeeId()) throw exception(EMPLOYEE_EDUCATION_NO_PERMISSION);
        // 更新
        EmployeeEducationDO updateObj = EmployeeEducationConvert.INSTANCE.convert(updateReqVO);
        employeeEducationMapper.updateById(updateObj);
    }

    @Override
    public void updateEmployeeEducationMy(EmployeeEducationUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeEducationExistsMy(updateReqVO.getId());
        EmployeeEducationDO employeeEducationDO = employeeEducationMapper.selectById(updateReqVO.getId());
        if(updateReqVO.getUserId() != employeeEducationDO.getEmployeeId()) throw exception(EMPLOYEE_EDUCATION_NO_PERMISSION);
        // 更新
        EmployeeEducationDO updateObj = EmployeeEducationConvert.INSTANCE.convert(updateReqVO);
        employeeEducationMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmployeeEducation(Long id) {
        // 校验存在
        validateEmployeeEducationExists(id);
        // 删除
        employeeEducationMapper.deleteById(id);
    }

    @Override
    public void deleteEmployeeEducationMy(Long id) {
        // 校验存在
        validateEmployeeEducationExistsMy(id);
        // 删除
        employeeEducationMapper.deleteById(id);
    }

    private void validateEmployeeEducationExists(Long id) {
        if (employeeEducationMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_EDUCATION_NOT_EXISTS);
        }
    }

    private void validateEmployeeEducationExistsMy(Long id) {
        if (employeeEducationMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_EDUCATION_NOT_EXISTS);
        }
        if (!employeeEducationMapper.selectById(id).getCreator().equals(getLoginUserId())){
            throw exception(EMPLOYEE_EDUCATION_NOT_EXISTS);
        }
    }

    @Override
    public EmployeeEducationDO getEmployeeEducation(Long id) {
        return employeeEducationMapper.selectById(id);
    }

    @Override
    public List<EmployeeEducationDO> getEmployeeEducationMy(Long id) {
        return employeeEducationMapper.selectOneInfo(String.valueOf(id));
    }

    @Override
    public List<EmployeeEducationDO> getEmployeeEducationList(Collection<Long> ids) {
        return employeeEducationMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EmployeeEducationDO> getEmployeeEducationPage(EmployeeEducationPageReqVO pageReqVO) {
        return employeeEducationMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmployeeEducationDO> getEmployeeEducationList(EmployeeEducationExportReqVO exportReqVO) {
        return employeeEducationMapper.selectList(exportReqVO);
    }

}
