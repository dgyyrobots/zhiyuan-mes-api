package com.dofast.module.wms.service.electroformline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.electroformline.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformline.ElectroformLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 制版房领料单身体 Service 接口
 *
 * @author 惠智造
 */
public interface ElectroformLineService {

    /**
     * 创建制版房领料单身体
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createElectroformLine(@Valid ElectroformLineCreateReqVO createReqVO);

    /**
     * 更新制版房领料单身体
     *
     * @param updateReqVO 更新信息
     */
    void updateElectroformLine(@Valid ElectroformLineUpdateReqVO updateReqVO);

    /**
     * 删除制版房领料单身体
     *
     * @param id 编号
     */
    void deleteElectroformLine(Long id);

    /**
     * 获得制版房领料单身体
     *
     * @param id 编号
     * @return 制版房领料单身体
     */
    ElectroformLineDO getElectroformLine(Long id);

    /**
     * 获得制版房领料单身体列表
     *
     * @param ids 编号
     * @return 制版房领料单身体列表
     */
    List<ElectroformLineDO> getElectroformLineList(Collection<Long> ids);

    /**
     * 获得制版房领料单身体分页
     *
     * @param pageReqVO 分页查询
     * @return 制版房领料单身体分页
     */
    PageResult<ElectroformLineDO> getElectroformLinePage(ElectroformLinePageReqVO pageReqVO);

    /**
     * 获得制版房领料单身体列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 制版房领料单身体列表
     */
    List<ElectroformLineDO> getElectroformLineList(ElectroformLineExportReqVO exportReqVO);

}
