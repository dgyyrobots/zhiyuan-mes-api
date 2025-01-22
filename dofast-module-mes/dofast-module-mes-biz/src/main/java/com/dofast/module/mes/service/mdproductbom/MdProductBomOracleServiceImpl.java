package com.dofast.module.mes.service.mdproductbom;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.dal.dataobject.mdproductbom.MdProductBomDO;
import com.dofast.module.mes.dal.oracle.mdproductbom.MdProductBomOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class MdProductBomOracleServiceImpl implements MdProductBomOracleService{
    @Resource
    private MdProductBomOracleMapper mdProductBomOracleMapper;

    @Override
    public List<Map<String, Object>> initProductBomInfo() {
        String erpCode = Constant.ERP_PROD_DODE;
        return mdProductBomOracleMapper.initProductBomInfo(erpCode);
    }
}
