package com.dofast.module.mes.dal.oracle.mditem;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface MdItemOracleMapper {

    /**
     * 初始化ERP所有物料信息, 届时到Mysql中比对是否存在
     * @return
     */
    List<Map<String, Object>> initItemInfo();


}
