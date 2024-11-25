package com.dofast.module.pro.dal.oracle.route;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface RouteOracleMapper {

    List<Map<String, Object>> initRoute();


    List<Map<String, Object>> initRouteBindProcess();

    List<Map<String, Object>> initChangeWorkOrderRouteBindProcess(String workOrderNo);
}
