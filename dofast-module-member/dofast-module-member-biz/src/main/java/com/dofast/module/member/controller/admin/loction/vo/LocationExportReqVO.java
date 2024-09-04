package com.dofast.module.member.controller.admin.loction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 三级位置信息 Excel 导出 Request VO，参数和 PageReqVO 是一致的")
@Data
public class LocationExportReqVO {

    @Schema(description = "省市区名称", example = "王五")
    private String name;

    @Schema(description = "上级ID", example = "22747")
    private Integer parentid;

    @Schema(description = "简称", example = "李四")
    private String shortname;

    @Schema(description = "级别:0,中国；1，省分；2，市；3，区、县", example = "1")
    private Byte leveltype;

    @Schema(description = "城市代码")
    private String citycode;

    @Schema(description = "邮编")
    private String zipcode;

    @Schema(description = "经度")
    private String lng;

    @Schema(description = "纬度")
    private String lat;

    @Schema(description = "拼音")
    private String pinyin;

    @Schema(description = "枚举", example = "2")
    private String status;

}
