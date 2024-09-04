package com.dofast.module.cal.service.shift;

import java.util.*;
import javax.validation.*;
import com.dofast.module.cal.controller.admin.shift.vo.*;
import com.dofast.module.cal.dal.dataobject.shift.ShiftDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 计划班次 Service 接口
 *
 * @author 惠智造
 */
public interface ShiftService {

    /**
     * 创建计划班次
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createShift(@Valid ShiftCreateReqVO createReqVO);

    /**
     * 更新计划班次
     *
     * @param updateReqVO 更新信息
     */
    void updateShift(@Valid ShiftUpdateReqVO updateReqVO);

    /**
     * 删除计划班次
     *
     * @param id 编号
     */
    void deleteShift(Long id);

    /**
     * 获得计划班次
     *
     * @param id 编号
     * @return 计划班次
     */
    ShiftDO getShift(Long id);

    /**
     * 获得计划班次列表
     *
     * @param ids 编号
     * @return 计划班次列表
     */
    List<ShiftDO> getShiftList(Collection<Long> ids);

    /**
     * 获得计划班次分页
     *
     * @param pageReqVO 分页查询
     * @return 计划班次分页
     */
    PageResult<ShiftDO> getShiftPage(ShiftPageReqVO pageReqVO);

    /**
     * 获得计划班次列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 计划班次列表
     */
    List<ShiftDO> getShiftList(ShiftExportReqVO exportReqVO);

}
