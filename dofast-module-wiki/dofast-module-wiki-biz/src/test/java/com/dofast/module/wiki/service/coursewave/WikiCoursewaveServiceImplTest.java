package com.dofast.module.wiki.service.coursewave;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wiki.controller.admin.coursewave.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewave.WikiCoursewaveDO;
import com.dofast.module.wiki.dal.mysql.coursewave.WikiCoursewaveMapper;
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
 * {@link WikiCoursewaveServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(WikiCoursewaveServiceImpl.class)
public class WikiCoursewaveServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WikiCoursewaveServiceImpl coursewaveService;

    @Resource
    private WikiCoursewaveMapper coursewaveMapper;

    @Test
    public void testCreateCoursewave_success() {
        // 准备参数
        WikiCoursewaveCreateReqVO reqVO = randomPojo(WikiCoursewaveCreateReqVO.class);

        // 调用
        Long coursewaveId = coursewaveService.createCoursewave(reqVO);
        // 断言
        assertNotNull(coursewaveId);
        // 校验记录的属性是否正确
        WikiCoursewaveDO coursewave = coursewaveMapper.selectById(coursewaveId);
        assertPojoEquals(reqVO, coursewave);
    }

    @Test
    public void testUpdateCoursewave_success() {
        // mock 数据
        WikiCoursewaveDO dbCoursewave = randomPojo(WikiCoursewaveDO.class);
        coursewaveMapper.insert(dbCoursewave);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WikiCoursewaveUpdateReqVO reqVO = randomPojo(WikiCoursewaveUpdateReqVO.class, o -> {
            o.setId(dbCoursewave.getId()); // 设置更新的 ID
        });

        // 调用
        coursewaveService.updateCoursewave(reqVO);
        // 校验是否更新正确
        WikiCoursewaveDO coursewave = coursewaveMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, coursewave);
    }

    @Test
    public void testUpdateCoursewave_notExists() {
        // 准备参数
        WikiCoursewaveUpdateReqVO reqVO = randomPojo(WikiCoursewaveUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> coursewaveService.updateCoursewave(reqVO), COURSEWAVE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCoursewave_success() {
        // mock 数据
        WikiCoursewaveDO dbCoursewave = randomPojo(WikiCoursewaveDO.class);
        coursewaveMapper.insert(dbCoursewave);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCoursewave.getId();

        // 调用
        coursewaveService.deleteCoursewave(id);
       // 校验数据不存在了
       assertNull(coursewaveMapper.selectById(id));
    }

    @Test
    public void testDeleteCoursewave_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> coursewaveService.deleteCoursewave(id), COURSEWAVE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCoursewavePage() {
       // mock 数据
       WikiCoursewaveDO dbCoursewave = randomPojo(WikiCoursewaveDO.class, o -> { // 等会查询到
           o.setCategoryId(null);
           o.setCoursewareName(null);
           o.setCreateTime(null);
       });
       coursewaveMapper.insert(dbCoursewave);
       // 测试 categoryId 不匹配
       coursewaveMapper.insert(cloneIgnoreId(dbCoursewave, o -> o.setCategoryId(null)));
       // 测试 coursewareName 不匹配
       coursewaveMapper.insert(cloneIgnoreId(dbCoursewave, o -> o.setCoursewareName(null)));
       // 测试 createTime 不匹配
       coursewaveMapper.insert(cloneIgnoreId(dbCoursewave, o -> o.setCreateTime(null)));
       // 准备参数
       WikiCoursewavePageReqVO reqVO = new WikiCoursewavePageReqVO();
       reqVO.setCategoryId(null);
       reqVO.setCoursewareName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<WikiCoursewaveDO> pageResult = coursewaveService.getCoursewavePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCoursewave, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCoursewaveList() {
       // mock 数据
       WikiCoursewaveDO dbCoursewave = randomPojo(WikiCoursewaveDO.class, o -> { // 等会查询到
           o.setCategoryId(null);
           o.setCoursewareName(null);
           o.setCreateTime(null);
       });
       coursewaveMapper.insert(dbCoursewave);
       // 测试 categoryId 不匹配
       coursewaveMapper.insert(cloneIgnoreId(dbCoursewave, o -> o.setCategoryId(null)));
       // 测试 coursewareName 不匹配
       coursewaveMapper.insert(cloneIgnoreId(dbCoursewave, o -> o.setCoursewareName(null)));
       // 测试 createTime 不匹配
       coursewaveMapper.insert(cloneIgnoreId(dbCoursewave, o -> o.setCreateTime(null)));
       // 准备参数
       WikiCoursewaveExportReqVO reqVO = new WikiCoursewaveExportReqVO();
       reqVO.setCategoryId(null);
       reqVO.setCoursewareName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<WikiCoursewaveDO> list = coursewaveService.getCoursewaveList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCoursewave, list.get(0));
    }

}
