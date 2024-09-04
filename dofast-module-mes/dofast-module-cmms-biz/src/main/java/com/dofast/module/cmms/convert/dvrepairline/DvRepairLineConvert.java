package com.dofast.module.cmms.convert.dvrepairline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvrepairline.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvrepairline.DvRepairLineDO;

/**
 * 设备维修单行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvRepairLineConvert {

    DvRepairLineConvert INSTANCE = Mappers.getMapper(DvRepairLineConvert.class);

    DvRepairLineDO convert(DvRepairLineCreateReqVO bean);

    DvRepairLineDO convert(DvRepairLineUpdateReqVO bean);

    DvRepairLineRespVO convert(DvRepairLineDO bean);

    List<DvRepairLineRespVO> convertList(List<DvRepairLineDO> list);

    PageResult<DvRepairLineRespVO> convertPage(PageResult<DvRepairLineDO> page);

    List<DvRepairLineExcelVO> convertList02(List<DvRepairLineDO> list);

}
