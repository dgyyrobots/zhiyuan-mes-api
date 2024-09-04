package com.dofast.module.wiki.service.lecturer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wiki.controller.admin.lecturer.vo.*;
import com.dofast.module.wiki.dal.dataobject.lecturer.WikiLecturerDO;
import com.dofast.module.wiki.dal.mysql.lecturer.WikiLecturerMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.wiki.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link WikiLecturerServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(WikiLecturerServiceImpl.class)
public class WikiLecturerServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WikiLecturerServiceImpl lecturerService;

    @Resource
    private WikiLecturerMapper lecturerMapper;

    @Test
    public void testCreateLecturer_success() {
        // 准备参数
        WikiLecturerCreateReqVO reqVO = randomPojo(WikiLecturerCreateReqVO.class);

        // 调用
        Long lecturerId = lecturerService.createLecturer(reqVO);
        // 断言
        assertNotNull(lecturerId);
        // 校验记录的属性是否正确
        WikiLecturerDO lecturer = lecturerMapper.selectById(lecturerId);
        assertPojoEquals(reqVO, lecturer);
    }

    @Test
    public void testUpdateLecturer_success() {
        // mock 数据
        WikiLecturerDO dbLecturer = randomPojo(WikiLecturerDO.class);
        lecturerMapper.insert(dbLecturer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WikiLecturerUpdateReqVO reqVO = randomPojo(WikiLecturerUpdateReqVO.class, o -> {
            o.setId(dbLecturer.getId()); // 设置更新的 ID
        });

        // 调用
        lecturerService.updateLecturer(reqVO);
        // 校验是否更新正确
        WikiLecturerDO lecturer = lecturerMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, lecturer);
    }

    @Test
    public void testUpdateLecturer_notExists() {
        // 准备参数
        WikiLecturerUpdateReqVO reqVO = randomPojo(WikiLecturerUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> lecturerService.updateLecturer(reqVO), LECTURER_NOT_EXISTS);
    }

    @Test
    public void testDeleteLecturer_success() {
        // mock 数据
        WikiLecturerDO dbLecturer = randomPojo(WikiLecturerDO.class);
        lecturerMapper.insert(dbLecturer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbLecturer.getId();

        // 调用
        lecturerService.deleteLecturer(id);
       // 校验数据不存在了
       assertNull(lecturerMapper.selectById(id));
    }

    @Test
    public void testDeleteLecturer_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> lecturerService.deleteLecturer(id), LECTURER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLecturerPage() {
       // mock 数据
       WikiLecturerDO dbLecturer = randomPojo(WikiLecturerDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setDirection(null);
           o.setPicture(null);
           o.setCreateTime(null);
       });
       lecturerMapper.insert(dbLecturer);
       // 测试 name 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setName(null)));
       // 测试 direction 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setDirection(null)));
       // 测试 picture 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setPicture(null)));
       // 测试 createTime 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setCreateTime(null)));
       // 准备参数
       WikiLecturerPageReqVO reqVO = new WikiLecturerPageReqVO();
       reqVO.setName(null);
       reqVO.setDirection(null);
       reqVO.setPicture(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<WikiLecturerDO> pageResult = lecturerService.getLecturerPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbLecturer, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLecturerList() {
       // mock 数据
       WikiLecturerDO dbLecturer = randomPojo(WikiLecturerDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setDirection(null);
           o.setPicture(null);
           o.setCreateTime(null);
       });
       lecturerMapper.insert(dbLecturer);
       // 测试 name 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setName(null)));
       // 测试 direction 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setDirection(null)));
       // 测试 picture 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setPicture(null)));
       // 测试 createTime 不匹配
       lecturerMapper.insert(cloneIgnoreId(dbLecturer, o -> o.setCreateTime(null)));
       // 准备参数
       WikiLecturerExportReqVO reqVO = new WikiLecturerExportReqVO();
       reqVO.setName(null);
       reqVO.setDirection(null);
       reqVO.setPicture(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<WikiLecturerDO> list = lecturerService.getLecturerList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbLecturer, list.get(0));
    }

}
