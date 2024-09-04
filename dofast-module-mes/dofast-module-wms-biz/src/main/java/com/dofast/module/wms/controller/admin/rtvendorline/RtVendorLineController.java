package com.dofast.module.wms.controller.admin.rtvendorline;

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

import com.dofast.module.wms.controller.admin.rtvendorline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendorline.RtVendorLineDO;
import com.dofast.module.wms.convert.rtvendorline.RtVendorLineConvert;
import com.dofast.module.wms.service.rtvendorline.RtVendorLineService;

@Tag(name = "仓储管理 - 供应商退货行")
@RestController
@RequestMapping("/mes/wms/rt-vendor-line")
@Validated
public class RtVendorLineController {

    @Resource
    private RtVendorLineService rtVendorLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建供应商退货行")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:create')")
    public CommonResult<Long> createRtVendorLine(@Valid @RequestBody RtVendorLineCreateReqVO createReqVO) {
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
        return success(rtVendorLineService.createRtVendorLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新供应商退货行")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:update')")
    public CommonResult<Boolean> updateRtVendorLine(@Valid @RequestBody RtVendorLineUpdateReqVO updateReqVO) {
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
        rtVendorLineService.updateRtVendorLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除供应商退货行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:delete')")
    public CommonResult<Boolean> deleteRtVendorLine(@RequestParam("id") Long id) {
        rtVendorLineService.deleteRtVendorLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得供应商退货行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:query')")
    public CommonResult<RtVendorLineRespVO> getRtVendorLine(@RequestParam("id") Long id) {
        RtVendorLineDO rtVendorLine = rtVendorLineService.getRtVendorLine(id);
        return success(RtVendorLineConvert.INSTANCE.convert(rtVendorLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得供应商退货行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:query')")
    public CommonResult<List<RtVendorLineRespVO>> getRtVendorLineList(@RequestParam("ids") Collection<Long> ids) {
        List<RtVendorLineDO> list = rtVendorLineService.getRtVendorLineList(ids);
        return success(RtVendorLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得供应商退货行分页")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:query')")
    public CommonResult<PageResult<RtVendorLineRespVO>> getRtVendorLinePage(@Valid RtVendorLinePageReqVO pageVO) {
        PageResult<RtVendorLineDO> pageResult = rtVendorLineService.getRtVendorLinePage(pageVO);
        return success(RtVendorLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出供应商退货行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:rt-vendor-line:export')")
    @OperateLog(type = EXPORT)
    public void exportRtVendorLineExcel(@Valid RtVendorLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RtVendorLineDO> list = rtVendorLineService.getRtVendorLineList(exportReqVO);
        // 导出 Excel
        List<RtVendorLineExcelVO> datas = RtVendorLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "供应商退货行.xls", "数据", RtVendorLineExcelVO.class, datas);
    }

}
