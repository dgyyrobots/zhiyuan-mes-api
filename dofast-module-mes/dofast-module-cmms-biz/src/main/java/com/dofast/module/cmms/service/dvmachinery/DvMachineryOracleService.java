package com.dofast.module.cmms.service.dvmachinery;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;


public interface DvMachineryOracleService {

    /**
     * 初始化设备列表
     * @return
     */
    List<Map<String, Object>> initMachineryList();

}
