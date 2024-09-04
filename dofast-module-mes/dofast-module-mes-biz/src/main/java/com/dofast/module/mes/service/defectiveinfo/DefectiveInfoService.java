package com.dofast.module.mes.service.defectiveinfo;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.defectiveinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.defectiveinfo.DefectiveInfoDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 不良品信息管理 Service 接口
 *
 * @author 惠智造
 */
public interface DefectiveInfoService {

    /**
     * 创建不良品信息管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDefectiveInfo(@Valid DefectiveInfoCreateReqVO createReqVO);

    /**
     * 更新不良品信息管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDefectiveInfo(@Valid DefectiveInfoUpdateReqVO updateReqVO);

    /**
     * 删除不良品信息管理
     *
     * @param id 编号
     */
    void deleteDefectiveInfo(Long id);

    /**
     * 获得不良品信息管理
     *
     * @param id 编号
     * @return 不良品信息管理
     */
    DefectiveInfoDO getDefectiveInfo(Long id);

    /**
     * 获得不良品信息管理列表
     *
     * @param ids 编号
     * @return 不良品信息管理列表
     */
    List<DefectiveInfoDO> getDefectiveInfoList(Collection<Long> ids);

    /**
     * 获得不良品信息管理分页
     *
     * @param pageReqVO 分页查询
     * @return 不良品信息管理分页
     */
    PageResult<DefectiveInfoDO> getDefectiveInfoPage(DefectiveInfoPageReqVO pageReqVO);

    /**
     * 获得不良品信息管理列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 不良品信息管理列表
     */
    List<DefectiveInfoDO> getDefectiveInfoList(DefectiveInfoExportReqVO exportReqVO);

}
