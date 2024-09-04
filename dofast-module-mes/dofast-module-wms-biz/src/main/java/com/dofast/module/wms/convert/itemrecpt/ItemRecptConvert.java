package com.dofast.module.wms.convert.itemrecpt;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;

/**
 * 物料入库单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ItemRecptConvert {

    ItemRecptConvert INSTANCE = Mappers.getMapper(ItemRecptConvert.class);

    ItemRecptDO convert(ItemRecptCreateReqVO bean);

    ItemRecptDO convert(ItemRecptUpdateReqVO bean);

    ItemRecptRespVO convert(ItemRecptDO bean);
    ItemRecptUpdateReqVO convert01(ItemRecptDO bean);

    List<ItemRecptRespVO> convertList(List<ItemRecptDO> list);

    PageResult<ItemRecptRespVO> convertPage(PageResult<ItemRecptDO> page);

    List<ItemRecptExcelVO> convertList02(List<ItemRecptDO> list);

}
