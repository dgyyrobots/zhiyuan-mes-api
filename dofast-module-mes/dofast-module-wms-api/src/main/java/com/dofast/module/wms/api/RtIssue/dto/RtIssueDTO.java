package com.dofast.module.wms.api.RtIssue.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


@Data
public class RtIssueDTO {

    private String rtCode;

    private String rtName;

    private Long workorderId;

    private String workorderCode;

    private Long warehouseId;

    private String warehouseCode;

    private String warehouseName;

    private Long locationId;

    private String locationCode;

    private String locationName;

    private Long areaId;

    private String areaCode;

    private String areaName;

    private LocalDateTime rtDate;

    private String status;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private String updator;

    private String taskCode;

}
