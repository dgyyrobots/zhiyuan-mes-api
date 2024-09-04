package com.dofast.module.mes.service.mdworkstationtool;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdworkstationtool.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationtool.MdWorkstationToolDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工装夹具资源 Service 接口
 *
 * @author 芋道源码
 */
public interface MdWorkstationToolService {
    int deleteByWorkstationId(Long workstationId);
    String checkToolTypeExists(MdWorkstationToolBaseVO mdWorkstationTool);
    /**
     * 创建工装夹具资源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdWorkstationTool(@Valid MdWorkstationToolCreateReqVO createReqVO);

    /**
     * 更新工装夹具资源
     *
     * @param updateReqVO 更新信息
     */
    void updateMdWorkstationTool(@Valid MdWorkstationToolUpdateReqVO updateReqVO);

    /**
     * 删除工装夹具资源
     *
     * @param id 编号
     */
    void deleteMdWorkstationTool(Long id);

    /**
     * 获得工装夹具资源
     *
     * @param id 编号
     * @return 工装夹具资源
     */
    MdWorkstationToolDO getMdWorkstationTool(Long id);

    /**
     * 获得工装夹具资源列表
     *
     * @param ids 编号
     * @return 工装夹具资源列表
     */
    List<MdWorkstationToolDO> getMdWorkstationToolList(Collection<Long> ids);

    /**
     * 获得工装夹具资源分页
     *
     * @param pageReqVO 分页查询
     * @return 工装夹具资源分页
     */
    PageResult<MdWorkstationToolDO> getMdWorkstationToolPage(MdWorkstationToolPageReqVO pageReqVO);

    /**
     * 获得工装夹具资源列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工装夹具资源列表
     */
    List<MdWorkstationToolDO> getMdWorkstationToolList(MdWorkstationToolExportReqVO exportReqVO);

}
