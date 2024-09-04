package com.dofast.module.mes.controller.admin.freezeinfo;

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

import com.dofast.module.mes.controller.admin.freezeinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.freezeinfo.FreezeInfoDO;
import com.dofast.module.mes.convert.freezeinfo.FreezeInfoConvert;
import com.dofast.module.mes.service.freezeinfo.FreezeInfoService;

@Tag(name = "管理后台 - 产品冻结信息")
@RestController
@RequestMapping("/mes/freeze-info")
@Validated
public class FreezeInfoController {

    @Resource
    private FreezeInfoService freezeInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建产品冻结信息")
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:create')")
    public CommonResult<Long> createFreezeInfo(@Valid @RequestBody FreezeInfoCreateReqVO createReqVO) {
        return success(freezeInfoService.createFreezeInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品冻结信息")
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:update')")
    public CommonResult<Boolean> updateFreezeInfo(@Valid @RequestBody FreezeInfoUpdateReqVO updateReqVO) {
        freezeInfoService.updateFreezeInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品冻结信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:delete')")
    public CommonResult<Boolean> deleteFreezeInfo(@RequestParam("id") Long id) {
        freezeInfoService.deleteFreezeInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品冻结信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:query')")
    public CommonResult<FreezeInfoRespVO> getFreezeInfo(@RequestParam("id") Long id) {
        FreezeInfoDO freezeInfo = freezeInfoService.getFreezeInfo(id);
        return success(FreezeInfoConvert.INSTANCE.convert(freezeInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品冻结信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:query')")
    public CommonResult<List<FreezeInfoRespVO>> getFreezeInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<FreezeInfoDO> list = freezeInfoService.getFreezeInfoList(ids);
        return success(FreezeInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品冻结信息分页")
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:query')")
    public CommonResult<PageResult<FreezeInfoRespVO>> getFreezeInfoPage(@Valid FreezeInfoPageReqVO pageVO) {
        PageResult<FreezeInfoDO> pageResult = freezeInfoService.getFreezeInfoPage(pageVO);
        return success(FreezeInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品冻结信息 Excel")
    @PreAuthorize("@ss.hasPermission('mes:freeze-info:export')")
    @OperateLog(type = EXPORT)
    public void exportFreezeInfoExcel(@Valid FreezeInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FreezeInfoDO> list = freezeInfoService.getFreezeInfoList(exportReqVO);
        // 导出 Excel
        List<FreezeInfoExcelVO> datas = FreezeInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品冻结信息.xls", "数据", FreezeInfoExcelVO.class, datas);
    }

}
