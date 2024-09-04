package com.dofast.module.finance.service.cash;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.finance.controller.admin.cash.vo.CashDepositorCreateReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashDepositorExportReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashDepositorPageReqVO;
import com.dofast.module.finance.controller.admin.cash.vo.CashDepositorUpdateReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashDepositorDO;
import com.dofast.module.finance.dal.mysql.cash.CashDepositorMapper;
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
import static com.dofast.module.finance.enums.ErrorCodeConstants.CASH_DEPOSITOR_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link CashDepositorServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CashDepositorServiceImpl.class)
public class CashDepositorServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CashDepositorServiceImpl cashDepositorService;

    @Resource
    private CashDepositorMapper cashDepositorMapper;

    @Test
    public void testCreateCashDepositor_success() {
        // 准备参数
        CashDepositorCreateReqVO reqVO = randomPojo(CashDepositorCreateReqVO.class);

        // 调用
        Long cashDepositorId = cashDepositorService.createCashDepositor(reqVO);
        // 断言
        assertNotNull(cashDepositorId);
        // 校验记录的属性是否正确
        CashDepositorDO cashDepositor = cashDepositorMapper.selectById(cashDepositorId);
        assertPojoEquals(reqVO, cashDepositor);
    }

    @Test
    public void testUpdateCashDepositor_success() {
        // mock 数据
        CashDepositorDO dbCashDepositor = randomPojo(CashDepositorDO.class);
        cashDepositorMapper.insert(dbCashDepositor);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CashDepositorUpdateReqVO reqVO = randomPojo(CashDepositorUpdateReqVO.class, o -> {
            o.setId(dbCashDepositor.getId()); // 设置更新的 ID
        });

        // 调用
        cashDepositorService.updateCashDepositor(reqVO);
        // 校验是否更新正确
        CashDepositorDO cashDepositor = cashDepositorMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cashDepositor);
    }

    @Test
    public void testUpdateCashDepositor_notExists() {
        // 准备参数
        CashDepositorUpdateReqVO reqVO = randomPojo(CashDepositorUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cashDepositorService.updateCashDepositor(reqVO), CASH_DEPOSITOR_NOT_EXISTS);
    }

    @Test
    public void testDeleteCashDepositor_success() {
        // mock 数据
        CashDepositorDO dbCashDepositor = randomPojo(CashDepositorDO.class);
        cashDepositorMapper.insert(dbCashDepositor);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCashDepositor.getId();

        // 调用
        cashDepositorService.deleteCashDepositor(id);
       // 校验数据不存在了
       assertNull(cashDepositorMapper.selectById(id));
    }

    @Test
    public void testDeleteCashDepositor_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cashDepositorService.deleteCashDepositor(id), CASH_DEPOSITOR_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashDepositorPage() {
       // mock 数据
       CashDepositorDO dbCashDepositor = randomPojo(CashDepositorDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setAbbr(null);
           o.setTitle(null);
           o.setTags(null);
           o.setProvider(null);
           o.setBank(null);
           o.setCustomerNo(null);
           o.setAccount(null);
           o.setUnionBankNo(null);
           o.setClearingBankNo(null);
//           o.setPublic(null);
           o.setCurrency(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       cashDepositorMapper.insert(dbCashDepositor);
       // 测试 type 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setType(null)));
       // 测试 abbr 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setAbbr(null)));
       // 测试 title 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setTitle(null)));
       // 测试 tags 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setTags(null)));
       // 测试 provider 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setProvider(null)));
       // 测试 bank 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setBank(null)));
       // 测试 customerNo 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setCustomerNo(null)));
       // 测试 account 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setAccount(null)));
       // 测试 unionBankNo 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setUnionBankNo(null)));
       // 测试 clearingBankNo 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setClearingBankNo(null)));
       // 测试 public 不匹配
//       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setPublic(null)));
       // 测试 currency 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setCurrency(null)));
       // 测试 status 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setCreateTime(null)));
       // 准备参数
       CashDepositorPageReqVO reqVO = new CashDepositorPageReqVO();
       reqVO.setType(null);
       reqVO.setAbbr(null);
       reqVO.setTitle(null);
       reqVO.setTags(null);
       reqVO.setProvider(null);
       reqVO.setBank(null);
       reqVO.setCustomerNo(null);
       reqVO.setAccount(null);
       reqVO.setUnionBankNo(null);
       reqVO.setClearingBankNo(null);
//       reqVO.setPublic(null);
       reqVO.setCurrency(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CashDepositorDO> pageResult = cashDepositorService.getCashDepositorPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCashDepositor, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCashDepositorList() {
       // mock 数据
       CashDepositorDO dbCashDepositor = randomPojo(CashDepositorDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setAbbr(null);
           o.setTitle(null);
           o.setTags(null);
           o.setProvider(null);
           o.setBank(null);
           o.setCustomerNo(null);
           o.setAccount(null);
           o.setUnionBankNo(null);
           o.setClearingBankNo(null);
//           o.setPublic(null);
           o.setCurrency(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       cashDepositorMapper.insert(dbCashDepositor);
       // 测试 type 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setType(null)));
       // 测试 abbr 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setAbbr(null)));
       // 测试 title 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setTitle(null)));
       // 测试 tags 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setTags(null)));
       // 测试 provider 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setProvider(null)));
       // 测试 bank 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setBank(null)));
       // 测试 customerNo 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setCustomerNo(null)));
       // 测试 account 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setAccount(null)));
       // 测试 unionBankNo 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setUnionBankNo(null)));
       // 测试 clearingBankNo 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setClearingBankNo(null)));
       // 测试 public 不匹配
//       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setPublic(null)));
       // 测试 currency 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setCurrency(null)));
       // 测试 status 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       cashDepositorMapper.insert(cloneIgnoreId(dbCashDepositor, o -> o.setCreateTime(null)));
       // 准备参数
       CashDepositorExportReqVO reqVO = new CashDepositorExportReqVO();
       reqVO.setType(null);
       reqVO.setAbbr(null);
       reqVO.setTitle(null);
       reqVO.setTags(null);
       reqVO.setProvider(null);
       reqVO.setBank(null);
       reqVO.setCustomerNo(null);
       reqVO.setAccount(null);
       reqVO.setUnionBankNo(null);
       reqVO.setClearingBankNo(null);
//       reqVO.setPublic(null);
       reqVO.setCurrency(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CashDepositorDO> list = cashDepositorService.getCashDepositorList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCashDepositor, list.get(0));
    }

}
