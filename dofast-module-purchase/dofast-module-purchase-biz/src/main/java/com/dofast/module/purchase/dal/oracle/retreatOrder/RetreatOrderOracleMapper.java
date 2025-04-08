package com.dofast.module.purchase.dal.oracle.retreatOrder;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface RetreatOrderOracleMapper {


    List<Map<String, Object>> initRetreatOrder(String erpCode);

    List<Map<String, Object>> initRetreatGoods(String erpCode);


}
