package com.dofast.module.pro.api.RouteApi;

import lombok.Data;

@Data
public class RouteDTO {
    /**
     * 工艺路线ID
     */
    private Long id;
    /**
     * 工艺路线编号
     */
    private String routeCode;
    /**
     * 工艺路线名称
     */
    private String routeName;
    /**
     * 工艺路线说明
     */
    private String routeDesc;
    /**
     * 是否启用
     */
    private String enableFlag;
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
