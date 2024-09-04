package com.dofast.module.cmms.service.dvrepairline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvrepairline.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvrepairline.DvRepairLineDO;
import com.dofast.module.cmms.dal.mysql.dvrepairline.DvRepairLineMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link DvRepairLineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvRepairLineServiceImpl.class)
public class DvRepairLineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvRepairLineServiceImpl dvRepairLineService;

    @Resource
    private DvRepairLineMapper dvRepairLineMapper;

    @Test
    public void testCreateDvRepairLine_success() {
        // 准备参数
        DvRepairLineCreateReqVO reqVO = randomPojo(DvRepairLineCreateReqVO.class);

        // 调用
        Long dvRepairLineId = dvRepairLineService.createDvRepairLine(reqVO);
        // 断言
        assertNotNull(dvRepairLineId);
        // 校验记录的属性是否正确
        DvRepairLineDO dvRepairLine = dvRepairLineMapper.selectById(dvRepairLineId);
        assertPojoEquals(reqVO, dvRepairLine);
    }

    @Test
    public void testUpdateDvRepairLine_success() {
        // mock 数据
        DvRepairLineDO dbDvRepairLine = randomPojo(DvRepairLineDO.class);
        dvRepairLineMapper.insert(dbDvRepairLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvRepairLineUpdateReqVO reqVO = randomPojo(DvRepairLineUpdateReqVO.class, o -> {
            o.setId(dbDvRepairLine.getId()); // 设置更新的 ID
        });

        // 调用
        dvRepairLineService.updateDvRepairLine(reqVO);
        // 校验是否更新正确
        DvRepairLineDO dvRepairLine = dvRepairLineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvRepairLine);
    }

    @Test
    public void testUpdateDvRepairLine_notExists() {
        // 准备参数
        DvRepairLineUpdateReqVO reqVO = randomPojo(DvRepairLineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvRepairLineService.updateDvRepairLine(reqVO), DV_REPAIR_LINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvRepairLine_success() {
        // mock 数据
        DvRepairLineDO dbDvRepairLine = randomPojo(DvRepairLineDO.class);
        dvRepairLineMapper.insert(dbDvRepairLine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvRepairLine.getId();

        // 调用
        dvRepairLineService.deleteDvRepairLine(id);
       // 校验数据不存在了
       assertNull(dvRepairLineMapper.selectById(id));
    }

    @Test
    public void testDeleteDvRepairLine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvRepairLineService.deleteDvRepairLine(id), DV_REPAIR_LINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvRepairLinePage() {
       // mock 数据
       DvRepairLineDO dbDvRepairLine = randomPojo(DvRepairLineDO.class, o -> { // 等会查询到
           o.setRepairId(null);
           o.setSubjectId(null);
           o.setSubjectCode(null);
           o.setSubjectName(null);
           o.setSubjectType(null);
           o.setSubjectContent(null);
           o.setSubjectStandard(null);
           o.setMalfunction(null);
           o.setMalfunctionUrl(null);
           o.setRepairDes(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvRepairLineMapper.insert(dbDvRepairLine);
       // 测试 repairId 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setRepairId(null)));
       // 测试 subjectId 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectId(null)));
       // 测试 subjectCode 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectCode(null)));
       // 测试 subjectName 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectName(null)));
       // 测试 subjectType 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectType(null)));
       // 测试 subjectContent 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectContent(null)));
       // 测试 subjectStandard 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectStandard(null)));
       // 测试 malfunction 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setMalfunction(null)));
       // 测试 malfunctionUrl 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setMalfunctionUrl(null)));
       // 测试 repairDes 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setRepairDes(null)));
       // 测试 remark 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setCreateTime(null)));
       // 准备参数
       DvRepairLinePageReqVO reqVO = new DvRepairLinePageReqVO();
       reqVO.setRepairId(null);
       reqVO.setSubjectId(null);
       reqVO.setSubjectCode(null);
       reqVO.setSubjectName(null);
       reqVO.setSubjectType(null);
       reqVO.setSubjectContent(null);
       reqVO.setSubjectStandard(null);
       reqVO.setMalfunction(null);
       reqVO.setMalfunctionUrl(null);
       reqVO.setRepairDes(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvRepairLineDO> pageResult = dvRepairLineService.getDvRepairLinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvRepairLine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvRepairLineList() {
       // mock 数据
       DvRepairLineDO dbDvRepairLine = randomPojo(DvRepairLineDO.class, o -> { // 等会查询到
           o.setRepairId(null);
           o.setSubjectId(null);
           o.setSubjectCode(null);
           o.setSubjectName(null);
           o.setSubjectType(null);
           o.setSubjectContent(null);
           o.setSubjectStandard(null);
           o.setMalfunction(null);
           o.setMalfunctionUrl(null);
           o.setRepairDes(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvRepairLineMapper.insert(dbDvRepairLine);
       // 测试 repairId 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setRepairId(null)));
       // 测试 subjectId 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectId(null)));
       // 测试 subjectCode 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectCode(null)));
       // 测试 subjectName 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectName(null)));
       // 测试 subjectType 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectType(null)));
       // 测试 subjectContent 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectContent(null)));
       // 测试 subjectStandard 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setSubjectStandard(null)));
       // 测试 malfunction 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setMalfunction(null)));
       // 测试 malfunctionUrl 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setMalfunctionUrl(null)));
       // 测试 repairDes 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setRepairDes(null)));
       // 测试 remark 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvRepairLineMapper.insert(cloneIgnoreId(dbDvRepairLine, o -> o.setCreateTime(null)));
       // 准备参数
       DvRepairLineExportReqVO reqVO = new DvRepairLineExportReqVO();
       reqVO.setRepairId(null);
       reqVO.setSubjectId(null);
       reqVO.setSubjectCode(null);
       reqVO.setSubjectName(null);
       reqVO.setSubjectType(null);
       reqVO.setSubjectContent(null);
       reqVO.setSubjectStandard(null);
       reqVO.setMalfunction(null);
       reqVO.setMalfunctionUrl(null);
       reqVO.setRepairDes(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvRepairLineDO> list = dvRepairLineService.getDvRepairLineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvRepairLine, list.get(0));
    }

}
