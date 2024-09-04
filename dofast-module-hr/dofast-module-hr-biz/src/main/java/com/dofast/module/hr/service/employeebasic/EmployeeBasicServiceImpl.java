package com.dofast.module.hr.service.employeebasic;

import com.dofast.framework.common.pad.util.PadSecurityUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.employeebasic.vo.*;
import com.dofast.module.hr.dal.dataobject.employeebasic.EmployeeBasicDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.employeebasic.EmployeeBasicConvert;
import com.dofast.module.hr.dal.mysql.employeebasic.EmployeeBasicMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 员工基本信息 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class EmployeeBasicServiceImpl implements EmployeeBasicService {

    @Resource
    private EmployeeBasicMapper employeeBasicMapper;

    @Override
    public Long createEmployeeBasic(EmployeeBasicCreateReqVO createReqVO) {
        //校验
        List<EmployeeBasicDO> list = employeeBasicMapper.selectValidateExistList(createReqVO);
        if(list != null){
            throw exception(EMPLOYEE_BASIC_IS_EXISTS);
        }
        // 插入
        EmployeeBasicDO employeeBasic = EmployeeBasicConvert.INSTANCE.convert(createReqVO);
        employeeBasicMapper.insert(employeeBasic);
        // 返回
        return employeeBasic.getId();
    }

    @Override
    public Long createEmployeeBasicMy(EmployeeBasicCreateReqVO createReqVO) {
        //校验
        List<EmployeeBasicDO> list = employeeBasicMapper.selectValidateExistList(createReqVO);
        if(list.size()!=0 ){
            throw exception(EMPLOYEE_BASIC_IS_EXISTS);
        }

        if(list.size()>1 ){
            throw exception(EMPLOYEE_BASIC_IS_EXISTS);
        }
        // 插入
        EmployeeBasicDO employeeBasic = EmployeeBasicConvert.INSTANCE.convert(createReqVO);
        employeeBasic.setCreator(String.valueOf(getLoginUserId()));
        employeeBasicMapper.insert(employeeBasic);
        // 返回
        return employeeBasic.getId();
    }

    @Override
    public void updateEmployeeBasic(EmployeeBasicUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeBasicExists(updateReqVO.getId());
        // 更新
        EmployeeBasicDO updateObj = EmployeeBasicConvert.INSTANCE.convert(updateReqVO);
        employeeBasicMapper.updateById(updateObj);
    }

    @Override
    public void updateEmployeeBasicMy(EmployeeBasicUpdateReqVO updateReqVO) {
        // 校验存在
        validateEmployeeBasicExistsMy(updateReqVO.getId());
        // 更新
        EmployeeBasicDO updateObj = EmployeeBasicConvert.INSTANCE.convert(updateReqVO);
        employeeBasicMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmployeeBasic(Long id) {
        // 校验存在
        validateEmployeeBasicExists(id);
        // 删除
        employeeBasicMapper.deleteById(id);
    }

    @Override
    public void deleteEmployeeBasicMy(Long id) {
        // 校验存在
        validateEmployeeBasicExistsMy(id);
        // 删除
        employeeBasicMapper.deleteById(id);
    }

    private void validateEmployeeBasicExists(Long id) {
        if (employeeBasicMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_BASIC_NOT_EXISTS);
        }
        if(employeeBasicMapper.selectById(id).getStus().equals("FINISHED")){
            throw exception(EMPLOYEE_BASIC_FINISHED_NO_PERMISSION);
        }
    }

    private void validateEmployeeBasicExistsMy(Long id) {
        EmployeeBasicDO employeeBasicDO = employeeBasicMapper.selectById(id);
        if (employeeBasicDO == null) {
            throw exception(EMPLOYEE_BASIC_NOT_EXISTS);
        }
        if (!employeeBasicDO.getCreator().equals(String.valueOf(getLoginUserId()))){
            throw exception(EMPLOYEE_BASIC_FINISHED_NO_PERMISSION);
        }
        if(employeeBasicDO.getStus().equals("FINISHED")){
            throw exception(EMPLOYEE_BASIC_FINISHED_NO_PERMISSION);
        }
    }

    @Override
    public EmployeeBasicDO getEmployeeBasic(Long id) {
        return employeeBasicMapper.selectById(id);
    }

    @Override
    public EmployeeBasicDO getEmployeeBasicMy(Long id) {
        return employeeBasicMapper.selectOneInfo(String.valueOf(id));
    }

    @Override
    public List<EmployeeBasicDO> getEmployeeBasicList(Collection<Integer> ids) {
        return employeeBasicMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EmployeeBasicDO> getEmployeeBasicPage(EmployeeBasicPageReqVO pageReqVO) {
        return employeeBasicMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmployeeBasicDO> getEmployeeBasicList(EmployeeBasicExportReqVO exportReqVO) {
        return employeeBasicMapper.selectList(exportReqVO);
    }


}
