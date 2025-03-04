package com.dofast.module.mes.convert.electroplatelog;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.electroplatelog.vo.*;
import com.dofast.module.mes.dal.dataobject.electroplatelog.ElectroplateLogDO;

/**
 * 制版房记录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ElectroplateLogConvert {

    ElectroplateLogConvert INSTANCE = Mappers.getMapper(ElectroplateLogConvert.class);

    ElectroplateLogDO convert(ElectroplateLogCreateReqVO bean);

    ElectroplateLogDO convert(ElectroplateLogUpdateReqVO bean);

    ElectroplateLogRespVO convert(ElectroplateLogDO bean);

    List<ElectroplateLogRespVO> convertList(List<ElectroplateLogDO> list);

    PageResult<ElectroplateLogRespVO> convertPage(PageResult<ElectroplateLogDO> page);

    List<ElectroplateLogExcelVO> convertList02(List<ElectroplateLogDO> list);

}
