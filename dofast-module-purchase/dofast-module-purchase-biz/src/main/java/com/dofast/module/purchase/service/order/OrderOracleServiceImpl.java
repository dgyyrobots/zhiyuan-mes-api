package com.dofast.module.purchase.service.order;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;
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
        String erpCode = Constant.ERP_PROD_DODE;
        return purchaseOrderOracleMapper.initPurchaseOrder(erpCode);
    }

    @Override
    public List<Map<String, Object>> initPurchaseGoods() {
        String erpCode = Constant.ERP_PROD_DODE;
        return purchaseOrderOracleMapper.initPurchaseGoods(erpCode);
    }

    @Override
    public  Map<String,Object> getReceiveSeq(GoodsDO goods){
        String erpCode = Constant.ERP_PROD_DODE;
        return purchaseOrderOracleMapper.getReceiveSeq(erpCode, goods);
    }

}
