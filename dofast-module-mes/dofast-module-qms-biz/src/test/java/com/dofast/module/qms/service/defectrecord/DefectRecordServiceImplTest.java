package com.dofast.module.qms.service.defectrecord;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.defectrecord.vo.*;
import com.dofast.module.qms.dal.dataobject.defectrecord.DefectRecordDO;
import com.dofast.module.qms.dal.mysql.defectrecord.DefectRecordMapper;
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
 * {@link DefectRecordServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DefectRecordServiceImpl.class)
public class DefectRecordServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DefectRecordServiceImpl defectRecordService;

    @Resource
    private DefectRecordMapper defectRecordMapper;

    @Test
    public void testCreateDefectRecord_success() {
        // 准备参数
        DefectRecordCreateReqVO reqVO = randomPojo(DefectRecordCreateReqVO.class);

        // 调用
        Long defectRecordId = defectRecordService.createDefectRecord(reqVO);
        // 断言
        assertNotNull(defectRecordId);
        // 校验记录的属性是否正确
        DefectRecordDO defectRecord = defectRecordMapper.selectById(defectRecordId);
        assertPojoEquals(reqVO, defectRecord);
    }

    @Test
    public void testUpdateDefectRecord_success() {
        // mock 数据
        DefectRecordDO dbDefectRecord = randomPojo(DefectRecordDO.class);
        defectRecordMapper.insert(dbDefectRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DefectRecordUpdateReqVO reqVO = randomPojo(DefectRecordUpdateReqVO.class, o -> {
            o.setId(dbDefectRecord.getId()); // 设置更新的 ID
        });

        // 调用
        defectRecordService.updateDefectRecord(reqVO);
        // 校验是否更新正确
        DefectRecordDO defectRecord = defectRecordMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, defectRecord);
    }

    @Test
    public void testUpdateDefectRecord_notExists() {
        // 准备参数
        DefectRecordUpdateReqVO reqVO = randomPojo(DefectRecordUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> defectRecordService.updateDefectRecord(reqVO), DEFECT_RECORD_NOT_EXISTS);
    }

    @Test
    public void testDeleteDefectRecord_success() {
        // mock 数据
        DefectRecordDO dbDefectRecord = randomPojo(DefectRecordDO.class);
        defectRecordMapper.insert(dbDefectRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDefectRecord.getId();

        // 调用
        defectRecordService.deleteDefectRecord(id);
       // 校验数据不存在了
       assertNull(defectRecordMapper.selectById(id));
    }

    @Test
    public void testDeleteDefectRecord_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> defectRecordService.deleteDefectRecord(id), DEFECT_RECORD_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDefectRecordPage() {
       // mock 数据
       DefectRecordDO dbDefectRecord = randomPojo(DefectRecordDO.class, o -> { // 等会查询到
           o.setQcType(null);
           o.setQcId(null);
           o.setLineId(null);
           o.setDefectName(null);
           o.setDefectLevel(null);
           o.setDefectQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       defectRecordMapper.insert(dbDefectRecord);
       // 测试 qcType 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setQcType(null)));
       // 测试 qcId 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setQcId(null)));
       // 测试 lineId 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setLineId(null)));
       // 测试 defectName 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setDefectName(null)));
       // 测试 defectLevel 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setDefectLevel(null)));
       // 测试 defectQuantity 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setDefectQuantity(null)));
       // 测试 remark 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setCreateTime(null)));
       // 准备参数
       DefectRecordPageReqVO reqVO = new DefectRecordPageReqVO();
       reqVO.setQcType(null);
       reqVO.setQcId(null);
       reqVO.setLineId(null);
       reqVO.setDefectName(null);
       reqVO.setDefectLevel(null);
       reqVO.setDefectQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DefectRecordDO> pageResult = defectRecordService.getDefectRecordPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDefectRecord, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDefectRecordList() {
       // mock 数据
       DefectRecordDO dbDefectRecord = randomPojo(DefectRecordDO.class, o -> { // 等会查询到
           o.setQcType(null);
           o.setQcId(null);
           o.setLineId(null);
           o.setDefectName(null);
           o.setDefectLevel(null);
           o.setDefectQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       defectRecordMapper.insert(dbDefectRecord);
       // 测试 qcType 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setQcType(null)));
       // 测试 qcId 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setQcId(null)));
       // 测试 lineId 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setLineId(null)));
       // 测试 defectName 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setDefectName(null)));
       // 测试 defectLevel 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setDefectLevel(null)));
       // 测试 defectQuantity 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setDefectQuantity(null)));
       // 测试 remark 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       defectRecordMapper.insert(cloneIgnoreId(dbDefectRecord, o -> o.setCreateTime(null)));
       // 准备参数
       DefectRecordExportReqVO reqVO = new DefectRecordExportReqVO();
       reqVO.setQcType(null);
       reqVO.setQcId(null);
       reqVO.setLineId(null);
       reqVO.setDefectName(null);
       reqVO.setDefectLevel(null);
       reqVO.setDefectQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DefectRecordDO> list = defectRecordService.getDefectRecordList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDefectRecord, list.get(0));
    }

}
