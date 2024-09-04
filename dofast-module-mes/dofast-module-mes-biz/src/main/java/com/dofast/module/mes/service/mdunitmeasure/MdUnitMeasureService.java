package com.dofast.module.mes.service.mdunitmeasure;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdunitmeasure.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitmeasure.MdUnitMeasureDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 单位 Service 接口
 *
 * @author 芋道源码
 */
public interface MdUnitMeasureService {

    /**
     * 创建单位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdUnitMeasure(@Valid MdUnitMeasureCreateReqVO createReqVO);

    /**
     * 更新单位
     *
     * @param updateReqVO 更新信息
     */
    void updateMdUnitMeasure(@Valid MdUnitMeasureUpdateReqVO updateReqVO);

    /**
     * 删除单位
     *
     * @param id 编号
     */
    void deleteMdUnitMeasure(Long id);

    /**
     * 获得单位
     *
     * @param id 编号
     * @return 单位
     */
    MdUnitMeasureDO getMdUnitMeasure(Long id);

    /**
     * 获得单位列表
     *
     * @param ids 编号
     * @return 单位列表
     */
    List<MdUnitMeasureDO> getMdUnitMeasureList(Collection<Long> ids);

    /**
     * 获得单位分页
     *
     * @param pageReqVO 分页查询
     * @return 单位分页
     */
    PageResult<MdUnitMeasureDO> getMdUnitMeasurePage(MdUnitMeasurePageReqVO pageReqVO);

    /**
     * 获得单位列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 单位列表
     */
    List<MdUnitMeasureDO> getMdUnitMeasureList(MdUnitMeasureExportReqVO exportReqVO);
    List<MdUnitMeasureDO> getMdUnitMeasureList(MdUnitMeasureListVO exportReqVO);

}
