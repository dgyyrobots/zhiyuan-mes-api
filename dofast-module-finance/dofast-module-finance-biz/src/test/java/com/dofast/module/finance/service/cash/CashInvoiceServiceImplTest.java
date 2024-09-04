package com.dofast.module.finance.service.cash;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import com.dofast.module.finance.dal.mysql.cash.CashInvoiceMapper;
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
 * {@link CashInvoiceServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CashInvoiceServiceImpl.class)
public class CashInvoiceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CashInvoiceServiceImpl cashInvoiceService;

    @Resource
    private CashInvoiceMapper cashInvoiceMapper;

    @Test
    public void testCreateCashInvoice_success() {
        // 准备参数
        CashInvoiceCreateReqVO reqVO = randomPojo(CashInvoiceCreateReqVO.class);

        // 调用
        Long cashInvoiceId = cashInvoiceService.createCashInvoice(reqVO);
        // 断言
        assertNotNull(cashInvoiceId);
        // 校验记录的属性是否正确
        CashInvoiceDO cashInvoice = cashInvoiceMapper.selectById(cashInvoiceId);
        assertPojoEquals(reqVO, cashInvoice);
    }

    @Test
    public void testUpdateCashInvoice_success() {
        // mock 数据
        CashInvoiceDO dbCashInvoice = randomPojo(CashInvoiceDO.class);
        cashInvoiceMapper.insert(dbCashInvoice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CashInvoiceUpdateReqVO reqVO = randomPojo(CashInvoiceUpdateReqVO.class, o -> {
            o.setId(dbCashInvoice.getId()); // 设置更新的 ID
        });

        // 调用
        cashInvoiceService.updateCashInvoice(reqVO);
        // 校验是否更新正确
        CashInvoiceDO cashInvoice = cashInvoiceMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cashInvoice);
    }

    @Test
    public void testUpdateCashInvoice_notExists() {
        // 准备参数
        CashInvoiceUpdateReqVO reqVO = randomPojo(CashInvoiceUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cashInvoiceService.updateCashInvoice(reqVO), CASH_INVOICE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCashInvoice_success() {
        // mock 数据
        CashInvoiceDO dbCashInvoice = randomPojo(CashInvoiceDO.class);
        cashInvoiceMapper.insert(dbCashInvoice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCashInvoice.getId();

        // 调用
        cashInvoiceService.deleteCashInvoice(id);
       // 校验数据不存在了
       assertNull(cashInvoiceMapper.selectById(id));
    }

    @Test
    public void testDeleteCashInvoice_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cashInvoiceService.deleteCashInvoice(id), CASH_INVOICE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashInvoicePage() {
       // mock 数据
       CashInvoiceDO dbCashInvoice = randomPojo(CashInvoiceDO.class, o -> { // 等会查询到
           o.setSerialNumber(null);
           o.setSn(null);
           o.setCompany(null);
           o.setCustomer(null);
           o.setContract(null);
           o.setContact(null);
           o.setAddress(null);
           o.setMoney(null);
           o.setKind(null);
           o.setType(null);
           o.setSaleType(null);
           o.setTaxRate(null);
           o.setInvoiceTitle(null);
           o.setTaxNumber(null);
           o.setRegistedAddress(null);
           o.setPhone(null);
           o.setBankName(null);
           o.setBankAccount(null);
           o.setExpress(null);
           o.setWaybill(null);
           o.setSendway(null);
           o.setSendAccount(null);
           o.setStatus(null);
           o.setSubStatus(null);
           o.setDescription(null);
           o.setReceivedBy(null);
           o.setReceivedDate(null);
           o.setCheckedBy(null);
           o.setCheckedDate(null);
           o.setDrawnBy(null);
           o.setDrawnDate(null);
           o.setExpressedBy(null);
           o.setExpressedDate(null);
           o.setTaxRefundedBy(null);
           o.setTaxRefundedDate(null);
           o.setCreateTime(null);
       });
       cashInvoiceMapper.insert(dbCashInvoice);
       // 测试 serialNumber 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSerialNumber(null)));
       // 测试 sn 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSn(null)));
       // 测试 company 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCompany(null)));
       // 测试 customer 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCustomer(null)));
       // 测试 contract 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setContract(null)));
       // 测试 contact 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setContact(null)));
       // 测试 address 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setAddress(null)));
       // 测试 money 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setMoney(null)));
       // 测试 kind 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setKind(null)));
       // 测试 type 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setType(null)));
       // 测试 saleType 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSaleType(null)));
       // 测试 taxRate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxRate(null)));
       // 测试 invoiceTitle 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setInvoiceTitle(null)));
       // 测试 taxNumber 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxNumber(null)));
       // 测试 registedAddress 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setRegistedAddress(null)));
       // 测试 phone 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setPhone(null)));
       // 测试 bankName 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setBankName(null)));
       // 测试 bankAccount 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setBankAccount(null)));
       // 测试 express 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setExpress(null)));
       // 测试 waybill 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setWaybill(null)));
       // 测试 sendway 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSendway(null)));
       // 测试 sendAccount 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSendAccount(null)));
       // 测试 status 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setStatus(null)));
       // 测试 subStatus 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSubStatus(null)));
       // 测试 desc 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setDescription(null)));
       // 测试 receivedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setReceivedBy(null)));
       // 测试 receivedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setReceivedDate(null)));
       // 测试 checkedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCheckedBy(null)));
       // 测试 checkedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCheckedDate(null)));
       // 测试 drawnBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setDrawnBy(null)));
       // 测试 drawnDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setDrawnDate(null)));
       // 测试 expressedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setExpressedBy(null)));
       // 测试 expressedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setExpressedDate(null)));
       // 测试 taxRefundedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxRefundedBy(null)));
       // 测试 taxRefundedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxRefundedDate(null)));
       // 测试 createTime 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCreateTime(null)));
       // 准备参数
       CashInvoicePageReqVO reqVO = new CashInvoicePageReqVO();
       reqVO.setSerialNumber(null);
       reqVO.setSn(null);
       reqVO.setCompany(null);
       reqVO.setCustomer(null);
       reqVO.setContract(null);
       reqVO.setContact(null);
       reqVO.setAddress(null);
       reqVO.setMoney(null);
       reqVO.setKind(null);
       reqVO.setType(null);
       reqVO.setSaleType(null);
       reqVO.setTaxRate(null);
       reqVO.setInvoiceTitle(null);
       reqVO.setTaxNumber(null);
       reqVO.setRegistedAddress(null);
       reqVO.setPhone(null);
       reqVO.setBankName(null);
       reqVO.setBankAccount(null);
       reqVO.setExpress(null);
       reqVO.setWaybill(null);
       reqVO.setSendway(null);
       reqVO.setSendAccount(null);
       reqVO.setStatus(null);
       reqVO.setSubStatus(null);
       reqVO.setDescription(null);
       reqVO.setReceivedBy(null);
       reqVO.setReceivedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCheckedBy(null);
       reqVO.setCheckedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDrawnBy(null);
       reqVO.setDrawnDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setExpressedBy(null);
       reqVO.setExpressedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setTaxRefundedBy(null);
       reqVO.setTaxRefundedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CashInvoiceDO> pageResult = cashInvoiceService.getCashInvoicePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCashInvoice, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashInvoiceList() {
       // mock 数据
       CashInvoiceDO dbCashInvoice = randomPojo(CashInvoiceDO.class, o -> { // 等会查询到
           o.setSerialNumber(null);
           o.setSn(null);
           o.setCompany(null);
           o.setCustomer(null);
           o.setContract(null);
           o.setContact(null);
           o.setAddress(null);
           o.setMoney(null);
           o.setKind(null);
           o.setType(null);
           o.setSaleType(null);
           o.setTaxRate(null);
           o.setInvoiceTitle(null);
           o.setTaxNumber(null);
           o.setRegistedAddress(null);
           o.setPhone(null);
           o.setBankName(null);
           o.setBankAccount(null);
           o.setExpress(null);
           o.setWaybill(null);
           o.setSendway(null);
           o.setSendAccount(null);
           o.setStatus(null);
           o.setSubStatus(null);
           o.setDescription(null);
           o.setReceivedBy(null);
           o.setReceivedDate(null);
           o.setCheckedBy(null);
           o.setCheckedDate(null);
           o.setDrawnBy(null);
           o.setDrawnDate(null);
           o.setExpressedBy(null);
           o.setExpressedDate(null);
           o.setTaxRefundedBy(null);
           o.setTaxRefundedDate(null);
           o.setCreateTime(null);
       });
       cashInvoiceMapper.insert(dbCashInvoice);
       // 测试 serialNumber 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSerialNumber(null)));
       // 测试 sn 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSn(null)));
       // 测试 company 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCompany(null)));
       // 测试 customer 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCustomer(null)));
       // 测试 contract 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setContract(null)));
       // 测试 contact 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setContact(null)));
       // 测试 address 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setAddress(null)));
       // 测试 money 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setMoney(null)));
       // 测试 kind 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setKind(null)));
       // 测试 type 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setType(null)));
       // 测试 saleType 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSaleType(null)));
       // 测试 taxRate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxRate(null)));
       // 测试 invoiceTitle 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setInvoiceTitle(null)));
       // 测试 taxNumber 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxNumber(null)));
       // 测试 registedAddress 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setRegistedAddress(null)));
       // 测试 phone 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setPhone(null)));
       // 测试 bankName 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setBankName(null)));
       // 测试 bankAccount 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setBankAccount(null)));
       // 测试 express 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setExpress(null)));
       // 测试 waybill 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setWaybill(null)));
       // 测试 sendway 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSendway(null)));
       // 测试 sendAccount 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSendAccount(null)));
       // 测试 status 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setStatus(null)));
       // 测试 subStatus 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setSubStatus(null)));
       // 测试 desc 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setDescription(null)));
       // 测试 receivedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setReceivedBy(null)));
       // 测试 receivedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setReceivedDate(null)));
       // 测试 checkedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCheckedBy(null)));
       // 测试 checkedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCheckedDate(null)));
       // 测试 drawnBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setDrawnBy(null)));
       // 测试 drawnDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setDrawnDate(null)));
       // 测试 expressedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setExpressedBy(null)));
       // 测试 expressedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setExpressedDate(null)));
       // 测试 taxRefundedBy 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxRefundedBy(null)));
       // 测试 taxRefundedDate 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setTaxRefundedDate(null)));
       // 测试 createTime 不匹配
       cashInvoiceMapper.insert(cloneIgnoreId(dbCashInvoice, o -> o.setCreateTime(null)));
       // 准备参数
       CashInvoiceExportReqVO reqVO = new CashInvoiceExportReqVO();
       reqVO.setSerialNumber(null);
       reqVO.setSn(null);
       reqVO.setCompany(null);
       reqVO.setCustomer(null);
       reqVO.setContract(null);
       reqVO.setContact(null);
       reqVO.setAddress(null);
       reqVO.setMoney(null);
       reqVO.setKind(null);
       reqVO.setType(null);
       reqVO.setSaleType(null);
       reqVO.setTaxRate(null);
       reqVO.setInvoiceTitle(null);
       reqVO.setTaxNumber(null);
       reqVO.setRegistedAddress(null);
       reqVO.setPhone(null);
       reqVO.setBankName(null);
       reqVO.setBankAccount(null);
       reqVO.setExpress(null);
       reqVO.setWaybill(null);
       reqVO.setSendway(null);
       reqVO.setSendAccount(null);
       reqVO.setStatus(null);
       reqVO.setSubStatus(null);
       reqVO.setDescription(null);
       reqVO.setReceivedBy(null);
       reqVO.setReceivedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCheckedBy(null);
       reqVO.setCheckedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDrawnBy(null);
       reqVO.setDrawnDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setExpressedBy(null);
       reqVO.setExpressedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setTaxRefundedBy(null);
       reqVO.setTaxRefundedDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CashInvoiceDO> list = cashInvoiceService.getCashInvoiceList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCashInvoice, list.get(0));
    }

}
