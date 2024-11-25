package com.dofast.module.pro.service.feedbackmember;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.feedbackmember.vo.*;
import com.dofast.module.pro.dal.dataobject.feedbackmember.FeedbackMemberDO;
import com.dofast.module.pro.dal.mysql.feedbackmember.FeedbackMemberMapper;
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
 * {@link FeedbackMemberServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FeedbackMemberServiceImpl.class)
public class FeedbackMemberServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FeedbackMemberServiceImpl feedbackMemberService;

    @Resource
    private FeedbackMemberMapper feedbackMemberMapper;

    @Test
    public void testCreateFeedbackMember_success() {
        // 准备参数
        FeedbackMemberCreateReqVO reqVO = randomPojo(FeedbackMemberCreateReqVO.class);

        // 调用
        Long feedbackMemberId = feedbackMemberService.createFeedbackMember(reqVO);
        // 断言
        assertNotNull(feedbackMemberId);
        // 校验记录的属性是否正确
        FeedbackMemberDO feedbackMember = feedbackMemberMapper.selectById(feedbackMemberId);
        assertPojoEquals(reqVO, feedbackMember);
    }

    @Test
    public void testUpdateFeedbackMember_success() {
        // mock 数据
        FeedbackMemberDO dbFeedbackMember = randomPojo(FeedbackMemberDO.class);
        feedbackMemberMapper.insert(dbFeedbackMember);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FeedbackMemberUpdateReqVO reqVO = randomPojo(FeedbackMemberUpdateReqVO.class, o -> {
            o.setId(dbFeedbackMember.getId()); // 设置更新的 ID
        });

        // 调用
        feedbackMemberService.updateFeedbackMember(reqVO);
        // 校验是否更新正确
        FeedbackMemberDO feedbackMember = feedbackMemberMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, feedbackMember);
    }

    @Test
    public void testUpdateFeedbackMember_notExists() {
        // 准备参数
        FeedbackMemberUpdateReqVO reqVO = randomPojo(FeedbackMemberUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> feedbackMemberService.updateFeedbackMember(reqVO), FEEDBACK_MEMBER_NOT_EXISTS);
    }

    @Test
    public void testDeleteFeedbackMember_success() {
        // mock 数据
        FeedbackMemberDO dbFeedbackMember = randomPojo(FeedbackMemberDO.class);
        feedbackMemberMapper.insert(dbFeedbackMember);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFeedbackMember.getId();

        // 调用
        feedbackMemberService.deleteFeedbackMember(id);
       // 校验数据不存在了
       assertNull(feedbackMemberMapper.selectById(id));
    }

    @Test
    public void testDeleteFeedbackMember_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> feedbackMemberService.deleteFeedbackMember(id), FEEDBACK_MEMBER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackMemberPage() {
       // mock 数据
       FeedbackMemberDO dbFeedbackMember = randomPojo(FeedbackMemberDO.class, o -> { // 等会查询到
           o.setFeedbackId(null);
           o.setTaskCode(null);
           o.setTeamCode(null);
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setCreateTime(null);
       });
       feedbackMemberMapper.insert(dbFeedbackMember);
       // 测试 feedbackCode 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setFeedbackId(null)));
       // 测试 taskCode 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setTaskCode(null)));
       // 测试 teamCode 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setTeamCode(null)));
       // 测试 userId 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setNickName(null)));
       // 测试 createTime 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setCreateTime(null)));
       // 准备参数
       FeedbackMemberPageReqVO reqVO = new FeedbackMemberPageReqVO();
       reqVO.setFeedbackId(null);
       reqVO.setTaskCode(null);
       reqVO.setTeamCode(null);
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<FeedbackMemberDO> pageResult = feedbackMemberService.getFeedbackMemberPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFeedbackMember, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackMemberList() {
       // mock 数据
       FeedbackMemberDO dbFeedbackMember = randomPojo(FeedbackMemberDO.class, o -> { // 等会查询到
           o.setFeedbackId(null);
           o.setTaskCode(null);
           o.setTeamCode(null);
           o.setUserId(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setCreateTime(null);
       });
       feedbackMemberMapper.insert(dbFeedbackMember);
       // 测试 feedbackCode 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setFeedbackId(null)));
       // 测试 taskCode 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setTaskCode(null)));
       // 测试 teamCode 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setTeamCode(null)));
       // 测试 userId 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setUserId(null)));
       // 测试 userName 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setNickName(null)));
       // 测试 createTime 不匹配
       feedbackMemberMapper.insert(cloneIgnoreId(dbFeedbackMember, o -> o.setCreateTime(null)));
       // 准备参数
       FeedbackMemberExportReqVO reqVO = new FeedbackMemberExportReqVO();
       reqVO.setFeedbackId(null);
       reqVO.setTaskCode(null);
       reqVO.setTeamCode(null);
       reqVO.setUserId(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<FeedbackMemberDO> list = feedbackMemberService.getFeedbackMemberList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFeedbackMember, list.get(0));
    }

}
