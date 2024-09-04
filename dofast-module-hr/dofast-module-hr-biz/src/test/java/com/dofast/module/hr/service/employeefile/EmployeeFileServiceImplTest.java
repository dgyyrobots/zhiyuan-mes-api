package com.dofast.module.hr.service.employeefile;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.hr.controller.admin.employeefile.vo.*;
import com.dofast.module.hr.dal.dataobject.employeefile.EmployeeFileDO;
import com.dofast.module.hr.dal.mysql.employeefile.EmployeeFileMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.hr.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link EmployeeFileServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(EmployeeFileServiceImpl.class)
public class EmployeeFileServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmployeeFileServiceImpl employeeFileService;

    @Resource
    private EmployeeFileMapper employeeFileMapper;

    @Test
    public void testCreateEmployeeFile_success() {
        // 准备参数
        EmployeeFileCreateReqVO reqVO = randomPojo(EmployeeFileCreateReqVO.class);

        // 调用
        Long employeeFileId = employeeFileService.createEmployeeFile(reqVO);
        // 断言
        assertNotNull(employeeFileId);
        // 校验记录的属性是否正确
        EmployeeFileDO employeeFile = employeeFileMapper.selectById(employeeFileId);
        assertPojoEquals(reqVO, employeeFile);
    }

    @Test
    public void testUpdateEmployeeFile_success() {
        // mock 数据
        EmployeeFileDO dbEmployeeFile = randomPojo(EmployeeFileDO.class);
        employeeFileMapper.insert(dbEmployeeFile);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmployeeFileUpdateReqVO reqVO = randomPojo(EmployeeFileUpdateReqVO.class, o -> {
            o.setId(dbEmployeeFile.getId()); // 设置更新的 ID
        });

        // 调用
        employeeFileService.updateEmployeeFile(reqVO);
        // 校验是否更新正确
        EmployeeFileDO employeeFile = employeeFileMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, employeeFile);
    }

    @Test
    public void testUpdateEmployeeFile_notExists() {
        // 准备参数
        EmployeeFileUpdateReqVO reqVO = randomPojo(EmployeeFileUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> employeeFileService.updateEmployeeFile(reqVO), EMPLOYEE_FILE_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmployeeFile_success() {
        // mock 数据
        EmployeeFileDO dbEmployeeFile = randomPojo(EmployeeFileDO.class);
        employeeFileMapper.insert(dbEmployeeFile);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmployeeFile.getId();

        // 调用
        employeeFileService.deleteEmployeeFile(id);
       // 校验数据不存在了
       assertNull(employeeFileMapper.selectById(id));
    }

    @Test
    public void testDeleteEmployeeFile_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> employeeFileService.deleteEmployeeFile(id), EMPLOYEE_FILE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeFilePage() {
       // mock 数据
       EmployeeFileDO dbEmployeeFile = randomPojo(EmployeeFileDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
       });
       employeeFileMapper.insert(dbEmployeeFile);
       // 测试 createTime 不匹配
       employeeFileMapper.insert(cloneIgnoreId(dbEmployeeFile, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeFilePageReqVO reqVO = new EmployeeFilePageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<EmployeeFileDO> pageResult = employeeFileService.getEmployeeFilePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmployeeFile, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeFileList() {
       // mock 数据
       EmployeeFileDO dbEmployeeFile = randomPojo(EmployeeFileDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
       });
       employeeFileMapper.insert(dbEmployeeFile);
       // 测试 createTime 不匹配
       employeeFileMapper.insert(cloneIgnoreId(dbEmployeeFile, o -> o.setCreateTime(null)));
       // 准备参数
       EmployeeFileExportReqVO reqVO = new EmployeeFileExportReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<EmployeeFileDO> list = employeeFileService.getEmployeeFileList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmployeeFile, list.get(0));
    }

}
