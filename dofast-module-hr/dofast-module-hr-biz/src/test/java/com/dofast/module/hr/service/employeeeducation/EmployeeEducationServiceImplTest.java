package com.dofast.module.hr.service.employeeeducation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.hr.controller.admin.employeeeducation.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeeducation.EmployeeEducationDO;
import com.dofast.module.hr.dal.mysql.employeeeducation.EmployeeEducationMapper;
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
 * {@link EmployeeEducationServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(EmployeeEducationServiceImpl.class)
public class EmployeeEducationServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmployeeEducationServiceImpl employeeEducationService;

    @Resource
    private EmployeeEducationMapper employeeEducationMapper;

    @Test
    public void testCreateEmployeeEducation_success() {
        // 准备参数
        EmployeeEducationCreateReqVO reqVO = randomPojo(EmployeeEducationCreateReqVO.class);

        // 调用
        Long employeeEducationId = employeeEducationService.createEmployeeEducation(reqVO);
        // 断言
        assertNotNull(employeeEducationId);
        // 校验记录的属性是否正确
        EmployeeEducationDO employeeEducation = employeeEducationMapper.selectById(employeeEducationId);
        assertPojoEquals(reqVO, employeeEducation);
    }

    @Test
    public void testUpdateEmployeeEducation_success() {
        // mock 数据
        EmployeeEducationDO dbEmployeeEducation = randomPojo(EmployeeEducationDO.class);
        employeeEducationMapper.insert(dbEmployeeEducation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmployeeEducationUpdateReqVO reqVO = randomPojo(EmployeeEducationUpdateReqVO.class, o -> {
            o.setId(dbEmployeeEducation.getId()); // 设置更新的 ID
        });

        // 调用
        employeeEducationService.updateEmployeeEducation(reqVO);
        // 校验是否更新正确
        EmployeeEducationDO employeeEducation = employeeEducationMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, employeeEducation);
    }

    @Test
    public void testUpdateEmployeeEducation_notExists() {
        // 准备参数
        EmployeeEducationUpdateReqVO reqVO = randomPojo(EmployeeEducationUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> employeeEducationService.updateEmployeeEducation(reqVO), EMPLOYEE_EDUCATION_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmployeeEducation_success() {
        // mock 数据
        EmployeeEducationDO dbEmployeeEducation = randomPojo(EmployeeEducationDO.class);
        employeeEducationMapper.insert(dbEmployeeEducation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmployeeEducation.getId();

        // 调用
        employeeEducationService.deleteEmployeeEducation(id);
       // 校验数据不存在了
       assertNull(employeeEducationMapper.selectById(id));
    }

    @Test
    public void testDeleteEmployeeEducation_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> employeeEducationService.deleteEmployeeEducation(id), EMPLOYEE_EDUCATION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeEducationPage() {
       // mock 数据
       EmployeeEducationDO dbEmployeeEducation = randomPojo(EmployeeEducationDO.class, o -> { // 等会查询到
           o.setEducationSchool(null);
           o.setEducationSpecialty(null);
           o.setEducationHonor(null);
           o.setEducationStarttime(null);
           o.setEducationEndtime(null);
           o.setEmployeeId(null);
           o.setCreateTime(null);
       });
       employeeEducationMapper.insert(dbEmployeeEducation);
       // 测试 educationSchool 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationSchool(null)));
       // 测试 educationSpecialty 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationSpecialty(null)));
       // 测试 educationHonor 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationHonor(null)));
       // 测试 educationStarttime 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationStarttime(null)));
       // 测试 educationEndtime 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationEndtime(null)));
       // 测试 employeeId 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEmployeeId(null)));
       // 测试 createTime 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeEducationPageReqVO reqVO = new EmployeeEducationPageReqVO();
       reqVO.setEducationSchool(null);
       reqVO.setEducationSpecialty(null);
       reqVO.setEducationHonor(null);
       reqVO.setEducationStarttime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEducationEndtime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEmployeeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<EmployeeEducationDO> pageResult = employeeEducationService.getEmployeeEducationPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmployeeEducation, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeEducationList() {
       // mock 数据
       EmployeeEducationDO dbEmployeeEducation = randomPojo(EmployeeEducationDO.class, o -> { // 等会查询到
           o.setEducationSchool(null);
           o.setEducationSpecialty(null);
           o.setEducationHonor(null);
           o.setEducationStarttime(null);
           o.setEducationEndtime(null);
           o.setEmployeeId(null);
           o.setCreateTime(null);
       });
       employeeEducationMapper.insert(dbEmployeeEducation);
       // 测试 educationSchool 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationSchool(null)));
       // 测试 educationSpecialty 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationSpecialty(null)));
       // 测试 educationHonor 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationHonor(null)));
       // 测试 educationStarttime 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationStarttime(null)));
       // 测试 educationEndtime 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEducationEndtime(null)));
       // 测试 employeeId 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setEmployeeId(null)));
       // 测试 createTime 不匹配
       employeeEducationMapper.insert(cloneIgnoreId(dbEmployeeEducation, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeEducationExportReqVO reqVO = new EmployeeEducationExportReqVO();
       reqVO.setEducationSchool(null);
       reqVO.setEducationSpecialty(null);
       reqVO.setEducationHonor(null);
       reqVO.setEducationStarttime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEducationEndtime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEmployeeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<EmployeeEducationDO> list = employeeEducationService.getEmployeeEducationList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmployeeEducation, list.get(0));
    }

}
