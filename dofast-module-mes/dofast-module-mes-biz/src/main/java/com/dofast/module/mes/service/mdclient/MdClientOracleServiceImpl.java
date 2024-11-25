package com.dofast.module.mes.service.mdclient;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.dal.oracle.mdclient.MdClientOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class MdClientOracleServiceImpl implements MdClientOracleService {


    @Resource
    public MdClientOracleMapper mdClientOracleMapper;

    @Override
    public List<Map<String, Object>> initClient(String type) {
        return mdClientOracleMapper.initClient(type);
    }

}
