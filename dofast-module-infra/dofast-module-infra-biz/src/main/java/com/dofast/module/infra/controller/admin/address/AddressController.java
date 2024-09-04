package com.dofast.module.infra.controller.admin.address;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.module.infra.controller.admin.address.vo.AddressData;
import com.dofast.module.infra.service.address.AddressKDNService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.io.IOException;

import static com.dofast.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 快递鸟")
@RestController("AddressControllerA")
@RequestMapping("/infra/address")
@Validated
public class AddressController {
    @Resource
    private AddressKDNService addressKDNService;

    @GetMapping("/source")
    @Operation(summary = "获得地址解析信息")
    @Parameter(name = "content", description = "不完整的地址信息", required = true,
            example = "深圳市龙岗区坂田街道长坑路西2巷2号202 黄大大 18273778575")
    public CommonResult<AddressData> getCodeGenTablePage(@RequestParam("content") String content) throws IOException {
        AddressData data = addressKDNService.getAddressData(content);
        return success(data);
    }
}
