package com.dofast.module.cmms.service.dvmachinery;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvmachinery.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinery.DvMachineryDO;
import com.dofast.module.cmms.dal.mysql.dvmachinery.DvMachineryMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.cmms.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link DvMachineryServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvMachineryServiceImpl.class)
public class DvMachineryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvMachineryServiceImpl dvMachineryService;

    @Resource
    private DvMachineryMapper dvMachineryMapper;

    @Test
    public void testCreateDvMachinery_success() {
        // 准备参数
        DvMachineryCreateReqVO reqVO = randomPojo(DvMachineryCreateReqVO.class);

        // 调用
        Long dvMachineryId = dvMachineryService.createDvMachinery(reqVO);
        // 断言
        assertNotNull(dvMachineryId);
        // 校验记录的属性是否正确
        DvMachineryDO dvMachinery = dvMachineryMapper.selectById(dvMachineryId);
        assertPojoEquals(reqVO, dvMachinery);
    }

    @Test
    public void testUpdateDvMachinery_success() {
        // mock 数据
        DvMachineryDO dbDvMachinery = randomPojo(DvMachineryDO.class);
        dvMachineryMapper.insert(dbDvMachinery);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvMachineryUpdateReqVO reqVO = randomPojo(DvMachineryUpdateReqVO.class, o -> {
            o.setId(dbDvMachinery.getId()); // 设置更新的 ID
        });

        // 调用
        dvMachineryService.updateDvMachinery(reqVO);
        // 校验是否更新正确
        DvMachineryDO dvMachinery = dvMachineryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvMachinery);
    }

    @Test
    public void testUpdateDvMachinery_notExists() {
        // 准备参数
        DvMachineryUpdateReqVO reqVO = randomPojo(DvMachineryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvMachineryService.updateDvMachinery(reqVO), DV_MACHINERY_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvMachinery_success() {
        // mock 数据
        DvMachineryDO dbDvMachinery = randomPojo(DvMachineryDO.class);
        dvMachineryMapper.insert(dbDvMachinery);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvMachinery.getId();

        // 调用
        dvMachineryService.deleteDvMachinery(id);
       // 校验数据不存在了
       assertNull(dvMachineryMapper.selectById(id));
    }

    @Test
    public void testDeleteDvMachinery_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvMachineryService.deleteDvMachinery(id), DV_MACHINERY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvMachineryPage() {
       // mock 数据
       DvMachineryDO dbDvMachinery = randomPojo(DvMachineryDO.class, o -> { // 等会查询到
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setMachineryBrand(null);
           o.setMachinerySpec(null);
           o.setMachineryTypeId(null);
           o.setMachineryTypeCode(null);
           o.setMachineryTypeName(null);
           o.setWorkshopId(null);
           o.setWorkshopCode(null);
           o.setWorkshopName(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvMachineryMapper.insert(dbDvMachinery);
       // 测试 machineryCode 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryName(null)));
       // 测试 machineryBrand 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryBrand(null)));
       // 测试 machinerySpec 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachinerySpec(null)));
       // 测试 machineryTypeId 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryTypeId(null)));
       // 测试 machineryTypeCode 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryTypeCode(null)));
       // 测试 machineryTypeName 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryTypeName(null)));
       // 测试 workshopId 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setWorkshopId(null)));
       // 测试 workshopCode 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setWorkshopCode(null)));
       // 测试 workshopName 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setWorkshopName(null)));
       // 测试 status 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setCreateTime(null)));
       // 准备参数
       DvMachineryPageReqVO reqVO = new DvMachineryPageReqVO();
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setMachineryBrand(null);
       reqVO.setMachinerySpec(null);
       reqVO.setMachineryTypeId(null);
       reqVO.setMachineryTypeCode(null);
       reqVO.setMachineryTypeName(null);
       reqVO.setWorkshopId(null);
       reqVO.setWorkshopCode(null);
       reqVO.setWorkshopName(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvMachineryDO> pageResult = dvMachineryService.getDvMachineryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvMachinery, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvMachineryList() {
       // mock 数据
       DvMachineryDO dbDvMachinery = randomPojo(DvMachineryDO.class, o -> { // 等会查询到
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setMachineryBrand(null);
           o.setMachinerySpec(null);
           o.setMachineryTypeId(null);
           o.setMachineryTypeCode(null);
           o.setMachineryTypeName(null);
           o.setWorkshopId(null);
           o.setWorkshopCode(null);
           o.setWorkshopName(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvMachineryMapper.insert(dbDvMachinery);
       // 测试 machineryCode 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryName(null)));
       // 测试 machineryBrand 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryBrand(null)));
       // 测试 machinerySpec 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachinerySpec(null)));
       // 测试 machineryTypeId 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryTypeId(null)));
       // 测试 machineryTypeCode 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryTypeCode(null)));
       // 测试 machineryTypeName 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setMachineryTypeName(null)));
       // 测试 workshopId 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setWorkshopId(null)));
       // 测试 workshopCode 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setWorkshopCode(null)));
       // 测试 workshopName 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setWorkshopName(null)));
       // 测试 status 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvMachineryMapper.insert(cloneIgnoreId(dbDvMachinery, o -> o.setCreateTime(null)));
       // 准备参数
       DvMachineryExportReqVO reqVO = new DvMachineryExportReqVO();
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setMachineryBrand(null);
       reqVO.setMachinerySpec(null);
       reqVO.setMachineryTypeId(null);
       reqVO.setMachineryTypeCode(null);
       reqVO.setMachineryTypeName(null);
       reqVO.setWorkshopId(null);
       reqVO.setWorkshopCode(null);
       reqVO.setWorkshopName(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvMachineryDO> list = dvMachineryService.getDvMachineryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvMachinery, list.get(0));
    }

}
