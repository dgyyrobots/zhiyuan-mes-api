package com.dofast.module.pro.controller.pad.protransordermob;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.controller.admin.transorder.vo.*;
import com.dofast.module.pro.convert.transorder.TransOrderConvert;
import com.dofast.module.pro.dal.dataobject.task.TaskDO;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import com.dofast.module.pro.service.task.TaskService;
import com.dofast.module.pro.service.transorder.TransOrderService;
import com.dofast.module.mes.controller.admin.Autocode.AutoCodeUtil;
import com.dofast.module.wms.api.BarcodeApi.BarCodeUtil;
import com.dofast.module.wms.controller.admin.barcode.vo.BarcodeBaseVO;
import com.dofast.module.wms.controller.admin.barcode.vo.BarcodeUpdateReqVO;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;
import com.dofast.module.wms.service.barcode.BarcodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "PAD生产管理 - 流转单")
@RestController
@RequestMapping("/mes/pro/trans-order")
@Validated
public class PadTransOrderController extends PadBaseController {

    @Resource
    private TransOrderService transOrderService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    @Autowired
    private TaskService taskService;

    @Autowired
    private BarcodeService barcodeService;

    @Operation(summary = "获取流转单清单")
    @GetMapping("/getList")
    public AjaxResult getList(TransOrderDO proTransOrder){
        TransOrderExportReqVO transOrderExportReqVO = BeanUtil.toBean(proTransOrder, TransOrderExportReqVO.class);
        List<TransOrderDO> list = transOrderService.getTransOrderList(transOrderExportReqVO);
        return AjaxResult.success(list);
    }

    /**
     * 获取流转单详细信息
     */
    @Operation(summary = "流转单详情查询接口")
    @PreAuthenticated
    @GetMapping("/getInfo")
    public AjaxResult getInfo(TransOrderDO proTransOrder)
    {
        TransOrderDO order = null;
        if(StrUtils.isNotNull(proTransOrder.getId())){
            order = transOrderService.getTransOrder(proTransOrder.getId());
        }

        if(PadStringUtils.isNotNull(proTransOrder.getTransOrderCode())){
            TransOrderDO param = new TransOrderDO();
            param.setTransOrderCode(proTransOrder.getTransOrderCode());
            TransOrderExportReqVO transOrderExportReqVO = BeanUtil.toBean(param, TransOrderExportReqVO.class);
            List<TransOrderDO> orders =transOrderService.getTransOrderList(transOrderExportReqVO);
            if(!CollectionUtil.isEmpty(orders)){
                order = orders.get(0);
            }
        }
        return AjaxResult.success(order);
    }


    /**
     * 新增流转单
     */
    @Operation(summary = "流转单新增接口")
    @PreAuthenticated
    @PostMapping
    public AjaxResult add(@RequestBody TransOrderDO proTransOrder) throws IOException
    {
        if(PadStringUtils.isNotNull(proTransOrder.getTransOrderCode())){
            String transOrderCdoe = autoCodeUtil.genSerialCode(UserConstants.TRANS_ORDER_CODE,"");
            proTransOrder.setTransOrderCode(transOrderCdoe);
        }

        if(PadStringUtils.isNotNull(proTransOrder.getTaskId())){
            TaskDO task =taskService.getTask(proTransOrder.getTaskId());
            proTransOrder.setTaskCode(task.getTaskCode());
            proTransOrder.setWorkstationId(task.getWorkstationId());
            proTransOrder.setWorkstationCode(task.getWorkstationCode());
            proTransOrder.setWorkstationName(task.getWorkstationName());
            proTransOrder.setProcessId(task.getProcessId());
            proTransOrder.setProcessCode(task.getProcessCode());
            proTransOrder.setProcessName(task.getProcessName());
            proTransOrder.setWorkorderId(task.getWorkorderId());
            proTransOrder.setWorkorderCode(task.getWorkorderCode());
            proTransOrder.setWorkorderName(task.getWorkorderName());
            //TODO:批次信息的获取
            proTransOrder.setItemId(task.getItemId());
            proTransOrder.setItemCode(task.getItemCode());
            proTransOrder.setItemName(task.getItemName());
            proTransOrder.setUnitOfMeasure(task.getUnitOfMeasure());
            proTransOrder.setSpecification(task.getSpecification());
        }else{
            return AjaxResult.error("请提供生产任务数据");
        }

        if(StrUtils.isNotNull((proTransOrder.getQuantityTransfered()))){
            return AjaxResult.error("请填写报工数量");
        }
        // proTransOrder.setCreator();
        TransOrderCreateReqVO transOrderCreateReqVO = BeanUtil.toBean(proTransOrder, TransOrderCreateReqVO.class);
        transOrderService.createTransOrder(transOrderCreateReqVO);

        //自动生成条码
        BarcodeDO code = new BarcodeDO();
        code.setBarcodeType(UserConstants.BARCODE_TYPE_TRANSORDER);
        code.setBarcodeContent("TRANSORDER-"+proTransOrder.getTransOrderCode());
        code.setBarcodeFormart(UserConstants.QR_CODE);
        code.setBussinessId(proTransOrder.getId());
        code.setBussinessCode(proTransOrder.getTransOrderCode());
        code.setEnableFlag(UserConstants.YES);
        BarcodeBaseVO baseVO = BeanUtil.toBean(code, BarcodeBaseVO.class);
        String path =barcodeService.generateBarcode(baseVO);
        code.setBarcodeUrl(path);
        BarcodeUpdateReqVO barcodeUpdateReqVO = BeanUtil.toBean(code, BarcodeUpdateReqVO.class);
        barcodeService.updateBarcode(barcodeUpdateReqVO);
        proTransOrder.setBarcodeUrl(path);
        TransOrderUpdateReqVO transOrderUpdateReqVO = BeanUtil.toBean(proTransOrder, TransOrderUpdateReqVO.class);
        transOrderService.updateTransOrder(transOrderUpdateReqVO);
        return AjaxResult.success(proTransOrder);
    }

    /**
     * 修改流转单
     */
    @Operation(summary = "流转单修改接口")
    @PreAuthenticated
    @PutMapping
    public AjaxResult edit(@RequestBody TransOrderDO proTransOrder)
    {
        TransOrderUpdateReqVO transOrderUpdateReqVO = BeanUtil.toBean(proTransOrder, TransOrderUpdateReqVO.class);
        transOrderService.updateTransOrder(transOrderUpdateReqVO);
        return toAjax(true);
    }
}
