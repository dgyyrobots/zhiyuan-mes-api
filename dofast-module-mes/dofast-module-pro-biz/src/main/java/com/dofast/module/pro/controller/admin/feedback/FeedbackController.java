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
import com.dofast.module.mes.controller.admin.mditem.vo.MdItemExportReqVO;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.service.mditem.MdItemService;
import com.dofast.module.mes.service.mdworkstation.MdWorkstationService;
import com.dofast.module.mes.service.mdworkstationworker.MdWorkstationWorkerService;
import com.dofast.module.pro.api.FeedbackApi.dto.FeedbackDTO;
import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import com.dofast.module.pro.controller.admin.feedback.vo.*;
import com.dofast.module.pro.controller.admin.task.vo.TaskUpdateReqVO;
import com.dofast.module.pro.controller.admin.workorder.vo.WorkorderUpdateReqVO;
import com.dofast.module.pro.convert.feedback.FeedbackConvert;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.feedback.FeedbackService;
import com.dofast.module.pro.service.routeprocess.RouteProcessService;
import com.dofast.module.pro.service.task.TaskService;
import com.dofast.module.pro.service.workorder.WorkorderService;
import com.dofast.module.qms.api.oqcApi.OqcApi;
import com.dofast.module.qms.api.oqcApi.dto.OqcDTO;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.trade.api.mixinorder.MixinOrderApi;
import com.dofast.module.wms.controller.admin.itemconsume.vo.ItemConsumeUpdateReqVO;
import com.dofast.module.wms.controller.admin.itemrecpt.vo.ItemRecptUpdateReqVO;
import com.dofast.module.wms.controller.admin.productproduce.vo.ProductProduceUpdateReqVO;
import com.dofast.module.wms.controller.admin.productproduceline.vo.ProductProduceLineExportReqVO;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeTxBean;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptDO;
import com.dofast.module.wms.dal.dataobject.itemrecpt.ItemRecptTxBean;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProduceDO;
import com.dofast.module.wms.dal.dataobject.productproduce.ProductProductTxBean;
import com.dofast.module.wms.dal.dataobject.productproduceline.ProductProduceLineDO;
import com.dofast.module.wms.service.itemconsume.ItemConsumeService;
import com.dofast.module.wms.service.itemrecpt.ItemRecptService;
import com.dofast.module.wms.service.productproduce.ProductProduceService;
import com.dofast.module.wms.service.productproduceline.ProductProduceLineService;
import com.dofast.module.wms.service.productsalse.ProductSalseService;
import com.dofast.module.wms.service.storagecore.StorageCoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

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

    @PostMapping("/create")
    @Operation(summary = "创建生产报工记录")
    @PreAuthorize("@ss.hasPermission('pro:feedback:create')")
    public CommonResult<Long> createFeedback(@Valid @RequestBody FeedbackCreateReqVO createReqVO) {
        return success(feedbackService.createFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产报工记录")
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    public CommonResult<Boolean> updateFeedback(@Valid @RequestBody FeedbackUpdateReqVO updateReqVO) {
        feedbackService.updateFeedback(updateReqVO);
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
        return success(FeedbackConvert.INSTANCE.convert(feedback));
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
     * @param recordId
     * @return
     */
    @PreAuthorize("@ss.hasPermission('pro:feedback:update')")
    @Operation(summary = "执行生产报工")
    @Transactional
    @PutMapping("/{recordId}")
    public CommonResult execute(@PathVariable("recordId") Long recordId){

        if(!StrUtils.isNotNull(recordId)){
            return error(ErrorCodeConstants.FEEDBACK_NEED_SAVE_FIRST);
        }

        FeedbackDO feedback= feedbackService.getFeedback(recordId);

        if(feedback.getQuantityFeedback().compareTo(Double.valueOf(0)) !=1){
            return error(ErrorCodeConstants.FEEDBACK_NUM_IS_ZERO);
        }
        WorkorderDO workorder = workorderService.getWorkorder(feedback.getWorkorderId());
        if ( workorder.getQuantityProduced() > feedback.getQuantity()){
            return error(QUENTITYP_RODUCED_IS_MORE);
        }
        //更新生产任务的生产数量
        TaskDO task = taskService.getTask(feedback.getTaskId());
        Double quantityProduced,quantityQuanlify,quantityUnquanlify;
        quantityQuanlify = task.getQuantityQuanlify()==null? 0:task.getQuantityQuanlify();
        quantityUnquanlify = task.getQuantityUnquanlify() ==null? 0:task.getQuantityUnquanlify();
        quantityProduced = task.getQuantityProduced()==null? 0:task.getQuantityProduced();
//        task.setQuantityProduced(feedback.getQuantityFeedback());
        task.setQuantityQuanlify(quantityQuanlify + (feedback.getQuantityQualified()));
        task.setQuantityUnquanlify(quantityUnquanlify + feedback.getQuantityUnquanlified());
        task.setDeleted(true);
        TaskUpdateReqVO taskUpdateReqVO = BeanUtil.toBean(task, TaskUpdateReqVO.class);
        taskService.updateTask(taskUpdateReqVO);

        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        if(routeProcessService.checkKeyProcess(feedback)){
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
        }

        //根据当前工序的物料BOM配置，进行物料消耗
        //先生成消耗单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ItemConsumeDO itemConsume = itemConsumeService.generateItemConsume(feedbackDTO);
        if(StrUtils.isNotNull(itemConsume)){
            //再执行库存消耗动作
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
     * @param record
     */
    private void executeProductProduce(ProductProduceDO record, WorkorderDO workorder){
        List<ProductProductTxBean> beans = productProduceService.getTxBeans(record.getId());
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
        productProduceService.updateProductProduce(productProduceUpdateReqVO);
    }

    /**
     * 执行物料消耗库存动作
     * @param record
     */
    private void executeItemConsume(ItemConsumeDO record){
        //需要在此处进行分批次领料的线边库扣减
        List<ItemConsumeTxBean> beans = itemConsumeService.getTxBeans(record.getId());
        storageCoreService.processItemConsume(beans);
        record.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        ItemConsumeUpdateReqVO itemConsumeUpdateReqVO = BeanUtil.toBean(record, ItemConsumeUpdateReqVO.class);
        itemConsumeService.updateItemConsume(itemConsumeUpdateReqVO);
    }

    /**
     * 执行物料产出入线边库动作
     * @param record
     */
    private void executeItemProduce(ItemRecptDO record){
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
        if(!StrUtils.isNotNull(id)){
            return error(ErrorCodeConstants.FEEDBACK_NEED_SAVE_FIRST);
        }
        FeedbackDO feedback= feedbackService.getFeedback(id);
        if(status.equals("UNAPPROVED")){
            FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
            feedbackUpdateReqVO.setStatus("UNAPPROVED");
            feedbackService.updateFeedback(feedbackUpdateReqVO);
            return error(ErrorCodeConstants.FEEDBACK_NOT_APPROVED);
        }

        if(feedback.getQuantityFeedback().compareTo(Double.valueOf(0)) !=1){
            return error(ErrorCodeConstants.FEEDBACK_NUM_IS_ZERO);
        }

        WorkorderDO workorder = workorderService.getWorkorder(feedback.getWorkorderId());
        if ( workorder.getQuantityProduced() > feedback.getQuantity()){
            return error(QUENTITYP_RODUCED_IS_MORE);
        }
        //更新生产任务的生产数量
        TaskDO task = taskService.getTask(feedback.getTaskId());
        Double quantityProduced,quantityQuanlify,quantityUnquanlify;
        quantityQuanlify = task.getQuantityQuanlify()==null? 0:task.getQuantityQuanlify();
        quantityUnquanlify = task.getQuantityUnquanlify() ==null? 0:task.getQuantityUnquanlify();
        quantityProduced = task.getQuantityProduced()==null? 0:task.getQuantityProduced();
        task.setQuantityProduced(quantityProduced + feedback.getQuantityFeedback());
        task.setQuantityQuanlify(quantityQuanlify + feedback.getQuantityQualified());
        task.setQuantityUnquanlify(quantityUnquanlify + feedback.getQuantityUnquanlified());
        TaskUpdateReqVO taskUpdateReqVO = BeanUtil.toBean(task, TaskUpdateReqVO.class);
        taskService.updateTask(taskUpdateReqVO);
        //更新工单的生产数量
        workorder.setQuantityProduced(quantityProduced + feedback.getQuantityFeedback());
        WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
        workorderService.updateWorkorder(workorderUpdateReqVO);

        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        checkKeyProcess(feedback, workorder);

        //父工单操作
        parentOrder(feedback, workorder);

        //根据当前工序的物料BOM配置，进行物料消耗
        //先生成消耗单
        FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);
        ItemConsumeDO itemConsume = itemConsumeService.generateItemConsume(feedbackDTO);
        if(StrUtils.isNotNull(itemConsume)){
            //再执行库存消耗动作
            executeItemConsume(itemConsume);
        }

        //更新报工单的状态
        feedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        FeedbackUpdateReqVO feedbackUpdateReqVO = BeanUtil.toBean(feedback, FeedbackUpdateReqVO.class);
        feedbackService.updateFeedback(feedbackUpdateReqVO);
        return success();
    }

    private void checkKeyProcess(FeedbackDO feedback, WorkorderDO workorder){
        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        if(routeProcessService.checkKeyProcess(feedback)){

            MdItemDO itemDO = mdItemService.getMdItem(workorder.getProductId());
            //更新生产工单的生产数量
            Double produced = workorder.getQuantityProduced() == null? 0:workorder.getQuantityProduced();
            Double feedBackQuantity = feedback.getQuantityFeedback() ==null? 0:feedback.getQuantityFeedback();
            workorder.setQuantityProduced( produced + feedBackQuantity);
            WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
            //判断是否排产数量加上已生产数量是否与排产数量相等
            workorderUpdateReqVO.setStatus(UserConstants.ORDER_STATUS_FINISHED);
            workorderService.updateWorkorder(workorderUpdateReqVO);

            FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);

            //判断产品里的是产品还是物料
            if(itemDO.getItemOrProduct().equals("ITEM")){
                //生成物料产出记录单
                ItemRecptDO itemRecptDO = itemRecptService.generateItemRecpt(feedbackDTO);
                //执行物料产出入线边库
                executeItemProduce(itemRecptDO);
            }else {
                //生成产品产出记录单
                ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
                //执行产品产出入线边库
                executeProductProduce(productRecord,workorder);
            }

        }
    }

    private void parentOrder(FeedbackDO feedback, WorkorderDO workorder){
        //父工单操作
        if (workorder.getParentId() != null && workorder.getParentId() != 0 && routeProcessService.checkKeyProcess(feedback)){
            WorkorderDO parentOrder = workorderService.getWorkorder(workorder.getParentId());
            if (parentOrder.getParentId() != null && parentOrder.getParentId() != 0){
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
        TaskDO task = taskService.getTask(feedback.getTaskId(),feedback.getProcessId());
        feedback.setQuantityFeedback(feedback.getQuantity());
        //判断报工是否超出排产数量
        if (task.getQuantityProduced()+feedback.getQuantityFeedback()>task.getQuantity()){
            return error(QUENTITYP_RODUCED_IS_MORE);
        }
        //报工数量不能为空
        if(feedback.getQuantityFeedback() <= Double.valueOf(0)){
            return error(ErrorCodeConstants.FEEDBACK_NUM_IS_ZERO);
        }
        task.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        //更新任务已完成数量
        task.setQuantityProduced(task.getQuantityProduced()+feedback.getQuantityFeedback());
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
        if(StrUtils.isNotNull(itemConsume)){
            //再执行库存消耗动作
            executeItemConsume(itemConsume);
        }
        return success("报工成功，请继续扫码");
    }

    private void checkKeyProcessOneClick(FeedbackDO feedback, WorkorderDO workorder){
        //如果是关键工序，则更新当前工单的已生产数量，进行产品产出动作
        if(routeProcessService.checkKeyProcess(feedback)){

            //判断报工是否超出工单排产数量
            if (workorder.getQuantityProduced()+feedback.getQuantityFeedback()>workorder.getQuantity()){
                throw  exception(QUENTITYP_RODUCED_IS_MORE);
            }

            MdItemDO itemDO = mdItemService.getMdItem(workorder.getProductId());
            //更新生产工单的生产数量
            Double produced = workorder.getQuantityProduced() == null? 0:workorder.getQuantityProduced();
            Double feedBackQuantity = feedback.getQuantityFeedback() ==null? 0:feedback.getQuantityFeedback();
            workorder.setQuantityProduced( produced + feedBackQuantity);
            WorkorderUpdateReqVO workorderUpdateReqVO = BeanUtil.toBean(workorder, WorkorderUpdateReqVO.class);
            //判断是否排产数量加上已生产数量是否与排产数量相等
            workorderUpdateReqVO.setStatus(UserConstants.ORDER_STATUS_FINISHED);
            workorderService.updateWorkorder(workorderUpdateReqVO);

            FeedbackDTO feedbackDTO = BeanUtil.toBean(feedback, FeedbackDTO.class);

            //判断产品里的是产品还是物料
            if(itemDO.getItemOrProduct().equals("ITEM")){
                //生成物料产出记录单
                ItemRecptDO itemRecptDO = itemRecptService.generateItemRecpt(feedbackDTO);
                //执行物料产出入线边库
                executeItemProduce(itemRecptDO);
            }else {
                //生成产品产出记录单
                ProductProduceDO productRecord = productProduceService.generateProductProduce(feedbackDTO);
                //执行产品产出入线边库
                executeProductProduce(productRecord,workorder);
            }

        }
    }
}
