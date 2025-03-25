package com.dofast.module.wms.controller.admin.warehouse;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.utils.WmsBarCodeUtil;
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

import com.dofast.module.wms.controller.admin.warehouse.vo.*;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.convert.warehouse.WarehouseConvert;
import com.dofast.module.wms.service.warehouse.WarehouseService;

@Tag(name = "管理后台 - 仓库")
@RestController
@RequestMapping("/wms/warehouse")
@Validated
public class WarehouseController {

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private WmsBarCodeUtil wmsBarCodeUtil;

    /**
     * 查询树型的列表
     * @return
     */
    @Operation(summary = "查询树型的列表")
    @GetMapping("/getTreeList")
    public CommonResult getTreeList(){
        List<WarehouseDO> list = warehouseService.getTreeList();
        System.out.println(list);
        return success(list);
    }

    @PostMapping("/create")
    @Operation(summary = "创建仓库")
    @PreAuthorize("@ss.hasPermission('wms:warehouse:create')")
    public CommonResult<Long> createWarehouse(@Valid @RequestBody WarehouseCreateReqVO createReqVO) throws IOException{
        if(Constant.NOT_UNIQUE.equals(warehouseService.checkWarehouseCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.WAREHOUSE_CODE_EXISTS);
        }
        if(Constant.NOT_UNIQUE.equals(warehouseService.checkWarehouseNameUnique(createReqVO))){
            return error(ErrorCodeConstants.WAREHOUSE_NAME_EXISTS);
        }
        warehouseService.createWarehouse(createReqVO);
        wmsBarCodeUtil.generateBarCode(Constant.BARCODE_TYPE_WAREHOUSE,createReqVO.getId(),createReqVO.getWarehouseCode(),createReqVO.getWarehouseName());
        return success(createReqVO.getId());
    }

    @PutMapping("/update")
    @Operation(summary = "更新仓库")
    @PreAuthorize("@ss.hasPermission('wms:warehouse:update')")
    public CommonResult<Boolean> updateWarehouse(@Valid @RequestBody WarehouseUpdateReqVO updateReqVO) {
        warehouseService.updateWarehouse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除仓库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:warehouse:delete')")
    public CommonResult<Boolean> deleteWarehouse(@RequestParam("id") Long id) {
        storageAreaService.deleteByWarehouseId(id);
        storageLocationService.deleteByWarehouseId(id);
        warehouseService.deleteWarehouse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得仓库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:warehouse:query')")
    public CommonResult<WarehouseRespVO> getWarehouse(@RequestParam("id") Long id) {
        WarehouseDO warehouse = warehouseService.getWarehouse(id);
        return success(WarehouseConvert.INSTANCE.convert(warehouse));
    }

    @GetMapping("/list")
    @Operation(summary = "获得仓库列表")
    @Parameter(name = "ids", description = "编号列表", required = false, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:warehouse:query')")
    public CommonResult<List<WarehouseRespVO>> getWarehouseList(@RequestParam(name = "ids", required = false) Collection<Long> ids) {
        List<WarehouseDO> list ;
        if (ids == null) {
            list = warehouseService.getWarehouseList(new WarehouseExportReqVO());
        } else {
            list = warehouseService.getWarehouseList(ids);
        }
        return success(WarehouseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得仓库分页")
    @PreAuthorize("@ss.hasPermission('wms:warehouse:query')")
    public CommonResult<PageResult<WarehouseRespVO>> getWarehousePage(@Valid WarehousePageReqVO pageVO) {
        PageResult<WarehouseDO> pageResult = warehouseService.getWarehousePage(pageVO);
        return success(WarehouseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出仓库 Excel")
    @PreAuthorize("@ss.hasPermission('wms:warehouse:export')")
    @OperateLog(type = EXPORT)
    public void exportWarehouseExcel(@Valid WarehouseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WarehouseDO> list = warehouseService.getWarehouseList(exportReqVO);
        // 导出 Excel
        List<WarehouseExcelVO> datas = WarehouseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "仓库.xls", "数据", WarehouseExcelVO.class, datas);
    }

}
