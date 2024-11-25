package com.dofast.module.pro.service.process;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.pro.dal.oracle.process.ProcessOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class ProcessOracleServiceImpl implements ProcessOracleService{

    @Resource
    private ProcessOracleMapper  processOracleMapper;

    /**
     * 从ERP中获取工序信息
     */
    @Override
    public List<Map<String, Object>> initProcess(){
        return processOracleMapper.initProcess();
    }

}
