package com.dofast.module.iot.controller.admin.group.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备分组 Excel VO
 *
 * @author 惠智造
 */
@Data
public class GroupExcelVO {

    @ExcelProperty("分组ID")
    private Long id;

    @ExcelProperty("分组名称")
    private String groupName;

    @ExcelProperty("分组排序")
    private Byte groupOrder;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户昵称")
    private String userName;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
