package com.dofast.module.pro.controller.pad.protask.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProTaskFeedbackReqVO {
    @NotNull(message = "任务ID不能为空")
    private Long taskId;
    @NotNull(message = "报工数量不能为空")
    private Double quantityFeedback;
    @NotNull(message = "不良品数量不能为空")
    private Double quantityUnquanlified;
    @NotNull(message = "合格品品数量不能为空")
    private Double quantityQualified;
    @NotNull(message = "报工人员")
    private String userName;
}
