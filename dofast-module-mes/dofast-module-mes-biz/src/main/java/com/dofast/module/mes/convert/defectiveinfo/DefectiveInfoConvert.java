package com.dofast.module.mes.convert.defectiveinfo;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.defectiveinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.defectiveinfo.DefectiveInfoDO;

/**
 * 不良品信息管理 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface DefectiveInfoConvert {

    DefectiveInfoConvert INSTANCE = Mappers.getMapper(DefectiveInfoConvert.class);

    DefectiveInfoDO convert(DefectiveInfoCreateReqVO bean);

    DefectiveInfoDO convert(DefectiveInfoUpdateReqVO bean);

    DefectiveInfoRespVO convert(DefectiveInfoDO bean);

    List<DefectiveInfoRespVO> convertList(List<DefectiveInfoDO> list);

    PageResult<DefectiveInfoRespVO> convertPage(PageResult<DefectiveInfoDO> page);

    List<DefectiveInfoExcelVO> convertList02(List<DefectiveInfoDO> list);

}
