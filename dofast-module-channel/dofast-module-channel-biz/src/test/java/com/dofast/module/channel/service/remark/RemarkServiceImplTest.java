package com.dofast.module.channel.service.remark;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.channel.controller.admin.remark.vo.*;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;
import com.dofast.module.channel.dal.mysql.remark.RemarkMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.channel.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link RemarkServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(RemarkServiceImpl.class)
public class RemarkServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RemarkServiceImpl remarkService;

    @Resource
    private RemarkMapper remarkMapper;

    @Test
    public void testCreateRemark_success() {
        // 准备参数
        RemarkCreateReqVO reqVO = randomPojo(RemarkCreateReqVO.class);

        // 调用
        Long remarkId = remarkService.createRemark(reqVO);
        // 断言
        assertNotNull(remarkId);
        // 校验记录的属性是否正确
        RemarkDO remark = remarkMapper.selectById(remarkId);
        assertPojoEquals(reqVO, remark);
    }

    @Test
    public void testUpdateRemark_success() {
        // mock 数据
        RemarkDO dbRemark = randomPojo(RemarkDO.class);
        remarkMapper.insert(dbRemark);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RemarkUpdateReqVO reqVO = randomPojo(RemarkUpdateReqVO.class, o -> {
            o.setId(dbRemark.getId()); // 设置更新的 ID
        });

        // 调用
        remarkService.updateRemark(reqVO);
        // 校验是否更新正确
        RemarkDO remark = remarkMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, remark);
    }

    @Test
    public void testUpdateRemark_notExists() {
        // 准备参数
        RemarkUpdateReqVO reqVO = randomPojo(RemarkUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> remarkService.updateRemark(reqVO), REMARK_NOT_EXISTS);
    }

    @Test
    public void testDeleteRemark_success() {
        // mock 数据
        RemarkDO dbRemark = randomPojo(RemarkDO.class);
        remarkMapper.insert(dbRemark);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRemark.getId();

        // 调用
        remarkService.deleteRemark(id);
       // 校验数据不存在了
       assertNull(remarkMapper.selectById(id));
    }

    @Test
    public void testDeleteRemark_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> remarkService.deleteRemark(id), REMARK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRemarkPage() {
       // mock 数据
       RemarkDO dbRemark = randomPojo(RemarkDO.class, o -> { // 等会查询到
           o.setTradeOrderId(null);
           o.setSortCode(null);
           o.setRemark(null);
           o.setType(null);
           o.setSalId(null);
           o.setCreateTime(null);
       });
       remarkMapper.insert(dbRemark);
       // 测试 tradeOrderId 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setTradeOrderId(null)));
       // 测试 sortCode 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setSortCode(null)));
       // 测试 remark 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setRemark(null)));
       // 测试 type 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setType(null)));
       // 测试 salId 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setSalId(null)));
       // 测试 createTime 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setCreateTime(null)));
       // 准备参数
       RemarkPageReqVO reqVO = new RemarkPageReqVO();
       reqVO.setTradeOrderId(null);
       reqVO.setSortCode(null);
       reqVO.setRemark(null);
       reqVO.setType(null);
       reqVO.setSalId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RemarkDO> pageResult = remarkService.getRemarkPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRemark, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRemarkList() {
       // mock 数据
       RemarkDO dbRemark = randomPojo(RemarkDO.class, o -> { // 等会查询到
           o.setTradeOrderId(null);
           o.setSortCode(null);
           o.setRemark(null);
           o.setType(null);
           o.setSalId(null);
           o.setCreateTime(null);
       });
       remarkMapper.insert(dbRemark);
       // 测试 tradeOrderId 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setTradeOrderId(null)));
       // 测试 sortCode 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setSortCode(null)));
       // 测试 remark 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setRemark(null)));
       // 测试 type 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setType(null)));
       // 测试 salId 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setSalId(null)));
       // 测试 createTime 不匹配
       remarkMapper.insert(cloneIgnoreId(dbRemark, o -> o.setCreateTime(null)));
       // 准备参数
       RemarkExportReqVO reqVO = new RemarkExportReqVO();
       reqVO.setTradeOrderId(null);
       reqVO.setSortCode(null);
       reqVO.setRemark(null);
       reqVO.setType(null);
       reqVO.setSalId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RemarkDO> list = remarkService.getRemarkList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRemark, list.get(0));
    }

}
