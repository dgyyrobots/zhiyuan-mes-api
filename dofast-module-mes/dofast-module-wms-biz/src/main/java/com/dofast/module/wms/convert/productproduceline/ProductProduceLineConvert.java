package com.dofast.module.wms.convert.productproduceline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.productproduceline.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;

/**
 * 产品产出记录表行 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ProductProduceLineConvert {

    ProductProduceLineConvert INSTANCE = Mappers.getMapper(ProductProduceLineConvert.class);

    ProductProduceLineDO convert(ProductProduceLineCreateReqVO bean);

    ProductProduceLineDO convert(ProductProduceLineUpdateReqVO bean);

    ProductProduceLineRespVO convert(ProductProduceLineDO bean);

    List<ProductProduceLineRespVO> convertList(List<ProductProduceLineDO> list);

    PageResult<ProductProduceLineRespVO> convertPage(PageResult<ProductProduceLineDO> page);

    List<ProductProduceLineExcelVO> convertList02(List<ProductProduceLineDO> list);

}
