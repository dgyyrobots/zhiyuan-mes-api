package com.dofast.module.pro.service.feedbackdefect;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.feedbackdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.feedbackdefect.FeedbackDefectDO;
import com.dofast.module.pro.dal.mysql.feedbackdefect.FeedbackDefectMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link FeedbackDefectServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FeedbackDefectServiceImpl.class)
public class FeedbackDefectServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FeedbackDefectServiceImpl feedbackDefectService;

    @Resource
    private FeedbackDefectMapper feedbackDefectMapper;

    @Test
    public void testCreateFeedbackDefect_success() {
        // 准备参数
        FeedbackDefectCreateReqVO reqVO = randomPojo(FeedbackDefectCreateReqVO.class);

        // 调用
        Long feedbackDefectId = feedbackDefectService.createFeedbackDefect(reqVO);
        // 断言
        assertNotNull(feedbackDefectId);
        // 校验记录的属性是否正确
        FeedbackDefectDO feedbackDefect = feedbackDefectMapper.selectById(feedbackDefectId);
        assertPojoEquals(reqVO, feedbackDefect);
    }

    @Test
    public void testUpdateFeedbackDefect_success() {
        // mock 数据
        FeedbackDefectDO dbFeedbackDefect = randomPojo(FeedbackDefectDO.class);
        feedbackDefectMapper.insert(dbFeedbackDefect);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FeedbackDefectUpdateReqVO reqVO = randomPojo(FeedbackDefectUpdateReqVO.class, o -> {
            o.setId(dbFeedbackDefect.getId()); // 设置更新的 ID
        });

        // 调用
        feedbackDefectService.updateFeedbackDefect(reqVO);
        // 校验是否更新正确
        FeedbackDefectDO feedbackDefect = feedbackDefectMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, feedbackDefect);
    }

    @Test
    public void testUpdateFeedbackDefect_notExists() {
        // 准备参数
        FeedbackDefectUpdateReqVO reqVO = randomPojo(FeedbackDefectUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> feedbackDefectService.updateFeedbackDefect(reqVO), FEEDBACK_DEFECT_NOT_EXISTS);
    }

    @Test
    public void testDeleteFeedbackDefect_success() {
        // mock 数据
        FeedbackDefectDO dbFeedbackDefect = randomPojo(FeedbackDefectDO.class);
        feedbackDefectMapper.insert(dbFeedbackDefect);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFeedbackDefect.getId();

        // 调用
        feedbackDefectService.deleteFeedbackDefect(id);
       // 校验数据不存在了
       assertNull(feedbackDefectMapper.selectById(id));
    }

    @Test
    public void testDeleteFeedbackDefect_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> feedbackDefectService.deleteFeedbackDefect(id), FEEDBACK_DEFECT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackDefectPage() {
       // mock 数据
       FeedbackDefectDO dbFeedbackDefect = randomPojo(FeedbackDefectDO.class, o -> { // 等会查询到
           o.setFeedbackId(null);
           o.setTaskCode(null);
           o.setCreateTime(null);
           o.setDefectName(null);
           o.setDefectId(null);
           o.setStartMeter(null);
           o.setEndMeter(null);
           o.setDefectMeter(null);
       });
       feedbackDefectMapper.insert(dbFeedbackDefect);
       // 测试 feedbackId 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setFeedbackId(null)));
       // 测试 taskCode 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setTaskCode(null)));
       // 测试 createTime 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setCreateTime(null)));
       // 测试 defectName 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setDefectName(null)));
       // 测试 defectId 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setDefectId(null)));
       // 测试 startMeter 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setStartMeter(null)));
       // 测试 endMeter 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setEndMeter(null)));
       // 测试 defectMeter 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setDefectMeter(null)));
       // 准备参数
       FeedbackDefectPageReqVO reqVO = new FeedbackDefectPageReqVO();
       reqVO.setFeedbackId(null);
       reqVO.setTaskCode(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDefectName(null);
       reqVO.setDefectId(null);
       reqVO.setStartMeter(null);
       reqVO.setEndMeter(null);
       reqVO.setDefectMeter(null);

       // 调用
       PageResult<FeedbackDefectDO> pageResult = feedbackDefectService.getFeedbackDefectPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFeedbackDefect, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackDefectList() {
       // mock 数据
       FeedbackDefectDO dbFeedbackDefect = randomPojo(FeedbackDefectDO.class, o -> { // 等会查询到
           o.setFeedbackId(null);
           o.setTaskCode(null);
           o.setCreateTime(null);
           o.setDefectName(null);
           o.setDefectId(null);
           o.setStartMeter(null);
           o.setEndMeter(null);
           o.setDefectMeter(null);
       });
       feedbackDefectMapper.insert(dbFeedbackDefect);
       // 测试 feedbackId 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setFeedbackId(null)));
       // 测试 taskCode 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setTaskCode(null)));
       // 测试 createTime 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setCreateTime(null)));
       // 测试 defectName 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setDefectName(null)));
       // 测试 defectId 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setDefectId(null)));
       // 测试 startMeter 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setStartMeter(null)));
       // 测试 endMeter 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setEndMeter(null)));
       // 测试 defectMeter 不匹配
       feedbackDefectMapper.insert(cloneIgnoreId(dbFeedbackDefect, o -> o.setDefectMeter(null)));
       // 准备参数
       FeedbackDefectExportReqVO reqVO = new FeedbackDefectExportReqVO();
       reqVO.setFeedbackId(null);
       reqVO.setTaskCode(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDefectName(null);
       reqVO.setDefectId(null);
       reqVO.setStartMeter(null);
       reqVO.setEndMeter(null);
       reqVO.setDefectMeter(null);

       // 调用
       List<FeedbackDefectDO> list = feedbackDefectService.getFeedbackDefectList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFeedbackDefect, list.get(0));
    }

}
