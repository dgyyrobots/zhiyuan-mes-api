package com.dofast.module.iot.service.category;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.iot.controller.admin.category.vo.*;
import com.dofast.module.iot.dal.dataobject.category.CategoryDO;
import com.dofast.module.iot.dal.mysql.category.CategoryMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link CategoryServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CategoryServiceImpl.class)
public class CategoryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CategoryServiceImpl categoryService;

    @Resource
    private CategoryMapper categoryMapper;

    @Test
    public void testCreateCategory_success() {
        // 准备参数
        CategoryCreateReqVO reqVO = randomPojo(CategoryCreateReqVO.class);

        // 调用
        Long categoryId = categoryService.createCategory(reqVO);
        // 断言
        assertNotNull(categoryId);
        // 校验记录的属性是否正确
        CategoryDO category = categoryMapper.selectById(categoryId);
        assertPojoEquals(reqVO, category);
    }

    @Test
    public void testUpdateCategory_success() {
        /*// mock 数据
        CategoryDO dbCategory = randomPojo(CategoryDO.class);
        categoryMapper.insert(dbCategory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CategoryUpdateReqVO reqVO = randomPojo(CategoryUpdateReqVO.class, o -> {
            o.setId(dbCategory.getId()); // 设置更新的 ID
        });

        // 调用
        categoryService.updateCategory(reqVO);
        // 校验是否更新正确
        CategoryDO category = categoryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, category);*/
    }

    @Test
    public void testUpdateCategory_notExists() {
        // 准备参数
        CategoryUpdateReqVO reqVO = randomPojo(CategoryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> categoryService.updateCategory(reqVO), CATEGORY_NOT_EXISTS);
    }

    @Test
    public void testDeleteCategory_success() {
      /*  // mock 数据
        CategoryDO dbCategory = randomPojo(CategoryDO.class);
        categoryMapper.insert(dbCategory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCategory.getId();

        // 调用
        categoryService.deleteCategory(id);
       // 校验数据不存在了
       assertNull(categoryMapper.selectById(id));*/
    }

    @Test
    public void testDeleteCategory_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> categoryService.deleteCategory(id), CATEGORY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCategoryPage() {
       // mock 数据
       CategoryDO dbCategory = randomPojo(CategoryDO.class, o -> { // 等会查询到
           o.setCategoryName(null);
           o.setIsSys(null);
           o.setParentId(null);
           o.setOrderNum(null);
           o.setDelFlag(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       categoryMapper.insert(dbCategory);
       // 测试 categoryName 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setCategoryName(null)));
       // 测试 isSys 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setIsSys(null)));
       // 测试 parentId 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setParentId(null)));
       // 测试 orderNum 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setOrderNum(null)));
       // 测试 delFlag 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setDelFlag(null)));
       // 测试 remark 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setCreateTime(null)));
       // 准备参数
       CategoryPageReqVO reqVO = new CategoryPageReqVO();
       reqVO.setCategoryName(null);
       reqVO.setIsSys(null);
       reqVO.setParentId(null);
       reqVO.setOrderNum(null);
       reqVO.setDelFlag(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CategoryDO> pageResult = categoryService.getCategoryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCategory, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCategoryList() {
       // mock 数据
       CategoryDO dbCategory = randomPojo(CategoryDO.class, o -> { // 等会查询到
           o.setCategoryName(null);
           o.setIsSys(null);
           o.setParentId(null);
           o.setOrderNum(null);
           o.setDelFlag(null);
           o.setRemark(null);
           o.setCreateTime(null);
       });
       categoryMapper.insert(dbCategory);
       // 测试 categoryName 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setCategoryName(null)));
       // 测试 isSys 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setIsSys(null)));
       // 测试 parentId 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setParentId(null)));
       // 测试 orderNum 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setOrderNum(null)));
       // 测试 delFlag 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setDelFlag(null)));
       // 测试 remark 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setRemark(null)));
       // 测试 createTime 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setCreateTime(null)));
       // 准备参数
       CategoryExportReqVO reqVO = new CategoryExportReqVO();
       reqVO.setCategoryName(null);
       reqVO.setIsSys(null);
       reqVO.setParentId(null);
       reqVO.setOrderNum(null);
       reqVO.setDelFlag(null);
       reqVO.setRemark(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CategoryDO> list = categoryService.getCategoryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCategory, list.get(0));
    }

}
