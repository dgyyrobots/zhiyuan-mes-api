package com.dofast.module.hr.service.salarycommission;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.salarycommission.vo.*;
import com.dofast.module.hr.dal.dataobject.salarycommission.SalarycommissionDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.salarycommission.SalarycommissionConvert;
import com.dofast.module.hr.dal.mysql.salarycommission.SalarycommissionMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 绩效工资 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class SalarycommissionServiceImpl implements SalarycommissionService {

    @Resource
    private SalarycommissionMapper salarycommissionMapper;

    @Override
    public Integer createSalarycommission(SalarycommissionCreateReqVO createReqVO) {
        // 插入
        SalarycommissionDO salarycommission = SalarycommissionConvert.INSTANCE.convert(createReqVO);
        salarycommissionMapper.insert(salarycommission);
        // 返回
        return salarycommission.getId();
    }

    @Override
    public void updateSalarycommission(SalarycommissionUpdateReqVO updateReqVO) {
        // 校验存在
        validateSalarycommissionExists(updateReqVO.getId());
        // 更新
        SalarycommissionDO updateObj = SalarycommissionConvert.INSTANCE.convert(updateReqVO);
        salarycommissionMapper.updateById(updateObj);
    }

    @Override
    public void deleteSalarycommission(Integer id) {
        // 校验存在
        validateSalarycommissionExists(id);
        // 删除
        salarycommissionMapper.deleteById(id);
    }

    private void validateSalarycommissionExists(Integer id) {
        if (salarycommissionMapper.selectById(id) == null) {
            throw exception(SALARYCOMMISSION_NOT_EXISTS);
        }
    }

    @Override
    public SalarycommissionDO getSalarycommission(Integer id) {
        return salarycommissionMapper.selectById(id);
    }

    @Override
    public List<SalarycommissionDO> getSalarycommissionList(Collection<Integer> ids) {
        return salarycommissionMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SalarycommissionDO> getSalarycommissionPage(SalarycommissionPageReqVO pageReqVO) {
        return salarycommissionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SalarycommissionDO> getSalarycommissionList(SalarycommissionExportReqVO exportReqVO) {
        return salarycommissionMapper.selectList(exportReqVO);
    }

}
