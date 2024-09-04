package com.dofast.module.system.service.expresstemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateExportReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplatePageReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresstemplate.ExpressTemplateDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 运费模板 Service 接口
 *
 * @author 惠智造
 */
public interface ExpressTemplateService {

    /**
     * 创建运费模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExpressTemplate(@Valid ExpressTemplateCreateReqVO createReqVO);

    /**
     * 更新运费模板
     *
     * @param updateReqVO 更新信息
     */
    void updateExpressTemplate(@Valid ExpressTemplateUpdateReqVO updateReqVO);

    /**
     * 删除运费模板
     *
     * @param id 编号
     */
    void deleteExpressTemplate(Long id);

    /**
     * 获得运费模板
     *
     * @param id 编号
     * @return 运费模板
     */
    ExpressTemplateDO getExpressTemplate(Long id);

    /**
     * 获得运费模板列表
     *
     * @param ids 编号
     * @return 运费模板列表
     */
    List<ExpressTemplateDO> getExpressTemplateList(Collection<Long> ids);

    /**
     * 获得运费模板分页
     *
     * @param pageReqVO 分页查询
     * @return 运费模板分页
     */
    PageResult<ExpressTemplateDO> getExpressTemplatePage(ExpressTemplatePageReqVO pageReqVO);

    /**
     * 获得运费模板列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 运费模板列表
     */
    List<ExpressTemplateDO> getExpressTemplateList(ExpressTemplateExportReqVO exportReqVO);

}
