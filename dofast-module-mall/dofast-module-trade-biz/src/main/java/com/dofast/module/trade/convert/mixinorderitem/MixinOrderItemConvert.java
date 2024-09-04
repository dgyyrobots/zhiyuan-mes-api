package com.dofast.module.trade.convert.mixinorderitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemCreateReqVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemExcelVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemRespVO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.MixinOrderItemUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.mixinorderitem.MixinOrderItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 销售的物料明细 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface MixinOrderItemConvert {

    MixinOrderItemConvert INSTANCE = Mappers.getMapper(MixinOrderItemConvert.class);

    MixinOrderItemDO convert(MixinOrderItemCreateReqVO bean);

    MixinOrderItemDO convert(MixinOrderItemUpdateReqVO bean);

    MixinOrderItemRespVO convert(MixinOrderItemDO bean);

    List<MixinOrderItemRespVO> convertList(List<MixinOrderItemDO> list);

    PageResult<MixinOrderItemRespVO> convertPage(PageResult<MixinOrderItemDO> page);

    List<MixinOrderItemExcelVO> convertList02(List<MixinOrderItemDO> list);

}
