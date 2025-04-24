package com.dofast.module.wms.controller.admin.rtissue;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.api.FeedbackApi.FeedbackApi;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.TaskApi.TaskApi;
import com.dofast.module.pro.api.TaskApi.dto.TaskDTO;
import com.dofast.module.wms.api.ERPApi.WorkorderERPAPI;
import com.dofast.module.wms.controller.admin.feedline.vo.FeedLineExportReqVO;
import com.dofast.module.wms.controller.admin.issueheader.vo.IssueHeaderExportReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.controller.admin.rtissueline.vo.RtIssueLineCreateReqVO;
import com.dofast.module.wms.controller.admin.rtissueline.vo.RtIssueLineListVO;
import com.dofast.module.wms.dal.dataobject.feedline.FeedLineDO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.rtissue.RtIssueTxBean;
import com.dofast.module.wms.dal.dataobject.rtissueline.RtIssueLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.feedline.FeedLineService;
import com.dofast.module.wms.service.issueheader.IssueHeaderService;
import com.dofast.module.wms.service.issueline.IssueLineService;
import com.dofast.module.wms.service.materialstock.MaterialStockService;
import com.dofast.module.wms.service.rtissueline.RtIssueLineService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import org.redisson.executor.TasksService;
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
import java.beans.beancontext.BeanContext;
import java.math.BigDecimal;
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

    @Resource
    private MaterialStockService materialStockService;

    @Resource
    private TaskApi taskApi;

    @Resource
    private FeedLineService feedLineService;

    @Resource
    private FeedbackApi feedBackApi;

    @Resource
    private IssueLineService issuseLineService;

    @Resource
    private IssueHeaderService issueHeaderService;

    @Resource
    private WorkorderERPAPI workorderERPAPI;

    @PostMapping("/create")
    @Operation(summary = "创建生产退料单头")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:create')")
    public CommonResult<Long> createRtIssue(@Valid @RequestBody RtIssueCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtIssueService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.RT_ISSUE_CODE_EXISTS);
        }
        // 校验当前的任务单是否已报工
        FeedbackDTO feedbackInfo = feedBackApi.getFeedBackByTaskCode(createReqVO.getTaskCode());
       /* if(feedbackInfo != null){
            return error(ErrorCodeConstants.RT_ISSUE_HAS_FEEDBACK);
        }*/

        WarehouseDO warehouse = null;
        StorageLocationDO location = null;
        StorageAreaDO area = null;
        if(StrUtils.isNotNull(createReqVO.getWarehouseId())){
            warehouse =  warehouseService.getWarehouse(createReqVO.getWarehouseId());
            createReqVO.setWarehouseCode(warehouse.getWarehouseCode());
            createReqVO.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StrUtils.isNotNull(createReqVO.getLocationId())){
            location = storageLocationService.getStorageLocation(createReqVO.getLocationId());
            createReqVO.setLocationCode(location.getLocationCode());
            createReqVO.setLocationName(location.getLocationName());
        }
        if(StrUtils.isNotNull(createReqVO.getAreaId())){
            area = storageAreaService.getStorageArea(createReqVO.getAreaId());
            createReqVO.setAreaCode(area.getAreaCode());
            createReqVO.setAreaName(area.getAreaName());
        }
        Long rtissueId = rtIssueService.createRtIssue(createReqVO);
        List<Map<String, Object>> lines = createReqVO.getRtissuelineList();
        for(Map<String, Object> line : lines){
            //追加单身
            RtIssueLineCreateReqVO req = new RtIssueLineCreateReqVO();
            // 追加库存
            req.setWarehouseId(createReqVO.getWarehouseId());
            req.setWarehouseCode(warehouse.getWarehouseCode());
            req.setWarehouseName(warehouse.getWarehouseName());
            req.setLocationId(createReqVO.getLocationId());
            req.setLocationCode(location.getLocationCode());
            req.setLocationName(location.getLocationName());
            req.setAreaId(createReqVO.getAreaId());
            req.setAreaCode(area.getAreaCode());
            req.setAreaName(area.getAreaName());
            req.setRtId(rtissueId);
            req.setBatchCode((String) line.get("batchCode"));
            req.setItemCode((String) line.get("itemCode"));
            req.setItemName((String) line.get("itemName"));
            req.setSpecification((String) line.get("specification"));
            Integer itemId = (Integer) line.get("itemId");
            req.setItemId(itemId.longValue());
            req.setUnitOfMeasure((String) line.get("unitOfMeasure"));
            // 基于当前的物料与批次号获取库存Id
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setBatchCode((String) line.get("batchCode"));
            exportReqVO.setItemCode((String) line.get("itemCode"));
            List<MaterialStockDO> materialStockList = materialStockService.getMaterialStockList(exportReqVO);
            if(materialStockList.isEmpty()){
                continue;
            }
            req.setMaterialStockId(materialStockList.get(0).getId());
            BigDecimal quantity = new BigDecimal(String.valueOf(line.get("quantity")));
            req.setQuantityRt(quantity);
            // 追加ERP项次, 项序用于接口回传
            Integer sequence = Optional.ofNullable((Integer) line.get("sequence")).orElse(0);
            req.setSequence(sequence.longValue());
            Integer setSequenceOrder = Optional.ofNullable((Integer) line.get("sequenceOrder")).orElse(0);
            req.setSequenceOrder(setSequenceOrder.longValue());
            rtIssueLineService.createRtIssueLine(req);
        }
        // 追加退料单身信息
        return success();
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
        // 校验当前的任务单是否已报工
        /*FeedbackDTO feedbackInfo = feedBackApi.getFeedBackByTaskCode(rtIssue.getTaskCode());
        if(feedbackInfo != null){
            return error(ErrorCodeConstants.RT_ISSUE_HAS_FEEDBACK);
        }*/
        RtIssueLineListVO param = new RtIssueLineListVO();
        param.setRtId(rtId);
        List<RtIssueLineDO> lines = rtIssueLineService.selectList(param);
        if(CollUtil.isEmpty(lines)){
            return error(ErrorCodeConstants.RT_ISSUE_NEED_MAT);
        }

        // 追加ERP接口调用
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>>  list = new ArrayList<>(); // 装填领料信息

        for (RtIssueLineDO reIssueLine: lines) {
            Long sequence = reIssueLine.getSequence();
            Long sequenceOrder = reIssueLine.getSequenceOrder();
            if(sequence == null || sequenceOrder == null){
                // 不在bom中管控
                // 不回传ERP
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("sfdc001", rtIssue.getWorkorderCode()); // 工单单号
            map.put("sfdc002", reIssueLine.getSequence()); // 工单项次
            map.put("sfdc003", reIssueLine.getSequenceOrder()); // 工单项序
            map.put("sfdc007", reIssueLine.getQuantityRt()); // 申请数量
            map.put("sfdc012", rtIssue.getLocationCode()); // ERP库区
            map.put("sfdc013", rtIssue.getAreaCode()); // ERP库位
            map.put("sfdc014", reIssueLine.getBatchCode()); // 批号
            map.put("sfdc015", "Y01"); // 理由码 成套发料对应H01，成套退料对应Y01，超领对应H02，超领退对应Y02
            map.put("sfdc016", ""); // 库存管理特征
            map.put("source_seq", ""); // MES项次
            list.add(map);
        }
        params.put("goodsList", list);
        params.put("sfda002", "23"); // 一般退料
        params.put("source_no", rtIssue.getRtCode()); // 退料单号

        if(list.size()>0) {
            /*String erpResult = workorderERPAPI.workOrderIssueCreate(params);
            if (!erpResult.contains("SUCCESS")) {
                // 过账失败
                System.out.println("ERP过账失败：" + erpResult);
                return error(ErrorCodeConstants.RT_ISSUE_ERR_INTERFACE_ERROR);
            }*/
        }
        List<RtIssueTxBean> beans = rtIssueService.getTxBeans(rtId);

        //执行生产退料
        storageCoreService.processRtIssue(beans, rtIssue);

        rtIssue.setStatus(Constant.ORDER_STATUS_FINISHED);

        RtIssueUpdateReqVO updateReqVO = RtIssueConvert.INSTANCE.convert01(rtIssue);
        rtIssueService.updateRtIssue(updateReqVO);

        // 修改上料记录表数据-用于后续报工数据卡控
        TaskDTO taskDTO = taskApi.getTask(rtIssue.getTaskCode());
        FeedLineExportReqVO exportReqVO = new FeedLineExportReqVO();
        exportReqVO.setTaskCode(taskDTO.getTaskCode());
        List<FeedLineDO> feedLineList = feedLineService.getFeedLineList(exportReqVO);
        // 将feedLineList与param进行对比, 若当前的物料料号相同, 则将feedLineList中对应的数量减去param中的数量
        for(FeedLineDO feedLine : feedLineList){
            for(RtIssueLineDO line : lines){
                if(feedLine.getItemCode().equals(line.getItemCode())){
                    BigDecimal feedQuantity = new BigDecimal(feedLine.getQuantity());
                    BigDecimal finQuantity = feedQuantity.subtract(line.getQuantityRt());
                    feedLine.setQuantity(finQuantity.doubleValue());
                }
            }
        }
        // 更新FeedLine表
        feedLineService.updateFeedLineBatch(feedLineList);

        // 修改领料单身表信息
        IssueHeaderExportReqVO issueReq = new IssueHeaderExportReqVO();
        issueReq.setTaskCode(taskDTO.getTaskCode());
        List<IssueHeaderDO> issueList = issueHeaderService.getIssueHeaderList(issueReq);
        if(CollUtil.isEmpty(issueList)){
            return error(ErrorCodeConstants.ISSUE_LINE_NOT_EXISTS);
        }
        List<IssueLineDO> issueLine = issuseLineService.getIssueLineByHeaderId(issueList.get(0).getId());
        // 操作同上料记录表, 若当前的物料料号相同, 则将issueLine中对应的数量减去param中的数量
        for(IssueLineDO issue : issueLine){
            for(RtIssueLineDO line : lines){
                if(issue.getItemCode().equals(line.getItemCode())){
                    BigDecimal issueQuantity = new BigDecimal(String.valueOf(issue.getQuantityIssued()));
                    BigDecimal finQuantity = issueQuantity.subtract(line.getQuantityRt());
                    issue.setQuantityIssued(finQuantity);
                }
            }
        }
        // 更新IssueLine表
        issuseLineService.updateIssueLineBatch(issueLine);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产退料单头")
    @PreAuthorize("@ss.hasPermission('wms:rt-issue:update')")
    public CommonResult<Boolean> updateRtIssue(@Valid @RequestBody RtIssueUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(rtIssueService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.RT_ISSUE_CODE_EXISTS);
        }
        List<Map<String, Object>> rtissueLine = updateReqVO.getRtissuelineList();
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
        // 获取当前的单身信息
        RtIssueLineDO rtIssueLineDO = new RtIssueLineDO();
        rtIssueLineDO.setRtId(updateReqVO.getId());
        List<RtIssueLineDO> rtIssueLineList = rtIssueLineService.selectList(RtIssueConvert.INSTANCE.conver02(rtIssueLineDO));
        // 将当前前台获取的RtissueLine与获取的rtIssueLineList进行对比, 若当前的物料料号相同, 则将rtIssueLineList中对应的数量更新为param中的数量
        for(RtIssueLineDO line : rtIssueLineList){
            for(Map<String, Object> map : rtissueLine) {
                if (line.getItemCode().equals(map.get("itemCode"))) {
                    line.setQuantityRt(new BigDecimal(String.valueOf(map.get("quantity"))));
                }
            }
        }
        rtIssueLineService.updateRtIssueLineBatch(rtIssueLineList);
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
        RtIssueRespVO respVO = RtIssueConvert.INSTANCE.convert(rtIssue);
        // 初始化上料信息
        FeedLineExportReqVO exportReqVO = new FeedLineExportReqVO();
        exportReqVO.setTaskCode(rtIssue.getTaskCode());
        List<FeedLineDO> feedLineList = feedLineService.getFeedLineList(exportReqVO);
        // 将feedLineList转为List<Map<String, Object>
        List<Map<String, Object>> feedLineMapList = new ArrayList<>();
        for(FeedLineDO feedLine : feedLineList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", feedLine.getId());
            map.put("batchCode", feedLine.getBatchCode());
            map.put("itemCode", feedLine.getItemCode());
            map.put("itemName", feedLine.getItemName());
            map.put("itemId", feedLine.getItemId());
            map.put("specification", feedLine.getSpecification());
            map.put("unitOfMeasure", feedLine.getUnitOfMeasure());
            map.put("quantity", feedLine.getQuantity());
            map.put("warehouseCode", feedLine.getWarehouseCode());
            map.put("warehouseName", feedLine.getWarehouseName());
            map.put("warehouseId", feedLine.getWarehouseId());
            map.put("locationCode", feedLine.getLocationCode());
            map.put("locationName", feedLine.getLocationName());
            map.put("locationId", feedLine.getLocationId());
            map.put("areaCode", feedLine.getAreaCode());
            map.put("areaName", feedLine.getAreaName());
            map.put("areaId", feedLine.getAreaId());
            feedLineMapList.add(map);
        }
        // 初始化上料信息
        respVO.setIssuelineList(feedLineMapList);
        // 基于单身表初始化退料信息
        RtIssueLineListVO param = new RtIssueLineListVO();
        param.setRtId(id);
        List<RtIssueLineDO> rtIssueLineList = rtIssueLineService.selectList(param);
        // 将rtIssueLineList转为List<Map<String, Object>
        List<Map<String, Object>> rtIssueLineMapList = new ArrayList<>();
        for(RtIssueLineDO rtIssueLine : rtIssueLineList){
            Map<String, Object> map = new HashMap<>();
            map.put("id", rtIssueLine.getId());
            map.put("batchCode", rtIssueLine.getBatchCode());
            map.put("itemCode", rtIssueLine.getItemCode());
            map.put("itemName", rtIssueLine.getItemName());
            map.put("itemId", rtIssueLine.getItemId());
            map.put("specification", rtIssueLine.getSpecification());
            map.put("unitOfMeasure", rtIssueLine.getUnitOfMeasure());
            map.put("quantity", rtIssueLine.getQuantityRt());
            map.put("itemId", rtIssueLine.getItemId());
            map.put("warehouseCode", rtIssueLine.getWarehouseCode());
            map.put("warehouseName", rtIssueLine.getWarehouseName());
            map.put("warehouseId", rtIssueLine.getWarehouseId());
            map.put("locationCode", rtIssueLine.getLocationCode());
            map.put("locationName", rtIssueLine.getLocationName());
            map.put("locationId", rtIssueLine.getLocationId());
            map.put("areaCode", rtIssueLine.getAreaCode());
            map.put("areaName", rtIssueLine.getAreaName());
            map.put("areaId", rtIssueLine.getAreaId());
            rtIssueLineMapList.add(map);
        }
        // 初始化退料信息
        respVO.setRtissuelineList(rtIssueLineMapList);
        return success(respVO);
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
