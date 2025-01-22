package com.dofast.module.system.service.user;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.system.dal.oracle.user.AdminUserOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class AdminUserOracleServiceImpl implements AdminUserOracleService {

    @Resource
    private AdminUserOracleMapper adminUserOracleMapper;

    @Override
    public List<Map<String, Object>> initUser() {
        String erpCode = Constant.ERP_PROD_DODE;
        return adminUserOracleMapper.initUser(erpCode);
    }

}
