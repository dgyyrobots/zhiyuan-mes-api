package com.dofast.module.system.service.expresstemplateitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemCreateReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemExportReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemPageReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresstemplateitem.ExpressTemplateItemDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 运费模板细节 Service 接口
 *
 * @author 惠智造
 */
public interface ExpressTemplateItemService {

    /**
     * 创建运费模板细节
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExpressTemplateItem(@Valid ExpressTemplateItemCreateReqVO createReqVO);

    /**
     * 更新运费模板细节
     *
     * @param updateReqVO 更新信息
     */
    void updateExpressTemplateItem(@Valid ExpressTemplateItemUpdateReqVO updateReqVO);

    /**
     * 删除运费模板细节
     *
     * @param id 编号
     */
    void deleteExpressTemplateItem(Long id);

    /**
     * 获得运费模板细节
     *
     * @param id 编号
     * @return 运费模板细节
     */
    ExpressTemplateItemDO getExpressTemplateItem(Long id);

    /**
     * 获得运费模板细节列表
     *
     * @param ids 编号
     * @return 运费模板细节列表
     */
    List<ExpressTemplateItemDO> getExpressTemplateItemList(Collection<Long> ids);

    /**
     * 获得运费模板细节分页
     *
     * @param pageReqVO 分页查询
     * @return 运费模板细节分页
     */
    PageResult<ExpressTemplateItemDO> getExpressTemplateItemPage(ExpressTemplateItemPageReqVO pageReqVO);

    /**
     * 获得运费模板细节列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 运费模板细节列表
     */
    List<ExpressTemplateItemDO> getExpressTemplateItemList(ExpressTemplateItemExportReqVO exportReqVO);

}
