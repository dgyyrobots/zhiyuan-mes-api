package com.dofast.module.wms.controller.admin.rtissue;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.rtissueline.vo.RtIssueLineListVO;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueTxBean;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.rtissueline.RtIssueLineService;
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

import com.dofast.module.wms.controller.admin.rtissue.vo.*;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueDO;
import com.dofast.module.wms.convert.rtissue.RtIssueConvert;
import com.dofast.module.wms.service.rtissue.RtIssueService;

@Tag(name = "仓储管理 - 生产退料单头")
@RestController
@RequestMapping("/mes/wms/rt-issue")
@Validated
public class RtIssueController {

    @Resource
    private RtIssueService rtIssueService;

    @Resource
    private RtIssueLineService rtIssueLineService;

    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private StorageCoreService storageCoreService;

    @PostMapping("/create")
    @Operation(summary = "创建生产退料单头")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:create')")
    public CommonResult<Long> createRtIssue(@Valid @RequestBody RtIssueCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtIssueService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.RT_ISSUE_CODE_EXISTS);
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
        return success(rtIssueService.createRtIssue(createReqVO));
    }

    /**
     * 执行退料
     * @param rtId
     * @return
     */
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:update')")
    @Transactional
    @PutMapping("/{rtId}")
    public CommonResult execute(@PathVariable Long rtId){
        RtIssueDO rtIssue = rtIssueService.getRtIssue(rtId);
        RtIssueLineListVO param = new RtIssueLineListVO();
        param.setRtId(rtId);
        List<RtIssueLineDO> lines = rtIssueLineService.selectList(param);
        if(CollUtil.isEmpty(lines)){
            return error(ErrorCodeConstants.RT_ISSUE_NEED_MAT);
        }

        List<RtIssueTxBean> beans = rtIssueService.getTxBeans(rtId);

        //执行生产退料
        storageCoreService.processRtIssue(beans);


        rtIssue.setStatus(Constant.ORDER_STATUS_FINISHED);

        RtIssueUpdateReqVO updateReqVO = RtIssueConvert.INSTANCE.convert01(rtIssue);
        rtIssueService.updateRtIssue(updateReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产退料单头")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:update')")
    public CommonResult<Boolean> updateRtIssue(@Valid @RequestBody RtIssueUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtIssueService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.RT_ISSUE_CODE_EXISTS);
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
        rtIssueService.updateRtIssue(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产退料单头")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:delete')")
    public CommonResult<Boolean> deleteRtIssue(@RequestParam("id") Long id) {
        rtIssueLineService.deleteByRtId(id);
        rtIssueService.deleteRtIssue(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产退料单头")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:query')")
    public CommonResult<RtIssueRespVO> getRtIssue(@RequestParam("id") Long id) {
        RtIssueDO rtIssue = rtIssueService.getRtIssue(id);
        return success(RtIssueConvert.INSTANCE.convert(rtIssue));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产退料单头列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:query')")
    public CommonResult<List<RtIssueRespVO>> getRtIssueList(@RequestParam("ids") Collection<Long> ids) {
        List<RtIssueDO> list = rtIssueService.getRtIssueList(ids);
        return success(RtIssueConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产退料单头分页")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:query')")
    public CommonResult<PageResult<RtIssueRespVO>> getRtIssuePage(@Valid RtIssuePageReqVO pageVO) {
        PageResult<RtIssueDO> pageResult = rtIssueService.getRtIssuePage(pageVO);
        return success(RtIssueConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产退料单头 Excel")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:export')")
    @OperateLog(type = EXPORT)
    public void exportRtIssueExcel(@Valid RtIssueExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RtIssueDO> list = rtIssueService.getRtIssueList(exportReqVO);
        // 导出 Excel
        List<RtIssueExcelVO> datas = RtIssueConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产退料单头.xls", "数据", RtIssueExcelVO.class, datas);
    }

}
