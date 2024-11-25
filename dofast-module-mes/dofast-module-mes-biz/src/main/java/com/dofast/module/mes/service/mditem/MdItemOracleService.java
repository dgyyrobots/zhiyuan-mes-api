package com.dofast.module.mes.service.mditem;

import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;

import java.util.List;
import java.util.Map;

public interface MdItemOracleService {

    /**
     * 从ERP获取物料信息
     * @return
     */
    List<Map<String, Object>> initMdItemInfo();

}
