package com.dofast.module.pro.service.workorder;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.pro.dal.oracle.workorder.WorkorderOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
@Validated
@DS("oracle")
public class WorkorderOracleServiceImpl implements WorkorderOracleService {

    @Resource
    private WorkorderOracleMapper workorderOracleMapper;

    /**
     * 初始化工单信息
     */
    @Override
    public List<Map<String , Object>> initWorkorderInfo(){
        return workorderOracleMapper.initWorkorder();
    }

    /**
     * 初始化变更工单信息
     */
    @Override
    public List<Map<String , Object>> initChangeWorkorderInfo(){
        return workorderOracleMapper.initChangeWorkorder();
    }


}
