package com.dofast.module.mes.controller.admin.freezeinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品冻结信息 Excel VO
 *
 * @author 惠智造
 */
@Data
public class FreezeInfoExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("产品物料编码")
    private String itemCode;

    @ExcelProperty("产品物料名称")
    private String itemName;

    @ExcelProperty("产品物料数量")
    private Long itenQty;

    @ExcelProperty("状态(1-解冻, 2-冻结)")
    private Long state;

    @ExcelProperty("仓库编号")
    private String whCode;

    @ExcelProperty("库位")
    private String storageCode;

    @ExcelProperty("冻结原因")
    private String freezeMemo;

    @ExcelProperty("冻结人员")
    private String freezer;

    @ExcelProperty("冻结时间")
    private LocalDateTime freezeTime;

    @ExcelProperty("解冻人员")
    private String thawPerson;

    @ExcelProperty("解冻原因")
    private String thawMemo;

    @ExcelProperty("解冻时间")
    private LocalDateTime thawTime;

    @ExcelProperty("产品物料SN")
    private String itenSn;

}
