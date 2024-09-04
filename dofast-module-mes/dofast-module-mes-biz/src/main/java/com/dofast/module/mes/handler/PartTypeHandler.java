package com.dofast.module.mes.handler;

import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import com.dofast.module.mes.enums.PartTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartTypeHandler {
    @Autowired
    List<PartTypeTemplate> partTypeTemplates;

    public String choiceExecute(AutoCodePartDO sysAutoCodePart){
        String partType = sysAutoCodePart.getPartType();
        return partTypeTemplates.get(PartTypeEnum.getByCode(partType).getBeanIndex()).partHandle(sysAutoCodePart);
    }
}
