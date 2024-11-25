package com.dofast.module.wms.service.warehouse;

import java.util.List;
import java.util.Map;


public interface WarehouseOracleService {

    // 初始化库位信息
    List<Map<String, Object> > initAreaInfo();

    // 初始化线边仓 , 库区 , 库位信息
    List<Map<String, Object> > initLocationInfo();

}
