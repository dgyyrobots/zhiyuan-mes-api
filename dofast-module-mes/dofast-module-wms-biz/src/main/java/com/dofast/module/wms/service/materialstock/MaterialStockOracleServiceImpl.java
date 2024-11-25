package com.dofast.module.wms.service.materialstock;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.wms.dal.oracle.materialstock.MaterialStockOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class MaterialStockOracleServiceImpl implements MaterialStockOracleService{

    @Resource
    private MaterialStockOracleMapper materialStockOracleMapper;

    @Override
    public List<Map<String, Object>> initMaterialStock(){
        return materialStockOracleMapper.initMaterialStock();
    }

}
