package com.dofast.module.wms.service.electroformheader;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.electroformheader.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformheader.ElectroformHeaderDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 制版房领料单头 Service 接口
 *
 * @author 惠智造
 */
public interface ElectroformHeaderService {

    /**
     * 创建制版房领料单头
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createElectroformHeader(@Valid ElectroformHeaderCreateReqVO createReqVO);

    /**
     * 更新制版房领料单头
     *
     * @param updateReqVO 更新信息
     */
    void updateElectroformHeader(@Valid ElectroformHeaderUpdateReqVO updateReqVO);

    /**
     * 删除制版房领料单头
     *
     * @param id 编号
     */
    void deleteElectroformHeader(Long id);

    /**
     * 获得制版房领料单头
     *
     * @param id 编号
     * @return 制版房领料单头
     */
    ElectroformHeaderDO getElectroformHeader(Long id);

    /**
     * 获得制版房领料单头列表
     *
     * @param ids 编号
     * @return 制版房领料单头列表
     */
    List<ElectroformHeaderDO> getElectroformHeaderList(Collection<Long> ids);

    /**
     * 获得制版房领料单头分页
     *
     * @param pageReqVO 分页查询
     * @return 制版房领料单头分页
     */
    PageResult<ElectroformHeaderDO> getElectroformHeaderPage(ElectroformHeaderPageReqVO pageReqVO);

    /**
     * 获得制版房领料单头列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 制版房领料单头列表
     */
    List<ElectroformHeaderDO> getElectroformHeaderList(ElectroformHeaderExportReqVO exportReqVO);

}
