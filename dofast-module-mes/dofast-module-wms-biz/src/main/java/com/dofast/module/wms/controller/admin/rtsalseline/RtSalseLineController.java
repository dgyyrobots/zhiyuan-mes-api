package com.dofast.module.wms.controller.admin.rtsalseline;

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

import com.dofast.module.wms.controller.admin.rtsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalseline.RtSalseLineDO;
import com.dofast.module.wms.convert.rtsalseline.RtSalseLineConvert;
import com.dofast.module.wms.service.rtsalseline.RtSalseLineService;

@Tag(name = "仓储管理 - 产品销售退货行")
@RestController
@RequestMapping("/mes/wms/rt-salse-line")
@Validated
public class RtSalseLineController {

    @Resource
    private RtSalseLineService rtSalseLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;


    @PostMapping("/create")
    @Operation(summary = "创建产品销售退货行")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:create')")
    public CommonResult<Long> createRtSalseLine(@Valid @RequestBody RtSalseLineCreateReqVO createReqVO) {

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

        return success(rtSalseLineService.createRtSalseLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品销售退货行")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:update')")
    public CommonResult<Boolean> updateRtSalseLine(@Valid @RequestBody RtSalseLineUpdateReqVO updateReqVO) {
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
        rtSalseLineService.updateRtSalseLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品销售退货行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:delete')")
    public CommonResult<Boolean> deleteRtSalseLine(@RequestParam("id") Long id) {
        rtSalseLineService.deleteRtSalseLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品销售退货行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:query')")
    public CommonResult<RtSalseLineRespVO> getRtSalseLine(@RequestParam("id") Long id) {
        RtSalseLineDO rtSalseLine = rtSalseLineService.getRtSalseLine(id);
        return success(RtSalseLineConvert.INSTANCE.convert(rtSalseLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品销售退货行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:query')")
    public CommonResult<List<RtSalseLineRespVO>> getRtSalseLineList(@RequestParam("ids") Collection<Long> ids) {
        List<RtSalseLineDO> list = rtSalseLineService.getRtSalseLineList(ids);
        return success(RtSalseLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品销售退货行分页")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:query')")
    public CommonResult<PageResult<RtSalseLineRespVO>> getRtSalseLinePage(@Valid RtSalseLinePageReqVO pageVO) {
        PageResult<RtSalseLineDO> pageResult = rtSalseLineService.getRtSalseLinePage(pageVO);
        return success(RtSalseLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品销售退货行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse-line:export')")
    @OperateLog(type = EXPORT)
    public void exportRtSalseLineExcel(@Valid RtSalseLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RtSalseLineDO> list = rtSalseLineService.getRtSalseLineList(exportReqVO);
        // 导出 Excel
        List<RtSalseLineExcelVO> datas = RtSalseLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品销售退货行.xls", "数据", RtSalseLineExcelVO.class, datas);
    }

}
