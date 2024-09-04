package com.dofast.module.pro.api.ProcessApi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessDTO {
    private Long id;

    private String processCode;

    private String processName;

    private String attention;

    private String enableFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;

}
