package com.dofast.module.cmms.service.dvmachinery;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.cmms.dal.mysql.dvmachinery.DvMachineryMapper;
import com.dofast.module.cmms.dal.oracle.dvmachinery.DvMachineryOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated

public class DvMachineryOracleServiceImpl implements DvMachineryOracleService {

    @Resource
    private DvMachineryOracleMapper dvMachineryOracleMapper;

    /**
     * 初始化设备列表
     * @return
     */
    @DS("oracle")
    public List<Map<String, Object>> initMachineryList(){
        return dvMachineryOracleMapper.initMachineList();
    }

}
