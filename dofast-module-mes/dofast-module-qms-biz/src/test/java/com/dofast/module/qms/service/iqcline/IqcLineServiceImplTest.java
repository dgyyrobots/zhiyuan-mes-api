package com.dofast.module.qms.service.iqcline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.iqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.iqcline.IqcLineDO;
import com.dofast.module.qms.dal.mysql.iqcline.IqcLineMapper;
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
 * {@link IqcLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IqcLineServiceImpl.class)
public class IqcLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IqcLineServiceImpl iqcLineService;

    @Resource
    private IqcLineMapper iqcLineMapper;

    @Test
    public void testCreateIqcLine_success() {
        // 准备参数
        IqcLineCreateReqVO reqVO = randomPojo(IqcLineCreateReqVO.class);

        // 调用
        Long iqcLineId = iqcLineService.createIqcLine(reqVO);
        // 断言
        assertNotNull(iqcLineId);
        // 校验记录的属性是否正确
        IqcLineDO iqcLine = iqcLineMapper.selectById(iqcLineId);
        assertPojoEquals(reqVO, iqcLine);
    }

    @Test
    public void testUpdateIqcLine_success() {
        // mock 数据
        IqcLineDO dbIqcLine = randomPojo(IqcLineDO.class);
        iqcLineMapper.insert(dbIqcLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IqcLineUpdateReqVO reqVO = randomPojo(IqcLineUpdateReqVO.class, o -> {
            o.setId(dbIqcLine.getId()); // 设置更新的 ID
        });

        // 调用
        iqcLineService.updateIqcLine(reqVO);
        // 校验是否更新正确
        IqcLineDO iqcLine = iqcLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, iqcLine);
    }

    @Test
    public void testUpdateIqcLine_notExists() {
        // 准备参数
        IqcLineUpdateReqVO reqVO = randomPojo(IqcLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> iqcLineService.updateIqcLine(reqVO), IQC_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteIqcLine_success() {
        // mock 数据
        IqcLineDO dbIqcLine = randomPojo(IqcLineDO.class);
        iqcLineMapper.insert(dbIqcLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIqcLine.getId();

        // 调用
        iqcLineService.deleteIqcLine(id);
       // 校验数据不存在了
       assertNull(iqcLineMapper.selectById(id));
    }

    @Test
    public void testDeleteIqcLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> iqcLineService.deleteIqcLine(id), IQC_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIqcLinePage() {
       // mock 数据
       IqcLineDO dbIqcLine = randomPojo(IqcLineDO.class, o -> { // 等会查询到
           o.setIqcId(null);
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
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       iqcLineMapper.insert(dbIqcLine);
       // 测试 iqcId 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIqcId(null)));
       // 测试 indexId 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setThresholdMin(null)));
       // 测试 crQuantity 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setMinQuantity(null)));
       // 测试 remark 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setCreateTime(null)));
       // 准备参数
       IqcLinePageReqVO reqVO = new IqcLinePageReqVO();
       reqVO.setIqcId(null);
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
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IqcLineDO> pageResult = iqcLineService.getIqcLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIqcLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIqcLineList() {
       // mock 数据
       IqcLineDO dbIqcLine = randomPojo(IqcLineDO.class, o -> { // 等会查询到
           o.setIqcId(null);
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
           o.setCrQuantity(null);
           o.setMajQuantity(null);
           o.setMinQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       iqcLineMapper.insert(dbIqcLine);
       // 测试 iqcId 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIqcId(null)));
       // 测试 indexId 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setThresholdMin(null)));
       // 测试 crQuantity 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setMinQuantity(null)));
       // 测试 remark 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       iqcLineMapper.insert(cloneIgnoreId(dbIqcLine, o -> o.setCreateTime(null)));
       // 准备参数
       IqcLineExportReqVO reqVO = new IqcLineExportReqVO();
       reqVO.setIqcId(null);
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
       reqVO.setCrQuantity(null);
       reqVO.setMajQuantity(null);
       reqVO.setMinQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IqcLineDO> list = iqcLineService.getIqcLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIqcLine, list.get(0));
    }

}
