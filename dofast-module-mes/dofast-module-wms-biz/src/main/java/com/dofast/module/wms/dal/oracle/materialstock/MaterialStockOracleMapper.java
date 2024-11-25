package com.dofast.module.wms.dal.oracle.materialstock;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface MaterialStockOracleMapper {

    List<Map<String, Object>> initMaterialStock();

}
