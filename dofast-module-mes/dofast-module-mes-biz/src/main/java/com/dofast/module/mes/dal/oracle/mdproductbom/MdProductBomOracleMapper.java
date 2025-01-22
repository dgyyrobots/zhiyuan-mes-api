package com.dofast.module.mes.dal.oracle.mdproductbom;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface MdProductBomOracleMapper {

    /**
     * 基于ERP获取物料BOM信息
     */
    List<Map<String , Object>> initProductBomInfo(String erpCode);

}
