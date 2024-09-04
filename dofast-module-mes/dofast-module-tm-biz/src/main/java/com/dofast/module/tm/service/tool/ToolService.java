package com.dofast.module.tm.service.tool;

import java.util.*;
import javax.validation.*;
import com.dofast.module.tm.controller.admin.tool.vo.*;
import com.dofast.module.tm.dal.dataobject.tool.ToolDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工装夹具清单 Service 接口
 *
 * @author 惠智造
 */
public interface ToolService {

    /**
     * 创建工装夹具清单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTool(@Valid ToolCreateReqVO createReqVO);

    /**
     * 更新工装夹具清单
     *
     * @param updateReqVO 更新信息
     */
    void updateTool(@Valid ToolUpdateReqVO updateReqVO);

    /**
     * 删除工装夹具清单
     *
     * @param id 编号
     */
    void deleteTool(Long id);

    /**
     * 获得工装夹具清单
     *
     * @param id 编号
     * @return 工装夹具清单
     */
    ToolDO getTool(Long id);

    /**
     * 获得工装夹具清单列表
     *
     * @param ids 编号
     * @return 工装夹具清单列表
     */
    List<ToolDO> getToolList(Collection<Long> ids);

    /**
     * 获得工装夹具清单分页
     *
     * @param pageReqVO 分页查询
     * @return 工装夹具清单分页
     */
    PageResult<ToolDO> getToolPage(ToolPageReqVO pageReqVO);

    /**
     * 获得工装夹具清单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工装夹具清单列表
     */
    List<ToolDO> getToolList(ToolExportReqVO exportReqVO);

}
