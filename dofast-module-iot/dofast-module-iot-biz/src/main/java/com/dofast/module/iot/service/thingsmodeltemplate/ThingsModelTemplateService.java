package com.dofast.module.iot.service.thingsmodeltemplate;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodeltemplate.ThingsModelTemplateDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 物模型模板 Service 接口
 *
 * @author 惠智造
 */
public interface ThingsModelTemplateService {

    /**
     * 创建物模型模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createThingsModelTemplate(@Valid ThingsModelTemplateCreateReqVO createReqVO);

    /**
     * 更新物模型模板
     *
     * @param updateReqVO 更新信息
     */
    void updateThingsModelTemplate(@Valid ThingsModelTemplateUpdateReqVO updateReqVO);

    /**
     * 删除物模型模板
     *
     * @param id 编号
     */
    void deleteThingsModelTemplate(Long id);

    /**
     * 获得物模型模板
     *
     * @param id 编号
     * @return 物模型模板
     */
    ThingsModelTemplateDO getThingsModelTemplate(Long id);

    /**
     * 获得物模型模板列表
     *
     * @param ids 编号
     * @return 物模型模板列表
     */
    List<ThingsModelTemplateDO> getThingsModelTemplateList(Collection<Long> ids);

    /**
     * 获得物模型模板分页
     *
     * @param pageReqVO 分页查询
     * @return 物模型模板分页
     */
    PageResult<ThingsModelTemplateDO> getThingsModelTemplatePage(ThingsModelTemplatePageReqVO pageReqVO);

    /**
     * 获得物模型模板列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物模型模板列表
     */
    List<ThingsModelTemplateDO> getThingsModelTemplateList(ThingsModelTemplateExportReqVO exportReqVO);

}
