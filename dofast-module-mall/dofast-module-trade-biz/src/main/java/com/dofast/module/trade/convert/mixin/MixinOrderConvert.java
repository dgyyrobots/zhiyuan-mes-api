package com.dofast.module.trade.convert.mixin;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmCreateReqVO;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmRespVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderCreateReqVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderExcelVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderRespVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderUpdateReqVO;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售订单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface MixinOrderConvert {

    MixinOrderConvert INSTANCE = Mappers.getMapper(MixinOrderConvert.class);

    MixinOrderDO convert(MixinOrderCreateReqVO bean);

    MixinOrderDO convert(MixinOrderBpmCreateReqVO bpmCreateReqVO);

    MixinOrderDO convert(MixinOrderUpdateReqVO bean);

    MixinOrderRespVO convert(MixinOrderDO bean);

    List<MixinOrderRespVO> convertList(List<MixinOrderDO> list);

    PageResult<MixinOrderRespVO> convertPage(PageResult<MixinOrderDO> page);

    default PageResult<MixinOrderRespVO> convertPage(PageResult<MixinOrderDO> page, List<TradeOrderDO> tradeOrders) {
        PageResult<MixinOrderRespVO> result = convertPage(page);
        for (MixinOrderRespVO mixinOrder : result.getList()) {
            List<String> orderNos = new ArrayList<>();
            for (TradeOrderDO tradeOrder: tradeOrders){
                if (tradeOrder.getMixinOrderId() == mixinOrder.getId()){
                    orderNos.add(tradeOrder.getNo());
                }
            }
            mixinOrder.setTradeOrderNos(orderNos);
        }
        return result;
    }

    List<MixinOrderExcelVO> convertList02(List<MixinOrderDO> list);

    MixinOrderBpmRespVO convertBpm(MixinOrderDO leave);

    PageResult<MixinOrderBpmRespVO> convertPageBpm(PageResult<MixinOrderDO> pageResult);
}
