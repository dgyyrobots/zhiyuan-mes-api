package com.dofast.module.pro.service.transorder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.transorder.vo.*;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import com.dofast.module.pro.dal.mysql.transorder.TransOrderMapper;
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
 * {@link TransOrderServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TransOrderServiceImpl.class)
public class TransOrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TransOrderServiceImpl transOrderService;

    @Resource
    private TransOrderMapper transOrderMapper;

    @Test
    public void testCreateTransOrder_success() {
        // 准备参数
        TransOrderCreateReqVO reqVO = randomPojo(TransOrderCreateReqVO.class);

        // 调用
        Long transOrderId = transOrderService.createTransOrder(reqVO);
        // 断言
        assertNotNull(transOrderId);
        // 校验记录的属性是否正确
        TransOrderDO transOrder = transOrderMapper.selectById(transOrderId);
        assertPojoEquals(reqVO, transOrder);
    }

    @Test
    public void testUpdateTransOrder_success() {
        // mock 数据
        TransOrderDO dbTransOrder = randomPojo(TransOrderDO.class);
        transOrderMapper.insert(dbTransOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TransOrderUpdateReqVO reqVO = randomPojo(TransOrderUpdateReqVO.class, o -> {
            o.setId(dbTransOrder.getId()); // 设置更新的 ID
        });

        // 调用
        transOrderService.updateTransOrder(reqVO);
        // 校验是否更新正确
        TransOrderDO transOrder = transOrderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, transOrder);
    }

    @Test
    public void testUpdateTransOrder_notExists() {
        // 准备参数
        TransOrderUpdateReqVO reqVO = randomPojo(TransOrderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> transOrderService.updateTransOrder(reqVO), TRANS_ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteTransOrder_success() {
        // mock 数据
        TransOrderDO dbTransOrder = randomPojo(TransOrderDO.class);
        transOrderMapper.insert(dbTransOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTransOrder.getId();

        // 调用
        transOrderService.deleteTransOrder(id);
       // 校验数据不存在了
       assertNull(transOrderMapper.selectById(id));
    }

    @Test
    public void testDeleteTransOrder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> transOrderService.deleteTransOrder(id), TRANS_ORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransOrderPage() {
       // mock 数据
       TransOrderDO dbTransOrder = randomPojo(TransOrderDO.class, o -> { // 等会查询到
           o.setTransOrderCode(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setBatchCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setBarcodeUrl(null);
           o.setQuantityTransfered(null);
           o.setProduceDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transOrderMapper.insert(dbTransOrder);
       // 测试 transOrderCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setTransOrderCode(null)));
       // 测试 taskId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setTaskCode(null)));
       // 测试 workstationId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProcessName(null)));
       // 测试 workorderId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkorderName(null)));
       // 测试 batchCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setBatchCode(null)));
       // 测试 itemId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setUnitOfMeasure(null)));
       // 测试 barcodeUrl 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setBarcodeUrl(null)));
       // 测试 quantityTransfered 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setQuantityTransfered(null)));
       // 测试 produceDate 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProduceDate(null)));
       // 测试 remark 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setCreateTime(null)));
       // 准备参数
       TransOrderPageReqVO reqVO = new TransOrderPageReqVO();
       reqVO.setTransOrderCode(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setBatchCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setBarcodeUrl(null);
       reqVO.setQuantityTransfered(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       PageResult<TransOrderDO> pageResult = transOrderService.getTransOrderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTransOrder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransOrderList() {
       // mock 数据
       TransOrderDO dbTransOrder = randomPojo(TransOrderDO.class, o -> { // 等会查询到
           o.setTransOrderCode(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWorkorderName(null);
           o.setBatchCode(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setBarcodeUrl(null);
           o.setQuantityTransfered(null);
           o.setProduceDate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transOrderMapper.insert(dbTransOrder);
       // 测试 transOrderCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setTransOrderCode(null)));
       // 测试 taskId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setTaskCode(null)));
       // 测试 workstationId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProcessName(null)));
       // 测试 workorderId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setWorkorderName(null)));
       // 测试 batchCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setBatchCode(null)));
       // 测试 itemId 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setUnitOfMeasure(null)));
       // 测试 barcodeUrl 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setBarcodeUrl(null)));
       // 测试 quantityTransfered 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setQuantityTransfered(null)));
       // 测试 produceDate 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setProduceDate(null)));
       // 测试 remark 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transOrderMapper.insert(cloneIgnoreId(dbTransOrder, o -> o.setCreateTime(null)));
       // 准备参数
       TransOrderExportReqVO reqVO = new TransOrderExportReqVO();
       reqVO.setTransOrderCode(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWorkorderName(null);
       reqVO.setBatchCode(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setBarcodeUrl(null);
       reqVO.setQuantityTransfered(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);

       // 调用
       List<TransOrderDO> list = transOrderService.getTransOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTransOrder, list.get(0));
    }

}
