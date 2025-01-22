package com.dofast.module.mes.dal.oracle.mdclient;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface MdClientOracleMapper {

    /**
     * Typ:
     *  1: 客户
     *  2: 供应商
     *  3: 二者皆是
     * @param type
     * @return
     */
    List<Map<String, Object>> initClient(@Param("type") String type,@Param("erpCode") String erpCode);

}
