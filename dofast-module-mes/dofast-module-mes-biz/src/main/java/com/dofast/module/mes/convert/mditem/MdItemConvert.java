package com.dofast.module.mes.convert.mditem;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mditem.vo.*;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;

/**
 * 物料产品 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdItemConvert {

    MdItemConvert INSTANCE = Mappers.getMapper(MdItemConvert.class);

    MdItemDO convert(MdItemCreateReqVO bean);

    MdItemDO convert(MdItemUpdateReqVO bean);
    MdItemDTO convert01(MdItemDO bean);
    MdItemRespVO convert(MdItemDO bean);

    MdItemUpdateReqVO convert02(MdItemDO bean);

    List<MdItemRespVO> convertList(List<MdItemDO> list);

    PageResult<MdItemRespVO> convertPage(PageResult<MdItemDO> page);

    List<MdItemExcelVO> convertList02(List<MdItemDO> list);

}
