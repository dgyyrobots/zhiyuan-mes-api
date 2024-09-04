package com.dofast.module.cal.service.shift;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.shift.vo.*;
import com.dofast.module.cal.dal.dataobject.shift.ShiftDO;
import com.dofast.module.cal.dal.mysql.shift.ShiftMapper;
import com.dofast.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;
import static com.dofast.framework.test.core.util.AssertUtils.*;
import static com.dofast.framework.test.core.util.RandomUtils.*;
import static com.dofast.framework.common.util.date.LocalDateTimeUtils.*;
import static com.dofast.framework.common.util.object.ObjectUtils.*;
import static com.dofast.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link ShiftServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(ShiftServiceImpl.class)
public class ShiftServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ShiftServiceImpl shiftService;

    @Resource
    private ShiftMapper shiftMapper;

    @Test
    public void testCreateShift_success() {
        // 准备参数
        ShiftCreateReqVO reqVO = randomPojo(ShiftCreateReqVO.class);

        // 调用
        Long shiftId = shiftService.createShift(reqVO);
        // 断言
        assertNotNull(shiftId);
        // 校验记录的属性是否正确
        ShiftDO shift = shiftMapper.selectById(shiftId);
        assertPojoEquals(reqVO, shift);
    }

    @Test
    public void testUpdateShift_success() {
        // mock 数据
        ShiftDO dbShift = randomPojo(ShiftDO.class);
        shiftMapper.insert(dbShift);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ShiftUpdateReqVO reqVO = randomPojo(ShiftUpdateReqVO.class, o -> {
            o.setId(dbShift.getId()); // 设置更新的 ID
        });

        // 调用
        shiftService.updateShift(reqVO);
        // 校验是否更新正确
        ShiftDO shift = shiftMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, shift);
    }

    @Test
    public void testUpdateShift_notExists() {
        // 准备参数
        ShiftUpdateReqVO reqVO = randomPojo(ShiftUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> shiftService.updateShift(reqVO), SHIFT_NOT_EXISTS);
    }

    @Test
    public void testDeleteShift_success() {
        // mock 数据
        ShiftDO dbShift = randomPojo(ShiftDO.class);
        shiftMapper.insert(dbShift);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbShift.getId();

        // 调用
        shiftService.deleteShift(id);
       // 校验数据不存在了
       assertNull(shiftMapper.selectById(id));
    }

    @Test
    public void testDeleteShift_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> shiftService.deleteShift(id), SHIFT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetShiftPage() {
       // mock 数据
       ShiftDO dbShift = randomPojo(ShiftDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setOrderNum(null);
           o.setShiftName(null);
           o.setStartTime(null);
           o.setEndTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       shiftMapper.insert(dbShift);
       // 测试 planId 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setPlanId(null)));
       // 测试 orderNum 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setOrderNum(null)));
       // 测试 shiftName 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setShiftName(null)));
       // 测试 startTime 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setStartTime(null)));
       // 测试 endTime 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setEndTime(null)));
       // 测试 remark 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setCreateTime(null)));
       // 准备参数
       ShiftPageReqVO reqVO = new ShiftPageReqVO();
       reqVO.setPlanId(null);
       reqVO.setOrderNum(null);
       reqVO.setShiftName(null);
       reqVO.setStartTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ShiftDO> pageResult = shiftService.getShiftPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbShift, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetShiftList() {
       // mock 数据
       ShiftDO dbShift = randomPojo(ShiftDO.class, o -> { // 等会查询到
           o.setPlanId(null);
           o.setOrderNum(null);
           o.setShiftName(null);
           o.setStartTime(null);
           o.setEndTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       shiftMapper.insert(dbShift);
       // 测试 planId 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setPlanId(null)));
       // 测试 orderNum 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setOrderNum(null)));
       // 测试 shiftName 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setShiftName(null)));
       // 测试 startTime 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setStartTime(null)));
       // 测试 endTime 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setEndTime(null)));
       // 测试 remark 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       shiftMapper.insert(cloneIgnoreId(dbShift, o -> o.setCreateTime(null)));
       // 准备参数
       ShiftExportReqVO reqVO = new ShiftExportReqVO();
       reqVO.setPlanId(null);
       reqVO.setOrderNum(null);
       reqVO.setShiftName(null);
       reqVO.setStartTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<ShiftDO> list = shiftService.getShiftList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbShift, list.get(0));
    }

}
