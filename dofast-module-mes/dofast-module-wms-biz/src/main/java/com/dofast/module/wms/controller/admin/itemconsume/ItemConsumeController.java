package com.dofast.module.wms.controller.admin.itemconsume;

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

import com.dofast.module.wms.controller.admin.itemconsume.vo.*;
import com.dofast.module.wms.dal.dataobject.itemconsume.ItemConsumeDO;
import com.dofast.module.wms.convert.itemconsume.ItemConsumeConvert;
import com.dofast.module.wms.service.itemconsume.ItemConsumeService;

@Tag(name = "管理后台 - 物料消耗记录")
@RestController
@RequestMapping("/wms/item-consume")
@Validated
public class ItemConsumeController {

    @Resource
    private ItemConsumeService itemConsumeService;

    @PostMapping("/create")
    @Operation(summary = "创建物料消耗记录")
    @PreAuthorize("@ss.hasPermission('wms:item-consume:create')")
    public CommonResult<Long> createItemConsume(@Valid @RequestBody ItemConsumeCreateReqVO createReqVO) {
        return success(itemConsumeService.createItemConsume(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料消耗记录")
    @PreAuthorize("@ss.hasPermission('wms:item-consume:update')")
    public CommonResult<Boolean> updateItemConsume(@Valid @RequestBody ItemConsumeUpdateReqVO updateReqVO) {
        itemConsumeService.updateItemConsume(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料消耗记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:item-consume:delete')")
    public CommonResult<Boolean> deleteItemConsume(@RequestParam("id") Long id) {
        itemConsumeService.deleteItemConsume(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料消耗记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:item-consume:query')")
    public CommonResult<ItemConsumeRespVO> getItemConsume(@RequestParam("id") Long id) {
        ItemConsumeDO itemConsume = itemConsumeService.getItemConsume(id);
        return success(ItemConsumeConvert.INSTANCE.convert(itemConsume));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料消耗记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:item-consume:query')")
    public CommonResult<List<ItemConsumeRespVO>> getItemConsumeList(@RequestParam("ids") Collection<Long> ids) {
        List<ItemConsumeDO> list = itemConsumeService.getItemConsumeList(ids);
        return success(ItemConsumeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料消耗记录分页")
    @PreAuthorize("@ss.hasPermission('wms:item-consume:query')")
    public CommonResult<PageResult<ItemConsumeRespVO>> getItemConsumePage(@Valid ItemConsumePageReqVO pageVO) {
        PageResult<ItemConsumeDO> pageResult = itemConsumeService.getItemConsumePage(pageVO);
        return success(ItemConsumeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料消耗记录 Excel")
    @PreAuthorize("@ss.hasPermission('wms:item-consume:export')")
    @OperateLog(type = EXPORT)
    public void exportItemConsumeExcel(@Valid ItemConsumeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ItemConsumeDO> list = itemConsumeService.getItemConsumeList(exportReqVO);
        // 导出 Excel
        List<ItemConsumeExcelVO> datas = ItemConsumeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料消耗记录.xls", "数据", ItemConsumeExcelVO.class, datas);
    }


    /**
     * 看板: 用于计算物料消耗
     * @return
     */
    @GetMapping("/weekly-summary")
    public CommonResult getWeeklyConsumeSummary() {
        return success(itemConsumeService.getWeeklyConsumeSummary());
    }

}
