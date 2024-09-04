package com.dofast.module.qms.service.defect;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.qms.controller.admin.defect.vo.*;
import com.dofast.module.qms.dal.dataobject.defect.DefectDO;
import com.dofast.module.qms.dal.mysql.defect.DefectMapper;
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
 * {@link DefectServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DefectServiceImpl.class)
public class DefectServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DefectServiceImpl defectService;

    @Resource
    private DefectMapper defectMapper;

    @Test
    public void testCreateDefect_success() {
        // 准备参数
        DefectCreateReqVO reqVO = randomPojo(DefectCreateReqVO.class);

        // 调用
        Long defectId = defectService.createDefect(reqVO);
        // 断言
        assertNotNull(defectId);
        // 校验记录的属性是否正确
        DefectDO defect = defectMapper.selectById(defectId);
        assertPojoEquals(reqVO, defect);
    }

    @Test
    public void testUpdateDefect_success() {
        // mock 数据
        DefectDO dbDefect = randomPojo(DefectDO.class);
        defectMapper.insert(dbDefect);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DefectUpdateReqVO reqVO = randomPojo(DefectUpdateReqVO.class, o -> {
            o.setId(dbDefect.getId()); // 设置更新的 ID
        });

        // 调用
        defectService.updateDefect(reqVO);
        // 校验是否更新正确
        DefectDO defect = defectMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, defect);
    }

    @Test
    public void testUpdateDefect_notExists() {
        // 准备参数
        DefectUpdateReqVO reqVO = randomPojo(DefectUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> defectService.updateDefect(reqVO), DEFECT_NOT_EXISTS);
    }

    @Test
    public void testDeleteDefect_success() {
        // mock 数据
        DefectDO dbDefect = randomPojo(DefectDO.class);
        defectMapper.insert(dbDefect);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDefect.getId();

        // 调用
        defectService.deleteDefect(id);
       // 校验数据不存在了
       assertNull(defectMapper.selectById(id));
    }

    @Test
    public void testDeleteDefect_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> defectService.deleteDefect(id), DEFECT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDefectPage() {
       // mock 数据
       DefectDO dbDefect = randomPojo(DefectDO.class, o -> { // 等会查询到
           o.setDefectCode(null);
           o.setDefectName(null);
           o.setIndexType(null);
           o.setDefectLevel(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       defectMapper.insert(dbDefect);
       // 测试 defectCode 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setDefectCode(null)));
       // 测试 defectName 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setDefectName(null)));
       // 测试 indexType 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setIndexType(null)));
       // 测试 defectLevel 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setDefectLevel(null)));
       // 测试 remark 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setCreateTime(null)));
       // 准备参数
       DefectPageReqVO reqVO = new DefectPageReqVO();
       reqVO.setDefectCode(null);
       reqVO.setDefectName(null);
       reqVO.setIndexType(null);
       reqVO.setDefectLevel(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DefectDO> pageResult = defectService.getDefectPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDefect, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDefectList() {
       // mock 数据
       DefectDO dbDefect = randomPojo(DefectDO.class, o -> { // 等会查询到
           o.setDefectCode(null);
           o.setDefectName(null);
           o.setIndexType(null);
           o.setDefectLevel(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       defectMapper.insert(dbDefect);
       // 测试 defectCode 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setDefectCode(null)));
       // 测试 defectName 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setDefectName(null)));
       // 测试 indexType 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setIndexType(null)));
       // 测试 defectLevel 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setDefectLevel(null)));
       // 测试 remark 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       defectMapper.insert(cloneIgnoreId(dbDefect, o -> o.setCreateTime(null)));
       // 准备参数
       DefectExportReqVO reqVO = new DefectExportReqVO();
       reqVO.setDefectCode(null);
       reqVO.setDefectName(null);
       reqVO.setIndexType(null);
       reqVO.setDefectLevel(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DefectDO> list = defectService.getDefectList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDefect, list.get(0));
    }

}
