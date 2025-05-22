package com.dofast.module.system.convert.dj002;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.system.controller.admin.dj002.vo.*;
import com.dofast.module.system.dal.dataobject.dj002.Dj002DO;

/**
 * 系统地址信息 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface Dj002Convert {

    Dj002Convert INSTANCE = Mappers.getMapper(Dj002Convert.class);

    Dj002DO convert(Dj002CreateReqVO bean);

    Dj002DO convert(Dj002UpdateReqVO bean);

    Dj002RespVO convert(Dj002DO bean);

    List<Dj002RespVO> convertList(List<Dj002DO> list);

    PageResult<Dj002RespVO> convertPage(PageResult<Dj002DO> page);

    List<Dj002ExcelVO> convertList02(List<Dj002DO> list);

}
