package com.dofast.module.mes.service.qualityabnormity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.qualityabnormity.vo.*;
import com.dofast.module.mes.dal.dataobject.qualityabnormity.QualityAbnormityDO;
import com.dofast.module.mes.dal.mysql.qualityabnormity.QualityAbnormityMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link QualityAbnormityServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(QualityAbnormityServiceImpl.class)
public class QualityAbnormityServiceImplTest extends BaseDbUnitTest {

    @Resource
    private QualityAbnormityServiceImpl qualityAbnormityService;

    @Resource
    private QualityAbnormityMapper qualityAbnormityMapper;

    @Test
    public void testCreateQualityAbnormity_success() {
        // 准备参数
        QualityAbnormityCreateReqVO reqVO = randomPojo(QualityAbnormityCreateReqVO.class);

        // 调用
        Long qualityAbnormityId = qualityAbnormityService.createQualityAbnormity(reqVO);
        // 断言
        assertNotNull(qualityAbnormityId);
        // 校验记录的属性是否正确
        QualityAbnormityDO qualityAbnormity = qualityAbnormityMapper.selectById(qualityAbnormityId);
        assertPojoEquals(reqVO, qualityAbnormity);
    }

    @Test
    public void testUpdateQualityAbnormity_success() {
        // mock 数据
        QualityAbnormityDO dbQualityAbnormity = randomPojo(QualityAbnormityDO.class);
        qualityAbnormityMapper.insert(dbQualityAbnormity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        QualityAbnormityUpdateReqVO reqVO = randomPojo(QualityAbnormityUpdateReqVO.class, o -> {
            o.setId(dbQualityAbnormity.getId()); // 设置更新的 ID
        });

        // 调用
        qualityAbnormityService.updateQualityAbnormity(reqVO);
        // 校验是否更新正确
        QualityAbnormityDO qualityAbnormity = qualityAbnormityMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, qualityAbnormity);
    }

    @Test
    public void testUpdateQualityAbnormity_notExists() {
        // 准备参数
        QualityAbnormityUpdateReqVO reqVO = randomPojo(QualityAbnormityUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> qualityAbnormityService.updateQualityAbnormity(reqVO), QUALITY_ABNORMITY_NOT_EXISTS);
    }

