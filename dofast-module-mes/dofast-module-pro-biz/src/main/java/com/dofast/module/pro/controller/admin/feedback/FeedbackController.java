package com.dofast.module.pro.controller.admin.feedback;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.framework.common.exception.ServiceException;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.common.util.bean.BeanUtils;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.framework.security.core.util.SecurityFrameworkUtils;
import com.dofast.framework.tenant.core.aop.TenantIgnore;
import com.dofast.module.cmms.api.dvmachinery.DvMachineryApi;
import com.dofast.module.cmms.api.dvmachinery.dto.DvMachineryDTO;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.mditem.vo.MdItemExportReqVO;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.service.mditem.MdItemService;
import com.dofast.module.mes.service.mditemtype.MdItemTypeService;
import com.dofast.module.mes.service.mdworkstation.MdWorkstationService;
import com.dofast.module.mes.service.mdworkstationworker.MdWorkstationWorkerService;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.pro.controller.admin.feedback.vo.*;
import com.dofast.module.pro.controller.admin.feedbackdefect.vo.FeedbackDefectCreateReqVO;
import com.dofast.module.pro.controller.admin.feedbackdefect.vo.FeedbackDefectExportReqVO;
import com.dofast.module.pro.controller.admin.feedbackdefect.vo.FeedbackDefectUpdateReqVO;
import com.dofast.module.pro.controller.admin.feedbackmember.vo.FeedbackMemberCreateReqVO;
import com.dofast.module.pro.controller.admin.feedbackmember.vo.FeedbackMemberExportReqVO;
import com.dofast.module.pro.controller.admin.feedbackmember.vo.FeedbackMemberPageReqVO;
import com.dofast.module.pro.controller.admin.routeprocess.vo.RouteProcessExportReqVO;
import com.dofast.module.pro.controller.admin.task.vo.TaskUpdateReqVO;
import com.dofast.module.pro.controller.admin.workorder.vo.WorkorderUpdateReqVO;
import com.dofast.module.pro.convert.feedback.FeedbackConvert;
import com.dofast.module.pro.convert.feedbackmember.FeedbackMemberConvert;
import com.dofast.module.pro.convert.task.TaskConvert;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.dataobject.feedbackdefect.FeedbackDefectDO;
import com.dofast.module.pro.dal.dataobject.feedbackmember.FeedbackMemberDO;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.feedback.FeedbackService;
import com.dofast.module.pro.service.feedbackdefect.FeedbackDefectService;
import com.dofast.module.pro.service.feedbackmember.FeedbackMemberService;
import com.dofast.module.pro.service.route.RouteService;
import com.dofast.module.pro.service.routeprocess.RouteProcessService;
import com.dofast.module.pro.service.task.TaskService;
import com.dofast.module.pro.service.workorder.WorkorderService;
import com.dofast.module.qms.api.oqcApi.OqcApi;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.trade.api.mixinorder.MixinOrderApi;
import com.dofast.module.wms.api.ERPApi.WorkorderERPAPI;
import com.dofast.module.wms.api.Issueheader.IssueApi;
import com.dofast.module.wms.api.Issueheader.dto.IssueLineDTO;
import com.dofast.module.wms.api.Issueheader.dto.IssueheaderDTO;
import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import com.dofast.module.wms.api.WarehouseApi.WarehouseApiImpl;
import com.dofast.module.wms.controller.admin.itemconsume.vo.ItemConsumeExportReqVO;
import com.dofast.module.wms.controller.admin.itemconsume.vo.ItemConsumeUpdateReqVO;
import com.dofast.module.wms.controller.admin.itemconsumeline.vo.ItemConsumeLineExportReqVO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.ItemRecptUpdateReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockCreateReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockUpdateReqVO;
import com.dofast.module.wms.controller.admin.productproduce.vo.ProductProduceExportReqVO;
import com.dofast.module.wms.controller.admin.productproduce.vo.ProductProduceUpdateReqVO;
import com.dofast.module.wms.controller.admin.productproduceline.vo.ProductProduceLineExportReqVO;
import com.dofast.module.wms.controller.admin.storagearea.vo.StorageAreaExportReqVO;
import com.dofast.module.wms.controller.admin.transaction.vo.TransactionUpdateReqVO;
import com.dofast.module.wms.convert.itemconsume.ItemConsumeConvert;
import com.dofast.module.wms.convert.materialstock.MaterialStockConvert;
import com.dofast.module.wms.convert.productproduce.ProductProduceConvert;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeTxBean;
import com.dofast.module.wms.dal.dataobject.itemconsumeline.ItemConsumeLineDO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProductTxBean;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.dal.mysql.itemconsumeline.ItemConsumeLineMapper;
import com.dofast.module.wms.dal.mysql.productproduceline.ProductProduceLineMapper;
import com.dofast.module.wms.service.itemconsume.ItemConsumeService;
import com.dofast.module.wms.service.itemrecpt.ItemRecptService;
import com.dofast.module.wms.service.materialstock.MaterialStockService;
import com.dofast.module.wms.service.productproduce.ProductProduceService;
import com.dofast.module.wms.service.productproduceline.ProductProduceLineService;
import com.dofast.module.wms.service.productsalse.ProductSalseService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
import com.dofast.module.wms.service.transaction.TransactionService;
import com.dofast.module.wms.service.warehouse.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUsername;
import static cn.hutool.core.date.DateUtil.formatDate;
import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static com.dofast.module.pro.enums.ErrorCodeConstants.QUENTITYP_RODUCED_IS_MORE;

