package com.dofast.module.wms.convert.productsalseline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.productsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;

/**
 * 产品销售出库行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSalseLineConvert {

    ProductSalseLineConvert INSTANCE = Mappers.getMapper(ProductSalseLineConvert.class);

    ProductSalseLineDO convert(ProductSalseLineCreateReqVO bean);

    ProductSalseLineDO convert(ProductSalseLineUpdateReqVO bean);

    ProductSalseLineRespVO convert(ProductSalseLineDO bean);

    List<ProductSalseLineRespVO> convertList(List<ProductSalseLineDO> list);

    PageResult<ProductSalseLineRespVO> convertPage(PageResult<ProductSalseLineDO> page);

    List<ProductSalseLineExcelVO> convertList02(List<ProductSalseLineDO> list);

}
