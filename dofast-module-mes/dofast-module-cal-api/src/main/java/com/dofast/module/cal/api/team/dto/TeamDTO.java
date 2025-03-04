package com.dofast.module.cal.api.team.dto;

import lombok.Data;

@Data
public class TeamDTO {

    /**
     * 班组ID
     */
    private Long id;
    /**
     * 班组编号
     */
    private String teamCode;
    /**
     * 班组名称
     */
    private String teamName;
    /**
     * 班组类型
     */
    private String calendarType;
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

    /**
     * 负责人Id
     */
    private Long principalId;
    /**
     * 负责人名称
     */
    private String principalName;
    /**
     * 班组人数
     */
    private Long personCount;

    /**
     * 设备名称
     */
    private String machineryName;

    /**
     * 设备编码
     */
    private String machineryCode;
    /**
     * 设备ID
     */
    private Long machineryId;
}
