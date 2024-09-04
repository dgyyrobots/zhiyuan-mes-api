package com.dofast.module.mes.controller.admin.mdworkstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 工作站 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdWorkstationExcelVO {

    @ExcelProperty("工作站ID")
    private Long id;

    @ExcelProperty("工作站编码")
    private String workstationCode;

    @ExcelProperty("工作站名称")
    private String workstationName;

    @ExcelProperty("工作站地点")
    private String workstationAddress;

    @ExcelProperty("所在车间ID")
    private Long workshopId;

    @ExcelProperty("所在车间编码")
    private String workshopCode;

    @ExcelProperty("所在车间名称")
    private String workshopName;

    @ExcelProperty("工序ID")
    private Long processId;

    @ExcelProperty("工序编码")
    private String processCode;

    @ExcelProperty("工序名称")
    private String processName;

    @ExcelProperty("线边库ID")
    private Long warehouseId;

    @ExcelProperty("线边库编码")
    private String warehouseCode;

    @ExcelProperty("线边库名称")
    private String warehouseName;

    @ExcelProperty("库区ID")
    private Long locationId;

    @ExcelProperty("库区编码")
    private String locationCode;

    @ExcelProperty("库区名称")
    private String locationName;

    @ExcelProperty("库位ID")
    private Long areaId;

    @ExcelProperty("库位编码")
    private String areaCode;

    @ExcelProperty("库位名称")
    private String areaName;

    @ExcelProperty("是否启用")
    private String enableFlag;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("预留字段1")
    private String attr1;

    @ExcelProperty("预留字段2")
    private String attr2;

    @ExcelProperty("预留字段3")
    private Integer attr3;

    @ExcelProperty("预留字段4")
    private Integer attr4;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
