package com.dofast.module.mes.controller.admin.Autocode;

import com.dofast.module.mes.api.autocode.AutoCodeApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Tag(name = "管理后台 - 编码生成")
@RestController
@RequestMapping("/mes/autocode")
public class AutocodeController {
    @Resource
    private AutoCodeApi autoCodeApi;

    @Operation(summary = "获取一个自动生成的编码")
    @GetMapping(value = {"/get/{ruleCode}/{inputCharacter}","/get/{ruleCode}"})
    public String getAutoCode(@PathVariable String ruleCode, @PathVariable(required = false) String inputCharacter){
        return autoCodeApi.genSerialCode(ruleCode,inputCharacter);
    }

}
