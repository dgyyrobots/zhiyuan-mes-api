package com.dofast.module.mes.controller.admin.mditemtype.vo;

import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MdItemTreeTypeListVO implements Serializable {
    private Long id;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MdItemTreeTypeListVO> children;

    public MdItemTreeTypeListVO(MdItemTypeDO dept)
    {
        this.id = dept.getId();
        this.label = dept.getItemTypeName();
        this.children = dept.getChildren().stream().map(MdItemTreeTypeListVO::new).collect(Collectors.toList());
    }
}
