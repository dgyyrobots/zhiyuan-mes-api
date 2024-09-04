package com.dofast.module.mes.controller.admin.userworkstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 用户工作站绑定关系 Excel VO
 *
 * @author 惠智造
 */
@Data
public class UserWorkstationExcelVO {

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名")
    private String userName;

    @ExcelProperty("名称")
    private Long nickName;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("工作站编号")
    private String workstationCode;

    @ExcelProperty("工作站名称")
    private String workstationName;

    @ExcelProperty("操作时间")
    private LocalDateTime operationTime;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("预留字段1")
    private String attr1;

    @ExcelProperty("预留字段2")
    private String attr2;

    @ExcelProperty("预留字段3")
    private Integer attr3;

    @ExcelProperty("预留字段4")
    private Integer attr4;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("记录ID")
    private Long id;

}
