package com.dofast.module.system.convert.user;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.controller.admin.user.vo.usersFace.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.system.dal.dataobject.user.UsersFaceDO;

/**
 * 用户人脸数据 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface UsersFaceConvert {

    UsersFaceConvert INSTANCE = Mappers.getMapper(UsersFaceConvert.class);

    UsersFaceDO convert(UsersFaceCreateReqVO bean);

    UsersFaceDO convert(UsersFaceUpdateReqVO bean);

    UsersFaceRespVO convert(UsersFaceDO bean);

    List<UsersFaceRespVO> convertList(List<UsersFaceDO> list);

    PageResult<UsersFaceRespVO> convertPage(PageResult<UsersFaceDO> page);

    List<UsersFaceExcelVO> convertList02(List<UsersFaceDO> list);

}
