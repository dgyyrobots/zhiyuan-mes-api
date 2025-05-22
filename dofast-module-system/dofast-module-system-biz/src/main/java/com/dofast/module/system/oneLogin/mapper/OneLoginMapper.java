package com.dofast.module.system.oneLogin.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dofast.module.system.dal.dataobject.dj002.Dj002DO;
import com.dofast.module.system.oneLogin.domain.InitLoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Mapper
public interface OneLoginMapper {

    List<InitLoginVo> initOneLogin(@Param("tenantId") String tenantId, @Param("userId") String userId);

    void register(String userId, String userName, String password, String sysId, String tenantId);

    String getLoginAddress(@Param("tenantId") String tenantId, @Param("net") String net, @Param("sysName") String sysName);

    String getTenantNameByTenantId(String tenantId);

    @InterceptorIgnore(tenantLine = "true")
    void updateDj002Net(Dj002DO updateObj);

}
