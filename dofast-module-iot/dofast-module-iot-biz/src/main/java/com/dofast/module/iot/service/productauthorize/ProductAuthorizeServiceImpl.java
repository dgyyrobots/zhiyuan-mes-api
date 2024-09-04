package com.dofast.module.iot.service.productauthorize;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.iot.controller.admin.productauthorize.vo.*;
import com.dofast.module.iot.dal.dataobject.productauthorize.ProductAuthorizeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.iot.convert.productauthorize.ProductAuthorizeConvert;
import com.dofast.module.iot.dal.mysql.productauthorize.ProductAuthorizeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.iot.enums.ErrorCodeConstants.*;

/**
 * 产品授权码 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ProductAuthorizeServiceImpl implements ProductAuthorizeService {

    @Resource
    private ProductAuthorizeMapper productAuthorizeMapper;

    @Override
    public Long createProductAuthorize(ProductAuthorizeCreateReqVO createReqVO) {
        // 插入
        ProductAuthorizeDO productAuthorize = ProductAuthorizeConvert.INSTANCE.convert(createReqVO);
        productAuthorizeMapper.insert(productAuthorize);
        // 返回
        return productAuthorize.getId();
    }

    @Override
    public void updateProductAuthorize(ProductAuthorizeUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductAuthorizeExists(updateReqVO.getId());
        // 更新
        ProductAuthorizeDO updateObj = ProductAuthorizeConvert.INSTANCE.convert(updateReqVO);
        productAuthorizeMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductAuthorize(Long id) {
        // 校验存在
        validateProductAuthorizeExists(id);
        // 删除
        productAuthorizeMapper.deleteById(id);
    }

    private void validateProductAuthorizeExists(Long id) {
        if (productAuthorizeMapper.selectById(id) == null) {
            throw exception(PRODUCT_AUTHORIZE_NOT_EXISTS);
        }
    }

    @Override
    public ProductAuthorizeDO getProductAuthorize(Long id) {
        return productAuthorizeMapper.selectById(id);
    }

    @Override
    public List<ProductAuthorizeDO> getProductAuthorizeList(Collection<Long> ids) {
        return productAuthorizeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductAuthorizeDO> getProductAuthorizePage(ProductAuthorizePageReqVO pageReqVO) {
        return productAuthorizeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductAuthorizeDO> getProductAuthorizeList(ProductAuthorizeExportReqVO exportReqVO) {
        return productAuthorizeMapper.selectList(exportReqVO);
    }

}
