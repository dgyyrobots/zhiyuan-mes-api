package com.dofast.module.wms.convert.itemconsume;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.itemconsume.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;

/**
 * 物料消耗记录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ItemConsumeConvert {

    ItemConsumeConvert INSTANCE = Mappers.getMapper(ItemConsumeConvert.class);

    ItemConsumeDO convert(ItemConsumeCreateReqVO bean);

    ItemConsumeDO convert(ItemConsumeUpdateReqVO bean);

    ItemConsumeRespVO convert(ItemConsumeDO bean);

    ItemConsumeUpdateReqVO convert01(ItemConsumeDO bean);

    List<ItemConsumeRespVO> convertList(List<ItemConsumeDO> list);

    PageResult<ItemConsumeRespVO> convertPage(PageResult<ItemConsumeDO> page);

    List<ItemConsumeExcelVO> convertList02(List<ItemConsumeDO> list);

}
