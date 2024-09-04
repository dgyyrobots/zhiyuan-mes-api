package com.dofast.module.hr.service.commissionrule;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.hr.controller.admin.commissionrule.vo.CommissionruleCreateReqVO;
import com.dofast.module.hr.controller.admin.commissionrule.vo.CommissionruleExportReqVO;
import com.dofast.module.hr.controller.admin.commissionrule.vo.CommissionrulePageReqVO;
import com.dofast.module.hr.controller.admin.commissionrule.vo.CommissionruleUpdateReqVO;
import com.dofast.module.hr.dal.dataobject.commissionrule.CommissionruleDO;
import com.dofast.module.hr.dal.mysql.commissionrule.CommissionruleMapper;
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
import static com.dofast.module.hr.enums.ErrorCodeConstants.COMMISSIONRULE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link CommissionruleServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CommissionruleServiceImpl.class)
public class CommissionruleServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CommissionruleServiceImpl commissionruleService;

    @Resource
    private CommissionruleMapper commissionruleMapper;

    @Test
    public void testCreateCommissionrule_success() {
        // 准备参数
        CommissionruleCreateReqVO reqVO = randomPojo(CommissionruleCreateReqVO.class);

        // 调用
        Integer commissionruleId = commissionruleService.createCommissionrule(reqVO);
        // 断言
        assertNotNull(commissionruleId);
        // 校验记录的属性是否正确
        CommissionruleDO commissionrule = commissionruleMapper.selectById(commissionruleId);
        assertPojoEquals(reqVO, commissionrule);
    }

    @Test
    public void testUpdateCommissionrule_success() {
        // mock 数据
        CommissionruleDO dbCommissionrule = randomPojo(CommissionruleDO.class);
        commissionruleMapper.insert(dbCommissionrule);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CommissionruleUpdateReqVO reqVO = randomPojo(CommissionruleUpdateReqVO.class, o -> {
            o.setId(dbCommissionrule.getId()); // 设置更新的 ID
        });

        // 调用
        commissionruleService.updateCommissionrule(reqVO);
        // 校验是否更新正确
        CommissionruleDO commissionrule = commissionruleMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, commissionrule);
    }

    @Test
    public void testUpdateCommissionrule_notExists() {
        // 准备参数
        CommissionruleUpdateReqVO reqVO = randomPojo(CommissionruleUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> commissionruleService.updateCommissionrule(reqVO), COMMISSIONRULE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCommissionrule_success() {
        // mock 数据
        CommissionruleDO dbCommissionrule = randomPojo(CommissionruleDO.class);
        commissionruleMapper.insert(dbCommissionrule);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbCommissionrule.getId();

        // 调用
        commissionruleService.deleteCommissionrule(id);
       // 校验数据不存在了
       assertNull(commissionruleMapper.selectById(id));
    }

    @Test
    public void testDeleteCommissionrule_notExists() {
        // 准备参数
//        Integer id = randomIntegerId();
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> commissionruleService.deleteCommissionrule(id), COMMISSIONRULE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCommissionrulePage() {
       // mock 数据
       CommissionruleDO dbCommissionrule = randomPojo(CommissionruleDO.class, o -> { // 等会查询到
           o.setMonth(null);
           o.setAccount(null);
           o.setSale(null);
           o.setLine(null);
           o.setCreateTime(null);
       });
       commissionruleMapper.insert(dbCommissionrule);
       // 测试 month 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setMonth(null)));
       // 测试 account 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setAccount(null)));
       // 测试 sale 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setSale(null)));
       // 测试 line 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setLine(null)));
       // 测试 createTime 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setCreateTime(null)));
       // 准备参数
       CommissionrulePageReqVO reqVO = new CommissionrulePageReqVO();
       reqVO.setMonth(null);
       reqVO.setAccount(null);
       reqVO.setSale(null);
       reqVO.setLine(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CommissionruleDO> pageResult = commissionruleService.getCommissionrulePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCommissionrule, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCommissionruleList() {
       // mock 数据
       CommissionruleDO dbCommissionrule = randomPojo(CommissionruleDO.class, o -> { // 等会查询到
           o.setMonth(null);
           o.setAccount(null);
           o.setSale(null);
           o.setLine(null);
           o.setCreateTime(null);
       });
       commissionruleMapper.insert(dbCommissionrule);
       // 测试 month 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setMonth(null)));
       // 测试 account 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setAccount(null)));
       // 测试 sale 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setSale(null)));
       // 测试 line 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setLine(null)));
       // 测试 createTime 不匹配
       commissionruleMapper.insert(cloneIgnoreId(dbCommissionrule, o -> o.setCreateTime(null)));
       // 准备参数
       CommissionruleExportReqVO reqVO = new CommissionruleExportReqVO();
       reqVO.setMonth(null);
       reqVO.setAccount(null);
       reqVO.setSale(null);
       reqVO.setLine(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CommissionruleDO> list = commissionruleService.getCommissionruleList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCommissionrule, list.get(0));
    }

}
