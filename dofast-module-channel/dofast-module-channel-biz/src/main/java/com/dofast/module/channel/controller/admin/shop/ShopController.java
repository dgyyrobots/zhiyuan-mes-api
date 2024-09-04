package com.dofast.module.channel.controller.admin.shop;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.config.Dian3Config;
import com.dofast.module.channel.controller.admin.shop.vo.*;
import com.dofast.module.channel.convert.shop.ShopConvert;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import com.dofast.module.channel.serv.ShopServ;
import com.dofast.module.channel.service.shop.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 店铺授权")
@RestController
@RequestMapping("/channel/shop")
@Validated
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopServ shopServ;

    @Autowired
    private Dian3 dian3;

    @Autowired
    private Dian3Config dian3Config;


    @PostMapping("/create")
    @Operation(summary = "创建店铺授权")
    @PreAuthorize("@ss.hasPermission('channel:shop:create')")
    public CommonResult<String> createShop(@Valid @RequestBody ShopCreateReqVO createReqVO) {
        return success(shopService.createShop(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新店铺授权")
    @PreAuthorize("@ss.hasPermission('channel:shop:update')")
    public CommonResult<Boolean> updateShop(@Valid @RequestBody ShopUpdateReqVO updateReqVO) {
        shopService.updateShop(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除店铺授权")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('channel:shop:delete')")
    public CommonResult<Boolean> deleteShop(@RequestParam("id") String id) {
        shopService.deleteShop(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得店铺授权")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:shop:query')")
    public CommonResult<ShopRespVO> getShop(@RequestParam("id") String id) {
        ShopDO shop = shopService.getShop(id);
        ShopRespVO convert = ShopConvert.INSTANCE.convert(shop);
        return success(convert);
    }

    @GetMapping("/list")
    @Operation(summary = "获得店铺授权列表")
    @PreAuthorize("@ss.hasPermission('channel:shop:query')")
    public CommonResult<List<ShopRespVO>> getShopList() {
        List<ShopDO> list = shopService.getShopList();
        return success(ShopConvert.INSTANCE.convertList(list));
    }

    @PostMapping("/page")
    @Operation(summary = "获得店铺授权分页")
    @PreAuthorize("@ss.hasPermission('channel:shop:query')")
    public CommonResult<PageResult<ShopRespVO>> getShopPage(@Valid @RequestBody ShopPageReqVO pageVO) {
        PageResult<ShopDO> pageResult = shopService.getShopPage(pageVO);
        return success(ShopConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出店铺授权 Excel")
    @PreAuthorize("@ss.hasPermission('channel:shop:export')")
    @OperateLog(type = EXPORT)
    public void exportShopExcel(@Valid ShopExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ShopDO> list = shopService.getShopList(exportReqVO);
        // 导出 Excel
        List<ShopExcelVO> datas = ShopConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "店铺授权.xls", "数据", ShopExcelVO.class, datas);
    }

    @PostMapping("/dashboard")
    @Operation(summary = "获得渠道和店铺的分页")
    @PreAuthorize("@ss.hasPermission('channel:shop:query')")
    public CommonResult<PageResult<ShopRespVO>> getChanals(@RequestBody @Valid ShopPageReqVO pageVO) {
        PageResult<ShopDO> pageResult = shopService.getShopPage(pageVO);
        return success(ShopConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获得渠道和店铺的信息")
    @PreAuthorize("@ss.hasPermission('channel:shop:query')")
    public CommonResult<List<ChannelShopVO>> getChannelAndShops() {
        List<ChannelShopVO> channelShopVOList = shopService.getChannelShops();
        return success(ShopConvert.INSTANCE.convertList03(channelShopVOList));
    }

    @PostMapping("/sync-by-code")
    @Operation(summary = "Dian3同步订单")
    @Parameter(name = "shopCode", description = "商铺编号", example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:shop:query')")
    public CommonResult<Boolean> ShopSyncByCode(@RequestParam(name = "shopCode", required = false) String shopCode) {
        HashMap<String, Object> params = new HashMap<>();
        if(shopCode != null){
            params.put("codes", shopCode);
        }
        shopServ.syncShop(params);
        return success(true);
    }
}
