package com.dofast.module.hr.service.salary;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.hr.controller.admin.salary.vo.*;
import com.dofast.module.hr.dal.dataobject.salary.SalaryDO;
import com.dofast.module.hr.dal.mysql.salary.SalaryMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link SalaryServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(SalaryServiceImpl.class)
public class SalaryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SalaryServiceImpl salaryService;

    @Resource
    private SalaryMapper salaryMapper;

    @Test
    public void testCreateSalary_success() {
        // 准备参数
        SalaryCreateReqVO reqVO = randomPojo(SalaryCreateReqVO.class);

        // 调用
        Integer salaryId = salaryService.createSalary(reqVO);
        // 断言
        assertNotNull(salaryId);
        // 校验记录的属性是否正确
        SalaryDO salary = salaryMapper.selectById(salaryId);
        assertPojoEquals(reqVO, salary);
    }

    @Test
    public void testUpdateSalary_success() {
        // mock 数据
        SalaryDO dbSalary = randomPojo(SalaryDO.class);
        salaryMapper.insert(dbSalary);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SalaryUpdateReqVO reqVO = randomPojo(SalaryUpdateReqVO.class, o -> {
            o.setId(dbSalary.getId()); // 设置更新的 ID
        });

        // 调用
        salaryService.updateSalary(reqVO);
        // 校验是否更新正确
        SalaryDO salary = salaryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, salary);
    }

    @Test
    public void testUpdateSalary_notExists() {
        // 准备参数
        SalaryUpdateReqVO reqVO = randomPojo(SalaryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> salaryService.updateSalary(reqVO), SALARY_NOT_EXISTS);
    }

    @Test
    public void testDeleteSalary_success() {
        // mock 数据
        SalaryDO dbSalary = randomPojo(SalaryDO.class);
        salaryMapper.insert(dbSalary);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbSalary.getId();

        // 调用
        salaryService.deleteSalary(id);
       // 校验数据不存在了
       assertNull(salaryMapper.selectById(id));
    }

    @Test
    public void testDeleteSalary_notExists() {
        // 准备参数
//        Integer id = randomIntegerId();
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> salaryService.deleteSalary(id), SALARY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalaryPage() {
       // mock 数据
       SalaryDO dbSalary = randomPojo(SalaryDO.class, o -> { // 等会查询到
           o.setOrderFlowNo(null);
           o.setDetailNo(null);
           o.setSn(null);
           o.setMonth(null);
           o.setAccount(null);
           o.setCompany(null);
           o.setDept(null);
           o.setBasic(null);
           o.setPerformance(null);
           o.setBonus(null);
           o.setAllowance(null);
           o.setExemption(null);
           o.setDeduction(null);
           o.setDeserved(null);
           o.setActual(null);
           o.setCompanySsf(null);
           o.setCompanyHpf(null);
           o.setCurTaxableIncome(null);
           o.setTaxableIncome(null);
           o.setTaxPayable(null);
           o.setTaxPaid(null);
           o.setTax(null);
           o.setConfirmer(null);
           o.setConfirmTime(null);
           o.setGranter(null);
           o.setGrantTime(null);
           o.setDesc(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       salaryMapper.insert(dbSalary);
       // 测试 orderFlowNo 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setOrderFlowNo(null)));
       // 测试 detailNo 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDetailNo(null)));
       // 测试 sn 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setSn(null)));
       // 测试 month 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setMonth(null)));
       // 测试 account 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setAccount(null)));
       // 测试 company 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCompany(null)));
       // 测试 dept 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDept(null)));
       // 测试 basic 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setBasic(null)));
       // 测试 performance 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setPerformance(null)));
       // 测试 bonus 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setBonus(null)));
       // 测试 allowance 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setAllowance(null)));
       // 测试 exemption 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setExemption(null)));
       // 测试 deduction 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDeduction(null)));
       // 测试 deserved 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDeserved(null)));
       // 测试 actual 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setActual(null)));
       // 测试 companySsf 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCompanySsf(null)));
       // 测试 companyHpf 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCompanyHpf(null)));
       // 测试 curTaxableIncome 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCurTaxableIncome(null)));
       // 测试 taxableIncome 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTaxableIncome(null)));
       // 测试 taxPayable 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTaxPayable(null)));
       // 测试 taxPaid 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTaxPaid(null)));
       // 测试 tax 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTax(null)));
       // 测试 confirmer 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setConfirmer(null)));
       // 测试 confirmTime 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setConfirmTime(null)));
       // 测试 granter 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setGranter(null)));
       // 测试 grantTime 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setGrantTime(null)));
       // 测试 desc 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDesc(null)));
       // 测试 status 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCreateTime(null)));
       // 准备参数
       SalaryPageReqVO reqVO = new SalaryPageReqVO();
       reqVO.setOrderFlowNo(null);
       reqVO.setDetailNo(null);
       reqVO.setSn(null);
       reqVO.setMonth(null);
       reqVO.setAccount(null);
       reqVO.setCompany(null);
       reqVO.setDept(null);
       reqVO.setBasic(null);
       reqVO.setPerformance(null);
       reqVO.setBonus(null);
       reqVO.setAllowance(null);
       reqVO.setExemption(null);
       reqVO.setDeduction(null);
       reqVO.setDeserved(null);
       reqVO.setActual(null);
       reqVO.setCompanySsf(null);
       reqVO.setCompanyHpf(null);
       reqVO.setCurTaxableIncome(null);
       reqVO.setTaxableIncome(null);
       reqVO.setTaxPayable(null);
       reqVO.setTaxPaid(null);
       reqVO.setTax(null);
       reqVO.setConfirmer(null);
       reqVO.setConfirmTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setGranter(null);
       reqVO.setGrantTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDesc(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SalaryDO> pageResult = salaryService.getSalaryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSalary, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalaryList() {
       // mock 数据
       SalaryDO dbSalary = randomPojo(SalaryDO.class, o -> { // 等会查询到
           o.setOrderFlowNo(null);
           o.setDetailNo(null);
           o.setSn(null);
           o.setMonth(null);
           o.setAccount(null);
           o.setCompany(null);
           o.setDept(null);
           o.setBasic(null);
           o.setPerformance(null);
           o.setBonus(null);
           o.setAllowance(null);
           o.setExemption(null);
           o.setDeduction(null);
           o.setDeserved(null);
           o.setActual(null);
           o.setCompanySsf(null);
           o.setCompanyHpf(null);
           o.setCurTaxableIncome(null);
           o.setTaxableIncome(null);
           o.setTaxPayable(null);
           o.setTaxPaid(null);
           o.setTax(null);
           o.setConfirmer(null);
           o.setConfirmTime(null);
           o.setGranter(null);
           o.setGrantTime(null);
           o.setDesc(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       salaryMapper.insert(dbSalary);
       // 测试 orderFlowNo 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setOrderFlowNo(null)));
       // 测试 detailNo 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDetailNo(null)));
       // 测试 sn 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setSn(null)));
       // 测试 month 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setMonth(null)));
       // 测试 account 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setAccount(null)));
       // 测试 company 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCompany(null)));
       // 测试 dept 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDept(null)));
       // 测试 basic 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setBasic(null)));
       // 测试 performance 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setPerformance(null)));
       // 测试 bonus 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setBonus(null)));
       // 测试 allowance 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setAllowance(null)));
       // 测试 exemption 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setExemption(null)));
       // 测试 deduction 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDeduction(null)));
       // 测试 deserved 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDeserved(null)));
       // 测试 actual 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setActual(null)));
       // 测试 companySsf 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCompanySsf(null)));
       // 测试 companyHpf 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCompanyHpf(null)));
       // 测试 curTaxableIncome 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCurTaxableIncome(null)));
       // 测试 taxableIncome 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTaxableIncome(null)));
       // 测试 taxPayable 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTaxPayable(null)));
       // 测试 taxPaid 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTaxPaid(null)));
       // 测试 tax 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setTax(null)));
       // 测试 confirmer 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setConfirmer(null)));
       // 测试 confirmTime 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setConfirmTime(null)));
       // 测试 granter 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setGranter(null)));
       // 测试 grantTime 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setGrantTime(null)));
       // 测试 desc 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setDesc(null)));
       // 测试 status 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       salaryMapper.insert(cloneIgnoreId(dbSalary, o -> o.setCreateTime(null)));
       // 准备参数
       SalaryExportReqVO reqVO = new SalaryExportReqVO();
       reqVO.setOrderFlowNo(null);
       reqVO.setDetailNo(null);
       reqVO.setSn(null);
       reqVO.setMonth(null);
       reqVO.setAccount(null);
       reqVO.setCompany(null);
       reqVO.setDept(null);
       reqVO.setBasic(null);
       reqVO.setPerformance(null);
       reqVO.setBonus(null);
       reqVO.setAllowance(null);
       reqVO.setExemption(null);
       reqVO.setDeduction(null);
       reqVO.setDeserved(null);
       reqVO.setActual(null);
       reqVO.setCompanySsf(null);
       reqVO.setCompanyHpf(null);
       reqVO.setCurTaxableIncome(null);
       reqVO.setTaxableIncome(null);
       reqVO.setTaxPayable(null);
       reqVO.setTaxPaid(null);
       reqVO.setTax(null);
       reqVO.setConfirmer(null);
       reqVO.setConfirmTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setGranter(null);
       reqVO.setGrantTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDesc(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<SalaryDO> list = salaryService.getSalaryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSalary, list.get(0));
    }

}
