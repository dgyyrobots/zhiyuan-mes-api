package com.dofast.module.system.service.dept;

import java.util.List;
import java.util.Map;

public interface DeptOracleService {
    /**
     * 从ERP获取所有的部门信息并初始化部门表
     * @return
     */
    List<Map<String, Object>> initDept();
}
