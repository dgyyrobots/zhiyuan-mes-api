package com.dofast.module.hr.service.employeeworkhistory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.employeeworkhistory.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeworkhistory.EmployeeWorkhistoryDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.employeeworkhistory.EmployeeWorkhistoryConvert;
import com.dofast.module.hr.dal.mysql.employeeworkhistory.EmployeeWorkhistoryMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 员工工作经历 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class EmployeeWorkhistoryServiceImpl implements EmployeeWorkhistoryService {

    @Resource
    private EmployeeWorkhistoryMapper employeeWorkhistoryMapper;

    @Override
    public Long createEmployeeWorkhistory(EmployeeWorkhistoryCreateReqVO createReqVO) {
        // 插入
        EmployeeWorkhistoryDO employeeWorkhistory = EmployeeWorkhistoryConvert.INSTANCE.convert(createReqVO);
        employeeWorkhistoryMapper.insert(employeeWorkhistory);
        // 返回
        return employeeWorkhistory.getId();
    }

    @Override
    public Long createEmployeeWorkhistoryMy(EmployeeWorkhistoryCreateReqVO createReqVO) {
        // 插入
        EmployeeWorkhistoryDO employeeWorkhistory = EmployeeWorkhistoryConvert.INSTANCE.convert(createReqVO);
        employeeWorkhistory.setCreator(String.valueOf(getLoginUserId()));
        employeeWorkhistoryMapper.insert(employeeWorkhistory);
        // 返回
        return employeeWorkhistory.getId();
    }

    @Override
    public void updateEmployeeWorkhistory(EmployeeWorkhistoryUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeWorkhistoryExists(updateReqVO.getId());
        // 校验是否为本人操作
        EmployeeWorkhistoryDO employeeWorkhistoryDO = employeeWorkhistoryMapper.selectById(updateReqVO.getId());
        if(updateReqVO.getUserId() != employeeWorkhistoryDO.getEmployeeId()) throw exception(EMPLOYEE_EDUCATION_NO_PERMISSION);
        // 更新
        EmployeeWorkhistoryDO updateObj = EmployeeWorkhistoryConvert.INSTANCE.convert(updateReqVO);
        employeeWorkhistoryMapper.updateById(updateObj);
    }

    @Override
    public void updateEmployeeWorkhistoryMy(EmployeeWorkhistoryUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeWorkhistoryExistsMy(updateReqVO.getId());
        // 校验是否为本人操作
        EmployeeWorkhistoryDO employeeWorkhistoryDO = employeeWorkhistoryMapper.selectById(updateReqVO.getId());
        if(updateReqVO.getUserId() != employeeWorkhistoryDO.getEmployeeId()) throw exception(EMPLOYEE_EDUCATION_NO_PERMISSION);
        // 更新
        EmployeeWorkhistoryDO updateObj = EmployeeWorkhistoryConvert.INSTANCE.convert(updateReqVO);
        employeeWorkhistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmployeeWorkhistory(Long id) {
        // 校验存在
        validateEmployeeWorkhistoryExists(id);
        // 删除
        employeeWorkhistoryMapper.deleteById(id);
    }

    @Override
    public void deleteEmployeeWorkhistoryMy(Long id) {
        // 校验存在
        validateEmployeeWorkhistoryExistsMy(id);
        // 删除
        employeeWorkhistoryMapper.deleteById(id);
    }

    private void validateEmployeeWorkhistoryExists(Long id) {
        if (employeeWorkhistoryMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_WORKHISTORY_NOT_EXISTS);
        }
    }

    private void validateEmployeeWorkhistoryExistsMy(Long id) {
        if (employeeWorkhistoryMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_WORKHISTORY_NOT_EXISTS);
        }
        if (!employeeWorkhistoryMapper.selectById(id).equals(String.valueOf(getLoginUserId()))){
            throw exception(EMPLOYEE_WORKHISTORY_NOT_EXISTS);
        }
    }

    @Override
    public EmployeeWorkhistoryDO getEmployeeWorkhistory(Long id) {
        return employeeWorkhistoryMapper.selectById(id);
    }

    @Override
    public List<EmployeeWorkhistoryDO> getEmployeeWorkhistoryMy(Long id) {
        return employeeWorkhistoryMapper.selectOneInfo(String.valueOf(id));
    }

    @Override
    public List<EmployeeWorkhistoryDO> getEmployeeWorkhistoryList(Collection<Long> ids) {
        return employeeWorkhistoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EmployeeWorkhistoryDO> getEmployeeWorkhistoryPage(EmployeeWorkhistoryPageReqVO pageReqVO) {
        return employeeWorkhistoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmployeeWorkhistoryDO> getEmployeeWorkhistoryList(EmployeeWorkhistoryExportReqVO exportReqVO) {
        return employeeWorkhistoryMapper.selectList(exportReqVO);
    }

}
