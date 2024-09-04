package com.dofast.module.finance.service.cash;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.finance.controller.admin.cash.vo.CashBalanceCreateReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashBalanceExportReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashBalancePageReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashBalanceUpdateReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashBalanceDO;
import com.dofast.module.finance.dal.mysql.cash.CashBalanceMapper;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomLongId;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_BALANCE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link CashBalanceServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CashBalanceServiceImpl.class)
public class CashBalanceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CashBalanceServiceImpl cashBalanceService;

    @Resource
    private CashBalanceMapper cashBalanceMapper;

    @Test
    public void testCreateCashBalance_success() {
        // 准备参数
        CashBalanceCreateReqVO reqVO = randomPojo(CashBalanceCreateReqVO.class);

        // 调用
        Long cashBalanceId = cashBalanceService.createCashBalance(reqVO);
        // 断言
        assertNotNull(cashBalanceId);
        // 校验记录的属性是否正确
        CashBalanceDO cashBalance = cashBalanceMapper.selectById(cashBalanceId);
        assertPojoEquals(reqVO, cashBalance);
    }

    @Test
    public void testUpdateCashBalance_success() {
        // mock 数据
        CashBalanceDO dbCashBalance = randomPojo(CashBalanceDO.class);
        cashBalanceMapper.insert(dbCashBalance);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CashBalanceUpdateReqVO reqVO = randomPojo(CashBalanceUpdateReqVO.class, o -> {
            o.setId(dbCashBalance.getId()); // 设置更新的 ID
        });

        // 调用
        cashBalanceService.updateCashBalance(reqVO);
        // 校验是否更新正确
        CashBalanceDO cashBalance = cashBalanceMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cashBalance);
    }

    @Test
    public void testUpdateCashBalance_notExists() {
        // 准备参数
        CashBalanceUpdateReqVO reqVO = randomPojo(CashBalanceUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cashBalanceService.updateCashBalance(reqVO), CASH_BALANCE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCashBalance_success() {
        // mock 数据
        CashBalanceDO dbCashBalance = randomPojo(CashBalanceDO.class);
        cashBalanceMapper.insert(dbCashBalance);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCashBalance.getId();

        // 调用
        cashBalanceService.deleteCashBalance(id);
       // 校验数据不存在了
       assertNull(cashBalanceMapper.selectById(id));
    }

    @Test
    public void testDeleteCashBalance_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cashBalanceService.deleteCashBalance(id), CASH_BALANCE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashBalancePage() {
       // mock 数据
       CashBalanceDO dbCashBalance = randomPojo(CashBalanceDO.class, o -> { // 等会查询到
           o.setDepositor(null);
           o.setDate(null);
           o.setMoney(null);
           o.setCreateTime(null);
       });
       cashBalanceMapper.insert(dbCashBalance);
       // 测试 depositor 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setDepositor(null)));
       // 测试 date 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setDate(null)));
       // 测试 money 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setMoney(null)));
       // 测试 createTime 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setCreateTime(null)));
       // 准备参数
       CashBalancePageReqVO reqVO = new CashBalancePageReqVO();
       reqVO.setDepositor(null);
       reqVO.setDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
//       reqVO.setMoney(randomBigDecimal());
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CashBalanceDO> pageResult = cashBalanceService.getCashBalancePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCashBalance, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashBalanceList() {
       // mock 数据
       CashBalanceDO dbCashBalance = randomPojo(CashBalanceDO.class, o -> { // 等会查询到
           o.setDepositor(null);
           o.setDate(null);
           o.setMoney(null);
           o.setCreateTime(null);
       });
       cashBalanceMapper.insert(dbCashBalance);
       // 测试 depositor 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setDepositor(null)));
       // 测试 date 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setDate(null)));
       // 测试 money 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setMoney(null)));
       // 测试 createTime 不匹配
       cashBalanceMapper.insert(cloneIgnoreId(dbCashBalance, o -> o.setCreateTime(null)));
       // 准备参数
       CashBalanceExportReqVO reqVO = new CashBalanceExportReqVO();
       reqVO.setDepositor(null);
       reqVO.setDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
//       reqVO.setMoney(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CashBalanceDO> list = cashBalanceService.getCashBalanceList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCashBalance, list.get(0));
    }

}
