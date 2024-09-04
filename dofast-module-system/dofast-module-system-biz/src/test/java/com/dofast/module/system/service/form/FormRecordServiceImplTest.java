package com.dofast.module.system.service.form;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.system.controller.admin.form.vo.*;
import com.dofast.module.system.dal.dataobject.form.FormRecordDO;
import com.dofast.module.system.dal.mysql.form.FormRecordMapper;
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
 * {@link FormRecordServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(FormRecordServiceImpl.class)
public class FormRecordServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FormRecordServiceImpl formRecordService;

    @Resource
    private FormRecordMapper formRecordMapper;

    @Test
    public void testCreateFormRecord_success() {
        // 准备参数
        FormRecordCreateReqVO reqVO = randomPojo(FormRecordCreateReqVO.class);

        // 调用
        Long formRecordId = formRecordService.createFormRecord(reqVO);
        // 断言
        assertNotNull(formRecordId);
        // 校验记录的属性是否正确
        FormRecordDO formRecord = formRecordMapper.selectById(formRecordId);
        assertPojoEquals(reqVO, formRecord);
    }

    @Test
    public void testUpdateFormRecord_success() {
        // mock 数据
        FormRecordDO dbFormRecord = randomPojo(FormRecordDO.class);
        formRecordMapper.insert(dbFormRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FormRecordUpdateReqVO reqVO = randomPojo(FormRecordUpdateReqVO.class, o -> {
            o.setId(dbFormRecord.getId()); // 设置更新的 ID
        });

        // 调用
        formRecordService.updateFormRecord(reqVO);
        // 校验是否更新正确
        FormRecordDO formRecord = formRecordMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, formRecord);
    }

    @Test
    public void testUpdateFormRecord_notExists() {
        // 准备参数
        FormRecordUpdateReqVO reqVO = randomPojo(FormRecordUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> formRecordService.updateFormRecord(reqVO), FORM_RECORD_NOT_EXISTS);
    }

    @Test
    public void testDeleteFormRecord_success() {
        // mock 数据
        FormRecordDO dbFormRecord = randomPojo(FormRecordDO.class);
        formRecordMapper.insert(dbFormRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFormRecord.getId();

        // 调用
        formRecordService.deleteFormRecord(id);
       // 校验数据不存在了
       assertNull(formRecordMapper.selectById(id));
    }

    @Test
    public void testDeleteFormRecord_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> formRecordService.deleteFormRecord(id), FORM_RECORD_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFormRecordPage() {
       // mock 数据
       FormRecordDO dbFormRecord = randomPojo(FormRecordDO.class, o -> { // 等会查询到
           o.setFormId(null);
           o.setValue(null);
           o.setCreateTime(null);
       });
       formRecordMapper.insert(dbFormRecord);
       // 测试 formId 不匹配
       formRecordMapper.insert(cloneIgnoreId(dbFormRecord, o -> o.setFormId(null)));
       // 测试 value 不匹配
       formRecordMapper.insert(cloneIgnoreId(dbFormRecord, o -> o.setValue(null)));
       // 测试 createTime 不匹配
       formRecordMapper.insert(cloneIgnoreId(dbFormRecord, o -> o.setCreateTime(null)));
       // 准备参数
       FormRecordPageReqVO reqVO = new FormRecordPageReqVO();
       reqVO.setFormId(null);
       reqVO.setValue(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<FormRecordDO> pageResult = formRecordService.getFormRecordPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFormRecord, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFormRecordList() {
       // mock 数据
       FormRecordDO dbFormRecord = randomPojo(FormRecordDO.class, o -> { // 等会查询到
           o.setFormId(null);
           o.setValue(null);
           o.setCreateTime(null);
       });
       formRecordMapper.insert(dbFormRecord);
       // 测试 formId 不匹配
       formRecordMapper.insert(cloneIgnoreId(dbFormRecord, o -> o.setFormId(null)));
       // 测试 value 不匹配
       formRecordMapper.insert(cloneIgnoreId(dbFormRecord, o -> o.setValue(null)));
       // 测试 createTime 不匹配
       formRecordMapper.insert(cloneIgnoreId(dbFormRecord, o -> o.setCreateTime(null)));
       // 准备参数
       FormRecordExportReqVO reqVO = new FormRecordExportReqVO();
       reqVO.setFormId(null);
       reqVO.setValue(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<FormRecordDO> list = formRecordService.getFormRecordList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFormRecord, list.get(0));
    }

}
