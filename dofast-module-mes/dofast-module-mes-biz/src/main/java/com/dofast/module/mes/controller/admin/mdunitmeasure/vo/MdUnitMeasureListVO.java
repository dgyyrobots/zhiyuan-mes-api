package com.dofast.module.mes.controller.admin.mdunitmeasure.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MdUnitMeasureListVO {
    private Long id;

    private String measureCode;

    private String measureName;

    private String primaryFlag;

    private Long primaryId;

    private BigDecimal changeRate;

    private String enableFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;
}
