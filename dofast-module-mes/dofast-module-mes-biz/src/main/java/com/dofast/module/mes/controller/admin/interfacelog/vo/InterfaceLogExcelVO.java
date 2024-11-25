package com.dofast.module.mes.controller.admin.interfacelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 接口操作日志 Excel VO
 *
 * @author 惠智造
 */
@Data
public class InterfaceLogExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("接口名称")
    private String interfaceName;

    @ExcelProperty("发起方")
    private String requester;

    @ExcelProperty("接收方")
    private String receiver;

    @ExcelProperty("请求类型(POST/GET/PUT)")
    private String requestType;

    @ExcelProperty("请求报文")
    private String requestMap;

    @ExcelProperty("接收报文")
    private String resultMap;

}
