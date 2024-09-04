package com.dofast.module.pro.api.WorkrecordApi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WorkrecordDTO {

    /**
     * 记录ID
     */
    private Long recordId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 名称
     */
    private Long nickName;

    /**
     * 工作站ID
     */
    private Long workstationId;

    /**
     * 工作站编号
     */
    private String workstationCode;

    /**
     * 工作站名称
     */
    private String workstationName;

    /**
     * 操作类型
     */
    private String operationFlag;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

    /**
     * 备注
     */
    private String remark;
}