package com.dofast.module.iot.service.thingsmodel;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.thingsmodel.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodel.ThingsModelDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物模型 Service 接口
 *
 * @author 惠智造
 */
public interface ThingsModelService {

    /**
     * 创建物模型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createThingsModel(@Valid ThingsModelCreateReqVO createReqVO);

    /**
     * 更新物模型
     *
     * @param updateReqVO 更新信息
     */
    void updateThingsModel(@Valid ThingsModelUpdateReqVO updateReqVO);

    /**
     * 删除物模型
     *
     * @param id 编号
     */
    void deleteThingsModel(Long id);

    /**
     * 获得物模型
     *
     * @param id 编号
     * @return 物模型
     */
    ThingsModelDO getThingsModel(Long id);

    /**
     * 获得物模型列表
     *
     * @param ids 编号
     * @return 物模型列表
     */
    List<ThingsModelDO> getThingsModelList(Collection<Long> ids);

    /**
     * 获得物模型分页
     *
     * @param pageReqVO 分页查询
     * @return 物模型分页
     */
    PageResult<ThingsModelDO> getThingsModelPage(ThingsModelPageReqVO pageReqVO);

    /**
     * 获得物模型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物模型列表
     */
    List<ThingsModelDO> getThingsModelList(ThingsModelExportReqVO exportReqVO);

}
