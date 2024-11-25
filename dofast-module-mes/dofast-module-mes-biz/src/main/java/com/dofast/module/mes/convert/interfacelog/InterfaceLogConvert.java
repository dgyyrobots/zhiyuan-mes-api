package com.dofast.module.mes.convert.interfacelog;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.interfacelog.vo.*;
import com.dofast.module.mes.dal.dataobject.interfacelog.InterfaceLogDO;

/**
 * 接口操作日志 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface InterfaceLogConvert {

    InterfaceLogConvert INSTANCE = Mappers.getMapper(InterfaceLogConvert.class);

    InterfaceLogDO convert(InterfaceLogCreateReqVO bean);

    InterfaceLogDO convert(InterfaceLogUpdateReqVO bean);

    InterfaceLogRespVO convert(InterfaceLogDO bean);

    List<InterfaceLogRespVO> convertList(List<InterfaceLogDO> list);

    PageResult<InterfaceLogRespVO> convertPage(PageResult<InterfaceLogDO> page);

    List<InterfaceLogExcelVO> convertList02(List<InterfaceLogDO> list);

}
