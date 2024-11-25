package com.dofast.module.mes.service.mdunitmeasure;

import java.util.List;
import java.util.Map;

public interface MdUnitMeasureOracleService {

    /**
     * 初始化单位信息
     * @return
     */
    List<Map<String, Object>> initUnitMeasure();

    /**
     * 初始化单位换算信息
     * @return
     */
    List<Map<String, Object>> initUnitConverse();



}
