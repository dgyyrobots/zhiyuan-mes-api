package com.dofast.module.cmms.service.dvchecksubject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvchecksubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvchecksubject.DvCheckSubjectDO;
import com.dofast.module.cmms.dal.mysql.dvchecksubject.DvCheckSubjectMapper;
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
 * {@link DvCheckSubjectServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvCheckSubjectServiceImpl.class)
public class DvCheckSubjectServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvCheckSubjectServiceImpl dvCheckSubjectService;

    @Resource
    private DvCheckSubjectMapper dvCheckSubjectMapper;

    @Test
    public void testCreateDvCheckSubject_success() {
        // 准备参数
        DvCheckSubjectCreateReqVO reqVO = randomPojo(DvCheckSubjectCreateReqVO.class);

        // 调用
        Long dvCheckSubjectId = dvCheckSubjectService.createDvCheckSubject(reqVO);
        // 断言
        assertNotNull(dvCheckSubjectId);
        // 校验记录的属性是否正确
        DvCheckSubjectDO dvCheckSubject = dvCheckSubjectMapper.selectById(dvCheckSubjectId);
        assertPojoEquals(reqVO, dvCheckSubject);
    }

    @Test
    public void testUpdateDvCheckSubject_success() {
        // mock 数据
        DvCheckSubjectDO dbDvCheckSubject = randomPojo(DvCheckSubjectDO.class);
        dvCheckSubjectMapper.insert(dbDvCheckSubject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvCheckSubjectUpdateReqVO reqVO = randomPojo(DvCheckSubjectUpdateReqVO.class, o -> {
            o.setId(dbDvCheckSubject.getId()); // 设置更新的 ID
        });

        // 调用
        dvCheckSubjectService.updateDvCheckSubject(reqVO);
        // 校验是否更新正确
        DvCheckSubjectDO dvCheckSubject = dvCheckSubjectMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvCheckSubject);
    }

    @Test
    public void testUpdateDvCheckSubject_notExists() {
        // 准备参数
        DvCheckSubjectUpdateReqVO reqVO = randomPojo(DvCheckSubjectUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvCheckSubjectService.updateDvCheckSubject(reqVO), DV_CHECK_SUBJECT_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvCheckSubject_success() {
        // mock 数据
        DvCheckSubjectDO dbDvCheckSubject = randomPojo(DvCheckSubjectDO.class);
        dvCheckSubjectMapper.insert(dbDvCheckSubject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvCheckSubject.getId();

        // 调用
        dvCheckSubjectService.deleteDvCheckSubject(id);
       // 校验数据不存在了
       assertNull(dvCheckSubjectMapper.selectById(id));
    }

    @Test
    public void testDeleteDvCheckSubject_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvCheckSubjectService.deleteDvCheckSubject(id), DV_CHECK_SUBJECT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvCheckSubjectPage() {
       // mock 数据
       DvCheckSubjectDO dbDvCheckSubject = randomPojo(DvCheckSubjectDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setSubjectId(null);
           o.setSubjectCode(null);
           o.setSubjectName(null);
           o.setSubjectType(null);
           o.setSubjectContent(null);
           o.setSubjectStandard(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvCheckSubjectMapper.insert(dbDvCheckSubject);
       // 测试 planId 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setPlanId(null)));
       // 测试 subjectId 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectId(null)));
       // 测试 subjectCode 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectCode(null)));
       // 测试 subjectName 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectName(null)));
       // 测试 subjectType 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectType(null)));
       // 测试 subjectContent 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectContent(null)));
       // 测试 subjectStandard 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectStandard(null)));
       // 测试 remark 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setCreateTime(null)));
       // 准备参数
       DvCheckSubjectPageReqVO reqVO = new DvCheckSubjectPageReqVO();
       reqVO.setPlanId(null);
       reqVO.setSubjectId(null);
       reqVO.setSubjectCode(null);
       reqVO.setSubjectName(null);
       reqVO.setSubjectType(null);
       reqVO.setSubjectContent(null);
       reqVO.setSubjectStandard(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvCheckSubjectDO> pageResult = dvCheckSubjectService.getDvCheckSubjectPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvCheckSubject, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvCheckSubjectList() {
       // mock 数据
       DvCheckSubjectDO dbDvCheckSubject = randomPojo(DvCheckSubjectDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setSubjectId(null);
           o.setSubjectCode(null);
           o.setSubjectName(null);
           o.setSubjectType(null);
           o.setSubjectContent(null);
           o.setSubjectStandard(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvCheckSubjectMapper.insert(dbDvCheckSubject);
       // 测试 planId 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setPlanId(null)));
       // 测试 subjectId 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectId(null)));
       // 测试 subjectCode 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectCode(null)));
       // 测试 subjectName 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectName(null)));
       // 测试 subjectType 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectType(null)));
       // 测试 subjectContent 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectContent(null)));
       // 测试 subjectStandard 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setSubjectStandard(null)));
       // 测试 remark 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvCheckSubjectMapper.insert(cloneIgnoreId(dbDvCheckSubject, o -> o.setCreateTime(null)));
       // 准备参数
       DvCheckSubjectExportReqVO reqVO = new DvCheckSubjectExportReqVO();
       reqVO.setPlanId(null);
       reqVO.setSubjectId(null);
       reqVO.setSubjectCode(null);
       reqVO.setSubjectName(null);
       reqVO.setSubjectType(null);
       reqVO.setSubjectContent(null);
       reqVO.setSubjectStandard(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvCheckSubjectDO> list = dvCheckSubjectService.getDvCheckSubjectList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvCheckSubject, list.get(0));
    }

}
