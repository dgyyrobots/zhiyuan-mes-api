package com.dofast.module.wms.service.productproduceline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.productproduceline.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品产出记录表行 Service 接口
 *
 * @author 惠智造
 */
public interface ProductProduceLineService {

    /**
     * 创建产品产出记录表行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductProduceLine(@Valid ProductProduceLineCreateReqVO createReqVO);

    /**
     * 更新产品产出记录表行
     *
     * @param updateReqVO 更新信息
     */
    void updateProductProduceLine(@Valid ProductProduceLineUpdateReqVO updateReqVO);

    /**
     * 删除产品产出记录表行
     *
     * @param id 编号
     */
    void deleteProductProduceLine(Long id);

    /**
     * 获得产品产出记录表行
     *
     * @param id 编号
     * @return 产品产出记录表行
     */
    ProductProduceLineDO getProductProduceLine(Long id);

    /**
     * 获得产品产出记录表行列表
     *
     * @param ids 编号
     * @return 产品产出记录表行列表
     */
    List<ProductProduceLineDO> getProductProduceLineList(Collection<Long> ids);

    /**
     * 获得产品产出记录表行分页
     *
     * @param pageReqVO 分页查询
     * @return 产品产出记录表行分页
     */
    PageResult<ProductProduceLineDO> getProductProduceLinePage(ProductProduceLinePageReqVO pageReqVO);

    /**
     * 获得产品产出记录表行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品产出记录表行列表
     */
    List<ProductProduceLineDO> getProductProduceLineList(ProductProduceLineExportReqVO exportReqVO);

}
