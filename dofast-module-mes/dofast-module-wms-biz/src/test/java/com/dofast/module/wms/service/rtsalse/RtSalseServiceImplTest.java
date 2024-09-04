package com.dofast.module.wms.service.rtsalse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.rtsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseDO;
import com.dofast.module.wms.dal.mysql.rtsalse.RtSalseMapper;
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
 * {@link RtSalseServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(RtSalseServiceImpl.class)
public class RtSalseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RtSalseServiceImpl rtSalseService;

    @Resource
    private RtSalseMapper rtSalseMapper;

    @Test
    public void testCreateRtSalse_success() {
        // 准备参数
        RtSalseCreateReqVO reqVO = randomPojo(RtSalseCreateReqVO.class);

        // 调用
        Long rtSalseId = rtSalseService.createRtSalse(reqVO);
        // 断言
        assertNotNull(rtSalseId);
        // 校验记录的属性是否正确
        RtSalseDO rtSalse = rtSalseMapper.selectById(rtSalseId);
        assertPojoEquals(reqVO, rtSalse);
    }

    @Test
    public void testUpdateRtSalse_success() {
        // mock 数据
        RtSalseDO dbRtSalse = randomPojo(RtSalseDO.class);
        rtSalseMapper.insert(dbRtSalse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RtSalseUpdateReqVO reqVO = randomPojo(RtSalseUpdateReqVO.class, o -> {
            o.setId(dbRtSalse.getId()); // 设置更新的 ID
        });

        // 调用
        rtSalseService.updateRtSalse(reqVO);
        // 校验是否更新正确
        RtSalseDO rtSalse = rtSalseMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, rtSalse);
    }

    @Test
    public void testUpdateRtSalse_notExists() {
        // 准备参数
        RtSalseUpdateReqVO reqVO = randomPojo(RtSalseUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rtSalseService.updateRtSalse(reqVO), RT_SALSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRtSalse_success() {
        // mock 数据
        RtSalseDO dbRtSalse = randomPojo(RtSalseDO.class);
        rtSalseMapper.insert(dbRtSalse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRtSalse.getId();

        // 调用
        rtSalseService.deleteRtSalse(id);
       // 校验数据不存在了
       assertNull(rtSalseMapper.selectById(id));
    }

    @Test
    public void testDeleteRtSalse_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rtSalseService.deleteRtSalse(id), RT_SALSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtSalsePage() {
       // mock 数据
       RtSalseDO dbRtSalse = randomPojo(RtSalseDO.class, o -> { // 等会查询到
           o.setRtCode(null);
           o.setRtName(null);
           o.setSoCode(null);
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
           o.setRtDate(null);
           o.setRtReason(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       rtSalseMapper.insert(dbRtSalse);
       // 测试 rtCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtCode(null)));
       // 测试 rtName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtName(null)));
       // 测试 soCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setSoCode(null)));
       // 测试 clientId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientNick(null)));
       // 测试 warehouseId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAreaName(null)));
       // 测试 rtDate 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtDate(null)));
       // 测试 rtReason 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtReason(null)));
       // 测试 status 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setCreateTime(null)));
       // 准备参数
       RtSalsePageReqVO reqVO = new RtSalsePageReqVO();
       reqVO.setRtCode(null);
       reqVO.setRtName(null);
       reqVO.setSoCode(null);
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
       reqVO.setRtDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRtReason(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RtSalseDO> pageResult = rtSalseService.getRtSalsePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRtSalse, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRtSalseList() {
       // mock 数据
       RtSalseDO dbRtSalse = randomPojo(RtSalseDO.class, o -> { // 等会查询到
           o.setRtCode(null);
           o.setRtName(null);
           o.setSoCode(null);
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
           o.setRtDate(null);
           o.setRtReason(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       rtSalseMapper.insert(dbRtSalse);
       // 测试 rtCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtCode(null)));
       // 测试 rtName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtName(null)));
       // 测试 soCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setSoCode(null)));
       // 测试 clientId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientId(null)));
       // 测试 clientCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientCode(null)));
       // 测试 clientName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientName(null)));
       // 测试 clientNick 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setClientNick(null)));
       // 测试 warehouseId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAreaName(null)));
       // 测试 rtDate 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtDate(null)));
       // 测试 rtReason 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRtReason(null)));
       // 测试 status 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       rtSalseMapper.insert(cloneIgnoreId(dbRtSalse, o -> o.setCreateTime(null)));
       // 准备参数
       RtSalseExportReqVO reqVO = new RtSalseExportReqVO();
       reqVO.setRtCode(null);
       reqVO.setRtName(null);
       reqVO.setSoCode(null);
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
       reqVO.setRtDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRtReason(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RtSalseDO> list = rtSalseService.getRtSalseList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRtSalse, list.get(0));
    }

}
