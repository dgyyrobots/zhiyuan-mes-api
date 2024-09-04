package com.dofast.module.wms.controller.admin.rtsalse;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.rtsalseline.vo.RtSalseLineListVO;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseTxBean;
import com.dofast.module.wms.dal.dataobject.rtsalseline.RtSalseLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.rtsalseline.RtSalseLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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

import com.dofast.module.wms.controller.admin.rtsalse.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalse.RtSalseDO;
import com.dofast.module.wms.convert.rtsalse.RtSalseConvert;
import com.dofast.module.wms.service.rtsalse.RtSalseService;

@Tag(name = "仓储管理 - 产品销售退货单")
@RestController
@RequestMapping("/mes/wms/rt-salse")
@Validated
public class RtSalseController {

    @Resource
    private RtSalseService rtSalseService;

    @Resource
    private RtSalseLineService rtSalseLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private StorageCoreService storageCoreService;

    @PostMapping("/create")
    @Operation(summary = "创建产品销售退货单")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:create')")
    public CommonResult<Long> createRtSalse(@Valid @RequestBody RtSalseCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtSalseService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.RT_SALSE_CODE_EXISTS);
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
        return success(rtSalseService.createRtSalse(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品销售退货单")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:update')")
    public CommonResult<Boolean> updateRtSalse(@Valid @RequestBody RtSalseUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtSalseService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.RT_SALSE_CODE_EXISTS);
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
        rtSalseService.updateRtSalse(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品销售退货单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:delete')")
    public CommonResult<Boolean> deleteRtSalse(@RequestParam("id") Long id) {
        rtSalseLineService.deleteByRtId(id);
        rtSalseService.deleteRtSalse(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品销售退货单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:query')")
    public CommonResult<RtSalseRespVO> getRtSalse(@RequestParam("id") Long id) {
        RtSalseDO rtSalse = rtSalseService.getRtSalse(id);
        return success(RtSalseConvert.INSTANCE.convert(rtSalse));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品销售退货单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:query')")
    public CommonResult<List<RtSalseRespVO>> getRtSalseList(@RequestParam("ids") Collection<Long> ids) {
        List<RtSalseDO> list = rtSalseService.getRtSalseList(ids);
        return success(RtSalseConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品销售退货单分页")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:query')")
    public CommonResult<PageResult<RtSalseRespVO>> getRtSalsePage(@Valid RtSalsePageReqVO pageVO) {
        PageResult<RtSalseDO> pageResult = rtSalseService.getRtSalsePage(pageVO);
        return success(RtSalseConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品销售退货单 Excel")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:export')")
    @OperateLog(type = EXPORT)
    public void exportRtSalseExcel(@Valid RtSalseExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RtSalseDO> list = rtSalseService.getRtSalseList(exportReqVO);
        // 导出 Excel
        List<RtSalseExcelVO> datas = RtSalseConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品销售退货单.xls", "数据", RtSalseExcelVO.class, datas);
    }

    /**
     * 执行退货
     * @param rtId
     * @return
     */
    @Operation(summary = "执行退货")
    @PreAuthorize("@ss.hasPermission('wms:rt-salse:update')")
    @Transactional
    @PutMapping("/{rtId}")
    public CommonResult execute(@PathVariable Long rtId){
        RtSalseDO rtSalse = rtSalseService.getRtSalse(rtId);
        RtSalseLineListVO param = new RtSalseLineListVO();
        param.setRtId(rtId);
        List<RtSalseLineDO> lines = rtSalseLineService.selectWmRtSalseLineList(param);
        if(CollectionUtils.isEmpty(lines)){
            return error(ErrorCodeConstants.RT_SALSE_NEED_LINE);
        }

        List<RtSalseTxBean> beans = rtSalseService.getTxBeans(rtId);

        storageCoreService.processRtSalse(beans);

        rtSalse.setStatus(Constant.ORDER_STATUS_FINISHED);

        RtSalseUpdateReqVO updateReqVO = RtSalseConvert.INSTANCE.convert01(rtSalse);
        rtSalseService.updateRtSalse(updateReqVO);
        return success(true);
    }

}
