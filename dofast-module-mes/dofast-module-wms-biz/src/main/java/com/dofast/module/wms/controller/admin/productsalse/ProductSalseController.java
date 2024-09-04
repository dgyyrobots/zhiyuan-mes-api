package com.dofast.module.wms.controller.admin.productsalse;

import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.productsalseline.vo.ProductSalseLineListVO;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseTxBean;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.productsalseline.ProductSalseLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.transaction.annotation.Transactional;
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

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.productsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseDO;
import com.dofast.module.wms.convert.productsalse.ProductSalseConvert;
import com.dofast.module.wms.service.productsalse.ProductSalseService;

@Tag(name = "仓储管理 - 销售出库单")
@RestController
@RequestMapping("/mes/wms/product-salse")
@Validated
public class ProductSalseController {

    @Resource
    private ProductSalseService productSalseService;

    @Resource
    private ProductSalseLineService productSalseLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private StorageCoreService storageCoreService;

    @PostMapping("/create")
    @Operation(summary = "创建销售出库单")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:create')")
    public CommonResult<Long> createProductSalse(@Valid @RequestBody ProductSalseCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(productSalseService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.PRODUCT_SALSE_ID_EXISTS);
        }

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

        return success(productSalseService.createProductSalse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新销售出库单")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:update')")
    public CommonResult<Boolean> updateProductSalse(@Valid @RequestBody ProductSalseUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(productSalseService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.PRODUCT_SALSE_ID_EXISTS);
        }

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

        productSalseService.updateProductSalse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除销售出库单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:product-salse:delete')")
    public CommonResult<Boolean> deleteProductSalse(@RequestParam("id") Long id) {
        productSalseLineService.deleteBySalseId(id);
        productSalseService.deleteProductSalse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得销售出库单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:query')")
    public CommonResult<ProductSalseRespVO> getProductSalse(@RequestParam("id") Long id) {
        ProductSalseDO productSalse = productSalseService.getProductSalse(id);
        return success(ProductSalseConvert.INSTANCE.convert(productSalse));
    }

    /**
     * 执行出库
     * @return
     */

    @Operation(summary = "销售执行出库")
    @Transactional
    @PutMapping("/{salseId}")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:update')")
    public CommonResult execute(@PathVariable Long salseId){
        ProductSalseDO salse = productSalseService.getProductSalse(salseId);

        ProductSalseLineListVO param = new ProductSalseLineListVO();
        param.setSalseId(salseId);
        List<ProductSalseLineDO> lines = productSalseLineService.selectWmProductSalseLineList(param);
        if(CollectionUtil.isEmpty(lines)){
            return error(ErrorCodeConstants.PRODUCT_SALSE_LINE_PRODUCT_NOT_NULL);
        }

        List<ProductSalseTxBean> beans = productSalseService.getTxBeans(salseId);
        storageCoreService.processProductSalse(beans);

        salse.setStatus(Constant.ORDER_STATUS_FINISHED);

        ProductSalseUpdateReqVO updateReqVO = ProductSalseConvert.INSTANCE.convert01(salse);
        productSalseService.updateProductSalse(updateReqVO);
        return success(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获得销售出库单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:query')")
    public CommonResult<List<ProductSalseRespVO>> getProductSalseList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductSalseDO> list = productSalseService.getProductSalseList(ids);
        return success(ProductSalseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得销售出库单分页")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:query')")
    public CommonResult<PageResult<ProductSalseRespVO>> getProductSalsePage(@Valid ProductSalsePageReqVO pageVO) {
        PageResult<ProductSalseDO> pageResult = productSalseService.getProductSalsePage(pageVO);
        return success(ProductSalseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出销售出库单 Excel")
    @PreAuthorize("@ss.hasPermission('wms:product-salse:export')")
    @OperateLog(type = EXPORT)
    public void exportProductSalseExcel(@Valid ProductSalseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductSalseDO> list = productSalseService.getProductSalseList(exportReqVO);
        // 导出 Excel
        List<ProductSalseExcelVO> datas = ProductSalseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "销售出库单.xls", "数据", ProductSalseExcelVO.class, datas);
    }

}
