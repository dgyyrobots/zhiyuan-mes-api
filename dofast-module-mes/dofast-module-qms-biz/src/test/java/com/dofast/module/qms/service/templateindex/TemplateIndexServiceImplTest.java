package com.dofast.module.qms.service.templateindex;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.templateindex.vo.*;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;
import com.dofast.module.qms.dal.mysql.templateindex.TemplateIndexMapper;
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
 * {@link TemplateIndexServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(TemplateIndexServiceImpl.class)
public class TemplateIndexServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TemplateIndexServiceImpl templateIndexService;

    @Resource
    private TemplateIndexMapper templateIndexMapper;

    @Test
    public void testCreateTemplateIndex_success() {
        // 准备参数
        TemplateIndexCreateReqVO reqVO = randomPojo(TemplateIndexCreateReqVO.class);

        // 调用
        Long templateIndexId = templateIndexService.createTemplateIndex(reqVO);
        // 断言
        assertNotNull(templateIndexId);
        // 校验记录的属性是否正确
        TemplateIndexDO templateIndex = templateIndexMapper.selectById(templateIndexId);
        assertPojoEquals(reqVO, templateIndex);
    }

    @Test
    public void testUpdateTemplateIndex_success() {
        // mock 数据
        TemplateIndexDO dbTemplateIndex = randomPojo(TemplateIndexDO.class);
        templateIndexMapper.insert(dbTemplateIndex);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TemplateIndexUpdateReqVO reqVO = randomPojo(TemplateIndexUpdateReqVO.class, o -> {
            o.setId(dbTemplateIndex.getId()); // 设置更新的 ID
        });

        // 调用
        templateIndexService.updateTemplateIndex(reqVO);
        // 校验是否更新正确
        TemplateIndexDO templateIndex = templateIndexMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, templateIndex);
    }

    @Test
    public void testUpdateTemplateIndex_notExists() {
        // 准备参数
        TemplateIndexUpdateReqVO reqVO = randomPojo(TemplateIndexUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> templateIndexService.updateTemplateIndex(reqVO), TEMPLATE_INDEX_NOT_EXISTS);
    }

    @Test
    public void testDeleteTemplateIndex_success() {
        // mock 数据
        TemplateIndexDO dbTemplateIndex = randomPojo(TemplateIndexDO.class);
        templateIndexMapper.insert(dbTemplateIndex);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTemplateIndex.getId();

        // 调用
        templateIndexService.deleteTemplateIndex(id);
       // 校验数据不存在了
       assertNull(templateIndexMapper.selectById(id));
    }

    @Test
    public void testDeleteTemplateIndex_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> templateIndexService.deleteTemplateIndex(id), TEMPLATE_INDEX_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTemplateIndexPage() {
       // mock 数据
       TemplateIndexDO dbTemplateIndex = randomPojo(TemplateIndexDO.class, o -> { // 等会查询到
           o.setTemplateId(null);
           o.setIndexId(null);
           o.setIndexCode(null);
           o.setIndexName(null);
           o.setIndexType(null);
           o.setQcTool(null);
           o.setCheckMethod(null);
           o.setStanderVal(null);
           o.setUnitOfMeasure(null);
           o.setThresholdMax(null);
           o.setThresholdMin(null);
           o.setDocUrl(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       templateIndexMapper.insert(dbTemplateIndex);
       // 测试 templateId 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setTemplateId(null)));
       // 测试 indexId 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setThresholdMin(null)));
       // 测试 docUrl 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setDocUrl(null)));
       // 测试 remark 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setCreateTime(null)));
       // 准备参数
       TemplateIndexPageReqVO reqVO = new TemplateIndexPageReqVO();
       reqVO.setTemplateId(null);
       reqVO.setIndexId(null);
       reqVO.setIndexCode(null);
       reqVO.setIndexName(null);
       reqVO.setIndexType(null);
       reqVO.setQcTool(null);
       reqVO.setCheckMethod(null);
       reqVO.setStanderVal(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setThresholdMax(null);
       reqVO.setThresholdMin(null);
       reqVO.setDocUrl(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TemplateIndexDO> pageResult = templateIndexService.getTemplateIndexPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTemplateIndex, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTemplateIndexList() {
       // mock 数据
       TemplateIndexDO dbTemplateIndex = randomPojo(TemplateIndexDO.class, o -> { // 等会查询到
           o.setTemplateId(null);
           o.setIndexId(null);
           o.setIndexCode(null);
           o.setIndexName(null);
           o.setIndexType(null);
           o.setQcTool(null);
           o.setCheckMethod(null);
           o.setStanderVal(null);
           o.setUnitOfMeasure(null);
           o.setThresholdMax(null);
           o.setThresholdMin(null);
           o.setDocUrl(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       templateIndexMapper.insert(dbTemplateIndex);
       // 测试 templateId 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setTemplateId(null)));
       // 测试 indexId 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setThresholdMin(null)));
       // 测试 docUrl 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setDocUrl(null)));
       // 测试 remark 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       templateIndexMapper.insert(cloneIgnoreId(dbTemplateIndex, o -> o.setCreateTime(null)));
       // 准备参数
       TemplateIndexExportReqVO reqVO = new TemplateIndexExportReqVO();
       reqVO.setTemplateId(null);
       reqVO.setIndexId(null);
       reqVO.setIndexCode(null);
       reqVO.setIndexName(null);
       reqVO.setIndexType(null);
       reqVO.setQcTool(null);
       reqVO.setCheckMethod(null);
       reqVO.setStanderVal(null);
       reqVO.setUnitOfMeasure(null);
       reqVO.setThresholdMax(null);
       reqVO.setThresholdMin(null);
       reqVO.setDocUrl(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<TemplateIndexDO> list = templateIndexService.getTemplateIndexList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbTemplateIndex, list.get(0));
    }

}
