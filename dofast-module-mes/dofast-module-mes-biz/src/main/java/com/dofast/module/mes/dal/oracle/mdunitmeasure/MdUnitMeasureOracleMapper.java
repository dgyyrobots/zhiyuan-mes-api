package com.dofast.module.mes.dal.oracle.mdunitmeasure;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface MdUnitMeasureOracleMapper {

    /**
     * 初始化单位信息
     * @return
     */
    List<Map<String, Object>> initMdUnitMeasure(String erpCode);

    /**
     * 初始化单位换算信息
     * @return
     */
    List<Map<String, Object>> initUnitConverse(String erpCode);
}
