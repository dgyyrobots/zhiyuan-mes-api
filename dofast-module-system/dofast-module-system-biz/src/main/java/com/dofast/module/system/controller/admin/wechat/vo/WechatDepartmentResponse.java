package com.dofast.module.system.controller.admin.wechat.vo;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "企业微信获取部门列表的返回信息")
public class WechatDepartmentResponse {
    private Integer errcode;

    private String errmsg;

    @SerializedName("department")
    private List<WechatDepartment> departments;
}
