package com.dofast.module.pro.service.route;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.pro.dal.oracle.route.RouteOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class RouteOracleServiceImpl implements RouteOracleService {

    @Resource
    private RouteOracleMapper routeOracleMapper;

    /**
     * 初始化工艺信息
     * @return
     */
    @Override
    public List<Map<String, Object>> initRoute(){
        return routeOracleMapper.initRoute();
    }


    /**
     * 初始化工序工艺绑定信息
     * @return
     */
    @Override
    public List<Map<String, Object>> initRouteBindProcess(){
        return routeOracleMapper.initRouteBindProcess();
    }

    /**
     * 初始化变更工单工序工艺绑定信息
     * @return
     */
    @Override
    public List<Map<String, Object>> initChangeWorkOrderRouteBindProcess(String workOrderNo){
        return routeOracleMapper.initChangeWorkOrderRouteBindProcess(workOrderNo);
    }



}
