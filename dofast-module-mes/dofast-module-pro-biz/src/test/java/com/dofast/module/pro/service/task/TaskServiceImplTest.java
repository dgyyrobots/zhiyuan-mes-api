package com.dofast.module.pro.service.task;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.task.vo.*;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.mysql.task.TaskMapper;
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
 * {@link TaskServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TaskServiceImpl.class)
public class TaskServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TaskServiceImpl taskService;

    @Resource
    private TaskMapper taskMapper;

    @Test
    public void testCreateTask_success() {
        // 准备参数
        TaskCreateReqVO reqVO = randomPojo(TaskCreateReqVO.class);

        // 调用
        Long taskId = taskService.createTask(reqVO);
        // 断言
        assertNotNull(taskId);
        // 校验记录的属性是否正确
        TaskDO task = taskMapper.selectById(taskId);
        assertPojoEquals(reqVO, task);
    }

    @Test
    public void testUpdateTask_success() {
        // mock 数据
        TaskDO dbTask = randomPojo(TaskDO.class);
        taskMapper.insert(dbTask);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TaskUpdateReqVO reqVO = randomPojo(TaskUpdateReqVO.class, o -> {
            o.setId(dbTask.getId()); // 设置更新的 ID
        });

        // 调用
        taskService.updateTask(reqVO);
        // 校验是否更新正确
        TaskDO task = taskMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, task);
    }

    @Test
    public void testUpdateTask_notExists() {
        // 准备参数
        TaskUpdateReqVO reqVO = randomPojo(TaskUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> taskService.updateTask(reqVO), TASK_NOT_EXISTS);
    }

    @Test
    public void testDeleteTask_success() {
        // mock 数据
        TaskDO dbTask = randomPojo(TaskDO.class);
        taskMapper.insert(dbTask);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTask.getId();

        // 调用
        taskService.deleteTask(id);
       // 校验数据不存在了
       assertNull(taskMapper.selectById(id));
    }

    @Test
    public void testDeleteTask_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> taskService.deleteTask(id), TASK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTaskPage() {
       // mock 数据
       TaskDO dbTask = randomPojo(TaskDO.class, o -> { // 等会查询到
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setQuantityProduced(null);
           o.setQuantityQuanlify(null);
           o.setQuantityUnquanlify(null);
           o.setQuantityChanged(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setStartTime(null);
           o.setDuration(null);
           o.setEndTime(null);
           o.setColorCode(null);
           o.setRequestDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       taskMapper.insert(dbTask);
       // 测试 taskCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setTaskName(null)));
       // 测试 workorderId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkorderName(null)));
       // 测试 workstationId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setProcessName(null)));
       // 测试 itemId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantity(null)));
       // 测试 quantityProduced 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityProduced(null)));
       // 测试 quantityQuanlify 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityQuanlify(null)));
       // 测试 quantityUnquanlify 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityUnquanlify(null)));
       // 测试 quantityChanged 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityChanged(null)));
       // 测试 clientId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientNick(null)));
       // 测试 startTime 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setStartTime(null)));
       // 测试 duration 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setDuration(null)));
       // 测试 endTime 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setEndTime(null)));
       // 测试 colorCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setColorCode(null)));
       // 测试 requestDate 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setRequestDate(null)));
       // 测试 status 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setCreateTime(null)));
       // 准备参数
       TaskPageReqVO reqVO = new TaskPageReqVO();
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityProduced(null);
       reqVO.setQuantityQuanlify(null);
       reqVO.setQuantityUnquanlify(null);
       reqVO.setQuantityChanged(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setStartTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDuration(null);
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setColorCode(null);
       reqVO.setRequestDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       PageResult<TaskDO> pageResult = taskService.getTaskPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTask, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTaskList() {
       // mock 数据
       TaskDO dbTask = randomPojo(TaskDO.class, o -> { // 等会查询到
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantity(null);
           o.setQuantityProduced(null);
           o.setQuantityQuanlify(null);
           o.setQuantityUnquanlify(null);
           o.setQuantityChanged(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setStartTime(null);
           o.setDuration(null);
           o.setEndTime(null);
           o.setColorCode(null);
           o.setRequestDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       taskMapper.insert(dbTask);
       // 测试 taskCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setTaskName(null)));
       // 测试 workorderId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkorderName(null)));
       // 测试 workstationId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setProcessName(null)));
       // 测试 itemId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setUnitOfMeasure(null)));
       // 测试 quantity 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantity(null)));
       // 测试 quantityProduced 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityProduced(null)));
       // 测试 quantityQuanlify 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityQuanlify(null)));
       // 测试 quantityUnquanlify 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityUnquanlify(null)));
       // 测试 quantityChanged 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setQuantityChanged(null)));
       // 测试 clientId 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setClientNick(null)));
       // 测试 startTime 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setStartTime(null)));
       // 测试 duration 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setDuration(null)));
       // 测试 endTime 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setEndTime(null)));
       // 测试 colorCode 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setColorCode(null)));
       // 测试 requestDate 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setRequestDate(null)));
       // 测试 status 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       taskMapper.insert(cloneIgnoreId(dbTask, o -> o.setCreateTime(null)));
       // 准备参数
       TaskExportReqVO reqVO = new TaskExportReqVO();
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantity(null);
       reqVO.setQuantityProduced(null);
       reqVO.setQuantityQuanlify(null);
       reqVO.setQuantityUnquanlify(null);
       reqVO.setQuantityChanged(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setDuration(null);
       reqVO.setColorCode(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       List<TaskDO> list = taskService.getTaskList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTask, list.get(0));
    }

}
