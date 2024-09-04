package com.dofast.module.hr.service.salarydetail;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.hr.controller.admin.salarydetail.vo.*;
import com.dofast.module.hr.dal.dataobject.salarydetail.SalarydetailDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.hr.convert.salarydetail.SalarydetailConvert;
import com.dofast.module.hr.dal.mysql.salarydetail.SalarydetailMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;

/**
 * 工资明细 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class SalarydetailServiceImpl implements SalarydetailService {

    @Resource
    private SalarydetailMapper salarydetailMapper;

    @Override
    public Integer createSalarydetail(SalarydetailCreateReqVO createReqVO) {
        // 插入
        SalarydetailDO salarydetail = SalarydetailConvert.INSTANCE.convert(createReqVO);
        salarydetailMapper.insert(salarydetail);
        // 返回
        return salarydetail.getId();
    }

    @Override
    public void updateSalarydetail(SalarydetailUpdateReqVO updateReqVO) {
        // 校验存在
        validateSalarydetailExists(updateReqVO.getId());
        // 更新
        SalarydetailDO updateObj = SalarydetailConvert.INSTANCE.convert(updateReqVO);
        salarydetailMapper.updateById(updateObj);
    }

    @Override
    public void deleteSalarydetail(Integer id) {
        // 校验存在
        validateSalarydetailExists(id);
        // 删除
        salarydetailMapper.deleteById(id);
    }

    private void validateSalarydetailExists(Integer id) {
        if (salarydetailMapper.selectById(id) == null) {
            throw exception(SALARYDETAIL_NOT_EXISTS);
        }
    }

    @Override
    public SalarydetailDO getSalarydetail(Integer id) {
        return salarydetailMapper.selectById(id);
    }

    @Override
    public List<SalarydetailDO> getSalarydetailList(Collection<Integer> ids) {
        return salarydetailMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SalarydetailDO> getSalarydetailPage(SalarydetailPageReqVO pageReqVO) {
        return salarydetailMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SalarydetailDO> getSalarydetailList(SalarydetailExportReqVO exportReqVO) {
        return salarydetailMapper.selectList(exportReqVO);
    }

}
