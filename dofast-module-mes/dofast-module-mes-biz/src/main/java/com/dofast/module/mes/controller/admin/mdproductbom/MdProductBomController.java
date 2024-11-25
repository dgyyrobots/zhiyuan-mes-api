package com.dofast.module.mes.controller.admin.mdproductbom;

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

import com.dofast.module.mes.controller.admin.mdproductbom.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductbom.MdProductBomDO;
import com.dofast.module.mes.convert.mdproductbom.MdProductBomConvert;
import com.dofast.module.mes.service.mdproductbom.MdProductBomService;

@Tag(name = "管理后台 - 产品BOM关系")
@RestController
@RequestMapping("/mes/md-product-bom")
@Validated
public class MdProductBomController {

    @Resource
    private MdProductBomService mdProductBomService;

    @PostMapping("/create")
    @Operation(summary = "创建产品BOM关系")
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:create')")
    public CommonResult<Long> createMdProductBom(@Valid @RequestBody MdProductBomCreateReqVO createReqVO) {
        return success(mdProductBomService.createMdProductBom(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品BOM关系")
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:update')")
    public CommonResult<Boolean> updateMdProductBom(@Valid @RequestBody MdProductBomUpdateReqVO updateReqVO) {
        mdProductBomService.updateMdProductBom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品BOM关系")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:delete')")
    public CommonResult<Boolean> deleteMdProductBom(@RequestParam("id") Long id) {
        mdProductBomService.deleteMdProductBom(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品BOM关系")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:query')")
    public CommonResult<MdProductBomRespVO> getMdProductBom(@RequestParam("id") Long id) {
        MdProductBomDO mdProductBom = mdProductBomService.getMdProductBom(id);
        return success(MdProductBomConvert.INSTANCE.convert(mdProductBom));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品BOM关系列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:query')")
    public CommonResult<List<MdProductBomRespVO>> getMdProductBomList(@RequestParam("ids") Collection<Long> ids) {
        List<MdProductBomDO> list = mdProductBomService.getMdProductBomList(ids);
        return success(MdProductBomConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品BOM关系分页")
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:query')")
    public CommonResult<PageResult<MdProductBomRespVO>> getMdProductBomPage(@Valid MdProductBomPageReqVO pageVO) {
        PageResult<MdProductBomDO> pageResult = mdProductBomService.getMdProductBomPage(pageVO);
        return success(MdProductBomConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品BOM关系 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-product-bom:export')")
    @OperateLog(type = EXPORT)
    public void exportMdProductBomExcel(@Valid MdProductBomExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdProductBomDO> list = mdProductBomService.getMdProductBomList(exportReqVO);
        // 导出 Excel
        List<MdProductBomExcelVO> datas = MdProductBomConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品BOM关系.xls", "数据", MdProductBomExcelVO.class, datas);
    }

}
