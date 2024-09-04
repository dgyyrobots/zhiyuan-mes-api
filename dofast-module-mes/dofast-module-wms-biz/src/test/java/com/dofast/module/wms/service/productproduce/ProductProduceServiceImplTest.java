package com.dofast.module.wms.service.productproduce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.productproduce.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;
import com.dofast.module.wms.dal.mysql.productproduce.ProductProduceMapper;
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
 * {@link ProductProduceServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ProductProduceServiceImpl.class)
public class ProductProduceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProductProduceServiceImpl productProduceService;

    @Resource
    private ProductProduceMapper productProduceMapper;

    @Test
    public void testCreateProductProduce_success() {
        // 准备参数
        ProductProduceCreateReqVO reqVO = randomPojo(ProductProduceCreateReqVO.class);

        // 调用
        Long productProduceId = productProduceService.createProductProduce(reqVO);
        // 断言
        assertNotNull(productProduceId);
        // 校验记录的属性是否正确
        ProductProduceDO productProduce = productProduceMapper.selectById(productProduceId);
        assertPojoEquals(reqVO, productProduce);
    }

    @Test
    public void testUpdateProductProduce_success() {
        // mock 数据
        ProductProduceDO dbProductProduce = randomPojo(ProductProduceDO.class);
        productProduceMapper.insert(dbProductProduce);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProductProduceUpdateReqVO reqVO = randomPojo(ProductProduceUpdateReqVO.class, o -> {
            o.setId(dbProductProduce.getId()); // 设置更新的 ID
        });

        // 调用
        productProduceService.updateProductProduce(reqVO);
        // 校验是否更新正确
        ProductProduceDO productProduce = productProduceMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, productProduce);
    }

    @Test
    public void testUpdateProductProduce_notExists() {
        // 准备参数
        ProductProduceUpdateReqVO reqVO = randomPojo(ProductProduceUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> productProduceService.updateProductProduce(reqVO), PRODUCT_PRODUCE_NOT_EXISTS);
    }

    @Test
    public void testDeleteProductProduce_success() {
        // mock 数据
        ProductProduceDO dbProductProduce = randomPojo(ProductProduceDO.class);
        productProduceMapper.insert(dbProductProduce);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProductProduce.getId();

        // 调用
        productProduceService.deleteProductProduce(id);
       // 校验数据不存在了
       assertNull(productProduceMapper.selectById(id));
    }

    @Test
    public void testDeleteProductProduce_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> productProduceService.deleteProductProduce(id), PRODUCT_PRODUCE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductProducePage() {
       // mock 数据
       ProductProduceDO dbProductProduce = randomPojo(ProductProduceDO.class, o -> { // 等会查询到
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
           o.setProduceDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       productProduceMapper.insert(dbProductProduce);
       // 测试 workorderId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkorderName(null)));
       // 测试 taskId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setTaskName(null)));
       // 测试 workstationId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProcessName(null)));
       // 测试 produceDate 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProduceDate(null)));
       // 测试 status 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setCreateTime(null)));
       // 准备参数
       ProductProducePageReqVO reqVO = new ProductProducePageReqVO();
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
       reqVO.setProduceDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ProductProduceDO> pageResult = productProduceService.getProductProducePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProductProduce, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProductProduceList() {
       // mock 数据
       ProductProduceDO dbProductProduce = randomPojo(ProductProduceDO.class, o -> { // 等会查询到
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
           o.setProduceDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       productProduceMapper.insert(dbProductProduce);
       // 测试 workorderId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkorderCode(null)));
       // 测试 workorderName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkorderName(null)));
       // 测试 taskId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setTaskCode(null)));
       // 测试 taskName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setTaskName(null)));
       // 测试 workstationId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setWorkstationName(null)));
       // 测试 processId 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProcessName(null)));
       // 测试 produceDate 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setProduceDate(null)));
       // 测试 status 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       productProduceMapper.insert(cloneIgnoreId(dbProductProduce, o -> o.setCreateTime(null)));
       // 准备参数
       ProductProduceExportReqVO reqVO = new ProductProduceExportReqVO();
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
       reqVO.setProduceDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ProductProduceDO> list = productProduceService.getProductProduceList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbProductProduce, list.get(0));
    }

}
