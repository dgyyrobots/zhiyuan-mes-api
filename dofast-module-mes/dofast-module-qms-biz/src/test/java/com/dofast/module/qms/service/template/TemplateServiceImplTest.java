package com.dofast.module.qms.service.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.template.vo.*;
import com.dofast.module.qms.dal.dataobject.template.TemplateDO;
import com.dofast.module.qms.dal.mysql.template.TemplateMapper;
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
 * {@link TemplateServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TemplateServiceImpl.class)
public class TemplateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TemplateServiceImpl templateService;

    @Resource
    private TemplateMapper templateMapper;

    @Test
    public void testCreateTemplate_success() {
        // 准备参数
        TemplateCreateReqVO reqVO = randomPojo(TemplateCreateReqVO.class);

        // 调用
        Long templateId = templateService.createTemplate(reqVO);
        // 断言
        assertNotNull(templateId);
        // 校验记录的属性是否正确
        TemplateDO template = templateMapper.selectById(templateId);
        assertPojoEquals(reqVO, template);
    }

    @Test
    public void testUpdateTemplate_success() {
        // mock 数据
        TemplateDO dbTemplate = randomPojo(TemplateDO.class);
        templateMapper.insert(dbTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TemplateUpdateReqVO reqVO = randomPojo(TemplateUpdateReqVO.class, o -> {
            o.setId(dbTemplate.getId()); // 设置更新的 ID
        });

        // 调用
        templateService.updateTemplate(reqVO);
        // 校验是否更新正确
        TemplateDO template = templateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, template);
    }

    @Test
    public void testUpdateTemplate_notExists() {
        // 准备参数
        TemplateUpdateReqVO reqVO = randomPojo(TemplateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> templateService.updateTemplate(reqVO), TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteTemplate_success() {
        // mock 数据
        TemplateDO dbTemplate = randomPojo(TemplateDO.class);
        templateMapper.insert(dbTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTemplate.getId();

        // 调用
        templateService.deleteTemplate(id);
       // 校验数据不存在了
       assertNull(templateMapper.selectById(id));
    }

    @Test
    public void testDeleteTemplate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> templateService.deleteTemplate(id), TEMPLATE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTemplatePage() {
       // mock 数据
       TemplateDO dbTemplate = randomPojo(TemplateDO.class, o -> { // 等会查询到
           o.setTemplateCode(null);
           o.setTemplateName(null);
           o.setQcTypes(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       templateMapper.insert(dbTemplate);
       // 测试 templateCode 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setTemplateCode(null)));
       // 测试 templateName 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setTemplateName(null)));
       // 测试 qcTypes 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setQcTypes(null)));
       // 测试 enableFlag 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       TemplatePageReqVO reqVO = new TemplatePageReqVO();
       reqVO.setTemplateCode(null);
       reqVO.setTemplateName(null);
       reqVO.setQcTypes(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TemplateDO> pageResult = templateService.getTemplatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTemplate, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTemplateList() {
       // mock 数据
       TemplateDO dbTemplate = randomPojo(TemplateDO.class, o -> { // 等会查询到
           o.setTemplateCode(null);
           o.setTemplateName(null);
           o.setQcTypes(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       templateMapper.insert(dbTemplate);
       // 测试 templateCode 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setTemplateCode(null)));
       // 测试 templateName 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setTemplateName(null)));
       // 测试 qcTypes 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setQcTypes(null)));
       // 测试 enableFlag 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       templateMapper.insert(cloneIgnoreId(dbTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       TemplateExportReqVO reqVO = new TemplateExportReqVO();
       reqVO.setTemplateCode(null);
       reqVO.setTemplateName(null);
       reqVO.setQcTypes(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TemplateDO> list = templateService.getTemplateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTemplate, list.get(0));
    }

}
