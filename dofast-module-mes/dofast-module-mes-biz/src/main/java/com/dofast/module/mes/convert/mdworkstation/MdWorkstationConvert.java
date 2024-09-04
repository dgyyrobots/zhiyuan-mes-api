package com.dofast.module.mes.convert.mdworkstation;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.api.WorkStationAPi.dto.WorkStationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;

/**
 * 工作站 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdWorkstationConvert {

    MdWorkstationConvert INSTANCE = Mappers.getMapper(MdWorkstationConvert.class);

    MdWorkstationDO convert(MdWorkstationCreateReqVO bean);

    MdWorkstationDO convert(MdWorkstationUpdateReqVO bean);
    WorkStationDTO convert01(MdWorkstationDO bean);

    MdWorkstationRespVO convert(MdWorkstationDO bean);

    List<MdWorkstationRespVO> convertList(List<MdWorkstationDO> list);

    PageResult<MdWorkstationRespVO> convertPage(PageResult<MdWorkstationDO> page);

    List<MdWorkstationExcelVO> convertList02(List<MdWorkstationDO> list);

}
