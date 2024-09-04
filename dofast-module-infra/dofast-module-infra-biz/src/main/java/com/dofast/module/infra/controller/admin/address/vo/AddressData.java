package com.dofast.module.infra.controller.admin.address.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@Schema(description = "管理后台 - 地址解析，返回给前端的数据 AddressData")
@ToString(callSuper = true)
public class AddressData {
    @Schema(description = "姓名")
    private String Name;

    @Schema(description = "手机号")
    private String Mobile;

    @Schema(description = "省份")
    private String ProvinceName;

    @Schema(description = "城市")
    private String CityName;

    @Schema(description = "所在地区/县级市")
    private String ExpAreaName;

    @Schema(description = "街道名称")
    private String StreetName;

    @Schema(description = "详细地址")
    private String Address;

    public AddressData(String name, String mobile, String provinceName,
                       String cityName, String expAreaName, String streetName, String address) {
        Name = name;
        Mobile = mobile;
        ProvinceName = provinceName;
        CityName = cityName;
        ExpAreaName = expAreaName;
        StreetName = streetName;
        Address = address;
    }
}
