package com.dofast.module.pro.controller.admin.transorder;

import com.dofast.module.mes.api.autocode.AutoCodeApi;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.api.BarCodeApi;
import com.dofast.module.wms.api.BarcodeApi.BarCodeUtil;
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
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.pro.controller.admin.transorder.vo.*;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import com.dofast.module.pro.convert.transorder.TransOrderConvert;
import com.dofast.module.pro.service.transorder.TransOrderService;

@Tag(name = "生产管理 - 流转单")
@RestController
@RequestMapping("/mes/pro/trans-order")
@Validated
public class TransOrderController {

    @Resource
    private TransOrderService transOrderService;

    @Resource
    private BarCodeUtil autoCodeApi;

    @PostMapping("/create")
    @Operation(summary = "创建流转单")
    @PreAuthorize("@ss.hasPermission('pro:trans-order:create')")
    public CommonResult<Long> createTransOrder(@Valid @RequestBody TransOrderCreateReqVO createReqVO) throws IOException{
        Long id = transOrderService.createTransOrder(createReqVO);
        autoCodeApi.generateBarCode(
                Constant.BARCODE_TYPE_TRANSORDER,createReqVO.getId(),
                createReqVO.getTransOrderCode(),null);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新流转单")
    @PreAuthorize("@ss.hasPermission('pro:trans-order:update')")
    public CommonResult<Boolean> updateTransOrder(@Valid @RequestBody TransOrderUpdateReqVO updateReqVO) {
        transOrderService.updateTransOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除流转单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:trans-order:delete')")
    public CommonResult<Boolean> deleteTransOrder(@RequestParam("id") Long id) {
        transOrderService.deleteTransOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得流转单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:trans-order:query')")
    public CommonResult<TransOrderRespVO> getTransOrder(@RequestParam("id") Long id) {
        TransOrderDO transOrder = transOrderService.getTransOrder(id);
        return success(TransOrderConvert.INSTANCE.convert(transOrder));
    }

    @GetMapping("/list")
    @Operation(summary = "获得流转单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:trans-order:query')")
    public CommonResult<List<TransOrderRespVO>> getTransOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<TransOrderDO> list = transOrderService.getTransOrderList(ids);
        return success(TransOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得流转单分页")
    @PreAuthorize("@ss.hasPermission('pro:trans-order:query')")
    public CommonResult<PageResult<TransOrderRespVO>> getTransOrderPage(@Valid TransOrderPageReqVO pageVO) {
        PageResult<TransOrderDO> pageResult = transOrderService.getTransOrderPage(pageVO);
        return success(TransOrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出流转单 Excel")
    @PreAuthorize("@ss.hasPermission('pro:trans-order:export')")
    @OperateLog(type = EXPORT)
    public void exportTransOrderExcel(@Valid TransOrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TransOrderDO> list = transOrderService.getTransOrderList(exportReqVO);
        // 导出 Excel
        List<TransOrderExcelVO> datas = TransOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "流转单.xls", "数据", TransOrderExcelVO.class, datas);
    }

}
