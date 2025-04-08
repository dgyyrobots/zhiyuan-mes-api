package com.dofast.module.mes.convert.mdvendor;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.api.MdVendorApi.dto.MdVendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdvendor.vo.*;
import com.dofast.module.mes.dal.dataobject.mdvendor.MdVendorDO;

/**
 * 供应商 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PadMdVendorConvert {

    PadMdVendorConvert INSTANCE = Mappers.getMapper(PadMdVendorConvert.class);

    MdVendorDO convert(MdVendorCreateReqVO bean);

    MdVendorDO convert(MdVendorUpdateReqVO bean);

    MdVendorRespVO convert(MdVendorDO bean);

    MdVendorDTO convert01(MdVendorDO bean);

    List<MdVendorRespVO> convertList(List<MdVendorDO> list);

    PageResult<MdVendorRespVO> convertPage(PageResult<MdVendorDO> page);

    List<MdVendorExcelVO> convertList02(List<MdVendorDO> list);

}
