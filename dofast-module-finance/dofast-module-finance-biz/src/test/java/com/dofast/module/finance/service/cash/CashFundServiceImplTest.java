package com.dofast.module.finance.service.cash;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.module.finance.dal.mysql.cash.CashFundMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link CashFundServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CashFundServiceImpl.class)
public class CashFundServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CashFundServiceImpl cashFundService;

    @Resource
    private CashFundMapper cashFundMapper;

    @Test
    public void testCreateCashFund_success() {
        // 准备参数
        CashFundCreateReqVO reqVO = randomPojo(CashFundCreateReqVO.class);

        // 调用
        Long cashFundId = cashFundService.createCashFund(reqVO);
        // 断言
        assertNotNull(cashFundId);
        // 校验记录的属性是否正确
        CashFundDO cashFund = cashFundMapper.selectById(cashFundId);
        assertPojoEquals(reqVO, cashFund);
    }

    @Test
    public void testUpdateCashFund_success() {
        // mock 数据
        CashFundDO dbCashFund = randomPojo(CashFundDO.class);
        cashFundMapper.insert(dbCashFund);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CashFundUpdateReqVO reqVO = randomPojo(CashFundUpdateReqVO.class, o -> {
            o.setId(dbCashFund.getId()); // 设置更新的 ID
        });

        // 调用
        cashFundService.updateCashFund(reqVO);
        // 校验是否更新正确
        CashFundDO cashFund = cashFundMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cashFund);
    }

    @Test
    public void testUpdateCashFund_notExists() {
        // 准备参数
        CashFundUpdateReqVO reqVO = randomPojo(CashFundUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cashFundService.updateCashFund(reqVO), CASH_FUND_NOT_EXISTS);
    }

    @Test
    public void testDeleteCashFund_success() {
        // mock 数据
        CashFundDO dbCashFund = randomPojo(CashFundDO.class);
        cashFundMapper.insert(dbCashFund);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCashFund.getId();

        // 调用
        cashFundService.deleteCashFund(id);
       // 校验数据不存在了
       assertNull(cashFundMapper.selectById(id));
    }

    @Test
    public void testDeleteCashFund_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cashFundService.deleteCashFund(id), CASH_FUND_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashFundPage() {
       // mock 数据
       CashFundDO dbCashFund = randomPojo(CashFundDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setOrigin(null);
           o.setParent(null);
           o.setTrader(null);
           o.setContract(null);
           o.setOrder(null);
           o.setBatch(null);
           o.setDeserved(null);
           o.setActual(null);
           o.setBalance(null);
           o.setCustom(null);
           o.setDescription(null);
           o.setCreateTime(null);
       });
       cashFundMapper.insert(dbCashFund);
       // 测试 type 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setType(null)));
       // 测试 origin 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setOrigin(null)));
       // 测试 parent 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setParent(null)));
       // 测试 trader 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setTrader(null)));
       // 测试 contract 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setContract(null)));
       // 测试 order 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setOrder(null)));
       // 测试 batch 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setBatch(null)));
       // 测试 deserved 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setDeserved(null)));
       // 测试 actual 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setActual(null)));
       // 测试 balance 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setBalance(null)));
       // 测试 custom 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setCustom(null)));
       // 测试 desc 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setDescription(null)));
       // 测试 createTime 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setCreateTime(null)));
       // 准备参数
       CashFundPageReqVO reqVO = new CashFundPageReqVO();
       reqVO.setType(null);
       reqVO.setOrigin(null);
       reqVO.setParent(null);
       reqVO.setTrader(null);
       reqVO.setContract(null);
       reqVO.setOrder(null);
       reqVO.setBatch(null);
       reqVO.setDeserved(null);
       reqVO.setActual(null);
       reqVO.setBalance(null);
       reqVO.setCustom(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CashFundDO> pageResult = cashFundService.getCashFundPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCashFund, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashFundList() {
       // mock 数据
       CashFundDO dbCashFund = randomPojo(CashFundDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setOrigin(null);
           o.setParent(null);
           o.setTrader(null);
           o.setContract(null);
           o.setOrder(null);
           o.setBatch(null);
           o.setDeserved(null);
           o.setActual(null);
           o.setBalance(null);
           o.setCustom(null);
           o.setDescription(null);
           o.setCreateTime(null);
       });
       cashFundMapper.insert(dbCashFund);
       // 测试 type 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setType(null)));
       // 测试 origin 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setOrigin(null)));
       // 测试 parent 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setParent(null)));
       // 测试 trader 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setTrader(null)));
       // 测试 contract 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setContract(null)));
       // 测试 order 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setOrder(null)));
       // 测试 batch 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setBatch(null)));
       // 测试 deserved 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setDeserved(null)));
       // 测试 actual 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setActual(null)));
       // 测试 balance 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setBalance(null)));
       // 测试 custom 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setCustom(null)));
       // 测试 desc 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setDescription(null)));
       // 测试 createTime 不匹配
       cashFundMapper.insert(cloneIgnoreId(dbCashFund, o -> o.setCreateTime(null)));
       // 准备参数
       CashFundExportReqVO reqVO = new CashFundExportReqVO();
       reqVO.setType(null);
       reqVO.setOrigin(null);
       reqVO.setParent(null);
       reqVO.setTrader(null);
       reqVO.setContract(null);
       reqVO.setOrder(null);
       reqVO.setBatch(null);
       reqVO.setDeserved(null);
       reqVO.setActual(null);
       reqVO.setBalance(null);
       reqVO.setCustom(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CashFundDO> list = cashFundService.getCashFundList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCashFund, list.get(0));
    }

}
