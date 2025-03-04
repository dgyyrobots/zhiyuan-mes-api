package com.dofast.module.wms.convert.electroformheader;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.electroformheader.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformheader.ElectroformHeaderDO;

/**
 * 制版房领料单头 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ElectroformHeaderConvert {

    ElectroformHeaderConvert INSTANCE = Mappers.getMapper(ElectroformHeaderConvert.class);

    ElectroformHeaderDO convert(ElectroformHeaderCreateReqVO bean);

    ElectroformHeaderDO convert(ElectroformHeaderUpdateReqVO bean);

    ElectroformHeaderRespVO convert(ElectroformHeaderDO bean);

    List<ElectroformHeaderRespVO> convertList(List<ElectroformHeaderDO> list);

    PageResult<ElectroformHeaderRespVO> convertPage(PageResult<ElectroformHeaderDO> page);

    List<ElectroformHeaderExcelVO> convertList02(List<ElectroformHeaderDO> list);

}
