package com.dofast.module.mes.controller.admin.mdworkstation;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.mdworkstationmachine.MdWorkstationMachineController;
import com.dofast.module.mes.dal.dataobject.mdworkshop.MdWorkshopDO;
import com.dofast.module.mes.enums.ErrorCodeConstants;
import com.dofast.module.mes.service.mdworkshop.MdWorkshopService;
import com.dofast.module.mes.service.mdworkstationmachine.MdWorkstationMachineService;
import com.dofast.module.mes.service.mdworkstationtool.MdWorkstationToolService;
import com.dofast.module.mes.service.mdworkstationworker.MdWorkstationWorkerService;
import com.dofast.module.pro.api.ProcessApi.ProcessApi;
import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;
import com.dofast.module.wms.api.BarcodeApi.BarCodeUtil;
import com.dofast.module.wms.api.StorageAreaApi.StorageAreaApi;
import com.dofast.module.wms.api.StorageAreaApi.dto.StorageAreaDTO;
import com.dofast.module.wms.api.StorageLocationApi.StorageLocationApi;
import com.dofast.module.wms.api.StorageLocationApi.dto.StorageLocationDTO;
import com.dofast.module.wms.api.WarehosueApi.WarehouseApi;
import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
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

import com.dofast.module.mes.controller.admin.mdworkstation.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.convert.mdworkstation.MdWorkstationConvert;
import com.dofast.module.mes.service.mdworkstation.MdWorkstationService;

@Tag(name = "管理后台 - 工作站")
@RestController
@RequestMapping("/mes/md-workstation")
@Validated
public class MdWorkstationController {

    @Resource
    private MdWorkstationService mdWorkstationService;
    @Resource
    private MdWorkstationMachineService mdWorkstationMachineService;
    @Resource
    private MdWorkstationToolService mdWorkstationToolService;
    @Resource
    private MdWorkstationWorkerService mdWorkstationWorkerService;
    @Resource
    private MdWorkshopService mdWorkshopService;

    @Resource
    private ProcessApi processApi;

    @Resource
    private WarehouseApi warehouseApi;
    @Resource
    private StorageLocationApi storageLocationApi;
    @Resource
    private StorageAreaApi storageAreaApi;


    @Resource
    private BarCodeUtil barCodeUtil;

    @PostMapping("/create")
    @Operation(summary = "创建工作站")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:create')")
    public CommonResult<Long> createMdWorkstation(@Valid @RequestBody MdWorkstationCreateReqVO createReqVO)throws IOException {
        if(Constant.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_WORKSTATION_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationNameUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_WORKSTATION_NAME_NOT_UNIQUE);
        }


        ProcessDTO process = processApi.getcess(createReqVO.getProcessId());
        createReqVO.setProcessCode(process.getProcessCode());
        createReqVO.setProcessName(process.getProcessName());

        MdWorkshopDO workshop = mdWorkshopService.getMdWorkshop(createReqVO.getWorkshopId());
        createReqVO.setWorkshopCode(workshop.getWorkshopCode());
        createReqVO.setWorkshopName(workshop.getWorkshopName());

