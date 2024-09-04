package com.dofast.module.channel.controller.admin.address;

import com.dofast.module.channel.convert.address.AddressConvert;
import com.dofast.module.channel.dal.dataobject.address.ChannelAddressDO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.channel.controller.admin.address.vo.*;
import com.dofast.module.channel.service.address.ChannelAddressService;

@Tag(name = "管理后台 - 交易客户")
@RestController
@RequestMapping("/channel/customer")
@Validated
public class ChannelAddressController {

    @Resource
    private ChannelAddressService channelAddressService;

    @PostMapping("/create")
    @Operation(summary = "创建交易客户")
    @PreAuthorize("@ss.hasPermission('channel:customer:create')")
    public CommonResult<Integer> createCustomer(@Valid @RequestBody AddressCreateReqVO createReqVO) {
        return success(channelAddressService.createAddress(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新交易客户")
    @PreAuthorize("@ss.hasPermission('channel:customer:update')")
    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody AddressUpdateReqVO updateReqVO) {
        channelAddressService.updateAddress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除交易客户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('channel:customer:delete')")
    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Integer id) {
        channelAddressService.deleteAddress(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得交易客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:customer:query')")
    public CommonResult<AddressRespVO> getCustomer(@RequestParam("id") Integer id) {
        ChannelAddressDO customer = channelAddressService.getAddress(id);
        return success(AddressConvert.INSTANCE.convert(customer));
    }

    @GetMapping("/list")
    @Operation(summary = "获得交易客户列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:customer:query')")
    public CommonResult<List<AddressRespVO>> getCustomerList(@RequestParam("ids") Collection<Integer> ids) {
        List<ChannelAddressDO> list = channelAddressService.getAddressList(ids);
        return success(AddressConvert.INSTANCE.convertList(list));
    }

    @PostMapping("/page")
    @Operation(summary = "获得交易客户分页")
    @PreAuthorize("@ss.hasPermission('channel:customer:query')")
    public CommonResult<PageResult<AddressRespVO>> getCustomerPage(@Valid @RequestBody AddressPageReqVO pageVO) {
        PageResult<ChannelAddressDO> pageResult = channelAddressService.getAddressPage(pageVO);
        return success(AddressConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出交易客户 Excel")
    @PreAuthorize("@ss.hasPermission('channel:customer:export')")
    @OperateLog(type = EXPORT)
    public void exportCustomerExcel(@Valid AddressExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ChannelAddressDO> list = channelAddressService.getAddressList(exportReqVO);
        // 导出 Excel
        List<AddressExcelVO> datas = AddressConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "交易客户.xls", "数据", AddressExcelVO.class, datas);
    }

}
