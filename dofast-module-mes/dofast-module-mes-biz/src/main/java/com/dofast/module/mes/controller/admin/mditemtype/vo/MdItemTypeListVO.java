package com.dofast.module.mes.controller.admin.mditemtype.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MdItemTypeListVO {
    private Long id;

    private String itemTypeCode;

    private String itemTypeName;

    private Long parentTypeId;

    private String ancestors;

    private String itemOrProduct;

    private Integer orderNum;

    private String enableFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;
}
