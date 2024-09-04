package com.dofast.module.cmms.service.dvcheckmachinery;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvcheckmachinery.DvCheckMachineryDO;
import com.dofast.module.cmms.dal.mysql.dvcheckmachinery.DvCheckMachineryMapper;
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
 * {@link DvCheckMachineryServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvCheckMachineryServiceImpl.class)
public class DvCheckMachineryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvCheckMachineryServiceImpl dvCheckMachineryService;

    @Resource
    private DvCheckMachineryMapper dvCheckMachineryMapper;

    @Test
    public void testCreateDvCheckMachinery_success() {
        // 准备参数
        DvCheckMachineryCreateReqVO reqVO = randomPojo(DvCheckMachineryCreateReqVO.class);

        // 调用
        Long dvCheckMachineryId = dvCheckMachineryService.createDvCheckMachinery(reqVO);
        // 断言
        assertNotNull(dvCheckMachineryId);
        // 校验记录的属性是否正确
        DvCheckMachineryDO dvCheckMachinery = dvCheckMachineryMapper.selectById(dvCheckMachineryId);
        assertPojoEquals(reqVO, dvCheckMachinery);
    }

    @Test
    public void testUpdateDvCheckMachinery_success() {
        // mock 数据
        DvCheckMachineryDO dbDvCheckMachinery = randomPojo(DvCheckMachineryDO.class);
        dvCheckMachineryMapper.insert(dbDvCheckMachinery);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvCheckMachineryUpdateReqVO reqVO = randomPojo(DvCheckMachineryUpdateReqVO.class, o -> {
            o.setId(dbDvCheckMachinery.getId()); // 设置更新的 ID
        });

        // 调用
        dvCheckMachineryService.updateDvCheckMachinery(reqVO);
        // 校验是否更新正确
        DvCheckMachineryDO dvCheckMachinery = dvCheckMachineryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvCheckMachinery);
    }

    @Test
    public void testUpdateDvCheckMachinery_notExists() {
        // 准备参数
        DvCheckMachineryUpdateReqVO reqVO = randomPojo(DvCheckMachineryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvCheckMachineryService.updateDvCheckMachinery(reqVO), DV_CHECK_MACHINERY_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvCheckMachinery_success() {
        // mock 数据
        DvCheckMachineryDO dbDvCheckMachinery = randomPojo(DvCheckMachineryDO.class);
        dvCheckMachineryMapper.insert(dbDvCheckMachinery);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvCheckMachinery.getId();

        // 调用
        dvCheckMachineryService.deleteDvCheckMachinery(id);
       // 校验数据不存在了
       assertNull(dvCheckMachineryMapper.selectById(id));
    }

    @Test
    public void testDeleteDvCheckMachinery_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvCheckMachineryService.deleteDvCheckMachinery(id), DV_CHECK_MACHINERY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvCheckMachineryPage() {
       // mock 数据
       DvCheckMachineryDO dbDvCheckMachinery = randomPojo(DvCheckMachineryDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setMachineryBrand(null);
           o.setMachinerySpec(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvCheckMachineryMapper.insert(dbDvCheckMachinery);
       // 测试 planId 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setPlanId(null)));
       // 测试 machineryId 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryName(null)));
       // 测试 machineryBrand 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryBrand(null)));
       // 测试 machinerySpec 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachinerySpec(null)));
       // 测试 remark 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setCreateTime(null)));
       // 准备参数
       DvCheckMachineryPageReqVO reqVO = new DvCheckMachineryPageReqVO();
       reqVO.setPlanId(null);
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setMachineryBrand(null);
       reqVO.setMachinerySpec(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvCheckMachineryDO> pageResult = dvCheckMachineryService.getDvCheckMachineryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvCheckMachinery, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvCheckMachineryList() {
       // mock 数据
       DvCheckMachineryDO dbDvCheckMachinery = randomPojo(DvCheckMachineryDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setMachineryBrand(null);
           o.setMachinerySpec(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvCheckMachineryMapper.insert(dbDvCheckMachinery);
       // 测试 planId 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setPlanId(null)));
       // 测试 machineryId 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryName(null)));
       // 测试 machineryBrand 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachineryBrand(null)));
       // 测试 machinerySpec 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setMachinerySpec(null)));
       // 测试 remark 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvCheckMachineryMapper.insert(cloneIgnoreId(dbDvCheckMachinery, o -> o.setCreateTime(null)));
       // 准备参数
       DvCheckMachineryExportReqVO reqVO = new DvCheckMachineryExportReqVO();
       reqVO.setPlanId(null);
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setMachineryBrand(null);
       reqVO.setMachinerySpec(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvCheckMachineryDO> list = dvCheckMachineryService.getDvCheckMachineryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvCheckMachinery, list.get(0));
    }

}
