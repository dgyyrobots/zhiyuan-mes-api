package com.dofast.module.system.service.expresscompany;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyCreateReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyExportReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyPageReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresscompany.ExpressCompanyDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 租户物流公司 Service 接口
 *
 * @author 惠智造
 */
public interface ExpressCompanyService {

    /**
     * 创建租户物流公司
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createExpressCompany(@Valid ExpressCompanyCreateReqVO createReqVO);

    /**
     * 更新租户物流公司
     *
     * @param updateReqVO 更新信息
     */
    void updateExpressCompany(@Valid ExpressCompanyUpdateReqVO updateReqVO);

    /**
     * 删除租户物流公司
     *
     * @param id 编号
     */
    void deleteExpressCompany(Integer id);

    /**
     * 获得租户物流公司
     *
     * @param id 编号
     * @return 租户物流公司
     */
    ExpressCompanyDO getExpressCompany(Integer id);

    /**
     * 获得租户物流公司列表
     *
     * @param ids 编号
     * @return 租户物流公司列表
     */
    List<ExpressCompanyDO> getExpressCompanyList(Collection<Integer> ids);

    /**
     * 获得租户物流公司列表
     *
     * @return 租户物流公司列表
     */
    List<ExpressCompanyDO> getExpressCompanyList();
    /**
     * 获得租户物流公司分页
     *
     * @param pageReqVO 分页查询
     * @return 租户物流公司分页
     */
    PageResult<ExpressCompanyDO> getExpressCompanyPage(ExpressCompanyPageReqVO pageReqVO);

    /**
     * 获得租户物流公司列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 租户物流公司列表
     */
    List<ExpressCompanyDO> getExpressCompanyList(ExpressCompanyExportReqVO exportReqVO);

    /**
     * 根据code获取物料公司
     */
     ExpressCompanyDO getExpressCompanyByCode(String code);

}
