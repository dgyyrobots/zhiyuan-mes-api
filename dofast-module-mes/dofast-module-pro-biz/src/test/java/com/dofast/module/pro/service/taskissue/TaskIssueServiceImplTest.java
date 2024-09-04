package com.dofast.module.pro.service.taskissue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.taskissue.vo.*;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;
import com.dofast.module.pro.dal.mysql.taskissue.TaskIssueMapper;
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
 * {@link TaskIssueServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TaskIssueServiceImpl.class)
public class TaskIssueServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TaskIssueServiceImpl taskIssueService;

    @Resource
    private TaskIssueMapper taskIssueMapper;

    @Test
    public void testCreateTaskIssue_success() {
        // 准备参数
        TaskIssueCreateReqVO reqVO = randomPojo(TaskIssueCreateReqVO.class);

        // 调用
        Long taskIssueId = taskIssueService.createTaskIssue(reqVO);
        // 断言
        assertNotNull(taskIssueId);
        // 校验记录的属性是否正确
        TaskIssueDO taskIssue = taskIssueMapper.selectById(taskIssueId);
        assertPojoEquals(reqVO, taskIssue);
    }

    @Test
    public void testUpdateTaskIssue_success() {
        // mock 数据
        TaskIssueDO dbTaskIssue = randomPojo(TaskIssueDO.class);
        taskIssueMapper.insert(dbTaskIssue);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TaskIssueUpdateReqVO reqVO = randomPojo(TaskIssueUpdateReqVO.class, o -> {
            o.setId(dbTaskIssue.getId()); // 设置更新的 ID
        });

        // 调用
        taskIssueService.updateTaskIssue(reqVO);
        // 校验是否更新正确
        TaskIssueDO taskIssue = taskIssueMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, taskIssue);
    }

    @Test
    public void testUpdateTaskIssue_notExists() {
        // 准备参数
        TaskIssueUpdateReqVO reqVO = randomPojo(TaskIssueUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> taskIssueService.updateTaskIssue(reqVO), TASK_ISSUE_NOT_EXISTS);
    }

    @Test
    public void testDeleteTaskIssue_success() {
        // mock 数据
        TaskIssueDO dbTaskIssue = randomPojo(TaskIssueDO.class);
        taskIssueMapper.insert(dbTaskIssue);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTaskIssue.getId();

        // 调用
        taskIssueService.deleteTaskIssue(id);
       // 校验数据不存在了
       assertNull(taskIssueMapper.selectById(id));
    }

    @Test
    public void testDeleteTaskIssue_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> taskIssueService.deleteTaskIssue(id), TASK_ISSUE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTaskIssuePage() {
       // mock 数据
       TaskIssueDO dbTaskIssue = randomPojo(TaskIssueDO.class, o -> { // 等会查询到
           o.setTaskId(null);
           o.setWorkorderId(null);
           o.setWorkstationId(null);
           o.setSourceDocId(null);
           o.setSourceDocCode(null);
           o.setBatchCode(null);
           o.setSourceLineId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityIssued(null);
           o.setQuantityAvailable(null);
           o.setQuantityUsed(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       taskIssueMapper.insert(dbTaskIssue);
       // 测试 taskId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setTaskId(null)));
       // 测试 workorderId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setWorkorderId(null)));
       // 测试 workstationId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setWorkstationId(null)));
       // 测试 sourceDocId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSourceDocId(null)));
       // 测试 sourceDocCode 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSourceDocCode(null)));
       // 测试 batchCode 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setBatchCode(null)));
       // 测试 sourceLineId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSourceLineId(null)));
       // 测试 itemId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityIssued 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setQuantityIssued(null)));
       // 测试 quantityAvailable 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setQuantityAvailable(null)));
       // 测试 quantityUsed 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setQuantityUsed(null)));
       // 测试 remark 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setCreateTime(null)));
       // 准备参数
       TaskIssuePageReqVO reqVO = new TaskIssuePageReqVO();
       reqVO.setTaskId(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkstationId(null);
       reqVO.setSourceDocId(null);
       reqVO.setSourceDocCode(null);
       reqVO.setBatchCode(null);
       reqVO.setSourceLineId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityIssued(null);
       reqVO.setQuantityAvailable(null);
       reqVO.setQuantityUsed(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       PageResult<TaskIssueDO> pageResult = taskIssueService.getTaskIssuePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTaskIssue, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTaskIssueList() {
       // mock 数据
       TaskIssueDO dbTaskIssue = randomPojo(TaskIssueDO.class, o -> { // 等会查询到
           o.setTaskId(null);
           o.setWorkorderId(null);
           o.setWorkstationId(null);
           o.setSourceDocId(null);
           o.setSourceDocCode(null);
           o.setBatchCode(null);
           o.setSourceLineId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityIssued(null);
           o.setQuantityAvailable(null);
           o.setQuantityUsed(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       taskIssueMapper.insert(dbTaskIssue);
       // 测试 taskId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setTaskId(null)));
       // 测试 workorderId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setWorkorderId(null)));
       // 测试 workstationId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setWorkstationId(null)));
       // 测试 sourceDocId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSourceDocId(null)));
       // 测试 sourceDocCode 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSourceDocCode(null)));
       // 测试 batchCode 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setBatchCode(null)));
       // 测试 sourceLineId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSourceLineId(null)));
       // 测试 itemId 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityIssued 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setQuantityIssued(null)));
       // 测试 quantityAvailable 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setQuantityAvailable(null)));
       // 测试 quantityUsed 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setQuantityUsed(null)));
       // 测试 remark 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       taskIssueMapper.insert(cloneIgnoreId(dbTaskIssue, o -> o.setCreateTime(null)));
       // 准备参数
       TaskIssueExportReqVO reqVO = new TaskIssueExportReqVO();
       reqVO.setTaskId(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkstationId(null);
       reqVO.setSourceDocId(null);
       reqVO.setSourceDocCode(null);
       reqVO.setBatchCode(null);
       reqVO.setSourceLineId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityIssued(null);
       reqVO.setQuantityAvailable(null);
       reqVO.setQuantityUsed(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);


       // 调用
       List<TaskIssueDO> list = taskIssueService.getTaskIssueList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTaskIssue, list.get(0));
    }

}
