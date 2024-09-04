package com.dofast.module.cmms.service.dvsubject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvsubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvsubject.DvSubjectDO;
import com.dofast.module.cmms.dal.mysql.dvsubject.DvSubjectMapper;
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
 * {@link DvSubjectServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvSubjectServiceImpl.class)
public class DvSubjectServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvSubjectServiceImpl dvSubjectService;

    @Resource
    private DvSubjectMapper dvSubjectMapper;

    @Test
    public void testCreateDvSubject_success() {
        // 准备参数
        DvSubjectCreateReqVO reqVO = randomPojo(DvSubjectCreateReqVO.class);

        // 调用
        Long dvSubjectId = dvSubjectService.createDvSubject(reqVO);
        // 断言
        assertNotNull(dvSubjectId);
        // 校验记录的属性是否正确
        DvSubjectDO dvSubject = dvSubjectMapper.selectById(dvSubjectId);
        assertPojoEquals(reqVO, dvSubject);
    }

    @Test
    public void testUpdateDvSubject_success() {
        // mock 数据
        DvSubjectDO dbDvSubject = randomPojo(DvSubjectDO.class);
        dvSubjectMapper.insert(dbDvSubject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvSubjectUpdateReqVO reqVO = randomPojo(DvSubjectUpdateReqVO.class, o -> {
            o.setId(dbDvSubject.getId()); // 设置更新的 ID
        });

        // 调用
        dvSubjectService.updateDvSubject(reqVO);
        // 校验是否更新正确
        DvSubjectDO dvSubject = dvSubjectMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvSubject);
    }

    @Test
    public void testUpdateDvSubject_notExists() {
        // 准备参数
        DvSubjectUpdateReqVO reqVO = randomPojo(DvSubjectUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvSubjectService.updateDvSubject(reqVO), DV_SUBJECT_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvSubject_success() {
        // mock 数据
        DvSubjectDO dbDvSubject = randomPojo(DvSubjectDO.class);
        dvSubjectMapper.insert(dbDvSubject);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvSubject.getId();

        // 调用
        dvSubjectService.deleteDvSubject(id);
       // 校验数据不存在了
       assertNull(dvSubjectMapper.selectById(id));
    }

    @Test
    public void testDeleteDvSubject_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvSubjectService.deleteDvSubject(id), DV_SUBJECT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvSubjectPage() {
       // mock 数据
       DvSubjectDO dbDvSubject = randomPojo(DvSubjectDO.class, o -> { // 等会查询到
           o.setSubjectCode(null);
           o.setSubjectName(null);
           o.setSubjectType(null);
           o.setSubjectContent(null);
           o.setSubjectStandard(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvSubjectMapper.insert(dbDvSubject);
       // 测试 subjectCode 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectCode(null)));
       // 测试 subjectName 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectName(null)));
       // 测试 subjectType 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectType(null)));
       // 测试 subjectContent 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectContent(null)));
       // 测试 subjectStandard 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectStandard(null)));
       // 测试 enableFlag 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setCreateTime(null)));
       // 准备参数
       DvSubjectPageReqVO reqVO = new DvSubjectPageReqVO();
       reqVO.setSubjectCode(null);
       reqVO.setSubjectName(null);
       reqVO.setSubjectType(null);
       reqVO.setSubjectContent(null);
       reqVO.setSubjectStandard(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvSubjectDO> pageResult = dvSubjectService.getDvSubjectPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvSubject, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvSubjectList() {
       // mock 数据
       DvSubjectDO dbDvSubject = randomPojo(DvSubjectDO.class, o -> { // 等会查询到
           o.setSubjectCode(null);
           o.setSubjectName(null);
           o.setSubjectType(null);
           o.setSubjectContent(null);
           o.setSubjectStandard(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvSubjectMapper.insert(dbDvSubject);
       // 测试 subjectCode 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectCode(null)));
       // 测试 subjectName 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectName(null)));
       // 测试 subjectType 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectType(null)));
       // 测试 subjectContent 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectContent(null)));
       // 测试 subjectStandard 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setSubjectStandard(null)));
       // 测试 enableFlag 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvSubjectMapper.insert(cloneIgnoreId(dbDvSubject, o -> o.setCreateTime(null)));
       // 准备参数
       DvSubjectExportReqVO reqVO = new DvSubjectExportReqVO();
       reqVO.setSubjectCode(null);
       reqVO.setSubjectName(null);
       reqVO.setSubjectType(null);
       reqVO.setSubjectContent(null);
       reqVO.setSubjectStandard(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvSubjectDO> list = dvSubjectService.getDvSubjectList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvSubject, list.get(0));
    }

}
