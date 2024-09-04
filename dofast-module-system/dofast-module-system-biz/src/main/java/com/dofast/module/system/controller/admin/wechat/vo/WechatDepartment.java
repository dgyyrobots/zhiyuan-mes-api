package com.dofast.module.system.controller.admin.wechat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "企业微信部门信息")
public class WechatDepartment {
        private Integer id;

        private Integer parentid;

        private Integer order;
}
