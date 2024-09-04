package com.dofast.module.hr.service.salarycommission;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.hr.controller.admin.salarycommission.vo.SalarycommissionCreateReqVO;
import com.dofast.module.hr.controller.admin.salarycommission.vo.SalarycommissionExportReqVO;
import com.dofast.module.hr.controller.admin.salarycommission.vo.SalarycommissionPageReqVO;
import com.dofast.module.hr.controller.admin.salarycommission.vo.SalarycommissionUpdateReqVO;
import com.dofast.module.hr.dal.dataobject.salarycommission.SalarycommissionDO;
import com.dofast.module.hr.dal.mysql.salarycommission.SalarycommissionMapper;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomInteger;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.module.hr.enums.ErrorCodeConstants.SALARYCOMMISSION_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SalarycommissionServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(SalarycommissionServiceImpl.class)
public class SalarycommissionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SalarycommissionServiceImpl salarycommissionService;

    @Resource
    private SalarycommissionMapper salarycommissionMapper;

    @Test
    public void testCreateSalarycommission_success() {
        // 准备参数
        SalarycommissionCreateReqVO reqVO = randomPojo(SalarycommissionCreateReqVO.class);

        // 调用
        Integer salarycommissionId = salarycommissionService.createSalarycommission(reqVO);
        // 断言
        assertNotNull(salarycommissionId);
        // 校验记录的属性是否正确
        SalarycommissionDO salarycommission = salarycommissionMapper.selectById(salarycommissionId);
        assertPojoEquals(reqVO, salarycommission);
    }

    @Test
    public void testUpdateSalarycommission_success() {
        // mock 数据
        SalarycommissionDO dbSalarycommission = randomPojo(SalarycommissionDO.class);
        salarycommissionMapper.insert(dbSalarycommission);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SalarycommissionUpdateReqVO reqVO = randomPojo(SalarycommissionUpdateReqVO.class, o -> {
            o.setId(dbSalarycommission.getId()); // 设置更新的 ID
        });

        // 调用
        salarycommissionService.updateSalarycommission(reqVO);
        // 校验是否更新正确
        SalarycommissionDO salarycommission = salarycommissionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, salarycommission);
    }

    @Test
    public void testUpdateSalarycommission_notExists() {
        // 准备参数
        SalarycommissionUpdateReqVO reqVO = randomPojo(SalarycommissionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> salarycommissionService.updateSalarycommission(reqVO), SALARYCOMMISSION_NOT_EXISTS);
    }

    @Test
    public void testDeleteSalarycommission_success() {
        // mock 数据
        SalarycommissionDO dbSalarycommission = randomPojo(SalarycommissionDO.class);
        salarycommissionMapper.insert(dbSalarycommission);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbSalarycommission.getId();

        // 调用
        salarycommissionService.deleteSalarycommission(id);
       // 校验数据不存在了
       assertNull(salarycommissionMapper.selectById(id));
    }

    @Test
    public void testDeleteSalarycommission_notExists() {
        // 准备参数
//        Integer id = randomIntegerId();
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> salarycommissionService.deleteSalarycommission(id), SALARYCOMMISSION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalarycommissionPage() {
       // mock 数据
       SalarycommissionDO dbSalarycommission = randomPojo(SalarycommissionDO.class, o -> { // 等会查询到
           o.setSalary(null);
           o.setType(null);
           o.setLine(null);
           o.setAmount(null);
           o.setRate(null);
           o.setCommission(null);
           o.setDesc(null);
           o.setCreateTime(null);
       });
       salarycommissionMapper.insert(dbSalarycommission);
       // 测试 salary 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setSalary(null)));
       // 测试 type 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setType(null)));
       // 测试 line 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setLine(null)));
       // 测试 amount 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setAmount(null)));
       // 测试 rate 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setRate(null)));
       // 测试 commission 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setCommission(null)));
       // 测试 desc 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setDesc(null)));
       // 测试 createTime 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setCreateTime(null)));
       // 准备参数
       SalarycommissionPageReqVO reqVO = new SalarycommissionPageReqVO();
       reqVO.setSalary(null);
       reqVO.setType(null);
       reqVO.setLine(null);
       reqVO.setAmount(null);
       reqVO.setRate(null);
       reqVO.setCommission(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SalarycommissionDO> pageResult = salarycommissionService.getSalarycommissionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSalarycommission, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalarycommissionList() {
       // mock 数据
       SalarycommissionDO dbSalarycommission = randomPojo(SalarycommissionDO.class, o -> { // 等会查询到
           o.setSalary(null);
           o.setType(null);
           o.setLine(null);
           o.setAmount(null);
           o.setRate(null);
           o.setCommission(null);
           o.setDesc(null);
           o.setCreateTime(null);
       });
       salarycommissionMapper.insert(dbSalarycommission);
       // 测试 salary 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setSalary(null)));
       // 测试 type 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setType(null)));
       // 测试 line 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setLine(null)));
       // 测试 amount 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setAmount(null)));
       // 测试 rate 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setRate(null)));
       // 测试 commission 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setCommission(null)));
       // 测试 desc 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setDesc(null)));
       // 测试 createTime 不匹配
       salarycommissionMapper.insert(cloneIgnoreId(dbSalarycommission, o -> o.setCreateTime(null)));
       // 准备参数
       SalarycommissionExportReqVO reqVO = new SalarycommissionExportReqVO();
       reqVO.setSalary(null);
       reqVO.setType(null);
       reqVO.setLine(null);
       reqVO.setAmount(null);
       reqVO.setRate(null);
       reqVO.setCommission(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<SalarycommissionDO> list = salarycommissionService.getSalarycommissionList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSalarycommission, list.get(0));
    }

}