        //线边库的设置
        WarehouseDTO warehouse = null;
        StorageLocationDTO location = null;
        StorageAreaDTO area = null;
        if(StrUtils.isNotNull(createReqVO.getWarehouseId())){
            //如果有指定线边库
            warehouse = warehouseApi.getWarehouse(createReqVO.getWarehouseId());
            location = storageLocationApi.getLocation(createReqVO.getLocationId());
            area = storageAreaApi.getArea(createReqVO.getAreaId());
        }else {
            //设置默认的线边库
            warehouse = warehouseApi.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
            if(StrUtils.isNull(warehouse)){
                //如果没有找到默认的线边库，则进行一次初始化
                warehouse = warehouseApi.initVirtualWarehouse();
            }
            location = storageLocationApi.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
            area = storageAreaApi.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);
        }
        createReqVO.setWarehouseId(warehouse.getId());
        createReqVO.setWarehouseCode(warehouse.getWarehouseCode());
        createReqVO.setWarehouseName(warehouse.getWarehouseName());
        createReqVO.setLocationId(location.getId());
        createReqVO.setLocationCode(location.getLocationCode());
        createReqVO.setLocationName(location.getLocationName());
        createReqVO.setAreaId(area.getId());
        createReqVO.setAreaCode(area.getAreaCode());
        createReqVO.setAreaName(area.getAreaName());

        Long id = mdWorkstationService.createMdWorkstation(createReqVO);
        barCodeUtil.generateBarCode(Constant.BARCODE_TYPE_WORKSTATION,id,createReqVO.getWorkshopCode(),createReqVO.getWorkstationName());
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新工作站")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:update')")
    public CommonResult<Boolean> updateMdWorkstation(@Valid @RequestBody MdWorkstationUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_WORKSTATION_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdWorkstationService.checkWorkStationNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_WORKSTATION_NAME_NOT_UNIQUE);
        }

        ProcessDTO process = processApi.getcess(updateReqVO.getProcessId());
        updateReqVO.setProcessCode(process.getProcessCode());
        updateReqVO.setProcessName(process.getProcessName());

        MdWorkshopDO workshop = mdWorkshopService.getMdWorkshop(updateReqVO.getWorkshopId());
        updateReqVO.setWorkshopCode(workshop.getWorkshopCode());
        updateReqVO.setWorkshopName(workshop.getWorkshopName());
        mdWorkstationService.updateMdWorkstation(updateReqVO);

        //线边库的设置
        WarehouseDTO warehouse = null;
        StorageLocationDTO location = null;
        StorageAreaDTO area = null;
        if(StrUtils.isNotNull(updateReqVO.getWarehouseId())){
            //如果有指定线边库
            warehouse = warehouseApi.getWarehouse(updateReqVO.getWarehouseId());
            location = storageLocationApi.getLocation(updateReqVO.getLocationId());
            area = storageAreaApi.getArea(updateReqVO.getAreaId());
        }else {
            //设置默认的线边库
            warehouse = warehouseApi.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
            if(StrUtils.isNull(warehouse)){
                //如果没有找到默认的线边库，则进行一次初始化
                warehouse = warehouseApi.initVirtualWarehouse();
            }
            location = storageLocationApi.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
            area = storageAreaApi.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);
        }
        updateReqVO.setWarehouseCode(warehouse.getWarehouseCode());
        updateReqVO.setWarehouseName(warehouse.getWarehouseName());
        updateReqVO.setLocationCode(location.getLocationCode());
        updateReqVO.setLocationName(location.getLocationName());
        updateReqVO.setAreaCode(area.getAreaCode());
        updateReqVO.setAreaName(area.getAreaName());
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工作站")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:delete')")
    public CommonResult<Boolean> deleteMdWorkstation(@RequestParam("id") Long id) {
        mdWorkstationMachineService.deleteByWorkstationId(id);
        mdWorkstationToolService.deleteByWorkstationId(id);
        mdWorkstationWorkerService.deleteByWorkstationId(id);
        mdWorkstationService.deleteMdWorkstation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工作站")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:query')")
    public CommonResult<MdWorkstationRespVO> getMdWorkstation(@RequestParam("id") Long id) {
        MdWorkstationDO mdWorkstation = mdWorkstationService.getMdWorkstation(id);
        return success(MdWorkstationConvert.INSTANCE.convert(mdWorkstation));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工作站列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:query')")
    public CommonResult<List<MdWorkstationRespVO>> getMdWorkstationList(@RequestParam("ids") Collection<Long> ids) {
        List<MdWorkstationDO> list = mdWorkstationService.getMdWorkstationList(ids);
        return success(MdWorkstationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工作站分页")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:query')")
    public CommonResult<PageResult<MdWorkstationRespVO>> getMdWorkstationPage(@Valid MdWorkstationPageReqVO pageVO) {
        PageResult<MdWorkstationDO> pageResult = mdWorkstationService.getMdWorkstationPage(pageVO);
        return success(MdWorkstationConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工作站 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation:export')")
    @OperateLog(type = EXPORT)
    public void exportMdWorkstationExcel(@Valid MdWorkstationExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdWorkstationDO> list = mdWorkstationService.getMdWorkstationList(exportReqVO);
        // 导出 Excel
        List<MdWorkstationExcelVO> datas = MdWorkstationConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工作站.xls", "数据", MdWorkstationExcelVO.class, datas);
    }

}
