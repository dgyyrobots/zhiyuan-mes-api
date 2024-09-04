package com.dofast.module.mes.service.mdworkstation;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 工作站 Service 接口
 *
 * @author 芋道源码
 */
public interface MdWorkstationService {
    String checkWorkStationCodeUnique(MdWorkstationBaseVO mdWorkstation);
    String checkWorkStationNameUnique(MdWorkstationBaseVO mdWorkstation);
    /**
     * 创建工作站
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdWorkstation(@Valid MdWorkstationCreateReqVO createReqVO);

    /**
     * 更新工作站
     *
     * @param updateReqVO 更新信息
     */
    void updateMdWorkstation(@Valid MdWorkstationUpdateReqVO updateReqVO);

    /**
     * 删除工作站
     *
     * @param id 编号
     */
    void deleteMdWorkstation(Long id);

    /**
     * 获得工作站
     *
     * @param id 编号
     * @return 工作站
     */
    MdWorkstationDO getMdWorkstation(Long id);

    /**
     * 获得工作站列表
     *
     * @param ids 编号
     * @return 工作站列表
     */
    List<MdWorkstationDO> getMdWorkstationList(Collection<Long> ids);

    /**
     * 获得工作站分页
     *
     * @param pageReqVO 分页查询
     * @return 工作站分页
     */
    PageResult<MdWorkstationDO> getMdWorkstationPage(MdWorkstationPageReqVO pageReqVO);

    /**
     * 获得工作站列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 工作站列表
     */
    List<MdWorkstationDO> getMdWorkstationList(MdWorkstationExportReqVO exportReqVO);

    List<MdWorkstationDO> getMdWorkstationListByworkstationIds(Collection<Long> workstationIds);
}
