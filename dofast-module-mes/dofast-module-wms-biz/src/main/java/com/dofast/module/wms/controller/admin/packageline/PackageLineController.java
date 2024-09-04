package com.dofast.module.wms.controller.admin.packageline;

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

import com.dofast.module.wms.controller.admin.packageline.vo.*;
import com.dofast.module.wms.dal.dataobject.packageline.PackageLineDO;
import com.dofast.module.wms.convert.packageline.PackageLineConvert;
import com.dofast.module.wms.service.packageline.PackageLineService;

@Tag(name = "仓储管理 - 装箱明细")
@RestController
@RequestMapping("/mes/wms/package-line")
@Validated
public class PackageLineController {

    @Resource
    private PackageLineService packageLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建装箱明细")
    @PreAuthorize("@ss.hasPermission('wms:package-line:create')")
    public CommonResult<Long> createPackageLine(@Valid @RequestBody PackageLineCreateReqVO createReqVO) {
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
        return success(packageLineService.createPackageLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新装箱明细")
    @PreAuthorize("@ss.hasPermission('wms:package-line:update')")
    public CommonResult<Boolean> updatePackageLine(@Valid @RequestBody PackageLineUpdateReqVO updateReqVO) {
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
        packageLineService.updatePackageLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除装箱明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:package-line:delete')")
    public CommonResult<Boolean> deletePackageLine(@RequestParam("id") Long id) {
        packageLineService.deletePackageLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得装箱明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:package-line:query')")
    public CommonResult<PackageLineRespVO> getPackageLine(@RequestParam("id") Long id) {
        PackageLineDO packageLine = packageLineService.getPackageLine(id);
        return success(PackageLineConvert.INSTANCE.convert(packageLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得装箱明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:package-line:query')")
    public CommonResult<List<PackageLineRespVO>> getPackageLineList(@RequestParam("ids") Collection<Long> ids) {
        List<PackageLineDO> list = packageLineService.getPackageLineList(ids);
        return success(PackageLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得装箱明细分页")
    @PreAuthorize("@ss.hasPermission('wms:package-line:query')")
    public CommonResult<PageResult<PackageLineRespVO>> getPackageLinePage(@Valid PackageLinePageReqVO pageVO) {
        PageResult<PackageLineDO> pageResult = packageLineService.getPackageLinePage(pageVO);
        return success(PackageLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出装箱明细 Excel")
    @PreAuthorize("@ss.hasPermission('wms:package-line:export')")
    @OperateLog(type = EXPORT)
    public void exportPackageLineExcel(@Valid PackageLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PackageLineDO> list = packageLineService.getPackageLineList(exportReqVO);
        // 导出 Excel
        List<PackageLineExcelVO> datas = PackageLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "装箱明细.xls", "数据", PackageLineExcelVO.class, datas);
    }

}
