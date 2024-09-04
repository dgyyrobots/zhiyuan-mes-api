package com.dofast.module.wms.service.itemconsume;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.itemconsume.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;
import com.dofast.module.wms.dal.mysql.itemconsume.ItemConsumeMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ItemConsumeServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ItemConsumeServiceImpl.class)
public class ItemConsumeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ItemConsumeServiceImpl itemConsumeService;

    @Resource
    private ItemConsumeMapper itemConsumeMapper;

    @Test
    public void testCreateItemConsume_success() {
        // 准备参数
        ItemConsumeCreateReqVO reqVO = randomPojo(ItemConsumeCreateReqVO.class);

        // 调用
        Long itemConsumeId = itemConsumeService.createItemConsume(reqVO);
        // 断言
        assertNotNull(itemConsumeId);
        // 校验记录的属性是否正确
        ItemConsumeDO itemConsume = itemConsumeMapper.selectById(itemConsumeId);
        assertPojoEquals(reqVO, itemConsume);
    }

    @Test
    public void testUpdateItemConsume_success() {
        // mock 数据
        ItemConsumeDO dbItemConsume = randomPojo(ItemConsumeDO.class);
        itemConsumeMapper.insert(dbItemConsume);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ItemConsumeUpdateReqVO reqVO = randomPojo(ItemConsumeUpdateReqVO.class, o -> {
            o.setId(dbItemConsume.getId()); // 设置更新的 ID
        });

        // 调用
        itemConsumeService.updateItemConsume(reqVO);
        // 校验是否更新正确
        ItemConsumeDO itemConsume = itemConsumeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, itemConsume);
    }

    @Test
    public void testUpdateItemConsume_notExists() {
        // 准备参数
        ItemConsumeUpdateReqVO reqVO = randomPojo(ItemConsumeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> itemConsumeService.updateItemConsume(reqVO), ITEM_CONSUME_NOT_EXISTS);
    }

    @Test
    public void testDeleteItemConsume_success() {
        // mock 数据
        ItemConsumeDO dbItemConsume = randomPojo(ItemConsumeDO.class);
        itemConsumeMapper.insert(dbItemConsume);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbItemConsume.getId();

        // 调用
        itemConsumeService.deleteItemConsume(id);
       // 校验数据不存在了
       assertNull(itemConsumeMapper.selectById(id));
    }

    @Test
    public void testDeleteItemConsume_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> itemConsumeService.deleteItemConsume(id), ITEM_CONSUME_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemConsumePage() {
       // mock 数据
       ItemConsumeDO dbItemConsume = randomPojo(ItemConsumeDO.class, o -> { // 等会查询到
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setConsumeDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemConsumeMapper.insert(dbItemConsume);
       // 测试 workorderId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkorderName(null)));
       // 测试 taskId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setTaskName(null)));
       // 测试 workstationId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setProcessName(null)));
       // 测试 consumeDate 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setConsumeDate(null)));
       // 测试 status 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setCreateTime(null)));
       // 准备参数
       ItemConsumePageReqVO reqVO = new ItemConsumePageReqVO();
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setConsumeDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ItemConsumeDO> pageResult = itemConsumeService.getItemConsumePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbItemConsume, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemConsumeList() {
       // mock 数据
       ItemConsumeDO dbItemConsume = randomPojo(ItemConsumeDO.class, o -> { // 等会查询到
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setTaskName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setConsumeDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       itemConsumeMapper.insert(dbItemConsume);
       // 测试 workorderId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkorderName(null)));
       // 测试 taskId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setTaskName(null)));
       // 测试 workstationId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setProcessName(null)));
       // 测试 consumeDate 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setConsumeDate(null)));
       // 测试 status 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       itemConsumeMapper.insert(cloneIgnoreId(dbItemConsume, o -> o.setCreateTime(null)));
       // 准备参数
       ItemConsumeExportReqVO reqVO = new ItemConsumeExportReqVO();
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setTaskName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setConsumeDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ItemConsumeDO> list = itemConsumeService.getItemConsumeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbItemConsume, list.get(0));
    }

}
