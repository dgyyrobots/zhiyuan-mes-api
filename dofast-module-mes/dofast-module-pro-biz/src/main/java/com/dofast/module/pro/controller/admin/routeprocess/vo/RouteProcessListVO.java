package com.dofast.module.pro.controller.admin.routeprocess.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RouteProcessListVO {
    private Long id;

    private Long routeId;

    private Long processId;

    private String processCode;

    private String processName;

    private Integer orderNum;

    private Long nextProcessId;

    private String nextProcessCode;

    private String nextProcessName;

    private String linkType;

    private Integer defaultPreTime;

    private Integer defaultSufTime;

    private String colorCode;

    private String keyFlag;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private LocalDateTime createTime;

}
