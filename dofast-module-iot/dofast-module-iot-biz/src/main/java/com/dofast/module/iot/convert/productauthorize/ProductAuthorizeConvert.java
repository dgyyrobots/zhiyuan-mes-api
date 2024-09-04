package com.dofast.module.iot.convert.productauthorize;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.productauthorize.vo.*;
import com.dofast.module.iot.dal.dataobject.productauthorize.ProductAuthorizeDO;

/**
 * 产品授权码 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ProductAuthorizeConvert {

    ProductAuthorizeConvert INSTANCE = Mappers.getMapper(ProductAuthorizeConvert.class);

    ProductAuthorizeDO convert(ProductAuthorizeCreateReqVO bean);

    ProductAuthorizeDO convert(ProductAuthorizeUpdateReqVO bean);

    ProductAuthorizeRespVO convert(ProductAuthorizeDO bean);

    List<ProductAuthorizeRespVO> convertList(List<ProductAuthorizeDO> list);

    PageResult<ProductAuthorizeRespVO> convertPage(PageResult<ProductAuthorizeDO> page);

    List<ProductAuthorizeExcelVO> convertList02(List<ProductAuthorizeDO> list);

}
