package com.dofast.module.wms.controller.admin.productsalseline;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
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

import com.dofast.module.wms.controller.admin.productsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import com.dofast.module.wms.convert.productsalseline.ProductSalseLineConvert;
import com.dofast.module.wms.service.productsalseline.ProductSalseLineService;

@Tag(name = "仓储管理 - 产品销售出库行")
@RestController
@RequestMapping("/mes/wms/product-salse-line")
@Validated
public class ProductSalseLineController {

    @Resource
    private ProductSalseLineService productSalseLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建产品销售出库行")
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:create')")
    public CommonResult<Long> createProductSalseLine(@Valid @RequestBody ProductSalseLineCreateReqVO createReqVO) {
        if(StrUtils.isNotNull(createReqVO.getWarehouseId())){
            WarehouseDO warehouseDO = warehouseService.getWarehouse(createReqVO.getWarehouseId());
            createReqVO.setWarehouseCode(warehouseDO.getWarehouseCode());
            createReqVO.setWarehouseName(warehouseDO.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getLocationId())){
            StorageLocationDO storageLocationDO = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            createReqVO.setLocationCode(storageLocationDO.getLocationCode());
            createReqVO.setLocationName(storageLocationDO.getLocationName());
        }
        if(StrUtils.isNotNull(createReqVO.getAreaId())){
            StorageAreaDO storageAreaDO = storageAreaService.getStorageArea(createReqVO.getAreaId());
            createReqVO.setAreaCode(storageAreaDO.getAreaCode());
            createReqVO.setAreaName(storageAreaDO.getAreaName());
        }
        return success(productSalseLineService.createProductSalseLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品销售出库行")
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:update')")
    public CommonResult<Boolean> updateProductSalseLine(@Valid @RequestBody ProductSalseLineUpdateReqVO updateReqVO) {
        if(StrUtils.isNotNull(updateReqVO.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getWarehouseId());
            updateReqVO.setWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(updateReqVO.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(updateReqVO.getLocationId());
            updateReqVO.setLocationCode(location.getLocationCode());
            updateReqVO.setLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(updateReqVO.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(updateReqVO.getAreaId());
            updateReqVO.setAreaCode(area.getAreaCode());
            updateReqVO.setAreaName(area.getAreaName());
        }
        productSalseLineService.updateProductSalseLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品销售出库行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:delete')")
    public CommonResult<Boolean> deleteProductSalseLine(@RequestParam("id") Long id) {
        productSalseLineService.deleteProductSalseLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品销售出库行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:query')")
    public CommonResult<ProductSalseLineRespVO> getProductSalseLine(@RequestParam("id") Long id) {
        ProductSalseLineDO productSalseLine = productSalseLineService.getProductSalseLine(id);
        return success(ProductSalseLineConvert.INSTANCE.convert(productSalseLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品销售出库行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:query')")
    public CommonResult<List<ProductSalseLineRespVO>> getProductSalseLineList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductSalseLineDO> list = productSalseLineService.getProductSalseLineList(ids);
        return success(ProductSalseLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品销售出库行分页")
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:query')")
    public CommonResult<PageResult<ProductSalseLineRespVO>> getProductSalseLinePage(@Valid ProductSalseLinePageReqVO pageVO) {
        PageResult<ProductSalseLineDO> pageResult = productSalseLineService.getProductSalseLinePage(pageVO);
        return success(ProductSalseLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品销售出库行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:product-salse-line:export')")
    @OperateLog(type = EXPORT)
    public void exportProductSalseLineExcel(@Valid ProductSalseLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductSalseLineDO> list = productSalseLineService.getProductSalseLineList(exportReqVO);
        // 导出 Excel
        List<ProductSalseLineExcelVO> datas = ProductSalseLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品销售出库行.xls", "数据", ProductSalseLineExcelVO.class, datas);
    }

}
