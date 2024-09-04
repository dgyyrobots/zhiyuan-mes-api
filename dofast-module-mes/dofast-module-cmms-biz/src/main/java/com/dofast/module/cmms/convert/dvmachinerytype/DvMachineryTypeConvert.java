package com.dofast.module.cmms.convert.dvmachinerytype;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvmachinerytype.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvmachinerytype.DvMachineryTypeDO;

/**
 * 设备类型 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvMachineryTypeConvert {

    DvMachineryTypeConvert INSTANCE = Mappers.getMapper(DvMachineryTypeConvert.class);

    DvMachineryTypeDO convert(DvMachineryTypeCreateReqVO bean);

    DvMachineryTypeDO convert(DvMachineryTypeUpdateReqVO bean);

    DvMachineryTypeRespVO convert(DvMachineryTypeDO bean);

    List<DvMachineryTypeRespVO> convertList(List<DvMachineryTypeDO> list);

    PageResult<DvMachineryTypeRespVO> convertPage(PageResult<DvMachineryTypeDO> page);

    List<DvMachineryTypeExcelVO> convertList02(List<DvMachineryTypeDO> list);

}
