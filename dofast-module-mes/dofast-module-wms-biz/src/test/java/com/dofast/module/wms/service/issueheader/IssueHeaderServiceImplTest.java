package com.dofast.module.wms.service.issueheader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.issueheader.vo.*;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.dal.mysql.issueheader.IssueHeaderMapper;
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
 * {@link IssueHeaderServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(IssueHeaderServiceImpl.class)
public class IssueHeaderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IssueHeaderServiceImpl issueHeaderService;

    @Resource
    private IssueHeaderMapper issueHeaderMapper;

    @Test
    public void testCreateIssueHeader_success() {
        // 准备参数
        IssueHeaderCreateReqVO reqVO = randomPojo(IssueHeaderCreateReqVO.class);

        // 调用
        Long issueHeaderId = issueHeaderService.createIssueHeader(reqVO);
        // 断言
        assertNotNull(issueHeaderId);
        // 校验记录的属性是否正确
        IssueHeaderDO issueHeader = issueHeaderMapper.selectById(issueHeaderId);
        assertPojoEquals(reqVO, issueHeader);
    }

    @Test
    public void testUpdateIssueHeader_success() {
        // mock 数据
        IssueHeaderDO dbIssueHeader = randomPojo(IssueHeaderDO.class);
        issueHeaderMapper.insert(dbIssueHeader);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IssueHeaderUpdateReqVO reqVO = randomPojo(IssueHeaderUpdateReqVO.class, o -> {
            o.setId(dbIssueHeader.getId()); // 设置更新的 ID
        });

        // 调用
        issueHeaderService.updateIssueHeader(reqVO);
        // 校验是否更新正确
        IssueHeaderDO issueHeader = issueHeaderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, issueHeader);
    }

    @Test
    public void testUpdateIssueHeader_notExists() {
        // 准备参数
        IssueHeaderUpdateReqVO reqVO = randomPojo(IssueHeaderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> issueHeaderService.updateIssueHeader(reqVO), ISSUE_HEADER_NOT_EXISTS);
    }

    @Test
    public void testDeleteIssueHeader_success() {
        // mock 数据
        IssueHeaderDO dbIssueHeader = randomPojo(IssueHeaderDO.class);
        issueHeaderMapper.insert(dbIssueHeader);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIssueHeader.getId();

        // 调用
        issueHeaderService.deleteIssueHeader(id);
       // 校验数据不存在了
       assertNull(issueHeaderMapper.selectById(id));
    }

    @Test
    public void testDeleteIssueHeader_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> issueHeaderService.deleteIssueHeader(id), ISSUE_HEADER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIssueHeaderPage() {
       // mock 数据
       IssueHeaderDO dbIssueHeader = randomPojo(IssueHeaderDO.class, o -> { // 等会查询到
           o.setIssueCode(null);
           o.setIssueName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setIssueDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       issueHeaderMapper.insert(dbIssueHeader);
       // 测试 issueCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setIssueCode(null)));
       // 测试 issueName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setIssueName(null)));
       // 测试 workstationId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkstationName(null)));
       // 测试 workorderId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkorderCode(null)));
       // 测试 taskId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setTaskCode(null)));
       // 测试 clientId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientNick(null)));
       // 测试 warehouseId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAreaName(null)));
       // 测试 issueDate 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setIssueDate(null)));
       // 测试 status 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setCreateTime(null)));
       // 准备参数
       IssueHeaderPageReqVO reqVO = new IssueHeaderPageReqVO();
       reqVO.setIssueCode(null);
       reqVO.setIssueName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setIssueDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IssueHeaderDO> pageResult = issueHeaderService.getIssueHeaderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIssueHeader, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIssueHeaderList() {
       // mock 数据
       IssueHeaderDO dbIssueHeader = randomPojo(IssueHeaderDO.class, o -> { // 等会查询到
           o.setIssueCode(null);
           o.setIssueName(null);
           o.setWorkstationId(null);
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setTaskId(null);
           o.setTaskCode(null);
           o.setClientId(null);
           o.setClientCode(null);
           o.setClientName(null);
           o.setClientNick(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setIssueDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       issueHeaderMapper.insert(dbIssueHeader);
       // 测试 issueCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setIssueCode(null)));
       // 测试 issueName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setIssueName(null)));
       // 测试 workstationId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkstationId(null)));
       // 测试 workstationCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkstationName(null)));
       // 测试 workorderId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWorkorderCode(null)));
       // 测试 taskId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setTaskId(null)));
       // 测试 taskCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setTaskCode(null)));
       // 测试 clientId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setClientNick(null)));
       // 测试 warehouseId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAreaName(null)));
       // 测试 issueDate 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setIssueDate(null)));
       // 测试 status 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       issueHeaderMapper.insert(cloneIgnoreId(dbIssueHeader, o -> o.setCreateTime(null)));
       // 准备参数
       IssueHeaderExportReqVO reqVO = new IssueHeaderExportReqVO();
       reqVO.setIssueCode(null);
       reqVO.setIssueName(null);
       reqVO.setWorkstationId(null);
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setTaskId(null);
       reqVO.setTaskCode(null);
       reqVO.setClientId(null);
       reqVO.setClientCode(null);
       reqVO.setClientName(null);
       reqVO.setClientNick(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setIssueDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IssueHeaderDO> list = issueHeaderService.getIssueHeaderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIssueHeader, list.get(0));
    }

}
