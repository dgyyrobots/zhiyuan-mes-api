package com.dofast.module.system.service.dept;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.system.dal.oracle.dept.DeptOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class DeptOracleServiceImpl implements DeptOracleService {

    @Resource
    private DeptOracleMapper deptOracleMapper;

    @Override
    public List<Map<String, Object>> initDept() {
        return deptOracleMapper.initDept();
    }
}
