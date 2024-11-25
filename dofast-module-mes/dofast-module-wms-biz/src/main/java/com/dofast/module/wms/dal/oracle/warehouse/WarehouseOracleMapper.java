package com.dofast.module.wms.dal.oracle.warehouse;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface WarehouseOracleMapper {

    List<Map<String, Object>> initLocationInfo();

    List<Map<String, Object>> initAreaInfo();

}
