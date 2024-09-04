package com.dofast.module.mes.handler;

import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Order(1)
public class PartTypeNowDateHandler implements PartTypeTemplate{
    @Override
    public String partHandle(AutoCodePartDO sysAutoCodePart) {
        String formatDate = sysAutoCodePart.getDateFormat();
        return DateTimeFormatter.ofPattern(formatDate).format(LocalDateTime.now());
    }
}
