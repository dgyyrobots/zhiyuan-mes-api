package com.dofast.module.wms.convert.barcode;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.barcode.vo.*;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;

/**
 * 条码清单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BarcodeConvert {

    BarcodeConvert INSTANCE = Mappers.getMapper(BarcodeConvert.class);

    BarcodeDO convert(BarcodeCreateReqVO bean);

    BarcodeDO convert(BarcodeUpdateReqVO bean);

    BarcodeRespVO convert(BarcodeDO bean);

    List<BarcodeRespVO> convertList(List<BarcodeDO> list);

    PageResult<BarcodeRespVO> convertPage(PageResult<BarcodeDO> page);

    List<BarcodeExcelVO> convertList02(List<BarcodeDO> list);

}
