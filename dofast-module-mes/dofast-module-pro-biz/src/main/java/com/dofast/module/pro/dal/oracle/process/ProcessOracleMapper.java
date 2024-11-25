package com.dofast.module.pro.dal.oracle.process;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface ProcessOracleMapper {

    /**
     * 从ERP中获取工序信息
     */
    List<Map<String, Object>> initProcess();

}
