package com.dofast.module.mes.service.mdproductbom;

import java.util.List;
import java.util.Map;
public interface MdProductBomOracleService {

    /**
     * 初始化产品Bom信息
     * @return
     */
    List<Map<String, Object>> initProductBomInfo();
}
