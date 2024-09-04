package com.dofast.module.qms.service.templateproduct;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.templateproduct.vo.*;
import com.dofast.module.qms.dal.dataobject.templateproduct.TemplateProductDO;
import com.dofast.module.qms.dal.mysql.templateproduct.TemplateProductMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link TemplateProductServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TemplateProductServiceImpl.class)
public class TemplateProductServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TemplateProductServiceImpl templateProductService;

    @Resource
    private TemplateProductMapper templateProductMapper;

    @Test
    public void testCreateTemplateProduct_success() {
        // 准备参数
        TemplateProductCreateReqVO reqVO = randomPojo(TemplateProductCreateReqVO.class);

        // 调用
        Long templateProductId = templateProductService.createTemplateProduct(reqVO);
        // 断言
        assertNotNull(templateProductId);
        // 校验记录的属性是否正确
        TemplateProductDO templateProduct = templateProductMapper.selectById(templateProductId);
        assertPojoEquals(reqVO, templateProduct);
    }

    @Test
    public void testUpdateTemplateProduct_success() {
        // mock 数据
        TemplateProductDO dbTemplateProduct = randomPojo(TemplateProductDO.class);
        templateProductMapper.insert(dbTemplateProduct);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TemplateProductUpdateReqVO reqVO = randomPojo(TemplateProductUpdateReqVO.class, o -> {
            o.setId(dbTemplateProduct.getId()); // 设置更新的 ID
        });

        // 调用
        templateProductService.updateTemplateProduct(reqVO);
        // 校验是否更新正确
        TemplateProductDO templateProduct = templateProductMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, templateProduct);
    }

    @Test
    public void testUpdateTemplateProduct_notExists() {
        // 准备参数
        TemplateProductUpdateReqVO reqVO = randomPojo(TemplateProductUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> templateProductService.updateTemplateProduct(reqVO), TEMPLATE_PRODUCT_NOT_EXISTS);
    }

    @Test
    public void testDeleteTemplateProduct_success() {
        // mock 数据
        TemplateProductDO dbTemplateProduct = randomPojo(TemplateProductDO.class);
        templateProductMapper.insert(dbTemplateProduct);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTemplateProduct.getId();

        // 调用
        templateProductService.deleteTemplateProduct(id);
       // 校验数据不存在了
       assertNull(templateProductMapper.selectById(id));
    }

    @Test
    public void testDeleteTemplateProduct_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> templateProductService.deleteTemplateProduct(id), TEMPLATE_PRODUCT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTemplateProductPage() {
       // mock 数据
       TemplateProductDO dbTemplateProduct = randomPojo(TemplateProductDO.class, o -> { // 等会查询到
           o.setTemplateId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       templateProductMapper.insert(dbTemplateProduct);
       // 测试 templateId 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setTemplateId(null)));
       // 测试 itemId 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityCheck 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setQuantityUnqualified(null)));
       // 测试 crRate 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setMinRate(null)));
       // 测试 remark 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setCreateTime(null)));
       // 准备参数
       TemplateProductPageReqVO reqVO = new TemplateProductPageReqVO();
       reqVO.setTemplateId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TemplateProductDO> pageResult = templateProductService.getTemplateProductPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTemplateProduct, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTemplateProductList() {
       // mock 数据
       TemplateProductDO dbTemplateProduct = randomPojo(TemplateProductDO.class, o -> { // 等会查询到
           o.setTemplateId(null);
           o.setItemId(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setSpecification(null);
           o.setUnitOfMeasure(null);
           o.setQuantityCheck(null);
           o.setQuantityUnqualified(null);
           o.setCrRate(null);
           o.setMajRate(null);
           o.setMinRate(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       templateProductMapper.insert(dbTemplateProduct);
       // 测试 templateId 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setTemplateId(null)));
       // 测试 itemId 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setItemId(null)));
       // 测试 itemCode 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setItemName(null)));
       // 测试 specification 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setSpecification(null)));
       // 测试 unitOfMeasure 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setUnitOfMeasure(null)));
       // 测试 quantityCheck 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setQuantityCheck(null)));
       // 测试 quantityUnqualified 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setQuantityUnqualified(null)));
       // 测试 crRate 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setCrRate(null)));
       // 测试 majRate 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setMajRate(null)));
       // 测试 minRate 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setMinRate(null)));
       // 测试 remark 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       templateProductMapper.insert(cloneIgnoreId(dbTemplateProduct, o -> o.setCreateTime(null)));
       // 准备参数
       TemplateProductExportReqVO reqVO = new TemplateProductExportReqVO();
       reqVO.setTemplateId(null);
       reqVO.setItemId(null);
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setSpecification(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setQuantityCheck(null);
       reqVO.setQuantityUnqualified(null);
       reqVO.setCrRate(null);
       reqVO.setMajRate(null);
       reqVO.setMinRate(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TemplateProductDO> list = templateProductService.getTemplateProductList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTemplateProduct, list.get(0));
    }

}
