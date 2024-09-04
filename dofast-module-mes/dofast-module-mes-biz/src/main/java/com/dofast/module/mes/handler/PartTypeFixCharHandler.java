package com.dofast.module.mes.handler;

import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class PartTypeFixCharHandler implements PartTypeTemplate{
    @Override
    public String partHandle(AutoCodePartDO sysAutoCodePart) {
        return sysAutoCodePart.getFixCharacter();
    }
}
