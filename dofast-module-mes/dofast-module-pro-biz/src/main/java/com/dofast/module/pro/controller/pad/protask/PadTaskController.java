package com.dofast.module.pro.controller.pad.protask;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.framework.web.core.util.WebFrameworkUtils;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;
import com.dofast.module.mes.service.mdworkstation.MdWorkstationService;
import com.dofast.module.mes.service.mdworkstationworker.MdWorkstationWorkerService;
import com.dofast.module.pro.controller.admin.feedback.vo.FeedbackCreateReqVO;
import com.dofast.module.pro.controller.admin.task.vo.TaskExportReqVO;
import com.dofast.module.pro.controller.admin.task.vo.TaskUpdateReqVO;
import com.dofast.module.pro.controller.admin.taskissue.vo.TaskIssueExportReqVO;
import com.dofast.module.pro.controller.admin.workorder.vo.WorkorderExportReqVO;
import com.dofast.module.pro.controller.pad.protask.vo.PadTaskDetail;
import com.dofast.module.pro.controller.pad.protask.vo.PadTaskUpdateStatusReqVO;
import com.dofast.module.pro.controller.pad.protask.vo.ProTaskFeedbackReqVO;
import com.dofast.module.pro.convert.feedback.FeedbackConvert;
import com.dofast.module.pro.convert.task.TaskConvert;
import com.dofast.module.pro.dal.dataobject.feedback.FeedbackDO;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.service.feedback.FeedbackService;
import com.dofast.module.pro.service.task.TaskService;
import com.dofast.module.pro.service.taskissue.TaskIssueService;
import com.dofast.module.pro.service.workorder.WorkorderService;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import com.dofast.module.trade.api.mixinorder.MixinOrderApi;
import com.dofast.module.trade.api.mixinorder.dto.MixinOrderDTO;
import com.dofast.module.wms.service.issueheader.IssueHeaderService;
import com.dofast.module.wms.service.issueline.IssueLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "PAD生产管理 - 生产任务")
@Validated
@RestController
@RequestMapping("/mobile/pro/protask")
public class PadTaskController extends PadBaseController {

    @Autowired
    private TaskService proTaskService;

    @Autowired
    private FeedbackService proFeedbackService;

    @Autowired
    private MdWorkstationService mdWorkstationService;

    @Autowired
    private TaskIssueService proTaskIssueService;

    @Autowired
    private IssueHeaderService wmIssueHeaderService;

    @Autowired
    private IssueLineService wmIssueLineService;

    @Resource
    private WorkorderService workorderService;

    @Resource
    private MixinOrderApi mixinOrderApi;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private MdWorkstationWorkerService workstationWorkerService;

    @Resource
    private TaskService taskService;


    @GetMapping("/getlist")
    public AjaxResult getIssueList(TaskIssueDO proTaskIssue) {
        TaskIssueExportReqVO taskIssueExportReqVO = BeanUtil.toBean(proTaskIssue, TaskIssueExportReqVO.class);
        List<TaskIssueDO> list = proTaskIssueService.getTaskIssueList(taskIssueExportReqVO);
        return AjaxResult.success(list);
    }

    /**
     * 查询工作站的生产任务
     */
    @Operation(summary = "查询状态未完成的生产任务接口")
    @PreAuthenticated
    @GetMapping("/getTaskList")
    public AjaxResult list(TaskDO proTask)
    {
        TaskExportReqVO taskExportReqVO = BeanUtil.toBean(proTask, TaskExportReqVO.class);
        List<TaskDO> l = proTaskService.getMyTaskList(taskExportReqVO);
        List<PadTaskDetail> list = new ArrayList<>();
        if (!l.isEmpty()){
            for (TaskDO taskDO : l){
                PadTaskDetail padTaskDetail = BeanUtil.toBean(taskDO, PadTaskDetail.class);
                padTaskDetail.setTaskId(taskDO.getId());
                WorkorderDO workorderDO = workorderService.getWorkorder(taskDO.getWorkorderId());
                MixinOrderDTO mixinOrderDTO = mixinOrderApi.getMixinOrder(workorderDO.getMixinOrderId());
                padTaskDetail.setSaleNo(mixinOrderDTO.getSaleNo());
                list.add(padTaskDetail);
            }
        }
        return AjaxResult.success(list);
    }


