package com.dofast.module.hr.service.salarydetail;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.hr.controller.admin.salarydetail.vo.SalarydetailCreateReqVO;
import com.dofast.module.hr.controller.admin.salarydetail.vo.SalarydetailExportReqVO;
import com.dofast.module.hr.controller.admin.salarydetail.vo.SalarydetailPageReqVO;
import com.dofast.module.hr.controller.admin.salarydetail.vo.SalarydetailUpdateReqVO;
import com.dofast.module.hr.dal.dataobject.salarydetail.SalarydetailDO;
import com.dofast.module.hr.dal.mysql.salarydetail.SalarydetailMapper;
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
import static com.dofast.module.hr.enums.ErrorCodeConstants.SALARYDETAIL_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SalarydetailServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(SalarydetailServiceImpl.class)
public class SalarydetailServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SalarydetailServiceImpl salarydetailService;

    @Resource
    private SalarydetailMapper salarydetailMapper;

    @Test
    public void testCreateSalarydetail_success() {
        // 准备参数
        SalarydetailCreateReqVO reqVO = randomPojo(SalarydetailCreateReqVO.class);

        // 调用
        Integer salarydetailId = salarydetailService.createSalarydetail(reqVO);
        // 断言
        assertNotNull(salarydetailId);
        // 校验记录的属性是否正确
        SalarydetailDO salarydetail = salarydetailMapper.selectById(salarydetailId);
        assertPojoEquals(reqVO, salarydetail);
    }

    @Test
    public void testUpdateSalarydetail_success() {
        // mock 数据
        SalarydetailDO dbSalarydetail = randomPojo(SalarydetailDO.class);
        salarydetailMapper.insert(dbSalarydetail);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SalarydetailUpdateReqVO reqVO = randomPojo(SalarydetailUpdateReqVO.class, o -> {
            o.setId(dbSalarydetail.getId()); // 设置更新的 ID
        });

        // 调用
        salarydetailService.updateSalarydetail(reqVO);
        // 校验是否更新正确
        SalarydetailDO salarydetail = salarydetailMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, salarydetail);
    }

    @Test
    public void testUpdateSalarydetail_notExists() {
        // 准备参数
        SalarydetailUpdateReqVO reqVO = randomPojo(SalarydetailUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> salarydetailService.updateSalarydetail(reqVO), SALARYDETAIL_NOT_EXISTS);
    }

    @Test
    public void testDeleteSalarydetail_success() {
        // mock 数据
        SalarydetailDO dbSalarydetail = randomPojo(SalarydetailDO.class);
        salarydetailMapper.insert(dbSalarydetail);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbSalarydetail.getId();

        // 调用
        salarydetailService.deleteSalarydetail(id);
       // 校验数据不存在了
       assertNull(salarydetailMapper.selectById(id));
    }

    @Test
    public void testDeleteSalarydetail_notExists() {
        // 准备参数
//        Integer id = randomIntegerId();
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> salarydetailService.deleteSalarydetail(id), SALARYDETAIL_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalarydetailPage() {
       // mock 数据
       SalarydetailDO dbSalarydetail = randomPojo(SalarydetailDO.class, o -> { // 等会查询到
           o.setSalary(null);
           o.setItem(null);
           o.setType(null);
           o.setAmount(null);
           o.setDesc(null);
           o.setCreateTime(null);
       });
       salarydetailMapper.insert(dbSalarydetail);
       // 测试 salary 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setSalary(null)));
       // 测试 item 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setItem(null)));
       // 测试 type 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setType(null)));
       // 测试 amount 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setAmount(null)));
       // 测试 desc 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setDesc(null)));
       // 测试 createTime 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setCreateTime(null)));
       // 准备参数
       SalarydetailPageReqVO reqVO = new SalarydetailPageReqVO();
       reqVO.setSalary(null);
       reqVO.setItem(null);
       reqVO.setType(null);
       reqVO.setAmount(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<SalarydetailDO> pageResult = salarydetailService.getSalarydetailPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSalarydetail, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSalarydetailList() {
       // mock 数据
       SalarydetailDO dbSalarydetail = randomPojo(SalarydetailDO.class, o -> { // 等会查询到
           o.setSalary(null);
           o.setItem(null);
           o.setType(null);
           o.setAmount(null);
           o.setDesc(null);
           o.setCreateTime(null);
       });
       salarydetailMapper.insert(dbSalarydetail);
       // 测试 salary 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setSalary(null)));
       // 测试 item 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setItem(null)));
       // 测试 type 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setType(null)));
       // 测试 amount 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setAmount(null)));
       // 测试 desc 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setDesc(null)));
       // 测试 createTime 不匹配
       salarydetailMapper.insert(cloneIgnoreId(dbSalarydetail, o -> o.setCreateTime(null)));
       // 准备参数
       SalarydetailExportReqVO reqVO = new SalarydetailExportReqVO();
       reqVO.setSalary(null);
       reqVO.setItem(null);
       reqVO.setType(null);
       reqVO.setAmount(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<SalarydetailDO> list = salarydetailService.getSalarydetailList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSalarydetail, list.get(0));
    }

}
