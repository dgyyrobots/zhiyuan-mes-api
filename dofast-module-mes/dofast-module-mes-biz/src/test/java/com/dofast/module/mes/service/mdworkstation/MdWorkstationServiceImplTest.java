package com.dofast.module.mes.service.mdworkstation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.mdworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.dal.mysql.mdworkstation.MdWorkstationMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link MdWorkstationServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(MdWorkstationServiceImpl.class)
public class MdWorkstationServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MdWorkstationServiceImpl mdWorkstationService;

    @Resource
    private MdWorkstationMapper mdWorkstationMapper;

    @Test
    public void testCreateMdWorkstation_success() {
        // 准备参数
        MdWorkstationCreateReqVO reqVO = randomPojo(MdWorkstationCreateReqVO.class);

        // 调用
        Long mdWorkstationId = mdWorkstationService.createMdWorkstation(reqVO);
        // 断言
        assertNotNull(mdWorkstationId);
        // 校验记录的属性是否正确
        MdWorkstationDO mdWorkstation = mdWorkstationMapper.selectById(mdWorkstationId);
        assertPojoEquals(reqVO, mdWorkstation);
    }

    @Test
    public void testUpdateMdWorkstation_success() {
        // mock 数据
        MdWorkstationDO dbMdWorkstation = randomPojo(MdWorkstationDO.class);
        mdWorkstationMapper.insert(dbMdWorkstation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MdWorkstationUpdateReqVO reqVO = randomPojo(MdWorkstationUpdateReqVO.class, o -> {
            o.setId(dbMdWorkstation.getId()); // 设置更新的 ID
        });

        // 调用
        mdWorkstationService.updateMdWorkstation(reqVO);
        // 校验是否更新正确
        MdWorkstationDO mdWorkstation = mdWorkstationMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mdWorkstation);
    }

    @Test
    public void testUpdateMdWorkstation_notExists() {
        // 准备参数
        MdWorkstationUpdateReqVO reqVO = randomPojo(MdWorkstationUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationService.updateMdWorkstation(reqVO), MD_WORKSTATION_NOT_EXISTS);
    }

    @Test
    public void testDeleteMdWorkstation_success() {
        // mock 数据
        MdWorkstationDO dbMdWorkstation = randomPojo(MdWorkstationDO.class);
        mdWorkstationMapper.insert(dbMdWorkstation);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMdWorkstation.getId();

        // 调用
        mdWorkstationService.deleteMdWorkstation(id);
       // 校验数据不存在了
       assertNull(mdWorkstationMapper.selectById(id));
    }

    @Test
    public void testDeleteMdWorkstation_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationService.deleteMdWorkstation(id), MD_WORKSTATION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationPage() {
       // mock 数据
       MdWorkstationDO dbMdWorkstation = randomPojo(MdWorkstationDO.class, o -> { // 等会查询到
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setWorkstationAddress(null);
           o.setWorkshopId(null);
           o.setWorkshopCode(null);
           o.setWorkshopName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationMapper.insert(dbMdWorkstation);
       // 测试 workstationCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkstationName(null)));
       // 测试 workstationAddress 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkstationAddress(null)));
       // 测试 workshopId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkshopId(null)));
       // 测试 workshopCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkshopCode(null)));
       // 测试 workshopName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkshopName(null)));
       // 测试 processId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setProcessName(null)));
       // 测试 warehouseId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAreaName(null)));
       // 测试 enableFlag 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationPageReqVO reqVO = new MdWorkstationPageReqVO();
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setWorkstationAddress(null);
       reqVO.setWorkshopId(null);
       reqVO.setWorkshopCode(null);
       reqVO.setWorkshopName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MdWorkstationDO> pageResult = mdWorkstationService.getMdWorkstationPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMdWorkstation, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationList() {
       // mock 数据
       MdWorkstationDO dbMdWorkstation = randomPojo(MdWorkstationDO.class, o -> { // 等会查询到
           o.setWorkstationCode(null);
           o.setWorkstationName(null);
           o.setWorkstationAddress(null);
           o.setWorkshopId(null);
           o.setWorkshopCode(null);
           o.setWorkshopName(null);
           o.setProcessId(null);
           o.setProcessCode(null);
           o.setProcessName(null);
           o.setWarehouseId(null);
           o.setWarehouseCode(null);
           o.setWarehouseName(null);
           o.setLocationId(null);
           o.setLocationCode(null);
           o.setLocationName(null);
           o.setAreaId(null);
           o.setAreaCode(null);
           o.setAreaName(null);
           o.setEnableFlag(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationMapper.insert(dbMdWorkstation);
       // 测试 workstationCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkstationCode(null)));
       // 测试 workstationName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkstationName(null)));
       // 测试 workstationAddress 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkstationAddress(null)));
       // 测试 workshopId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkshopId(null)));
       // 测试 workshopCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkshopCode(null)));
       // 测试 workshopName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWorkshopName(null)));
       // 测试 processId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setProcessId(null)));
       // 测试 processCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setProcessCode(null)));
       // 测试 processName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setProcessName(null)));
       // 测试 warehouseId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWarehouseId(null)));
       // 测试 warehouseCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWarehouseCode(null)));
       // 测试 warehouseName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setWarehouseName(null)));
       // 测试 locationId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setLocationId(null)));
       // 测试 locationCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setLocationCode(null)));
       // 测试 locationName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setLocationName(null)));
       // 测试 areaId 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAreaId(null)));
       // 测试 areaCode 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAreaCode(null)));
       // 测试 areaName 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAreaName(null)));
       // 测试 enableFlag 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setEnableFlag(null)));
       // 测试 remark 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationMapper.insert(cloneIgnoreId(dbMdWorkstation, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationExportReqVO reqVO = new MdWorkstationExportReqVO();
       reqVO.setWorkstationCode(null);
       reqVO.setWorkstationName(null);
       reqVO.setWorkstationAddress(null);
       reqVO.setWorkshopId(null);
       reqVO.setWorkshopCode(null);
       reqVO.setWorkshopName(null);
       reqVO.setProcessId(null);
       reqVO.setProcessCode(null);
       reqVO.setProcessName(null);
       reqVO.setWarehouseId(null);
       reqVO.setWarehouseCode(null);
       reqVO.setWarehouseName(null);
       reqVO.setLocationId(null);
       reqVO.setLocationCode(null);
       reqVO.setLocationName(null);
       reqVO.setAreaId(null);
       reqVO.setAreaCode(null);
       reqVO.setAreaName(null);
       reqVO.setEnableFlag(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MdWorkstationDO> list = mdWorkstationService.getMdWorkstationList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMdWorkstation, list.get(0));
    }

}
