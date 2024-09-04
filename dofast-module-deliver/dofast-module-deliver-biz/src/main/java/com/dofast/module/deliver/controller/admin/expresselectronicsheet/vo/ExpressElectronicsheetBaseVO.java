package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 电子面单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpressElectronicsheetBaseVO implements Serializable {

    @Schema(description = "配送快递名字", example = "芋艿")
    private String companyName;

    @Schema(description = "类型", example = "1")
    private String type;

    @Schema(description = "配送信息")
    private String info;

    @Schema(description = "配送编码(快递鸟)")
    private String kdnCode;

    @Schema(description = "信息模板")
    private Template template;

    @Schema(description = "快递配置信息")
    private Config config;


}



