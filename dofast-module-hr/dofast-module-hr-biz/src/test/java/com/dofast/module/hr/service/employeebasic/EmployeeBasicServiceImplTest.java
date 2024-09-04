package com.dofast.module.hr.service.employeebasic;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.hr.controller.admin.employeebasic.vo.*;
import com.dofast.module.hr.dal.dataobject.employeebasic.EmployeeBasicDO;
import com.dofast.module.hr.dal.mysql.employeebasic.EmployeeBasicMapper;
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
 * {@link EmployeeBasicServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(EmployeeBasicServiceImpl.class)
public class EmployeeBasicServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmployeeBasicServiceImpl employeeBasicService;

    @Resource
    private EmployeeBasicMapper employeeBasicMapper;

    @Test
    public void testCreateEmployeeBasic_success() {
        // 准备参数
        EmployeeBasicCreateReqVO reqVO = randomPojo(EmployeeBasicCreateReqVO.class);

        // 调用
        Long employeeBasicId = employeeBasicService.createEmployeeBasic(reqVO);
        // 断言
        assertNotNull(employeeBasicId);
        // 校验记录的属性是否正确
        EmployeeBasicDO employeeBasic = employeeBasicMapper.selectById(employeeBasicId);
        assertPojoEquals(reqVO, employeeBasic);
    }

    @Test
    public void testUpdateEmployeeBasic_success() {
        // mock 数据
        EmployeeBasicDO dbEmployeeBasic = randomPojo(EmployeeBasicDO.class);
        employeeBasicMapper.insert(dbEmployeeBasic);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmployeeBasicUpdateReqVO reqVO = randomPojo(EmployeeBasicUpdateReqVO.class, o -> {
            o.setId(dbEmployeeBasic.getId()); // 设置更新的 ID
        });

        // 调用
        employeeBasicService.updateEmployeeBasic(reqVO);
        // 校验是否更新正确
        EmployeeBasicDO employeeBasic = employeeBasicMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, employeeBasic);
    }

    @Test
    public void testUpdateEmployeeBasic_notExists() {
        // 准备参数
        EmployeeBasicUpdateReqVO reqVO = randomPojo(EmployeeBasicUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> employeeBasicService.updateEmployeeBasic(reqVO), EMPLOYEE_BASIC_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmployeeBasic_success() {
        // mock 数据
        EmployeeBasicDO dbEmployeeBasic = randomPojo(EmployeeBasicDO.class);
        employeeBasicMapper.insert(dbEmployeeBasic);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmployeeBasic.getId();

        // 调用
        employeeBasicService.deleteEmployeeBasic(id);
       // 校验数据不存在了
       assertNull(employeeBasicMapper.selectById(id));
    }

    @Test
    public void testDeleteEmployeeBasic_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> employeeBasicService.deleteEmployeeBasic(id), EMPLOYEE_BASIC_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeBasicPage() {
       // mock 数据
       EmployeeBasicDO dbEmployeeBasic = randomPojo(EmployeeBasicDO.class, o -> { // 等会查询到
           o.setEmployeePhoto(null);
           o.setEmployeeRealname(null);
           o.setEmployeeSex(null);
           o.setEmployeeMarried(null);
           o.setEmployeePolitics(null);
           o.setEmployeeNativeplace(null);
           o.setEmployeeBirthday(null);
           o.setEmployeeHeight(null);
           o.setHouseholdProvinceId(null);
           o.setHouseholdProvinceName(null);
           o.setHouseholdNature(null);
           o.setHouseholdCityId(null);
           o.setHouseholdAddress(null);
           o.setHouseholdCityName(null);
           o.setEmployeeWeight(null);
           o.setBloodtype(null);
           o.setEducationName(null);
           o.setEducation(null);
           o.setEmails(null);
           o.setQq(null);
           o.setIdNo(null);
           o.setPhone(null);
           o.setCardName(null);
           o.setCardNo(null);
           o.setCardAddress(null);
           o.setNowProvinceId(null);
           o.setNowProvinceName(null);
           o.setNowProvinceAddress(null);
           o.setNowCityId(null);
           o.setNowCityName(null);
           o.setUrgentNameOne(null);
           o.setUrgentNameOnePhone(null);
           o.setSpecialty(null);
           o.setRemark(null);
           o.setDepId(null);
           o.setDepName(null);
           o.setPositionId(null);
           o.setPositionName(null);
           o.setNickName(null);
           o.setSocialPaymentTime(null);
           o.setDateOfBirthTime(null);
           o.setWorkAge(null);
           o.setWorkLevel(null);
           o.setEntryTime(null);
           o.setWorkerTime(null);
           o.setWorkName(null);
           o.setEthnic(null);
           o.setChara(null);
           o.setStus(null);
           o.setCreateTime(null);
       });
       employeeBasicMapper.insert(dbEmployeeBasic);
       // 测试 employeePhoto 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeePhoto(null)));
       // 测试 employeeRealname 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeRealname(null)));
       // 测试 employeeSex 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeSex(null)));
       // 测试 employeeMarried 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeMarried(null)));
       // 测试 employeePolitics 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeePolitics(null)));
       // 测试 employeeNativeplace 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeNativeplace(null)));
       // 测试 employeeBirthday 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeBirthday(null)));
       // 测试 employeeHeight 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeHeight(null)));
       // 测试 householdProvinceId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdProvinceId(null)));
       // 测试 householdProvinceName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdProvinceName(null)));
       // 测试 householdNature 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdNature(null)));
       // 测试 householdCityId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdCityId(null)));
       // 测试 householdAddress 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdAddress(null)));
       // 测试 householdCityName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdCityName(null)));
       // 测试 employeeWeight 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeWeight(null)));
       // 测试 bloodtype 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setBloodtype(null)));
       // 测试 educationName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEducationName(null)));
       // 测试 education 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEducation(null)));
       // 测试 emails 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmails(null)));
       // 测试 qq 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setQq(null)));
       // 测试 idNo 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setIdNo(null)));
       // 测试 phone 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setPhone(null)));
       // 测试 cardName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCardName(null)));
       // 测试 cardNo 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCardNo(null)));
       // 测试 cardAddress 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCardAddress(null)));
       // 测试 nowProvinceId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowProvinceId(null)));
       // 测试 nowProvinceName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowProvinceName(null)));
       // 测试 nowProvinceAddress 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowProvinceAddress(null)));
       // 测试 nowCityId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowCityId(null)));
       // 测试 nowCityName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowCityName(null)));
       // 测试 urgentNameOne 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setUrgentNameOne(null)));
       // 测试 urgentNameOnePhone 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setUrgentNameOnePhone(null)));
       // 测试 specialty 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setSpecialty(null)));
       // 测试 remark 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setRemark(null)));
       // 测试 depId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setDepId(null)));
       // 测试 depName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setDepName(null)));
       // 测试 positionId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setPositionId(null)));
       // 测试 positionName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setPositionName(null)));
       // 测试 nickName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNickName(null)));
       // 测试 socialPaymentTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setSocialPaymentTime(null)));
       // 测试 dateOfBirthTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setDateOfBirthTime(null)));
       // 测试 workAge 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkAge(null)));
       // 测试 workLevel 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkLevel(null)));
       // 测试 entryTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEntryTime(null)));
       // 测试 workerTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkerTime(null)));
       // 测试 workName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkName(null)));
       // 测试 ethnic 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEthnic(null)));
       // 测试 chara 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setChara(null)));
       // 测试 stus 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setStus(null)));
       // 测试 createTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeBasicPageReqVO reqVO = new EmployeeBasicPageReqVO();
       reqVO.setEmployeePhoto(null);
       reqVO.setEmployeeRealname(null);
       reqVO.setEmployeeSex(null);
       reqVO.setEmployeeMarried(null);
       reqVO.setEmployeePolitics(null);
       reqVO.setEmployeeNativeplace(null);
       reqVO.setEmployeeBirthday(null);
       reqVO.setEmployeeHeight(null);
       reqVO.setHouseholdProvinceId(null);
       reqVO.setHouseholdProvinceName(null);
       reqVO.setHouseholdNature(null);
       reqVO.setHouseholdCityId(null);
       reqVO.setHouseholdAddress(null);
       reqVO.setHouseholdCityName(null);
       reqVO.setEmployeeWeight(null);
       reqVO.setBloodtype(null);
       reqVO.setEducationName(null);
       reqVO.setEducation(null);
       reqVO.setEmails(null);
       reqVO.setQq(null);
       reqVO.setIdNo(null);
       reqVO.setPhone(null);
       reqVO.setCardName(null);
       reqVO.setCardNo(null);
       reqVO.setCardAddress(null);
       reqVO.setNowProvinceId(null);
       reqVO.setNowProvinceName(null);
       reqVO.setNowProvinceAddress(null);
       reqVO.setNowCityId(null);
       reqVO.setNowCityName(null);
       reqVO.setUrgentNameOne(null);
       reqVO.setUrgentNameOnePhone(null);
       reqVO.setSpecialty(null);
       reqVO.setRemark(null);
       reqVO.setDepId(null);
       reqVO.setDepName(null);
       reqVO.setPositionId(null);
       reqVO.setPositionName(null);
       reqVO.setNickName(null);
       //reqVO.setSocialPaymentTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       //reqVO.setDateOfBirthTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setWorkAge(null);
       reqVO.setWorkLevel(null);
       //reqVO.setEntryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       //reqVO.setWorkerTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setWorkName(null);
       reqVO.setEthnic(null);
       reqVO.setChara(null);
       reqVO.setStus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<EmployeeBasicDO> pageResult = employeeBasicService.getEmployeeBasicPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmployeeBasic, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeBasicList() {
       // mock 数据
       EmployeeBasicDO dbEmployeeBasic = randomPojo(EmployeeBasicDO.class, o -> { // 等会查询到
           o.setEmployeePhoto(null);
           o.setEmployeeRealname(null);
           o.setEmployeeSex(null);
           o.setEmployeeMarried(null);
           o.setEmployeePolitics(null);
           o.setEmployeeNativeplace(null);
           o.setEmployeeBirthday(null);
           o.setEmployeeHeight(null);
           o.setHouseholdProvinceId(null);
           o.setHouseholdProvinceName(null);
           o.setHouseholdNature(null);
           o.setHouseholdCityId(null);
           o.setHouseholdAddress(null);
           o.setHouseholdCityName(null);
           o.setEmployeeWeight(null);
           o.setBloodtype(null);
           o.setEducationName(null);
           o.setEducation(null);
           o.setEmails(null);
           o.setQq(null);
           o.setIdNo(null);
           o.setPhone(null);
           o.setCardName(null);
           o.setCardNo(null);
           o.setCardAddress(null);
           o.setNowProvinceId(null);
           o.setNowProvinceName(null);
           o.setNowProvinceAddress(null);
           o.setNowCityId(null);
           o.setNowCityName(null);
           o.setUrgentNameOne(null);
           o.setUrgentNameOnePhone(null);
           o.setSpecialty(null);
           o.setRemark(null);
           o.setDepId(null);
           o.setDepName(null);
           o.setPositionId(null);
           o.setPositionName(null);
           o.setNickName(null);
           o.setSocialPaymentTime(null);
           o.setDateOfBirthTime(null);
           o.setWorkAge(null);
           o.setWorkLevel(null);
           o.setEntryTime(null);
           o.setWorkerTime(null);
           o.setWorkName(null);
           o.setEthnic(null);
           o.setChara(null);
           o.setStus(null);
           o.setCreateTime(null);
       });
       employeeBasicMapper.insert(dbEmployeeBasic);
       // 测试 employeePhoto 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeePhoto(null)));
       // 测试 employeeRealname 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeRealname(null)));
       // 测试 employeeSex 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeSex(null)));
       // 测试 employeeMarried 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeMarried(null)));
       // 测试 employeePolitics 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeePolitics(null)));
       // 测试 employeeNativeplace 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeNativeplace(null)));
       // 测试 employeeBirthday 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeBirthday(null)));
       // 测试 employeeHeight 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeHeight(null)));
       // 测试 householdProvinceId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdProvinceId(null)));
       // 测试 householdProvinceName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdProvinceName(null)));
       // 测试 householdNature 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdNature(null)));
       // 测试 householdCityId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdCityId(null)));
       // 测试 householdAddress 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdAddress(null)));
       // 测试 householdCityName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setHouseholdCityName(null)));
       // 测试 employeeWeight 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmployeeWeight(null)));
       // 测试 bloodtype 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setBloodtype(null)));
       // 测试 educationName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEducationName(null)));
       // 测试 education 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEducation(null)));
       // 测试 emails 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEmails(null)));
       // 测试 qq 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setQq(null)));
       // 测试 idNo 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setIdNo(null)));
       // 测试 phone 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setPhone(null)));
       // 测试 cardName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCardName(null)));
       // 测试 cardNo 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCardNo(null)));
       // 测试 cardAddress 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCardAddress(null)));
       // 测试 nowProvinceId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowProvinceId(null)));
       // 测试 nowProvinceName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowProvinceName(null)));
       // 测试 nowProvinceAddress 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowProvinceAddress(null)));
       // 测试 nowCityId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowCityId(null)));
       // 测试 nowCityName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNowCityName(null)));
       // 测试 urgentNameOne 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setUrgentNameOne(null)));
       // 测试 urgentNameOnePhone 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setUrgentNameOnePhone(null)));
       // 测试 specialty 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setSpecialty(null)));
       // 测试 remark 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setRemark(null)));
       // 测试 depId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setDepId(null)));
       // 测试 depName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setDepName(null)));
       // 测试 positionId 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setPositionId(null)));
       // 测试 positionName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setPositionName(null)));
       // 测试 nickName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setNickName(null)));
       // 测试 socialPaymentTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setSocialPaymentTime(null)));
       // 测试 dateOfBirthTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setDateOfBirthTime(null)));
       // 测试 workAge 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkAge(null)));
       // 测试 workLevel 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkLevel(null)));
       // 测试 entryTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEntryTime(null)));
       // 测试 workerTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkerTime(null)));
       // 测试 workName 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setWorkName(null)));
       // 测试 ethnic 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setEthnic(null)));
       // 测试 chara 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setChara(null)));
       // 测试 stus 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setStus(null)));
       // 测试 createTime 不匹配
       employeeBasicMapper.insert(cloneIgnoreId(dbEmployeeBasic, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeBasicExportReqVO reqVO = new EmployeeBasicExportReqVO();
       reqVO.setEmployeePhoto(null);
       reqVO.setEmployeeRealname(null);
       reqVO.setEmployeeSex(null);
       reqVO.setEmployeeMarried(null);
       reqVO.setEmployeePolitics(null);
       reqVO.setEmployeeNativeplace(null);
       reqVO.setEmployeeBirthday(null);
       reqVO.setEmployeeHeight(null);
       reqVO.setHouseholdProvinceId(null);
       reqVO.setHouseholdProvinceName(null);
       reqVO.setHouseholdNature(null);
       reqVO.setHouseholdCityId(null);
       reqVO.setHouseholdAddress(null);
       reqVO.setHouseholdCityName(null);
       reqVO.setEmployeeWeight(null);
       reqVO.setBloodtype(null);
       reqVO.setEducationName(null);
       reqVO.setEducation(null);
       reqVO.setEmails(null);
       reqVO.setQq(null);
       reqVO.setIdNo(null);
       reqVO.setPhone(null);
       reqVO.setCardName(null);
       reqVO.setCardNo(null);
       reqVO.setCardAddress(null);
       reqVO.setNowProvinceId(null);
       reqVO.setNowProvinceName(null);
       reqVO.setNowProvinceAddress(null);
       reqVO.setNowCityId(null);
       reqVO.setNowCityName(null);
       reqVO.setUrgentNameOne(null);
       reqVO.setUrgentNameOnePhone(null);
       reqVO.setSpecialty(null);
       reqVO.setRemark(null);
       reqVO.setDepId(null);
       reqVO.setDepName(null);
       reqVO.setPositionId(null);
       reqVO.setPositionName(null);
       reqVO.setNickName(null);
       //reqVO.setSocialPaymentTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       //reqVO.setDateOfBirthTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setWorkAge(null);
       reqVO.setWorkLevel(null);
       //reqVO.setEntryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       //reqVO.setWorkerTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setWorkName(null);
       reqVO.setEthnic(null);
       reqVO.setChara(null);
       reqVO.setStus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<EmployeeBasicDO> list = employeeBasicService.getEmployeeBasicList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmployeeBasic, list.get(0));
    }

}
