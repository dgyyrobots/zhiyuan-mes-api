package com.dofast.module.wms.service.productproduceline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.productproduceline.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.productproduceline.ProductProduceLineConvert;
import com.dofast.module.wms.dal.mysql.productproduceline.ProductProduceLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 产品产出记录表行 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class ProductProduceLineServiceImpl implements ProductProduceLineService {

    @Resource
    private ProductProduceLineMapper productProduceLineMapper;

    @Override
    public Long createProductProduceLine(ProductProduceLineCreateReqVO createReqVO) {
        // 插入
        ProductProduceLineDO productProduceLine = ProductProduceLineConvert.INSTANCE.convert(createReqVO);
        productProduceLineMapper.insert(productProduceLine);
        // 返回
        return productProduceLine.getId();
    }

    @Override
    public void updateProductProduceLine(ProductProduceLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductProduceLineExists(updateReqVO.getId());
        // 更新
        ProductProduceLineDO updateObj = ProductProduceLineConvert.INSTANCE.convert(updateReqVO);
        productProduceLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductProduceLine(Long id) {
        // 校验存在
        validateProductProduceLineExists(id);
        // 删除
        productProduceLineMapper.deleteById(id);
    }

    private void validateProductProduceLineExists(Long id) {
        if (productProduceLineMapper.selectById(id) == null) {
            throw exception(PRODUCT_PRODUCE_LINE_NOT_EXISTS);
        }
    }

    @Override
    public ProductProduceLineDO getProductProduceLine(Long id) {
        return productProduceLineMapper.selectById(id);
    }

    @Override
    public List<ProductProduceLineDO> getProductProduceLineList(Collection<Long> ids) {
        return productProduceLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductProduceLineDO> getProductProduceLinePage(ProductProduceLinePageReqVO pageReqVO) {
        return productProduceLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductProduceLineDO> getProductProduceLineList(ProductProduceLineExportReqVO exportReqVO) {
        return productProduceLineMapper.selectList(exportReqVO);
    }

}
