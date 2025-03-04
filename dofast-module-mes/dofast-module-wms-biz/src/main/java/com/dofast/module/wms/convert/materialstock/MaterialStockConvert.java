package com.dofast.module.wms.convert.materialstock;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.materialstock.vo.*;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;

/**
 * 库存记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MaterialStockConvert {

    MaterialStockConvert INSTANCE = Mappers.getMapper(MaterialStockConvert.class);

    MaterialStockDO convert(MaterialStockCreateReqVO bean);

    MaterialStockCreateReqVO convert01(MaterialStockDO bean);

    MaterialStockDO convert(MaterialStockUpdateReqVO bean);

    MaterialStockRespVO convert(MaterialStockDO bean);

    MaterialStockUpdateReqVO convert02(MaterialStockDO bean);

    List<MaterialStockRespVO> convertList(List<MaterialStockDO> list);

    PageResult<MaterialStockRespVO> convertPage(PageResult<MaterialStockDO> page);

    List<MaterialStockExcelVO> convertList02(List<MaterialStockDO> list);

}
