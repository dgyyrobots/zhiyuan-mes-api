package com.dofast.module.wiki.service.coursewarefile;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wiki.controller.admin.coursewarefile.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewarefile.CoursewareFileDO;
import com.dofast.module.wiki.dal.mysql.coursewarefile.CoursewareFileMapper;
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
 * {@link CoursewareFileServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(CoursewareFileServiceImpl.class)
public class CoursewareFileServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CoursewareFileServiceImpl coursewareFileService;

    @Resource
    private CoursewareFileMapper coursewareFileMapper;

    @Test
    public void testCreateCoursewareFile_success() {
        // 准备参数
        CoursewareFileCreateReqVO reqVO = randomPojo(CoursewareFileCreateReqVO.class);

        // 调用
        Long coursewareFileId = coursewareFileService.createCoursewareFile(reqVO);
        // 断言
        assertNotNull(coursewareFileId);
        // 校验记录的属性是否正确
        CoursewareFileDO coursewareFile = coursewareFileMapper.selectById(coursewareFileId);
        assertPojoEquals(reqVO, coursewareFile);
    }

    @Test
    public void testUpdateCoursewareFile_success() {
        // mock 数据
        CoursewareFileDO dbCoursewareFile = randomPojo(CoursewareFileDO.class);
        coursewareFileMapper.insert(dbCoursewareFile);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CoursewareFileUpdateReqVO reqVO = randomPojo(CoursewareFileUpdateReqVO.class, o -> {
            o.setId(dbCoursewareFile.getId()); // 设置更新的 ID
        });

        // 调用
        coursewareFileService.updateCoursewareFile(reqVO);
        // 校验是否更新正确
        CoursewareFileDO coursewareFile = coursewareFileMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, coursewareFile);
    }

    @Test
    public void testUpdateCoursewareFile_notExists() {
        // 准备参数
        CoursewareFileUpdateReqVO reqVO = randomPojo(CoursewareFileUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> coursewareFileService.updateCoursewareFile(reqVO), COURSEWARE_FILE_NOT_EXISTS);
    }

    @Test
    public void testDeleteCoursewareFile_success() {
        // mock 数据
        CoursewareFileDO dbCoursewareFile = randomPojo(CoursewareFileDO.class);
        coursewareFileMapper.insert(dbCoursewareFile);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCoursewareFile.getId();

        // 调用
        coursewareFileService.deleteCoursewareFile(id);
       // 校验数据不存在了
       assertNull(coursewareFileMapper.selectById(id));
    }

    @Test
    public void testDeleteCoursewareFile_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> coursewareFileService.deleteCoursewareFile(id), COURSEWARE_FILE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCoursewareFilePage() {
       // mock 数据
       CoursewareFileDO dbCoursewareFile = randomPojo(CoursewareFileDO.class, o -> { // 等会查询到
           o.setCoursewareId(null);
           o.setFilename(null);
           o.setFilepath(null);
           o.setFileSize(null);
           o.setCreateTime(null);
       });
       coursewareFileMapper.insert(dbCoursewareFile);
       // 测试 coursewareId 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setCoursewareId(null)));
       // 测试 filename 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setFilename(null)));
       // 测试 filepath 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setFilepath(null)));
       // 测试 fileSize 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setFileSize(null)));
       // 测试 createTime 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setCreateTime(null)));
       // 准备参数
       CoursewareFilePageReqVO reqVO = new CoursewareFilePageReqVO();
       reqVO.setCoursewareId(null);
       reqVO.setFilename(null);
       reqVO.setFilepath(null);
       reqVO.setFileSize(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<CoursewareFileDO> pageResult = coursewareFileService.getCoursewareFilePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCoursewareFile, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCoursewareFileList() {
       // mock 数据
       CoursewareFileDO dbCoursewareFile = randomPojo(CoursewareFileDO.class, o -> { // 等会查询到
           o.setCoursewareId(null);
           o.setFilename(null);
           o.setFilepath(null);
           o.setFileSize(null);
           o.setCreateTime(null);
       });
       coursewareFileMapper.insert(dbCoursewareFile);
       // 测试 coursewareId 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setCoursewareId(null)));
       // 测试 filename 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setFilename(null)));
       // 测试 filepath 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setFilepath(null)));
       // 测试 fileSize 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setFileSize(null)));
       // 测试 createTime 不匹配
       coursewareFileMapper.insert(cloneIgnoreId(dbCoursewareFile, o -> o.setCreateTime(null)));
       // 准备参数
       CoursewareFileExportReqVO reqVO = new CoursewareFileExportReqVO();
       reqVO.setCoursewareId(null);
       reqVO.setFilename(null);
       reqVO.setFilepath(null);
       reqVO.setFileSize(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<CoursewareFileDO> list = coursewareFileService.getCoursewareFileList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCoursewareFile, list.get(0));
    }

}
