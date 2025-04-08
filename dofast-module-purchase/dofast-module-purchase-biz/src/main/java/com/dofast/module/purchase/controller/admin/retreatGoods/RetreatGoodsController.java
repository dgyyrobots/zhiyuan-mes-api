package com.dofast.module.purchase.controller.admin.retreatGoods;

import com.dofast.module.purchase.controller.admin.retreatGoods.vo.*;
import com.dofast.module.purchase.convert.retreatGoods.RetreatGoodsConvert;
import com.dofast.module.purchase.dal.dataobject.retreatGoods.RetreatGoodsDO;
import com.dofast.module.purchase.service.retreatGoods.RetreatGoodsService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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


@Tag(name = "管理后台 - ERP仓退单单身")
@RestController
@RequestMapping("/purchase/retreatGoods")
@Validated
public class RetreatGoodsController {

    @Resource
    private RetreatGoodsService goodsService;

    @PostMapping("/create")
    @Operation(summary = "创建ERP仓退单单身")
    @PreAuthorize("@ss.hasPermission('purchase:goods:create')")
    public CommonResult<Integer> createGoods(@Valid @RequestBody RetreatGoodsCreateReqVO createReqVO) {
        return success(goodsService.createGoods(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新ERP仓退单单身")
    @PreAuthorize("@ss.hasPermission('purchase:goods:update')")
    public CommonResult<Boolean> updateGoods(@Valid @RequestBody RetreatGoodsUpdateReqVO updateReqVO) {
        goodsService.updateGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除ERP仓退单单身")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('purchase:goods:delete')")
    public CommonResult<Boolean> deleteGoods(@RequestParam("id") Integer id) {
        goodsService.deleteGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得ERP仓退单单身")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('purchase:goods:query')")
    public CommonResult<RetreatGoodsRespVO> getGoods(@RequestParam("id") Integer id) {
        RetreatGoodsDO goods = goodsService.getGoods(id);
        return success(RetreatGoodsConvert.INSTANCE.convert(goods));
    }

    @GetMapping("/list")
    @Operation(summary = "获得ERP仓退单单身列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('purchase:goods:query')")
    public CommonResult<List<RetreatGoodsRespVO>> getGoodsList(@RequestParam("ids") Collection<Integer> ids) {
        List<RetreatGoodsDO> list = goodsService.getGoodsList(ids);
        return success(RetreatGoodsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得ERP仓退单单身分页")
    @PreAuthorize("@ss.hasPermission('purchase:goods:query')")
    public CommonResult<PageResult<RetreatGoodsRespVO>> getGoodsPage(@Valid RetreatGoodsPageReqVO pageVO) {
        PageResult<RetreatGoodsDO> pageResult = goodsService.getGoodsPage(pageVO);
        return success(RetreatGoodsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出ERP仓退单单身 Excel")
    @PreAuthorize("@ss.hasPermission('purchase:goods:export')")
    @OperateLog(type = EXPORT)
    public void exportGoodsExcel(@Valid RetreatGoodsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RetreatGoodsDO> list = goodsService.getGoodsList(exportReqVO);
        // 导出 Excel
        List<RetreatGoodsExcelVO> datas = RetreatGoodsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "ERP仓退单单身.xls", "数据", RetreatGoodsExcelVO.class, datas);
    }

}
