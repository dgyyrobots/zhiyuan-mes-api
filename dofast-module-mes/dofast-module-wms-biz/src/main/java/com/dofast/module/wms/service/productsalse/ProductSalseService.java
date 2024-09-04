package com.dofast.module.wms.service.productsalse;

import java.util.*;
import javax.validation.*;

import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;
import com.dofast.module.wms.controller.admin.productsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseDO;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseTxBean;

/**
 * 销售出库单 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductSalseService {
    /**
     * 获取产品销售出库事务Bean
     * @param salseId
     * @return
     */
    public List<ProductSalseTxBean> getTxBeans(Long salseId);
    /**
     * 检查编号唯一性
     * @param wmProductSalse
     * @return
     */
    public String checkUnique(ProductSalseBaseVO wmProductSalse);
    /**
     * 创建销售出库单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductSalse(@Valid ProductSalseCreateReqVO createReqVO);

    /**
     * 更新销售出库单
     *
     * @param updateReqVO 更新信息
     */
    void updateProductSalse(@Valid ProductSalseUpdateReqVO updateReqVO);

    /**
     * 删除销售出库单
     *
     * @param id 编号
     */
    void deleteProductSalse(Long id);

    /**
     * 获得销售出库单
     *
     * @param id 编号
     * @return 销售出库单
     */
    ProductSalseDO getProductSalse(Long id);

    /**
     * 获得销售出库单列表
     *
     * @param ids 编号
     * @return 销售出库单列表
     */
    List<ProductSalseDO> getProductSalseList(Collection<Long> ids);

    /**
     * 获得销售出库单分页
     *
     * @param pageReqVO 分页查询
     * @return 销售出库单分页
     */
    PageResult<ProductSalseDO> getProductSalsePage(ProductSalsePageReqVO pageReqVO);

    /**
     * 获得销售出库单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 销售出库单列表
     */
    List<ProductSalseDO> getProductSalseList(ProductSalseExportReqVO exportReqVO);

    void generateProductSale(FeedbackDTO feedbackDTO, WorkorderDTO workorderDTO, OqcDTO oqcDTO);
}
