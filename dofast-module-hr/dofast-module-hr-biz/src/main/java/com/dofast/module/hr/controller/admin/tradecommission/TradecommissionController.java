package com.dofast.module.hr.controller.admin.tradecommission;

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

import com.dofast.module.hr.controller.admin.tradecommission.vo.*;
import com.dofast.module.hr.dal.dataobject.tradecommission.TradecommissionDO;
import com.dofast.module.hr.convert.tradecommission.TradecommissionConvert;
import com.dofast.module.hr.service.tradecommission.TradecommissionService;

@Tag(name = "管理后台 - 工资提成")
@RestController
@RequestMapping("/hr/tradecommission")
@Validated
public class TradecommissionController {

    @Resource
    private TradecommissionService tradecommissionService;

    @PostMapping("/create")
    @Operation(summary = "创建工资提成")
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:create')")
    public CommonResult<Integer> createTradecommission(@Valid @RequestBody TradecommissionCreateReqVO createReqVO) {
        return success(tradecommissionService.createTradecommission(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工资提成")
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:update')")
    public CommonResult<Boolean> updateTradecommission(@Valid @RequestBody TradecommissionUpdateReqVO updateReqVO) {
        tradecommissionService.updateTradecommission(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工资提成")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:delete')")
    public CommonResult<Boolean> deleteTradecommission(@RequestParam("id") Integer id) {
        tradecommissionService.deleteTradecommission(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工资提成")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:query')")
    public CommonResult<TradecommissionRespVO> getTradecommission(@RequestParam("id") Integer id) {
        TradecommissionDO tradecommission = tradecommissionService.getTradecommission(id);
        return success(TradecommissionConvert.INSTANCE.convert(tradecommission));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工资提成列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:query')")
    public CommonResult<List<TradecommissionRespVO>> getTradecommissionList(@RequestParam("ids") Collection<Integer> ids) {
        List<TradecommissionDO> list = tradecommissionService.getTradecommissionList(ids);
        return success(TradecommissionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工资提成分页")
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:query')")
    public CommonResult<PageResult<TradecommissionRespVO>> getTradecommissionPage(@Valid TradecommissionPageReqVO pageVO) {
        PageResult<TradecommissionDO> pageResult = tradecommissionService.getTradecommissionPage(pageVO);
        return success(TradecommissionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工资提成 Excel")
    @PreAuthorize("@ss.hasPermission('hr:tradecommission:export')")
    @OperateLog(type = EXPORT)
    public void exportTradecommissionExcel(@Valid TradecommissionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TradecommissionDO> list = tradecommissionService.getTradecommissionList(exportReqVO);
        // 导出 Excel
        List<TradecommissionExcelVO> datas = TradecommissionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工资提成.xls", "数据", TradecommissionExcelVO.class, datas);
    }

}
