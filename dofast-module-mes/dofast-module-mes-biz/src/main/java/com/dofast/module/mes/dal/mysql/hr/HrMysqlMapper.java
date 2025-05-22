package com.dofast.module.mes.dal.mysql.hr;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dofast.module.mes.dal.dataobject.oracle.oracle.HrDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HrMysqlMapper {


    void addtbhr(HrDTO hrDTO);

    String getTenantId(String judian);

    @InterceptorIgnore(tenantLine = "true")
    int findIfUserName(String userName);

    void insertToSystemUsers(HrDTO hrDTO);

    void insertToDj001(HrDTO hrDTO);

    @InterceptorIgnore(tenantLine = "true")
    List<String> findToDj002(String tenantId);

}
