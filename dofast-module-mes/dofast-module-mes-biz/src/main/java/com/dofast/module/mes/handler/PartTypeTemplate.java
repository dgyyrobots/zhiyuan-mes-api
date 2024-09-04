package com.dofast.module.mes.handler;

import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;

public interface PartTypeTemplate {
    /**
     * 分段的处理规则
     * @param sysAutoCodePart
     * @return
     */
    String partHandle(AutoCodePartDO sysAutoCodePart);
}
