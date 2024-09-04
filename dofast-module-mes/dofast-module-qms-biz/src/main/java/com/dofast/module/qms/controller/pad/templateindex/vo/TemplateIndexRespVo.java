package com.dofast.module.qms.controller.pad.templateindex.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TemplateIndexRespVo {
    /**
     * 检测模板ID
     */
    private Long templateId;
    /**
     * 检测项ID
     */
    private Long indexId;
    /**
     * 检测项编码
     */
    private String indexCode;
    /**
     * 检测项名称
     */
    private String indexName;
    /**
     * 检测项类型
     */
    private String indexType;
    /**
     * 检测工具
     */
    private String qcTool;
    /**
     * 检测要求
     */
    private String checkMethod;
    /**
     * 标准值
     */
    private BigDecimal standerVal;
    /**
     * 单位
     */
    private String unitOfMeasure;
    /**
     * 误差上限
     */
    private BigDecimal thresholdMax;
    /**
     * 误差下限
     */
    private BigDecimal thresholdMin;
    /**
     * 说明图
     */
    private String docUrl;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预留字段1
     */
    private String attr1;
    /**
     * 预留字段2
     */
    private String attr2;
    /**
     * 预留字段3
     */
    private Integer attr3;
    /**
     * 预留字段4
     */
    private Integer attr4;
}
