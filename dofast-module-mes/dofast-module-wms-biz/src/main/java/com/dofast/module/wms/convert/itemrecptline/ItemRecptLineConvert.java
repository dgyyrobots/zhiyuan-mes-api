package com.dofast.module.wms.convert.itemrecptline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.itemrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecptline.ItemRecptLineDO;

/**
 * 物料入库单行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ItemRecptLineConvert {

    ItemRecptLineConvert INSTANCE = Mappers.getMapper(ItemRecptLineConvert.class);

    ItemRecptLineDO convert(ItemRecptLineCreateReqVO bean);

    ItemRecptLineDO convert(ItemRecptLineUpdateReqVO bean);

    ItemRecptLineRespVO convert(ItemRecptLineDO bean);

    List<ItemRecptLineRespVO> convertList(List<ItemRecptLineDO> list);

    PageResult<ItemRecptLineRespVO> convertPage(PageResult<ItemRecptLineDO> page);

    List<ItemRecptLineExcelVO> convertList02(List<ItemRecptLineDO> list);

}
