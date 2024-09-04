package com.dofast.module.iot.service.productauthorize;

import java.util.*;
import javax.validation.*;
import com.dofast.module.iot.controller.admin.productauthorize.vo.*;
import com.dofast.module.iot.dal.dataobject.productauthorize.ProductAuthorizeDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 产品授权码 Service 接口
 *
 * @author 惠智造
 */
public interface ProductAuthorizeService {

    /**
     * 创建产品授权码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProductAuthorize(@Valid ProductAuthorizeCreateReqVO createReqVO);

    /**
     * 更新产品授权码
     *
     * @param updateReqVO 更新信息
     */
    void updateProductAuthorize(@Valid ProductAuthorizeUpdateReqVO updateReqVO);

    /**
     * 删除产品授权码
     *
     * @param id 编号
     */
    void deleteProductAuthorize(Long id);

    /**
     * 获得产品授权码
     *
     * @param id 编号
     * @return 产品授权码
     */
    ProductAuthorizeDO getProductAuthorize(Long id);

    /**
     * 获得产品授权码列表
     *
     * @param ids 编号
     * @return 产品授权码列表
     */
    List<ProductAuthorizeDO> getProductAuthorizeList(Collection<Long> ids);

    /**
     * 获得产品授权码分页
     *
     * @param pageReqVO 分页查询
     * @return 产品授权码分页
     */
    PageResult<ProductAuthorizeDO> getProductAuthorizePage(ProductAuthorizePageReqVO pageReqVO);

    /**
     * 获得产品授权码列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品授权码列表
     */
    List<ProductAuthorizeDO> getProductAuthorizeList(ProductAuthorizeExportReqVO exportReqVO);

}
