package com.dofast.module.purchase.convert.order;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmRespVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmRespVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoiceRespVO;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.purchase.controller.admin.order.vo.*;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;

/**
 * 采购订单 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderDO convert(OrderCreateReqVO bean);

    OrderDO convert(OrderBpmCreateReqVO bpmCreateReqVO);

    OrderUpdateReqVO convert01(OrderDO bean);

    OrderDO convert(OrderUpdateReqVO bean);

    OrderRespVO convert(OrderDO bean);

    List<OrderRespVO> convertList(List<OrderDO> list);

    PageResult<OrderRespVO> convertPage(PageResult<OrderDO> page);

    List<OrderExcelVO> convertList02(List<OrderDO> list);

    InvoiceBpmRespVO convertBpm(OrderDO orderDO);

    PageResult<OrderBpmRespVO> convertPageBpm(PageResult<OrderDO> pageResult);
}
