package com.dofast.module.pro.service.workorderbom;

import java.util.List;
import java.util.Map;
public interface WorkorderBomOracleService {

    /**
     * 从ERP获取工单BOM信息
     * @return
     */
    List<Map<String, Object>> initWorkorderBom();

    /**
     * 从ERP变更获取工单BOM信息
     * @return
     */
    List<Map<String, Object>> initChangeWorkorderBom(String workorderNo);


}
