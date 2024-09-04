package com.dofast.module.iot.controller.admin.productauthorize;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
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

import com.dofast.module.iot.controller.admin.productauthorize.vo.*;
import com.dofast.module.iot.dal.dataobject.productauthorize.ProductAuthorizeDO;
import com.dofast.module.iot.convert.productauthorize.ProductAuthorizeConvert;
import com.dofast.module.iot.service.productauthorize.ProductAuthorizeService;

@Tag(name = "管理后台 - 产品授权码")
@RestController
@RequestMapping("/iot/product-authorize")
@Validated
public class ProductAuthorizeController {

    @Resource
    private ProductAuthorizeService productAuthorizeService;

    @PostMapping("/create")
    @Operation(summary = "创建产品授权码")
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:create')")
    public CommonResult<Long> createProductAuthorize(@Valid @RequestBody ProductAuthorizeCreateReqVO createReqVO) {
        return success(productAuthorizeService.createProductAuthorize(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品授权码")
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:update')")
    public CommonResult<Boolean> updateProductAuthorize(@Valid @RequestBody ProductAuthorizeUpdateReqVO updateReqVO) {
        productAuthorizeService.updateProductAuthorize(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品授权码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:delete')")
    public CommonResult<Boolean> deleteProductAuthorize(@RequestParam("id") Long id) {
        productAuthorizeService.deleteProductAuthorize(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品授权码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:query')")
    public CommonResult<ProductAuthorizeRespVO> getProductAuthorize(@RequestParam("id") Long id) {
        ProductAuthorizeDO productAuthorize = productAuthorizeService.getProductAuthorize(id);
        return success(ProductAuthorizeConvert.INSTANCE.convert(productAuthorize));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品授权码列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:query')")
    public CommonResult<List<ProductAuthorizeRespVO>> getProductAuthorizeList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductAuthorizeDO> list = productAuthorizeService.getProductAuthorizeList(ids);
        return success(ProductAuthorizeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品授权码分页")
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:query')")
    public CommonResult<PageResult<ProductAuthorizeRespVO>> getProductAuthorizePage(@Valid ProductAuthorizePageReqVO pageVO) {
        PageResult<ProductAuthorizeDO> pageResult = productAuthorizeService.getProductAuthorizePage(pageVO);
        return success(ProductAuthorizeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品授权码 Excel")
    @PreAuthorize("@ss.hasPermission('iot:product-authorize:export')")
    @OperateLog(type = EXPORT)
    public void exportProductAuthorizeExcel(@Valid ProductAuthorizeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductAuthorizeDO> list = productAuthorizeService.getProductAuthorizeList(exportReqVO);
        // 导出 Excel
        List<ProductAuthorizeExcelVO> datas = ProductAuthorizeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品授权码.xls", "数据", ProductAuthorizeExcelVO.class, datas);
    }

}
