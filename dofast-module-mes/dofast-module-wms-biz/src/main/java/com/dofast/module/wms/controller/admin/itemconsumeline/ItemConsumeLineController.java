package com.dofast.module.wms.controller.admin.itemconsumeline;

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

import com.dofast.module.wms.controller.admin.itemconsumeline.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsumeline.ItemConsumeLineDO;
import com.dofast.module.wms.convert.itemconsumeline.ItemConsumeLineConvert;
import com.dofast.module.wms.service.itemconsumeline.ItemConsumeLineService;

@Tag(name = "管理后台 - 物料消耗记录行")
@RestController
@RequestMapping("/wms/item-consume-line")
@Validated
public class ItemConsumeLineController {

    @Resource
    private ItemConsumeLineService itemConsumeLineService;

    @PostMapping("/create")
    @Operation(summary = "创建物料消耗记录行")
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:create')")
    public CommonResult<Long> createItemConsumeLine(@Valid @RequestBody ItemConsumeLineCreateReqVO createReqVO) {
        return success(itemConsumeLineService.createItemConsumeLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料消耗记录行")
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:update')")
    public CommonResult<Boolean> updateItemConsumeLine(@Valid @RequestBody ItemConsumeLineUpdateReqVO updateReqVO) {
        itemConsumeLineService.updateItemConsumeLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料消耗记录行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:delete')")
    public CommonResult<Boolean> deleteItemConsumeLine(@RequestParam("id") Long id) {
        itemConsumeLineService.deleteItemConsumeLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料消耗记录行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:query')")
    public CommonResult<ItemConsumeLineRespVO> getItemConsumeLine(@RequestParam("id") Long id) {
        ItemConsumeLineDO itemConsumeLine = itemConsumeLineService.getItemConsumeLine(id);
        return success(ItemConsumeLineConvert.INSTANCE.convert(itemConsumeLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料消耗记录行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:query')")
    public CommonResult<List<ItemConsumeLineRespVO>> getItemConsumeLineList(@RequestParam("ids") Collection<Long> ids) {
        List<ItemConsumeLineDO> list = itemConsumeLineService.getItemConsumeLineList(ids);
        return success(ItemConsumeLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料消耗记录行分页")
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:query')")
    public CommonResult<PageResult<ItemConsumeLineRespVO>> getItemConsumeLinePage(@Valid ItemConsumeLinePageReqVO pageVO) {
        PageResult<ItemConsumeLineDO> pageResult = itemConsumeLineService.getItemConsumeLinePage(pageVO);
        return success(ItemConsumeLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料消耗记录行 Excel")
    @PreAuthorize("@ss.hasPermission('wms:item-consume-line:export')")
    @OperateLog(type = EXPORT)
    public void exportItemConsumeLineExcel(@Valid ItemConsumeLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ItemConsumeLineDO> list = itemConsumeLineService.getItemConsumeLineList(exportReqVO);
        // 导出 Excel
        List<ItemConsumeLineExcelVO> datas = ItemConsumeLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料消耗记录行.xls", "数据", ItemConsumeLineExcelVO.class, datas);
    }

}
