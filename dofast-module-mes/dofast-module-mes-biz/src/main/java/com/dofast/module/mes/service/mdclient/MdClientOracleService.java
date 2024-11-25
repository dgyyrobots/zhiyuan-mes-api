package com.dofast.module.mes.service.mdclient;

import java.util.List;
import java.util.Map;

public interface MdClientOracleService {

    /**
     * 从ERP获取客户与供应商信息
     * 部分厂家即是供应商也是客户, 故供应商与客户放在一个Sql中查询
     *
     * @return
     */
    List<Map<String, Object>> initClient(String type);

}
