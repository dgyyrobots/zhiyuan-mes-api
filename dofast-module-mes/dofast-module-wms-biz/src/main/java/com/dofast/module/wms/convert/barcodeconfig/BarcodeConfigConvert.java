package com.dofast.module.wms.convert.barcodeconfig;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.barcodeconfig.vo.*;
import com.dofast.module.wms.dal.dataobject.barcodeconfig.BarcodeConfigDO;

/**
 * 条码配置 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BarcodeConfigConvert {

    BarcodeConfigConvert INSTANCE = Mappers.getMapper(BarcodeConfigConvert.class);

    BarcodeConfigDO convert(BarcodeConfigCreateReqVO bean);

    BarcodeConfigDO convert(BarcodeConfigUpdateReqVO bean);

    BarcodeConfigRespVO convert(BarcodeConfigDO bean);

    List<BarcodeConfigRespVO> convertList(List<BarcodeConfigDO> list);

    PageResult<BarcodeConfigRespVO> convertPage(PageResult<BarcodeConfigDO> page);

    List<BarcodeConfigExcelVO> convertList02(List<BarcodeConfigDO> list);

}
