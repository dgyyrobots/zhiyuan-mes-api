package com.dofast.module.wms.service.productrecptline;

import java.util.*;
import javax.validation.*;
import com.dofast.module.wms.controller.admin.productrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecptline.ProductRecptLineDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品入库记录表行 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductRecptLineService {
    /**
     * 根据入库单ID删除所有行
     * @param recptId
     * @return
     */
    public int deleteByRecptId(Long recptId);
    /**
     * 创建产品入库记录表行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductRecptLine(@Valid ProductRecptLineCreateReqVO createReqVO);

    /**
     * 更新产品入库记录表行
     *
     * @param updateReqVO 更新信息
     */
    void updateProductRecptLine(@Valid ProductRecptLineUpdateReqVO updateReqVO);

    /**
     * 删除产品入库记录表行
     *
     * @param id 编号
     */
    void deleteProductRecptLine(Long id);

    /**
     * 查询产品入库记录行列表
     *
     * @param wmProductRecptLine 产品入库记录行
     * @return 产品入库记录行集合
     */
    public List<ProductRecptLineDO> selectWmProductRecptLineList(ProductRecptLineListVO listVO);
    /**
     * 获得产品入库记录表行
     *
     * @param id 编号
     * @return 产品入库记录表行
     */
    ProductRecptLineDO getProductRecptLine(Long id);

    /**
     * 获得产品入库记录表行列表
     *
     * @param ids 编号
     * @return 产品入库记录表行列表
     */
    List<ProductRecptLineDO> getProductRecptLineList(Collection<Long> ids);

    /**
     * 获得产品入库记录表行分页
     *
     * @param pageReqVO 分页查询
     * @return 产品入库记录表行分页
     */
    PageResult<ProductRecptLineDO> getProductRecptLinePage(ProductRecptLinePageReqVO pageReqVO);

    /**
     * 获得产品入库记录表行列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品入库记录表行列表
     */
    List<ProductRecptLineDO> getProductRecptLineList(ProductRecptLineExportReqVO exportReqVO);

}
