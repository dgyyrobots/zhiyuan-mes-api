package com.dofast.module.tm.service.tooltype;

import java.util.*;
import javax.validation.*;
import com.dofast.module.tm.controller.admin.tooltype.vo.*;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工装夹具类型 Service 接口
 *
 * @author 惠智造
 */
public interface ToolTypeService {

    /**
     * 创建工装夹具类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createToolType(@Valid ToolTypeCreateReqVO createReqVO);

    /**
     * 更新工装夹具类型
     *
     * @param updateReqVO 更新信息
     */
    void updateToolType(@Valid ToolTypeUpdateReqVO updateReqVO);

    /**
     * 删除工装夹具类型
     *
     * @param id 编号
     */
    void deleteToolType(Long id);

    /**
     * 获得工装夹具类型
     *
     * @param id 编号
     * @return 工装夹具类型
     */
    ToolTypeDO getToolType(Long id);

    /**
     * 获得工装夹具类型列表
     *
     * @param ids 编号
     * @return 工装夹具类型列表
     */
    List<ToolTypeDO> getToolTypeList(Collection<Long> ids);

    /**
     * 获得工装夹具类型列表
     *
     * @return 工装夹具类型列表
     */
    List<ToolTypeSimpleVO> getToolTypeSimpleList();

    /**
     * 获得工装夹具类型分页
     *
     * @param pageReqVO 分页查询
     * @return 工装夹具类型分页
     */
    PageResult<ToolTypeDO> getToolTypePage(ToolTypePageReqVO pageReqVO);

    /**
     * 获得工装夹具类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工装夹具类型列表
     */
    List<ToolTypeDO> getToolTypeList(ToolTypeExportReqVO exportReqVO);

}
