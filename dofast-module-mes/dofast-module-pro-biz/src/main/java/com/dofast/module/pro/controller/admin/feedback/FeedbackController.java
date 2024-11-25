package com.dofast.module.pro.controller.admin.feedback;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.framework.tenant.core.aop.TenantIgnore;
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
import com.dofast.module.pro.controller.admin.feedbackmember.vo.FeedbackMemberCreateReqVO;
import com.dofast.module.pro.controller.admin.feedbackmember.vo.FeedbackMemberExportReqVO;
import com.dofast.module.pro.controller.admin.feedbackmember.vo.FeedbackMemberPageReqVO;
import com.dofast.module.pro.controller.admin.routeprocess.vo.RouteProcessExportReqVO;
import com.dofast.module.pro.controller.admin.task.vo.TaskUpdateReqVO;
import com.dofast.module.pro.controller.admin.workorder.vo.WorkorderUpdateReqVO;
import com.dofast.module.pro.convert.feedback.FeedbackConvert;
import com.dofast.module.pro.convert.task.TaskConvert;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.dataobject.feedbackmember.FeedbackMemberDO;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.feedback.FeedbackService;
import com.dofast.module.pro.service.feedbackmember.FeedbackMemberService;
import com.dofast.module.pro.service.route.RouteService;
import com.dofast.module.pro.service.routeprocess.RouteProcessService;
import com.dofast.module.pro.service.task.TaskService;
import com.dofast.module.pro.service.workorder.WorkorderService;
import com.dofast.module.qms.api.oqcApi.OqcApi;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.trade.api.mixinorder.MixinOrderApi;
import com.dofast.module.wms.api.WarehosueApi.dto.WarehouseDTO;
import com.dofast.module.wms.api.WarehouseApi.WarehouseApiImpl;
import com.dofast.module.wms.controller.admin.itemconsume.vo.ItemConsumeUpdateReqVO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.ItemRecptUpdateReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockCreateReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockExportReqVO;
import com.dofast.module.wms.controller.admin.materialstock.vo.MaterialStockUpdateReqVO;
import com.dofast.module.wms.controller.admin.productproduce.vo.ProductProduceUpdateReqVO;
import com.dofast.module.wms.controller.admin.productproduceline.vo.ProductProduceLineExportReqVO;
import com.dofast.module.wms.controller.admin.storagearea.vo.StorageAreaExportReqVO;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProductTxBean;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.module.wms.dal.dataobject.storagearea.StorageAreaDO;
import com.dofast.module.wms.dal.dataobject.storagelocation.StorageLocationDO;
import com.dofast.module.wms.dal.dataobject.warehouse.WarehouseDO;
import com.dofast.module.wms.service.itemconsume.ItemConsumeService;
import com.dofast.module.wms.service.itemrecpt.ItemRecptService;
import com.dofast.module.wms.service.materialstock.MaterialStockService;
import com.dofast.module.wms.service.productproduce.ProductProduceService;
import com.dofast.module.wms.service.productproduceline.ProductProduceLineService;
import com.dofast.module.wms.service.productsalse.ProductSalseService;
import com.dofast.module.wms.service.storagearea.StorageAreaService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import com.dofast.module.wms.service.storagelocation.StorageLocationService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUsername;
import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
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

    @PostMapping("/create")
    @Operation(summary = "创建生产报工记录")
    @PreAuthorize("@ss.hasPermission('pro:feedback:create')")
    public CommonResult<Long> createFeedback(@Valid @RequestBody FeedbackCreateReqVO createReqVO) {
        List<Map<String, Object>> list =createReqVO.getFeedbackMemberList();
        Long feedbackId = feedbackService.createFeedback(createReqVO);
        String taskCode  = createReqVO.getTaskCode();
        TaskDO task = taskService.getTask(taskCode);
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
        return success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产报工记录")
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    public CommonResult<Boolean> updateFeedback(@Valid @RequestBody FeedbackUpdateReqVO updateReqVO) {
        feedbackService.updateFeedback(updateReqVO);
        List<Map<String, Object>> list =updateReqVO.getFeedbackMemberList();
        FeedbackMemberExportReqVO pageReqVO = new FeedbackMemberExportReqVO();
        pageReqVO.setFeedbackId(String.valueOf(updateReqVO.getId()));
        List<FeedbackMemberDO> memberList = feedBackMemberService.getFeedbackMemberList(pageReqVO);
        for (FeedbackMemberDO member : memberList) {
            feedBackMemberService.deleteFeedbackMember(member.getId());
        }
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
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
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
        /**
         *  2024-11-15注释, 澳美没有关键工序概念
         *  根据当前任务校验当前是否有下一道工序
         *      有: 入下一道制成线边仓(状态待入库)
         *          线边仓库区有工序编码
         *      无: 入本制程线边仓(状态待入库)
         */

        // 澳美的前制程的产成品是下道制程的半半成品, 均视为产品
        //生成产品产出记录单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
        //执行产品产出入线边库
        executeProductProduce(productRecord, workorder);
        /*if(routeProcessService.checkKeyProcess(feedback)){
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
        }*/

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
        FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
        feedbackService.updateFeedback(feedbackUpdateReqVO);
        return CommonResult.success();
    }

    /**
     * 执行产品产出入线边库动作
     *
     * @param record
     */
    private void executeProductProduce(ProductProduceDO record, WorkorderDO workorder) {
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
        String taskCode = record.getTaskCode();
        TaskDO task = taskService.getTask(taskCode);
        List<FeedbackDO> feedBackList = feedbackService.getFeedbackListByTaskId(task.getId());
        FeedbackDO feedBack = feedBackList.get(0);
        String processCode = task.getProcessCode();
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
        MdItemTypeDO itemType = mdItemTypeService.getMdItemType(item.getItemTypeCode());
        // 开始追加库存信息
        materialStock.setItemId(item.getId());
        materialStock.setItemCode(item.getItemCode());
        materialStock.setItemTypeId(itemType.getId());
        materialStock.setWorkorderId(workorder.getId());
        materialStock.setWorkorderCode(workorder.getWorkorderCode());
        materialStock.setUnitOfMeasure(task.getUnitOfMeasure());
        materialStock.setQuantityOnhand(BigDecimal.valueOf(task.getQuantityQuanlify())); // 产成品数量为任务单合格数量

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String parentBatchCode = currentDate.format(formatter) + ThreadLocalRandom.current().nextInt(1000, 10000);
        if (nextProcessCode != null) {
            // 入下一个制成线边仓
            // 仓库
            WarehouseDTO warehouse = warehouseApiImpl.selectWmWarehouseByWarehouseCode(Constant.LINE_EDGE_CODE);
            // 库区
            StorageLocationDO location = locationService.getStorageLocation(nextProcessCode);
            // 库位, 目前没有WMS, 仅获取第一个库位(MES管控, 无需回传ERP)
            List<StorageAreaDO> area = areaService.getStorageAreaByLocationId(location.getId());
            // 开始录入库存
            materialStock.setItemName(item.getItemName() + "-" + process.getProcessCode() + "半成品");
            materialStock.setWarehouseCode(warehouse.getWarehouseCode()); // 线边仓
            materialStock.setWarehouseId(warehouse.getId());
            materialStock.setWarehouseName(warehouse.getWarehouseName());
            materialStock.setLocationCode(location.getLocationCode()); // 线边仓库区
            materialStock.setLocationId(location.getId());
            materialStock.setLocationName(location.getLocationName());
            materialStock.setAreaCode(area.get(0).getAreaCode()); // 线边仓库区
            materialStock.setAreaId(area.get(0).getId());
            materialStock.setAreaName(area.get(0).getAreaName());
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
            materialStock.setBatchCode(task.getParentBatchCode() +"-"+ serial);
            feedBack.setBatchCode(task.getParentBatchCode() +"-"+ serial);
            feedBack.setErpBatchCode(parentBatchCode);
        } else {
            // 入主仓
            // 仓库
            WarehouseDTO warehouse = warehouseApiImpl.selectWmWarehouseByWarehouseCode(Constant.WAREHOUSE_CODE);
            // 开始录入库存
            materialStock.setItemName(item.getItemName()); // 最后一道工序视为产成品
            materialStock.setWarehouseCode(warehouse.getWarehouseCode()); // 线边仓
            materialStock.setWarehouseId(warehouse.getId());
            materialStock.setWarehouseName(warehouse.getWarehouseName());
            // 传递至成品仓-基于正式库决定, 暂时写死
            StorageLocationDO location = locationService.getStorageLocation("ST001");
            StorageAreaDO area = areaService.getStorageArea("01");
            materialStock.setLocationId(location.getId());
            materialStock.setLocationCode(location.getLocationCode());
            materialStock.setLocationName(location.getLocationName());
            materialStock.setAreaId(area.getId());
            materialStock.setAreaCode(area.getAreaCode());
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
            materialStock.setBatchCode(task.getWorkorderCode() +"-"+ serial);
            feedBack.setBatchCode(task.getParentBatchCode() +"-"+ serial);
            feedBack.setErpBatchCode(parentBatchCode);
        }
        // 追加库存
        materialStockService.createMaterialStock(materialStock);
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
    public CommonResult updateFeedbackStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        if (!StrUtils.isNotNull(id)) {
            return error(ErrorCodeConstants.FEEDBACK_NEED_SAVE_FIRST);
        }
        FeedbackDO feedback = feedbackService.getFeedback(id);
        if (status.equals("UNAPPROVED")) {
            FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
            feedbackUpdateReqVO.setStatus("UNAPPROVED");
            feedbackService.updateFeedback(feedbackUpdateReqVO);
            return error(ErrorCodeConstants.FEEDBACK_NOT_APPROVED);
        }

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
        checkKeyProcess(feedback, workorder);

        //父工单操作
        parentOrder(feedback, workorder);

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
        executeProductProduce(productRecord, workorder);
        //更新报工单的状态
        feedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);
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
                executeProductProduce(productRecord, workorder);
            }

        }
    }

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
        for (Map<String, Object> map : objList) {
            // 基于当前的库存信息
            MaterialStockExportReqVO exportReqVO = new MaterialStockExportReqVO();
            exportReqVO.setItemCode((String) map.get("itemCode"));
            exportReqVO.setItemName((String) map.get("itemName"));
            exportReqVO.setBatchCode((String) map.get("batchCode"));
            List<MaterialStockDO> materialStock = materialStockService.getMaterialStockList(exportReqVO);
            materialStock.get(0).setRecptStatus("Y");
            materialStockService.updateMaterialStock(BeanUtil.toBean(materialStock.get(0), MaterialStockUpdateReqVO.class));
        }
        return "success";
    }

}