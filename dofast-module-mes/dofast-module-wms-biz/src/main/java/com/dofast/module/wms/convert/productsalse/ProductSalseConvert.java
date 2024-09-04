package com.dofast.module.wms.convert.productsalse;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.productsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseDO;

/**
 * 销售出库单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSalseConvert {

    ProductSalseConvert INSTANCE = Mappers.getMapper(ProductSalseConvert.class);

    ProductSalseDO convert(ProductSalseCreateReqVO bean);

    ProductSalseDO convert(ProductSalseUpdateReqVO bean);

    ProductSalseRespVO convert(ProductSalseDO bean);
    ProductSalseUpdateReqVO convert01(ProductSalseDO bean);

    List<ProductSalseRespVO> convertList(List<ProductSalseDO> list);

    PageResult<ProductSalseRespVO> convertPage(PageResult<ProductSalseDO> page);

    List<ProductSalseExcelVO> convertList02(List<ProductSalseDO> list);

}
