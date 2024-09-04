package com.dofast.module.mes.convert.mdworkstationtool;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdworkstationtool.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationtool.MdWorkstationToolDO;

/**
 * 工装夹具资源 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdWorkstationToolConvert {

    MdWorkstationToolConvert INSTANCE = Mappers.getMapper(MdWorkstationToolConvert.class);

    MdWorkstationToolDO convert(MdWorkstationToolCreateReqVO bean);

    MdWorkstationToolDO convert(MdWorkstationToolUpdateReqVO bean);

    MdWorkstationToolRespVO convert(MdWorkstationToolDO bean);

    List<MdWorkstationToolRespVO> convertList(List<MdWorkstationToolDO> list);

    PageResult<MdWorkstationToolRespVO> convertPage(PageResult<MdWorkstationToolDO> page);

    List<MdWorkstationToolExcelVO> convertList02(List<MdWorkstationToolDO> list);

}
