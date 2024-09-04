package com.dofast.module.wms.service.warehouse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.wms.controller.admin.warehouse.vo.*;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.warehouse.WarehouseMapper;
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
 * {@link WarehouseServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(WarehouseServiceImpl.class)
public class WarehouseServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WarehouseServiceImpl warehouseService;

    @Resource
    private WarehouseMapper warehouseMapper;

    @Test
    public void testCreateWarehouse_success() {
        // 准备参数
        WarehouseCreateReqVO reqVO = randomPojo(WarehouseCreateReqVO.class);

        // 调用
        Long warehouseId = warehouseService.createWarehouse(reqVO);
        // 断言
        assertNotNull(warehouseId);
        // 校验记录的属性是否正确
        WarehouseDO warehouse = warehouseMapper.selectById(warehouseId);
        assertPojoEquals(reqVO, warehouse);
    }

    @Test
    public void testUpdateWarehouse_success() {
        // mock 数据
        WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class);
        warehouseMapper.insert(dbWarehouse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WarehouseUpdateReqVO reqVO = randomPojo(WarehouseUpdateReqVO.class, o -> {
            o.setId(dbWarehouse.getId()); // 设置更新的 ID
        });

        // 调用
        warehouseService.updateWarehouse(reqVO);
        // 校验是否更新正确
        WarehouseDO warehouse = warehouseMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, warehouse);
    }

    @Test
    public void testUpdateWarehouse_notExists() {
        // 准备参数
        WarehouseUpdateReqVO reqVO = randomPojo(WarehouseUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> warehouseService.updateWarehouse(reqVO), WAREHOUSE_NOT_EXISTS);
    }

    @Test
    public void testDeleteWarehouse_success() {
        // mock 数据
        WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class);
        warehouseMapper.insert(dbWarehouse);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbWarehouse.getId();

        // 调用
        warehouseService.deleteWarehouse(id);
       // 校验数据不存在了
       assertNull(warehouseMapper.selectById(id));
    }

    @Test
    public void testDeleteWarehouse_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> warehouseService.deleteWarehouse(id), WAREHOUSE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWarehousePage() {
       // mock 数据
       WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class, o -> { // 等会查询到
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocation(null);
           o.setArea(null);
           o.setCharge(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
           o.setSendState(null);
           o.setSendCity(null);
           o.setSendDistrict(null);
           o.setSendStreet(null);
           o.setSendDetail(null);
           o.setSendName(null);
           o.setSendPhone(null);
           o.setSendMobile(null);
       });
       warehouseMapper.insert(dbWarehouse);
       // 测试 warehouseCode 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setWarehouseName(null)));
       // 测试 location 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setLocation(null)));
       // 测试 area 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setArea(null)));
       // 测试 charge 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setCharge(null)));
       // 测试 enableFlag 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setCreateTime(null)));
       // 测试 sendState 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendState(null)));
       // 测试 sendCity 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendCity(null)));
       // 测试 sendDistrict 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendDistrict(null)));
       // 测试 sendStreet 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendStreet(null)));
       // 测试 sendDetail 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendDetail(null)));
       // 测试 sendName 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendName(null)));
       // 测试 sendPhone 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendPhone(null)));
       // 测试 sendMobile 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendMobile(null)));
       // 准备参数
       WarehousePageReqVO reqVO = new WarehousePageReqVO();
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocation(null);
       reqVO.setArea(null);
       reqVO.setCharge(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setSendState(null);
       reqVO.setSendCity(null);
       reqVO.setSendDistrict(null);
       reqVO.setSendStreet(null);
       reqVO.setSendDetail(null);
       reqVO.setSendName(null);
       reqVO.setSendPhone(null);
       reqVO.setSendMobile(null);

       // 调用
       PageResult<WarehouseDO> pageResult = warehouseService.getWarehousePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbWarehouse, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWarehouseList() {
       // mock 数据
       WarehouseDO dbWarehouse = randomPojo(WarehouseDO.class, o -> { // 等会查询到
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocation(null);
           o.setArea(null);
           o.setCharge(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
           o.setSendState(null);
           o.setSendCity(null);
           o.setSendDistrict(null);
           o.setSendStreet(null);
           o.setSendDetail(null);
           o.setSendName(null);
           o.setSendPhone(null);
           o.setSendMobile(null);
       });
       warehouseMapper.insert(dbWarehouse);
       // 测试 warehouseCode 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setWarehouseName(null)));
       // 测试 location 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setLocation(null)));
       // 测试 area 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setArea(null)));
       // 测试 charge 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setCharge(null)));
       // 测试 enableFlag 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setCreateTime(null)));
       // 测试 sendState 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendState(null)));
       // 测试 sendCity 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendCity(null)));
       // 测试 sendDistrict 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendDistrict(null)));
       // 测试 sendStreet 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendStreet(null)));
       // 测试 sendDetail 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendDetail(null)));
       // 测试 sendName 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendName(null)));
       // 测试 sendPhone 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendPhone(null)));
       // 测试 sendMobile 不匹配
       warehouseMapper.insert(cloneIgnoreId(dbWarehouse, o -> o.setSendMobile(null)));
       // 准备参数
       WarehouseExportReqVO reqVO = new WarehouseExportReqVO();
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocation(null);
       reqVO.setArea(null);
       reqVO.setCharge(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setSendState(null);
       reqVO.setSendCity(null);
       reqVO.setSendDistrict(null);
       reqVO.setSendStreet(null);
       reqVO.setSendDetail(null);
       reqVO.setSendName(null);
       reqVO.setSendPhone(null);
       reqVO.setSendMobile(null);

       // 调用
       List<WarehouseDO> list = warehouseService.getWarehouseList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbWarehouse, list.get(0));
    }

}
