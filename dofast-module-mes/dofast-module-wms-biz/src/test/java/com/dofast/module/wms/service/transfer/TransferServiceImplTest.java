package com.dofast.module.wms.service.transfer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.transfer.vo.*;
import com.dofast.module.wms.dal.dataobject.transfer.TransferDO;
import com.dofast.module.wms.dal.mysql.transfer.TransferMapper;
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
 * {@link TransferServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TransferServiceImpl.class)
public class TransferServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TransferServiceImpl transferService;

    @Resource
    private TransferMapper transferMapper;

    @Test
    public void testCreateTransfer_success() {
        // 准备参数
        TransferCreateReqVO reqVO = randomPojo(TransferCreateReqVO.class);

        // 调用
        Long transferId = transferService.createTransfer(reqVO);
        // 断言
        assertNotNull(transferId);
        // 校验记录的属性是否正确
        TransferDO transfer = transferMapper.selectById(transferId);
        assertPojoEquals(reqVO, transfer);
    }

    @Test
    public void testUpdateTransfer_success() {
        // mock 数据
        TransferDO dbTransfer = randomPojo(TransferDO.class);
        transferMapper.insert(dbTransfer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TransferUpdateReqVO reqVO = randomPojo(TransferUpdateReqVO.class, o -> {
            o.setId(dbTransfer.getId()); // 设置更新的 ID
        });

        // 调用
        transferService.updateTransfer(reqVO);
        // 校验是否更新正确
        TransferDO transfer = transferMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, transfer);
    }

    @Test
    public void testUpdateTransfer_notExists() {
        // 准备参数
        TransferUpdateReqVO reqVO = randomPojo(TransferUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> transferService.updateTransfer(reqVO), TRANSFER_NOT_EXISTS);
    }

    @Test
    public void testDeleteTransfer_success() {
        // mock 数据
        TransferDO dbTransfer = randomPojo(TransferDO.class);
        transferMapper.insert(dbTransfer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTransfer.getId();

        // 调用
        transferService.deleteTransfer(id);
       // 校验数据不存在了
       assertNull(transferMapper.selectById(id));
    }

    @Test
    public void testDeleteTransfer_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> transferService.deleteTransfer(id), TRANSFER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransferPage() {
       // mock 数据
       TransferDO dbTransfer = randomPojo(TransferDO.class, o -> { // 等会查询到
           o.setTransferCode(null);
           o.setTransferName(null);
           o.setTransferType(null);
           o.setDestination(null);
           o.setCarrier(null);
           o.setBookingNote(null);
           o.setReceiver(null);
           o.setReceiverNick(null);
           o.setFromWarehouseId(null);
           o.setFromWarehouseCode(null);
           o.setFromWarehouseName(null);
           o.setToWarehouseId(null);
           o.setToWarehouseCode(null);
           o.setToWarehouseName(null);
           o.setTransferDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transferMapper.insert(dbTransfer);
       // 测试 transferCode 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferCode(null)));
       // 测试 transferName 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferName(null)));
       // 测试 transferType 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferType(null)));
       // 测试 destination 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setDestination(null)));
       // 测试 carrier 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setCarrier(null)));
       // 测试 bookingNote 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setBookingNote(null)));
       // 测试 receiver 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setReceiver(null)));
       // 测试 receiverNick 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setReceiverNick(null)));
       // 测试 fromWarehouseId 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setFromWarehouseId(null)));
       // 测试 fromWarehouseCode 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setFromWarehouseCode(null)));
       // 测试 fromWarehouseName 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setFromWarehouseName(null)));
       // 测试 toWarehouseId 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setToWarehouseId(null)));
       // 测试 toWarehouseCode 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setToWarehouseCode(null)));
       // 测试 toWarehouseName 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setToWarehouseName(null)));
       // 测试 transferDate 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferDate(null)));
       // 测试 status 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setCreateTime(null)));
       // 准备参数
       TransferPageReqVO reqVO = new TransferPageReqVO();
       reqVO.setTransferCode(null);
       reqVO.setTransferName(null);
       reqVO.setTransferType(null);
       reqVO.setDestination(null);
       reqVO.setCarrier(null);
       reqVO.setBookingNote(null);
       reqVO.setReceiver(null);
       reqVO.setReceiverNick(null);
       reqVO.setFromWarehouseId(null);
       reqVO.setFromWarehouseCode(null);
       reqVO.setFromWarehouseName(null);
       reqVO.setToWarehouseId(null);
       reqVO.setToWarehouseCode(null);
       reqVO.setToWarehouseName(null);
       reqVO.setTransferDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TransferDO> pageResult = transferService.getTransferPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTransfer, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransferList() {
       // mock 数据
       TransferDO dbTransfer = randomPojo(TransferDO.class, o -> { // 等会查询到
           o.setTransferCode(null);
           o.setTransferName(null);
           o.setTransferType(null);
           o.setDestination(null);
           o.setCarrier(null);
           o.setBookingNote(null);
           o.setReceiver(null);
           o.setReceiverNick(null);
           o.setFromWarehouseId(null);
           o.setFromWarehouseCode(null);
           o.setFromWarehouseName(null);
           o.setToWarehouseId(null);
           o.setToWarehouseCode(null);
           o.setToWarehouseName(null);
           o.setTransferDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transferMapper.insert(dbTransfer);
       // 测试 transferCode 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferCode(null)));
       // 测试 transferName 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferName(null)));
       // 测试 transferType 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferType(null)));
       // 测试 destination 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setDestination(null)));
       // 测试 carrier 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setCarrier(null)));
       // 测试 bookingNote 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setBookingNote(null)));
       // 测试 receiver 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setReceiver(null)));
       // 测试 receiverNick 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setReceiverNick(null)));
       // 测试 fromWarehouseId 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setFromWarehouseId(null)));
       // 测试 fromWarehouseCode 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setFromWarehouseCode(null)));
       // 测试 fromWarehouseName 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setFromWarehouseName(null)));
       // 测试 toWarehouseId 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setToWarehouseId(null)));
       // 测试 toWarehouseCode 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setToWarehouseCode(null)));
       // 测试 toWarehouseName 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setToWarehouseName(null)));
       // 测试 transferDate 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setTransferDate(null)));
       // 测试 status 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transferMapper.insert(cloneIgnoreId(dbTransfer, o -> o.setCreateTime(null)));
       // 准备参数
       TransferExportReqVO reqVO = new TransferExportReqVO();
       reqVO.setTransferCode(null);
       reqVO.setTransferName(null);
       reqVO.setTransferType(null);
       reqVO.setDestination(null);
       reqVO.setCarrier(null);
       reqVO.setBookingNote(null);
       reqVO.setReceiver(null);
       reqVO.setReceiverNick(null);
       reqVO.setFromWarehouseId(null);
       reqVO.setFromWarehouseCode(null);
       reqVO.setFromWarehouseName(null);
       reqVO.setToWarehouseId(null);
       reqVO.setToWarehouseCode(null);
       reqVO.setToWarehouseName(null);
       reqVO.setTransferDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TransferDO> list = transferService.getTransferList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTransfer, list.get(0));
    }

}
