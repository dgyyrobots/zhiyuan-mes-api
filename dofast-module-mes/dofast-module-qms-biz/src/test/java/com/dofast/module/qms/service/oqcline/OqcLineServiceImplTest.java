package com.dofast.module.qms.service.oqcline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.oqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.oqcline.OqcLineDO;
import com.dofast.module.qms.dal.mysql.oqcline.OqcLineMapper;
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
 * {@link OqcLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(OqcLineServiceImpl.class)
public class OqcLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OqcLineServiceImpl oqcLineService;

    @Resource
    private OqcLineMapper oqcLineMapper;

    @Test
    public void testCreateOqcLine_success() {
        // 准备参数
        OqcLineCreateReqVO reqVO = randomPojo(OqcLineCreateReqVO.class);

        // 调用
        Long oqcLineId = oqcLineService.createOqcLine(reqVO);
        // 断言
        assertNotNull(oqcLineId);
        // 校验记录的属性是否正确
        OqcLineDO oqcLine = oqcLineMapper.selectById(oqcLineId);
        assertPojoEquals(reqVO, oqcLine);
    }

    @Test
    public void testUpdateOqcLine_success() {
        // mock 数据
        OqcLineDO dbOqcLine = randomPojo(OqcLineDO.class);
        oqcLineMapper.insert(dbOqcLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OqcLineUpdateReqVO reqVO = randomPojo(OqcLineUpdateReqVO.class, o -> {
            o.setId(dbOqcLine.getId()); // 设置更新的 ID
        });

        // 调用
        oqcLineService.updateOqcLine(reqVO);
        // 校验是否更新正确
        OqcLineDO oqcLine = oqcLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, oqcLine);
    }

    @Test
    public void testUpdateOqcLine_notExists() {
        // 准备参数
        OqcLineUpdateReqVO reqVO = randomPojo(OqcLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> oqcLineService.updateOqcLine(reqVO), OQC_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteOqcLine_success() {
        // mock 数据
        OqcLineDO dbOqcLine = randomPojo(OqcLineDO.class);
        oqcLineMapper.insert(dbOqcLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOqcLine.getId();

        // 调用
        oqcLineService.deleteOqcLine(id);
       // 校验数据不存在了
       assertNull(oqcLineMapper.selectById(id));
    }

    @Test
    public void testDeleteOqcLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> oqcLineService.deleteOqcLine(id), OQC_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOqcLinePage() {
       // mock 数据
       OqcLineDO dbOqcLine = randomPojo(OqcLineDO.class, o -> { // 等会查询到
           o.setOqcId(null);
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
       oqcLineMapper.insert(dbOqcLine);
       // 测试 oqcId 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setOqcId(null)));
       // 测试 indexId 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setThresholdMin(null)));
       // 测试 crQuantity 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setMinQuantity(null)));
       // 测试 remark 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setCreateTime(null)));
       // 准备参数
       OqcLinePageReqVO reqVO = new OqcLinePageReqVO();
       reqVO.setOqcId(null);
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
       PageResult<OqcLineDO> pageResult = oqcLineService.getOqcLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOqcLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOqcLineList() {
       // mock 数据
       OqcLineDO dbOqcLine = randomPojo(OqcLineDO.class, o -> { // 等会查询到
           o.setOqcId(null);
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
       oqcLineMapper.insert(dbOqcLine);
       // 测试 oqcId 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setOqcId(null)));
       // 测试 indexId 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setThresholdMin(null)));
       // 测试 crQuantity 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setMinQuantity(null)));
       // 测试 remark 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       oqcLineMapper.insert(cloneIgnoreId(dbOqcLine, o -> o.setCreateTime(null)));
       // 准备参数
       OqcLineExportReqVO reqVO = new OqcLineExportReqVO();
       reqVO.setOqcId(null);
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
       List<OqcLineDO> list = oqcLineService.getOqcLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOqcLine, list.get(0));
    }

}
