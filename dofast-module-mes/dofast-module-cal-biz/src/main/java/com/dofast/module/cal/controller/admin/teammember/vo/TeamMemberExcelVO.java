package com.dofast.module.cal.controller.admin.teammember.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 班组成员 Excel VO
 *
 * @author 惠智造
 */
@Data
public class TeamMemberExcelVO {

    @ExcelProperty("班组成员ID")
    private Long id;

    @ExcelProperty("班组ID")
    private Long teamId;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名")
    private String userName;

    @ExcelProperty("用户昵称")
    private String nickName;

    @ExcelProperty("电话")
    private String tel;

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

    @Schema(description = "班次")
    private String shiftInfo;

}
