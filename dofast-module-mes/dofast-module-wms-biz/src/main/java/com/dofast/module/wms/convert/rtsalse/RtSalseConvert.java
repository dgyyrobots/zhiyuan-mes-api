package com.dofast.module.wms.convert.rtsalse;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.rtsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseDO;

/**
 * 产品销售退货单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RtSalseConvert {

    RtSalseConvert INSTANCE = Mappers.getMapper(RtSalseConvert.class);

    RtSalseDO convert(RtSalseCreateReqVO bean);

    RtSalseDO convert(RtSalseUpdateReqVO bean);

    RtSalseRespVO convert(RtSalseDO bean);
    RtSalseUpdateReqVO convert01(RtSalseDO bean);

    List<RtSalseRespVO> convertList(List<RtSalseDO> list);

    PageResult<RtSalseRespVO> convertPage(PageResult<RtSalseDO> page);

    List<RtSalseExcelVO> convertList02(List<RtSalseDO> list);

}
