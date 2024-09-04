package com.dofast.module.purchase.controller.admin.goods;

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

import com.dofast.module.purchase.controller.admin.goods.vo.*;
import com.dofast.module.purchase.dal.dataobject.goods.GoodsDO;
import com.dofast.module.purchase.convert.goods.GoodsConvert;
import com.dofast.module.purchase.service.goods.GoodsService;

@Tag(name = "管理后台 - 采购商品明细")
@RestController
@RequestMapping("/purchase/goods")
@Validated
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("/create")
    @Operation(summary = "创建采购商品明细")
    @PreAuthorize("@ss.hasPermission('purchase:goods:create')")
    public CommonResult<Integer> createGoods(@Valid @RequestBody GoodsCreateReqVO createReqVO) {
        return success(goodsService.createGoods(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采购商品明细")
    @PreAuthorize("@ss.hasPermission('purchase:goods:update')")
    public CommonResult<Boolean> updateGoods(@Valid @RequestBody GoodsUpdateReqVO updateReqVO) {
        goodsService.updateGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采购商品明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('purchase:goods:delete')")
    public CommonResult<Boolean> deleteGoods(@RequestParam("id") Integer id) {
        goodsService.deleteGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采购商品明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('purchase:goods:query')")
    public CommonResult<GoodsRespVO> getGoods(@RequestParam("id") Integer id) {
        GoodsDO goods = goodsService.getGoods(id);
        return success(GoodsConvert.INSTANCE.convert(goods));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采购商品明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('purchase:goods:query')")
    public CommonResult<List<GoodsRespVO>> getGoodsList(@RequestParam("ids") Collection<Integer> ids) {
        List<GoodsDO> list = goodsService.getGoodsList(ids);
        return success(GoodsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采购商品明细分页")
    @PreAuthorize("@ss.hasPermission('purchase:goods:query')")
    public CommonResult<PageResult<GoodsRespVO>> getGoodsPage(@Valid GoodsPageReqVO pageVO) {
        PageResult<GoodsDO> pageResult = goodsService.getGoodsPage(pageVO);
        return success(GoodsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采购商品明细 Excel")
    @PreAuthorize("@ss.hasPermission('purchase:goods:export')")
    @OperateLog(type = EXPORT)
    public void exportGoodsExcel(@Valid GoodsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GoodsDO> list = goodsService.getGoodsList(exportReqVO);
        // 导出 Excel
        List<GoodsExcelVO> datas = GoodsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采购商品明细.xls", "数据", GoodsExcelVO.class, datas);
    }

}
