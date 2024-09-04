package com.dofast.module.wms.service.productsalseline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.productsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.productsalseline.ProductSalseLineConvert;
import com.dofast.module.wms.dal.mysql.productsalseline.ProductSalseLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 产品销售出库行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductSalseLineServiceImpl implements ProductSalseLineService {

    @Resource
    private ProductSalseLineMapper productSalseLineMapper;

    @Override
    public List<ProductSalseLineDO> selectWmProductSalseLineList(ProductSalseLineListVO lineListVO) {
        return productSalseLineMapper.selectList(lineListVO);
    }

    @Override
    public int deleteBySalseId(Long salseId) {
        return productSalseLineMapper.deleteBySalseId(salseId);
    }

    @Override
    public Long createProductSalseLine(ProductSalseLineCreateReqVO createReqVO) {
        // 插入
        ProductSalseLineDO productSalseLine = ProductSalseLineConvert.INSTANCE.convert(createReqVO);
        productSalseLineMapper.insert(productSalseLine);
        // 返回
        return productSalseLine.getId();
    }

    @Override
    public void updateProductSalseLine(ProductSalseLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductSalseLineExists(updateReqVO.getId());
        // 更新
        ProductSalseLineDO updateObj = ProductSalseLineConvert.INSTANCE.convert(updateReqVO);
        productSalseLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductSalseLine(Long id) {
        // 校验存在
        validateProductSalseLineExists(id);
        // 删除
        productSalseLineMapper.deleteById(id);
    }

    private void validateProductSalseLineExists(Long id) {
        if (productSalseLineMapper.selectById(id) == null) {
            throw exception(PRODUCT_SALSE_LINE_NOT_EXISTS);
        }
    }

    @Override
    public ProductSalseLineDO getProductSalseLine(Long id) {
        return productSalseLineMapper.selectById(id);
    }

    @Override
    public List<ProductSalseLineDO> getProductSalseLineList(Collection<Long> ids) {
        return productSalseLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductSalseLineDO> getProductSalseLinePage(ProductSalseLinePageReqVO pageReqVO) {
        return productSalseLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductSalseLineDO> getProductSalseLineList(ProductSalseLineExportReqVO exportReqVO) {
        return productSalseLineMapper.selectList(exportReqVO);
    }

}
