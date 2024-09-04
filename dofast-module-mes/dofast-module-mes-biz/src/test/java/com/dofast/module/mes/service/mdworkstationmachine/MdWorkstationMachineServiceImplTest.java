package com.dofast.module.mes.service.mdworkstationmachine;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.mes.controller.admin.mdworkstationmachine.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationmachine.MdWorkstationMachineDO;
import com.dofast.module.mes.dal.mysql.mdworkstationmachine.MdWorkstationMachineMapper;
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
 * {@link MdWorkstationMachineServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(MdWorkstationMachineServiceImpl.class)
public class MdWorkstationMachineServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MdWorkstationMachineServiceImpl mdWorkstationMachineService;

    @Resource
    private MdWorkstationMachineMapper mdWorkstationMachineMapper;

    @Test
    public void testCreateMdWorkstationMachine_success() {
        // 准备参数
        MdWorkstationMachineCreateReqVO reqVO = randomPojo(MdWorkstationMachineCreateReqVO.class);

        // 调用
        Long mdWorkstationMachineId = mdWorkstationMachineService.createMdWorkstationMachine(reqVO);
        // 断言
        assertNotNull(mdWorkstationMachineId);
        // 校验记录的属性是否正确
        MdWorkstationMachineDO mdWorkstationMachine = mdWorkstationMachineMapper.selectById(mdWorkstationMachineId);
        assertPojoEquals(reqVO, mdWorkstationMachine);
    }

    @Test
    public void testUpdateMdWorkstationMachine_success() {
        // mock 数据
        MdWorkstationMachineDO dbMdWorkstationMachine = randomPojo(MdWorkstationMachineDO.class);
        mdWorkstationMachineMapper.insert(dbMdWorkstationMachine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MdWorkstationMachineUpdateReqVO reqVO = randomPojo(MdWorkstationMachineUpdateReqVO.class, o -> {
            o.setId(dbMdWorkstationMachine.getId()); // 设置更新的 ID
        });

        // 调用
        mdWorkstationMachineService.updateMdWorkstationMachine(reqVO);
        // 校验是否更新正确
        MdWorkstationMachineDO mdWorkstationMachine = mdWorkstationMachineMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mdWorkstationMachine);
    }

    @Test
    public void testUpdateMdWorkstationMachine_notExists() {
        // 准备参数
        MdWorkstationMachineUpdateReqVO reqVO = randomPojo(MdWorkstationMachineUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationMachineService.updateMdWorkstationMachine(reqVO), MD_WORKSTATION_MACHINE_NOT_EXISTS);
    }

    @Test
    public void testDeleteMdWorkstationMachine_success() {
        // mock 数据
        MdWorkstationMachineDO dbMdWorkstationMachine = randomPojo(MdWorkstationMachineDO.class);
        mdWorkstationMachineMapper.insert(dbMdWorkstationMachine);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbMdWorkstationMachine.getId();

        // 调用
        mdWorkstationMachineService.deleteMdWorkstationMachine(id);
       // 校验数据不存在了
       assertNull(mdWorkstationMachineMapper.selectById(id));
    }

    @Test
    public void testDeleteMdWorkstationMachine_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> mdWorkstationMachineService.deleteMdWorkstationMachine(id), MD_WORKSTATION_MACHINE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationMachinePage() {
       // mock 数据
       MdWorkstationMachineDO dbMdWorkstationMachine = randomPojo(MdWorkstationMachineDO.class, o -> { // 等会查询到
           o.setWorkstationId(null);
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationMachineMapper.insert(dbMdWorkstationMachine);
       // 测试 workstationId 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setWorkstationId(null)));
       // 测试 machineryId 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setMachineryName(null)));
       // 测试 quantity 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationMachinePageReqVO reqVO = new MdWorkstationMachinePageReqVO();
       reqVO.setWorkstationId(null);
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<MdWorkstationMachineDO> pageResult = mdWorkstationMachineService.getMdWorkstationMachinePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMdWorkstationMachine, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMdWorkstationMachineList() {
       // mock 数据
       MdWorkstationMachineDO dbMdWorkstationMachine = randomPojo(MdWorkstationMachineDO.class, o -> { // 等会查询到
           o.setWorkstationId(null);
           o.setMachineryId(null);
           o.setMachineryCode(null);
           o.setMachineryName(null);
           o.setQuantity(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       mdWorkstationMachineMapper.insert(dbMdWorkstationMachine);
       // 测试 workstationId 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setWorkstationId(null)));
       // 测试 machineryId 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setMachineryId(null)));
       // 测试 machineryCode 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setMachineryCode(null)));
       // 测试 machineryName 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setMachineryName(null)));
       // 测试 quantity 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setQuantity(null)));
       // 测试 remark 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       mdWorkstationMachineMapper.insert(cloneIgnoreId(dbMdWorkstationMachine, o -> o.setCreateTime(null)));
       // 准备参数
       MdWorkstationMachineExportReqVO reqVO = new MdWorkstationMachineExportReqVO();
       reqVO.setWorkstationId(null);
       reqVO.setMachineryId(null);
       reqVO.setMachineryCode(null);
       reqVO.setMachineryName(null);
       reqVO.setQuantity(null);
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<MdWorkstationMachineDO> list = mdWorkstationMachineService.getMdWorkstationMachineList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMdWorkstationMachine, list.get(0));
    }

}
