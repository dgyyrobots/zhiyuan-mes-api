package com.dofast.module.wms.convert.productrecpt;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.productrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptDO;

/**
 * 产品入库录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductRecptConvert {

    ProductRecptConvert INSTANCE = Mappers.getMapper(ProductRecptConvert.class);

    ProductRecptDO convert(ProductRecptCreateReqVO bean);

    ProductRecptDO convert(ProductRecptUpdateReqVO bean);

    ProductRecptRespVO convert(ProductRecptDO bean);
    ProductRecptUpdateReqVO convert01(ProductRecptDO bean);

    List<ProductRecptRespVO> convertList(List<ProductRecptDO> list);

    PageResult<ProductRecptRespVO> convertPage(PageResult<ProductRecptDO> page);

    List<ProductRecptExcelVO> convertList02(List<ProductRecptDO> list);

}
