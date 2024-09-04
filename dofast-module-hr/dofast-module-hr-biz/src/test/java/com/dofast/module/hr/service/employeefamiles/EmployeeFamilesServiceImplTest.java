package com.dofast.module.hr.service.employeefamiles;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.hr.controller.admin.employeefamiles.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefamiles.EmployeeFamilesDO;
import com.dofast.module.hr.dal.mysql.employeefamiles.EmployeeFamilesMapper;
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
 * {@link EmployeeFamilesServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(EmployeeFamilesServiceImpl.class)
public class EmployeeFamilesServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmployeeFamilesServiceImpl employeeFamilesService;

    @Resource
    private EmployeeFamilesMapper employeeFamilesMapper;

    @Test
    public void testCreateEmployeeFamiles_success() {
        // 准备参数
        EmployeeFamilesCreateReqVO reqVO = randomPojo(EmployeeFamilesCreateReqVO.class);

        // 调用
        Long employeeFamilesId = employeeFamilesService.createEmployeeFamiles(reqVO);
        // 断言
        assertNotNull(employeeFamilesId);
        // 校验记录的属性是否正确
        EmployeeFamilesDO employeeFamiles = employeeFamilesMapper.selectById(employeeFamilesId);
        assertPojoEquals(reqVO, employeeFamiles);
    }

    @Test
    public void testUpdateEmployeeFamiles_success() {
        // mock 数据
        EmployeeFamilesDO dbEmployeeFamiles = randomPojo(EmployeeFamilesDO.class);
        employeeFamilesMapper.insert(dbEmployeeFamiles);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmployeeFamilesUpdateReqVO reqVO = randomPojo(EmployeeFamilesUpdateReqVO.class, o -> {
            o.setId(dbEmployeeFamiles.getId()); // 设置更新的 ID
        });

        // 调用
        employeeFamilesService.updateEmployeeFamiles(reqVO);
        // 校验是否更新正确
        EmployeeFamilesDO employeeFamiles = employeeFamilesMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, employeeFamiles);
    }

    @Test
    public void testUpdateEmployeeFamiles_notExists() {
        // 准备参数
        EmployeeFamilesUpdateReqVO reqVO = randomPojo(EmployeeFamilesUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> employeeFamilesService.updateEmployeeFamiles(reqVO), EMPLOYEE_FAMILES_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmployeeFamiles_success() {
        // mock 数据
        EmployeeFamilesDO dbEmployeeFamiles = randomPojo(EmployeeFamilesDO.class);
        employeeFamilesMapper.insert(dbEmployeeFamiles);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmployeeFamiles.getId();

        // 调用
        employeeFamilesService.deleteEmployeeFamiles(id);
       // 校验数据不存在了
       assertNull(employeeFamilesMapper.selectById(id));
    }

    @Test
    public void testDeleteEmployeeFamiles_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> employeeFamilesService.deleteEmployeeFamiles(id), EMPLOYEE_FAMILES_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeFamilesPage() {
       // mock 数据
       EmployeeFamilesDO dbEmployeeFamiles = randomPojo(EmployeeFamilesDO.class, o -> { // 等会查询到
           o.setFamilesName(null);
           o.setFamilesRealtion(null);
           o.setFamilesWorkunit(null);
           o.setFamilesWorkplace(null);
           o.setFamilesPhone(null);
           o.setEmployeeId(null);
           o.setCreateTime(null);
       });
       employeeFamilesMapper.insert(dbEmployeeFamiles);
       // 测试 familesName 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesName(null)));
       // 测试 familesRealtion 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesRealtion(null)));
       // 测试 familesWorkunit 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesWorkunit(null)));
       // 测试 familesWorkplace 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesWorkplace(null)));
       // 测试 familesPhone 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesPhone(null)));
       // 测试 employeeId 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setEmployeeId(null)));
       // 测试 createTime 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeFamilesPageReqVO reqVO = new EmployeeFamilesPageReqVO();
       reqVO.setFamilesName(null);
       reqVO.setFamilesRealtion(null);
       reqVO.setFamilesWorkunit(null);
       reqVO.setFamilesWorkplace(null);
       reqVO.setFamilesPhone(null);
       reqVO.setEmployeeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<EmployeeFamilesDO> pageResult = employeeFamilesService.getEmployeeFamilesPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmployeeFamiles, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeFamilesList() {
       // mock 数据
       EmployeeFamilesDO dbEmployeeFamiles = randomPojo(EmployeeFamilesDO.class, o -> { // 等会查询到
           o.setFamilesName(null);
           o.setFamilesRealtion(null);
           o.setFamilesWorkunit(null);
           o.setFamilesWorkplace(null);
           o.setFamilesPhone(null);
           o.setEmployeeId(null);
           o.setCreateTime(null);
       });
       employeeFamilesMapper.insert(dbEmployeeFamiles);
       // 测试 familesName 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesName(null)));
       // 测试 familesRealtion 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesRealtion(null)));
       // 测试 familesWorkunit 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesWorkunit(null)));
       // 测试 familesWorkplace 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesWorkplace(null)));
       // 测试 familesPhone 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setFamilesPhone(null)));
       // 测试 employeeId 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setEmployeeId(null)));
       // 测试 createTime 不匹配
       employeeFamilesMapper.insert(cloneIgnoreId(dbEmployeeFamiles, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeFamilesExportReqVO reqVO = new EmployeeFamilesExportReqVO();
       reqVO.setFamilesName(null);
       reqVO.setFamilesRealtion(null);
       reqVO.setFamilesWorkunit(null);
       reqVO.setFamilesWorkplace(null);
       reqVO.setFamilesPhone(null);
       reqVO.setEmployeeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<EmployeeFamilesDO> list = employeeFamilesService.getEmployeeFamilesList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmployeeFamiles, list.get(0));
    }

}
