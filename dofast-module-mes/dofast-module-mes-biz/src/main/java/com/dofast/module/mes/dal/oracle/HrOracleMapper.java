package com.dofast.module.mes.dal.oracle;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dofast.module.mes.dal.dataobject.oracle.oracle.HrDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface HrOracleMapper {

    List<HrDTO> selectHrList();


}
