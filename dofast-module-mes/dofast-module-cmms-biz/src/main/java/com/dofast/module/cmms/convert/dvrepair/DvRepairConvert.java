package com.dofast.module.cmms.convert.dvrepair;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvrepair.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvrepair.DvRepairDO;

/**
 * 设备维修单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvRepairConvert {

    DvRepairConvert INSTANCE = Mappers.getMapper(DvRepairConvert.class);

    DvRepairDO convert(DvRepairCreateReqVO bean);

    DvRepairDO convert(DvRepairUpdateReqVO bean);

    DvRepairRespVO convert(DvRepairDO bean);

    List<DvRepairRespVO> convertList(List<DvRepairDO> list);

    PageResult<DvRepairRespVO> convertPage(PageResult<DvRepairDO> page);

    List<DvRepairExcelVO> convertList02(List<DvRepairDO> list);

}
