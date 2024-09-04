package com.dofast.module.pro.controller.pad.protaskissuemob;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.controller.admin.mdproductsop.vo.MdProductSopExportReqVO;
import com.dofast.module.mes.controller.admin.mdproductsop.vo.MdProductSopRespVO;
import com.dofast.module.mes.convert.mdproductsop.MdProductSopConvert;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;
import com.dofast.module.mes.service.mdproductsop.MdProductSopService;
import com.dofast.module.pro.controller.admin.taskissue.vo.*;
import com.dofast.module.pro.convert.taskissue.TaskIssueConvert;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.dataobject.taskissue.TaskIssueDO;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import com.dofast.module.pro.service.task.TaskService;
import com.dofast.module.pro.service.taskissue.TaskIssueService;
import com.dofast.module.pro.service.transorder.TransOrderService;
import com.dofast.module.wms.controller.admin.issueheader.vo.IssueHeaderExportReqVO;
import com.dofast.module.wms.controller.admin.issueline.vo.IssueLineListVO;
import com.dofast.module.wms.dal.dataobject.issueheader.IssueHeaderDO;
import com.dofast.module.wms.dal.dataobject.issueline.IssueLineDO;
import com.dofast.module.wms.service.issueheader.IssueHeaderService;
import com.dofast.module.wms.service.issueline.IssueLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "PAD生产管理 - 生产任务投料")
@RestController
@RequestMapping("/mobile/pro/taskissue")
@Validated
public class PadTaskIssueController extends PadBaseController {

    @Resource
    private TaskIssueService taskIssueService;

    @Resource
    private TaskService taskService;

    @Resource
    private MdProductSopService mdProductSopService;

    @Resource
    private IssueHeaderService issueHeaderService;

    @Resource
    private IssueLineService issueLineService;

    @Resource
    private TransOrderService transOrderService;

    /**
     * 获取当前产品的SOP
     * @param mdProdutSop
     * @return
     */
    @GetMapping("getSopList")
    public AjaxResult getSopList(MdProductSopDO mdProdutSop){
        MdProductSopExportReqVO  mdProductSopExportReqVO = BeanUtil.toBean(mdProdutSop, MdProductSopExportReqVO.class);
        List<MdProductSopDO> list = mdProductSopService.getMdProductSopList(mdProductSopExportReqVO);
        return AjaxResult.success(list);
    }

    /**
     * 查询当前工作站、当前任务的投料清单
     * 至少提供workstationId、taskId两个参数
     */
    ///@PreAuthorize("@ss.hasPermi('mes:pro:taskissue:list')")
    @GetMapping("/getlist")
    public AjaxResult getIssueList(TaskIssueDO proTaskIssue) {
        TaskIssueExportReqVO taskIssueExportReqVO = BeanUtil.toBean(proTaskIssue, TaskIssueExportReqVO.class);
        List<TaskIssueDO> list = taskIssueService.getTaskIssueList(taskIssueExportReqVO);
        return AjaxResult.success(list);
    }

    /**
     * 查询当前工作站、当前任务可用的的领料清单
     * 如果某个领料单是领出到当前工作站或者当前任务的，则可以查询到
     */
    @PreAuthenticated
    @GetMapping("/getReserveIssueList")
    public AjaxResult c(TaskIssueDO taskIssueDO){
        TaskIssueExportReqVO proTaskIssue = BeanUtil.toBean(taskIssueDO, TaskIssueExportReqVO.class);
        IssueHeaderDO param = new IssueHeaderDO();
        //领料单上指定了工作站
        if(PadStringUtils.isNotNull(proTaskIssue.getWorkstationId())){
            param.setWorkstationId(proTaskIssue.getWorkstationId());
        }

        //领料单上指定了生产工单
        if(PadStringUtils.isNotNull(proTaskIssue.getWorkorderId())){
            param.setWorkorderId(proTaskIssue.getWorkorderId());
        }

        //领料单上指定了生产任务
        if(PadStringUtils.isNotNull(proTaskIssue.getTaskId())){
            param.setTaskId(proTaskIssue.getTaskId());
        }

        IssueHeaderExportReqVO issueHeaderExportReqVO = BeanUtil.toBean(param, IssueHeaderExportReqVO.class);
        List<IssueHeaderDO> issueList = issueHeaderService.getIssueHeaderList(issueHeaderExportReqVO);

        List<IssueLineDO> lines = new ArrayList<IssueLineDO>();
        if(CollUtil.isNotEmpty(issueList)){
            IssueLineDO p = new IssueLineDO();
            for (IssueHeaderDO header: issueList
            ) {
                p.setIssueId(header.getId());
                IssueLineListVO issueLineListVO = BeanUtil.toBean(p, IssueLineListVO.class);
                lines.addAll(issueLineService.selectList(issueLineListVO));
            }
        }

        List<TaskIssueDO> list = taskIssueService.getTaskIssueList(proTaskIssue);
        return AjaxResult.success(list);
    }

