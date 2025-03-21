package com.dofast.module.report.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 * Report 错误码枚举类
 *
 * system 系统，使用 1-003-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== AUTH 模块 1003000000 ==========
    ErrorCode GO_VIEW_PROJECT_NOT_EXISTS = new ErrorCode(1003000000, "GoView 项目不存在");

    ErrorCode PRINT_LOG_NOT_EXISTS = new ErrorCode(1003000001, "打印日志不存在");

    ErrorCode GO_VIEW_CODE_NOT_EXISTS = new ErrorCode(1003000002, "GoView登录随机码不存在");

    ErrorCode GO_VIEW_CODE_EXPIRED = new ErrorCode(1003000003, "GoView登录随机码已过期");

    ErrorCode GO_VIEW_TOKEN_EXISTS = new ErrorCode(1003000003, "PDA登录Token不存在");


}
