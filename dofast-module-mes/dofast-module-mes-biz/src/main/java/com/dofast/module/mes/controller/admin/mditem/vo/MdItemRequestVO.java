package com.dofast.module.mes.controller.admin.mditem.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Schema(description = "管理后台 - 物料需求 Request VO类")
public class MdItemRequestVO {
    private Long id;

    private String itemName;

    private String itemTypeName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MdItemRequestVO> node = new ArrayList<>();

    public void addNode(MdItemRequestVO mdItemRequestVO) {
        node.add(mdItemRequestVO);
    }
}
