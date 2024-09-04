package com.dofast.module.hr.service.employeefamiles;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.employeefamiles.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.employeefamiles.EmployeeFamilesConvert;
import com.dofast.module.hr.dal.mysql.employeefamiles.EmployeeFamilesMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 员工家庭成员 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class EmployeeFamilesServiceImpl implements EmployeeFamilesService {

    @Resource
    private EmployeeFamilesMapper employeeFamilesMapper;

    @Override
    public Long createEmployeeFamiles(EmployeeFamilesCreateReqVO createReqVO) {
        // 插入
        EmployeeFamilesDO employeeFamiles = EmployeeFamilesConvert.INSTANCE.convert(createReqVO);
        employeeFamilesMapper.insert(employeeFamiles);
        // 返回
        return employeeFamiles.getId();
    }

    @Override
    public Long createEmployeeFamilesMy(EmployeeFamilesCreateReqVO createReqVO) {
        // 插入
        EmployeeFamilesDO employeeFamiles = EmployeeFamilesConvert.INSTANCE.convert(createReqVO);
        employeeFamiles.setCreator(String.valueOf(getLoginUserId()));
        employeeFamilesMapper.insert(employeeFamiles);
        // 返回
        return employeeFamiles.getId();
    }

    @Override
    public void updateEmployeeFamiles(EmployeeFamilesUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeFamilesExists(updateReqVO.getId());
        // 校验是否为本人修改
        EmployeeFamilesDO familesDO = employeeFamilesMapper.selectById(updateReqVO.getId());
        if (familesDO.getEmployeeId() != updateReqVO.getUserId()) throw exception(EMPLOYEE_FAMILES_NO_PERMISSION);
        // 更新
        EmployeeFamilesDO updateObj = EmployeeFamilesConvert.INSTANCE.convert(updateReqVO);
        employeeFamilesMapper.updateById(updateObj);
    }

    @Override
    public void updateEmployeeFamilesMy(EmployeeFamilesUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeFamilesExistsMy(updateReqVO.getId());
        // 校验是否为本人修改
        EmployeeFamilesDO familesDO = employeeFamilesMapper.selectById(updateReqVO.getId());
        if (familesDO.getEmployeeId() != updateReqVO.getUserId()) throw exception(EMPLOYEE_FAMILES_NO_PERMISSION);
        // 更新
        EmployeeFamilesDO updateObj = EmployeeFamilesConvert.INSTANCE.convert(updateReqVO);
        employeeFamilesMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmployeeFamiles(Long id) {
        // 校验存在
        validateEmployeeFamilesExists(id);
        // 删除
        employeeFamilesMapper.deleteById(id);
    }

    @Override
    public void deleteEmployeeFamilesMy(Long id) {
        // 校验存在
        validateEmployeeFamilesExistsMy(id);
        // 删除
        employeeFamilesMapper.deleteById(id);
    }

    private void validateEmployeeFamilesExists(Long id) {
        if (employeeFamilesMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_FAMILES_NOT_EXISTS);
        }
    }

    private void validateEmployeeFamilesExistsMy(Long id) {
        if (employeeFamilesMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_FAMILES_NOT_EXISTS);
        }
        if (!employeeFamilesMapper.selectById(id).getCreator().equals(String.valueOf(getLoginUserId()))){
            throw exception(EMPLOYEE_FAMILES_NOT_EXISTS);
        }
    }

    @Override
    public EmployeeFamilesDO getEmployeeFamiles(Long id) {
        return employeeFamilesMapper.selectById(id);
    }

    @Override
    public List<EmployeeFamilesDO> getEmployeeFamilesMy(Long id) {
        return employeeFamilesMapper.selectOneInfo(String.valueOf(id));
    }

    @Override
    public List<EmployeeFamilesDO> getEmployeeFamilesList(Collection<Integer> ids) {
        return employeeFamilesMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EmployeeFamilesDO> getEmployeeFamilesPage(EmployeeFamilesPageReqVO pageReqVO) {
        return employeeFamilesMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmployeeFamilesDO> getEmployeeFamilesList(EmployeeFamilesExportReqVO exportReqVO) {
        return employeeFamilesMapper.selectList(exportReqVO);
    }

}
