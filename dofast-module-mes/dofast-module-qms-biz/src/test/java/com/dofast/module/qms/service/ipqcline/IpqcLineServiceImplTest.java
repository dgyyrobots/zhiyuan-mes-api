package com.dofast.module.qms.service.ipqcline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.ipqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqcline.IpqcLineDO;
import com.dofast.module.qms.dal.mysql.ipqcline.IpqcLineMapper;
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
 * {@link IpqcLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IpqcLineServiceImpl.class)
public class IpqcLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IpqcLineServiceImpl ipqcLineService;

    @Resource
    private IpqcLineMapper ipqcLineMapper;

    @Test
    public void testCreateIpqcLine_success() {
        // 准备参数
        IpqcLineCreateReqVO reqVO = randomPojo(IpqcLineCreateReqVO.class);

        // 调用
        Long ipqcLineId = ipqcLineService.createIpqcLine(reqVO);
        // 断言
        assertNotNull(ipqcLineId);
        // 校验记录的属性是否正确
        IpqcLineDO ipqcLine = ipqcLineMapper.selectById(ipqcLineId);
        assertPojoEquals(reqVO, ipqcLine);
    }

    @Test
    public void testUpdateIpqcLine_success() {
        // mock 数据
        IpqcLineDO dbIpqcLine = randomPojo(IpqcLineDO.class);
        ipqcLineMapper.insert(dbIpqcLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IpqcLineUpdateReqVO reqVO = randomPojo(IpqcLineUpdateReqVO.class, o -> {
            o.setId(dbIpqcLine.getId()); // 设置更新的 ID
        });

        // 调用
        ipqcLineService.updateIpqcLine(reqVO);
        // 校验是否更新正确
        IpqcLineDO ipqcLine = ipqcLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, ipqcLine);
    }

    @Test
    public void testUpdateIpqcLine_notExists() {
        // 准备参数
        IpqcLineUpdateReqVO reqVO = randomPojo(IpqcLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> ipqcLineService.updateIpqcLine(reqVO), IPQC_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteIpqcLine_success() {
        // mock 数据
        IpqcLineDO dbIpqcLine = randomPojo(IpqcLineDO.class);
        ipqcLineMapper.insert(dbIpqcLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIpqcLine.getId();

        // 调用
        ipqcLineService.deleteIpqcLine(id);
       // 校验数据不存在了
       assertNull(ipqcLineMapper.selectById(id));
    }

    @Test
    public void testDeleteIpqcLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> ipqcLineService.deleteIpqcLine(id), IPQC_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIpqcLinePage() {
       // mock 数据
       IpqcLineDO dbIpqcLine = randomPojo(IpqcLineDO.class, o -> { // 等会查询到
           o.setIpqcId(null);
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
       ipqcLineMapper.insert(dbIpqcLine);
       // 测试 ipqcId 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIpqcId(null)));
       // 测试 indexId 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setThresholdMin(null)));
       // 测试 crQuantity 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setMinQuantity(null)));
       // 测试 remark 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setCreateTime(null)));
       // 准备参数
       IpqcLinePageReqVO reqVO = new IpqcLinePageReqVO();
       reqVO.setIpqcId(null);
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
       PageResult<IpqcLineDO> pageResult = ipqcLineService.getIpqcLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIpqcLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIpqcLineList() {
       // mock 数据
       IpqcLineDO dbIpqcLine = randomPojo(IpqcLineDO.class, o -> { // 等会查询到
           o.setIpqcId(null);
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
       ipqcLineMapper.insert(dbIpqcLine);
       // 测试 ipqcId 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIpqcId(null)));
       // 测试 indexId 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexId(null)));
       // 测试 indexCode 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexCode(null)));
       // 测试 indexName 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexName(null)));
       // 测试 indexType 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setIndexType(null)));
       // 测试 qcTool 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setQcTool(null)));
       // 测试 checkMethod 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setCheckMethod(null)));
       // 测试 standerVal 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setStanderVal(null)));
       // 测试 unitOfMeasure 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setUnitOfMeasure(null)));
       // 测试 thresholdMax 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setThresholdMax(null)));
       // 测试 thresholdMin 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setThresholdMin(null)));
       // 测试 crQuantity 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setCrQuantity(null)));
       // 测试 majQuantity 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setMajQuantity(null)));
       // 测试 minQuantity 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setMinQuantity(null)));
       // 测试 remark 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       ipqcLineMapper.insert(cloneIgnoreId(dbIpqcLine, o -> o.setCreateTime(null)));
       // 准备参数
       IpqcLineExportReqVO reqVO = new IpqcLineExportReqVO();
       reqVO.setIpqcId(null);
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
       List<IpqcLineDO> list = ipqcLineService.getIpqcLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIpqcLine, list.get(0));
    }

}
