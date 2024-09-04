package com.dofast.module.qms.controller.admin.templateindex.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 检测模板-检测项 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class TemplateIndexListVO {

    private Long id;

    private Long templateId;

    private Long indexId;

    private String indexCode;

    private String indexName;

    private String indexType;

    private String qcTool;

    private String checkMethod;

    private BigDecimal standerVal;

    private String unitOfMeasure;

    private BigDecimal thresholdMax;

    private BigDecimal thresholdMin;

    private String docUrl;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;

}
