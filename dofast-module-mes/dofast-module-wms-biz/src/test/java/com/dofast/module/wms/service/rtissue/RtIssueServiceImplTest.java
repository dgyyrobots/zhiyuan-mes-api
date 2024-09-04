package com.dofast.module.wms.service.rtissue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.rtissue.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;
import com.dofast.module.wms.dal.mysql.rtissue.RtIssueMapper;
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
 * {@link RtIssueServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RtIssueServiceImpl.class)
public class RtIssueServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RtIssueServiceImpl rtIssueService;

    @Resource
    private RtIssueMapper rtIssueMapper;

    @Test
    public void testCreateRtIssue_success() {
        // 准备参数
        RtIssueCreateReqVO reqVO = randomPojo(RtIssueCreateReqVO.class);

        // 调用
        Long rtIssueId = rtIssueService.createRtIssue(reqVO);
        // 断言
        assertNotNull(rtIssueId);
        // 校验记录的属性是否正确
        RtIssueDO rtIssue = rtIssueMapper.selectById(rtIssueId);
        assertPojoEquals(reqVO, rtIssue);
    }

    @Test
    public void testUpdateRtIssue_success() {
        // mock 数据
        RtIssueDO dbRtIssue = randomPojo(RtIssueDO.class);
        rtIssueMapper.insert(dbRtIssue);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RtIssueUpdateReqVO reqVO = randomPojo(RtIssueUpdateReqVO.class, o -> {
            o.setId(dbRtIssue.getId()); // 设置更新的 ID
        });

        // 调用
        rtIssueService.updateRtIssue(reqVO);
        // 校验是否更新正确
        RtIssueDO rtIssue = rtIssueMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, rtIssue);
    }

    @Test
    public void testUpdateRtIssue_notExists() {
        // 准备参数
        RtIssueUpdateReqVO reqVO = randomPojo(RtIssueUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rtIssueService.updateRtIssue(reqVO), RT_ISSUE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRtIssue_success() {
        // mock 数据
        RtIssueDO dbRtIssue = randomPojo(RtIssueDO.class);
        rtIssueMapper.insert(dbRtIssue);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRtIssue.getId();

        // 调用
        rtIssueService.deleteRtIssue(id);
       // 校验数据不存在了
       assertNull(rtIssueMapper.selectById(id));
    }

    @Test
    public void testDeleteRtIssue_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rtIssueService.deleteRtIssue(id), RT_ISSUE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtIssuePage() {
       // mock 数据
       RtIssueDO dbRtIssue = randomPojo(RtIssueDO.class, o -> { // 等会查询到
           o.setRtCode(null);
           o.setRtName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setRtDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
           o.setUpdater(null);
       });
       rtIssueMapper.insert(dbRtIssue);
       // 测试 rtCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRtCode(null)));
       // 测试 rtName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRtName(null)));
       // 测试 workorderId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWorkorderCode(null)));
       // 测试 warehouseId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAreaName(null)));
       // 测试 rtDate 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRtDate(null)));
       // 测试 status 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setCreateTime(null)));
       // 测试 updator 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setUpdater(null)));
       // 准备参数
       RtIssuePageReqVO reqVO = new RtIssuePageReqVO();
       reqVO.setRtCode(null);
       reqVO.setRtName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setRtDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUpdator(null);

       // 调用
       PageResult<RtIssueDO> pageResult = rtIssueService.getRtIssuePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRtIssue, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtIssueList() {
       // mock 数据
       RtIssueDO dbRtIssue = randomPojo(RtIssueDO.class, o -> { // 等会查询到
           o.setRtCode(null);
           o.setRtName(null);
           o.setWorkorderId(null);
           o.setWorkorderCode(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setRtDate(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
           o.setUpdater(null);
       });
       rtIssueMapper.insert(dbRtIssue);
       // 测试 rtCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRtCode(null)));
       // 测试 rtName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRtName(null)));
       // 测试 workorderId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWorkorderId(null)));
       // 测试 workorderCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWorkorderCode(null)));
       // 测试 warehouseId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAreaName(null)));
       // 测试 rtDate 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRtDate(null)));
       // 测试 status 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setCreateTime(null)));
       // 测试 updator 不匹配
       rtIssueMapper.insert(cloneIgnoreId(dbRtIssue, o -> o.setUpdater(null)));
       // 准备参数
       RtIssueExportReqVO reqVO = new RtIssueExportReqVO();
       reqVO.setRtCode(null);
       reqVO.setRtName(null);
       reqVO.setWorkorderId(null);
       reqVO.setWorkorderCode(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setRtDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUpdator(null);

       // 调用
       List<RtIssueDO> list = rtIssueService.getRtIssueList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRtIssue, list.get(0));
    }

}
