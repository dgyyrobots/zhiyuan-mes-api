package com.dofast.module.mes.api.autocode;

import com.dofast.framework.common.pojo.PageResult;

import java.util.Collection;
import java.util.List;

public interface AutoCodeApi {

    String genSerialCode(String ruleCode,String inputCharacter);
}