    /**
     * 通过新增或者扫码的方式添加某个流转单或者领料单到当前工作站、当前任务的投料清单中
     * 此接口只支持一次性添加一行物料。如果要添加整个领料单，则可在领料单上指定工作站和任务；或者使用addIssue接口
     */
    @PreAuthenticated
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(TaskIssueDO proTaskIssue)
    {
        //前端至少会传递taskId、workstationId、sourceLineId、sourceDocType几个字段过来
        TaskDO task = taskService.getTask(proTaskIssue.getTaskId());
        proTaskIssue.setWorkorderId(task.getWorkorderId());

        //如果是领料单
        if(UserConstants.TASK_ISSUE_DOC_TYPE_ISSUE.equals(proTaskIssue.getSourceDocType())){
            IssueLineDO line = issueLineService.getIssueLine(proTaskIssue.getSourceLineId());
            IssueHeaderDO header = issueHeaderService.getIssueHeader(line.getIssueId());
            proTaskIssue.setSourceDocId(line.getIssueId());//设置领料单ID
            proTaskIssue.setSourceDocCode(header.getIssueCode());//设置领料单编号
            proTaskIssue.setBatchCode(line.getBatchCode());
            proTaskIssue.setItemId(line.getItemId());
            proTaskIssue.setItemCode(line.getItemCode());
            proTaskIssue.setItemName(line.getItemName());
            proTaskIssue.setSpecification(line.getSpecification());
            proTaskIssue.setUnitOfMeasure(line.getUnitOfMeasure());
            proTaskIssue.setQuantityIssued(line.getQuantityIssued());
        }else{
            //如果是流转单
            TransOrderDO transOrder = transOrderService.getTransOrder(proTaskIssue.getSourceDocId());
            proTaskIssue.setTaskId(transOrder.getTaskId());
            proTaskIssue.setWorkorderId(transOrder.getWorkorderId());
            proTaskIssue.setSourceDocCode(transOrder.getTransOrderCode());
            proTaskIssue.setBatchCode(transOrder.getBatchCode());
            proTaskIssue.setSourceLineId(transOrder.getId());//这里直接使用头ID作为source_line_id，因为流转单不是头行结构
            proTaskIssue.setItemId(transOrder.getItemId());
            proTaskIssue.setItemCode(transOrder.getItemCode());
            proTaskIssue.setItemName(transOrder.getItemName());
            proTaskIssue.setUnitOfMeasure(transOrder.getUnitOfMeasure());
            proTaskIssue.setQuantityIssued(transOrder.getQuantityTransfered());//流转单的流转数量作为投料数量
        }
        //不能重复添加
        TaskIssueBaseVO taskIssueBaseVO = BeanUtil.toBean(proTaskIssue, TaskIssueBaseVO.class);
        if(UserConstants.NOT_UNIQUE.equals(taskIssueService.checkUnique(taskIssueBaseVO))){
            return AjaxResult.error("物料已存在");
        }
        TaskIssueCreateReqVO taskIssueCreateReqVO = BeanUtil.toBean(proTaskIssue, TaskIssueCreateReqVO.class);
        taskIssueService.createTaskIssue(taskIssueCreateReqVO);
        return toAjax(true);
    }


    /**
     * 通过新增或者扫码的方式添加某个流转单或者领料单到当前工作站、当前任务的投料清单中
     * 此接口专门用于一次性添加整个领料单的场景，传递的proTaskIssue参数需要source_doc_id为对应的领料单头ID
     */
    @PreAuthenticated
    @PostMapping("/addIssue")
    public AjaxResult addIssue(@RequestBody TaskIssueDO proTaskIssue)
    {
        if(!UserConstants.TASK_ISSUE_DOC_TYPE_ISSUE.equals(proTaskIssue.getSourceDocType())){
            return AjaxResult.error("请选择或扫描生产领料单！");//这里只支持添加整个领料单
        }
        Long issueId = proTaskIssue.getSourceDocId();

        IssueLineDO param = new IssueLineDO();
        param.setIssueId(issueId);
        IssueLineListVO issueLineListVO = BeanUtil.toBean(param, IssueLineListVO.class);
        List<IssueLineDO> issueLines = issueLineService.selectList(issueLineListVO);

        if(CollUtil.isEmpty(issueLines)){
            return AjaxResult.error("领料单行为空");
        }

        for (IssueLineDO line: issueLines
        ) {
            TaskIssueDO taskIssue = new TaskIssueDO();
            taskIssue.setTaskId(proTaskIssue.getTaskId());
            taskIssue.setWorkstationId(proTaskIssue.getWorkstationId());
            taskIssue.setWorkorderId(proTaskIssue.getWorkorderId());
            taskIssue.setSourceDocType(UserConstants.TASK_ISSUE_DOC_TYPE_ISSUE);
            taskIssue.setSourceDocId(issueId);
            //taskIssue.setSourceDocCode(); //领料单编号先不设置，需要的时候关联查询即可
            taskIssue.setBatchCode(line.getBatchCode());
            taskIssue.setSourceLineId(line.getId());
            taskIssue.setItemId(line.getItemId());
            taskIssue.setItemCode(line.getItemCode());
            taskIssue.setItemName(line.getItemName());
            taskIssue.setSpecification(line.getSpecification());
            taskIssue.setUnitOfMeasure(line.getUnitOfMeasure());
            taskIssue.setQuantityIssued(line.getQuantityIssued());
            //taskIssue.setQuantityAvailable(); //可用数量，如果需要则要实时计算
            taskIssue.setQuantityUsed(new BigDecimal(0)); //新添加的都默认为0；添加后删除再次添加也是0；实际使用量应该根据流转单计算

            //不能重复添加
            TaskIssueBaseVO taskIssueBaseVO = BeanUtil.toBean(taskIssue, TaskIssueBaseVO.class);
            if(UserConstants.NOT_UNIQUE.equals(taskIssueService.checkUnique(taskIssueBaseVO))){
                return AjaxResult.error("物料已添加过");
            }
            TaskIssueCreateReqVO taskIssueCreateReqVO = BeanUtil.toBean(taskIssue, TaskIssueCreateReqVO.class);
            taskIssueService.createTaskIssue(taskIssueCreateReqVO);
        }

        return AjaxResult.success();
    }

    /**
     * 删除生产任务投料
     */
    @PreAuthenticated
    @PostMapping("/{recordId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable Long recordId)
    {
        taskIssueService.deleteTaskIssue(recordId);
        return toAjax(true);
    }
}
