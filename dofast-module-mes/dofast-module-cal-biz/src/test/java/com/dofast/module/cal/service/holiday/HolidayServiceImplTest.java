package com.dofast.module.cal.service.holiday;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.dofast.framework.test.core.ut.BaseDbUnitTest;

import com.dofast.module.cal.controller.admin.holiday.vo.*;
import com.dofast.module.cal.dal.dataobject.holiday.HolidayDO;
import com.dofast.module.cal.dal.mysql.holiday.HolidayMapper;
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
 * {@link HolidayServiceImpl} 的单元测试类
 *
 * @author 惠智造
 */
@Import(HolidayServiceImpl.class)
public class HolidayServiceImplTest extends BaseDbUnitTest {

    @Resource
    private HolidayServiceImpl holidayService;

    @Resource
    private HolidayMapper holidayMapper;

    @Test
    public void testCreateHoliday_success() {
        // 准备参数
        HolidayCreateReqVO reqVO = randomPojo(HolidayCreateReqVO.class);

        // 调用
        Long holidayId = holidayService.createHoliday(reqVO);
        // 断言
        assertNotNull(holidayId);
        // 校验记录的属性是否正确
        HolidayDO holiday = holidayMapper.selectById(holidayId);
        assertPojoEquals(reqVO, holiday);
    }

    @Test
    public void testUpdateHoliday_success() {
        // mock 数据
        HolidayDO dbHoliday = randomPojo(HolidayDO.class);
        holidayMapper.insert(dbHoliday);// @Sql: 先插入出一条存在的数据
        // 准备参数
        HolidayUpdateReqVO reqVO = randomPojo(HolidayUpdateReqVO.class, o -> {
            o.setId(dbHoliday.getId()); // 设置更新的 ID
        });

        // 调用
        holidayService.updateHoliday(reqVO);
        // 校验是否更新正确
        HolidayDO holiday = holidayMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, holiday);
    }

    @Test
    public void testUpdateHoliday_notExists() {
        // 准备参数
        HolidayUpdateReqVO reqVO = randomPojo(HolidayUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> holidayService.updateHoliday(reqVO), HOLIDAY_NOT_EXISTS);
    }

    @Test
    public void testDeleteHoliday_success() {
        // mock 数据
        HolidayDO dbHoliday = randomPojo(HolidayDO.class);
        holidayMapper.insert(dbHoliday);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbHoliday.getId();

        // 调用
        holidayService.deleteHoliday(id);
       // 校验数据不存在了
       assertNull(holidayMapper.selectById(id));
    }

    @Test
    public void testDeleteHoliday_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> holidayService.deleteHoliday(id), HOLIDAY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetHolidayPage() {
       // mock 数据
       HolidayDO dbHoliday = randomPojo(HolidayDO.class, o -> { // 等会查询到
           o.setTheDay(null);
           o.setHolidayType(null);
           o.setStartTime(null);
           o.setEndTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       holidayMapper.insert(dbHoliday);
       // 测试 theDay 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setTheDay(null)));
       // 测试 holidayType 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setHolidayType(null)));
       // 测试 startTime 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setStartTime(null)));
       // 测试 endTime 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setEndTime(null)));
       // 测试 remark 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setCreateTime(null)));
       // 准备参数
       HolidayPageReqVO reqVO = new HolidayPageReqVO();
       reqVO.setTheDay(null);
       reqVO.setHolidayType(null);
       reqVO.setStartTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<HolidayDO> pageResult = holidayService.getHolidayPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbHoliday, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetHolidayList() {
       // mock 数据
       HolidayDO dbHoliday = randomPojo(HolidayDO.class, o -> { // 等会查询到
           o.setTheDay(null);
           o.setHolidayType(null);
           o.setStartTime(null);
           o.setEndTime(null);
           o.setRemark(null);
           o.setAttr1(null);
           o.setAttr2(null);
           o.setAttr3(null);
           o.setAttr4(null);
           o.setCreateTime(null);
       });
       holidayMapper.insert(dbHoliday);
       // 测试 theDay 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setTheDay(null)));
       // 测试 holidayType 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setHolidayType(null)));
       // 测试 startTime 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setStartTime(null)));
       // 测试 endTime 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setEndTime(null)));
       // 测试 remark 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setRemark(null)));
       // 测试 attr1 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr1(null)));
       // 测试 attr2 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr2(null)));
       // 测试 attr3 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr3(null)));
       // 测试 attr4 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setAttr4(null)));
       // 测试 createTime 不匹配
       holidayMapper.insert(cloneIgnoreId(dbHoliday, o -> o.setCreateTime(null)));
       // 准备参数
       HolidayExportReqVO reqVO = new HolidayExportReqVO();
       reqVO.setTheDay(null);
       reqVO.setHolidayType(null);
       reqVO.setStartTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRemark(null);
       reqVO.setAttr1(null);
       reqVO.setAttr2(null);
       reqVO.setAttr3(null);
       reqVO.setAttr4(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<HolidayDO> list = holidayService.getHolidayList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbHoliday, list.get(0));
    }

}
