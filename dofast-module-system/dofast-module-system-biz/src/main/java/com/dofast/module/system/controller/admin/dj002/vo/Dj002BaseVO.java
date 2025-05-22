package com.dofast.module.system.controller.admin.dj002.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 系统地址信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class Dj002BaseVO {

    @Schema(description = "公司名称", example = "赵六")
    private String deptName;

    @Schema(description = "系统名称", example = "李四")
    private String sysName;

    @Schema(description = "内网系统访问地址", example = "https://www.iocoder.cn")
    private String sysUrl;

    @Schema(description = "外网系统访问地址")
    private String sysUrlNet;

    @Schema(description = "图标地址", example = "https://www.iocoder.cn")
    private String sysLogUrl;

    @Schema(description = "创建者")
    private String createUser;

    @Schema(description = "修改者")
    private String updateUser;

    @Schema(description = "程序编号")
    private String pg;

}
