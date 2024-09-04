package com.dofast.module.wms.service.productrecptline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.productrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecptline.ProductRecptLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.productrecptline.ProductRecptLineConvert;
import com.dofast.module.wms.dal.mysql.productrecptline.ProductRecptLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 产品入库记录表行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductRecptLineServiceImpl implements ProductRecptLineService {

    @Resource
    private ProductRecptLineMapper productRecptLineMapper;

    @Override
    public int deleteByRecptId(Long recptId) {
        return productRecptLineMapper.deleteByRecptId(recptId);
    }

    @Override
    public Long createProductRecptLine(ProductRecptLineCreateReqVO createReqVO) {
        // 插入
        ProductRecptLineDO productRecptLine = ProductRecptLineConvert.INSTANCE.convert(createReqVO);
        productRecptLineMapper.insert(productRecptLine);
        // 返回
        return productRecptLine.getId();
    }

    @Override
    public void updateProductRecptLine(ProductRecptLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductRecptLineExists(updateReqVO.getId());
        // 更新
        ProductRecptLineDO updateObj = ProductRecptLineConvert.INSTANCE.convert(updateReqVO);
        productRecptLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductRecptLine(Long id) {
        // 校验存在
        validateProductRecptLineExists(id);
        // 删除
        productRecptLineMapper.deleteById(id);
    }

    @Override
    public List<ProductRecptLineDO> selectWmProductRecptLineList(ProductRecptLineListVO listVO) {
        return productRecptLineMapper.selectList(listVO);
    }

    private void validateProductRecptLineExists(Long id) {
        if (productRecptLineMapper.selectById(id) == null) {
            throw exception(PRODUCT_RECPT_LINE_NOT_EXISTS);
        }
    }

    @Override
    public ProductRecptLineDO getProductRecptLine(Long id) {
        return productRecptLineMapper.selectById(id);
    }

    @Override
    public List<ProductRecptLineDO> getProductRecptLineList(Collection<Long> ids) {
        return productRecptLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductRecptLineDO> getProductRecptLinePage(ProductRecptLinePageReqVO pageReqVO) {
        return productRecptLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductRecptLineDO> getProductRecptLineList(ProductRecptLineExportReqVO exportReqVO) {
        return productRecptLineMapper.selectList(exportReqVO);
    }

}
