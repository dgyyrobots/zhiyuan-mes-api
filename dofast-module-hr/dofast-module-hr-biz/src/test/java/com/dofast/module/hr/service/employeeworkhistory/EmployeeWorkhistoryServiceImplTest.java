package com.dofast.module.hr.service.employeeworkhistory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.hr.controller.admin.employeeworkhistory.vo.*;
import com.dofast.module.hr.dal.dataobject.employeeworkhistory.EmployeeWorkhistoryDO;
import com.dofast.module.hr.dal.mysql.employeeworkhistory.EmployeeWorkhistoryMapper;
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
 * {@link EmployeeWorkhistoryServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(EmployeeWorkhistoryServiceImpl.class)
public class EmployeeWorkhistoryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmployeeWorkhistoryServiceImpl employeeWorkhistoryService;

    @Resource
    private EmployeeWorkhistoryMapper employeeWorkhistoryMapper;

    @Test
    public void testCreateEmployeeWorkhistory_success() {
        // 准备参数
        EmployeeWorkhistoryCreateReqVO reqVO = randomPojo(EmployeeWorkhistoryCreateReqVO.class);

        // 调用
        Long employeeWorkhistoryId = employeeWorkhistoryService.createEmployeeWorkhistory(reqVO);
        // 断言
        assertNotNull(employeeWorkhistoryId);
        // 校验记录的属性是否正确
        EmployeeWorkhistoryDO employeeWorkhistory = employeeWorkhistoryMapper.selectById(employeeWorkhistoryId);
        assertPojoEquals(reqVO, employeeWorkhistory);
    }

    @Test
    public void testUpdateEmployeeWorkhistory_success() {
        // mock 数据
        EmployeeWorkhistoryDO dbEmployeeWorkhistory = randomPojo(EmployeeWorkhistoryDO.class);
        employeeWorkhistoryMapper.insert(dbEmployeeWorkhistory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmployeeWorkhistoryUpdateReqVO reqVO = randomPojo(EmployeeWorkhistoryUpdateReqVO.class, o -> {
            o.setId(dbEmployeeWorkhistory.getId()); // 设置更新的 ID
        });

        // 调用
        employeeWorkhistoryService.updateEmployeeWorkhistory(reqVO);
        // 校验是否更新正确
        EmployeeWorkhistoryDO employeeWorkhistory = employeeWorkhistoryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, employeeWorkhistory);
    }

    @Test
    public void testUpdateEmployeeWorkhistory_notExists() {
        // 准备参数
        EmployeeWorkhistoryUpdateReqVO reqVO = randomPojo(EmployeeWorkhistoryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> employeeWorkhistoryService.updateEmployeeWorkhistory(reqVO), EMPLOYEE_WORKHISTORY_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmployeeWorkhistory_success() {
        // mock 数据
        EmployeeWorkhistoryDO dbEmployeeWorkhistory = randomPojo(EmployeeWorkhistoryDO.class);
        employeeWorkhistoryMapper.insert(dbEmployeeWorkhistory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmployeeWorkhistory.getId();

        // 调用
        employeeWorkhistoryService.deleteEmployeeWorkhistory(id);
       // 校验数据不存在了
       assertNull(employeeWorkhistoryMapper.selectById(id));
    }

    @Test
    public void testDeleteEmployeeWorkhistory_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> employeeWorkhistoryService.deleteEmployeeWorkhistory(id), EMPLOYEE_WORKHISTORY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeWorkhistoryPage() {
       // mock 数据
       EmployeeWorkhistoryDO dbEmployeeWorkhistory = randomPojo(EmployeeWorkhistoryDO.class, o -> { // 等会查询到
           o.setCompanyName(null);
           o.setTreatment(null);
           o.setTreatmentPost(null);
           o.setReasonForLeave(null);
           o.setEmployeeId(null);
           o.setCreateTime(null);
       });
       employeeWorkhistoryMapper.insert(dbEmployeeWorkhistory);
       // 测试 companyName 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setCompanyName(null)));
       // 测试 treatment 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setTreatment(null)));
       // 测试 treatmentPost 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setTreatmentPost(null)));
       // 测试 reasonForLeave 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setReasonForLeave(null)));
       // 测试 employeeId 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setEmployeeId(null)));
       // 测试 createTime 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeWorkhistoryPageReqVO reqVO = new EmployeeWorkhistoryPageReqVO();
       reqVO.setCompanyName(null);
       reqVO.setTreatment(null);
       reqVO.setTreatmentPost(null);
       reqVO.setReasonForLeave(null);
       reqVO.setEmployeeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<EmployeeWorkhistoryDO> pageResult = employeeWorkhistoryService.getEmployeeWorkhistoryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmployeeWorkhistory, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeWorkhistoryList() {
       // mock 数据
       EmployeeWorkhistoryDO dbEmployeeWorkhistory = randomPojo(EmployeeWorkhistoryDO.class, o -> { // 等会查询到
           o.setCompanyName(null);
           o.setTreatment(null);
           o.setTreatmentPost(null);
           o.setReasonForLeave(null);
           o.setEmployeeId(null);
           o.setCreateTime(null);
       });
       employeeWorkhistoryMapper.insert(dbEmployeeWorkhistory);
       // 测试 companyName 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setCompanyName(null)));
       // 测试 treatment 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setTreatment(null)));
       // 测试 treatmentPost 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setTreatmentPost(null)));
       // 测试 reasonForLeave 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setReasonForLeave(null)));
       // 测试 employeeId 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setEmployeeId(null)));
       // 测试 createTime 不匹配
       employeeWorkhistoryMapper.insert(cloneIgnoreId(dbEmployeeWorkhistory, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeWorkhistoryExportReqVO reqVO = new EmployeeWorkhistoryExportReqVO();
       reqVO.setCompanyName(null);
       reqVO.setTreatment(null);
       reqVO.setTreatmentPost(null);
       reqVO.setReasonForLeave(null);
       reqVO.setEmployeeId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<EmployeeWorkhistoryDO> list = employeeWorkhistoryService.getEmployeeWorkhistoryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmployeeWorkhistory, list.get(0));
    }

}
