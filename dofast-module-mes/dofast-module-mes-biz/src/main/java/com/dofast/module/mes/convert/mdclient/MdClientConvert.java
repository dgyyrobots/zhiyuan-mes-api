package com.dofast.module.mes.convert.mdclient;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdclient.vo.*;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;

/**
 * 客户 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdClientConvert {

    MdClientConvert INSTANCE = Mappers.getMapper(MdClientConvert.class);

    MdClientDO convert(MdClientCreateReqVO bean);

    MdClientDO convert(MdClientUpdateReqVO bean);

    MdClientRespVO convert(MdClientDO bean);

    List<MdClientRespVO> convertList(List<MdClientDO> list);

    PageResult<MdClientRespVO> convertPage(PageResult<MdClientDO> page);

    List<MdClientExcelVO> convertList02(List<MdClientDO> list);

}
