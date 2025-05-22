package com.dofast.module.system.controller.admin.dj002;

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

import com.dofast.module.system.controller.admin.dj002.vo.*;
import com.dofast.module.system.dal.dataobject.dj002.Dj002DO;
import com.dofast.module.system.convert.dj002.Dj002Convert;
import com.dofast.module.system.service.dj002.Dj002Service;

@Tag(name = "管理后台 - 系统地址信息")
@RestController
@RequestMapping("/system/dj002")
@Validated
public class Dj002Controller {

    @Resource
    private Dj002Service dj002Service;

    @PostMapping("/create")
    @Operation(summary = "创建系统地址信息")
    @PreAuthorize("@ss.hasPermission('system:dj002:create')")
    public CommonResult<Integer> createDj002(@Valid @RequestBody Dj002CreateReqVO createReqVO) {
        return success(dj002Service.createDj002(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统地址信息")
    @PreAuthorize("@ss.hasPermission('system:dj002:update')")
    public CommonResult<Boolean> updateDj002(@Valid @RequestBody Dj002UpdateReqVO updateReqVO) {
        dj002Service.updateDj002(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统地址信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:dj002:delete')")
    public CommonResult<Boolean> deleteDj002(@RequestParam("id") Integer id) {
        dj002Service.deleteDj002(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统地址信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:dj002:query')")
    public CommonResult<Dj002RespVO> getDj002(@RequestParam("id") Integer id) {
        Dj002DO dj002 = dj002Service.getDj002(id);
        return success(Dj002Convert.INSTANCE.convert(dj002));
    }

    @GetMapping("/list")
    @Operation(summary = "获得系统地址信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('system:dj002:query')")
    public CommonResult<List<Dj002RespVO>> getDj002List(@RequestParam("ids") Collection<Integer> ids) {
        List<Dj002DO> list = dj002Service.getDj002List(ids);
        return success(Dj002Convert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统地址信息分页")
    @PreAuthorize("@ss.hasPermission('system:dj002:query')")
    public CommonResult<PageResult<Dj002RespVO>> getDj002Page(@Valid Dj002PageReqVO pageVO) {
        PageResult<Dj002DO> pageResult = dj002Service.getDj002Page(pageVO);
        return success(Dj002Convert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统地址信息 Excel")
    @PreAuthorize("@ss.hasPermission('system:dj002:export')")
    @OperateLog(type = EXPORT)
    public void exportDj002Excel(@Valid Dj002ExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<Dj002DO> list = dj002Service.getDj002List(exportReqVO);
        // 导出 Excel
        List<Dj002ExcelVO> datas = Dj002Convert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "系统地址信息.xls", "数据", Dj002ExcelVO.class, datas);
    }

}
