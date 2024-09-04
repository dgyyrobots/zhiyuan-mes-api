package com.dofast.module.wms.service.productrecpt;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.productrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptTxBean;

/**
 * 产品入库录 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductRecptService {

    public List<ProductRecptTxBean> getTxBean(Long recptId);
    public String checkUnique(ProductRecptBaseVO wmProductRecpt);
    /**
     * 创建产品入库录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductRecpt(@Valid ProductRecptCreateReqVO createReqVO);

    /**
     * 更新产品入库录
     *
     * @param updateReqVO 更新信息
     */
    void updateProductRecpt(@Valid ProductRecptUpdateReqVO updateReqVO);

    /**
     * 删除产品入库录
     *
     * @param id 编号
     */
    void deleteProductRecpt(Long id);

    /**
     * 获得产品入库录
     *
     * @param id 编号
     * @return 产品入库录
     */
    ProductRecptDO getProductRecpt(Long id);

    /**
     * 获得产品入库录列表
     *
     * @param ids 编号
     * @return 产品入库录列表
     */
    List<ProductRecptDO> getProductRecptList(Collection<Long> ids);

    /**
     * 获得产品入库录分页
     *
     * @param pageReqVO 分页查询
     * @return 产品入库录分页
     */
    PageResult<ProductRecptDO> getProductRecptPage(ProductRecptPageReqVO pageReqVO);

    /**
     * 获得产品入库录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品入库录列表
     */
    List<ProductRecptDO> getProductRecptList(ProductRecptExportReqVO exportReqVO);

}
