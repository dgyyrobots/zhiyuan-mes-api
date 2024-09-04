package com.dofast.module.wms.controller.admin.productproduce;

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

import com.dofast.module.wms.controller.admin.productproduce.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;
import com.dofast.module.wms.convert.productproduce.ProductProduceConvert;
import com.dofast.module.wms.service.productproduce.ProductProduceService;

@Tag(name = "管理后台 - 产品产出记录")
@RestController
@RequestMapping("/wms/product-produce")
@Validated
public class ProductProduceController {

    @Resource
    private ProductProduceService productProduceService;

    @PostMapping("/create")
    @Operation(summary = "创建产品产出记录")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:create')")
    public CommonResult<Long> createProductProduce(@Valid @RequestBody ProductProduceCreateReqVO createReqVO) {
        return success(productProduceService.createProductProduce(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品产出记录")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:update')")
    public CommonResult<Boolean> updateProductProduce(@Valid @RequestBody ProductProduceUpdateReqVO updateReqVO) {
        productProduceService.updateProductProduce(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品产出记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:product-produce:delete')")
    public CommonResult<Boolean> deleteProductProduce(@RequestParam("id") Long id) {
        productProduceService.deleteProductProduce(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品产出记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:query')")
    public CommonResult<ProductProduceRespVO> getProductProduce(@RequestParam("id") Long id) {
        ProductProduceDO productProduce = productProduceService.getProductProduce(id);
        return success(ProductProduceConvert.INSTANCE.convert(productProduce));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品产出记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:query')")
    public CommonResult<List<ProductProduceRespVO>> getProductProduceList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductProduceDO> list = productProduceService.getProductProduceList(ids);
        return success(ProductProduceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/count-wait")
    @Operation(summary = "获得待出库总数")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:query')")
    public CommonResult<Integer> CountWaitAll() {
        ProductProduceExportReqVO productProduceExportReqVO = new ProductProduceExportReqVO();
        productProduceExportReqVO.setStatus("NORMAL");
        List<ProductProduceDO> list = productProduceService.getProductProduceList(productProduceExportReqVO);
        Integer result = list == null? 0:list.size();
        return success(result);
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品产出记录分页")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:query')")
    public CommonResult<PageResult<ProductProduceRespVO>> getProductProducePage(@Valid ProductProducePageReqVO pageVO) {
        PageResult<ProductProduceDO> pageResult = productProduceService.getProductProducePage(pageVO);
        return success(ProductProduceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品产出记录 Excel")
    @PreAuthorize("@ss.hasPermission('wms:product-produce:export')")
    @OperateLog(type = EXPORT)
    public void exportProductProduceExcel(@Valid ProductProduceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductProduceDO> list = productProduceService.getProductProduceList(exportReqVO);
        // 导出 Excel
        List<ProductProduceExcelVO> datas = ProductProduceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品产出记录.xls", "数据", ProductProduceExcelVO.class, datas);
    }

}
