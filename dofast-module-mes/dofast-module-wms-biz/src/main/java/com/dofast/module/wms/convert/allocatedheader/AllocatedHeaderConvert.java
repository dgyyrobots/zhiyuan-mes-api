package com.dofast.module.wms.convert.allocatedheader;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.allocatedheader.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedheader.AllocatedHeaderDO;

/**
 * 调拨单头 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface AllocatedHeaderConvert {

    AllocatedHeaderConvert INSTANCE = Mappers.getMapper(AllocatedHeaderConvert.class);

    AllocatedHeaderDO convert(AllocatedHeaderCreateReqVO bean);

    AllocatedHeaderDO convert(AllocatedHeaderUpdateReqVO bean);

    AllocatedHeaderUpdateReqVO convert01(AllocatedHeaderDO bean);


    AllocatedHeaderRespVO convert(AllocatedHeaderDO bean);

    List<AllocatedHeaderRespVO> convertList(List<AllocatedHeaderDO> list);

    PageResult<AllocatedHeaderRespVO> convertPage(PageResult<AllocatedHeaderDO> page);

    List<AllocatedHeaderExcelVO> convertList02(List<AllocatedHeaderDO> list);

}
