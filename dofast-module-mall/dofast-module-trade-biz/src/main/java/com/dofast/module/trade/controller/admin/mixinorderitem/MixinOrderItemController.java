package com.dofast.module.trade.controller.admin.mixinorderitem;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.mes.api.ItemApi.MdItemApi;
import com.dofast.module.mes.api.ItemApi.dto.MdItemDTO;
import com.dofast.module.trade.controller.admin.mixinorderitem.vo.*;
import com.dofast.module.trade.convert.mixinorderitem.MixinOrderItemConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.mixinorderitem.MixinOrderItemDO;
import com.dofast.module.trade.service.mixin.MixinOrderService;
import com.dofast.module.trade.service.mixinorderitem.MixinOrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 销售的物料明细")
@RestController
@RequestMapping("/trade/mixin-order-item")
@Validated
public class MixinOrderItemController {

    @Resource
    private MixinOrderItemService mixinOrderItemService;

    @Resource
    private MixinOrderService mixinOrderService;

    @Resource
    private MdItemApi mdItemApi;

    @PostMapping("/create")
    @Operation(summary = "创建销售的物料明细")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:create')")
    public CommonResult<Long> createMixinOrderItem(@Valid @RequestBody MixinOrderItemCreateReqVO createReqVO) {
        return success(mixinOrderItemService.createMixinOrderItem(createReqVO));
    }

    @PostMapping("/create-by-id")
    @Operation(summary = "通过物料id创建销售的物料明细")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:create')")
    public CommonResult<Boolean> createMixinOrderItemById(@RequestParam(name = "id")Long id,
                                                          @RequestParam(name = "ids", required = false) Collection<Long> ids) {
        MixinOrderDO mixinOrderDO = mixinOrderService.getMixinOrder(id);
        if (!ids.isEmpty()){
            for (Long itemId : ids){
                MdItemDTO mdItemDTO = mdItemApi.getMditemById(itemId);
                if (mdItemDTO == null) continue;
                mixinOrderItemService.generateMixinOrderItem(mdItemDTO, mixinOrderDO);
            }
        }
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新销售的物料明细")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:update')")
    public CommonResult<Boolean> updateMixinOrderItem(@Valid @RequestBody MixinOrderItemUpdateReqVO updateReqVO) {
        mixinOrderItemService.updateMixinOrderItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除销售的物料明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:delete')")
    public CommonResult<Boolean> deleteMixinOrderItem(@RequestParam("id") Long id) {
        mixinOrderItemService.deleteMixinOrderItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得销售的物料明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:query')")
    public CommonResult<MixinOrderItemRespVO> getMixinOrderItem(@RequestParam("id") Long id) {
        MixinOrderItemDO mixinOrderItem = mixinOrderItemService.getMixinOrderItem(id);
        return success(MixinOrderItemConvert.INSTANCE.convert(mixinOrderItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得销售的物料明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:query')")
    public CommonResult<List<MixinOrderItemRespVO>> getMixinOrderItemList(@RequestParam("ids") Collection<Long> ids) {
        List<MixinOrderItemDO> list = mixinOrderItemService.getMixinOrderItemList(ids);
        return success(MixinOrderItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得销售的物料明细分页")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:query')")
    public CommonResult<PageResult<MixinOrderItemRespVO>> getMixinOrderItemPage(@Valid MixinOrderItemPageReqVO pageVO) {
        PageResult<MixinOrderItemDO> pageResult = mixinOrderItemService.getMixinOrderItemPage(pageVO);
        return success(MixinOrderItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出销售的物料明细 Excel")
    @PreAuthorize("@ss.hasPermission('trade:mixin-order-item:export')")
    @OperateLog(type = EXPORT)
    public void exportMixinOrderItemExcel(@Valid MixinOrderItemExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MixinOrderItemDO> list = mixinOrderItemService.getMixinOrderItemList(exportReqVO);
        // 导出 Excel
        List<MixinOrderItemExcelVO> datas = MixinOrderItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "销售的物料明细.xls", "数据", MixinOrderItemExcelVO.class, datas);
    }

}
