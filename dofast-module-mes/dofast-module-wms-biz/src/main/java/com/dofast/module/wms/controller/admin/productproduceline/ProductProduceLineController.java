package com.dofast.module.wms.controller.admin.productproduceline;

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

import com.dofast.module.wms.controller.admin.productproduceline.vo.*;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.module.wms.convert.productproduceline.ProductProduceLineConvert;
import com.dofast.module.wms.service.productproduceline.ProductProduceLineService;

@Tag(name = "管理后台 - 产品产出记录表行")
@RestController
@RequestMapping("/wms/product-produce-line")
@Validated
public class ProductProduceLineController {

    @Resource
    private ProductProduceLineService productProduceLineService;

    @PostMapping("/create")
    @Operation(summary = "创建产品产出记录表行")
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:create')")
    public CommonResult<Long> createProductProduceLine(@Valid @RequestBody ProductProduceLineCreateReqVO createReqVO) {
        return success(productProduceLineService.createProductProduceLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品产出记录表行")
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:update')")
    public CommonResult<Boolean> updateProductProduceLine(@Valid @RequestBody ProductProduceLineUpdateReqVO updateReqVO) {
        productProduceLineService.updateProductProduceLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品产出记录表行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:delete')")
    public CommonResult<Boolean> deleteProductProduceLine(@RequestParam("id") Long id) {
        productProduceLineService.deleteProductProduceLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品产出记录表行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:query')")
    public CommonResult<ProductProduceLineRespVO> getProductProduceLine(@RequestParam("id") Long id) {
        ProductProduceLineDO productProduceLine = productProduceLineService.getProductProduceLine(id);
        return success(ProductProduceLineConvert.INSTANCE.convert(productProduceLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品产出记录表行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:query')")
    public CommonResult<List<ProductProduceLineRespVO>> getProductProduceLineList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductProduceLineDO> list = productProduceLineService.getProductProduceLineList(ids);
        return success(ProductProduceLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品产出记录表行分页")
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:query')")
    public CommonResult<PageResult<ProductProduceLineRespVO>> getProductProduceLinePage(@Valid ProductProduceLinePageReqVO pageVO) {
        PageResult<ProductProduceLineDO> pageResult = productProduceLineService.getProductProduceLinePage(pageVO);
        return success(ProductProduceLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品产出记录表行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:product-produce-line:export')")
    @OperateLog(type = EXPORT)
    public void exportProductProduceLineExcel(@Valid ProductProduceLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductProduceLineDO> list = productProduceLineService.getProductProduceLineList(exportReqVO);
        // 导出 Excel
        List<ProductProduceLineExcelVO> datas = ProductProduceLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品产出记录表行.xls", "数据", ProductProduceLineExcelVO.class, datas);
    }

}
