package com.dofast.module.cal.service.holiday;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cal.controller.admin.holiday.vo.*;
import com.dofast.module.cal.dal.dataobject.holiday.HolidayDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 节假日设置 Service 接口
 *
 * @author 惠智造
 */
public interface HolidayService {

    /**
     * 创建节假日设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHoliday(@Valid HolidayCreateReqVO createReqVO);

    /**
     * 更新节假日设置
     *
     * @param updateReqVO 更新信息
     */
    void updateHoliday(@Valid HolidayUpdateReqVO updateReqVO);

    /**
     * 删除节假日设置
     *
     * @param id 编号
     */
    void deleteHoliday(Long id);

    /**
     * 获得节假日设置
     *
     * @param id 编号
     * @return 节假日设置
     */
    HolidayDO getHoliday(Long id);

    /**
     * 获得节假日设置列表
     *
     * @param ids 编号
     * @return 节假日设置列表
     */
    List<HolidayDO> getHolidayList(Collection<Long> ids);

    /**
     * 获得节假日设置分页
     *
     * @param pageReqVO 分页查询
     * @return 节假日设置分页
     */
    PageResult<HolidayDO> getHolidayPage(HolidayPageReqVO pageReqVO);

    /**
     * 获得节假日设置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 节假日设置列表
     */
    List<HolidayDO> getHolidayList(HolidayExportReqVO exportReqVO);

}
