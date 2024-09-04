package com.dofast.module.system.service.expresscompanytemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateExportReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplatePageReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresscompanytemplate.ExpressCompanyTemplateDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 系统物流公司 Service 接口
 *
 * @author 惠智造
 */
public interface ExpressCompanyTemplateService {

    /**
     * 创建系统物流公司
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createExpressCompanyTemplate(@Valid ExpressCompanyTemplateCreateReqVO createReqVO);

    /**
     * 更新系统物流公司
     *
     * @param updateReqVO 更新信息
     */
    void updateExpressCompanyTemplate(@Valid ExpressCompanyTemplateUpdateReqVO updateReqVO);

    /**
     * 删除系统物流公司
     *
     * @param id 编号
     */
    void deleteExpressCompanyTemplate(Integer id);

    /**
     * 获得系统物流公司
     *
     * @param id 编号
     * @return 系统物流公司
     */
    ExpressCompanyTemplateDO getExpressCompanyTemplate(Integer id);

    /**
     * 获得系统物流公司列表
     *
     * @param ids 编号
     * @return 系统物流公司列表
     */
    List<ExpressCompanyTemplateDO> getExpressCompanyTemplateList(Collection<Integer> ids);

    /**
     * 获得系统物流公司分页
     *
     * @param pageReqVO 分页查询
     * @return 系统物流公司分页
     */
    PageResult<ExpressCompanyTemplateDO> getExpressCompanyTemplatePage(ExpressCompanyTemplatePageReqVO pageReqVO);

    /**
     * 获得系统物流公司列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 系统物流公司列表
     */
    List<ExpressCompanyTemplateDO> getExpressCompanyTemplateList(ExpressCompanyTemplateExportReqVO exportReqVO);

}
