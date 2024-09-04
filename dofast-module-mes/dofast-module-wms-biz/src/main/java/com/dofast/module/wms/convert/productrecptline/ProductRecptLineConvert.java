package com.dofast.module.wms.convert.productrecptline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.productrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecptline.ProductRecptLineDO;

/**
 * 产品入库记录表行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductRecptLineConvert {

    ProductRecptLineConvert INSTANCE = Mappers.getMapper(ProductRecptLineConvert.class);

    ProductRecptLineDO convert(ProductRecptLineCreateReqVO bean);

    ProductRecptLineDO convert(ProductRecptLineUpdateReqVO bean);

    ProductRecptLineRespVO convert(ProductRecptLineDO bean);

    List<ProductRecptLineRespVO> convertList(List<ProductRecptLineDO> list);

    PageResult<ProductRecptLineRespVO> convertPage(PageResult<ProductRecptLineDO> page);

    List<ProductRecptLineExcelVO> convertList02(List<ProductRecptLineDO> list);

}
