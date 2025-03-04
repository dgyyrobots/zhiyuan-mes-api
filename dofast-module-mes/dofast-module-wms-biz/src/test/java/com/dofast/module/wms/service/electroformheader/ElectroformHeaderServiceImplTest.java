package com.dofast.module.wms.service.electroformheader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.electroformheader.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformheader.ElectroformHeaderDO;
import com.dofast.module.wms.dal.mysql.electroformheader.ElectroformHeaderMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ElectroformHeaderServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ElectroformHeaderServiceImpl.class)
public class ElectroformHeaderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ElectroformHeaderServiceImpl electroformHeaderService;

    @Resource
    private ElectroformHeaderMapper electroformHeaderMapper;

    @Test
    public void testCreateElectroformHeader_success() {
        // 准备参数
        ElectroformHeaderCreateReqVO reqVO = randomPojo(ElectroformHeaderCreateReqVO.class);

        // 调用
        Long electroformHeaderId = electroformHeaderService.createElectroformHeader(reqVO);
        // 断言
        assertNotNull(electroformHeaderId);
        // 校验记录的属性是否正确
        ElectroformHeaderDO electroformHeader = electroformHeaderMapper.selectById(electroformHeaderId);
        assertPojoEquals(reqVO, electroformHeader);
    }

    @Test
    public void testUpdateElectroformHeader_success() {
        // mock 数据
        ElectroformHeaderDO dbElectroformHeader = randomPojo(ElectroformHeaderDO.class);
        electroformHeaderMapper.insert(dbElectroformHeader);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ElectroformHeaderUpdateReqVO reqVO = randomPojo(ElectroformHeaderUpdateReqVO.class, o -> {
            o.setId(dbElectroformHeader.getId()); // 设置更新的 ID
        });

        // 调用
        electroformHeaderService.updateElectroformHeader(reqVO);
        // 校验是否更新正确
        ElectroformHeaderDO electroformHeader = electroformHeaderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, electroformHeader);
    }

    @Test
    public void testUpdateElectroformHeader_notExists() {
        // 准备参数
        ElectroformHeaderUpdateReqVO reqVO = randomPojo(ElectroformHeaderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> electroformHeaderService.updateElectroformHeader(reqVO), ELECTROFORM_HEADER_NOT_EXISTS);
    }

    @Test
    public void testDeleteElectroformHeader_success() {
        // mock 数据
        ElectroformHeaderDO dbElectroformHeader = randomPojo(ElectroformHeaderDO.class);
        electroformHeaderMapper.insert(dbElectroformHeader);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbElectroformHeader.getId();

        // 调用
        electroformHeaderService.deleteElectroformHeader(id);
       // 校验数据不存在了
       assertNull(electroformHeaderMapper.selectById(id));
    }

    @Test
    public void testDeleteElectroformHeader_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> electroformHeaderService.deleteElectroformHeader(id), ELECTROFORM_HEADER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetElectroformHeaderPage() {
       // mock 数据
       ElectroformHeaderDO dbElectroformHeader = randomPojo(ElectroformHeaderDO.class, o -> { // 等会查询到
           o.setIssueCode(null);
           o.setIssueName(null);
           o.setIssueDate(null);
           o.setStatus(null);
           o.setCreateTime(null);
           o.setMachineryName(null);
           o.setMachineryCode(null);
           o.setMachineryId(null);
       });
       electroformHeaderMapper.insert(dbElectroformHeader);
       // 测试 issueCode 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setIssueCode(null)));
       // 测试 issueName 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setIssueName(null)));
       // 测试 issueDate 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setIssueDate(null)));
       // 测试 status 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setCreateTime(null)));
       // 测试 machineryName 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setMachineryName(null)));
       // 测试 machineryCode 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setMachineryCode(null)));
       // 测试 machineryId 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setMachineryId(null)));
       // 准备参数
       ElectroformHeaderPageReqVO reqVO = new ElectroformHeaderPageReqVO();
       reqVO.setIssueCode(null);
       reqVO.setIssueName(null);
       reqVO.setIssueDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setMachineryName(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryId(null);

       // 调用
       PageResult<ElectroformHeaderDO> pageResult = electroformHeaderService.getElectroformHeaderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbElectroformHeader, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetElectroformHeaderList() {
       // mock 数据
       ElectroformHeaderDO dbElectroformHeader = randomPojo(ElectroformHeaderDO.class, o -> { // 等会查询到
           o.setIssueCode(null);
           o.setIssueName(null);
           o.setIssueDate(null);
           o.setStatus(null);
           o.setCreateTime(null);
           o.setMachineryName(null);
           o.setMachineryCode(null);
           o.setMachineryId(null);
       });
       electroformHeaderMapper.insert(dbElectroformHeader);
       // 测试 issueCode 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setIssueCode(null)));
       // 测试 issueName 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setIssueName(null)));
       // 测试 issueDate 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setIssueDate(null)));
       // 测试 status 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setCreateTime(null)));
       // 测试 machineryName 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setMachineryName(null)));
       // 测试 machineryCode 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setMachineryCode(null)));
       // 测试 machineryId 不匹配
       electroformHeaderMapper.insert(cloneIgnoreId(dbElectroformHeader, o -> o.setMachineryId(null)));
       // 准备参数
       ElectroformHeaderExportReqVO reqVO = new ElectroformHeaderExportReqVO();
       reqVO.setIssueCode(null);
       reqVO.setIssueName(null);
       reqVO.setIssueDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setMachineryName(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryId(null);

       // 调用
       List<ElectroformHeaderDO> list = electroformHeaderService.getElectroformHeaderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbElectroformHeader, list.get(0));
    }

}
