package com.dofast.module.mes.service.mdunitmeasure;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.dal.oracle.mdunitmeasure.MdUnitMeasureOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class MdUnitMeasureOracleServiceImpl implements MdUnitMeasureOracleService{

    @Resource
    public MdUnitMeasureOracleMapper mdUnitMeasureOracleMapper;

    /**
     * 初始化单位信息
     * @return
     */
    @Override
    public List<Map<String, Object>> initUnitMeasure(){
        // 获取单位信息
       return mdUnitMeasureOracleMapper.initMdUnitMeasure();
    }

    /**
     * 初始化单位换算信息
     * @return
     */
    @Override
    public List<Map<String, Object>> initUnitConverse(){
        // 获取单位信息
        return mdUnitMeasureOracleMapper.initUnitConverse();
    }

}
