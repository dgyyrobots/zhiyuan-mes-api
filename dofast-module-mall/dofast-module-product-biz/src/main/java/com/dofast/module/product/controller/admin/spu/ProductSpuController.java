package com.dofast.module.product.controller.admin.spu;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.product.controller.admin.spu.vo.*;


import com.dofast.module.product.convert.spu.ProductSpuConvert;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.dal.dataobject.spu.ProductSpuDO;
import com.dofast.module.product.service.sku.ProductSkuService;
import com.dofast.module.product.service.spu.ProductSpuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


import java.util.ArrayList;

import java.util.Collection;

import java.util.List;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.dofast.module.product.enums.ErrorCodeConstants.SPU_NOT_EXISTS;

@Tag(name = "管理后台 - 商品 SPU")
@RestController
@RequestMapping("/product/spu")
@Validated
public class ProductSpuController {

    @Resource
    private ProductSpuService productSpuService;
    @Resource
    private ProductSkuService productSkuService;


    @PostMapping("/create")
    @Operation(summary = "创建商品 SPU")
    @PreAuthorize("@ss.hasPermission('product:spu:create')")
    public CommonResult<Long> createProductSpu(@Valid @RequestBody ProductSpuCreateReqVO createReqVO) {
        return success(productSpuService.createSpu(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品 SPU")
    @PreAuthorize("@ss.hasPermission('product:spu:update')")
    public CommonResult<Boolean> updateSpu(@Valid @RequestBody ProductSpuUpdateReqVO updateReqVO) {
        productSpuService.updateSpu(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新商品 SPU Status")
    @PreAuthorize("@ss.hasPermission('product:spu:update')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody ProductSpuUpdateStatusReqVO updateReqVO) {
        productSpuService.updateSpuStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商品 SPU")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('product:spu:delete')")
    public CommonResult<Boolean> deleteSpu(@RequestParam("id") Long id) {
        productSpuService.deleteSpu(id);
        return success(true);
    }

    @GetMapping("/get-detail")
    @Operation(summary = "获得商品 SPU 明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<ProductSpuDetailRespVO> getSpuDetail(@RequestParam("id") Long id) {
        // 获得商品 SPU
        ProductSpuDO spu = productSpuService.getSpu(id);
        if (spu == null) {
            throw exception(SPU_NOT_EXISTS);
        }
        // 查询商品 SKU
        List<ProductSkuDO> skus = productSkuService.getSkuListBySpuId(spu.getId());
        ProductSpuDetailRespVO productSpuDetailRespVO = ProductSpuConvert.INSTANCE.convertForSpuDetailRespVO(spu, skus);
        return success(ProductSpuConvert.INSTANCE.convertForSpuDetailRespVO(spu, skus));

    }



    @GetMapping("/list-all-simple")
    @Operation(summary = "获得商品 SPU 精简列表")
    @PermitAll
    public CommonResult<List<ProductSpuSimpleRespVO>> getSpuSimpleList() {
        List<ProductSpuDO> list = productSpuService.getSpuList();
        return success(ProductSpuConvert.INSTANCE.convertList02(list));
    }


    @GetMapping("/list")
    @Operation(summary = "获得商品 SPU 详情列表")
    @Parameter(name = "spuIds", description = "spu 编号列表", required = true, example = "[1,2,3]")
    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<List<ProductSpuDetailRespVO>> getSpuList(@RequestParam("spuIds") Collection<Long> spuIds) {
        return success(ProductSpuConvert.INSTANCE.convertForSpuDetailRespListVO(
                productSpuService.getSpuList(spuIds), productSkuService.getSkuListBySpuId(spuIds)));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商品 SPU 分页")
    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<PageResult<ProductSpuRespVO>> getSpuPage(@Valid ProductSpuPageReqVO pageVO) {
        return success(ProductSpuConvert.INSTANCE.convertPage(productSpuService.getSpuPage(pageVO)));
    }

    @GetMapping("/get-count")
    @Operation(summary = "获得商品 SPU 分页 tab count")
    @PreAuthorize("@ss.hasPermission('product:spu:query')")
    public CommonResult<Map<Integer, Long>> getSpuCount() {
        return success(productSpuService.getTabsCount());
    }

    @GetMapping("/export")
    @Operation(summary = "导出商品")
    @PreAuthorize("@ss.hasPermission('product:spu:export')")
    @OperateLog(type = EXPORT)
    public void exportUserList(@Validated ProductSpuExportReqVO reqVO,
                               HttpServletResponse response) throws IOException {
        List<ProductSpuDO> spuList = productSpuService.getSpuList(reqVO);
        // 导出 Excel
        List<ProductSpuExcelVO> datas = ProductSpuConvert.INSTANCE.convertList03(spuList);
        ExcelUtils.write(response, "商品列表.xls", "数据", ProductSpuExcelVO.class, datas);
    }

}
