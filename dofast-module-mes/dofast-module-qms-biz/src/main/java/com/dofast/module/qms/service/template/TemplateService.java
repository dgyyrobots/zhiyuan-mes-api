package com.dofast.module.qms.service.template;

import java.util.*;
import javax.validation.*;
import com.dofast.module.qms.controller.admin.template.vo.*;
import com.dofast.module.qms.dal.dataobject.template.TemplateDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 检测模板 Service 接口
 *
 * @author 芋道源码
 */
public interface TemplateService {
    /**
     * 根据检测类型和产品查找对应的检测模板
     * @param qcTemplate
     */
    public TemplateDO selectQcTemplateByProductAndQcType(TemplateBaseVO qcTemplate);

    /**
     * 根据物料/产品和检验类型查询对应的检测模板
     * @param param
     * @return
     */
    public TemplateDO findTemplateByProductIdAndQcType(TemplateBaseVO param);
    /**
     * 检测模板编号是否唯一
     * @param qcTemplate
     * @return
     */
    public String checkTemplateCodeUnique(TemplateBaseVO qcTemplate);
    /**
     * 创建检测模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTemplate(@Valid TemplateCreateReqVO createReqVO);

    /**
     * 更新检测模板
     *
     * @param updateReqVO 更新信息
     */
    void updateTemplate(@Valid TemplateUpdateReqVO updateReqVO);

    /**
     * 删除检测模板
     *
     * @param id 编号
     */
    void deleteTemplate(Long id);

    /**
     * 获得检测模板
     *
     * @param id 编号
     * @return 检测模板
     */
    TemplateDO getTemplate(Long id);

    /**
     * 获得检测模板列表
     *
     * @param ids 编号
     * @return 检测模板列表
     */
    List<TemplateDO> getTemplateList(Collection<Long> ids);

    /**
     * 获得检测模板分页
     *
     * @param pageReqVO 分页查询
     * @return 检测模板分页
     */
    PageResult<TemplateDO> getTemplatePage(TemplatePageReqVO pageReqVO);

    /**
     * 获得检测模板列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 检测模板列表
     */
    List<TemplateDO> getTemplateList(TemplateExportReqVO exportReqVO);

}
