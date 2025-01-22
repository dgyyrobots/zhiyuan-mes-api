package com.dofast.module.wms.convert.productproduce;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.productproduce.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;

/**
 * 产品产出记录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ProductProduceConvert {

    ProductProduceConvert INSTANCE = Mappers.getMapper(ProductProduceConvert.class);

    ProductProduceDO convert(ProductProduceCreateReqVO bean);

    ProductProduceDO convert(ProductProduceUpdateReqVO bean);

    ProductProduceRespVO convert(ProductProduceDO bean);

    ProductProduceUpdateReqVO convert01(ProductProduceDO bean);

    List<ProductProduceRespVO> convertList(List<ProductProduceDO> list);

    PageResult<ProductProduceRespVO> convertPage(PageResult<ProductProduceDO> page);

    List<ProductProduceExcelVO> convertList02(List<ProductProduceDO> list);

}