    @Test
    public void testDeleteQualityAbnormity_success() {
        // mock 数据
        QualityAbnormityDO dbQualityAbnormity = randomPojo(QualityAbnormityDO.class);
        qualityAbnormityMapper.insert(dbQualityAbnormity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbQualityAbnormity.getId();

        // 调用
        qualityAbnormityService.deleteQualityAbnormity(id);
       // 校验数据不存在了
       assertNull(qualityAbnormityMapper.selectById(id));
    }

    @Test
    public void testDeleteQualityAbnormity_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> qualityAbnormityService.deleteQualityAbnormity(id), QUALITY_ABNORMITY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetQualityAbnormityPage() {
       // mock 数据
       QualityAbnormityDO dbQualityAbnormity = randomPojo(QualityAbnormityDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setBatchesCode(null);
           o.setBatches(null);
           o.setBadDescription(null);
           o.setQuantity(null);
           o.setBadCode(null);
           o.setInspectDate(null);
           o.setInspector(null);
           o.setReinspectDate(null);
           o.setReinspector(null);
           o.setReinspectConclusion(null);
           o.setOrderNum(null);
           o.setSaleNum(null);
           o.setInspectGroup(null);
           o.setBadQuantity(null);
       });
       qualityAbnormityMapper.insert(dbQualityAbnormity);
       // 测试 createTime 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setCreateTime(null)));
       // 测试 itemCode 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setItemName(null)));
       // 测试 batchesCode 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBatchesCode(null)));
       // 测试 batches 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBatches(null)));
       // 测试 badDescription 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBadDescription(null)));
       // 测试 quantity 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setQuantity(null)));
       // 测试 badCode 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBadCode(null)));
       // 测试 inspectDate 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setInspector(null)));
       // 测试 reinspectDate 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setReinspectDate(null)));
       // 测试 reinspector 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setReinspector(null)));
       // 测试 reinspectConclusion 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setReinspectConclusion(null)));
       // 测试 orderNum 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setOrderNum(null)));
       // 测试 saleNum 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setSaleNum(null)));
       // 测试 inspectGroup 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setInspectGroup(null)));
       // 测试 badQuantity 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBadQuantity(null)));
       // 准备参数
       QualityAbnormityPageReqVO reqVO = new QualityAbnormityPageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setBatchesCode(null);
       reqVO.setBatches(null);
       reqVO.setBadDescription(null);
       reqVO.setQuantity(null);
       reqVO.setBadCode(null);
       reqVO.setInspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setInspector(null);
       reqVO.setReinspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReinspector(null);
       reqVO.setReinspectConclusion(null);
       reqVO.setOrderNum(null);
       reqVO.setSaleNum(null);
       reqVO.setInspectGroup(null);
       reqVO.setBadQuantity(null);

       // 调用
       PageResult<QualityAbnormityDO> pageResult = qualityAbnormityService.getQualityAbnormityPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbQualityAbnormity, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetQualityAbnormityList() {
       // mock 数据
       QualityAbnormityDO dbQualityAbnormity = randomPojo(QualityAbnormityDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setItemCode(null);
           o.setItemName(null);
           o.setBatchesCode(null);
           o.setBatches(null);
           o.setBadDescription(null);
           o.setQuantity(null);
           o.setBadCode(null);
           o.setInspectDate(null);
           o.setInspector(null);
           o.setReinspectDate(null);
           o.setReinspector(null);
           o.setReinspectConclusion(null);
           o.setOrderNum(null);
           o.setSaleNum(null);
           o.setInspectGroup(null);
           o.setBadQuantity(null);
       });
       qualityAbnormityMapper.insert(dbQualityAbnormity);
       // 测试 createTime 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setCreateTime(null)));
       // 测试 itemCode 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setItemCode(null)));
       // 测试 itemName 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setItemName(null)));
       // 测试 batchesCode 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBatchesCode(null)));
       // 测试 batches 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBatches(null)));
       // 测试 badDescription 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBadDescription(null)));
       // 测试 quantity 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setQuantity(null)));
       // 测试 badCode 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBadCode(null)));
       // 测试 inspectDate 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setInspectDate(null)));
       // 测试 inspector 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setInspector(null)));
       // 测试 reinspectDate 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setReinspectDate(null)));
       // 测试 reinspector 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setReinspector(null)));
       // 测试 reinspectConclusion 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setReinspectConclusion(null)));
       // 测试 orderNum 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setOrderNum(null)));
       // 测试 saleNum 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setSaleNum(null)));
       // 测试 inspectGroup 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setInspectGroup(null)));
       // 测试 badQuantity 不匹配
       qualityAbnormityMapper.insert(cloneIgnoreId(dbQualityAbnormity, o -> o.setBadQuantity(null)));
       // 准备参数
       QualityAbnormityExportReqVO reqVO = new QualityAbnormityExportReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setItemCode(null);
       reqVO.setItemName(null);
       reqVO.setBatchesCode(null);
       reqVO.setBatches(null);
       reqVO.setBadDescription(null);
       reqVO.setQuantity(null);
       reqVO.setBadCode(null);
       reqVO.setInspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setInspector(null);
       reqVO.setReinspectDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setReinspector(null);
       reqVO.setReinspectConclusion(null);
       reqVO.setOrderNum(null);
       reqVO.setSaleNum(null);
       reqVO.setInspectGroup(null);
       reqVO.setBadQuantity(null);

       // 调用
       List<QualityAbnormityDO> list = qualityAbnormityService.getQualityAbnormityList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbQualityAbnormity, list.get(0));
    }

}
