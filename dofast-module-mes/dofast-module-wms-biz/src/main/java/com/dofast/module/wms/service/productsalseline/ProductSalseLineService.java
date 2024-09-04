package com.dofast.module.wms.service.productsalseline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.productsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品销售出库行 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductSalseLineService {
    /**
     * 查询产品销售出库行列表
     *
     * @param lineListVO 产品销售出库行
     * @return 产品销售出库行集合
     */
    public List<ProductSalseLineDO> selectWmProductSalseLineList(ProductSalseLineListVO lineListVO);
    public int deleteBySalseId(Long salseId);
    /**
     * 创建产品销售出库行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductSalseLine(@Valid ProductSalseLineCreateReqVO createReqVO);

    /**
     * 更新产品销售出库行
     *
     * @param updateReqVO 更新信息
     */
    void updateProductSalseLine(@Valid ProductSalseLineUpdateReqVO updateReqVO);

    /**
     * 删除产品销售出库行
     *
     * @param id 编号
     */
    void deleteProductSalseLine(Long id);

    /**
     * 获得产品销售出库行
     *
     * @param id 编号
     * @return 产品销售出库行
     */
    ProductSalseLineDO getProductSalseLine(Long id);

    /**
     * 获得产品销售出库行列表
     *
     * @param ids 编号
     * @return 产品销售出库行列表
     */
    List<ProductSalseLineDO> getProductSalseLineList(Collection<Long> ids);

    /**
     * 获得产品销售出库行分页
     *
     * @param pageReqVO 分页查询
     * @return 产品销售出库行分页
     */
    PageResult<ProductSalseLineDO> getProductSalseLinePage(ProductSalseLinePageReqVO pageReqVO);

    /**
     * 获得产品销售出库行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品销售出库行列表
     */
    List<ProductSalseLineDO> getProductSalseLineList(ProductSalseLineExportReqVO exportReqVO);

}
