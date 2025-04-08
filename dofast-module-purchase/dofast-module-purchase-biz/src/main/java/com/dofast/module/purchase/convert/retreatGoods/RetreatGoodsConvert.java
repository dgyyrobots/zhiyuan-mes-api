package com.dofast.module.purchase.convert.retreatGoods;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.purchase.controller.admin.retreatGoods.vo.*;
import com.dofast.module.purchase.dal.dataobject.retreatGoods.RetreatGoodsDO;

/**
 * ERP仓退单单身 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface RetreatGoodsConvert {

    RetreatGoodsConvert INSTANCE = Mappers.getMapper(RetreatGoodsConvert.class);

    RetreatGoodsDO convert(RetreatGoodsCreateReqVO bean);

    RetreatGoodsDO convert(RetreatGoodsUpdateReqVO bean);

    RetreatGoodsRespVO convert(RetreatGoodsDO bean);

    List<RetreatGoodsRespVO> convertList(List<RetreatGoodsDO> list);

    PageResult<RetreatGoodsRespVO> convertPage(PageResult<RetreatGoodsDO> page);

    List<RetreatGoodsExcelVO> convertList02(List<RetreatGoodsDO> list);

}
