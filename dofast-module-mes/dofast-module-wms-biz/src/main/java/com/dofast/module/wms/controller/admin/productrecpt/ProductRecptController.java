package com.dofast.module.wms.controller.admin.productrecpt;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.productrecptline.vo.ProductRecptLineListVO;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptTxBean;
import com.dofast.module.wms.dal.dataobject.productrecptline.ProductRecptLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.productrecptline.ProductRecptLineService;
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

import com.dofast.module.wms.controller.admin.productrecpt.vo.*;
import com.dofast.module.wms.dal.dataobject.productrecpt.ProductRecptDO;
import com.dofast.module.wms.convert.productrecpt.ProductRecptConvert;
import com.dofast.module.wms.service.productrecpt.ProductRecptService;

@Tag(name = "仓储管理 - 产品入库录")
@RestController
@RequestMapping("/mes/wms/product-recpt")
@Validated
public class ProductRecptController {

    @Resource
    private ProductRecptService productRecptService;
    @Resource
    private ProductRecptLineService productRecptLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private StorageCoreService storageCoreService;

    @PostMapping("/create")
    @Operation(summary = "创建产品入库录")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:create')")
    public CommonResult<Long> createProductRecpt(@Valid @RequestBody ProductRecptCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(productRecptService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.PRODUCT_RECPT_CODE__EXISTS);
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
        return success(productRecptService.createProductRecpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品入库录")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:update')")
    public CommonResult<Boolean> updateProductRecpt(@Valid @RequestBody ProductRecptUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(productRecptService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.PRODUCT_RECPT_CODE__EXISTS);
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


        productRecptService.updateProductRecpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品入库录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:delete')")
    public CommonResult<Boolean> deleteProductRecpt(@RequestParam("id") Long id) {
        productRecptLineService.deleteProductRecptLine(id);
        productRecptService.deleteProductRecpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品入库录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:query')")
    public CommonResult<ProductRecptRespVO> getProductRecpt(@RequestParam("id") Long id) {
        ProductRecptDO productRecpt = productRecptService.getProductRecpt(id);
        return success(ProductRecptConvert.INSTANCE.convert(productRecpt));
    }

    /**
     * 执行入库
     * @return
     */
    @Operation(summary = "产品执行入库")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:update')")
    @Transactional
    @PutMapping("/{recptId}")
    public CommonResult execute(@PathVariable Long recptId){
        ProductRecptDO recpt = productRecptService.getProductRecpt(recptId);

        ProductRecptLineListVO param = new ProductRecptLineListVO();
        param.setRecptId(recptId);
        List<ProductRecptLineDO> lines = productRecptLineService.selectWmProductRecptLineList(param);
        if(CollUtil.isEmpty(lines)){
            return error(ErrorCodeConstants.PRODUCT_RECPT_NEEDED_LINE);
        }

        List<ProductRecptTxBean> beans = productRecptService.getTxBean(recptId);
        storageCoreService.processProductRecpt(beans);

        recpt.setStatus(Constant.ORDER_STATUS_FINISHED);

        ProductRecptUpdateReqVO updateReqVO = ProductRecptConvert.INSTANCE.convert01(recpt);
        productRecptService.updateProductRecpt(updateReqVO);

        return success(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品入库录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:query')")
    public CommonResult<List<ProductRecptRespVO>> getProductRecptList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductRecptDO> list = productRecptService.getProductRecptList(ids);
        return success(ProductRecptConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品入库录分页")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:query')")
    public CommonResult<PageResult<ProductRecptRespVO>> getProductRecptPage(@Valid ProductRecptPageReqVO pageVO) {
        PageResult<ProductRecptDO> pageResult = productRecptService.getProductRecptPage(pageVO);
        return success(ProductRecptConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品入库录 Excel")
    @PreAuthorize("@ss.hasPermission('wms:product-recpt:export')")
    @OperateLog(type = EXPORT)
    public void exportProductRecptExcel(@Valid ProductRecptExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductRecptDO> list = productRecptService.getProductRecptList(exportReqVO);
        // 导出 Excel
        List<ProductRecptExcelVO> datas = ProductRecptConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品入库录.xls", "数据", ProductRecptExcelVO.class, datas);
    }

}
