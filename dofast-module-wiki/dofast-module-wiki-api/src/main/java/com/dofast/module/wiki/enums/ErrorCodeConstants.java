package com.dofast.module.wiki.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 */
public interface ErrorCodeConstants {

    // === 首页的分类 1030001000 ===
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode(1030001000, "首页的分类不存在");

    // === 课件文件的保存地址 1030002000 ===
    ErrorCode COURSEWARE_FILE_NOT_EXISTS = new ErrorCode(1030002000, "课件文件的保存地址不存在");

    // === 讲师的信息	 1030003000 ===
    ErrorCode LECTURER_NOT_EXISTS = new ErrorCode(1030003000, "讲师的信息	不存在");

    // === 首页知识列表的信息	 1030004000 ===
    ErrorCode COURSEWAVE_NOT_EXISTS = new ErrorCode(1030004000, "首页知识列表的信息	不存在");

}
