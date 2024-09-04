package com.dofast.module.wms.convert.rtvendorline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.rtvendorline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendorline.RtVendorLineDO;

/**
 * 供应商退货行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RtVendorLineConvert {

    RtVendorLineConvert INSTANCE = Mappers.getMapper(RtVendorLineConvert.class);

    RtVendorLineDO convert(RtVendorLineCreateReqVO bean);

    RtVendorLineDO convert(RtVendorLineUpdateReqVO bean);

    RtVendorLineRespVO convert(RtVendorLineDO bean);

    List<RtVendorLineRespVO> convertList(List<RtVendorLineDO> list);

    PageResult<RtVendorLineRespVO> convertPage(PageResult<RtVendorLineDO> page);

    List<RtVendorLineExcelVO> convertList02(List<RtVendorLineDO> list);

}
