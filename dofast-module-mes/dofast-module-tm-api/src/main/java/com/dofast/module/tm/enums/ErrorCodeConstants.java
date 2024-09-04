package com.dofast.module.tm.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 */
public interface ErrorCodeConstants {

    // === 工装夹具清单 1050000000 ===
    ErrorCode TOOL_NOT_EXISTS = new ErrorCode(1050001000, "工装夹具清单不存在");
    
    // === 工装夹具类型 1050002000 ===
    ErrorCode TOOL_TYPE_NOT_EXISTS = new ErrorCode(1050002000, "工装夹具类型不存在");
    

}
