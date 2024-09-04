package com.dofast.module.qms.service.index;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.index.vo.*;
import com.dofast.module.qms.dal.dataobject.index.IndexDO;
import com.dofast.module.qms.dal.mysql.index.IndexMapper;
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
 * {@link IndexServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IndexServiceImpl.class)
public class IndexServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IndexServiceImpl indexService;

    @Resource
    private IndexMapper indexMapper;

    @Test
    public void testCreateIndex_success() {
        // 准备参数
        IndexCreateReqVO reqVO = randomPojo(IndexCreateReqVO.class);

        // 调用
        Long indexId = indexService.createIndex(reqVO);
        // 断言
        assertNotNull(indexId);
        // 校验记录的属性是否正确
        IndexDO index = indexMapper.selectById(indexId);
        assertPojoEquals(reqVO, index);
    }

    @Test
    public void testUpdateIndex_success() {
        // mock 数据
        IndexDO dbIndex = randomPojo(IndexDO.class);
        indexMapper.insert(dbIndex);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IndexUpdateReqVO reqVO = randomPojo(IndexUpdateReqVO.class, o -> {
            o.setId(dbIndex.getId()); // 设置更新的 ID
        });

        // 调用
        indexService.updateIndex(reqVO);
        // 校验是否更新正确
        IndexDO index = indexMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, index);
    }

    @Test
    public void testUpdateIndex_notExists() {
        // 准备参数
        IndexUpdateReqVO reqVO = randomPojo(IndexUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> indexService.updateIndex(reqVO), INDEX_NOT_EXISTS);
    }

    @Test
    public void testDeleteIndex_success() {
        // mock 数据
        IndexDO dbIndex = randomPojo(IndexDO.class);
        indexMapper.insert(dbIndex);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIndex.getId();

        // 调用
        indexService.deleteIndex(id);
       // 校验数据不存在了
       assertNull(indexMapper.selectById(id));
    }

    @Test
    public void testDeleteIndex_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> indexService.deleteIndex(id), INDEX_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIndexPage() {
       // mock 数据
       IndexDO dbIndex = randomPojo(IndexDO.class, o -> { // 等会查询到
           o.setIndexCode(null);
           o.setIndexName(null);
           o.setIndexType(null);
           o.setQcTool(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       indexMapper.insert(dbIndex);
       // 测试 indexCode 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setQcTool(null)));
       // 测试 remark 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setCreateTime(null)));
       // 准备参数
       IndexPageReqVO reqVO = new IndexPageReqVO();
       reqVO.setIndexCode(null);
       reqVO.setIndexName(null);
       reqVO.setIndexType(null);
       reqVO.setQcTool(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IndexDO> pageResult = indexService.getIndexPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIndex, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIndexList() {
       // mock 数据
       IndexDO dbIndex = randomPojo(IndexDO.class, o -> { // 等会查询到
           o.setIndexCode(null);
           o.setIndexName(null);
           o.setIndexType(null);
           o.setQcTool(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       indexMapper.insert(dbIndex);
       // 测试 indexCode 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setQcTool(null)));
       // 测试 remark 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       indexMapper.insert(cloneIgnoreId(dbIndex, o -> o.setCreateTime(null)));
       // 准备参数
       IndexExportReqVO reqVO = new IndexExportReqVO();
       reqVO.setIndexCode(null);
       reqVO.setIndexName(null);
       reqVO.setIndexType(null);
       reqVO.setQcTool(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IndexDO> list = indexService.getIndexList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIndex, list.get(0));
    }

}
