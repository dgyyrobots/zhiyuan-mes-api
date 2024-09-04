package com.dofast.module.mes.controller.admin.mditemtype;

import com.dofast.module.mes.api.autocode.AutoCodeApi;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.enums.ErrorCodeConstants;
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

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.mes.controller.admin.mditemtype.vo.*;
import com.dofast.module.mes.dal.dataobject.mditemtype.MdItemTypeDO;
import com.dofast.module.mes.convert.mditemtype.MdItemTypeConvert;
import com.dofast.module.mes.service.mditemtype.MdItemTypeService;

@Tag(name = "管理后台 - 物料产品分类")
@RestController
@RequestMapping("/mes/md-item-type")
@Validated
public class MdItemTypeController {

    @Resource
    private MdItemTypeService mdItemTypeService;
    @Resource
    private AutoCodeApi barCodeUtil;

    @PostMapping("/create")
    @Operation(summary = "创建物料产品分类")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:create')")
    public CommonResult<Long> createMdItemType(@Valid @RequestBody MdItemTypeCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdItemTypeService.checkItemTypeCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_TYPE_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdItemTypeService.checkItemTypeNameUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_TYPE_NAME_NOT_UNIQUE);
        }
        if(createReqVO.getParentTypeId() ==null || createReqVO.getParentTypeId()==0){
            createReqVO.setParentTypeId(0L);
        }
        createReqVO.setItemTypeCode(barCodeUtil.genSerialCode(Constant.ITEM_TYPE_CODE,null));
        return success(mdItemTypeService.createMdItemType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料产品分类")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:update')")
    public CommonResult<Boolean> updateMdItemType(@Valid @RequestBody MdItemTypeUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdItemTypeService.checkItemTypeCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_TYPE_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdItemTypeService.checkItemTypeNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_ITEM_TYPE_NAME_NOT_UNIQUE);
        }
        mdItemTypeService.updateMdItemType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料产品分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:delete')")
    public CommonResult<Boolean> deleteMdItemType(@RequestParam("id") Long id) {
        if(mdItemTypeService.checkHasChild(id)){
            return error(ErrorCodeConstants.MD_ITEM_TYPE_HAS_CHILDREN);
        }

        if(mdItemTypeService.checkHasItem(id)){
            return error(ErrorCodeConstants.MD_ITEM_TYPE_HAS_ITEM);
        }
        mdItemTypeService.deleteMdItemType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料产品分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:query')")
    public CommonResult<MdItemTypeRespVO> getMdItemType(@RequestParam("id") Long id) {
        MdItemTypeDO mdItemType = mdItemTypeService.getMdItemType(id);
        return success(MdItemTypeConvert.INSTANCE.convert(mdItemType));
    }

    /**
     * 分类列表（排除当前和父节点）
     * @param itemTypeId
     * @return
     */
    @Operation(summary = "分类列表（排除当前和父节点）")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:query')")
    @GetMapping("/list/exclude/{itemTypeId}")
    public CommonResult excludeChild(@PathVariable(value = "itemTypeId",required = false)Long itemTypeId){
        List<MdItemTypeDO> list = mdItemTypeService.selectALl();
        Iterator<MdItemTypeDO> it = list.iterator();
        Long parentTypeId =0L;
        while (it.hasNext()){
            MdItemTypeDO itemType = (MdItemTypeDO) it.next();
            if(itemType.getId().longValue() == itemTypeId.longValue()){
                parentTypeId = itemType.getParentTypeId();
                it.remove();
            }

            if(itemType.getId().longValue() == parentTypeId.longValue()){
                it.remove();
            }
        }
        return success(list);
    }

    /**
     * 获取树形结构数据
     * @param itemType
     * @return
     */
    @GetMapping("/treeselect")
    public CommonResult<List<MdItemTreeTypeListVO>> treeSelect(MdItemTypeListVO itemType){
        List<MdItemTypeDO> list = mdItemTypeService.getMdItemTypeList(itemType);
        return success(mdItemTypeService.buildTreeSelect(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料产品分类列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:query')")
    public CommonResult<List<MdItemTypeRespVO>> getMdItemTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<MdItemTypeDO> list = mdItemTypeService.getMdItemTypeList(ids);
        return success(MdItemTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料产品分类分页")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:query')")
    public CommonResult<PageResult<MdItemTypeRespVO>> getMdItemTypePage(@Valid MdItemTypePageReqVO pageVO) {
        PageResult<MdItemTypeDO> pageResult = mdItemTypeService.getMdItemTypePage(pageVO);
        return success(MdItemTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料产品分类 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:export')")
    @OperateLog(type = EXPORT)
    public void exportMdItemTypeExcel(@Valid MdItemTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdItemTypeDO> list = mdItemTypeService.getMdItemTypeList(exportReqVO);
        // 导出 Excel
        List<MdItemTypeExcelVO> datas = MdItemTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料产品分类.xls", "数据", MdItemTypeExcelVO.class, datas);
    }

    @GetMapping("/list-all")
    @Operation(summary = "获得物料产品分类列表list-all")
    @PreAuthorize("@ss.hasPermission('mes:md-item-type:query')")
    public CommonResult<List<MdItemTypeRespVO>> getMdItemTypeListAll() {
        MdItemTypeExportReqVO mdItemTypeExportReqVO = new MdItemTypeExportReqVO();
        List<MdItemTypeDO> list = mdItemTypeService.getMdItemTypeList(mdItemTypeExportReqVO);
        return success(MdItemTypeConvert.INSTANCE.convertList(list));
    }
}
