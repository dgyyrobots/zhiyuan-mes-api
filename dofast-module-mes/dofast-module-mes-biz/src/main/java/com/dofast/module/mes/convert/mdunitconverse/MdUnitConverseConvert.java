package com.dofast.module.mes.convert.mdunitconverse;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdunitconverse.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;

/**
 * 单位换算 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface MdUnitConverseConvert {

    MdUnitConverseConvert INSTANCE = Mappers.getMapper(MdUnitConverseConvert.class);

    MdUnitConverseDO convert(MdUnitConverseCreateReqVO bean);

    MdUnitConverseDO convert(MdUnitConverseUpdateReqVO bean);

    MdUnitConverseRespVO convert(MdUnitConverseDO bean);

    List<MdUnitConverseRespVO> convertList(List<MdUnitConverseDO> list);

    PageResult<MdUnitConverseRespVO> convertPage(PageResult<MdUnitConverseDO> page);

    List<MdUnitConverseExcelVO> convertList02(List<MdUnitConverseDO> list);

}
