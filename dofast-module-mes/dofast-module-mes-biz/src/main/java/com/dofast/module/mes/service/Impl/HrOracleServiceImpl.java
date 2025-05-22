package com.dofast.module.mes.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.dal.dataobject.oracle.oracle.HrDTO;
import com.dofast.module.mes.dal.oracle.HrOracleMapper;
import com.dofast.module.mes.service.oracle.HrOracleService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

@Service
@Validated
@DS("oracle")
public class HrOracleServiceImpl implements HrOracleService {

    @Resource
    private HrOracleMapper hrOracleMapper;

    @Override
    public List<HrDTO> selectHrList() {
        List<HrDTO> list = hrOracleMapper.selectHrList();
        list.forEach(System.out::println);
        return list;
    }
}
