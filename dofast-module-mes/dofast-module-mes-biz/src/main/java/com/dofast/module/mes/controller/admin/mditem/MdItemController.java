package com.dofast.module.mes.controller.admin.mditem;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.module.mes.enums.ErrorCodeConstants;
import com.dofast.module.mes.service.mditemtype.MdItemTypeService;
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
import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.mes.controller.admin.mditem.vo.*;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.convert.mditem.MdItemConvert;
import com.dofast.module.mes.service.mditem.MdItemService;

@Tag(name = "管理后台 - 物料产品")
@RestController
@RequestMapping("/mes/md-item")
@Validated
public class MdItemController {

    @Resource
    private MdItemService mdItemService;
    @Resource
    private MdItemTypeService mdItemTypeService;
    @Resource
    private BarCodeUtil barCodeUtil;

    @PostMapping("/create")
    @Operation(summary = "创建物料产品")
    @PreAuthorize("@ss.hasPermission('mes:md-item:create')")
    public CommonResult<Long> createMdItem(@Valid @RequestBody MdItemCreateReqVO createReqVO) throws IOException{
        if(Constant.NOT_UNIQUE.equals(mdItemService.checkItemCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdItemService.checkItemNameUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_NAME_NOT_UNIQUE);
        }
        MdItemTypeDO type =mdItemTypeService.getMdItemType(createReqVO.getItemTypeId());
        if(StrUtils.isNotNull(type)){
            createReqVO.setItemTypeCode(type.getItemTypeCode());
            createReqVO.setItemTypeName(type.getItemTypeName());
            createReqVO.setItemOrProduct(type.getItemOrProduct());
        }

        Long id = mdItemService.createMdItem(createReqVO);
        barCodeUtil.generateBarCode(Constant.BARCODE_TYPE_ITEM,id,createReqVO.getItemCode(),createReqVO.getItemName());
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料产品")
    @PreAuthorize("@ss.hasPermission('mes:md-item:update')")
    public CommonResult<Boolean> updateMdItem(@Valid @RequestBody MdItemUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdItemService.checkItemCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdItemService.checkItemNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_NAME_NOT_UNIQUE);
        }

        MdItemTypeDO type =mdItemTypeService.getMdItemType(updateReqVO.getItemTypeId());
        if(StrUtils.isNotNull(type)){
            updateReqVO.setItemTypeCode(type.getItemTypeCode());
            updateReqVO.setItemTypeName(type.getItemTypeName());
            updateReqVO.setItemOrProduct(type.getItemOrProduct());
        }
        if(StrUtils.isNotNull(updateReqVO.getSafeStockFlag())&& "N".equals(updateReqVO.getSafeStockFlag())){
            updateReqVO.setMinStock(new BigDecimal(0));
            updateReqVO.setMaxStock(new BigDecimal(0));
        }
        mdItemService.updateMdItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料产品")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-item:delete')")
    public CommonResult<Boolean> deleteMdItem(@RequestParam("id") Long id) {
        mdItemService.deleteMdItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料产品")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-item:query')")
    public CommonResult<MdItemRespVO> getMdItem(@RequestParam("id") Long id) {
        MdItemDO mdItem = mdItemService.getMdItem(id);
        return success(MdItemConvert.INSTANCE.convert(mdItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料产品列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-item:query')")
    public CommonResult<List<MdItemRespVO>> getMdItemList(@RequestParam("ids") Collection<Long> ids) {
        List<MdItemDO> list = mdItemService.getMdItemList(ids);
        return success(MdItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/count-all")
    @Operation(summary = "获得物料产品总数")
    @PreAuthorize("@ss.hasPermission('mes:md-item:query')")
    public CommonResult<Integer> CountAll() {
        MdItemExportReqVO mdItemExportReqVO = new MdItemExportReqVO();
        List<MdItemDO> list = mdItemService.getMdItemList(mdItemExportReqVO);
        Integer result = list == null?0: list.size();
        return success(result);
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料产品分页")
    @PreAuthorize("@ss.hasPermission('mes:md-item:query')")
    public CommonResult<PageResult<MdItemRespVO>> getMdItemPage(@Valid MdItemPageReqVO pageVO) {
        PageResult<MdItemDO> pageResult = mdItemService.getMdItemPage(pageVO);
        return success(MdItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料产品 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-item:export')")
    @OperateLog(type = EXPORT)
    public void exportMdItemExcel(@Valid MdItemExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdItemDO> list = mdItemService.getMdItemList(exportReqVO);
        // 导出 Excel
        List<MdItemExcelVO> datas = MdItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料产品.xls", "数据", MdItemExcelVO.class, datas);
    }

    @GetMapping("/get-item-request")
    @Operation(summary = "获取物料需求")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-item:query')")
    public CommonResult<List<MdItemRequestVO>> getMdItemRequest(@RequestParam("ids") Collection<Long> ids) {
        List<MdItemRequestVO> result = mdItemService.getMdItemRequest(ids);
        return success(result);
    }

}
