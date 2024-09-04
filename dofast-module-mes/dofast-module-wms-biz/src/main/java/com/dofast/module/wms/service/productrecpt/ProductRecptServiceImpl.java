package com.dofast.module.wms.service.productrecpt;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptTxBean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.productrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.productrecpt.ProductRecptConvert;
import com.dofast.module.wms.dal.mysql.productrecpt.ProductRecptMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 产品入库录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductRecptServiceImpl implements ProductRecptService {

    @Resource
    private ProductRecptMapper productRecptMapper;

    @Override
    public List<ProductRecptTxBean> getTxBean(Long recptId) {
        return productRecptMapper.getTxBean(recptId);
    }

    @Override
    public String checkUnique(ProductRecptBaseVO baseVO) {
        ProductRecptDO productRecptDO = productRecptMapper.checkUnique(baseVO);
        Long recptId = baseVO.getId()==null?-1L:baseVO.getId();
        if(StrUtils.isNotNull(productRecptDO) && productRecptDO.getId().longValue() != recptId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createProductRecpt(ProductRecptCreateReqVO createReqVO) {
        // 插入
        ProductRecptDO productRecpt = ProductRecptConvert.INSTANCE.convert(createReqVO);
        productRecptMapper.insert(productRecpt);
        // 返回
        return productRecpt.getId();
    }

    @Override
    public void updateProductRecpt(ProductRecptUpdateReqVO updateReqVO) {
        // 校验存在
        validateProductRecptExists(updateReqVO.getId());
        // 更新
        ProductRecptDO updateObj = ProductRecptConvert.INSTANCE.convert(updateReqVO);
        productRecptMapper.updateById(updateObj);
    }

    @Override
    public void deleteProductRecpt(Long id) {
        // 校验存在
        validateProductRecptExists(id);
        // 删除
        productRecptMapper.deleteById(id);
    }

    private void validateProductRecptExists(Long id) {
        if (productRecptMapper.selectById(id) == null) {
            throw exception(PRODUCT_RECPT_NOT_EXISTS);
        }
    }

    @Override
    public ProductRecptDO getProductRecpt(Long id) {
        return productRecptMapper.selectById(id);
    }

    @Override
    public List<ProductRecptDO> getProductRecptList(Collection<Long> ids) {
        return productRecptMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductRecptDO> getProductRecptPage(ProductRecptPageReqVO pageReqVO) {
        return productRecptMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductRecptDO> getProductRecptList(ProductRecptExportReqVO exportReqVO) {
        return productRecptMapper.selectList(exportReqVO);
    }

}
