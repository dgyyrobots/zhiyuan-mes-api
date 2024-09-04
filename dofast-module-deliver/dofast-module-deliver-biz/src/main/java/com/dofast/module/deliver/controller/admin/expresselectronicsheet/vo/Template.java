package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template implements Serializable {
    @Schema(description = "模板ID")
    private Long id;
    @Schema(description = "模板大小",example = "100*180")
    private String size;
}