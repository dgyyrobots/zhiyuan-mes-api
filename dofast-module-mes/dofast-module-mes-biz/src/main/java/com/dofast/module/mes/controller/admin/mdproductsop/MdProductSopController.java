package com.dofast.module.mes.controller.admin.mdproductsop;

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

import com.dofast.module.mes.controller.admin.mdproductsop.vo.*;
import com.dofast.module.mes.dal.dataobject.mdproductsop.MdProductSopDO;
import com.dofast.module.mes.convert.mdproductsop.MdProductSopConvert;
import com.dofast.module.mes.service.mdproductsop.MdProductSopService;

@Tag(name = "管理后台 - 产品SOP")
@RestController
@RequestMapping("/mes/md-product-sop")
@Validated
public class MdProductSopController {

    @Resource
    private MdProductSopService mdProductSopService;

    @PostMapping("/create")
    @Operation(summary = "创建产品SOP")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:create')")
    public CommonResult<Long> createMdProductSop(@Valid @RequestBody MdProductSopCreateReqVO createReqVO) {
        return success(mdProductSopService.createMdProductSop(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品SOP")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:update')")
    public CommonResult<Boolean> updateMdProductSop(@Valid @RequestBody MdProductSopUpdateReqVO updateReqVO) {
        mdProductSopService.updateMdProductSop(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品SOP")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:delete')")
    public CommonResult<Boolean> deleteMdProductSop(@RequestParam("id") Long id) {
        mdProductSopService.deleteMdProductSop(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品SOP")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:query')")
    public CommonResult<MdProductSopRespVO> getMdProductSop(@RequestParam("id") Long id) {
        MdProductSopDO mdProductSop = mdProductSopService.getMdProductSop(id);
        return success(MdProductSopConvert.INSTANCE.convert(mdProductSop));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品SOP列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:query')")
    public CommonResult<List<MdProductSopRespVO>> getMdProductSopList(@RequestParam("ids") Collection<Long> ids) {
        List<MdProductSopDO> list = mdProductSopService.getMdProductSopList(ids);
        return success(MdProductSopConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品SOP分页")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:query')")
    public CommonResult<PageResult<MdProductSopRespVO>> getMdProductSopPage(@Valid MdProductSopPageReqVO pageVO) {
        PageResult<MdProductSopDO> pageResult = mdProductSopService.getMdProductSopPage(pageVO);
        return success(MdProductSopConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品SOP Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-product-sop:export')")
    @OperateLog(type = EXPORT)
    public void exportMdProductSopExcel(@Valid MdProductSopExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdProductSopDO> list = mdProductSopService.getMdProductSopList(exportReqVO);
        // 导出 Excel
        List<MdProductSopExcelVO> datas = MdProductSopConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品SOP.xls", "数据", MdProductSopExcelVO.class, datas);
    }

}
