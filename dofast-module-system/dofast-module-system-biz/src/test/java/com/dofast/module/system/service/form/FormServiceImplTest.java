package com.dofast.module.system.service.form;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormDO;
import com.dofast.module.system.dal.mysql.form.FormAliasMapper;
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
 * {@link FormServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FormServiceImpl.class)
public class FormServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FormServiceImpl formService;

    @Resource
    private FormAliasMapper formAliasMapper;

    @Test
    public void testCreateForm_success() {
        // 准备参数
        FormCreateReqVO reqVO = randomPojo(FormCreateReqVO.class);

        // 调用
        Integer formId = formService.createForm(reqVO);
        // 断言
        assertNotNull(formId);
        // 校验记录的属性是否正确
        FormDO form = formAliasMapper.selectById(formId);
        assertPojoEquals(reqVO, form);
    }

    @Test
    public void testUpdateForm_success() {
        // mock 数据
        FormDO dbForm = randomPojo(FormDO.class);
        formAliasMapper.insert(dbForm);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FormUpdateReqVO reqVO = randomPojo(FormUpdateReqVO.class, o -> {
            o.setId(dbForm.getId()); // 设置更新的 ID
        });

        // 调用
        formService.updateForm(reqVO);
        // 校验是否更新正确
        FormDO form = formAliasMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, form);
    }

    @Test
    public void testUpdateForm_notExists() {
        // 准备参数
        FormUpdateReqVO reqVO = randomPojo(FormUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> formService.updateForm(reqVO), FORM_NOT_EXISTS);
    }

    @Test
    public void testDeleteForm_success() {
        // mock 数据
        FormDO dbForm = randomPojo(FormDO.class);
        formAliasMapper.insert(dbForm);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbForm.getId();

        // 调用
        formService.deleteForm(id);
       // 校验数据不存在了
       assertNull(formAliasMapper.selectById(id));
    }

    /*@Test
    public void testDeleteForm_notExists() {
        // 准备参数
        Integer id = randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> formService.deleteForm(id), FORM_NOT_EXISTS);
    }*/

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFormPage() {
       // mock 数据
       FormDO dbForm = randomPojo(FormDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setType(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       formAliasMapper.insert(dbForm);
       // 测试 name 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setName(null)));
       // 测试 type 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setType(null)));
       // 测试 status 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setCreateTime(null)));
       // 准备参数
       FormPageReqVO reqVO = new FormPageReqVO();
       reqVO.setName(null);
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<FormDO> pageResult = formService.getFormPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbForm, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFormList() {
       // mock 数据
       FormDO dbForm = randomPojo(FormDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setType(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       formAliasMapper.insert(dbForm);
       // 测试 name 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setName(null)));
       // 测试 type 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setType(null)));
       // 测试 status 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       formAliasMapper.insert(cloneIgnoreId(dbForm, o -> o.setCreateTime(null)));
       // 准备参数
       FormExportReqVO reqVO = new FormExportReqVO();
       reqVO.setName(null);
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<FormDO> list = formService.getFormList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbForm, list.get(0));
    }

}
