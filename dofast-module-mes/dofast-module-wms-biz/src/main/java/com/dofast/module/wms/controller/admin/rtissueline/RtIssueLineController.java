package com.dofast.module.wms.controller.admin.rtissueline;

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

import com.dofast.module.wms.controller.admin.rtissueline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.module.wms.convert.rtissueline.RtIssueLineConvert;
import com.dofast.module.wms.service.rtissueline.RtIssueLineService;

@Tag(name = "仓储管理 - 生产退料单行")
@RestController
@RequestMapping("/mes/wms/rt-issue-line")
@Validated
public class RtIssueLineController {

    @Resource
    private RtIssueLineService rtIssueLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建生产退料单行")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:create')")
    public CommonResult<Long> createRtIssueLine(@Valid @RequestBody RtIssueLineCreateReqVO createReqVO) {

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
        return success(rtIssueLineService.createRtIssueLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产退料单行")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:update')")
    public CommonResult<Boolean> updateRtIssueLine(@Valid @RequestBody RtIssueLineUpdateReqVO updateReqVO) {
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
        rtIssueLineService.updateRtIssueLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产退料单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:delete')")
    public CommonResult<Boolean> deleteRtIssueLine(@RequestParam("id") Long id) {
        rtIssueLineService.deleteRtIssueLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产退料单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:query')")
    public CommonResult<RtIssueLineRespVO> getRtIssueLine(@RequestParam("id") Long id) {
        RtIssueLineDO rtIssueLine = rtIssueLineService.getRtIssueLine(id);
        return success(RtIssueLineConvert.INSTANCE.convert(rtIssueLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产退料单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:query')")
    public CommonResult<List<RtIssueLineRespVO>> getRtIssueLineList(@RequestParam("ids") Collection<Long> ids) {
        List<RtIssueLineDO> list = rtIssueLineService.getRtIssueLineList(ids);
        return success(RtIssueLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产退料单行分页")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:query')")
    public CommonResult<PageResult<RtIssueLineRespVO>> getRtIssueLinePage(@Valid RtIssueLinePageReqVO pageVO) {
        PageResult<RtIssueLineDO> pageResult = rtIssueLineService.getRtIssueLinePage(pageVO);
        return success(RtIssueLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产退料单行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue-line:export')")
    @OperateLog(type = EXPORT)
    public void exportRtIssueLineExcel(@Valid RtIssueLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RtIssueLineDO> list = rtIssueLineService.getRtIssueLineList(exportReqVO);
        // 导出 Excel
        List<RtIssueLineExcelVO> datas = RtIssueLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产退料单行.xls", "数据", RtIssueLineExcelVO.class, datas);
    }

}
