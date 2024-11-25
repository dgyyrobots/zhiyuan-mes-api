package com.dofast.module.cmms.dal.oracle.dvmachinery;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface DvMachineryOracleMapper {
    List<Map<String, Object>> initMachineList();
}
