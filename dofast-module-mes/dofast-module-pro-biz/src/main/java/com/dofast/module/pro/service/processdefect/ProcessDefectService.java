package com.dofast.module.pro.service.processdefect;

import java.util.*;
import javax.validation.*;
import com.dofast.module.pro.controller.admin.processdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.processdefect.ProcessDefectDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工序异常缺陷名称 Service 接口
 *
 * @author 惠智造
 */
public interface ProcessDefectService {

    /**
     * 创建工序异常缺陷名称
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createcessDefect(@Valid ProcessDefectCreateReqVO createReqVO);

    /**
     * 更新工序异常缺陷名称
     *
     * @param updateReqVO 更新信息
     */
    void updatecessDefect(@Valid ProcessDefectUpdateReqVO updateReqVO);

    /**
     * 删除工序异常缺陷名称
     *
     * @param id 编号
     */
    void deletecessDefect(Long id);

    /**
     * 获得工序异常缺陷名称
     *
     * @param id 编号
     * @return 工序异常缺陷名称
     */
    ProcessDefectDO getcessDefect(Long id);

    /**
     * 基于工序获取异常缺陷
     * @param processCode
     * @return
     */
    List<ProcessDefectDO> getcessDefectByCode(String processCode);

    /**
     * 获得工序异常缺陷名称列表
     *
     * @param ids 编号
     * @return 工序异常缺陷名称列表
     */
    List<ProcessDefectDO> getcessDefectList(Collection<Long> ids);

    /**
     * 获得工序异常缺陷名称分页
     *
     * @param pageReqVO 分页查询
     * @return 工序异常缺陷名称分页
     */
    PageResult<ProcessDefectDO> getcessDefectPage(ProcessDefectPageReqVO pageReqVO);

    /**
     * 获得工序异常缺陷名称列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工序异常缺陷名称列表
     */
    List<ProcessDefectDO> getcessDefectList(ProcessDefectExportReqVO exportReqVO);

}
