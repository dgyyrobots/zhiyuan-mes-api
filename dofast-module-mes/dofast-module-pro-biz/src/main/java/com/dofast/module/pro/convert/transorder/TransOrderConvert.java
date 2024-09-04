package com.dofast.module.pro.convert.transorder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.transorder.vo.*;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;

/**
 * 流转单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TransOrderConvert {

    TransOrderConvert INSTANCE = Mappers.getMapper(TransOrderConvert.class);

    TransOrderDO convert(TransOrderCreateReqVO bean);

    TransOrderDO convert(TransOrderUpdateReqVO bean);

    TransOrderRespVO convert(TransOrderDO bean);

    List<TransOrderRespVO> convertList(List<TransOrderDO> list);

    PageResult<TransOrderRespVO> convertPage(PageResult<TransOrderDO> page);

    List<TransOrderExcelVO> convertList02(List<TransOrderDO> list);

}
