package com.dofast.module.channel.controller.admin.remark;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;

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

import com.dofast.module.channel.controller.admin.remark.vo.*;
import com.dofast.module.channel.dal.dataobject.remark.RemarkDO;
import com.dofast.module.channel.convert.remark.RemarkConvert;
import com.dofast.module.channel.service.remark.RemarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "管理后台 - 订单备注")
@RestController
@RequestMapping("/channel/remark")
@Validated
public class RemarkController {

    @Resource
    private RemarkService remarkService;

    @PostMapping("/create-trade")
    @Operation(summary = "创建订单备注")
    @PreAuthorize("@ss.hasPermission('channel:remark:create')")
    public CommonResult<Long> createTradeRemark(@Validated({RemarkBaseVO.TradeOrderRemark.class}) @RequestBody RemarkCreateReqVO createReqVO) {
        return success(remarkService.createRemark(createReqVO));
    }

    @PostMapping("/create-mixin")
    @Operation(summary = "创建订单备注")
    @PreAuthorize("@ss.hasPermission('channel:remark:create')")
    public CommonResult<Long> createMixinRemark(@Validated({RemarkBaseVO.MixinOrderRemark.class}) @RequestBody RemarkCreateReqVO createReqVO) {
        return success(remarkService.createRemark(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单备注")
    @PreAuthorize("@ss.hasPermission('channel:remark:update')")
    public CommonResult<Boolean> updateRemark(@Valid @RequestBody RemarkUpdateReqVO updateReqVO) {
        remarkService.updateRemark(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单备注")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('channel:remark:delete')")
    public CommonResult<Boolean> deleteRemark(@RequestParam("id") Long id) {
        remarkService.deleteRemark(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单备注")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:remark:query')")
    public CommonResult<RemarkRespVO> getRemark(@RequestParam("id") Long id) {
        RemarkDO remark = remarkService.getRemark(id);
        return success(RemarkConvert.INSTANCE.convert(remark));
    }

    @GetMapping("/toggle-top")
    @Operation(summary = "顶置备注")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:remark:update')")
    public CommonResult<Boolean> toggleTopRemark(@RequestParam("id") Long id) {
        remarkService.toggleTopRemark(id);
        return success(true);
    }

    @GetMapping("/get-list-trade-order")
    @Operation(summary = "通过商城ID获取评论表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:remark:query')")
    public CommonResult<List<RemarkRespVO>> getRemarkListByTradeOrder(@RequestParam("id") Long id) {
        List<RemarkDO> list = remarkService.getRemarkListByTradeOrder(id);
        return success(RemarkConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/get-list-sales-order")
    @Operation(summary = "通过销售订单ID获取评论表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:remark:query')")
    public CommonResult<List<RemarkRespVO>> getRemarkListBySalesOrder(@RequestParam("id") Long id) {
        List<RemarkDO> list = remarkService.getRemarkListBySalesOrder(id);
        return success(RemarkConvert.INSTANCE.convertList(list));
    }


    @GetMapping("/list")
    @Operation(summary = "获得订单备注列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:remark:query')")
    public CommonResult<List<RemarkRespVO>> getRemarkList(@RequestParam("ids") Collection<Long> ids) {
        List<RemarkDO> list = remarkService.getRemarkList(ids);
        return success(RemarkConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单备注分页")
    @PreAuthorize("@ss.hasPermission('channel:remark:query')")
    public CommonResult<PageResult<RemarkRespVO>> getRemarkPage(@Valid RemarkPageReqVO pageVO) {
        PageResult<RemarkDO> pageResult = remarkService.getRemarkPage(pageVO);
        return success(RemarkConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单备注 Excel")
    @PreAuthorize("@ss.hasPermission('channel:remark:export')")
    @OperateLog(type = EXPORT)
    public void exportRemarkExcel(@Valid RemarkExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RemarkDO> list = remarkService.getRemarkList(exportReqVO);
        // 导出 Excel
        List<RemarkExcelVO> datas = RemarkConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单备注.xls", "数据", RemarkExcelVO.class, datas);
    }

}
