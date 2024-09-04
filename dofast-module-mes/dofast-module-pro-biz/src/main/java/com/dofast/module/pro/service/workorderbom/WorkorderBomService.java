package com.dofast.module.pro.service.workorderbom;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.workorderbom.vo.*;
import com.dofast.module.pro.dal.dataobject.workorderbom.WorkorderBomDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 生产工单BOM组成 Service 接口
 *
 * @author 芋道源码
 */
public interface WorkorderBomService {

    int deleteProWorkorderBomByWorkorderId(Long wordId);
    /**
     * 创建生产工单BOM组成
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkorderBom(@Valid WorkorderBomCreateReqVO createReqVO);

    /**
     * 更新生产工单BOM组成
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkorderBom(@Valid WorkorderBomUpdateReqVO updateReqVO);

    /**
     * 删除生产工单BOM组成
     *
     * @param id 编号
     */
    void deleteWorkorderBom(Long id);

    /**
     * 获得生产工单BOM组成
     *
     * @param id 编号
     * @return 生产工单BOM组成
     */
    WorkorderBomDO getWorkorderBom(Long id);

    /**
     * 获得生产工单BOM组成列表
     *
     * @param ids 编号
     * @return 生产工单BOM组成列表
     */
    List<WorkorderBomDO> getWorkorderBomList(Collection<Long> ids);

    /**
     * 获得生产工单BOM组成分页
     *
     * @param pageReqVO 分页查询
     * @return 生产工单BOM组成分页
     */
    PageResult<WorkorderBomDO> getWorkorderBomPage(WorkorderBomPageReqVO pageReqVO);

    /**
     * 获得生产工单BOM组成列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 生产工单BOM组成列表
     */
    List<WorkorderBomDO> getWorkorderBomList(WorkorderBomExportReqVO exportReqVO);

}
