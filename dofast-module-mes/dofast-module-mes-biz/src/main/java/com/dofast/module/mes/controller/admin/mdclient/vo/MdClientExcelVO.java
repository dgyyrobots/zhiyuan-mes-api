package com.dofast.module.mes.controller.admin.mdclient.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 客户 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdClientExcelVO {

    @ExcelProperty("客户ID")
    private Long id;

    @ExcelProperty("客户编码")
    private String clientCode;

    @ExcelProperty("客户名称")
    private String clientName;

    @ExcelProperty("客户简称")
    private String clientNick;

    @ExcelProperty("客户英文名称")
    private String clientEn;

    @ExcelProperty("客户简介")
    private String clientDes;

    @ExcelProperty("客户LOGO地址")
    private String clientLogo;

    @ExcelProperty("客户类型")
    private String clientType;

    @ExcelProperty("客户地址")
    private String address;

    @ExcelProperty("客户官网地址")
    private String website;

    @ExcelProperty("客户邮箱地址")
    private String email;

    @ExcelProperty("客户电话")
    private String tel;

    @ExcelProperty("联系人1")
    private String contact1;

    @ExcelProperty("联系人1-电话")
    private String contact1Tel;

    @ExcelProperty("联系人1-邮箱")
    private String contact1Email;

    @ExcelProperty("联系人2")
    private String contact2;

    @ExcelProperty("联系人2-电话")
    private String contact2Tel;

    @ExcelProperty("联系人2-邮箱")
    private String contact2Email;

    @ExcelProperty("统一社会信用代码")
    private String creditCode;

    @ExcelProperty("是否启用")
    private String enableFlag;

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

}
