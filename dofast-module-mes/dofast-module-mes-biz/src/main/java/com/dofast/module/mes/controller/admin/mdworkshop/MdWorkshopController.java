package com.dofast.module.mes.controller.admin.mdworkshop;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.enums.ErrorCodeConstants;
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

import com.dofast.module.mes.controller.admin.mdworkshop.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkshop.MdWorkshopDO;
import com.dofast.module.mes.convert.mdworkshop.MdWorkshopConvert;
import com.dofast.module.mes.service.mdworkshop.MdWorkshopService;

@Tag(name = "管理后台 - 车间")
@RestController
@RequestMapping("/mes/md-workshop")
@Validated
public class MdWorkshopController {

    @Resource
    private MdWorkshopService mdWorkshopService;

    @PostMapping("/create")
    @Operation(summary = "创建车间")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:create')")
    public CommonResult<Long> createMdWorkshop(@Valid @RequestBody MdWorkshopCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdWorkshopService.checkWorkshopCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_WORKSHOP_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdWorkshopService.checkWorkshopNameUnique(createReqVO))){
            return error(ErrorCodeConstants.MD_WORKSHOP_NAME_NOT_UNIQUE);
        }
        return success(mdWorkshopService.createMdWorkshop(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车间")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:update')")
    public CommonResult<Boolean> updateMdWorkshop(@Valid @RequestBody MdWorkshopUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(mdWorkshopService.checkWorkshopCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_WORKSHOP_CODE_NOT_UNIQUE);
        }
        if(Constant.NOT_UNIQUE.equals(mdWorkshopService.checkWorkshopNameUnique(updateReqVO))){
            return error(ErrorCodeConstants.MD_WORKSHOP_NAME_NOT_UNIQUE);
        }
        mdWorkshopService.updateMdWorkshop(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车间")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:delete')")
    public CommonResult<Boolean> deleteMdWorkshop(@RequestParam("id") Long id) {
        mdWorkshopService.deleteMdWorkshop(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车间")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:query')")
    public CommonResult<MdWorkshopRespVO> getMdWorkshop(@RequestParam("id") Long id) {
        MdWorkshopDO mdWorkshop = mdWorkshopService.getMdWorkshop(id);
        return success(MdWorkshopConvert.INSTANCE.convert(mdWorkshop));
    }

    @GetMapping("/list")
    @Operation(summary = "获得车间列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:query')")
    public CommonResult<List<MdWorkshopRespVO>> getMdWorkshopList(@RequestParam("ids") Collection<Long> ids) {
        List<MdWorkshopDO> list = mdWorkshopService.getMdWorkshopList(ids);
        return success(MdWorkshopConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获得车间简单列表")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:query')")
    public CommonResult<List<MdWorkShopSimpleVO>> getMdWorkshopSimpleList() {
        List<MdWorkShopSimpleVO> list = mdWorkshopService.getMdWorkshopSimpleList();
        return success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "获得车间分页")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:query')")
    public CommonResult<PageResult<MdWorkshopRespVO>> getMdWorkshopPage(@Valid MdWorkshopPageReqVO pageVO) {
        PageResult<MdWorkshopDO> pageResult = mdWorkshopService.getMdWorkshopPage(pageVO);
        return success(MdWorkshopConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车间 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-workshop:export')")
    @OperateLog(type = EXPORT)
    public void exportMdWorkshopExcel(@Valid MdWorkshopExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdWorkshopDO> list = mdWorkshopService.getMdWorkshopList(exportReqVO);
        // 导出 Excel
        List<MdWorkshopExcelVO> datas = MdWorkshopConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "车间.xls", "数据", MdWorkshopExcelVO.class, datas);
    }

}
