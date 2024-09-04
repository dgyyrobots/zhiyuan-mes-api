package com.dofast.module.hr.service.salary;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.salary.vo.*;
import com.dofast.module.hr.dal.dataobject.salary.SalaryDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.salary.SalaryConvert;
import com.dofast.module.hr.dal.mysql.salary.SalaryMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 工资总 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class SalaryServiceImpl implements SalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    @Override
    public Integer createSalary(SalaryCreateReqVO createReqVO) {
        // 插入
        SalaryDO salary = SalaryConvert.INSTANCE.convert(createReqVO);
        salaryMapper.insert(salary);
        // 返回
        return salary.getId();
    }

    @Override
    public void updateSalary(SalaryUpdateReqVO updateReqVO) {
        // 校验存在
        validateSalaryExists(updateReqVO.getId());
        // 更新
        SalaryDO updateObj = SalaryConvert.INSTANCE.convert(updateReqVO);
        salaryMapper.updateById(updateObj);
    }

    @Override
    public void deleteSalary(Integer id) {
        // 校验存在
        validateSalaryExists(id);
        // 删除
        salaryMapper.deleteById(id);
    }

    private void validateSalaryExists(Integer id) {
        if (salaryMapper.selectById(id) == null) {
            throw exception(SALARY_NOT_EXISTS);
        }
    }

    @Override
    public SalaryDO getSalary(Integer id) {
        return salaryMapper.selectById(id);
    }

    @Override
    public List<SalaryDO> getSalaryList(Collection<Integer> ids) {
        return salaryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SalaryDO> getSalaryPage(SalaryPageReqVO pageReqVO) {
        return salaryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SalaryDO> getSalaryList(SalaryExportReqVO exportReqVO) {
        return salaryMapper.selectList(exportReqVO);
    }

}
