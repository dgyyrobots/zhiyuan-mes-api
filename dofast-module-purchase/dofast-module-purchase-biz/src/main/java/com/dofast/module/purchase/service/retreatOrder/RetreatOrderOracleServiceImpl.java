package com.dofast.module.purchase.service.retreatOrder;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.purchase.dal.oracle.retreatOrder.RetreatOrderOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class RetreatOrderOracleServiceImpl implements RetreatOrderOracleService {

    @Resource
    private RetreatOrderOracleMapper retreatOrderOracleMapper;

    @Override
    public List<Map<String, Object>> initRetreatOrder() {
        String erpCode = Constant.ERP_PROD_DODE;
        return retreatOrderOracleMapper.initRetreatOrder(erpCode);
    }

    @Override
    public List<Map<String, Object>> initRetreatGoods() {
        String erpCode = Constant.ERP_PROD_DODE;
        return  retreatOrderOracleMapper.initRetreatGoods(erpCode);
    }
}
