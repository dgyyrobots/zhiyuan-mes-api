package com.dofast.module.hr.service.tradecommission;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.hr.controller.admin.tradecommission.vo.TradecommissionCreateReqVO;
import com.dofast.module.hr.controller.admin.tradecommission.vo.TradecommissionExportReqVO;
import com.dofast.module.hr.controller.admin.tradecommission.vo.TradecommissionPageReqVO;
import com.dofast.module.hr.controller.admin.tradecommission.vo.TradecommissionUpdateReqVO;
import com.dofast.module.hr.dal.dataobject.tradecommission.TradecommissionDO;
import com.dofast.module.hr.dal.mysql.tradecommission.TradecommissionMapper;
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
import static com.dofast.module.hr.enums.ErrorCodeConstants.TRADECOMMISSION_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link TradecommissionServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(TradecommissionServiceImpl.class)
public class TradecommissionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TradecommissionServiceImpl tradecommissionService;

    @Resource
    private TradecommissionMapper tradecommissionMapper;

    @Test
    public void testCreateTradecommission_success() {
        // 准备参数
        TradecommissionCreateReqVO reqVO = randomPojo(TradecommissionCreateReqVO.class);

        // 调用
        Integer tradecommissionId = tradecommissionService.createTradecommission(reqVO);
        // 断言
        assertNotNull(tradecommissionId);
        // 校验记录的属性是否正确
        TradecommissionDO tradecommission = tradecommissionMapper.selectById(tradecommissionId);
        assertPojoEquals(reqVO, tradecommission);
    }

    @Test
    public void testUpdateTradecommission_success() {
        // mock 数据
        TradecommissionDO dbTradecommission = randomPojo(TradecommissionDO.class);
        tradecommissionMapper.insert(dbTradecommission);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TradecommissionUpdateReqVO reqVO = randomPojo(TradecommissionUpdateReqVO.class, o -> {
            o.setId(dbTradecommission.getId()); // 设置更新的 ID
        });

        // 调用
        tradecommissionService.updateTradecommission(reqVO);
        // 校验是否更新正确
        TradecommissionDO tradecommission = tradecommissionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, tradecommission);
    }

    @Test
    public void testUpdateTradecommission_notExists() {
        // 准备参数
        TradecommissionUpdateReqVO reqVO = randomPojo(TradecommissionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> tradecommissionService.updateTradecommission(reqVO), TRADECOMMISSION_NOT_EXISTS);
    }

    @Test
    public void testDeleteTradecommission_success() {
        // mock 数据
        TradecommissionDO dbTradecommission = randomPojo(TradecommissionDO.class);
        tradecommissionMapper.insert(dbTradecommission);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbTradecommission.getId();

        // 调用
        tradecommissionService.deleteTradecommission(id);
       // 校验数据不存在了
       assertNull(tradecommissionMapper.selectById(id));
    }

    @Test
    public void testDeleteTradecommission_notExists() {
        // 准备参数
//        Integer id = randomIntegerId();
        Integer id = randomInteger();

        // 调用, 并断言异常
        assertServiceException(() -> tradecommissionService.deleteTradecommission(id), TRADECOMMISSION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTradecommissionPage() {
       // mock 数据
       TradecommissionDO dbTradecommission = randomPojo(TradecommissionDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setSignType(null);
           o.setSaleType(null);
           o.setTrade(null);
           o.setContract(null);
           o.setAccount(null);
           o.setContribution(null);
           o.setRate(null);
           o.setAmount(null);
           o.setDesc(null);
           o.setCreateTime(null);
       });
       tradecommissionMapper.insert(dbTradecommission);
       // 测试 type 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setType(null)));
       // 测试 signType 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setSignType(null)));
       // 测试 saleType 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setSaleType(null)));
       // 测试 trade 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setTrade(null)));
       // 测试 contract 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setContract(null)));
       // 测试 account 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setAccount(null)));
       // 测试 contribution 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setContribution(null)));
       // 测试 rate 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setRate(null)));
       // 测试 amount 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setAmount(null)));
       // 测试 desc 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setDesc(null)));
       // 测试 createTime 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setCreateTime(null)));
       // 准备参数
       TradecommissionPageReqVO reqVO = new TradecommissionPageReqVO();
       reqVO.setType(null);
       reqVO.setSignType(null);
       reqVO.setSaleType(null);
       reqVO.setTrade(null);
       reqVO.setContract(null);
       reqVO.setAccount(null);
       reqVO.setContribution(null);
       reqVO.setRate(null);
       reqVO.setAmount(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TradecommissionDO> pageResult = tradecommissionService.getTradecommissionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTradecommission, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTradecommissionList() {
       // mock 数据
       TradecommissionDO dbTradecommission = randomPojo(TradecommissionDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setSignType(null);
           o.setSaleType(null);
           o.setTrade(null);
           o.setContract(null);
           o.setAccount(null);
           o.setContribution(null);
           o.setRate(null);
           o.setAmount(null);
           o.setDesc(null);
           o.setCreateTime(null);
       });
       tradecommissionMapper.insert(dbTradecommission);
       // 测试 type 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setType(null)));
       // 测试 signType 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setSignType(null)));
       // 测试 saleType 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setSaleType(null)));
       // 测试 trade 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setTrade(null)));
       // 测试 contract 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setContract(null)));
       // 测试 account 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setAccount(null)));
       // 测试 contribution 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setContribution(null)));
       // 测试 rate 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setRate(null)));
       // 测试 amount 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setAmount(null)));
       // 测试 desc 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setDesc(null)));
       // 测试 createTime 不匹配
       tradecommissionMapper.insert(cloneIgnoreId(dbTradecommission, o -> o.setCreateTime(null)));
       // 准备参数
       TradecommissionExportReqVO reqVO = new TradecommissionExportReqVO();
       reqVO.setType(null);
       reqVO.setSignType(null);
       reqVO.setSaleType(null);
       reqVO.setTrade(null);
       reqVO.setContract(null);
       reqVO.setAccount(null);
       reqVO.setContribution(null);
       reqVO.setRate(null);
       reqVO.setAmount(null);
       reqVO.setDesc(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TradecommissionDO> list = tradecommissionService.getTradecommissionList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTradecommission, list.get(0));
    }

}
