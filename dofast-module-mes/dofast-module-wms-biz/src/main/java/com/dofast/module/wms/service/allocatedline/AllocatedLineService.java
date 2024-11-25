package com.dofast.module.wms.service.allocatedline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.allocatedline.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedline.AllocatedLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 调拨单身 Service 接口
 *
 * @author 惠智造
 */
public interface AllocatedLineService {

    /**
     * 创建调拨单身
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAllocatedLine(@Valid AllocatedLineCreateReqVO createReqVO);

    /**
     * 更新调拨单身
     *
     * @param updateReqVO 更新信息
     */
    void updateAllocatedLine(@Valid AllocatedLineUpdateReqVO updateReqVO);

    /**
     * 删除调拨单身
     *
     * @param id 编号
     */
    void deleteAllocatedLine(Long id);

    /**
     * 获得调拨单身
     *
     * @param id 编号
     * @return 调拨单身
     */
    AllocatedLineDO getAllocatedLine(Long id);

    /**
     * 获得调拨单身列表
     *
     * @param ids 编号
     * @return 调拨单身列表
     */
    List<AllocatedLineDO> getAllocatedLineList(Collection<Long> ids);

    /**
     * 获得调拨单身分页
     *
     * @param pageReqVO 分页查询
     * @return 调拨单身分页
     */
    PageResult<AllocatedLineDO> getAllocatedLinePage(AllocatedLinePageReqVO pageReqVO);

    /**
     * 获得调拨单身列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 调拨单身列表
     */
    List<AllocatedLineDO> getAllocatedLineList(AllocatedLineExportReqVO exportReqVO);

}
