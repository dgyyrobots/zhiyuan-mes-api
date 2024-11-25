package com.dofast.module.wms.convert.allocatedline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.allocatedline.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedline.AllocatedLineDO;

/**
 * 调拨单身 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface AllocatedLineConvert {

    AllocatedLineConvert INSTANCE = Mappers.getMapper(AllocatedLineConvert.class);

    AllocatedLineDO convert(AllocatedLineCreateReqVO bean);

    AllocatedLineDO convert(AllocatedLineUpdateReqVO bean);

    AllocatedLineRespVO convert(AllocatedLineDO bean);

    List<AllocatedLineRespVO> convertList(List<AllocatedLineDO> list);

    PageResult<AllocatedLineRespVO> convertPage(PageResult<AllocatedLineDO> page);

    List<AllocatedLineExcelVO> convertList02(List<AllocatedLineDO> list);

}
