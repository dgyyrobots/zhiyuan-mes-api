package com.dofast.module.mes.controller.admin.mdworkstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 工作站 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdWorkstationBaseVO {

    @Schema(description = "工作站编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "工作站编码不能为空")
    private String workstationCode;

    @Schema(description = "工作站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "工作站名称不能为空")
    private String workstationName;

    @Schema(description = "工作站地点")
    private String workstationAddress;

    @Schema(description = "所在车间ID", example = "30961")
    private Long workshopId;

    @Schema(description = "所在车间编码")
    private String workshopCode;

    @Schema(description = "所在车间名称", example = "赵六")
    private String workshopName;

    @Schema(description = "工序ID", example = "20399")
    private Long processId;

    @Schema(description = "工序编码")
    private String processCode;

    @Schema(description = "工序名称", example = "赵六")
    private String processName;

    @Schema(description = "线边库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "721")
    private Long warehouseId;

    @Schema(description = "线边库编码")
    private String warehouseCode;

    @Schema(description = "线边库名称", example = "李四")
    private String warehouseName;

    @Schema(description = "库区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10233")
    private Long locationId;

    @Schema(description = "库区编码")
    private String locationCode;

    @Schema(description = "库区名称", example = "张三")
    private String locationName;

    @Schema(description = "库位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15797")
    private Long areaId;

    @Schema(description = "库位编码")
    private String areaCode;

    @Schema(description = "库位名称", example = "李四")
    private String areaName;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
    private String enableFlag;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    public Long getId(){
        return null;
    }
}
