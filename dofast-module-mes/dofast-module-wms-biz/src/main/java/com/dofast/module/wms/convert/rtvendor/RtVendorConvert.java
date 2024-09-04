package com.dofast.module.wms.convert.rtvendor;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.rtvendor.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendor.RtVendorDO;

/**
 * 供应商退货 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RtVendorConvert {

    RtVendorConvert INSTANCE = Mappers.getMapper(RtVendorConvert.class);

    RtVendorDO convert(RtVendorCreateReqVO bean);

    RtVendorDO convert(RtVendorUpdateReqVO bean);

    RtVendorRespVO convert(RtVendorDO bean);
    RtVendorUpdateReqVO convert01(RtVendorDO bean);

    List<RtVendorRespVO> convertList(List<RtVendorDO> list);

    PageResult<RtVendorRespVO> convertPage(PageResult<RtVendorDO> page);

    List<RtVendorExcelVO> convertList02(List<RtVendorDO> list);

}
