package com.dofast.module.mes.service.mdworkstation;

import java.util.List;
import java.util.Map;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.dal.oracle.mdworkstation.MdWorkstationOracleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DS("oracle")
public class MdWorkstationOracleServiceImpl implements MdWorkstationOracleService{

    @Resource
    private MdWorkstationOracleMapper mdWorkstationOracleMapper;

    @Override
    public List<Map<String, Object>> initWorkstation(){
        String erpCode = Constant.ERP_PROD_DODE;
        return mdWorkstationOracleMapper.initWorkstation(erpCode);
    }


}
