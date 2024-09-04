package com.dofast.module.wms.convert.itemconsumeline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.itemconsumeline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsumeline.ItemConsumeLineDO;

/**
 * 物料消耗记录行 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ItemConsumeLineConvert {

    ItemConsumeLineConvert INSTANCE = Mappers.getMapper(ItemConsumeLineConvert.class);

    ItemConsumeLineDO convert(ItemConsumeLineCreateReqVO bean);

    ItemConsumeLineDO convert(ItemConsumeLineUpdateReqVO bean);

    ItemConsumeLineRespVO convert(ItemConsumeLineDO bean);

    List<ItemConsumeLineRespVO> convertList(List<ItemConsumeLineDO> list);

    PageResult<ItemConsumeLineRespVO> convertPage(PageResult<ItemConsumeLineDO> page);

    List<ItemConsumeLineExcelVO> convertList02(List<ItemConsumeLineDO> list);

}
