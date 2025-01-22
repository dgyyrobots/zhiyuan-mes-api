package com.dofast.module.mes.dal.oracle.mdworkshop;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface MdWorkshopOracleMapper {

    /**
     * 从ERP获取车间信息
     * @return
     */
    List<Map<String, Object>> initWorkShop(String erpCode);


}
