package com.dofast.module.pro.convert.transconsume;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.transconsume.vo.*;
import com.dofast.module.pro.dal.dataobject.transconsume.TransConsumeDO;

/**
 * 物料消耗记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TransConsumeConvert {

    TransConsumeConvert INSTANCE = Mappers.getMapper(TransConsumeConvert.class);

    TransConsumeDO convert(TransConsumeCreateReqVO bean);

    TransConsumeDO convert(TransConsumeUpdateReqVO bean);

    TransConsumeRespVO convert(TransConsumeDO bean);

    List<TransConsumeRespVO> convertList(List<TransConsumeDO> list);

    PageResult<TransConsumeRespVO> convertPage(PageResult<TransConsumeDO> page);

    List<TransConsumeExcelVO> convertList02(List<TransConsumeDO> list);

}
