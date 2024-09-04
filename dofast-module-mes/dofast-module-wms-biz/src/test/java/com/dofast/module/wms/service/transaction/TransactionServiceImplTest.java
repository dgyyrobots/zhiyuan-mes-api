package com.dofast.module.wms.service.transaction;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.transaction.vo.*;
import com.dofast.module.wms.dal.dataobject.transaction.TransactionDO;
import com.dofast.module.wms.dal.mysql.transaction.TransactionMapper;
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
 * {@link TransactionServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TransactionServiceImpl.class)
public class TransactionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TransactionServiceImpl transactionService;

    @Resource
    private TransactionMapper transactionMapper;

    @Test
    public void testCreateTransaction_success() {
        // 准备参数
        TransactionCreateReqVO reqVO = randomPojo(TransactionCreateReqVO.class);

        // 调用
        Long transactionId = transactionService.createTransaction(reqVO);
        // 断言
        assertNotNull(transactionId);
        // 校验记录的属性是否正确
        TransactionDO transaction = transactionMapper.selectById(transactionId);
        assertPojoEquals(reqVO, transaction);
    }

    @Test
    public void testUpdateTransaction_success() {
        // mock 数据
        TransactionDO dbTransaction = randomPojo(TransactionDO.class);
        transactionMapper.insert(dbTransaction);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TransactionUpdateReqVO reqVO = randomPojo(TransactionUpdateReqVO.class, o -> {
            o.setId(dbTransaction.getId()); // 设置更新的 ID
        });

        // 调用
        transactionService.updateTransaction(reqVO);
        // 校验是否更新正确
        TransactionDO transaction = transactionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, transaction);
    }

    @Test
    public void testUpdateTransaction_notExists() {
        // 准备参数
        TransactionUpdateReqVO reqVO = randomPojo(TransactionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> transactionService.updateTransaction(reqVO), TRANSACTION_NOT_EXISTS);
    }

    @Test
    public void testDeleteTransaction_success() {
        // mock 数据
        TransactionDO dbTransaction = randomPojo(TransactionDO.class);
        transactionMapper.insert(dbTransaction);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTransaction.getId();

        // 调用
        transactionService.deleteTransaction(id);
       // 校验数据不存在了
       assertNull(transactionMapper.selectById(id));
    }

    @Test
    public void testDeleteTransaction_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> transactionService.deleteTransaction(id), TRANSACTION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransactionPage() {
       // mock 数据
       TransactionDO dbTransaction = randomPojo(TransactionDO.class, o -> { // 等会查询到
           o.setTransactionType(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setBatchCode(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setSourceDocType(null);
           o.setSourceDocId(null);
           o.setSourceDocCode(null);
           o.setSourceDocLineId(null);
           o.setMaterialStockId(null);
           o.setTransactionFlag(null);
           o.setTransactionQuantity(null);
           o.setTransactionDate(null);
           o.setRelatedTransactionId(null);
           o.setErpDate(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setRecptDate(null);
           o.setExpireDate(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transactionMapper.insert(dbTransaction);
       // 测试 transactionType 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionType(null)));
       // 测试 itemId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setUnitOfMeasure(null)));
       // 测试 batchCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAreaName(null)));
       // 测试 vendorId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorNick(null)));
       // 测试 sourceDocType 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocType(null)));
       // 测试 sourceDocId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocId(null)));
       // 测试 sourceDocCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocCode(null)));
       // 测试 sourceDocLineId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocLineId(null)));
       // 测试 materialStockId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setMaterialStockId(null)));
       // 测试 transactionFlag 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionFlag(null)));
       // 测试 transactionQuantity 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionQuantity(null)));
       // 测试 transactionDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionDate(null)));
       // 测试 relatedTransactionId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setRelatedTransactionId(null)));
       // 测试 erpDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setErpDate(null)));
       // 测试 workorderId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWorkorderCode(null)));
       // 测试 recptDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setRecptDate(null)));
       // 测试 expireDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setExpireDate(null)));
       // 测试 attr1 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setCreateTime(null)));
       // 准备参数
       TransactionPageReqVO reqVO = new TransactionPageReqVO();
       reqVO.setTransactionType(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setBatchCode(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setSourceDocType(null);
       reqVO.setSourceDocId(null);
       reqVO.setSourceDocCode(null);
       reqVO.setSourceDocLineId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setTransactionFlag(null);
       reqVO.setTransactionQuantity(null);
       reqVO.setTransactionDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRelatedTransactionId(null);
       reqVO.setErpDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setRecptDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TransactionDO> pageResult = transactionService.getTransactionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTransaction, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTransactionList() {
       // mock 数据
       TransactionDO dbTransaction = randomPojo(TransactionDO.class, o -> { // 等会查询到
           o.setTransactionType(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setBatchCode(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setVendorId(null);
           o.setVendorCode(null);
           o.setVendorName(null);
           o.setVendorNick(null);
           o.setSourceDocType(null);
           o.setSourceDocId(null);
           o.setSourceDocCode(null);
           o.setSourceDocLineId(null);
           o.setMaterialStockId(null);
           o.setTransactionFlag(null);
           o.setTransactionQuantity(null);
           o.setTransactionDate(null);
           o.setRelatedTransactionId(null);
           o.setErpDate(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setRecptDate(null);
           o.setExpireDate(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       transactionMapper.insert(dbTransaction);
       // 测试 transactionType 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionType(null)));
       // 测试 itemId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setUnitOfMeasure(null)));
       // 测试 batchCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setBatchCode(null)));
       // 测试 warehouseId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAreaName(null)));
       // 测试 vendorId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorId(null)));
       // 测试 vendorCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorCode(null)));
       // 测试 vendorName 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorName(null)));
       // 测试 vendorNick 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setVendorNick(null)));
       // 测试 sourceDocType 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocType(null)));
       // 测试 sourceDocId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocId(null)));
       // 测试 sourceDocCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocCode(null)));
       // 测试 sourceDocLineId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setSourceDocLineId(null)));
       // 测试 materialStockId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setMaterialStockId(null)));
       // 测试 transactionFlag 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionFlag(null)));
       // 测试 transactionQuantity 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionQuantity(null)));
       // 测试 transactionDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setTransactionDate(null)));
       // 测试 relatedTransactionId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setRelatedTransactionId(null)));
       // 测试 erpDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setErpDate(null)));
       // 测试 workorderId 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setWorkorderCode(null)));
       // 测试 recptDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setRecptDate(null)));
       // 测试 expireDate 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setExpireDate(null)));
       // 测试 attr1 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       transactionMapper.insert(cloneIgnoreId(dbTransaction, o -> o.setCreateTime(null)));
       // 准备参数
       TransactionExportReqVO reqVO = new TransactionExportReqVO();
       reqVO.setTransactionType(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setBatchCode(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setVendorId(null);
       reqVO.setVendorCode(null);
       reqVO.setVendorName(null);
       reqVO.setVendorNick(null);
       reqVO.setSourceDocType(null);
       reqVO.setSourceDocId(null);
       reqVO.setSourceDocCode(null);
       reqVO.setSourceDocLineId(null);
       reqVO.setMaterialStockId(null);
       reqVO.setTransactionFlag(null);
       reqVO.setTransactionQuantity(null);
       reqVO.setTransactionDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRelatedTransactionId(null);
       reqVO.setErpDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setRecptDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setExpireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TransactionDO> list = transactionService.getTransactionList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTransaction, list.get(0));
    }

}
