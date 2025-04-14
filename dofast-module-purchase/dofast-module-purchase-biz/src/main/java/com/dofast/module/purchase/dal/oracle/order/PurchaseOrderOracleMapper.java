package com.dofast.module.purchase.dal.oracle.order;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface PurchaseOrderOracleMapper {

    List<Map<String, Object>> initPurchaseOrder(String erpCode);

    List<Map<String, Object>> initPurchaseGoods(String erpCode);

    Map<String,Object> getReceiveSeq(@Param("erpCode")String erpCode, @Param("goodsDO") GoodsDO goodsDO);

}
