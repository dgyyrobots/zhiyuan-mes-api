package com.dofast.module.pro.service.transconsume;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.transconsume.vo.*;
import com.dofast.module.pro.dal.dataobject.transconsume.TransConsumeDO;
import com.dofast.module.pro.dal.mysql.transconsume.TransConsumeMapper;
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
 * {@link TransConsumeServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TransConsumeServiceImpl.class)
public class TransConsumeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TransConsumeServiceImpl transConsumeService;

    @Resource
    private TransConsumeMapper transConsumeMapper;

    @Test
    public void testCreateTransConsume_success() {
        // 准备参数
        TransConsumeCreateReqVO reqVO = randomPojo(TransConsumeCreateReqVO.class);

        // 调用
        Long transConsumeId = transConsumeService.createTransConsume(reqVO);
        // 断言
        assertNotNull(transConsumeId);
        // 校验记录的属性是否正确
        TransConsumeDO transConsume = transConsumeMapper.selectById(transConsumeId);
        assertPojoEquals(reqVO, transConsume);
    }

    @Test
    public void testUpdateTransConsume_success() {
        // mock 数据
        TransConsumeDO dbTransConsume = randomPojo(TransConsumeDO.class);
        transConsumeMapper.insert(dbTransConsume);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TransConsumeUpdateReqVO reqVO = randomPojo(TransConsumeUpdateReqVO.class, o -> {
            o.setId(dbTransConsume.getId()); // 设置更新的 ID
        });

        // 调用
        transConsumeService.updateTransConsume(reqVO);
        // 校验是否更新正确
        TransConsumeDO transConsume = transConsumeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, transConsume);
    }

    @Test
    public void testUpdateTransConsume_notExists() {
        // 准备参数
        TransConsumeUpdateReqVO reqVO = randomPojo(TransConsumeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> transConsumeService.updateTransConsume(reqVO), TRANS_CONSUME_NOT_EXISTS);
    }

    @Test
    public void testDeleteTransConsume_success() {
        // mock 数据
        TransConsumeDO dbTransConsume = randomPojo(TransConsumeDO.class);
        transConsumeMapper.insert(dbTransConsume);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTransConsume.getId();

        // 调用
        transConsumeService.deleteTransConsume(id);
       // 校验数据不存在了
       assertNull(transConsumeMapper.selectById(id));
    }

    @Test
    public void testDeleteTransConsume_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> transConsumeService.deleteTransConsume(id), TRANS_CONSUME_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransConsumePage() {
       // mock 数据
       TransConsumeDO dbTransConsume = randomPojo(TransConsumeDO.class, o -> { // 等会查询到
           o.setTransOrderId(null);
           o.setTransOrderCode(null);
           o.setTaskId(null);
           o.setWorkstationId(null);
           o.setProcessId(null);
           o.setWorkorderId(null);
           o.setBatchCode(null);
           o.setSourceDocId(null);
           o.setSourceDocCode(null);
           o.setSourceDocType(null);
           o.setSourceLineId(null);
           o.setSourceBatchCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityConsumed(null);
           o.setConsumeDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transConsumeMapper.insert(dbTransConsume);
       // 测试 transOrderId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setTransOrderId(null)));
       // 测试 transOrderCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setTransOrderCode(null)));
       // 测试 taskId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setTaskId(null)));
       // 测试 workstationId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setWorkstationId(null)));
       // 测试 processId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setProcessId(null)));
       // 测试 workorderId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setWorkorderId(null)));
       // 测试 batchCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setBatchCode(null)));
       // 测试 sourceDocId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceDocId(null)));
       // 测试 sourceDocCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceDocCode(null)));
       // 测试 sourceDocType 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceDocType(null)));
       // 测试 sourceLineId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceLineId(null)));
       // 测试 sourceBatchCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceBatchCode(null)));
       // 测试 itemId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityConsumed 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setQuantityConsumed(null)));
       // 测试 consumeDate 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setConsumeDate(null)));
       // 测试 remark 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setCreateTime(null)));
       // 准备参数
       TransConsumePageReqVO reqVO = new TransConsumePageReqVO();
       reqVO.setTransOrderId(null);
       reqVO.setTransOrderCode(null);
       reqVO.setTaskId(null);
       reqVO.setWorkstationId(null);
       reqVO.setProcessId(null);
       reqVO.setWorkorderId(null);
       reqVO.setBatchCode(null);
       reqVO.setSourceDocId(null);
       reqVO.setSourceDocCode(null);
       reqVO.setSourceDocType(null);
       reqVO.setSourceLineId(null);
       reqVO.setSourceBatchCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityConsumed(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       PageResult<TransConsumeDO> pageResult = transConsumeService.getTransConsumePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTransConsume, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransConsumeList() {
       // mock 数据
       TransConsumeDO dbTransConsume = randomPojo(TransConsumeDO.class, o -> { // 等会查询到
           o.setTransOrderId(null);
           o.setTransOrderCode(null);
           o.setTaskId(null);
           o.setWorkstationId(null);
           o.setProcessId(null);
           o.setWorkorderId(null);
           o.setBatchCode(null);
           o.setSourceDocId(null);
           o.setSourceDocCode(null);
           o.setSourceDocType(null);
           o.setSourceLineId(null);
           o.setSourceBatchCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityConsumed(null);
           o.setConsumeDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transConsumeMapper.insert(dbTransConsume);
       // 测试 transOrderId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setTransOrderId(null)));
       // 测试 transOrderCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setTransOrderCode(null)));
       // 测试 taskId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setTaskId(null)));
       // 测试 workstationId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setWorkstationId(null)));
       // 测试 processId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setProcessId(null)));
       // 测试 workorderId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setWorkorderId(null)));
       // 测试 batchCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setBatchCode(null)));
       // 测试 sourceDocId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceDocId(null)));
       // 测试 sourceDocCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceDocCode(null)));
       // 测试 sourceDocType 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceDocType(null)));
       // 测试 sourceLineId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceLineId(null)));
       // 测试 sourceBatchCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSourceBatchCode(null)));
       // 测试 itemId 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityConsumed 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setQuantityConsumed(null)));
       // 测试 consumeDate 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setConsumeDate(null)));
       // 测试 remark 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transConsumeMapper.insert(cloneIgnoreId(dbTransConsume, o -> o.setCreateTime(null)));
       // 准备参数
       TransConsumeExportReqVO reqVO = new TransConsumeExportReqVO();
       reqVO.setTransOrderId(null);
       reqVO.setTransOrderCode(null);
       reqVO.setTaskId(null);
       reqVO.setWorkstationId(null);
       reqVO.setProcessId(null);
       reqVO.setWorkorderId(null);
       reqVO.setBatchCode(null);
       reqVO.setSourceDocId(null);
       reqVO.setSourceDocCode(null);
       reqVO.setSourceDocType(null);
       reqVO.setSourceLineId(null);
       reqVO.setSourceBatchCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityConsumed(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       List<TransConsumeDO> list = transConsumeService.getTransConsumeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTransConsume, list.get(0));
    }

}
