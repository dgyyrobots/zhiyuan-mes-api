package com.dofast.module.purchase.dal.oracle.order;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface PurchaseOrderOracleMapper {

    List<Map<String, Object>> initPurchaseOrder(String erpCode);

    List<Map<String, Object>> initPurchaseGoods(String erpCode);


}
