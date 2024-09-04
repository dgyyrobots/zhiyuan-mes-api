package com.dofast.module.member.controller.admin.address;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.member.controller.admin.address.vo.AddressCreateReqVO;
import com.dofast.module.member.controller.admin.address.vo.AddressRespVO;
import com.dofast.module.member.controller.app.address.vo.AppAddressCreateReqVO;
import com.dofast.module.member.convert.address.AddressConvert;
import com.dofast.module.member.dal.dataobject.address.MemberAddressDO;
import com.dofast.module.member.service.address.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 用户收件地址")
@RestController("AddressControllerD")
@RequestMapping("/member/address")
@Validated
public class AddressController {

    @Resource
    private AddressService addressService;

    @PostMapping("/create")
    @Operation(summary = "创建用户收件地址")
    @PreAuthenticated
    public CommonResult<Long> createAddress(@Valid @RequestBody AddressCreateReqVO createReqVO) {
        return success(addressService.createAddress(createReqVO.getUserId(), createReqVO));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户收件地址列表")
    @Parameter(name = "userId", description = "用户编号", required = true)
    @PreAuthorize("@ss.hasPermission('member:user:query')")
    public CommonResult<List<AddressRespVO>> getAddressList(@RequestParam("userId") Long userId) {
        List<MemberAddressDO> list = addressService.getAddressList(userId);
        return success(AddressConvert.INSTANCE.convertList2(list));
    }





}
