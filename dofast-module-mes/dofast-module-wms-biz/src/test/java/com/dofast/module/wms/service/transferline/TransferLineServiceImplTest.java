package com.dofast.module.wms.service.transferline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.transferline.vo.*;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;
import com.dofast.module.wms.dal.mysql.transferline.TransferLineMapper;
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
 * {@link TransferLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TransferLineServiceImpl.class)
public class TransferLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TransferLineServiceImpl transferLineService;

    @Resource
    private TransferLineMapper transferLineMapper;

    @Test
    public void testCreateTransferLine_success() {
        // 准备参数
        TransferLineCreateReqVO reqVO = randomPojo(TransferLineCreateReqVO.class);

        // 调用
        Long transferLineId = transferLineService.createTransferLine(reqVO);
        // 断言
        assertNotNull(transferLineId);
        // 校验记录的属性是否正确
        TransferLineDO transferLine = transferLineMapper.selectById(transferLineId);
        assertPojoEquals(reqVO, transferLine);
    }

    @Test
    public void testUpdateTransferLine_success() {
        // mock 数据
        TransferLineDO dbTransferLine = randomPojo(TransferLineDO.class);
        transferLineMapper.insert(dbTransferLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TransferLineUpdateReqVO reqVO = randomPojo(TransferLineUpdateReqVO.class, o -> {
            o.setId(dbTransferLine.getId()); // 设置更新的 ID
        });

        // 调用
        transferLineService.updateTransferLine(reqVO);
        // 校验是否更新正确
        TransferLineDO transferLine = transferLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, transferLine);
    }

    @Test
    public void testUpdateTransferLine_notExists() {
        // 准备参数
        TransferLineUpdateReqVO reqVO = randomPojo(TransferLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> transferLineService.updateTransferLine(reqVO), TRANSFER_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteTransferLine_success() {
        // mock 数据
        TransferLineDO dbTransferLine = randomPojo(TransferLineDO.class);
        transferLineMapper.insert(dbTransferLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTransferLine.getId();

        // 调用
        transferLineService.deleteTransferLine(id);
       // 校验数据不存在了
       assertNull(transferLineMapper.selectById(id));
    }

    @Test
    public void testDeleteTransferLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> transferLineService.deleteTransferLine(id), TRANSFER_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransferLinePage() {
       // mock 数据
       TransferLineDO dbTransferLine = randomPojo(TransferLineDO.class, o -> { // 等会查询到
           o.setTransferId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityTransfer(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setBatchCode(null);
           o.setFromWarehouseId(null);
           o.setFromWarehouseCode(null);
           o.setFromWarehouseName(null);
           o.setFromLocationId(null);
           o.setFromLocationCode(null);
           o.setFromLocationName(null);
           o.setFromAreaId(null);
           o.setFromAreaCode(null);
           o.setFromAreaName(null);
           o.setToWarehouseId(null);
           o.setToWarehouseCode(null);
           o.setToWarehouseName(null);
           o.setToLocationId(null);
           o.setToLocationCode(null);
           o.setToLocationName(null);
           o.setToAreaId(null);
           o.setToAreaCode(null);
           o.setToAreaName(null);
           o.setExpireDate(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transferLineMapper.insert(dbTransferLine);
       // 测试 transferId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setTransferId(null)));
       // 测试 materialStockId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityTransfer 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setQuantityTransfer(null)));
       // 测试 workorderId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setWorkorderCode(null)));
       // 测试 batchCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setBatchCode(null)));
       // 测试 fromWarehouseId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromWarehouseId(null)));
       // 测试 fromWarehouseCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromWarehouseCode(null)));
       // 测试 fromWarehouseName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromWarehouseName(null)));
       // 测试 fromLocationId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromLocationId(null)));
       // 测试 fromLocationCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromLocationCode(null)));
       // 测试 fromLocationName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromLocationName(null)));
       // 测试 fromAreaId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromAreaId(null)));
       // 测试 fromAreaCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromAreaCode(null)));
       // 测试 fromAreaName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromAreaName(null)));
       // 测试 toWarehouseId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToWarehouseId(null)));
       // 测试 toWarehouseCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToWarehouseCode(null)));
       // 测试 toWarehouseName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToWarehouseName(null)));
       // 测试 toLocationId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToLocationId(null)));
       // 测试 toLocationCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToLocationCode(null)));
       // 测试 toLocationName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToLocationName(null)));
       // 测试 toAreaId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToAreaId(null)));
       // 测试 toAreaCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToAreaCode(null)));
       // 测试 toAreaName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToAreaName(null)));
       // 测试 expireDate 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setExpireDate(null)));
       // 测试 vendorId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorNick(null)));
       // 测试 remark 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setCreateTime(null)));
       // 准备参数
       TransferLinePageReqVO reqVO = new TransferLinePageReqVO();
       reqVO.setTransferId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityTransfer(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setBatchCode(null);
       reqVO.setFromWarehouseId(null);
       reqVO.setFromWarehouseCode(null);
       reqVO.setFromWarehouseName(null);
       reqVO.setFromLocationId(null);
       reqVO.setFromLocationCode(null);
       reqVO.setFromLocationName(null);
       reqVO.setFromAreaId(null);
       reqVO.setFromAreaCode(null);
       reqVO.setFromAreaName(null);
       reqVO.setToWarehouseId(null);
       reqVO.setToWarehouseCode(null);
       reqVO.setToWarehouseName(null);
       reqVO.setToLocationId(null);
       reqVO.setToLocationCode(null);
       reqVO.setToLocationName(null);
       reqVO.setToAreaId(null);
       reqVO.setToAreaCode(null);
       reqVO.setToAreaName(null);
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TransferLineDO> pageResult = transferLineService.getTransferLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTransferLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransferLineList() {
       // mock 数据
       TransferLineDO dbTransferLine = randomPojo(TransferLineDO.class, o -> { // 等会查询到
           o.setTransferId(null);
           o.setMaterialStockId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityTransfer(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setBatchCode(null);
           o.setFromWarehouseId(null);
           o.setFromWarehouseCode(null);
           o.setFromWarehouseName(null);
           o.setFromLocationId(null);
           o.setFromLocationCode(null);
           o.setFromLocationName(null);
           o.setFromAreaId(null);
           o.setFromAreaCode(null);
           o.setFromAreaName(null);
           o.setToWarehouseId(null);
           o.setToWarehouseCode(null);
           o.setToWarehouseName(null);
           o.setToLocationId(null);
           o.setToLocationCode(null);
           o.setToLocationName(null);
           o.setToAreaId(null);
           o.setToAreaCode(null);
           o.setToAreaName(null);
           o.setExpireDate(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transferLineMapper.insert(dbTransferLine);
       // 测试 transferId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setTransferId(null)));
       // 测试 materialStockId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setMaterialStockId(null)));
       // 测试 itemId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityTransfer 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setQuantityTransfer(null)));
       // 测试 workorderId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setWorkorderCode(null)));
       // 测试 batchCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setBatchCode(null)));
       // 测试 fromWarehouseId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromWarehouseId(null)));
       // 测试 fromWarehouseCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromWarehouseCode(null)));
       // 测试 fromWarehouseName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromWarehouseName(null)));
       // 测试 fromLocationId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromLocationId(null)));
       // 测试 fromLocationCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromLocationCode(null)));
       // 测试 fromLocationName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromLocationName(null)));
       // 测试 fromAreaId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromAreaId(null)));
       // 测试 fromAreaCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromAreaCode(null)));
       // 测试 fromAreaName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setFromAreaName(null)));
       // 测试 toWarehouseId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToWarehouseId(null)));
       // 测试 toWarehouseCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToWarehouseCode(null)));
       // 测试 toWarehouseName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToWarehouseName(null)));
       // 测试 toLocationId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToLocationId(null)));
       // 测试 toLocationCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToLocationCode(null)));
       // 测试 toLocationName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToLocationName(null)));
       // 测试 toAreaId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToAreaId(null)));
       // 测试 toAreaCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToAreaCode(null)));
       // 测试 toAreaName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setToAreaName(null)));
       // 测试 expireDate 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setExpireDate(null)));
       // 测试 vendorId 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setVendorNick(null)));
       // 测试 remark 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transferLineMapper.insert(cloneIgnoreId(dbTransferLine, o -> o.setCreateTime(null)));
       // 准备参数
       TransferLineExportReqVO reqVO = new TransferLineExportReqVO();
       reqVO.setTransferId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityTransfer(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setBatchCode(null);
       reqVO.setFromWarehouseId(null);
       reqVO.setFromWarehouseCode(null);
       reqVO.setFromWarehouseName(null);
       reqVO.setFromLocationId(null);
       reqVO.setFromLocationCode(null);
       reqVO.setFromLocationName(null);
       reqVO.setFromAreaId(null);
       reqVO.setFromAreaCode(null);
       reqVO.setFromAreaName(null);
       reqVO.setToWarehouseId(null);
       reqVO.setToWarehouseCode(null);
       reqVO.setToWarehouseName(null);
       reqVO.setToLocationId(null);
       reqVO.setToLocationCode(null);
       reqVO.setToLocationName(null);
       reqVO.setToAreaId(null);
       reqVO.setToAreaCode(null);
       reqVO.setToAreaName(null);
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TransferLineDO> list = transferLineService.getTransferLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTransferLine, list.get(0));
    }

}
