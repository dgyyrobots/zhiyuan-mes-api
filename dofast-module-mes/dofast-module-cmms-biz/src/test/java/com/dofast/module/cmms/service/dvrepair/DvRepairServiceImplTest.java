package com.dofast.module.cmms.service.dvrepair;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cmms.controller.admin.dvrepair.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvrepair.DvRepairDO;
import com.dofast.module.cmms.dal.mysql.dvrepair.DvRepairMapper;
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
 * {@link DvRepairServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DvRepairServiceImpl.class)
public class DvRepairServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DvRepairServiceImpl dvRepairService;

    @Resource
    private DvRepairMapper dvRepairMapper;

    @Test
    public void testCreateDvRepair_success() {
        // 准备参数
        DvRepairCreateReqVO reqVO = randomPojo(DvRepairCreateReqVO.class);

        // 调用
        Long dvRepairId = dvRepairService.createDvRepair(reqVO);
        // 断言
        assertNotNull(dvRepairId);
        // 校验记录的属性是否正确
        DvRepairDO dvRepair = dvRepairMapper.selectById(dvRepairId);
        assertPojoEquals(reqVO, dvRepair);
    }

    @Test
    public void testUpdateDvRepair_success() {
        // mock 数据
        DvRepairDO dbDvRepair = randomPojo(DvRepairDO.class);
        dvRepairMapper.insert(dbDvRepair);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DvRepairUpdateReqVO reqVO = randomPojo(DvRepairUpdateReqVO.class, o -> {
            o.setId(dbDvRepair.getId()); // 设置更新的 ID
        });

        // 调用
        dvRepairService.updateDvRepair(reqVO);
        // 校验是否更新正确
        DvRepairDO dvRepair = dvRepairMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, dvRepair);
    }

    @Test
    public void testUpdateDvRepair_notExists() {
        // 准备参数
        DvRepairUpdateReqVO reqVO = randomPojo(DvRepairUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> dvRepairService.updateDvRepair(reqVO), DV_REPAIR_NOT_EXISTS);
    }

    @Test
    public void testDeleteDvRepair_success() {
        // mock 数据
        DvRepairDO dbDvRepair = randomPojo(DvRepairDO.class);
        dvRepairMapper.insert(dbDvRepair);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDvRepair.getId();

        // 调用
        dvRepairService.deleteDvRepair(id);
       // 校验数据不存在了
       assertNull(dvRepairMapper.selectById(id));
    }

    @Test
    public void testDeleteDvRepair_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> dvRepairService.deleteDvRepair(id), DV_REPAIR_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvRepairPage() {
       // mock 数据
       DvRepairDO dbDvRepair = randomPojo(DvRepairDO.class, o -> { // 等会查询到
           o.setRepairCode(null);
           o.setRepairName(null);
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setMachineryBrand(null);
           o.setMachinerySpec(null);
           o.setMachineryTypeId(null);
           o.setRequireDate(null);
           o.setFinishDate(null);
           o.setConfirmDate(null);
           o.setRepairResult(null);
           o.setAcceptedBy(null);
           o.setConfirmBy(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvRepairMapper.insert(dbDvRepair);
       // 测试 repairCode 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRepairCode(null)));
       // 测试 repairName 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRepairName(null)));
       // 测试 machineryId 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryName(null)));
       // 测试 machineryBrand 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryBrand(null)));
       // 测试 machinerySpec 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachinerySpec(null)));
       // 测试 machineryTypeId 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryTypeId(null)));
       // 测试 requireDate 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRequireDate(null)));
       // 测试 finishDate 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setFinishDate(null)));
       // 测试 confirmDate 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setConfirmDate(null)));
       // 测试 repairResult 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRepairResult(null)));
       // 测试 acceptedBy 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAcceptedBy(null)));
       // 测试 confirmBy 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setConfirmBy(null)));
       // 测试 status 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setCreateTime(null)));
       // 准备参数
       DvRepairPageReqVO reqVO = new DvRepairPageReqVO();
       reqVO.setRepairCode(null);
       reqVO.setRepairName(null);
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setMachineryBrand(null);
       reqVO.setMachinerySpec(null);
       reqVO.setMachineryTypeId(null);
       reqVO.setRequireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFinishDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setConfirmDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRepairResult(null);
       reqVO.setAcceptedBy(null);
       reqVO.setConfirmBy(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DvRepairDO> pageResult = dvRepairService.getDvRepairPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDvRepair, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDvRepairList() {
       // mock 数据
       DvRepairDO dbDvRepair = randomPojo(DvRepairDO.class, o -> { // 等会查询到
           o.setRepairCode(null);
           o.setRepairName(null);
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setMachineryBrand(null);
           o.setMachinerySpec(null);
           o.setMachineryTypeId(null);
           o.setRequireDate(null);
           o.setFinishDate(null);
           o.setConfirmDate(null);
           o.setRepairResult(null);
           o.setAcceptedBy(null);
           o.setConfirmBy(null);
           o.setStatus(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       dvRepairMapper.insert(dbDvRepair);
       // 测试 repairCode 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRepairCode(null)));
       // 测试 repairName 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRepairName(null)));
       // 测试 machineryId 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryName(null)));
       // 测试 machineryBrand 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryBrand(null)));
       // 测试 machinerySpec 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachinerySpec(null)));
       // 测试 machineryTypeId 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setMachineryTypeId(null)));
       // 测试 requireDate 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRequireDate(null)));
       // 测试 finishDate 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setFinishDate(null)));
       // 测试 confirmDate 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setConfirmDate(null)));
       // 测试 repairResult 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRepairResult(null)));
       // 测试 acceptedBy 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAcceptedBy(null)));
       // 测试 confirmBy 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setConfirmBy(null)));
       // 测试 status 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setStatus(null)));
       // 测试 remark 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       dvRepairMapper.insert(cloneIgnoreId(dbDvRepair, o -> o.setCreateTime(null)));
       // 准备参数
       DvRepairExportReqVO reqVO = new DvRepairExportReqVO();
       reqVO.setRepairCode(null);
       reqVO.setRepairName(null);
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setMachineryBrand(null);
       reqVO.setMachinerySpec(null);
       reqVO.setMachineryTypeId(null);
       reqVO.setRequireDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setFinishDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setConfirmDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRepairResult(null);
       reqVO.setAcceptedBy(null);
       reqVO.setConfirmBy(null);
       reqVO.setStatus(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DvRepairDO> list = dvRepairService.getDvRepairList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDvRepair, list.get(0));
    }

}
