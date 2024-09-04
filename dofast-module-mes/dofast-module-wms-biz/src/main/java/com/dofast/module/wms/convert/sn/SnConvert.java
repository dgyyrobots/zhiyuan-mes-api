package com.dofast.module.wms.convert.sn;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.sn.vo.*;
import com.dofast.module.wms.dal.dataobject.sn.SnDO;

/**
 * SN码 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SnConvert {

    SnConvert INSTANCE = Mappers.getMapper(SnConvert.class);

    SnDO convert(SnCreateReqVO bean);

    SnDO convert(SnUpdateReqVO bean);

    SnRespVO convert(SnDO bean);

    List<SnRespVO> convertList(List<SnDO> list);

    PageResult<SnRespVO> convertPage(PageResult<SnDO> page);

    List<SnExcelVO> convertList02(List<SnDO> list);

}
