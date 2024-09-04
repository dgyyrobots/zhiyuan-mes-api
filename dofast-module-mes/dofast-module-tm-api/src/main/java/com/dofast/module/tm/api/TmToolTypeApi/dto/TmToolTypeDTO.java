package com.dofast.module.tm.api.TmToolTypeApi.dto;

import lombok.Data;

@Data
public class TmToolTypeDTO {
    /**
     * 工装夹具类型ID
     */
    private Long id;
    /**
     * 类型编码
     */
    private String toolTypeCode;
    /**
     * 类型名称
     */
    private String toolTypeName;
    /**
     * 是否编码管理
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private String codeFlag;
    /**
     * 保养维护类型
     *
     * 枚举 {@link TODO tm-fix-type 对应的类}
     */
    private String maintenType;
    /**
     * 保养周期
     */
    private Integer maintenPeriod;
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
