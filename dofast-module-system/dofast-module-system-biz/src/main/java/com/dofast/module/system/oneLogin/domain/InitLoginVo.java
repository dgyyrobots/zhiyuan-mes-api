package com.dofast.module.system.oneLogin.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InitLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;


    //系统名称
    private String sysName;

    //部门 名称(据点或者公司名称)
    private String deptName;

    //内网系统地址
    private String sysUrl;

    //外网系统地址
    private String sysUrlNet;

    //系统账号
    private String userName;

    //系统密码
    private String password;

    //系统图标地址
    private String sysLogUrl;

    //系统Id
    private String sysId;
}
