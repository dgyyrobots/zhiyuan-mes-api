package com.dofast.module.hr.service.employeefile;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.employeefile.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefile.EmployeeFileDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.employeefile.EmployeeFileConvert;
import com.dofast.module.hr.dal.mysql.employeefile.EmployeeFileMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 员工信息文件 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class EmployeeFileServiceImpl implements EmployeeFileService {

    @Resource
    private EmployeeFileMapper employeeFileMapper;

    @Override
    public Long createEmployeeFile(EmployeeFileCreateReqVO createReqVO) {
        //验证
        List<EmployeeFileDO> list = employeeFileMapper.selectValidateExistList(createReqVO);
        if (list != null){
            throw exception(EMPLOYEE_FILE_IS_EXISTS);
        }
        // 插入
        EmployeeFileDO employeeFile = EmployeeFileConvert.INSTANCE.convert(createReqVO);
        employeeFileMapper.insert(employeeFile);
        // 返回
        return employeeFile.getId();
    }

    @Override
    public Long createEmployeeFileMy(EmployeeFileCreateReqVO createReqVO) {
        //验证
        List<EmployeeFileDO> list = employeeFileMapper.selectValidateExistList(createReqVO);
        if (list.size()!=0){
            throw exception(EMPLOYEE_FILE_IS_EXISTS);
        }
        // 插入
        EmployeeFileDO employeeFile = EmployeeFileConvert.INSTANCE.convert(createReqVO);
        employeeFile.setCreator(String.valueOf(getLoginUserId()));
        employeeFileMapper.insert(employeeFile);
        // 返回
        return employeeFile.getId();
    }

    @Override
    public void updateEmployeeFile(EmployeeFileUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeFileExists(updateReqVO.getId());
        // 更新
        EmployeeFileDO updateObj = EmployeeFileConvert.INSTANCE.convert(updateReqVO);
        employeeFileMapper.updateById(updateObj);
    }

    @Override
    public void updateEmployeeFileMy(EmployeeFileUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeFileExistsMy(updateReqVO.getId());
        // 更新
        EmployeeFileDO updateObj = EmployeeFileConvert.INSTANCE.convert(updateReqVO);
        employeeFileMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmployeeFile(Long id) {
        // 校验存在
        validateEmployeeFileExists(id);
        // 删除
        employeeFileMapper.deleteById(id);
    }

    @Override
    public void deleteEmployeeFileMy(Long id) {
        // 校验存在
        validateEmployeeFileExistsMy(id);
        // 删除
        employeeFileMapper.deleteById(id);
    }

    private void validateEmployeeFileExists(Long id) {
        if (employeeFileMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_FILE_NOT_EXISTS);
        }
    }

    private void validateEmployeeFileExistsMy(Long id) {
        if (employeeFileMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_FILE_NOT_EXISTS);
        }
        if (!employeeFileMapper.selectById(id).getCreator().equals(String.valueOf(getLoginUserId()))){
            throw exception(EMPLOYEE_FILE_NOT_EXISTS);
        }
    }

    @Override
    public EmployeeFileDO getEmployeeFile(Long id) {
        return employeeFileMapper.selectById(id);
    }

    @Override
    public EmployeeFileDO getEmployeeFileMy(Long id) {
        return employeeFileMapper.selectOneInfo(String.valueOf(id));
    }

    @Override
    public List<EmployeeFileDO> getEmployeeFileList(Collection<Long> ids) {
        return employeeFileMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EmployeeFileDO> getEmployeeFilePage(EmployeeFilePageReqVO pageReqVO) {
        return employeeFileMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmployeeFileDO> getEmployeeFileList(EmployeeFileExportReqVO exportReqVO) {
        return employeeFileMapper.selectList(exportReqVO);
    }

}
