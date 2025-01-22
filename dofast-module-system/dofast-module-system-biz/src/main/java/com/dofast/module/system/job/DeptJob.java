package com.dofast.module.system.job;

import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.module.system.convert.dept.DeptConvert;
import com.dofast.module.system.dal.dataobject.dept.DeptDO;
import com.dofast.module.system.service.dept.DeptOracleService;
import com.dofast.module.system.service.dept.DeptService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DeptJob implements JobHandler {

    @Resource
    private DeptOracleService deptOracleService;

    @Resource
    private DeptService deptService;

    @Override
    public String execute(String param) throws Exception {
        // 经确认后, 用户表保存ERP编码与部门编码用于ERP数据对接
        // MES的部门独立管控
        //initDept
        /*List<Map<String, Object>> deptList = deptOracleService.initDept();
        // 先校验当前的集团 -> 子公司 -> 部门
        deptList.forEach(dept -> {
            String erpCode = (String) dept.get("ERP_CODE"); // ERP系统中的部门ID
            String Name = (String) dept.get("NAME"); // ERP系统中的部门名称
            String erpParentCode = (String) dept.get("ERP_PARENT_CODE"); // ERP系统中的上级部门ID
            if (erpCode == "ALL") {
                // 开始追加集团
                // 校验当前集团是否存在
                DeptDO deptDO = Optional.ofNullable(deptService.getDeptByErpCode("ALL")).orElse(null);
                if (deptDO == null) {
                    deptDO = new DeptDO();
                    deptDO.setErpCode(erpCode);
                    deptDO.setName(Name);
                    deptDO.setStatus(0);
                    deptDO.setParentId(0L); // 集团是最大的父级
                    // 追加信息
                    deptService.createDept(DeptConvert.INSTANCE.convert01(deptDO));
                }else{

                }
            }
        });*/
        return "";
    }
}
