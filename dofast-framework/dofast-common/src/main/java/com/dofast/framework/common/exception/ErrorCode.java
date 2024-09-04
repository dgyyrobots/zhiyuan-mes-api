package com.dofast.framework.common.exception;

import com.dofast.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.dofast.framework.common.exception.enums.ServiceErrorCodeRange;
import lombok.Data;

/**
 * 错误码对象
 *
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCodeConstants}
 * 业务异常错误码，占用 [1 000 000 000, +∞)，参见 {@link ServiceErrorCodeRange}
 *
 * TODO 错误码设计成对象的原因，为未来的 i18 国际化做准备
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    /**
     * 错误提示
     */
    private final String result;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
        this.result = null;
    }

    public ErrorCode(Integer code, String message, String result) {
        this.code = code;
        this.msg = message;
        this.result = result;
    }

}
