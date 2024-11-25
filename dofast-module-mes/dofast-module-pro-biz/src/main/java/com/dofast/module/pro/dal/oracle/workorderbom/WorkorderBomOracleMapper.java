package com.dofast.module.pro.dal.oracle.workorderbom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface WorkorderBomOracleMapper {

    /**
     * 从ERP获取工单BOM信息
     */
    List<Map<String , Object>> initWorkorderBomInfo();

    /**
     * 从ERP获取变更工单BOM信息
     */
    List<Map<String , Object>> initChangeWorkorderBomInfo(String workorderNo);


}
