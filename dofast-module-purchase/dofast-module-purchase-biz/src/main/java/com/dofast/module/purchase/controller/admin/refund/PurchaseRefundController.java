package com.dofast.module.purchase.controller.admin.refund;

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

import com.dofast.module.purchase.controller.admin.refund.vo.*;
import com.dofast.module.purchase.dal.dataobject.refund.RefundDO;
import com.dofast.module.purchase.convert.refund.RefundConvert;
import com.dofast.module.purchase.service.refund.RefundService;

@Tag(name = "管理后台 - 采购退货")
@RestController
@RequestMapping("/purchase/refund")
@Validated
public class PurchaseRefundController {

    @Resource
    private RefundService refundService;

    @PostMapping("/create")
    @Operation(summary = "创建采购退货")
    @PreAuthorize("@ss.hasPermission('purchase:refund:create')")
    public CommonResult<Integer> createRefund(@Valid @RequestBody RefundCreateReqVO createReqVO) {
        return success(refundService.createRefund(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采购退货")
    @PreAuthorize("@ss.hasPermission('purchase:refund:update')")
    public CommonResult<Boolean> updateRefund(@Valid @RequestBody RefundUpdateReqVO updateReqVO) {
        refundService.updateRefund(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采购退货")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('purchase:refund:delete')")
    public CommonResult<Boolean> deleteRefund(@RequestParam("id") Integer id) {
        refundService.deleteRefund(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采购退货")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('purchase:refund:query')")
    public CommonResult<RefundRespVO> getRefund(@RequestParam("id") Integer id) {
        RefundDO refund = refundService.getRefund(id);
        return success(RefundConvert.INSTANCE.convert(refund));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采购退货列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('purchase:refund:query')")
    public CommonResult<List<RefundRespVO>> getRefundList(@RequestParam("ids") Collection<Integer> ids) {
        List<RefundDO> list = refundService.getRefundList(ids);
        return success(RefundConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采购退货分页")
    @PreAuthorize("@ss.hasPermission('purchase:refund:query')")
    public CommonResult<PageResult<RefundRespVO>> getRefundPage(@Valid RefundPageReqVO pageVO) {
        PageResult<RefundDO> pageResult = refundService.getRefundPage(pageVO);
        return success(RefundConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采购退货 Excel")
    @PreAuthorize("@ss.hasPermission('purchase:refund:export')")
    @OperateLog(type = EXPORT)
    public void exportRefundExcel(@Valid RefundExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RefundDO> list = refundService.getRefundList(exportReqVO);
        // 导出 Excel
        List<RefundExcelVO> datas = RefundConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采购退货.xls", "数据", RefundExcelVO.class, datas);
    }

}
