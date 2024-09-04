package com.dofast.module.finance.service.cash;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.finance.controller.admin.cash.vo.CashTradeInvoiceCreateReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashTradeInvoiceExportReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashTradeInvoicePageReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashTradeInvoiceUpdateReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeInvoiceDO;
import com.dofast.module.finance.dal.mysql.cash.CashTradeInvoiceMapper;
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
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_TRADE_INVOICE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link CashTradeInvoiceServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CashTradeInvoiceServiceImpl.class)
public class CashTradeInvoiceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CashTradeInvoiceServiceImpl cashTradeInvoiceService;

    @Resource
    private CashTradeInvoiceMapper cashTradeInvoiceMapper;

    @Test
    public void testCreateCashTradeInvoice_success() {
        // 准备参数
        CashTradeInvoiceCreateReqVO reqVO = randomPojo(CashTradeInvoiceCreateReqVO.class);

        // 调用
        Long cashTradeInvoiceId = cashTradeInvoiceService.createCashTradeInvoice(reqVO);
        // 断言
        assertNotNull(cashTradeInvoiceId);
        // 校验记录的属性是否正确
        CashTradeInvoiceDO cashTradeInvoice = cashTradeInvoiceMapper.selectById(cashTradeInvoiceId);
        assertPojoEquals(reqVO, cashTradeInvoice);
    }

    @Test
    public void testUpdateCashTradeInvoice_success() {
        // mock 数据
        CashTradeInvoiceDO dbCashTradeInvoice = randomPojo(CashTradeInvoiceDO.class);
        cashTradeInvoiceMapper.insert(dbCashTradeInvoice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CashTradeInvoiceUpdateReqVO reqVO = randomPojo(CashTradeInvoiceUpdateReqVO.class, o -> {
//            o.setId(dbCashTradeInvoice.getId()); // 设置更新的 ID
        });

        // 调用
        cashTradeInvoiceService.updateCashTradeInvoice(reqVO);
        // 校验是否更新正确
//        CashTradeInvoiceDO cashTradeInvoice = cashTradeInvoiceMapper.selectById(reqVO.getId()); // 获取最新的
        CashTradeInvoiceDO cashTradeInvoice = cashTradeInvoiceMapper.selectById(1); // 获取最新的
        assertPojoEquals(reqVO, cashTradeInvoice);
    }

    @Test
    public void testUpdateCashTradeInvoice_notExists() {
        // 准备参数
        CashTradeInvoiceUpdateReqVO reqVO = randomPojo(CashTradeInvoiceUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cashTradeInvoiceService.updateCashTradeInvoice(reqVO), CASH_TRADE_INVOICE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCashTradeInvoice_success() {
        // mock 数据
        CashTradeInvoiceDO dbCashTradeInvoice = randomPojo(CashTradeInvoiceDO.class);
        cashTradeInvoiceMapper.insert(dbCashTradeInvoice);// @Sql: 先插入出一条存在的数据
        // 准备参数
//        Long id = dbCashTradeInvoice.getId();
        Long id = Long.valueOf(1);

        // 调用
        cashTradeInvoiceService.deleteCashTradeInvoice(id);
       // 校验数据不存在了
       assertNull(cashTradeInvoiceMapper.selectById(id));
    }

    @Test
    public void testDeleteCashTradeInvoice_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cashTradeInvoiceService.deleteCashTradeInvoice(id), CASH_TRADE_INVOICE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashTradeInvoicePage() {
       // mock 数据
       CashTradeInvoiceDO dbCashTradeInvoice = randomPojo(CashTradeInvoiceDO.class, o -> { // 等会查询到
           o.setTrade(null);
           o.setInvoice(null);
           o.setCreateTime(null);
       });
       cashTradeInvoiceMapper.insert(dbCashTradeInvoice);
       // 测试 trade 不匹配
       cashTradeInvoiceMapper.insert(cloneIgnoreId(dbCashTradeInvoice, o -> o.setTrade(null)));
       // 测试 invoice 不匹配
       cashTradeInvoiceMapper.insert(cloneIgnoreId(dbCashTradeInvoice, o -> o.setInvoice(null)));
       // 测试 createTime 不匹配
       cashTradeInvoiceMapper.insert(cloneIgnoreId(dbCashTradeInvoice, o -> o.setCreateTime(null)));
       // 准备参数
       CashTradeInvoicePageReqVO reqVO = new CashTradeInvoicePageReqVO();
       reqVO.setTrade(null);
       reqVO.setInvoice(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CashTradeInvoiceDO> pageResult = cashTradeInvoiceService.getCashTradeInvoicePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCashTradeInvoice, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashTradeInvoiceList() {
       // mock 数据
       CashTradeInvoiceDO dbCashTradeInvoice = randomPojo(CashTradeInvoiceDO.class, o -> { // 等会查询到
           o.setTrade(null);
           o.setInvoice(null);
           o.setCreateTime(null);
       });
       cashTradeInvoiceMapper.insert(dbCashTradeInvoice);
       // 测试 trade 不匹配
       cashTradeInvoiceMapper.insert(cloneIgnoreId(dbCashTradeInvoice, o -> o.setTrade(null)));
       // 测试 invoice 不匹配
       cashTradeInvoiceMapper.insert(cloneIgnoreId(dbCashTradeInvoice, o -> o.setInvoice(null)));
       // 测试 createTime 不匹配
       cashTradeInvoiceMapper.insert(cloneIgnoreId(dbCashTradeInvoice, o -> o.setCreateTime(null)));
       // 准备参数
       CashTradeInvoiceExportReqVO reqVO = new CashTradeInvoiceExportReqVO();
       reqVO.setTrade(null);
       reqVO.setInvoice(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CashTradeInvoiceDO> list = cashTradeInvoiceService.getCashTradeInvoiceList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCashTradeInvoice, list.get(0));
    }

}
