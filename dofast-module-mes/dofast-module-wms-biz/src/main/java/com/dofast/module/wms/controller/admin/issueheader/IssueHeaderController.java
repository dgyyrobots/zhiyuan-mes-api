package com.dofast.module.wms.controller.admin.issueheader;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.issueline.vo.IssueLineListVO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueTxBean;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.issueline.IssueLineMapper;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.issueline.IssueLineService;
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

import com.dofast.module.wms.controller.admin.issueheader.vo.*;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.convert.issueheader.IssueHeaderConvert;
import com.dofast.module.wms.service.issueheader.IssueHeaderService;

@Tag(name = "管理后台 - 生产领料单头")
@RestController
@RequestMapping("/wms/issue-header")
@Validated
public class IssueHeaderController {

    @Resource
    private IssueHeaderService issueHeaderService;
    @Resource
    private IssueLineService issueLineService;

    @Resource
    private IssueLineMapper issuLineMapper;


    @Resource
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private StorageCoreService storageCoreServicel;

    @PostMapping("/create")
    @Operation(summary = "创建生产领料单头")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:create')")
    public CommonResult<Long> createIssueHeader(@Valid @RequestBody IssueHeaderCreateReqVO createReqVO) {
        if (Constant.NOT_UNIQUE.equals(issueHeaderService.checkIssueCodeUnique(createReqVO))) {
            return error(ErrorCodeConstants.ISSUE_HEADER_CDOE_EXISTS);
        }

        //根据领料单头上的仓库、库区、库位ID设置对应的编号和名称
        if (StrUtils.isNotNull(createReqVO.getWarehouseId())) {
            WarehouseDO warehouseDO = warehouseService.getWarehouse(createReqVO.getWarehouseId());
            createReqVO.setWarehouseCode(warehouseDO.getWarehouseCode());
            createReqVO.setWarehouseName(warehouseDO.getWarehouseName());
        }
        if (StrUtils.isNotNull(createReqVO.getLocationId())) {
            StorageLocationDO storageLocationDO = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            createReqVO.setLocationCode(storageLocationDO.getLocationCode());
            createReqVO.setLocationName(storageLocationDO.getLocationName());
        }
        if (StrUtils.isNotNull(createReqVO.getAreaId())) {
            StorageAreaDO storageAreaDO = storageAreaService.getStorageArea(createReqVO.getAreaId());
            createReqVO.setAreaCode(storageAreaDO.getAreaCode());
            createReqVO.setAreaName(storageAreaDO.getAreaName());
        }

        return success(issueHeaderService.createIssueHeader(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产领料单头")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:update')")
    public CommonResult<Boolean> updateIssueHeader(@Valid @RequestBody IssueHeaderUpdateReqVO updateReqVO) {
        if (Constant.NOT_UNIQUE.equals(issueHeaderService.checkIssueCodeUnique(updateReqVO))) {
            return error(ErrorCodeConstants.ISSUE_HEADER_CDOE_EXISTS);
        }
        if (StrUtils.isNotNull(updateReqVO.getWarehouseId())) {
            WarehouseDO warehouse = warehouseService.getWarehouse(updateReqVO.getWarehouseId());
            updateReqVO.setWarehouseCode(warehouse.getWarehouseCode());
            updateReqVO.setWarehouseName(warehouse.getWarehouseName());
        }
        if (StrUtils.isNotNull(updateReqVO.getLocationId())) {
            StorageLocationDO location = storageLocationService.getStorageLocation(updateReqVO.getLocationId());
            updateReqVO.setLocationCode(location.getLocationCode());
            updateReqVO.setLocationName(location.getLocationName());
        }
        if (StrUtils.isNotNull(updateReqVO.getAreaId())) {
            StorageAreaDO area = storageAreaService.getStorageArea(updateReqVO.getAreaId());
            updateReqVO.setAreaCode(area.getAreaCode());
            updateReqVO.setAreaName(area.getAreaName());
        }
        issueHeaderService.updateIssueHeader(updateReqVO);
        return success(true);
    }

    /**
     * 执行出库
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermission('wms:issue-header:update')")
    @Transactional
    @PutMapping("/{issueId}")
    @Operation(summary = "执行出库")
    public CommonResult execute(@PathVariable Long issueId) {
        IssueHeaderDO header = issueHeaderService.getIssueHeader(issueId);
        IssueLineListVO param = new IssueLineListVO();
        param.setIssueId(issueId);
        param.setStatus("N");
        List<IssueLineDO> lines = issueLineService.selectList(param);
        if (CollUtil.isEmpty(lines)) {
            return error(ErrorCodeConstants.ISSUE_HEADER_NEED_LINE);
        }

        List<IssueTxBean> beans = issueHeaderService.getTxBeans(issueId);

        //调用库存核心
        storageCoreServicel.processIssue(beans);

        //更新单据状态
        header.setStatus(Constant.ORDER_STATUS_CONFIRMED); // 先修改为已确认, 当报工后将领料单转为完成
        IssueHeaderUpdateReqVO updateReqVO = IssueHeaderConvert.INSTANCE.convert01(header);
        issueHeaderService.updateIssueHeader(updateReqVO);

        // 将当前单身状态改为已领料
        for (IssueLineDO line : lines) {
            line.setStatus("Y"); // 已领料
        }
        issuLineMapper.updateBatch(lines);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产领料单头")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:issue-header:delete')")
    public CommonResult<Boolean> deleteIssueHeader(@RequestParam("id") Long id) {
        IssueHeaderDO header = issueHeaderService.getIssueHeader(id);
        if (!Constant.ORDER_STATUS_PREPARE.equals(header.getStatus())) {
            return error(ErrorCodeConstants.ISSUE_HEADER_NOT_DELETED);
        }
        issueLineService.deleteByIssueHeaderId(id);
        issueHeaderService.deleteIssueHeader(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产领料单头")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:query')")
    public CommonResult<IssueHeaderRespVO> getIssueHeader(@RequestParam("id") Long id) {
        IssueHeaderDO issueHeader = issueHeaderService.getIssueHeader(id);
        // 基于当前工单信息带出工单BOM
        // 工单BOM当前只允许在ERP修改, 故前端页面禁止变更
        List<Map<String, Object>> bomList = issueHeaderService.initBomByWorkOrder(issueHeader.getWorkorderCode());
        //issueHeader.setBomList(bomList);
        IssueHeaderRespVO respVO = IssueHeaderConvert.INSTANCE.convert(issueHeader);
        //respVO.setBomList(bomList);
        return success(respVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产领料单头列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:query')")
    public CommonResult<List<IssueHeaderRespVO>> getIssueHeaderList(@RequestParam("ids") Collection<Long> ids) {
        List<IssueHeaderDO> list = issueHeaderService.getIssueHeaderList(ids);
        return success(IssueHeaderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产领料单头分页")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:query')")
    public CommonResult<PageResult<IssueHeaderRespVO>> getIssueHeaderPage(@Valid IssueHeaderPageReqVO pageVO) {
        PageResult<IssueHeaderDO> pageResult = issueHeaderService.getIssueHeaderPage(pageVO);
        return success(IssueHeaderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产领料单头 Excel")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:export')")
    @OperateLog(type = EXPORT)
    public void exportIssueHeaderExcel(@Valid IssueHeaderExportReqVO exportReqVO,
                                       HttpServletResponse response) throws IOException {
        List<IssueHeaderDO> list = issueHeaderService.getIssueHeaderList(exportReqVO);
        // 导出 Excel
        List<IssueHeaderExcelVO> datas = IssueHeaderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产领料单头.xls", "数据", IssueHeaderExcelVO.class, datas);
    }

}
