package com.dofast.module.system.service.mail;

import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.system.controller.admin.mail.vo.template.MailTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.mail.vo.template.MailTemplatePageReqVO;
import com.dofast.module.system.controller.admin.mail.vo.template.MailTemplateUpdateReqVO;
import com.dofast.module.system.dal.dataobject.mail.MailTemplateDO;
import com.dofast.module.system.dal.mysql.mail.MailTemplateMapper;
import com.dofast.module.system.mq.producer.mail.MailProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.buildTime;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomLongId;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.module.system.enums.ErrorCodeConstants.MAIL_TEMPLATE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
* {@link MailTemplateServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(MailTemplateServiceImpl.class)
public class MailTemplateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MailTemplateServiceImpl mailTemplateService;

    @Resource
    private MailTemplateMapper mailTemplateMapper;

    @MockBean
    private MailProducer mailProducer;

    @Test
    public void testInitLocalCache() {
        MailTemplateDO templateDO01 = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(templateDO01);
        MailTemplateDO templateDO02 = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(templateDO02);

        // 调用
        mailTemplateService.initLocalCache();
        // 断言 mailTemplateCache 缓存
        Map<String, MailTemplateDO> mailTemplateCache = mailTemplateService.getMailTemplateCache();
        assertPojoEquals(templateDO01, mailTemplateCache.get(templateDO01.getCode()));
        assertPojoEquals(templateDO02, mailTemplateCache.get(templateDO02.getCode()));
    }

    @Test
    public void testCreateMailTemplate_success() {
        // 准备参数
        MailTemplateCreateReqVO reqVO = randomPojo(MailTemplateCreateReqVO.class);

        // 调用
        Long mailTemplateId = mailTemplateService.createMailTemplate(reqVO);
        // 断言
        assertNotNull(mailTemplateId);
        // 校验记录的属性是否正确
        MailTemplateDO mailTemplate = mailTemplateMapper.selectById(mailTemplateId);
        assertPojoEquals(reqVO, mailTemplate);
        verify(mailProducer).sendMailTemplateRefreshMessage();
    }

    @Test
    public void testUpdateMailTemplate_success() {
        // mock 数据
        MailTemplateDO dbMailTemplate = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MailTemplateUpdateReqVO reqVO = randomPojo(MailTemplateUpdateReqVO.class, o -> {
            o.setId(dbMailTemplate.getId()); // 设置更新的 ID
        });

        // 调用
        mailTemplateService.updateMailTemplate(reqVO);
        // 校验是否更新正确
        MailTemplateDO mailTemplate = mailTemplateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mailTemplate);
        verify(mailProducer).sendMailTemplateRefreshMessage();
    }

    @Test
    public void testUpdateMailTemplate_notExists() {
        // 准备参数
        MailTemplateUpdateReqVO reqVO = randomPojo(MailTemplateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mailTemplateService.updateMailTemplate(reqVO), MAIL_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteMailTemplate_success() {
        // mock 数据
        MailTemplateDO dbMailTemplate = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMailTemplate.getId();

        // 调用
        mailTemplateService.deleteMailTemplate(id);
        // 校验数据不存在了
        assertNull(mailTemplateMapper.selectById(id));
        verify(mailProducer).sendMailTemplateRefreshMessage();
    }

    @Test
    public void testDeleteMailTemplate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mailTemplateService.deleteMailTemplate(id), MAIL_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testGetMailTemplatePage() {
       // mock 数据
       MailTemplateDO dbMailTemplate = randomPojo(MailTemplateDO.class, o -> { // 等会查询到
           o.setName("源码");
           o.setCode("test_01");
           o.setAccountId(1L);
           o.setStatus(CommonStatusEnum.ENABLE.getStatus());
           o.setCreateTime(buildTime(2023, 2, 3));
       });
       mailTemplateMapper.insert(dbMailTemplate);
       // 测试 name 不匹配
       mailTemplateMapper.insert(cloneIgnoreId(dbMailTemplate, o -> o.setName("芋道")));
       // 测试 code 不匹配
       mailTemplateMapper.insert(cloneIgnoreId(dbMailTemplate, o -> o.setCode("test_02")));
       // 测试 accountId 不匹配
       mailTemplateMapper.insert(cloneIgnoreId(dbMailTemplate, o -> o.setAccountId(2L)));
       // 测试 status 不匹配
       mailTemplateMapper.insert(cloneIgnoreId(dbMailTemplate, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
       // 测试 createTime 不匹配
       mailTemplateMapper.insert(cloneIgnoreId(dbMailTemplate, o -> o.setCreateTime(buildTime(2023, 1, 5))));
       // 准备参数
       MailTemplatePageReqVO reqVO = new MailTemplatePageReqVO();
       reqVO.setName("源");
       reqVO.setCode("est_01");
       reqVO.setAccountId(1L);
       reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 5));

       // 调用
       PageResult<MailTemplateDO> pageResult = mailTemplateService.getMailTemplatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMailTemplate, pageResult.getList().get(0));
    }

    @Test
    public void testGetMailTemplateList() {
        // mock 数据
        MailTemplateDO dbMailTemplate01 = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate01);
        MailTemplateDO dbMailTemplate02 = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate02);

        // 调用
        List<MailTemplateDO> list = mailTemplateService.getMailTemplateList();
        // 断言
        assertEquals(2, list.size());
        assertEquals(dbMailTemplate01, list.get(0));
        assertEquals(dbMailTemplate02, list.get(1));
    }

    @Test
    public void testGetMailTemplate() {
        // mock 数据
        MailTemplateDO dbMailTemplate = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate);
        // 准备参数
        Long id = dbMailTemplate.getId();

        // 调用
        MailTemplateDO mailTemplate = mailTemplateService.getMailTemplate(id);
        // 断言
        assertPojoEquals(dbMailTemplate, mailTemplate);
    }

    @Test
    public void testGetMailTemplateByCodeFromCache() {
        // mock 数据
        MailTemplateDO dbMailTemplate = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate);
        mailTemplateService.initLocalCache();
        // 准备参数
        String code = dbMailTemplate.getCode();

        // 调用
        MailTemplateDO mailTemplate = mailTemplateService.getMailTemplateByCodeFromCache(code);
        // 断言
        assertPojoEquals(dbMailTemplate, mailTemplate);
    }

    @Test
    public void testFormatMailTemplateContent() {
        // 准备参数
        Map<String, Object> params = new HashMap<>();
        params.put("name", "小红");
        params.put("what", "饭");

        // 调用，并断言
        assertEquals("小红，你好，饭吃了吗？",
                mailTemplateService.formatMailTemplateContent("{name}，你好，{what}吃了吗？", params));
    }

    @Test
    public void testCountByAccountId() {
        // mock 数据
        MailTemplateDO dbMailTemplate = randomPojo(MailTemplateDO.class);
        mailTemplateMapper.insert(dbMailTemplate);
        // 测试 accountId 不匹配
        mailTemplateMapper.insert(cloneIgnoreId(dbMailTemplate, o -> o.setAccountId(2L)));
        // 准备参数
        Long accountId = dbMailTemplate.getAccountId();

        // 调用
        long count = mailTemplateService.countByAccountId(accountId);
        // 断言
        assertEquals(1, count);
    }

}
