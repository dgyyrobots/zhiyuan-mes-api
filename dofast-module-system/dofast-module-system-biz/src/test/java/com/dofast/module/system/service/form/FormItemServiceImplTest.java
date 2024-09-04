package com.dofast.module.system.service.form;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormItemDO;
import com.dofast.module.system.dal.mysql.form.FormItemMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link FormItemServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FormItemServiceImpl.class)
public class FormItemServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FormItemServiceImpl formItemService;

    @Resource
    private FormItemMapper formItemMapper;

    @Test
    public void testCreateFormItem_success() {
        // 准备参数
        FormItemCreateReqVO reqVO = randomPojo(FormItemCreateReqVO.class);

        // 调用
        Integer formItemId = formItemService.createFormItem(reqVO);
        // 断言
        assertNotNull(formItemId);
        // 校验记录的属性是否正确
        FormItemDO formItem = formItemMapper.selectById(formItemId);
        assertPojoEquals(reqVO, formItem);
    }

    @Test
    public void testUpdateFormItem_success() {
        // mock 数据
        FormItemDO dbFormItem = randomPojo(FormItemDO.class);
        formItemMapper.insert(dbFormItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FormItemUpdateReqVO reqVO = randomPojo(FormItemUpdateReqVO.class, o -> {
            o.setId(dbFormItem.getId()); // 设置更新的 ID
        });

        // 调用
        formItemService.updateFormItem(reqVO);
        // 校验是否更新正确
        FormItemDO formItem = formItemMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, formItem);
    }

    @Test
    public void testUpdateFormItem_notExists() {
        // 准备参数
        FormItemUpdateReqVO reqVO = randomPojo(FormItemUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> formItemService.updateFormItem(reqVO), FORM_ITEM_NOT_EXISTS);
    }

    @Test
    public void testDeleteFormItem_success() {
        // mock 数据
        FormItemDO dbFormItem = randomPojo(FormItemDO.class);
        formItemMapper.insert(dbFormItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbFormItem.getId();

        // 调用
        formItemService.deleteFormItem(id);
       // 校验数据不存在了
       assertNull(formItemMapper.selectById(id));
    }

    /*@Test
    public void testDeleteFormItem_notExists() {
        // 准备参数
        Integer id = randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> formItemService.deleteFormItem(id), FORM_ITEM_NOT_EXISTS);
    }*/

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFormItemPage() {
       // mock 数据
       FormItemDO dbFormItem = randomPojo(FormItemDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setField(null);
           o.setValidator(null);
           o.setType(null);
           o.setCreateTime(null);
       });
       formItemMapper.insert(dbFormItem);
       // 测试 name 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setName(null)));
       // 测试 field 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setField(null)));
       // 测试 validator 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setValidator(null)));
       // 测试 type 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setType(null)));
       // 测试 createTime 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setCreateTime(null)));
       // 准备参数
       FormItemPageReqVO reqVO = new FormItemPageReqVO();
       reqVO.setName(null);
       reqVO.setField(null);
       reqVO.setValidator(null);
       reqVO.setType(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<FormItemDO> pageResult = formItemService.getFormItemPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFormItem, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFormItemList() {
       // mock 数据
       FormItemDO dbFormItem = randomPojo(FormItemDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setField(null);
           o.setValidator(null);
           o.setType(null);
           o.setCreateTime(null);
       });
       formItemMapper.insert(dbFormItem);
       // 测试 name 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setName(null)));
       // 测试 field 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setField(null)));
       // 测试 validator 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setValidator(null)));
       // 测试 type 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setType(null)));
       // 测试 createTime 不匹配
       formItemMapper.insert(cloneIgnoreId(dbFormItem, o -> o.setCreateTime(null)));
       // 准备参数
       FormItemExportReqVO reqVO = new FormItemExportReqVO();
       reqVO.setName(null);
       reqVO.setField(null);
       reqVO.setValidator(null);
       reqVO.setType(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<FormItemDO> list = formItemService.getFormItemList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFormItem, list.get(0));
    }

}
