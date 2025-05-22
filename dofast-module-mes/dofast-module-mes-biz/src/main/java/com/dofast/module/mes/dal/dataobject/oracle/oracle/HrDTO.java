package com.dofast.module.mes.dal.dataobject.oracle.oracle;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HrDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    private String name;

    private String userName;

    private String password;

    private String deptNo;

    private String deptName;

    private String judian;

    private String judianQx;

    private String tenant_id;

    private String avatar;

    private String userId;

    private String sysId;

}
