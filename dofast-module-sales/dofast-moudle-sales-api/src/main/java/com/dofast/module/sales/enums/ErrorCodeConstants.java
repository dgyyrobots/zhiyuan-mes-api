package com.dofast.module.sales.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 * Report 错误码枚举类
 *

 * system 系统，使用 1-003-000-000 段
 */
public interface ErrorCodeConstants {

    // === AUTH 模块 1003000000 ===
    ErrorCode ORDER_AFTER_NOT_EXISTS = new ErrorCode(100010001, "售后流程表单不存在");
}
