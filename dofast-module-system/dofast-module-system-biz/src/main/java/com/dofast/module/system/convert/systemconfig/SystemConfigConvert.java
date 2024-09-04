package com.dofast.module.system.convert.systemconfig;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.system.controller.admin.systemconfig.vo.*;
import com.dofast.module.system.dal.dataobject.systemconfig.SystemConfigDO;

/**
 * 参数配置 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface SystemConfigConvert {

    SystemConfigConvert INSTANCE = Mappers.getMapper(SystemConfigConvert.class);

    SystemConfigDO convert(SystemConfigCreateReqVO bean);

    SystemConfigDO convert(SystemConfigUpdateReqVO bean);

    SystemConfigRespVO convert(SystemConfigDO bean);

    List<SystemConfigRespVO> convertList(List<SystemConfigDO> list);

    PageResult<SystemConfigRespVO> convertPage(PageResult<SystemConfigDO> page);

    List<SystemConfigExcelVO> convertList02(List<SystemConfigDO> list);

}
