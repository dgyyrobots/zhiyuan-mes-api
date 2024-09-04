package com.dofast.module.mes.controller.admin.mdworkstationtool;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.mes.controller.admin.mdworkstationtool.vo.*;
import com.dofast.module.mes.convert.mdworkstationtool.MdWorkstationToolConvert;
import com.dofast.module.mes.dal.dataobject.mdworkstationtool.MdWorkstationToolDO;
import com.dofast.module.mes.service.mdworkstationtool.MdWorkstationToolService;
import com.dofast.module.tm.api.TmToolTypeApi.TmToolTypeApi;
import com.dofast.module.tm.api.TmToolTypeApi.dto.TmToolTypeDTO;
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

@Tag(name = "管理后台 - 工装夹具资源")
@RestController
@RequestMapping("/mes/md-workstation-tool")
@Validated
public class MdWorkstationToolController {

    @Resource
    private MdWorkstationToolService mdWorkstationToolService;

    @Resource
    private TmToolTypeApi tmToolTypeApi;

    @PostMapping("/create")
    @Operation(summary = "创建工装夹具资源")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:create')")
    public CommonResult<Long> createMdWorkstationTool(@Valid @RequestBody MdWorkstationToolCreateReqVO createReqVO) {
        TmToolTypeDTO tmToolTypeDTO = tmToolTypeApi.selectById(createReqVO.getToolTypeId());
        createReqVO.setToolTypeCode(tmToolTypeDTO.getToolTypeCode());
        createReqVO.setToolTypeName(tmToolTypeDTO.getToolTypeName());
        return success(mdWorkstationToolService.createMdWorkstationTool(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工装夹具资源")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:update')")
    public CommonResult<Boolean> updateMdWorkstationTool(@Valid @RequestBody MdWorkstationToolUpdateReqVO updateReqVO) {
        TmToolTypeDTO tmToolTypeDTO = tmToolTypeApi.selectById(updateReqVO.getToolTypeId());
        updateReqVO.setToolTypeCode(tmToolTypeDTO.getToolTypeCode());
        updateReqVO.setToolTypeName(tmToolTypeDTO.getToolTypeName());
        mdWorkstationToolService.updateMdWorkstationTool(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工装夹具资源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:delete')")
    public CommonResult<Boolean> deleteMdWorkstationTool(@RequestParam("id") Long id) {
        mdWorkstationToolService.deleteMdWorkstationTool(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工装夹具资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:query')")
    public CommonResult<MdWorkstationToolRespVO> getMdWorkstationTool(@RequestParam("id") Long id) {
        MdWorkstationToolDO mdWorkstationTool = mdWorkstationToolService.getMdWorkstationTool(id);
        return success(MdWorkstationToolConvert.INSTANCE.convert(mdWorkstationTool));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工装夹具资源列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:query')")
    public CommonResult<List<MdWorkstationToolRespVO>> getMdWorkstationToolList(@RequestParam("ids") Collection<Long> ids) {
        List<MdWorkstationToolDO> list = mdWorkstationToolService.getMdWorkstationToolList(ids);
        return success(MdWorkstationToolConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工装夹具资源分页")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:query')")
    public CommonResult<PageResult<MdWorkstationToolRespVO>> getMdWorkstationToolPage(@Valid MdWorkstationToolPageReqVO pageVO) {
        PageResult<MdWorkstationToolDO> pageResult = mdWorkstationToolService.getMdWorkstationToolPage(pageVO);
        return success(MdWorkstationToolConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工装夹具资源 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-tool:export')")
    @OperateLog(type = EXPORT)
    public void exportMdWorkstationToolExcel(@Valid MdWorkstationToolExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdWorkstationToolDO> list = mdWorkstationToolService.getMdWorkstationToolList(exportReqVO);
        // 导出 Excel
        List<MdWorkstationToolExcelVO> datas = MdWorkstationToolConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工装夹具资源.xls", "数据", MdWorkstationToolExcelVO.class, datas);
    }

}
