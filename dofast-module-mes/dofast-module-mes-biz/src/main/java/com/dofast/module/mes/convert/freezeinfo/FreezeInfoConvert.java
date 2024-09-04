package com.dofast.module.mes.convert.freezeinfo;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.freezeinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.freezeinfo.FreezeInfoDO;

/**
 * 产品冻结信息 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface FreezeInfoConvert {

    FreezeInfoConvert INSTANCE = Mappers.getMapper(FreezeInfoConvert.class);

    FreezeInfoDO convert(FreezeInfoCreateReqVO bean);

    FreezeInfoDO convert(FreezeInfoUpdateReqVO bean);

    FreezeInfoRespVO convert(FreezeInfoDO bean);

    List<FreezeInfoRespVO> convertList(List<FreezeInfoDO> list);

    PageResult<FreezeInfoRespVO> convertPage(PageResult<FreezeInfoDO> page);

    List<FreezeInfoExcelVO> convertList02(List<FreezeInfoDO> list);

}
