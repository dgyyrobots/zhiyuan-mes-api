package com.dofast.module.finance.service.cash;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import com.dofast.module.finance.dal.mysql.cash.CashInvoiceDetailMapper;
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
 * {@link CashInvoiceDetailServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CashInvoiceDetailServiceImpl.class)
public class CashInvoiceDetailServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CashInvoiceDetailServiceImpl cashInvoiceDetailService;

    @Resource
    private CashInvoiceDetailMapper cashInvoiceDetailMapper;

    @Test
    public void testCreateCashInvoiceDetail_success() {
        // 准备参数
        CashInvoiceDetailCreateReqVO reqVO = randomPojo(CashInvoiceDetailCreateReqVO.class);

        // 调用
        Long cashInvoiceDetailId = cashInvoiceDetailService.createCashInvoiceDetail(reqVO);
        // 断言
        assertNotNull(cashInvoiceDetailId);
        // 校验记录的属性是否正确
        CashInvoiceDetailDO cashInvoiceDetail = cashInvoiceDetailMapper.selectById(cashInvoiceDetailId);
        assertPojoEquals(reqVO, cashInvoiceDetail);
    }

    @Test
    public void testUpdateCashInvoiceDetail_success() {
        // mock 数据
        CashInvoiceDetailDO dbCashInvoiceDetail = randomPojo(CashInvoiceDetailDO.class);
        cashInvoiceDetailMapper.insert(dbCashInvoiceDetail);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CashInvoiceDetailUpdateReqVO reqVO = randomPojo(CashInvoiceDetailUpdateReqVO.class, o -> {
            o.setId(dbCashInvoiceDetail.getId()); // 设置更新的 ID
        });

        // 调用
        cashInvoiceDetailService.updateCashInvoiceDetail(reqVO);
        // 校验是否更新正确
        CashInvoiceDetailDO cashInvoiceDetail = cashInvoiceDetailMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cashInvoiceDetail);
    }

    @Test
    public void testUpdateCashInvoiceDetail_notExists() {
        // 准备参数
        CashInvoiceDetailUpdateReqVO reqVO = randomPojo(CashInvoiceDetailUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cashInvoiceDetailService.updateCashInvoiceDetail(reqVO), CASH_INVOICE_DETAIL_NOT_EXISTS);
    }

    @Test
    public void testDeleteCashInvoiceDetail_success() {
        // mock 数据
        CashInvoiceDetailDO dbCashInvoiceDetail = randomPojo(CashInvoiceDetailDO.class);
        cashInvoiceDetailMapper.insert(dbCashInvoiceDetail);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCashInvoiceDetail.getId();

        // 调用
        cashInvoiceDetailService.deleteCashInvoiceDetail(id);
       // 校验数据不存在了
       assertNull(cashInvoiceDetailMapper.selectById(id));
    }

    @Test
    public void testDeleteCashInvoiceDetail_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cashInvoiceDetailService.deleteCashInvoiceDetail(id), CASH_INVOICE_DETAIL_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashInvoiceDetailPage() {
       // mock 数据
       CashInvoiceDetailDO dbCashInvoiceDetail = randomPojo(CashInvoiceDetailDO.class, o -> { // 等会查询到
           o.setInvoice(null);
           o.setItem(null);
           o.setItemType(null);
           o.setItemId(null);
           o.setModel(null);
           o.setUnit(null);
           o.setAmount(null);
           o.setPrice(null);
           o.setMoney(null);
           o.setTaxRate(null);
           o.setTaxMoney(null);
           o.setCreateTime(null);
       });
       cashInvoiceDetailMapper.insert(dbCashInvoiceDetail);
       // 测试 invoice 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setInvoice(null)));
       // 测试 item 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setItem(null)));
       // 测试 itemType 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setItemType(null)));
       // 测试 itemId 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setItemId(null)));
       // 测试 model 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setModel(null)));
       // 测试 unit 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setUnit(null)));
       // 测试 amount 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setAmount(null)));
       // 测试 price 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setPrice(null)));
       // 测试 money 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setMoney(null)));
       // 测试 taxRate 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setTaxRate(null)));
       // 测试 taxMoney 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setTaxMoney(null)));
       // 测试 createTime 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setCreateTime(null)));
       // 准备参数
       CashInvoiceDetailPageReqVO reqVO = new CashInvoiceDetailPageReqVO();
       reqVO.setInvoice(null);
       reqVO.setItem(null);
       reqVO.setItemType(null);
       reqVO.setItemId(null);
       reqVO.setModel(null);
       reqVO.setUnit(null);
       reqVO.setAmount(null);
       reqVO.setPrice(null);
       reqVO.setMoney(null);
       reqVO.setTaxRate(null);
       reqVO.setTaxMoney(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CashInvoiceDetailDO> pageResult = cashInvoiceDetailService.getCashInvoiceDetailPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCashInvoiceDetail, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashInvoiceDetailList() {
       // mock 数据
       CashInvoiceDetailDO dbCashInvoiceDetail = randomPojo(CashInvoiceDetailDO.class, o -> { // 等会查询到
           o.setInvoice(null);
           o.setItem(null);
           o.setItemType(null);
           o.setItemId(null);
           o.setModel(null);
           o.setUnit(null);
           o.setAmount(null);
           o.setPrice(null);
           o.setMoney(null);
           o.setTaxRate(null);
           o.setTaxMoney(null);
           o.setCreateTime(null);
       });
       cashInvoiceDetailMapper.insert(dbCashInvoiceDetail);
       // 测试 invoice 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setInvoice(null)));
       // 测试 item 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setItem(null)));
       // 测试 itemType 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setItemType(null)));
       // 测试 itemId 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setItemId(null)));
       // 测试 model 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setModel(null)));
       // 测试 unit 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setUnit(null)));
       // 测试 amount 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setAmount(null)));
       // 测试 price 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setPrice(null)));
       // 测试 money 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setMoney(null)));
       // 测试 taxRate 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setTaxRate(null)));
       // 测试 taxMoney 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setTaxMoney(null)));
       // 测试 createTime 不匹配
       cashInvoiceDetailMapper.insert(cloneIgnoreId(dbCashInvoiceDetail, o -> o.setCreateTime(null)));
       // 准备参数
       CashInvoiceDetailExportReqVO reqVO = new CashInvoiceDetailExportReqVO();
       reqVO.setInvoice(null);
       reqVO.setItem(null);
       reqVO.setItemType(null);
       reqVO.setItemId(null);
       reqVO.setModel(null);
       reqVO.setUnit(null);
       reqVO.setAmount(null);
       reqVO.setPrice(null);
       reqVO.setMoney(null);
       reqVO.setTaxRate(null);
       reqVO.setTaxMoney(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CashInvoiceDetailDO> list = cashInvoiceDetailService.getCashInvoiceDetailList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCashInvoiceDetail, list.get(0));
    }

}
