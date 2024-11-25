package com.dofast.module.pro.service.process;

import java.util.List;
import java.util.Map;

public interface ProcessOracleService {

    /**
     * 从ERP中获取工序信息
     */
    List<Map<String, Object>> initProcess();

}
