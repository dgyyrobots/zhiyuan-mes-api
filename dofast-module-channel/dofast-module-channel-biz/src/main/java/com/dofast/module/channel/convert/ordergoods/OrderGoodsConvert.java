package com.dofast.module.channel.convert.ordergoods;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.api.ordergoods.dto.OrderGoodsGetDTO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsCreateReqVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsExcelVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsRespVO;
import com.dofast.module.channel.controller.admin.ordergoods.vo.OrderGoodsUpdateReqVO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 子订单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface OrderGoodsConvert {

    OrderGoodsConvert INSTANCE = Mappers.getMapper(OrderGoodsConvert.class);

    OrderGoodsDO convert(OrderGoodsCreateReqVO bean);

    OrderGoodsDO convert(OrderGoodsUpdateReqVO bean);

    OrderGoodsRespVO convert(OrderGoodsDO bean);

    OrderGoodsGetDTO convertDTO(OrderGoodsDO bean);

    List<OrderGoodsRespVO> convertList(List<OrderGoodsDO> list);

    default List<OrderGoodsGetDTO> convertDTOList(List<OrderGoodsDO> list) {
        return list.stream().map((OrderGoodsDO item) -> OrderGoodsConvert.INSTANCE.convertDTO(item)).collect(Collectors.toList());
    }

    PageResult<OrderGoodsRespVO> convertPage(PageResult<OrderGoodsDO> page);

    List<OrderGoodsExcelVO> convertList02(List<OrderGoodsDO> list);

}
