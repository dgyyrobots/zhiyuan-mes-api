package com.dofast.module.qms.service.defect;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.defect.vo.*;
import com.dofast.module.qms.dal.dataobject.defect.DefectDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 常见缺陷 Service 接口
 *
 * @author 芋道源码
 */
public interface DefectService {

    /**
     * 创建常见缺陷
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDefect(@Valid DefectCreateReqVO createReqVO);

    /**
     * 更新常见缺陷
     *
     * @param updateReqVO 更新信息
     */
    void updateDefect(@Valid DefectUpdateReqVO updateReqVO);

    /**
     * 删除常见缺陷
     *
     * @param id 编号
     */
    void deleteDefect(Long id);

    /**
     * 获得常见缺陷
     *
     * @param id 编号
     * @return 常见缺陷
     */
    DefectDO getDefect(Long id);


    /**
     * 获得常见缺陷
     *
     * @param processCode 工序编号
     * @return 常见缺陷
     */
    List<DefectDO> getDefectByCode(String processCode);

    /**
     * 获得常见缺陷列表
     *
     * @param ids 编号
     * @return 常见缺陷列表
     */
    List<DefectDO> getDefectList(Collection<Long> ids);

    /**
     * 获得常见缺陷分页
     *
     * @param pageReqVO 分页查询
     * @return 常见缺陷分页
     */
    PageResult<DefectDO> getDefectPage(DefectPageReqVO pageReqVO);

    /**
     * 获得常见缺陷列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 常见缺陷列表
     */
    List<DefectDO> getDefectList(DefectExportReqVO exportReqVO);

}
