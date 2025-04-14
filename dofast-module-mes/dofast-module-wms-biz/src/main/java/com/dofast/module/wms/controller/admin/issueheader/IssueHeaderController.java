package com.dofast.module.wms.controller.admin.issueheader;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.cmms.api.dvmachinery.DvMachineryApi;
import com.dofast.module.cmms.api.dvmachinery.dto.DvMachineryDTO;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;
import com.dofast.module.pro.api.TaskApi.TaskApi;
import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;
import com.dofast.module.wms.api.ERPApi.WorkorderERPAPI;
import com.dofast.module.wms.controller.admin.allocatedheader.vo.AllocatedHeaderExportReqVO;
import com.dofast.module.wms.controller.admin.allocatedheader.vo.AllocatedHeaderUpdateReqVO;
import com.dofast.module.wms.controller.admin.allocatedrecord.vo.AllocatedRecordExportReqVO;
import com.dofast.module.wms.controller.admin.feedline.vo.FeedLineExportReqVO;
import com.dofast.module.wms.controller.admin.issueline.vo.IssueLineListVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockUpdateReqVO;
import com.dofast.module.wms.convert.allocatedheader.AllocatedHeaderConvert;
import com.dofast.module.wms.dal.dataobject.allocatedheader.AllocatedHeaderDO;
import com.dofast.module.wms.dal.dataobject.allocatedrecord.AllocatedRecordDO;
import com.dofast.module.wms.dal.dataobject.feedline.FeedLineDO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueTxBean;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.issueline.IssueLineMapper;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.feedline.FeedLineService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.dofast.module.pro.api.ProcessApi.ProcessApi;


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

    @Resource
    private TaskApi taskApi;

    @Resource
    private DvMachineryApi dvMachineryApi;

    @Resource
    private ProcessApi processApi;

    @Resource
    private FeedLineService feedLineService;

    @Resource
    private WorkorderERPAPI workorderERPAPI;


    @PostMapping("/create")
    @Operation(summary = "创建生产领料单头")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:create')")
    public CommonResult<Long> createIssueHeader(@Valid @RequestBody IssueHeaderCreateReqVO createReqVO) {
        if (Constant.NOT_UNIQUE.equals(issueHeaderService.checkIssueCodeUnique(createReqVO))) {
            return error(ErrorCodeConstants.ISSUE_HEADER_CDOE_EXISTS);
        }

        // 校验当前的任务单是否已创建调拨单
        String taskCode = createReqVO.getTaskCode();
        IssueHeaderExportReqVO exportReqVO = new IssueHeaderExportReqVO();
        exportReqVO.setTaskCode(taskCode);
        List<IssueHeaderDO> issueHeaderList = issueHeaderService.getIssueHeaderList(exportReqVO);
        if(!issueHeaderList.isEmpty()){
            return error(ErrorCodeConstants.ISSUE_HEADER_TASK_EXISTS);
        }

        TaskDTO taskDTO = taskApi.getTask(taskCode);
        LocalDate localDate = LocalDate.now();
        String dateStr = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 生成3位随机数
        int random = (int) ((Math.random() * 9 + 1) * 100);
        createReqVO.setIssueName(taskDTO.getProcessName() + "领料单" + dateStr + random);

        ProcessDTO processDTO = processApi.getcess(createReqVO.getProcessCode());
        createReqVO.setProcessName(processDTO.getProcessName());

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

    @PutMapping("/updateMachinery")
    @Operation(summary = "更新生产领料单头")
    @PreAuthorize("@ss.hasPermission('wms:issue-header:update')")
    public CommonResult<Boolean> updateMachinery(@Valid @RequestBody IssueHeaderUpdateReqVO updateReqVO) {
        // 根据设备编码获取设备信息, 并更新当前的领料单头
        String machineryCode = updateReqVO.getMachineryCode();
        DvMachineryDTO machineryDTO = dvMachineryApi.getMachineryInfo(machineryCode);
        updateReqVO.setMachineryId(machineryDTO.getId());
        updateReqVO.setMachineryName(machineryDTO.getMachineryName());
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
        // 2025-1-17追加校验
        // 若当前单身存在已上料且报工单号为空, 禁止执行出库
        IssueLineListVO param = new IssueLineListVO();
        param.setIssueId(issueId);
        param.setStatus("Y");
        param.setFeedbackStatus("N");
        List<IssueLineDO> feedBacklines = issueLineService.selectList(param); // 当前领料单下已上料未报工的单身信息

        param.setStatus("N");
        param.setFeedbackStatus("N");
        List<IssueLineDO> noFeedBacklines = issueLineService.selectList(param); // 当前领料单下未上料未报工的单身信息

        if(!noFeedBacklines.isEmpty() && !feedBacklines.isEmpty()){
            for (IssueLineDO noFeedBackline : noFeedBacklines) {
                for (IssueLineDO feedBackline : feedBacklines) {
                    if(noFeedBackline.getMachineryCode().equals(feedBackline.getMachineryCode())){
                        // 两者中存在相同机台设备, 禁止出库
                        return error(ErrorCodeConstants.ISSUE_HEADER_NOT_FEEDBACK_EXISTS);
                    }
                }
            }
        }else if(!feedBacklines.isEmpty()){
            return error(ErrorCodeConstants.ISSUE_HEADER_NOT_FEEDBACK_EXISTS);
        }

        param.setStatus("N");// 防止重复上料
        List<IssueLineDO> lines = issueLineService.selectList(param); // 当前领料单身中为上料的单据信息
        if (lines.isEmpty()) {
            return error(ErrorCodeConstants.ISSUE_HEADER_NEED_LINE);
        }

        // 追加ERP接口调用
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>>  list = new ArrayList<>(); // 装填领料信息

        for (IssueLineDO issueLine: lines) {

            FeedLineExportReqVO exportReqVO = new FeedLineExportReqVO();
            exportReqVO.setTaskCode(header.getTaskCode());
            exportReqVO.setItemCode(issueLine.getItemCode());
            exportReqVO.setBatchCode(issueLine.getBatchCode());
            exportReqVO.setBarcodeNumber(issueLine.getBarcodeNumber());

            Map<String, Object> map = new HashMap<>();
            map.put("sfdc001", header.getWorkorderCode()); // 工单单号
            map.put("sfdc002", issueLine.getSequence()); // 工单项次
            map.put("sfdc003", issueLine.getSequenceOrder()); // 工单项序
            map.put("sfdc007", issueLine.getQuantityIssued()); // 申请数量
            map.put("sfdc012", issueLine.getLocationCode()); // ERP库区
            map.put("sfdc013", issueLine.getAreaCode()); // ERP库位
            map.put("sfdc014", issueLine.getBatchCode()); // 批号
            map.put("sfdc015", "H01"); // 理由码 成套发料对应H01，成套退料对应Y01，超领对应H02，超领退对应Y02
            map.put("sfdc016", ""); // 库存管理特征
            map.put("source_seq", ""); // MES项次
            list.add(map);
        }
        params.put("goodsList", list);
        params.put("sfda002", "11"); // 成套领料
        params.put("source_no", header.getIssueCode()); // 成套领料

        /*String erpResult = workorderERPAPI.workOrderIssueCreate(params);

        if(!erpResult.contains("success")){
            // 过账失败
            System.out.println("ERP过账失败：" + erpResult);
        }*/

        // 追加上料详情
        List<FeedLineDO> createReqVOList = new ArrayList<>();
        for (IssueLineDO issueLine : lines) {
            TaskDTO task = taskApi.getTask(header.getTaskId());
            FeedLineDO lineDO = new FeedLineDO();
            lineDO.setTaskCode(header.getTaskCode());
            lineDO.setTaskName(task.getTaskName());
            lineDO.setAreaName(issueLine.getAreaName());
            lineDO.setAreaCode(issueLine.getAreaCode());
            lineDO.setAreaId(issueLine.getAreaId());
            lineDO.setLocationCode(issueLine.getLocationCode());
            lineDO.setLocationName(issueLine.getLocationName());
            lineDO.setLocationId(issueLine.getLocationId());
            lineDO.setWarehouseCode(issueLine.getWarehouseCode());
            lineDO.setWarehouseName(issueLine.getWarehouseName());
            lineDO.setWarehouseId(issueLine.getWarehouseId());
            lineDO.setIssueId(issueId);
            lineDO.setMaterialStockId(issueLine.getMaterialStockId());
            lineDO.setItemId(issueLine.getItemId());
            lineDO.setItemCode(issueLine.getItemCode());
            lineDO.setItemName(issueLine.getItemName());
            lineDO.setSpecification(issueLine.getSpecification());
            lineDO.setUnitOfMeasure(issueLine.getUnitOfMeasure());
            lineDO.setQuantity(issueLine.getQuantityIssued().doubleValue());
            lineDO.setBatchCode(issueLine.getBatchCode());
            lineDO.setWorkorderCode(header.getWorkorderCode());
            lineDO.setWorkstationCode(header.getWorkstationCode());
            lineDO.setWorkstationName(header.getWorkstationName());
            lineDO.setVendorCode(issueLine.getVendorCode());
            lineDO.setStatus("Y");
            lineDO.setFeedbackStatus("N"); // 追加报工状态
            lineDO.setMachineryCode(issueLine.getMachineryCode());
            lineDO.setMachineryName(issueLine.getMachineryName());
            lineDO.setMachineryId(issueLine.getMachineryId());
            lineDO.setBarcodeNumber(issueLine.getBarcodeNumber());
            // 2025-04-03: 追加ERP项次, 项序用于领料接口
            lineDO.setSequence(issueLine.getSequence());
            lineDO.setSequenceOrder(issueLine.getSequenceOrder());
            createReqVOList.add(lineDO);
        }
        feedLineService.insertBatch(createReqVOList);

        List<IssueTxBean> beans = issueHeaderService.getTxBeans(issueId);

        //调用库存核心
        storageCoreServicel.processIssue(beans);

        //更新单据状态
        header.setStatus(Constant.ORDER_STATUS_CONFIRMED); // 先修改为已确认, 当报工后将领料单转为完成
        IssueHeaderUpdateReqVO updateReqVO = IssueHeaderConvert.INSTANCE.convert01(header);
        issueHeaderService.updateIssueHeader(updateReqVO);

        // 上料后库存进入线边仓, 将领料单单身物料位置同步到虚拟线边仓
        WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(Constant.VIRTUAL_WH);
        StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(Constant.VIRTUAL_WS);
        StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(Constant.VIRTUAL_WA);

        // 将当前单身状态改为已领料
        for (IssueLineDO line : lines) {
            line.setStatus("Y"); // 已领料
            line.setWarehouseId(warehouse.getId());
            line.setWarehouseCode(warehouse.getWarehouseCode());
            line.setWarehouseName(warehouse.getWarehouseName());
            line.setLocationId(location.getId());
            line.setLocationCode(location.getLocationCode());
            line.setLocationName(location.getLocationName());
            line.setAreaId(area.getId());
            line.setAreaCode(area.getAreaCode());
            line.setAreaName(area.getAreaName());
        }

        // 基于当前领料单对应的任务单获取设备信息
        /*TaskDTO task = taskApi.getTask(header.getTaskId());
        DvMachineryDTO dvMachineryDTO =  dvMachineryApi.getMachineryInfo(task.getMachineryCode());
        dvMachineryDTO.setStatus("WORKING"); //扫码上料视为生产中
        dvMachineryApi.updateMachineryInfo(dvMachineryDTO);*/
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



    /**
     * 完成领料
     * @param allocatedId
     * @return
     */
    @GetMapping("/finsh")
    @Operation(summary = "更新调拨单头")
    @PreAuthorize("@ss.hasPermission('wms:allocated-header:update')")
    public CommonResult<Boolean> finshIssueHeader(@RequestParam("id") Long allocatedId) {
        IssueHeaderDO issueHeader = issueHeaderService.getIssueHeader(allocatedId);
        if(!issueHeader.getStatus().equals(Constant.ORDER_STATUS_CONFIRMED)){
            return error(ErrorCodeConstants.ISSUE_HEADER_NO_ISSUE);
        }
        issueHeader.setStatus(Constant.ORDER_STATUS_FINISHED);
        issueHeaderService.updateIssueHeader(IssueHeaderConvert.INSTANCE.convert01(issueHeader));
        return success(true);
    }
}
