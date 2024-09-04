package com.dofast.module.wms.controller.admin.storagelocation;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.utils.WmsBarCodeUtil;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import com.dofast.module.wms.controller.admin.storagelocation.vo.*;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.convert.storagelocation.StorageLocationConvert;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;

@Tag(name = "仓储管理 - 库区")
@RestController
@RequestMapping("/mes/wms/storage-location")
@Validated
public class StorageLocationController {

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    StorageAreaService storageAreaService;

    @Resource
    WmsBarCodeUtil wmsBarCodeUtil;

    @PostMapping("/create")
    @Operation(summary = "创建库区")
    @PreAuthorize("@ss.hasPermission('wms:storage-location:create')")
    public CommonResult<Long> createStorageLocation(@Valid @RequestBody StorageLocationCreateReqVO createReqVO) throws IOException {
        if(Constant.NOT_UNIQUE.equals(storageLocationService.checkLocationCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.STORAGE_LOCATION_CODE_EXISTS);
        }
        if(Constant.NOT_UNIQUE.equals(storageLocationService.checkLocationNameUnique(createReqVO))){
            return error(ErrorCodeConstants.STORAGE_LOCATION_NAME_EXISTS);
        }
        storageLocationService.createStorageLocation(createReqVO);
        wmsBarCodeUtil.generateBarCode(Constant.BARCODE_TYPE_WAREHOUSE,createReqVO.getId(),createReqVO.getLocationCode(),createReqVO.getLocationName());
        return success(createReqVO.getId());
    }

    @PutMapping("/update")
    @Operation(summary = "更新库区")
    @PreAuthorize("@ss.hasPermission('wms:storage-location:update')")
    public CommonResult<Boolean> updateStorageLocation(@Valid @RequestBody StorageLocationUpdateReqVO updateReqVO) {
        storageLocationService.updateStorageLocation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除库区")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:storage-location:delete')")
    public CommonResult<Boolean> deleteStorageLocation(@RequestParam("id") Long id) {
        storageAreaService.deleteByLocationId(id);
        storageLocationService.deleteStorageLocation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库区")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:storage-location:query')")
    public CommonResult<StorageLocationRespVO> getStorageLocation(@RequestParam("id") Long id) {
        StorageLocationDO storageLocation = storageLocationService.getStorageLocation(id);
        return success(StorageLocationConvert.INSTANCE.convert(storageLocation));
    }

    @GetMapping("/list")
    @Operation(summary = "获得库区列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:storage-location:query')")
    public CommonResult<List<StorageLocationRespVO>> getStorageLocationList(@RequestParam("ids") Collection<Long> ids) {
        List<StorageLocationDO> list = storageLocationService.getStorageLocationList(ids);
        return success(StorageLocationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得库区分页")
    @PreAuthorize("@ss.hasPermission('wms:storage-location:query')")
    public CommonResult<PageResult<StorageLocationRespVO>> getStorageLocationPage(@Valid StorageLocationPageReqVO pageVO) {
        PageResult<StorageLocationDO> pageResult = storageLocationService.getStorageLocationPage(pageVO);
        return success(StorageLocationConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出库区 Excel")
    @PreAuthorize("@ss.hasPermission('wms:storage-location:export')")
    @OperateLog(type = EXPORT)
    public void exportStorageLocationExcel(@Valid StorageLocationExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<StorageLocationDO> list = storageLocationService.getStorageLocationList(exportReqVO);
        // 导出 Excel
        List<StorageLocationExcelVO> datas = StorageLocationConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "库区.xls", "数据", StorageLocationExcelVO.class, datas);
    }

}
