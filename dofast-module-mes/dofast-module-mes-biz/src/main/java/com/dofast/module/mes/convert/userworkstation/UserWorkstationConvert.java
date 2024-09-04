package com.dofast.module.mes.convert.userworkstation;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.userworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;

/**
 * 用户工作站绑定关系 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface UserWorkstationConvert {

    UserWorkstationConvert INSTANCE = Mappers.getMapper(UserWorkstationConvert.class);

    UserWorkstationDO convert(UserWorkstationCreateReqVO bean);

    UserWorkstationDO convert(UserWorkstationUpdateReqVO bean);

    UserWorkstationRespVO convert(UserWorkstationDO bean);

    List<UserWorkstationRespVO> convertList(List<UserWorkstationDO> list);

    PageResult<UserWorkstationRespVO> convertPage(PageResult<UserWorkstationDO> page);

    List<UserWorkstationExcelVO> convertList02(List<UserWorkstationDO> list);

}
