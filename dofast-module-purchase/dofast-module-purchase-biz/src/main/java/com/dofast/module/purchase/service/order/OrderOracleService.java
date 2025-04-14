package com.dofast.module.purchase.service.order;

import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;

import java.util.List;
import java.util.Map;

public interface OrderOracleService {

    List<Map<String, Object>> initPurchaseOrder();

    List<Map<String, Object>> initPurchaseGoods();

    // 根据商品信息获取接收序列号
    Map<String,Object> getReceiveSeq(GoodsDO goods);

}
