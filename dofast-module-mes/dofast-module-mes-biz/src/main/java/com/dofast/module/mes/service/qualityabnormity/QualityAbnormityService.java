package com.dofast.module.mes.service.qualityabnormity;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.qualityabnormity.vo.*;
import com.dofast.module.mes.dal.dataobject.qualityabnormity.QualityAbnormityDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 品质异常信息 Service 接口
 *
 * @author 惠智造
 */
public interface QualityAbnormityService {

    /**
     * 创建品质异常信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQualityAbnormity(@Valid QualityAbnormityCreateReqVO createReqVO);

    /**
     * 更新品质异常信息
     *
     * @param updateReqVO 更新信息
     */
    void updateQualityAbnormity(@Valid QualityAbnormityUpdateReqVO updateReqVO);

    /**
     * 删除品质异常信息
     *
     * @param id 编号
     */
    void deleteQualityAbnormity(Long id);

    /**
     * 获得品质异常信息
     *
     * @param id 编号
     * @return 品质异常信息
     */
    QualityAbnormityDO getQualityAbnormity(Long id);

    /**
     * 获得品质异常信息列表
     *
     * @param ids 编号
     * @return 品质异常信息列表
     */
    List<QualityAbnormityDO> getQualityAbnormityList(Collection<Long> ids);

    /**
     * 获得品质异常信息分页
     *
     * @param pageReqVO 分页查询
     * @return 品质异常信息分页
     */
    PageResult<QualityAbnormityDO> getQualityAbnormityPage(QualityAbnormityPageReqVO pageReqVO);

    /**
     * 获得品质异常信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 品质异常信息列表
     */
    List<QualityAbnormityDO> getQualityAbnormityList(QualityAbnormityExportReqVO exportReqVO);

}
