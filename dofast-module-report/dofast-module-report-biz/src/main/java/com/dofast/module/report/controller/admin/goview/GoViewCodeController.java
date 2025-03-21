package com.dofast.module.report.controller.admin.goview;

import com.dofast.module.report.controller.admin.goview.vo.code.*;
import com.dofast.module.report.enums.ErrorCodeConstants;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import javax.servlet.http.*;
import java.time.LocalDateTime;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.pojo.CommonResult.error;
import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;
import com.dofast.module.report.convert.goviewcode.GoViewCodeConvert;
import com.dofast.module.report.service.goview.GoViewCodeService;

@Tag(name = "管理后台 - GoView登录")
@RequestMapping("/report/go-view/code")
@Validated
public class GoViewCodeController {

    @Resource
    private GoViewCodeService goViewCodeService;



    @PostMapping("/create")
    @Operation(summary = "创建GoView登录")
    @PreAuthorize("@ss.hasPermission('report:go-view-code:create')")
    public CommonResult<Long> createGoViewCode(@Valid @RequestBody GoViewCodeCreateReqVO createReqVO) {
        return success(goViewCodeService.createGoViewCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新GoView登录")
    @PreAuthorize("@ss.hasPermission('report:go-view-code:update')")
    public CommonResult<Boolean> updateGoViewCode(@Valid @RequestBody GoViewCodeUpdateReqVO updateReqVO) {
        goViewCodeService.updateGoViewCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除GoView登录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('report:go-view-code:delete')")
    public CommonResult<Boolean> deleteGoViewCode(@RequestParam("id") Long id) {
        goViewCodeService.deleteGoViewCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得GoView登录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('report:go-view-code:query')")
    public CommonResult<GoViewCodeRespVO> getGoViewCode(@RequestParam("id") Long id) {
        GoViewCodeDO goViewCode = goViewCodeService.getGoViewCode(id);
        return success(GoViewCodeConvert.INSTANCE.convert(goViewCode));
    }

    @GetMapping("/list")
    @Operation(summary = "获得GoView登录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('report:go-view-code:query')")
    public CommonResult<List<GoViewCodeRespVO>> getGoViewCodeList(@RequestParam("ids") Collection<Long> ids) {
        List<GoViewCodeDO> list = goViewCodeService.getGoViewCodeList(ids);
        return success(GoViewCodeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得GoView登录分页")
    @PreAuthorize("@ss.hasPermission('report:go-view-code:query')")
    public CommonResult<PageResult<GoViewCodeRespVO>> getGoViewCodePage(@Valid GoViewCodePageReqVO pageVO) {
        PageResult<GoViewCodeDO> pageResult = goViewCodeService.getGoViewCodePage(pageVO);
        return success(GoViewCodeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出GoView登录 Excel")
    @PreAuthorize("@ss.hasPermission('report:go-view-code:export')")
    @OperateLog(type = EXPORT)
    public void exportGoViewCodeExcel(@Valid GoViewCodeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GoViewCodeDO> list = goViewCodeService.getGoViewCodeList(exportReqVO);
        // 导出 Excel
        List<GoViewCodeExcelVO> datas = GoViewCodeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "GoView登录.xls", "数据", GoViewCodeExcelVO.class, datas);
    }




}
