package com.dofast.module.pro.controller.admin.processdefect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 工序异常缺陷名称 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProcessDefectBaseVO {

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "缺陷名称", example = "芋艿")
    private String defectName;

}
