package com.dofast.module.member.controller.admin.loction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@Schema(description = "管理后台 - 三级位置信息分页 根据ID List Response VO")
public class LocationQueryId {
    @Schema(description = "Id")
    private Integer id;

    @Schema(description = "城市名")
    private String name;

    @Schema(description = "父Id")
    private Integer parentid;

    @Schema(description = "邮编")
    private String zipcode;

    @Schema(description = "类列表", example = "")
    private List<LocationQueryId> list = new ArrayList<>();
}
