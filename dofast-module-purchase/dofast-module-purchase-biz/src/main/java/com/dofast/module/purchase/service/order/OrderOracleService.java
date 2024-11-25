package com.dofast.module.purchase.service.order;

import java.util.List;
import java.util.Map;

public interface OrderOracleService {

    List<Map<String, Object>> initPurchaseOrder();

    List<Map<String, Object>> initPurchaseGoods();


}
