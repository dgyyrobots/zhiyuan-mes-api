package com.dofast.module.cal.service.holiday;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.cal.controller.admin.holiday.vo.*;
import com.dofast.module.cal.dal.dataobject.holiday.HolidayDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.cal.convert.holiday.HolidayConvert;
import com.dofast.module.cal.dal.mysql.holiday.HolidayMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.cal.enums.ErrorCodeConstants.*;

/**
 * 节假日设置 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class HolidayServiceImpl implements HolidayService {

    @Resource
    private HolidayMapper holidayMapper;

    @Override
    public Long createHoliday(HolidayCreateReqVO createReqVO) {
        // 插入
        HolidayDO holiday = HolidayConvert.INSTANCE.convert(createReqVO);
        holidayMapper.insert(holiday);
        // 返回
        return holiday.getId();
    }

    @Override
    public void updateHoliday(HolidayUpdateReqVO updateReqVO) {
        // 校验存在
        validateHolidayExists(updateReqVO.getId());
        // 更新
        HolidayDO updateObj = HolidayConvert.INSTANCE.convert(updateReqVO);
        holidayMapper.updateById(updateObj);
    }

    @Override
    public void deleteHoliday(Long id) {
        // 校验存在
        validateHolidayExists(id);
        // 删除
        holidayMapper.deleteById(id);
    }

    private void validateHolidayExists(Long id) {
        if (holidayMapper.selectById(id) == null) {
            throw exception(HOLIDAY_NOT_EXISTS);
        }
    }

    @Override
    public HolidayDO getHoliday(Long id) {
        return holidayMapper.selectById(id);
    }

    @Override
    public List<HolidayDO> getHolidayList(Collection<Long> ids) {
        if(ids == null ){
            return holidayMapper.selectList();
        }else {
            return holidayMapper.selectBatchIds(ids);
        }
    }

    @Override
    public PageResult<HolidayDO> getHolidayPage(HolidayPageReqVO pageReqVO) {
        return holidayMapper.selectPage(pageReqVO);
    }

    @Override
    public List<HolidayDO> getHolidayList(HolidayExportReqVO exportReqVO) {
        return holidayMapper.selectList(exportReqVO);
    }

}
