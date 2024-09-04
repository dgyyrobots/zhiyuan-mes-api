package com.dofast.module.mes.controller.admin.mdworkstationworker;

import com.dofast.module.system.api.dept.PostApi;
import com.dofast.module.system.api.dept.dto.DeptPostDTO;
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

import com.dofast.module.mes.controller.admin.mdworkstationworker.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;
import com.dofast.module.mes.convert.mdworkstationworker.MdWorkstationWorkerConvert;
import com.dofast.module.mes.service.mdworkstationworker.MdWorkstationWorkerService;

@Tag(name = "管理后台 - 人力资源")
@RestController
@RequestMapping("/mes/md-workstation-worker")
@Validated
public class MdWorkstationWorkerController {

    @Resource
    private MdWorkstationWorkerService mdWorkstationWorkerService;

    @Resource
    private PostApi postApi;

    @PostMapping("/create")
    @Operation(summary = "创建人力资源")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:create')")
    public CommonResult<Long> createMdWorkstationWorker(@Valid @RequestBody MdWorkstationWorkerCreateReqVO createReqVO) {
        DeptPostDTO deptPostDTO = postApi.selectById(createReqVO.getPostId());
        createReqVO.setPostName(deptPostDTO.getName());
        createReqVO.setPostCode(deptPostDTO.getCode());
        return success(mdWorkstationWorkerService.createMdWorkstationWorker(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人力资源")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:update')")
    public CommonResult<Boolean> updateMdWorkstationWorker(@Valid @RequestBody MdWorkstationWorkerUpdateReqVO updateReqVO) {
        DeptPostDTO deptPostDTO = postApi.selectById(updateReqVO.getPostId());
        updateReqVO.setPostName(deptPostDTO.getName());
        updateReqVO.setPostCode(deptPostDTO.getCode());
        mdWorkstationWorkerService.updateMdWorkstationWorker(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人力资源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:delete')")
    public CommonResult<Boolean> deleteMdWorkstationWorker(@RequestParam("id") Long id) {
        mdWorkstationWorkerService.deleteMdWorkstationWorker(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人力资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:query')")
    public CommonResult<MdWorkstationWorkerRespVO> getMdWorkstationWorker(@RequestParam("id") Long id) {
        MdWorkstationWorkerDO mdWorkstationWorker = mdWorkstationWorkerService.getMdWorkstationWorker(id);
        return success(MdWorkstationWorkerConvert.INSTANCE.convert(mdWorkstationWorker));
    }

    @GetMapping("/list")
    @Operation(summary = "获得人力资源列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:query')")
    public CommonResult<List<MdWorkstationWorkerRespVO>> getMdWorkstationWorkerList(@RequestParam("ids") Collection<Long> ids) {
        List<MdWorkstationWorkerDO> list = mdWorkstationWorkerService.getMdWorkstationWorkerList(ids);
        return success(MdWorkstationWorkerConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人力资源分页")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:query')")
    public CommonResult<PageResult<MdWorkstationWorkerRespVO>> getMdWorkstationWorkerPage(@Valid MdWorkstationWorkerPageReqVO pageVO) {
        PageResult<MdWorkstationWorkerDO> pageResult = mdWorkstationWorkerService.getMdWorkstationWorkerPage(pageVO);
        return success(MdWorkstationWorkerConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人力资源 Excel")
    @PreAuthorize("@ss.hasPermission('mes:md-workstation-worker:export')")
    @OperateLog(type = EXPORT)
    public void exportMdWorkstationWorkerExcel(@Valid MdWorkstationWorkerExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MdWorkstationWorkerDO> list = mdWorkstationWorkerService.getMdWorkstationWorkerList(exportReqVO);
        // 导出 Excel
        List<MdWorkstationWorkerExcelVO> datas = MdWorkstationWorkerConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "人力资源.xls", "数据", MdWorkstationWorkerExcelVO.class, datas);
    }

}
