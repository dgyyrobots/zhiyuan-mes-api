package com.dofast.module.iot.convert.product;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.product.vo.*;
import com.dofast.module.iot.dal.dataobject.product.ProductDO;

/**
 * 产品 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    ProductDO convert(ProductCreateReqVO bean);

    ProductDO convert(ProductUpdateReqVO bean);

    ProductRespVO convert(ProductDO bean);

    List<ProductRespVO> convertList(List<ProductDO> list);

    PageResult<ProductRespVO> convertPage(PageResult<ProductDO> page);

    List<ProductExcelVO> convertList02(List<ProductDO> list);

}
