package com.dofast.module.mes.convert.mdworkstationmachine;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdworkstationmachine.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationmachine.MdWorkstationMachineDO;

/**
 * 设备资源 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdWorkstationMachineConvert {

    MdWorkstationMachineConvert INSTANCE = Mappers.getMapper(MdWorkstationMachineConvert.class);

    MdWorkstationMachineDO convert(MdWorkstationMachineCreateReqVO bean);

    MdWorkstationMachineDO convert(MdWorkstationMachineUpdateReqVO bean);

    MdWorkstationMachineRespVO convert(MdWorkstationMachineDO bean);

    List<MdWorkstationMachineRespVO> convertList(List<MdWorkstationMachineDO> list);

    PageResult<MdWorkstationMachineRespVO> convertPage(PageResult<MdWorkstationMachineDO> page);

    List<MdWorkstationMachineExcelVO> convertList02(List<MdWorkstationMachineDO> list);

}
