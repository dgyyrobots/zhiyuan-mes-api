package com.dofast.module.pro.service.workorderbom;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.dal.oracle.workorderbom.WorkorderBomOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class WorkorderBomOracleServiceImpl implements WorkorderBomOracleService{

    @Resource
    public WorkorderBomOracleMapper  workorderBomOracleMapper;

    @Override
    public List<Map<String, Object>> initWorkorderBom() {
        String erpCode = Constant.ERP_PROD_DODE;
        return workorderBomOracleMapper.initWorkorderBomInfo(erpCode);
    }

    @Override
    public List<Map<String, Object>> initChangeWorkorderBom(String workorderNo) {
        String erpCode = Constant.ERP_PROD_DODE;
        return workorderBomOracleMapper.initChangeWorkorderBomInfo(workorderNo, erpCode);
    }


}