@Tag(name = "生产管理 - 生产报工记录")
@RestController
@RequestMapping("/mes/pro/feedback")
@Validated
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private WorkorderService workorderService;

    @Resource
    private TaskService taskService;

    @Resource
    private RouteProcessService routeProcessService;

    @Resource
    private ProductProduceService productProduceService;

    @Resource
    private ItemConsumeService itemConsumeService;

    @Resource
    private StorageCoreService storageCoreService;

    @Resource
    private MdItemService mdItemService;

    @Resource
    private ItemRecptService itemRecptService;

    @Resource
    private OqcApi oqcApi;

    @Resource
    private ProductSalseService productSalseService;

    @Resource
    private MixinOrderApi mixinOrderApi;

    @Resource
    private AdminUserApi adminUserApi;

    @Autowired
    private MdWorkstationWorkerService workstationWorkerService;

    @Resource
    private MdWorkstationService workstationService;

    @Resource
    private MdWorkstationService mdWorkstationService;

    @Resource
    private RouteService routeService;

    @Resource
    private StorageLocationService locationService;

    @Resource
    private StorageAreaService areaService;

    @Resource
    private MaterialStockService materialStockService;

    @Resource
    private MdItemTypeService mdItemTypeService;

    @Resource
    private WarehouseApiImpl warehouseApiImpl;

    @Resource
    private FeedbackMemberService feedBackMemberService;

    @Resource
    private FeedbackDefectService feedbackDefectService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WarehouseService warehouseService;

    @Resource
    private StorageLocationService storageLocationService;

    @Resource
    private StorageAreaService storageAreaService;

    @Resource
    private DvMachineryApi dvMachineryApi;

    @Resource
    private IssueApi issueApi;

    @Resource
    private ProductProduceLineMapper productProduceLineMapper;

    @Resource
    private ItemConsumeLineMapper itemConsumeLineMapper;

    @Resource
    private WorkorderERPAPI workorderERPAPI;

    @PostMapping("/create")
    @Operation(summary = "创建生产报工记录")
    @PreAuthorize("@ss.hasPermission('pro:feedback:create')")
    public CommonResult<Long> createFeedback(@Valid @RequestBody FeedbackCreateReqVO createReqVO) {
        List<Map<String, Object>> list = createReqVO.getFeedbackMemberList();
        List<Map<String, Object>> defectlist = createReqVO.getProcessDefectList();

        // 获得用户基本信息
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserRespDTO userDTO = adminUserApi.getUser(loginUserId);
        createReqVO.setNickName(userDTO.getNickname());
        createReqVO.setUserName(userDTO.getUsername());
        // 生成三位数随机数
        int random = (int) ((Math.random() * 9 + 1) * 100);
        createReqVO.setFeedbackCode(new StringBuffer().append("AMBG01").append("-").append(createReqVO.getTaskCode()).append("-").append(random).toString());
        Long feedbackId = feedbackService.createFeedback(createReqVO);
        String taskCode = createReqVO.getTaskCode();

        TaskDO task = taskService.getTask(taskCode);

        // 2025-03-13 追加需求: 判定当前任务单对应的领料单是否存在未上料单据信息, 存在则不允许其进行报工操作
        // 基于任务单获取生产领料单
        IssueheaderDTO issueHeader = new IssueheaderDTO();
        issueHeader.setTaskId(task.getId());
        issueHeader.setWorkorderCode(createReqVO.getWorkorderCode());
        List<IssueheaderDTO> issueHeaderList = issueApi.listIssueHeader(issueHeader);
        if (issueHeaderList.isEmpty()) {
            return error(ErrorCodeConstants.ISSUE_NOT_EXISTS);
        }
        IssueheaderDTO issueHeaderDTO = issueHeaderList.get(0);
        // 基于生产领料单获取未上料的生产领料单行
        IssueLineDTO issueLine = new IssueLineDTO();
        issueLine.setIssueId(issueHeaderDTO.getId());
        issueLine.setStatus("N");
        List<IssueLineDTO> issueLineList = issueApi.listIssueLine(issueLine);
        if (!issueLineList.isEmpty()) {
            return error(ErrorCodeConstants.TASK_NOT_RECEPT);
        }

        task.setFeedbackStatus("Y");

        taskService.updateTask(TaskConvert.INSTANCE.convert01(task));
        for (Map<String, Object> map : list) {
            FeedbackMemberCreateReqVO req = new FeedbackMemberCreateReqVO();
            req.setFeedbackId(String.valueOf(feedbackId));
            req.setNickName((String) map.get("nickname"));
            Integer userId = (Integer) map.get("id");
            req.setUserId(userId.longValue());
            req.setUserName((String) map.get("username"));
            req.setTaskCode(createReqVO.getTaskCode());
            req.setTeamCode(createReqVO.getTeamCode());
            feedBackMemberService.createFeedbackMember(req);
        }

        for (Map<String, Object> map : defectlist) {
            FeedbackDefectCreateReqVO req = new FeedbackDefectCreateReqVO();
            Object startMeterObj = map.get("startMeter");
            Object endMeterObj = map.get("endMeter");
            Object defectMeterObj = map.get("defectMeter");

            Integer startMeter = parseInteger(startMeterObj, 0); // 提供默认值 0
            Integer endMeter = parseInteger(endMeterObj, 0); // 提供默认值 0
            Integer defectMeter = parseInteger(defectMeterObj, 0); // 提供默认值 0

            req.setFeedbackId(String.valueOf(feedbackId));
            req.setDefectId(Long.valueOf((Integer) map.get("id")));
            req.setDefectName((String) map.get("defectName"));
            req.setStartMeter(String.valueOf(startMeter));
            req.setEndMeter(String.valueOf(endMeter));
            req.setDefectMeter(String.valueOf(defectMeter));
            req.setTaskCode(createReqVO.getTaskCode());
            feedbackDefectService.createFeedbackDefect(req);
        }

        return success();
    }

    private Integer parseInteger(Object obj, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String str = obj.toString().trim();
        if (str.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产报工记录")
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    public CommonResult<Boolean> updateFeedback(@Valid @RequestBody FeedbackUpdateReqVO updateReqVO) {
        feedbackService.updateFeedback(updateReqVO);
        List<Map<String, Object>> list = Optional.ofNullable(updateReqVO.getFeedbackMemberList()).orElse(Collections.emptyList());
        List<Map<String, Object>> queryList = Optional.ofNullable(updateReqVO.getProcessDefectList()).orElse(Collections.emptyList());

        FeedbackMemberExportReqVO pageReqVO = new FeedbackMemberExportReqVO();
        pageReqVO.setFeedbackId(String.valueOf(updateReqVO.getId()));
        List<FeedbackMemberDO> memberList = feedBackMemberService.getFeedbackMemberList(pageReqVO);
        for (FeedbackMemberDO member : memberList) {
            feedBackMemberService.deleteFeedbackMember(member.getId());
        }
        if (!list.isEmpty()) {
            for (Map<String, Object> map : list) {
                FeedbackMemberCreateReqVO req = new FeedbackMemberCreateReqVO();
                req.setFeedbackId(String.valueOf(updateReqVO.getId()));
                req.setNickName((String) map.get("nickname"));
                Integer userId = (Integer) map.get("id");
                req.setUserId(userId.longValue());
                req.setUserName((String) map.get("username"));
                req.setTaskCode(updateReqVO.getTaskCode());
                req.setTeamCode(updateReqVO.getTeamCode());
                feedBackMemberService.createFeedbackMember(req);
            }
        }
        //获取当前所有的缺陷项信息, 进行比对
        FeedbackDefectExportReqVO defectReqVO = new FeedbackDefectExportReqVO();
        defectReqVO.setFeedbackId(String.valueOf(updateReqVO.getId()));
        List<FeedbackDefectDO> defectList = feedbackDefectService.getFeedbackDefectList(defectReqVO);
        for (FeedbackDefectDO defect : defectList) {
            Integer defectId = defect.getDefectId().intValue();
            // 遍历 queryList，寻找匹配的缺陷项
            for (Map<String, Object> defectMap : queryList) {
                // 获取报工单ID 和缺陷项ID
                String feedbackId = String.valueOf(updateReqVO.getId());
                Integer queryDefectId = (Integer) defectMap.get("defectId");

                // 判断是否匹配
                if (defectId == queryDefectId && queryDefectId.equals(defectId)) {
                    // 更新起始米数和结束米数
                    Object startMeterObj = defectMap.get("startMeter");
                    Object endMeterObj = defectMap.get("endMeter");
                    Object defectMeterObj = defectMap.get("defectMeter");

                    Integer newStartMeter = parseInteger(startMeterObj, 0); // 提供默认值 0
                    Integer newEndMeter = parseInteger(endMeterObj, 0); // 提供默认值 0
                    Integer newDefectMeter = parseInteger(defectMeterObj, 0); // 提供默认值 0

                    // 设置新的起始米数和结束米数
                    defect.setStartMeter(String.valueOf(newStartMeter));
                    defect.setEndMeter(String.valueOf(newEndMeter));
                    defect.setDefectMeter(String.valueOf(newDefectMeter));
                    // 调用服务更新缺陷项
                    FeedbackDefectUpdateReqVO updateDefectReq = new FeedbackDefectUpdateReqVO();
                    updateDefectReq.setId(defect.getId());
                    updateDefectReq.setStartMeter(defect.getStartMeter());
                    updateDefectReq.setEndMeter(defect.getEndMeter());
                    updateDefectReq.setDefectMeter(defect.getDefectMeter());
                    feedbackDefectService.updateFeedbackDefect(updateDefectReq);
                    break;
                }
            }
        }
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产报工记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:feedback:delete')")
    public CommonResult<Boolean> deleteFeedback(@RequestParam("id") Long id) {
        feedbackService.deleteFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产报工记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:feedback:query')")
    public CommonResult<FeedbackRespVO> getFeedback(@RequestParam("id") Long id) {
        FeedbackDO feedback = feedbackService.getFeedback(id);
        FeedbackRespVO req = FeedbackConvert.INSTANCE.convert(feedback);
        FeedbackMemberExportReqVO memberReq = new FeedbackMemberExportReqVO();
        memberReq.setFeedbackId(String.valueOf(id));
        req.setMemberList(feedBackMemberService.getFeedbackMemberList(memberReq));
        FeedbackDefectExportReqVO defectReq = new FeedbackDefectExportReqVO();
        defectReq.setFeedbackId(String.valueOf(id));
        req.setProcessDefectList(feedbackDefectService.getFeedbackDefectList(defectReq));
        return success(req);
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产报工记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:feedback:query')")
    public CommonResult<List<FeedbackRespVO>> getFeedbackList(@RequestParam("ids") Collection<Long> ids) {
        List<FeedbackDO> list = feedbackService.getFeedbackList(ids);
        return success(FeedbackConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产报工记录分页")
    @PreAuthorize("@ss.hasPermission('pro:feedback:query')")
    public CommonResult<PageResult<FeedbackRespVO>> getFeedbackPage(@Valid FeedbackPageReqVO pageVO) {
        PageResult<FeedbackDO> pageResult = feedbackService.getFeedbackPage(pageVO);
        return success(FeedbackConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产报工记录 Excel")
    @PreAuthorize("@ss.hasPermission('pro:feedback:export')")
    @OperateLog(type = EXPORT)
    public void exportFeedbackExcel(@Valid FeedbackExportReqVO exportReqVO,
                                    HttpServletResponse response) throws IOException {
        List<FeedbackDO> list = feedbackService.getFeedbackList(exportReqVO);
        // 导出 Excel
        List<FeedbackExcelVO> datas = FeedbackConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产报工记录.xls", "数据", FeedbackExcelVO.class, datas);
    }

    /**
     * 执行报工
     * 1.更新生产任务和生产工单的进度
     * 2.物料消耗
     * 3.产品产出
     *
     * @param recordId
     * @return
     */
   /* @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    @Operation(summary = "执行生产报工")
    @Transactional
    @PutMapping("/{recordId}")
    public CommonResult execute(@PathVariable("recordId") Long recordId) {

        if (!StrUtils.isNotNull(recordId)) {
            return error(ErrorCodeConstants.FEEDBACK_NEED_SAVE_FIRST);
        }

        FeedbackDO feedback = feedbackService.getFeedback(recordId);

        if (feedback.getQuantityFeedback().compareTo(Double.valueOf(0)) != 1) {
            return error(ErrorCodeConstants.FEEDBACK_NUM_IS_ZERO);
        }
        WorkorderDO workorder = workorderService.getWorkorder(feedback.getWorkorderId());
        if (workorder.getQuantityProduced() > feedback.getQuantity()) {
            return error(QUENTITYP_RODUCED_IS_MORE);
        }
        //更新生产任务的生产数量
        TaskDO task = taskService.getTask(feedback.getTaskId());
        Double quantityProduced, quantityQuanlify, quantityUnquanlify;
        quantityQuanlify = task.getQuantityQuanlify() == null ? 0 : task.getQuantityQuanlify();
        quantityUnquanlify = task.getQuantityUnquanlify() == null ? 0 : task.getQuantityUnquanlify();
        quantityProduced = task.getQuantityProduced() == null ? 0 : task.getQuantityProduced();
//        task.setQuantityProduced(feedback.getQuantityFeedback());
        task.setQuantityQuanlify(quantityQuanlify + (feedback.getQuantityQualified()));
        task.setQuantityUnquanlify(quantityUnquanlify + feedback.getQuantityUnquanlified());
        task.setDeleted(true);
        TaskUpdateReqVO taskUpdateReqVO = BeanUtil.toBean(task, TaskUpdateReqVO.class);
        taskService.updateTask(taskUpdateReqVO);
        *//**
     *  2024-11-15注释, 澳美没有关键工序概念
     *  根据当前任务校验当前是否有下一道工序
     *      有: 入下一道制成线边仓(状态待入库)
     *          线边仓库区有工序编码
     *      无: 入本制程线边仓(状态待入库)
     *//*
        // 澳美的前制程的产成品是下道制程的半半成品, 均视为产品
        //生成产品产出记录单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
        //执行产品产出入线边库
        executeProductProduce(feedback, productRecord, workorder);
        *//*if(routeProcessService.checkKeyProcess(feedback)){
            //更新生产工单的生产数量
            Double produced = workorder.getQuantityProduced() == null?0:workorder.getQuantityProduced();
            Double feedBackQuantity = feedback.getQuantityFeedback() ==null?0:feedback.getQuantityFeedback();
            workorder.setQuantityProduced( produced + feedBackQuantity);
            WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
            workorderService.updateWorkorder(workorderUpdateReqVO);

            //生成产品产出记录单
            FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
            ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
            //执行产品产出入线边库
            executeProductProduce(productRecord,workorder);
        }*//*
        //根据当前工序的物料BOM配置，进行物料消耗
        //先生成消耗单
        //FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ItemConsumeDO itemConsume = itemConsumeService.generateItemConsume(feedbackDTO);
        if (StrUtils.isNotNull(itemConsume)) {
            //执行库存消耗动作
            executeItemConsume(itemConsume);
        }
        //更新报工单的状态
        feedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        //feedback.setFeedbackTime(LocalDateTime.now());
        FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
        feedbackService.updateFeedback(feedbackUpdateReqVO);
        //更新设备状态
        DvMachineryDTO dvMachineryDTO = dvMachineryApi.getMachineryInfo(task.getMachineryCode());
        dvMachineryDTO.setStatus("STOP"); // 报工后设备停机
        dvMachineryApi.updateMachineryInfo(dvMachineryDTO);
        return CommonResult.success();
    }*/

    /**
     * 初始化仓库信息
     *
     * @param feedBack
     * @return
     */
    @PreAuthorize("@ss.hasPermission('pro:feedback:query')")
    @Operation(summary = "初始化仓库信息")
    @Transactional
    @GetMapping("/initWarehouse")
    public Map<String, Object> checkWarehouse(FeedbackRespVO feedBack) {
        Map<String, Object> result = new HashMap<>();
        Long taskId = feedBack.getTaskId();
        TaskDO task = taskService.getTask(taskId);
        Long workorderId = feedBack.getWorkorderId();
        WorkorderDO workorder = workorderService.getWorkorder(workorderId);

        String routeCode = workorder.getProductCode() + "-" + workorder.getRouteCode();
        // 获取工艺路线详情
        RouteDO route = routeService.getRoute(routeCode);
        RouteProcessExportReqVO exportReqVO = new RouteProcessExportReqVO();
        exportReqVO.setRouteId(route.getId());
        // 基于任务单判定所属工序
        // 基于工序查看是否存在下道工序
        // 存在->入下道制程线边仓  无->入本仓
        exportReqVO.setProcessCode(task.getProcessCode());
        List<RouteProcessDO> routeProcess = routeProcessService.getRouteProcessList(exportReqVO);
        RouteProcessDO process = routeProcess.get(0);
        String nextProcessCode = Optional.ofNullable(routeProcess.get(0).getNextProcessCode()).orElse(null);
        WarehouseDTO warehouse = null;
        StorageLocationDO location = null;
        StorageAreaDO area = null;

        if (nextProcessCode != null) {
            // 入下一个制成线边仓
            // 仓库
            warehouse = warehouseApiImpl.selectWmWarehouseByWarehouseCode(Constant.LINE_EDGE_CODE);
            // 库区
            location = locationService.getStorageLocation(nextProcessCode);
            // 库位, 目前没有WMS, 仅获取第一个库位(MES管控, 无需回传ERP)
            area = areaService.getStorageAreaByLocationId(location.getId()).get(0);
        } else {
            // 当前工序若为涂布, 入模压线边仓
            if ("AM001".equals(task.getProcessCode())) {
                warehouse = warehouseApiImpl.selectWmWarehouseByWarehouseCode(Constant.LINE_EDGE_CODE);
                location = locationService.getStorageLocation("AM002");
                area = areaService.getStorageAreaByLocationId(location.getId()).get(0);
            } else if ("AM004".equals(task.getProcessCode())) {
                warehouse = warehouseApiImpl.selectWmWarehouseByWarehouseCode(Constant.LINE_EDGE_CODE);
                location = locationService.getStorageLocation("AM005");
                area = areaService.getStorageAreaByLocationId(location.getId()).get(0);
            } else {
                warehouse = warehouseApiImpl.selectWmWarehouseByWarehouseCode(Constant.WAREHOUSE_CODE);
                // 传递至成品仓-基于正式库决定, 暂时写死
                location = locationService.getStorageLocation(66L);
                area = areaService.getStorageArea(55L);
            }
        }
        result.put("warehouse", warehouse);
        result.put("location", location);
        result.put("area", area);
        return result;
    }

    /**
     * 执行产品产出入线边库动作
     *
     * @param record
     */
    private void executeProductProduce(FeedbackDO feedBack, ProductProduceDO record, WorkorderDO workorder, Long warehouseId, Long locationId, Long areaId) {
        /*List<ProductProductTxBean> beans = productProduceService.getTxBeans(record.getId());
        for (ProductProductTxBean bean : beans) {
            MdItemDO mdItem = mdItemService.getMdItem(bean.getItemId());
            bean.setWarehouseCode(mdItem.getWarehouseCode());
            bean.setLocationCode(mdItem.getLocationCode());
            bean.setAreaCode(mdItem.getAreaCode());
            bean.setLocationName(mdItem.getLocationName());
        }
        beans.stream().forEach(v->{
            v.setItemName(v.getItemName()+" "+workorder.getWorkorderName());
        });
        storageCoreService.processProductProduce(beans);
        record.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        ProductProduceUpdateReqVO productProduceUpdateReqVO = BeanUtil.toBean(record, ProductProduceUpdateReqVO.class);
        productProduceService.updateProductProduce(productProduceUpdateReqVO);*/
        // 2024-11-1注释
        Long taskId = record.getTaskId();
        TaskDO task = taskService.getTask(taskId);
        String routeCode = workorder.getProductCode() + "-" + workorder.getRouteCode();
        // 获取工艺路线详情
        RouteDO route = routeService.getRoute(routeCode);
        RouteProcessExportReqVO exportReqVO = new RouteProcessExportReqVO();
        exportReqVO.setRouteId(route.getId());
        // 基于任务单判定所属工序
        // 基于工序查看是否存在下道工序
        // 存在->入下道制程线边仓  无->入本仓
        exportReqVO.setProcessCode(task.getProcessCode());
        List<RouteProcessDO> routeProcess = routeProcessService.getRouteProcessList(exportReqVO);
        RouteProcessDO process = routeProcess.get(0);
        String nextProcessCode = Optional.ofNullable(routeProcess.get(0).getNextProcessCode()).orElse(null);
        MaterialStockCreateReqVO materialStock = new MaterialStockCreateReqVO();
        MdItemDO item = mdItemService.getMdItemByItemCode(task.getItemCode());
        MdItemTypeDO itemType = mdItemTypeService.getMdItemType(item.getItemTypeId());
        // 开始追加库存信息
        materialStock.setItemId(item.getId());
        materialStock.setItemCode(item.getItemCode());
        materialStock.setItemTypeId(itemType.getId());
        materialStock.setWorkorderId(workorder.getId());
        materialStock.setWorkorderCode(workorder.getWorkorderCode());
        materialStock.setUnitOfMeasure(task.getUnitOfMeasure());
        materialStock.setQuantityOnhand(BigDecimal.valueOf(feedBack.getQuantityQualified())); // 产成品数量为报工单合格数量

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String parentBatchCode = currentDate.format(formatter) + ThreadLocalRandom.current().nextInt(1000, 10000);

        // 仓库
        WarehouseDTO warehouse = warehouseApiImpl.getWarehouse(warehouseId);
        // 库区
        StorageLocationDO location = locationService.getStorageLocation(locationId);
        // 库位, 目前没有WMS, 仅获取第一个库位(MES管控, 无需回传ERP)
        StorageAreaDO area = areaService.getStorageArea(areaId);
        // 开始录入库存
        // 后缀配置: 若warehouse的编码为WH165视为半成品, WH166视为成品
        String warehouseCode = warehouse.getWarehouseCode();
        if (warehouseCode.equals(Constant.LINE_EDGE_CODE)) {
            materialStock.setItemName(item.getItemName() + "-" + process.getProcessCode() + "半成品");
        } else {
            materialStock.setItemName(item.getItemName() + "-" + process.getProcessCode() + "产成品");
        }
        materialStock.setWarehouseCode(warehouse.getWarehouseCode()); // 线边仓
        materialStock.setWarehouseId(warehouse.getId());
        materialStock.setWarehouseName(warehouse.getWarehouseName());
        materialStock.setLocationCode(location.getLocationCode()); // 线边仓库区
        materialStock.setLocationId(location.getId());
        materialStock.setLocationName(location.getLocationName());
        materialStock.setAreaCode(area.getAreaCode()); // 线边仓库区
        materialStock.setAreaId(area.getId());
        materialStock.setAreaName(area.getAreaName());
        materialStock.setRecptStatus("N");// 需等待打印条码后质检合格开始入库
        //批次: 获取当前任务单及最新的流水号
        String serial = task.getSerial();
        if (serial == null) {
            serial = "001";
        } else {
            int serialInt = Integer.parseInt(serial);
            serialInt++;
            serial = String.format("%03d", serialInt);
        }
        materialStock.setBatchCode(task.getParentBatchCode() + "-" + serial);
        feedBack.setBatchCode(task.getParentBatchCode() + "-" + serial);
        feedBack.setErpBatchCode(parentBatchCode);
        // 修改任务单最新批次信息
        task.setSerial(serial);
        taskService.updateTask(TaskConvert.INSTANCE.convert01(task));

        // 追加库存
        materialStockService.createMaterialStock(materialStock);
        FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedBack, FeedbackUpdateReqVO.class);
        feedbackService.updateFeedback(feedbackUpdateReqVO);
    }


    /**
     * 执行物料消耗库存动作
     *
     * @param record
     */
    private void executeItemConsume(ItemConsumeDO record) {
        //需要在此处进行分批次领料的线边库扣减
        List<ItemConsumeTxBean> beans = itemConsumeService.getTxBeans(record.getId());
        storageCoreService.processItemConsume(beans);
        record.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        ItemConsumeUpdateReqVO itemConsumeUpdateReqVO = BeanUtil.toBean(record, ItemConsumeUpdateReqVO.class);
        itemConsumeService.updateItemConsume(itemConsumeUpdateReqVO);
    }

    /**
     * 执行物料产出入线边库动作
     *
     * @param record
     */
    private void executeItemProduce(ItemRecptDO record) {
        List<ItemRecptTxBean> beans = itemRecptService.getTxBeans(record.getId());
        storageCoreService.processItemRecpt(beans);
        record.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        ItemRecptUpdateReqVO itemRecptUpdateReqVO = BeanUtil.toBean(record, ItemRecptUpdateReqVO.class);
        itemRecptService.updateItemRecpt(itemRecptUpdateReqVO);
    }

    @PutMapping("/update-feedback-status")
    @Operation(summary = "更新生产报工记录的状态")
    @Transactional
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    public CommonResult updateFeedbackStatus(@RequestParam("id") Long id, @RequestParam("status") String status, @RequestParam("warehouseId") Long warehouseId, @RequestParam("locationId") Long locationId, @RequestParam("areaId") Long areaId) {
        if (!StrUtils.isNotNull(id)) {
            return error(ErrorCodeConstants.FEEDBACK_NEED_SAVE_FIRST);
        }
        FeedbackDO feedback = feedbackService.getFeedback(id);

        // 获得用户基本信息
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserRespDTO userDTO = adminUserApi.getUser(loginUserId);
        feedback.setNickName(userDTO.getNickname());
        feedback.setUserName(userDTO.getUsername());

        if (status.equals("UNAPPROVED")) {
            FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
            feedbackUpdateReqVO.setStatus("UNAPPROVED");
            feedbackService.updateFeedback(feedbackUpdateReqVO);
            // 追加IPQC记录(机长自检)
            return error(ErrorCodeConstants.FEEDBACK_NOT_APPROVED);
        }

        if (feedback.getQuantityFeedback().compareTo(Double.valueOf(0)) != 1) {
            return error(ErrorCodeConstants.FEEDBACK_NUM_IS_ZERO);
        }

        WorkorderDO workorder = workorderService.getWorkorder(feedback.getWorkorderId());
        /*if (workorder.getQuantityProduced() > feedback.getQuantity()) {
            return error(QUENTITYP_RODUCED_IS_MORE);
        }*/
        //更新生产任务的生产数量
        TaskDO task = taskService.getTask(feedback.getTaskId());
        Double quantityProduced, quantityQuanlify, quantityUnquanlify;
        quantityQuanlify = task.getQuantityQuanlify() == null ? 0 : task.getQuantityQuanlify();
        quantityUnquanlify = task.getQuantityUnquanlify() == null ? 0 : task.getQuantityUnquanlify();
        quantityProduced = task.getQuantityProduced() == null ? 0 : task.getQuantityProduced();
        task.setQuantityProduced(quantityProduced + feedback.getQuantityFeedback());
        task.setQuantityQuanlify(quantityQuanlify + feedback.getQuantityQualified());
        task.setQuantityUnquanlify(quantityUnquanlify + feedback.getQuantityUnquanlified());
        TaskUpdateReqVO taskUpdateReqVO = BeanUtil.toBean(task, TaskUpdateReqVO.class);
        taskService.updateTask(taskUpdateReqVO);

        //更新工单的生产数量
        //workorder.setQuantityProduced(quantityProduced + feedback.getQuantityFeedback());
        //WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
        //workorderService.updateWorkorder(workorderUpdateReqVO);
        //如果是关键工序，则更新当前工单的已生产数量
        //checkKeyProcess(feedback, workorder);

        //父工单操作
        //parentOrder(feedback, workorder);

        if (routeProcessService.checkFinProcess(task)) {
            //更新生产工单的生产数量
            Double produced = workorder.getQuantityProduced() == null ? 0 : workorder.getQuantityProduced();
            Double feedBackQuantity = feedback.getQuantityFeedback() == null ? 0 : feedback.getQuantityFeedback();
            workorder.setQuantityProduced(produced + feedBackQuantity);
            WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
            workorderService.updateWorkorder(workorderUpdateReqVO);
        }

        //根据当前工序的物料BOM配置，进行物料消耗
        //先生成消耗单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ItemConsumeDO itemConsume = itemConsumeService.generateItemConsume(feedbackDTO);
        if (StrUtils.isNotNull(itemConsume)) {
            //再执行库存消耗动作
            executeItemConsume(itemConsume);
        }
        //生成产品产出记录单
        ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
        //执行产品产出入线边库
        executeProductProduce(feedback, productRecord, workorder, warehouseId, locationId, areaId);
        //更新报工单的状态
        feedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        // 追加批次号信息
        feedback.setBatchCode(null);
        feedback.setErpBatchCode(null);
        FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
        feedbackService.updateFeedback(feedbackUpdateReqVO);
        return success();
    }

    private void checkKeyProcess(FeedbackDO feedback, WorkorderDO workorder) {
        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        if (routeProcessService.checkKeyProcess(feedback)) {
            MdItemDO itemDO = mdItemService.getMdItem(workorder.getProductId());
            //更新生产工单的生产数量
            Double produced = workorder.getQuantityProduced() == null ? 0 : workorder.getQuantityProduced();
            Double feedBackQuantity = feedback.getQuantityFeedback() == null ? 0 : feedback.getQuantityFeedback();
            workorder.setQuantityProduced(produced + feedBackQuantity);
            WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
            //判断是否排产数量加上已生产数量是否与排产数量相等
            workorderUpdateReqVO.setStatus(UserConstants.ORDER_STATUS_FINISHED);
            workorderService.updateWorkorder(workorderUpdateReqVO);
            FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
            //判断产品里的是产品还是物料
            // 澳美中每道制成都需要产出
            /*if (itemDO.getItemOrProduct().equals("ITEM")) {
                //生成物料产出记录单
                ItemRecptDO itemRecptDO = itemRecptService.generateItemRecpt(feedbackDTO);
                //执行物料产出入线边库
                executeItemProduce(itemRecptDO);
            } else {
                //生成产品产出记录单
                ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
                //执行产品产出入线边库
                executeProductProduce(productRecord, workorder);
            }*/
        }
    }

    private void parentOrder(FeedbackDO feedback, WorkorderDO workorder) {
        //父工单操作
        if (workorder.getParentId() != null && workorder.getParentId() != 0 && routeProcessService.checkKeyProcess(feedback)) {
            WorkorderDO parentOrder = workorderService.getWorkorder(workorder.getParentId());
            if (parentOrder.getParentId() != null && parentOrder.getParentId() != 0) {
                parentOrder(feedback, parentOrder);
            }
            checkKeyProcess(feedback, parentOrder);
            //创建出货检验单
            FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
            WorkorderDTO workorderDTO = BeanUtil.toBean(parentOrder, WorkorderDTO.class);
            OqcDTO oqcDTO = oqcApi.generateOqc(feedbackDTO, workorderDTO);
            //创建销售出库单
            productSalseService.generateProductSale(feedbackDTO, workorderDTO, oqcDTO);
        }
    }

    /*
    * 暂时取消一键报工
    @PutMapping("/one-click-create")
    @Operation(summary = "一键报工")
    @Transactional
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    public CommonResult<String> OneClickCreate(@RequestBody FeedbackDO feedback) {
        TaskDO task = taskService.getTask(feedback.getTaskId(), feedback.getProcessId());
        feedback.setQuantityFeedback(feedback.getQuantity());
        //判断报工是否超出排产数量
        if (task.getQuantityProduced() + feedback.getQuantityFeedback() > task.getQuantity()) {
            return error(QUENTITYP_RODUCED_IS_MORE);
        }
        //报工数量不能为空
        if (feedback.getQuantityFeedback() <= Double.valueOf(0)) {
            return error(ErrorCodeConstants.FEEDBACK_NUM_IS_ZERO);
        }
        task.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        //更新任务已完成数量
        task.setQuantityProduced(task.getQuantityProduced() + feedback.getQuantityFeedback());
        taskService.updateTask(BeanUtil.toBean(task, TaskUpdateReqVO.class));

        //创建一键报工报工记录
        feedbackService.OneClickCreateFeedback(feedback);


        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        WorkorderDO workorder = workorderService.getWorkorder(feedback.getWorkorderId());

        //判断是否为关键工序
        checkKeyProcessOneClick(feedback, workorder);

        //根据当前工序的物料BOM配置，进行物料消耗
        //先生成消耗单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ItemConsumeDO itemConsume = itemConsumeService.generateItemConsume(feedbackDTO);
        if (StrUtils.isNotNull(itemConsume)) {
            //再执行库存消耗动作
            executeItemConsume(itemConsume);
        }
        return success("报工成功，请继续扫码");
    }

    private void checkKeyProcessOneClick(FeedbackDO feedback, WorkorderDO workorder) {
        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        if (routeProcessService.checkKeyProcess(feedback)) {

            //判断报工是否超出工单排产数量
            if (workorder.getQuantityProduced() + feedback.getQuantityFeedback() > workorder.getQuantity()) {
                throw exception(QUENTITYP_RODUCED_IS_MORE);
            }

            MdItemDO itemDO = mdItemService.getMdItem(workorder.getProductId());
            //更新生产工单的生产数量
            Double produced = workorder.getQuantityProduced() == null ? 0 : workorder.getQuantityProduced();
            Double feedBackQuantity = feedback.getQuantityFeedback() == null ? 0 : feedback.getQuantityFeedback();
            workorder.setQuantityProduced(produced + feedBackQuantity);
            WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
            //判断是否排产数量加上已生产数量是否与排产数量相等
            workorderUpdateReqVO.setStatus(UserConstants.ORDER_STATUS_FINISHED);
            workorderService.updateWorkorder(workorderUpdateReqVO);

            FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);

            //判断产品里的是产品还是物料
            if (itemDO.getItemOrProduct().equals("ITEM")) {
                //生成物料产出记录单
                ItemRecptDO itemRecptDO = itemRecptService.generateItemRecpt(feedbackDTO);
                //执行物料产出入线边库
                executeItemProduce(itemRecptDO);
            } else {
                //生成产品产出记录单
                ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
                //执行产品产出入线边库
                executeProductProduce(feedback, productRecord, workorder);
            }

        }
    }*/

    /**
     * 报工产成品入库
     * 将待入库状态改为已入库
     *
     * @param params
     * @return
     */
    @PostMapping("/wareHousing")
    public String wareHousing(@RequestBody Map<String, Object> params) {
        System.out.println(params.toString());
        List<Map<String, Object>> objList = (List<Map<String, Object>>) params.get("wareList");

        System.out.println(objList.toString());

        // 准备调用ERP接口的参数容器
        Map<String, Object> erpParams = new HashMap<>();
        List<Map<String, Object>> workOrders = new ArrayList<>();

        // 遍历每个报工单进行数据转换
        for (Map<String, Object> ware : objList) {
            // 构建goodsList明细（根据ERP接口要求）
            List<Map<String, Object>> goodsList = new ArrayList<>();

            Map<String, Object> detail = new HashMap<>();
            detail.put("sfeb001", ware.get("workorderCode"));       // 工单单号
            detail.put("sfeb003", "1");                             // 入库类型（示例值，需确认）
            detail.put("sfeb004", ware.get("itemCode"));            // 料号
            detail.put("sfeb005", ware.get("specification"));       // 产品特征
            detail.put("sfeb008", ware.get("quantityFeedback"));    // 申请数量

            // 基于当前的库存信息
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setItemCode((String) ware.get("itemCode"));
            exportReqVO.setBatchCode((String) ware.get("batchCode"));
            List<MaterialStockDO> materialStock = materialStockService.getMaterialStockList(exportReqVO);

            detail.put("sfeb013", materialStock.get(0).getLocationCode());                          // 库位（示例值）
            detail.put("sfeb014", materialStock.get(0).getAreaCode());                       // 储位（示例值）
            detail.put("sfeb015", ware.get("batchCode"));        // 批号
            detail.put("source_seq", "");     // MES项次
            goodsList.add(detail);

            // 构建单个工单的master数据
            Map<String, Object> workOrder = new HashMap<>();
            workOrder.put("source_no", ware.get("feedbackCode"));   // MES报工单号
            workOrder.put("sfeadocno", "asft340");                  // 单别（示例值）
            workOrder.put("sfeadocdt", formatDate(new Date()));     // 单据日期
            workOrder.put("sfea001", formatDate(new Date()));       // 过账日期
            workOrder.put("sfea002", ware.get("userName"));         // 申请人员
            workOrder.put("goodsList", goodsList);                  // 当前工单明细

            workOrders.add(workOrder);
        }

        // 组装最终ERP接口参数
        erpParams.put("workOrders", workOrders);

        // 调用接口方法
       /* String result = workorderERPAPI.workOrderFinishCreate(erpParams);

        if(result!="success"){
            return null;
        }*/


        for (Map<String, Object> map : objList) {
            // 基于当前的库存信息
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setItemCode((String) map.get("itemCode"));
            exportReqVO.setBatchCode((String) map.get("batchCode"));
            List<MaterialStockDO> materialStock = materialStockService.getMaterialStockList(exportReqVO);
            materialStock.get(0).setRecptStatus("Y");
            materialStockService.updateMaterialStock(BeanUtil.toBean(materialStock.get(0), MaterialStockUpdateReqVO.class));

            // 修改当前单据状态为已入库
            Integer id = (Integer) map.get("id");
            FeedbackDO feedbackDO = feedbackService.getFeedback(id.longValue());
            feedbackDO.setStatus("WAREHOUSED");
            feedbackService.updateFeedback(FeedbackConvert.INSTANCE.convert02(feedbackDO));
        }
        return "success";
    }

    @PostMapping("/splitFeedback")
    public String splitFeedback(@RequestBody Map<String, Object> params) {
        String workorderCode = (String) params.get("workorderCode");
        Integer id = (Integer) params.get("id");
        String itemCode = (String) params.get("itemCode");
        String unitOfMeasure = (String) params.get("unitOfMeasure");
        List<Map<String, Object>> splitList = (List<Map<String, Object>>) params.get("splitDetails");
        FeedbackDO feedbackDO = feedbackService.getFeedback(id.longValue());
        TaskDO task = taskService.getTask(feedbackDO.getTaskId());
        BigDecimal updateCount = BigDecimal.ZERO;
        MdItemDO mdItemDO = mdItemService.getMdItemByItemCode(itemCode);

        String transactionType_out = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_OUT;
        String transactionType_in = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_IN;

        // 获取原报工单合格数量
        BigDecimal originalQualifiedQuantity = BigDecimal.valueOf(feedbackDO.getQuantityQualified());
        BigDecimal originalQuantity = BigDecimal.valueOf(feedbackDO.getQuantityFeedback());

        // 开始追加报工单信息
        for (Map<String, Object> split : splitList) {
            String splitStr = (String) split.get("quantity");
            BigDecimal quantityBig = new BigDecimal(splitStr); // 拆分数量
            updateCount = updateCount.add(quantityBig);

            // 计算拆分后的合格数量
            //BigDecimal splitQualifiedQuantity = originalQualifiedQuantity.multiply(quantityBig.divide(originalQuantity, 3, RoundingMode.HALF_UP));

            FeedbackDO addFeedback = new FeedbackDO();
            BeanUtils.copyProperties(feedbackDO, addFeedback);
            addFeedback.setId(null); // 清空ID
            addFeedback.setOriginCode(feedbackDO.getFeedbackCode()); // 记录来源单号
            addFeedback.setQuantityFeedback(quantityBig.doubleValue());
            addFeedback.setQuantityUnquanlified(0.0);
            addFeedback.setQuantityQualified(quantityBig.doubleValue()); // 设置拆分后的合格数量
            addFeedback.setTeamCode(feedbackDO.getTeamCode());

            // 更新批次信息
            String serial = task.getSerial();
            if (serial == null) {
                serial = "001";
            } else {
                int serialInt = Integer.parseInt(serial);
                serialInt++;
                serial = String.format("%03d", serialInt);
            }

            // AMGD01-202501006-001 拆分到最后一个"-"之前
            String batchBegin = feedbackDO.getBatchCode().substring(0, feedbackDO.getBatchCode().lastIndexOf("-"));
            addFeedback.setBatchCode(batchBegin + "-" + serial); // 更新子批次
            // 修改任务单最新批次信息
            task.setSerial(serial);
            taskService.updateTask(TaskConvert.INSTANCE.convert01(task));

            Long lineId = feedbackService.createFeedback(FeedbackConvert.INSTANCE.convert01(addFeedback));
            // 已完成的单据视为已入库
            // 获取库存信息, 修改当前已入库数量
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setItemCode(itemCode);
            exportReqVO.setBatchCode(feedbackDO.getBatchCode());
            List<MaterialStockDO> materialStockDO = materialStockService.getMaterialStockList(exportReqVO);
            if (!materialStockDO.isEmpty()) {
                MaterialStockDO materialStock = materialStockDO.get(0);
                //构造原库存减少事务
                TransactionUpdateReqVO transaction_out = new TransactionUpdateReqVO();
                BeanUtils.copyBeanProp(transaction_out, materialStock);
                transaction_out.setTransactionType(transactionType_out);
                transaction_out.setTransactionFlag(-1);//库存减少
                transaction_out.setTransactionQuantity(quantityBig);
                transaction_out.setTransactionDate(LocalDateTime.now());
                transaction_out.setSourceDocId(feedbackDO.getWorkorderId());
                transaction_out.setSourceDocCode(workorderCode);
                transaction_out.setSourceDocLineId(feedbackDO.getId());
                transactionService.processTransaction(transaction_out);

                //再构造一条目的库存增加的事务
                TransactionUpdateReqVO transaction_in = new TransactionUpdateReqVO();
                BeanUtils.copyBeanProp(transaction_in, addFeedback);
                transaction_in.setTransactionType(transactionType_in);
                transaction_in.setTransactionFlag(1);//库存增加
                transaction_in.setTransactionQuantity(quantityBig);
                //由于是新增的库存记录所以需要将查询出来的库存记录ID置为空
                transaction_in.setMaterialStockId(null);
                //使用出库事务的供应商初始化入库事务的供应商
                transaction_in.setVendorId(transaction_out.getVendorId());
                transaction_in.setVendorCode(transaction_out.getVendorCode());
                transaction_in.setVendorName(transaction_out.getVendorName());
                transaction_in.setVendorNick(transaction_out.getVendorNick());
                transaction_in.setItemId(mdItemDO.getId().longValue());
                transaction_in.setItemCode(mdItemDO.getItemCode());
                transaction_in.setItemName(mdItemDO.getItemName());
                transaction_in.setSpecification(mdItemDO.getSpecification());

                //库存,库区,库位信息继承原报工单
                WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(materialStock.getWarehouseCode());
                transaction_in.setWarehouseId(warehouse.getId());
                transaction_in.setWarehouseCode(warehouse.getWarehouseCode());
                transaction_in.setWarehouseName(warehouse.getWarehouseName());
                StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(materialStock.getLocationCode());
                transaction_in.setLocationId(location.getId());
                transaction_in.setLocationCode(location.getLocationCode());
                transaction_in.setLocationName(location.getLocationName());
                StorageAreaExportReqVO erv = new StorageAreaExportReqVO();
                erv.setLocationId(location.getId());
                erv.setAreaCode(materialStock.getAreaCode());
                List<StorageAreaDO> areaList = storageAreaService.getStorageAreaList(erv);
                StorageAreaDO area = areaList.get(0);
                transaction_in.setAreaId(area.getId());
                transaction_in.setAreaCode(area.getAreaCode());
                transaction_in.setAreaName(area.getAreaName());
                transaction_in.setSourceDocId(feedbackDO.getWorkorderId());
                transaction_in.setSourceDocCode(workorderCode);
                transaction_in.setSourceDocLineId(lineId);
                //设置入库相关联的出库事务ID
                transaction_in.setRelatedTransactionId(transaction_out.getId());
                transaction_in.setRecptStatus(materialStock.getRecptStatus());
                transactionService.processTransaction(transaction_in);
            }
            // 获取原先班组人员信息
            FeedbackMemberExportReqVO req = new FeedbackMemberExportReqVO();
            req.setFeedbackId(String.valueOf(feedbackDO.getId()));
            List<FeedbackMemberDO> memberList = feedBackMemberService.getFeedbackMemberList(req);
            for (FeedbackMemberDO member : memberList) {
                FeedbackMemberDO memberDO = new FeedbackMemberDO();
                BeanUtils.copyProperties(member, memberDO); // 复制原班组人员信息
                memberDO.setId(null); // 清空ID
                memberDO.setFeedbackId(String.valueOf(lineId)); // 关联新增的报工单
                feedBackMemberService.createFeedbackMember(FeedbackMemberConvert.INSTANCE.convert01(memberDO));
            }
        }
        // 更新原始报工单的数量和合格数量
        BigDecimal finQuantity = originalQuantity.subtract(updateCount); // 原始报工单剩余的数量
        // 计算原始报工单剩余的合格数量
        BigDecimal finQualifiedQuantity = originalQualifiedQuantity.subtract(updateCount);
        feedbackDO.setQuantityFeedback(finQuantity.doubleValue()); // 更新报工数量
        feedbackDO.setQuantityQualified(finQualifiedQuantity.doubleValue()); // 更新合格数量
        feedbackService.updateFeedback(FeedbackConvert.INSTANCE.convert02(feedbackDO));
        return "操作成功";
    }

    @GetMapping("/checkWarehousing")
    @Operation(summary = "获得报工条码信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:feedback:query')")
    public CommonResult<String> checkWarehousing(@RequestParam("id") Long id) {
        FeedbackDO feedback = feedbackService.getFeedback(id);
        MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
        exportReqVO.setBatchCode(feedback.getBatchCode());
        exportReqVO.setItemCode(feedback.getItemCode());
        List<MaterialStockDO> stockList = materialStockService.getMaterialStockList(exportReqVO);
        if (stockList.isEmpty()) {
            return error(ErrorCodeConstants.MATERIAL_STOCK_NOT_EXISTS);
        }
        MaterialStockDO stock = stockList.get(0);
        return success(stock.getRecptStatus());
    }


    @PostMapping("/reFeedback")
    @Operation(summary = "撤销报工")
    @PreAuthorize("@ss.hasPermission('pro:feedback:create')")
    public CommonResult<Long> reFeedback(@Valid @RequestBody Integer headerId) {
        String transactionType_out = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_OUT;
        String transactionType_in = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_IN;
        // 基于Id获取当前的报工单
        FeedbackDO feedback = feedbackService.getFeedback(headerId.longValue());
        // 基于当前的报工单获取当前的任务
        TaskDO task = taskService.getTask(feedback.getTaskId());
        // 基于当前的任务获取当前的工单
        WorkorderDO workorder = workorderService.getWorkorder(task.getWorkorderId());
        // 基于当前的工单获取当前的产品
        MdItemDO mdItem = mdItemService.getMdItem(workorder.getProductId());
        // 基于当前的报工单获取当前的子批次
        String batchCode = feedback.getBatchCode();
        // 基于当前的子批次获取当前的产成品库存信息
        // 后续将产成品删除
        MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
        exportReqVO.setBatchCode(batchCode);
        exportReqVO.setItemCode(mdItem.getItemCode());
        List<MaterialStockDO> stockList = materialStockService.getMaterialStockList(exportReqVO);
        if (stockList.isEmpty()) {
            return error(ErrorCodeConstants.MATERIAL_STOCK_NOT_EXISTS);
        }
        // 产成品信息
        MaterialStockDO product = stockList.get(0);
        // 基于任务单获取生产领料单
        IssueheaderDTO issueHeader = new IssueheaderDTO();
        issueHeader.setTaskId(task.getId());
        issueHeader.setWorkorderCode(workorder.getWorkorderCode());
        List<IssueheaderDTO> issueHeaderList = issueApi.listIssueHeader(issueHeader);
        if (issueHeaderList.isEmpty()) {
            return error(ErrorCodeConstants.ISSUE_NOT_EXISTS);
        }
        IssueheaderDTO issueHeaderDTO = issueHeaderList.get(0);
        // 基于生产领料单获取生产领料单行
        IssueLineDTO issueLine = new IssueLineDTO();
        issueLine.setIssueId(issueHeaderDTO.getId());
        // 2025-1-17 追加报工单过滤(报工单与领料单身一对多)
        issueLine.setFeedbackStatus("Y");
        issueLine.setFeedbackCode(feedback.getFeedbackCode());
        List<IssueLineDTO> issueLineList = issueApi.listIssueLine(issueLine);
        /**
         * 流程撤销
         * 1. 生产领料单获取生产领料单身
         * 2. 单身对应库存追加回库存表
         * 3. 将产成品删除
         */
        // 1. 生产领料单获取生产领料单身
        for (IssueLineDTO issueLineDTO : issueLineList) {
            // 2. 单身对应库存追加回库存表
            MaterialStockDO materialStock = new MaterialStockDO();
            materialStock.setItemCode(issueLineDTO.getItemCode());
            materialStock.setBatchCode(issueLineDTO.getBatchCode());
            materialStock.setLocationId(issueLineDTO.getLocationId());
            materialStock.setAreaId(issueLineDTO.getAreaId());
            // 获取当前的bom库存信息
            MaterialStockExportReqVO reqBom = new MaterialStockExportReqVO();
            BeanUtils.copyProperties(materialStock, reqBom);
            List<MaterialStockDO> materialStockDOList = materialStockService.getMaterialStockList(reqBom);
            System.out.print(materialStockDOList.toString());
            if (materialStockDOList.isEmpty()) {
                return error(ErrorCodeConstants.MATERIAL_STOCK_NOT_EXISTS);
            }
            MaterialStockDO bomStock = materialStockDOList.get(0);
            // 追加库存撤销报工事务
            TransactionUpdateReqVO transaction_in = new TransactionUpdateReqVO();
            BeanUtils.copyBeanProp(transaction_in, bomStock);
            transaction_in.setTransactionType(transactionType_in);
            transaction_in.setTransactionFlag(1);//库存增加
            transaction_in.setTransactionQuantity(issueLineDTO.getQuantityIssued());
            transaction_in.setMaterialStockId(bomStock.getId());
            //库存,库区,库位信息继承原领料单
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(issueLineDTO.getWarehouseCode());
            transaction_in.setWarehouseId(warehouse.getId());
            transaction_in.setWarehouseCode(warehouse.getWarehouseCode());
            transaction_in.setWarehouseName(warehouse.getWarehouseName());
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(issueLineDTO.getLocationCode());
            transaction_in.setLocationId(location.getId());
            transaction_in.setLocationCode(location.getLocationCode());
            transaction_in.setLocationName(location.getLocationName());
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(issueLineDTO.getAreaCode());
            transaction_in.setAreaId(area.getId());
            transaction_in.setAreaCode(area.getAreaCode());
            transaction_in.setAreaName(area.getAreaName());
            transaction_in.setSourceDocId(issueHeaderDTO.getWorkorderId());
            transaction_in.setSourceDocCode(issueHeaderDTO.getWorkorderCode());
            transaction_in.setSourceDocLineId(issueLineDTO.getId());
            //设置入库相关联的出库事务ID
            transactionService.processTransaction(transaction_in);
        }
        // 构造产成品库存减少事务
        // 清空产成品数量
        TransactionUpdateReqVO transaction_out = new TransactionUpdateReqVO();
        BeanUtils.copyBeanProp(transaction_out, product);
        transaction_out.setTransactionType(transactionType_out);
        transaction_out.setTransactionFlag(-1);//库存减少
        BigDecimal transactionQuantity = new BigDecimal(String.valueOf(product.getQuantityOnhand()));
        transaction_out.setTransactionQuantity(transactionQuantity);
        transaction_out.setTransactionDate(LocalDateTime.now());
        transaction_out.setSourceDocId(issueHeaderDTO.getWorkorderId());
        transaction_out.setSourceDocCode(issueHeaderDTO.getWorkorderCode());
        transaction_out.setSourceDocLineId(issueHeaderDTO.getId());
        transactionService.processTransaction(transaction_out);

        // 开始修改报工单状态
        // 撤销报工暂时不回滚上料信息
        feedback.setStatus(Constant.ORDER_STATUS_PREPARE);
        feedbackService.updateFeedback(FeedbackConvert.INSTANCE.convert02(feedback));

        // 回滚任务单生产数量
        // task.setQuantity(task.getQuantity() - feedback.getQuantityFeedback());
        task.setQuantityProduced(task.getQuantityProduced() - feedback.getQuantityFeedback());
        task.setQuantityQuanlify(task.getQuantityQuanlify() - feedback.getQuantityQualified());
        task.setQuantityUnquanlify(task.getQuantityUnquanlify() - feedback.getQuantityUnquanlified());
        task.setDeleted(false);
        taskService.updateTask(TaskConvert.INSTANCE.convert01(task));

        // 移除产品产出记录单信息
        ProductProduceDO productRecord = new ProductProduceDO();
        productRecord.setWorkorderId(workorder.getId());
        productRecord.setTaskCode(task.getTaskCode());
        productRecord.setTaskId(task.getId());
        ProductProduceExportReqVO produceVO = new ProductProduceExportReqVO();
        BeanUtils.copyProperties(productRecord, produceVO);
        List<ProductProduceDO> productProduceList = productProduceService.getProductProduceList(produceVO);
        if (productProduceList.isEmpty()) {
            return error(ErrorCodeConstants.PRODUCT_PRODUCE_NOT_EXISTS);
        }
        ProductProduceDO productProduce = productProduceList.get(0);
        productProduceService.deleteProductProduce(productProduce.getId());

        // 移除产品产出记录单行信息
        ProductProduceLineDO productProduceLine = new ProductProduceLineDO();
        productProduceLine.setRecordId(productProduce.getId());
        ProductProduceLineExportReqVO linereqVO = new ProductProduceLineExportReqVO();
        BeanUtils.copyProperties(productProduceLine, linereqVO);
        List<ProductProduceLineDO> productProduceLineList = productProduceLineMapper.selectList(linereqVO);
        for (ProductProduceLineDO productProduceLineDO : productProduceLineList) {
            productProduceLineMapper.deleteById(productProduceLineDO);
        }

        // 移除物料消耗单信息
        ItemConsumeDO itemConsume = new ItemConsumeDO();
        itemConsume.setWorkorderId(workorder.getId());
        itemConsume.setTaskCode(task.getTaskCode());
        itemConsume.setTaskId(task.getId());
        ItemConsumeExportReqVO consumeVO = new ItemConsumeExportReqVO();
        BeanUtils.copyProperties(itemConsume, consumeVO);
        List<ItemConsumeDO> itemConsumeList = itemConsumeService.getItemConsumeList(consumeVO);
        if (itemConsumeList.isEmpty()) {
            return error(ErrorCodeConstants.ITEM_CONSUME_NOT_EXISTS);
        }
        ItemConsumeDO itemConsumeDO = itemConsumeList.get(0);
        itemConsumeService.deleteItemConsume(itemConsumeDO.getId());
        // 移除物料消耗单行信息
        ItemConsumeLineDO itemConsumeLine = new ItemConsumeLineDO();
        itemConsumeLine.setRecordId(itemConsumeDO.getId());
        ItemConsumeLineExportReqVO lineVO = new ItemConsumeLineExportReqVO();
        BeanUtils.copyProperties(itemConsumeLine, lineVO);
        List<ItemConsumeLineDO> itemConsumeLineList = itemConsumeLineMapper.selectList(lineVO);
        for (ItemConsumeLineDO itemConsumeLineDO : itemConsumeLineList) {
            itemConsumeLineMapper.deleteById(itemConsumeLineDO);
        }

        return success();
    }

    @PostMapping("/mergeFeedback")
    @Operation(summary = "合并生产报工单")
    @PreAuthorize("@ss.hasPermission('pro:feedback:merge')")
    public CommonResult<Long> mergeFeedback(@RequestBody List<Long> feedbackIds) {
        // 获取所有选中的报工单
        List<FeedbackDO> feedbackList = feedbackService.getFeedbackList(feedbackIds);
        if (feedbackList.isEmpty()) {
            return error(ErrorCodeConstants.FEEDBACK_NOT_EXISTS);
        }

        // 校验所有报工单是否具有相同的工单号、工序代码和产品代码
        FeedbackDO first = feedbackList.get(0);
        boolean allSame = feedbackList.stream().allMatch(f ->
                Objects.equals(f.getItemCode(), first.getItemCode()) &&
                        Objects.equals(f.getProcessCode(), first.getProcessCode()) &&
                        Objects.equals(f.getWorkorderCode(), first.getWorkorderCode())
        );

        if (!allSame) {
            return error(ErrorCodeConstants.FEEDBACK_NOT_SAME);
        }

        Long warehouseId = null;
        Long locationId = null;
        Long areaId = null;
        for (FeedbackDO feedback : feedbackList) {
            // 校验是否存在未入库单据信息
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setBatchCode(feedback.getBatchCode());
            exportReqVO.setItemCode(feedback.getItemCode());
            List<MaterialStockDO> stockList = materialStockService.getMaterialStockList(exportReqVO);
            if (stockList.isEmpty()) {
                return error(ErrorCodeConstants.MATERIAL_STOCK_NOT_EXISTS);
            }
            MaterialStockDO stock = stockList.get(0);
            if (stock.getRecptStatus().equals("N")) {
                return error(ErrorCodeConstants.MATERIAL_STOCK_NOT_RECEPT);
            }
            warehouseId = stock.getWarehouseId();
            locationId = stock.getLocationId();
            areaId = stock.getAreaId();
        }

        WorkorderDO workorder = workorderService.getWorkorder(first.getWorkorderId());
        String transactionType_out = Constant.TRANSACTION_TYPE_WAREHOUSE_TRANS_OUT;

        // 合并报工单
        FeedbackDO mergedFeedback = new FeedbackDO();
        // 生成三位数随机数
        int random = (int) ((Math.random() * 9 + 1) * 100);
        mergedFeedback.setFeedbackCode(new StringBuffer().append("AMBG01").append("-").append(first.getTaskCode()).append("-").append(random).toString());
        mergedFeedback.setFeedbackType(first.getFeedbackType());
        mergedFeedback.setWorkorderId(first.getWorkorderId());
        mergedFeedback.setWorkorderCode(first.getWorkorderCode());
        mergedFeedback.setWorkorderName(first.getWorkorderName());
        mergedFeedback.setTaskId(first.getTaskId());
        mergedFeedback.setTaskCode(first.getTaskCode());
        mergedFeedback.setProcessCode(first.getProcessCode());
        mergedFeedback.setProcessName(first.getProcessName());
        mergedFeedback.setProcessId(first.getProcessId());
        mergedFeedback.setItemCode(first.getItemCode());
        mergedFeedback.setItemName(first.getItemName());
        mergedFeedback.setItemId(first.getItemId());
        mergedFeedback.setUnitOfMeasure(first.getUnitOfMeasure());
        mergedFeedback.setSpecification(first.getSpecification());
        mergedFeedback.setWorkstationName(first.getWorkstationName());
        mergedFeedback.setWorkstationCode(first.getWorkstationCode());
        mergedFeedback.setWorkstationId(first.getWorkstationId());
        mergedFeedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);

        // 循环feedbackList, 将报工单号基于逗号拼接为字符串
        StringBuilder sb = new StringBuilder();
        for (FeedbackDO feedback : feedbackList) {
            sb.append(feedback.getFeedbackCode()).append(",");
        }
        mergedFeedback.setOriginCode(sb.toString().substring(0, sb.length() - 1));

        // 获得用户基本信息
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        AdminUserRespDTO userDTO = adminUserApi.getUser(loginUserId);
        mergedFeedback.setNickName(userDTO.getNickname());
        mergedFeedback.setUserName(userDTO.getUsername());

        mergedFeedback.setQuantity(first.getQuantity());
        mergedFeedback.setQuantityFeedback(feedbackList.stream().mapToDouble(FeedbackDO::getQuantityFeedback).sum());
        mergedFeedback.setQuantityQualified(feedbackList.stream().mapToDouble(FeedbackDO::getQuantityQualified).sum());
        mergedFeedback.setQuantityUnquanlified(feedbackList.stream().mapToDouble(FeedbackDO::getQuantityUnquanlified).sum());

        Long mergedId = feedbackService.createFeedback(FeedbackConvert.INSTANCE.convert01(mergedFeedback));

        FeedbackDO merged = feedbackService.getFeedback(mergedId);
        // 产成品入库
        //生成产品产出记录单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(merged, FeedbackDTO.class);
        ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
        //执行产品产出入线边库
        executeProductProduce(merged, productRecord, workorder, warehouseId, locationId, areaId);

        // 移除原报工单库存信息
        for (FeedbackDO feedback : feedbackList) {
            // 移除原报工单库存信息
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setBatchCode(feedback.getBatchCode());
            exportReqVO.setItemCode(feedback.getItemCode());
            List<MaterialStockDO> stockList = materialStockService.getMaterialStockList(exportReqVO);
            if (stockList.isEmpty()) {
                return error(ErrorCodeConstants.MATERIAL_STOCK_NOT_EXISTS);
            }
            MaterialStockDO stock = stockList.get(0); // 扣减库存
            // 追加库存撤销报工事务
            TransactionUpdateReqVO transaction_out = new TransactionUpdateReqVO();
            BeanUtils.copyBeanProp(transaction_out, stock);
            transaction_out.setTransactionType(transactionType_out);
            transaction_out.setTransactionFlag(-1);//库存减少
            BigDecimal transactionQuantity = new BigDecimal(String.valueOf(feedback.getQuantityQualified()));
            transaction_out.setTransactionQuantity(transactionQuantity);
            transaction_out.setMaterialStockId(stock.getId());
            //库存,库区,库位信息继承原单
            WarehouseDO warehouse = warehouseService.selectWmWarehouseByWarehouseCode(stock.getWarehouseCode());
            transaction_out.setWarehouseId(warehouse.getId()); //库区
            transaction_out.setWarehouseCode(warehouse.getWarehouseCode());
            transaction_out.setWarehouseName(warehouse.getWarehouseName()); //库区
            StorageLocationDO location = storageLocationService.selectWmStorageLocationByLocationCode(stock.getLocationCode());
            transaction_out.setLocationId(location.getId()); //库位
            transaction_out.setLocationCode(location.getLocationCode());
            transaction_out.setLocationName(location.getLocationName()); //库位
            StorageAreaDO area = storageAreaService.selectWmStorageAreaByAreaCode(stock.getAreaCode());
            transaction_out.setAreaId(area.getId()); //库区
            transaction_out.setAreaCode(area.getAreaCode());
            transaction_out.setAreaName(area.getAreaName()); //库区
            transaction_out.setSourceDocId(feedback.getWorkorderId()); //来源单据ID
            transaction_out.setSourceDocCode(feedback.getWorkorderCode()); //来源单据编号
            transaction_out.setSourceDocLineId(feedback.getId()); //来源单据行ID
            transaction_out.setRecptStatus("Y"); // 合并的单据都是已入库的
            //设置入库相关联的出库事务ID
            transactionService.processTransaction(transaction_out);
            // 将原报工单数量清零
            feedback.setQuantityQualified(0.0);
            feedback.setQuantityUnquanlified(0.0);
            feedback.setQuantityFeedback(0.0);
            feedbackService.updateFeedback(FeedbackConvert.INSTANCE.convert02(feedback));
        }
        return success(mergedId);
    }


    /**
     * 看板: 各车间产量
     * @param tenantId
     * @return
     */
    @GetMapping("/workshop-capacity")
    public Map<String, Object> getWorkshopCapacity() {
        List<Map<String, Object>> source = feedbackService.getCapacity();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("dimensions", Arrays.asList("name", "value"));
        result.put("source", source);
        return result;
    }


}