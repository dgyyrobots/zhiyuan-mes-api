package com.dofast.module.mes.service.mdworkshop;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.dal.oracle.mdworkshop.MdWorkshopOracleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@DS("oracle")
public class MdWorkshopOracleServiceImpl implements MdWorkshopOracleService{

    @Resource
    private MdWorkshopOracleMapper mdWorkshopOracleMapper;

    @Override
    public List<Map<String, Object>> initWorkshop(){
        return mdWorkshopOracleMapper.initWorkShop();
    }

}
