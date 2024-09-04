package com.dofast.module.pro.service.processcontent;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.pro.controller.admin.processcontent.vo.*;
import com.dofast.module.pro.dal.dataobject.processcontent.ProcessContentDO;
import com.dofast.module.pro.dal.mysql.processcontent.ProcessContentMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ProcessContentServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ProcessContentServiceImpl.class)
public class ProcessContentServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProcessContentServiceImpl cessContentService;

    @Resource
    private ProcessContentMapper cessContentMapper;

    @Test
    public void testCreatecessContent_success() {
        // 准备参数
        ProcessContentCreateReqVO reqVO = randomPojo(ProcessContentCreateReqVO.class);

        // 调用
        Long cessContentId = cessContentService.createcessContent(reqVO);
        // 断言
        assertNotNull(cessContentId);
        // 校验记录的属性是否正确
        ProcessContentDO cessContent = cessContentMapper.selectById(cessContentId);
        assertPojoEquals(reqVO, cessContent);
    }

    @Test
    public void testUpdatecessContent_success() {
        // mock 数据
        ProcessContentDO dbcessContent = randomPojo(ProcessContentDO.class);
        cessContentMapper.insert(dbcessContent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProcessContentUpdateReqVO reqVO = randomPojo(ProcessContentUpdateReqVO.class, o -> {
            o.setId(dbcessContent.getId()); // 设置更新的 ID
        });

        // 调用
        cessContentService.updatecessContent(reqVO);
        // 校验是否更新正确
        ProcessContentDO cessContent = cessContentMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, cessContent);
    }

    @Test
    public void testUpdatecessContent_notExists() {
        // 准备参数
        ProcessContentUpdateReqVO reqVO = randomPojo(ProcessContentUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> cessContentService.updatecessContent(reqVO), CESS_CONTENT_NOT_EXISTS);
    }

    @Test
    public void testDeletecessContent_success() {
        // mock 数据
        ProcessContentDO dbcessContent = randomPojo(ProcessContentDO.class);
        cessContentMapper.insert(dbcessContent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbcessContent.getId();

        // 调用
        cessContentService.deletecessContent(id);
       // 校验数据不存在了
       assertNull(cessContentMapper.selectById(id));
    }

    @Test
    public void testDeletecessContent_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> cessContentService.deletecessContent(id), CESS_CONTENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetcessContentPage() {
       // mock 数据
       ProcessContentDO dbcessContent = randomPojo(ProcessContentDO.class, o -> { // 等会查询到
           o.setProcessId(null);
           o.setOrderNum(null);
           o.setContentText(null);
           o.setDevice(null);
           o.setMaterial(null);
           o.setDocUrl(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       cessContentMapper.insert(dbcessContent);
       // 测试 processId 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setProcessId(null)));
       // 测试 orderNum 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setOrderNum(null)));
       // 测试 contentText 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setContentText(null)));
       // 测试 device 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setDevice(null)));
       // 测试 material 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setMaterial(null)));
       // 测试 docUrl 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setDocUrl(null)));
       // 测试 remark 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setCreateTime(null)));
       // 准备参数
       ProcessContentPageReqVO reqVO = new ProcessContentPageReqVO();
       reqVO.setProcessId(null);
       reqVO.setOrderNum(null);
       reqVO.setContentText(null);
       reqVO.setDevice(null);
       reqVO.setMaterial(null);
       reqVO.setDocUrl(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ProcessContentDO> pageResult = cessContentService.getcessContentPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbcessContent, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetcessContentList() {
       // mock 数据
       ProcessContentDO dbcessContent = randomPojo(ProcessContentDO.class, o -> { // 等会查询到
           o.setProcessId(null);
           o.setOrderNum(null);
           o.setContentText(null);
           o.setDevice(null);
           o.setMaterial(null);
           o.setDocUrl(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       cessContentMapper.insert(dbcessContent);
       // 测试 processId 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setProcessId(null)));
       // 测试 orderNum 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setOrderNum(null)));
       // 测试 contentText 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setContentText(null)));
       // 测试 device 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setDevice(null)));
       // 测试 material 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setMaterial(null)));
       // 测试 docUrl 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setDocUrl(null)));
       // 测试 remark 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       cessContentMapper.insert(cloneIgnoreId(dbcessContent, o -> o.setCreateTime(null)));
       // 准备参数
       ProcessContentExportReqVO reqVO = new ProcessContentExportReqVO();
       reqVO.setProcessId(null);
       reqVO.setOrderNum(null);
       reqVO.setContentText(null);
       reqVO.setDevice(null);
       reqVO.setMaterial(null);
       reqVO.setDocUrl(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ProcessContentDO> list = cessContentService.getcessContentList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbcessContent, list.get(0));
    }

}
