package com.dofast.module.channel.convert.shop;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.shop.vo.*;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 店铺授权 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ShopConvert {

    ShopConvert INSTANCE = Mappers.getMapper(ShopConvert.class);

    ShopDO convert(ShopCreateReqVO bean);

    ShopDO convert(ShopUpdateReqVO bean);

    ShopRespVO convert(ShopDO bean);

    List<ShopRespVO> convertList(List<ShopDO> list);

    PageResult<ShopRespVO> convertPage(PageResult<ShopDO> page);

    List<ShopExcelVO> convertList02(List<ShopDO> list);

    List<ChannelShopVO> convertList03(List<ChannelShopVO> list);

}
