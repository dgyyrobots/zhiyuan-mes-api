package com.dofast.module.system.controller.admin.dj002.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 系统地址信息 Excel VO
 *
 * @author 惠智造
 */
@Data
public class Dj002ExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("公司名称")
    private String deptName;

    @ExcelProperty("系统名称")
    private String sysName;

    @ExcelProperty("内网系统访问地址")
    private String sysUrl;

    @ExcelProperty("外网系统访问地址")
    private String sysUrlNet;

    @ExcelProperty("图标地址")
    private String sysLogUrl;

    @ExcelProperty("创建时间")
    private LocalDate createTime;

    @ExcelProperty("创建者")
    private String createUser;

    @ExcelProperty("修改者")
    private String updateUser;

    @ExcelProperty("程序编号")
    private String pg;

}
