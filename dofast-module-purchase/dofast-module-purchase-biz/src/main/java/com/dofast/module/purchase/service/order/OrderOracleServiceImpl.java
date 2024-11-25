package com.dofast.module.purchase.service.order;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.purchase.dal.oracle.order.PurchaseOrderOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class OrderOracleServiceImpl implements OrderOracleService {

    @Resource
    private PurchaseOrderOracleMapper purchaseOrderOracleMapper;

    @Override
    public List<Map<String, Object>> initPurchaseOrder() {
        return purchaseOrderOracleMapper.initPurchaseOrder();
    }

    @Override
    public List<Map<String, Object>> initPurchaseGoods() {
        return purchaseOrderOracleMapper.initPurchaseGoods();
    }

}
