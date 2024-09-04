package com.dofast.module.pro.service.feedback;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.feedback.vo.*;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.mysql.feedback.FeedbackMapper;
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
 * {@link FeedbackServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(FeedbackServiceImpl.class)
public class FeedbackServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FeedbackServiceImpl feedbackService;

    @Resource
    private FeedbackMapper feedbackMapper;

    @Test
    public void testCreateFeedback_success() {
        // 准备参数
        FeedbackCreateReqVO reqVO = randomPojo(FeedbackCreateReqVO.class);

        // 调用
        Long feedbackId = feedbackService.createFeedback(reqVO);
        // 断言
        assertNotNull(feedbackId);
        // 校验记录的属性是否正确
        FeedbackDO feedback = feedbackMapper.selectById(feedbackId);
        assertPojoEquals(reqVO, feedback);
    }

    @Test
    public void testUpdateFeedback_success() {
        // mock 数据
        FeedbackDO dbFeedback = randomPojo(FeedbackDO.class);
        feedbackMapper.insert(dbFeedback);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FeedbackUpdateReqVO reqVO = randomPojo(FeedbackUpdateReqVO.class, o -> {
            o.setId(dbFeedback.getId()); // 设置更新的 ID
        });

        // 调用
        feedbackService.updateFeedback(reqVO);
        // 校验是否更新正确
        FeedbackDO feedback = feedbackMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, feedback);
    }

    @Test
    public void testUpdateFeedback_notExists() {
        // 准备参数
        FeedbackUpdateReqVO reqVO = randomPojo(FeedbackUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> feedbackService.updateFeedback(reqVO), FEEDBACK_NOT_EXISTS);
    }

    @Test
    public void testDeleteFeedback_success() {
        // mock 数据
        FeedbackDO dbFeedback = randomPojo(FeedbackDO.class);
        feedbackMapper.insert(dbFeedback);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFeedback.getId();

        // 调用
        feedbackService.deleteFeedback(id);
       // 校验数据不存在了
       assertNull(feedbackMapper.selectById(id));
    }

    @Test
    public void testDeleteFeedback_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> feedbackService.deleteFeedback(id), FEEDBACK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackPage() {
       // mock 数据
       FeedbackDO dbFeedback = randomPojo(FeedbackDO.class, o -> { // 等会查询到
           o.setFeedbackType(null);
           o.setFeedbackCode(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setUnitOfMeasure(null);
           o.setSpecification(null);
           o.setQuantity(null);
           o.setQuantityFeedback(null);
           o.setQuantityQualified(null);
           o.setQuantityUnquanlified(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setFeedbackChannel(null);
           o.setFeedbackTime(null);
           o.setRecordUser(null);
           o.setRecordNick(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       feedbackMapper.insert(dbFeedback);
       // 测试 feedbackType 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackType(null)));
       // 测试 feedbackCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackCode(null)));
       // 测试 workstationId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkstationName(null)));
       // 测试 workorderId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkorderName(null)));
       // 测试 processId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setProcessName(null)));
       // 测试 taskId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setTaskCode(null)));
       // 测试 itemId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setItemName(null)));
       // 测试 unitOfMeasure 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setUnitOfMeasure(null)));
       // 测试 specification 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setSpecification(null)));
       // 测试 quantity 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantity(null)));
       // 测试 quantityFeedback 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantityFeedback(null)));
       // 测试 quantityQualified 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantityQualified(null)));
       // 测试 quantityUnquanlified 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantityUnquanlified(null)));
       // 测试 userName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setNickName(null)));
       // 测试 feedbackChannel 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackChannel(null)));
       // 测试 feedbackTime 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackTime(null)));
       // 测试 recordUser 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setRecordUser(null)));
       // 测试 recordNick 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setRecordNick(null)));
       // 测试 status 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setCreateTime(null)));
       // 准备参数
       FeedbackPageReqVO reqVO = new FeedbackPageReqVO();
       reqVO.setFeedbackType(null);
       reqVO.setFeedbackCode(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setSpecification(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityFeedback(null);
       reqVO.setQuantityQualified(null);
       reqVO.setQuantityUnquanlified(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setFeedbackChannel(null);
       reqVO.setFeedbackTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRecordUser(null);
       reqVO.setRecordNick(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<FeedbackDO> pageResult = feedbackService.getFeedbackPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFeedback, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackList() {
       // mock 数据
       FeedbackDO dbFeedback = randomPojo(FeedbackDO.class, o -> { // 等会查询到
           o.setFeedbackType(null);
           o.setFeedbackCode(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setUnitOfMeasure(null);
           o.setSpecification(null);
           o.setQuantity(null);
           o.setQuantityFeedback(null);
           o.setQuantityQualified(null);
           o.setQuantityUnquanlified(null);
           o.setUserName(null);
           o.setNickName(null);
           o.setFeedbackChannel(null);
           o.setFeedbackTime(null);
           o.setRecordUser(null);
           o.setRecordNick(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       feedbackMapper.insert(dbFeedback);
       // 测试 feedbackType 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackType(null)));
       // 测试 feedbackCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackCode(null)));
       // 测试 workstationId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkstationName(null)));
       // 测试 workorderId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setWorkorderName(null)));
       // 测试 processId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setProcessName(null)));
       // 测试 taskId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setTaskCode(null)));
       // 测试 itemId 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setItemName(null)));
       // 测试 unitOfMeasure 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setUnitOfMeasure(null)));
       // 测试 specification 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setSpecification(null)));
       // 测试 quantity 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantity(null)));
       // 测试 quantityFeedback 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantityFeedback(null)));
       // 测试 quantityQualified 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantityQualified(null)));
       // 测试 quantityUnquanlified 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setQuantityUnquanlified(null)));
       // 测试 userName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setUserName(null)));
       // 测试 nickName 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setNickName(null)));
       // 测试 feedbackChannel 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackChannel(null)));
       // 测试 feedbackTime 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackTime(null)));
       // 测试 recordUser 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setRecordUser(null)));
       // 测试 recordNick 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setRecordNick(null)));
       // 测试 status 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setCreateTime(null)));
       // 准备参数
       FeedbackExportReqVO reqVO = new FeedbackExportReqVO();
       reqVO.setFeedbackType(null);
       reqVO.setFeedbackCode(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setSpecification(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityFeedback(null);
       reqVO.setQuantityQualified(null);
       reqVO.setQuantityUnquanlified(null);
       reqVO.setUserName(null);
       reqVO.setNickName(null);
       reqVO.setFeedbackChannel(null);
       reqVO.setFeedbackTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRecordUser(null);
       reqVO.setRecordNick(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<FeedbackDO> list = feedbackService.getFeedbackList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFeedback, list.get(0));
    }

}