    /**
     * 获取生产任务详细信息
     */
    @Operation(summary = "查询生产任务详情接口")
    @PreAuthenticated
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        TaskDO taskDO = proTaskService.getTask(taskId);
        PadTaskDetail padTaskDetail = TaskConvert.INSTANCE.convert03(taskDO);
        WorkorderDO workorderDO = workorderService.getWorkorder(taskDO.getWorkorderId());
        MixinOrderDTO mixinOrderDTO = mixinOrderApi.getMixinOrder(workorderDO.getMixinOrderId());
        padTaskDetail.setSaleNo(mixinOrderDTO.getSaleNo());
        return AjaxResult.success(padTaskDetail);
    }


    /**
     * 修改生产任务状态
     */
    @Operation(summary = "修改生产任务状态接口")
    @PostMapping("/change")
    @ResponseBody
    public AjaxResult changeStatus(PadTaskUpdateStatusReqVO proTask)
    {
        proTaskService.updateTaskStatus(proTask.getTaskId(), proTask.getStatus());
        return toAjax(true);
    }

    @PostMapping("/feedback")
    @ResponseBody
    public AjaxResult feedBack( ProTaskFeedbackReqVO taskFeedbackReqVO){
        FeedbackDO feedback = FeedbackConvert.INSTANCE.convert(taskFeedbackReqVO);

        TaskDO task = proTaskService.getTask(taskFeedbackReqVO.getTaskId());

        feedback.setProcessId(task.getProcessId());
        feedback.setProcessCode(task.getProcessCode());
        feedback.setProcessName(task.getProcessName());

        feedback.setFeedbackType("SELF");

        feedback.setTaskCode(task.getTaskCode());

        feedback.setWorkorderId(task.getWorkorderId());
        feedback.setWorkorderCode(task.getWorkorderCode());
        feedback.setWorkorderName(task.getWorkorderName());

        feedback.setItemId(task.getItemId());
        feedback.setItemCode(task.getItemCode());
        feedback.setItemName(task.getItemName());

        feedback.setQuantity(task.getQuantity().doubleValue());
        feedback.setFeedbackTime(LocalDateTime.now());

        if(feedback.getWorkstationId() == null){
            feedback.setWorkstationId(task.getWorkstationId());
        }

        MdWorkstationDO workstation = mdWorkstationService.getMdWorkstation(feedback.getWorkstationId());
        feedback.setWorkstationCode(workstation.getWorkstationCode());
        feedback.setWorkstationName(workstation.getWorkstationName());

        task.setQuantityProduced(task.getQuantityProduced() + feedback.getQuantityFeedback());
        task.setQuantityQuanlify(task.getQuantityQuanlify() + feedback.getQuantityQualified());
        task.setQuantityUnquanlify(task.getQuantityUnquanlify() + feedback.getQuantityUnquanlified());
        TaskUpdateReqVO taskUpdateReqVO = BeanUtil.toBean(task, TaskUpdateReqVO.class);
        proTaskService.updateTask(taskUpdateReqVO);
        FeedbackCreateReqVO feedbackCreateReqVO = BeanUtil.toBean(feedback, FeedbackCreateReqVO.class);
        proFeedbackService.createFeedback(feedbackCreateReqVO);
        return toAjax(true);
    }

    /**
     * 获取当前用户的生产任务
     */
    @Operation(summary = "获取当前用户的生产任务")
    @GetMapping("/get-task")
    @PreAuthenticated
    public AjaxResult getMyTask(@RequestParam("no") String no)
    {
        //根据销售单号查询工单
        MixinOrderDTO mixinOrderDTO = mixinOrderApi.getByNo(no);
        WorkorderExportReqVO workorderExportReqVO = new WorkorderExportReqVO();
        workorderExportReqVO.setMixinOrderId(mixinOrderDTO.getId());
        List<WorkorderDO> workorderDOS = workorderService.getWorkorderList(workorderExportReqVO);
        if (workorderDOS.isEmpty()){
            return AjaxResult.error(710002, "生产工单不存在");
        }
        List<TaskDO> taskDOS = new ArrayList<>();
        //判断当前用户的岗位
        AdminUserRespDTO adminUserRespDTO = adminUserApi.getUser(WebFrameworkUtils.getLoginUserId());
        if (CollectionUtil.isEmpty(adminUserRespDTO.getPostIds())) {
            return AjaxResult.error(716005, "当前订单不存在工单任务,请确认身份");
        }
        List<MdWorkstationWorkerDO>  workstationWorkers = workstationWorkerService.getMdWorkstationWorkerListByPostId(adminUserRespDTO.getPostIds());
        if (CollectionUtil.isEmpty(workstationWorkers)) {
            return AjaxResult.error(716005, "当前订单不存在工单任务,请确认身份");
        }
        Set<Long> workstationIds= CollectionUtils.convertSet(workstationWorkers, MdWorkstationWorkerDO::getWorkstationId);

        //更新生产任务的生产数量
        for (WorkorderDO workorderDO : workorderDOS){
            List<TaskDO> tasks = taskService.getTaskByOrder(workorderDO.getId());
            if (tasks == null){
                continue;
            }
            for (TaskDO task : tasks) {
                if (workstationIds.contains(task.getWorkstationId())){
                    taskDOS.add(task);
                }
            }
        }
        if (taskDOS.isEmpty()){
            return AjaxResult.error(716005, "当前订单不存在工单任务,请确认身份");
        }
        return AjaxResult.success(TaskConvert.INSTANCE.convertList(taskDOS));
    }
}
