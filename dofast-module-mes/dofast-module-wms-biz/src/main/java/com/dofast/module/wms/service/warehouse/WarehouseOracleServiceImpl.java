package com.dofast.module.wms.service.warehouse;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.wms.dal.oracle.warehouse.WarehouseOracleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class WarehouseOracleServiceImpl implements WarehouseOracleService {

    @Autowired
    private WarehouseOracleMapper warehouseOracleMapper;

    // 初始化线边仓 , 库区 , 库位信息
    @Override
    public List<Map<String, Object>> initAreaInfo(){
        return warehouseOracleMapper.initAreaInfo();
    }

    @Override
    // 初始化线边仓, 库区 , 库位信息
    public List<Map<String, Object>> initLocationInfo(){
        return warehouseOracleMapper.initLocationInfo();
    }



}
