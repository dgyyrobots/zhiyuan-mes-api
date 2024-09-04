package com.dofast.module.wms.controller.admin.productrecptline;

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

import com.dofast.module.wms.controller.admin.productrecptline.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecptline.ProductRecptLineDO;
import com.dofast.module.wms.convert.productrecptline.ProductRecptLineConvert;
import com.dofast.module.wms.service.productrecptline.ProductRecptLineService;

@Tag(name = "仓储管理 - 产品入库记录表行")
@RestController
@RequestMapping("/mes/wms/product-recpt-line")
@Validated
public class ProductRecptLineController {

    @Resource
    private ProductRecptLineService productRecptLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建产品入库记录表行")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:create')")
    public CommonResult<Long> createProductRecptLine(@Valid @RequestBody ProductRecptLineCreateReqVO createReqVO) {
        if(StrUtils.isNotNull(createReqVO.getWarehouseId())){
            WarehouseDO warehouse = warehouseService.getWarehouse(createReqVO.getWarehouseId());
            createReqVO.setWarehouseCode(warehouse.getWarehouseCode());
            createReqVO.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getLocationId())){
            StorageLocationDO location = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            createReqVO.setLocationCode(location.getLocationCode());
            createReqVO.setLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(createReqVO.getAreaId())){
            StorageAreaDO area = storageAreaService.getStorageArea(createReqVO.getAreaId());
            createReqVO.setAreaCode(area.getAreaCode());
            createReqVO.setAreaName(area.getAreaName());
        }
        return success(productRecptLineService.createProductRecptLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品入库记录表行")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:update')")
    public CommonResult<Boolean> updateProductRecptLine(@Valid @RequestBody ProductRecptLineUpdateReqVO updateReqVO) {
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
        productRecptLineService.updateProductRecptLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品入库记录表行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:delete')")
    public CommonResult<Boolean> deleteProductRecptLine(@RequestParam("id") Long id) {
        productRecptLineService.deleteProductRecptLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品入库记录表行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:query')")
    public CommonResult<ProductRecptLineRespVO> getProductRecptLine(@RequestParam("id") Long id) {
        ProductRecptLineDO productRecptLine = productRecptLineService.getProductRecptLine(id);
        return success(ProductRecptLineConvert.INSTANCE.convert(productRecptLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品入库记录表行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:query')")
    public CommonResult<List<ProductRecptLineRespVO>> getProductRecptLineList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductRecptLineDO> list = productRecptLineService.getProductRecptLineList(ids);
        return success(ProductRecptLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品入库记录表行分页")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:query')")
    public CommonResult<PageResult<ProductRecptLineRespVO>> getProductRecptLinePage(@Valid ProductRecptLinePageReqVO pageVO) {
        PageResult<ProductRecptLineDO> pageResult = productRecptLineService.getProductRecptLinePage(pageVO);
        return success(ProductRecptLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品入库记录表行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt-line:export')")
    @OperateLog(type = EXPORT)
    public void exportProductRecptLineExcel(@Valid ProductRecptLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductRecptLineDO> list = productRecptLineService.getProductRecptLineList(exportReqVO);
        // 导出 Excel
        List<ProductRecptLineExcelVO> datas = ProductRecptLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品入库记录表行.xls", "数据", ProductRecptLineExcelVO.class, datas);
    }

}
