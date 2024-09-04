package com.dofast.module.deliver.controller.admin.expresselectronicsheet;

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

import com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo.*;
import com.dofast.module.deliver.dal.dataobject.expresselectronicsheet.ExpressElectronicsheetDO;
import com.dofast.module.deliver.convert.expresselectronicsheet.ExpressElectronicsheetConvert;
import com.dofast.module.deliver.service.expresselectronicsheet.ExpressElectronicsheetService;

@Tag(name = "管理后台 - 电子面单")
@RestController
@RequestMapping("/deliver/express-electronicsheet")
@Validated
public class ExpressElectronicsheetController {

    @Resource
    private ExpressElectronicsheetService expressElectronicsheetService;

    @PostMapping("/create")
    @Operation(summary = "创建电子面单")
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:create')")
    public CommonResult<Long> createExpressElectronicsheet(@Valid @RequestBody ExpressElectronicsheetCreateReqVO createReqVO) {
        return success(expressElectronicsheetService.createExpressElectronicsheet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电子面单")
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:update')")
    public CommonResult<Boolean> updateExpressElectronicsheet(@Valid @RequestBody ExpressElectronicsheetUpdateReqVO updateReqVO) {
          expressElectronicsheetService.updateExpressElectronicsheet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电子面单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:delete')")
    public CommonResult<Boolean> deleteExpressElectronicsheet(@RequestParam("id") Long id) {
        expressElectronicsheetService.deleteExpressElectronicsheet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电子面单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:query')")
    public CommonResult<ExpressElectronicsheetRespVO> getExpressElectronicsheet(@RequestParam("id") Long id) {
        ExpressElectronicsheetDO expressElectronicsheet = expressElectronicsheetService.getExpressElectronicsheet(id);
        return success(ExpressElectronicsheetConvert.INSTANCE.convert(expressElectronicsheet));
    }

    @GetMapping("/list")
    @Operation(summary = "获得电子面单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:query')")
    public CommonResult<List<ExpressElectronicsheetRespVO>> getExpressElectronicsheetList(@RequestParam("ids") Collection<Long> ids) {
        List<ExpressElectronicsheetDO> list = expressElectronicsheetService.getExpressElectronicsheetList(ids);
        return success(ExpressElectronicsheetConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电子面单分页")
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:query')")
    public CommonResult<PageResult<ExpressElectronicsheetRespVO>> getExpressElectronicsheetPage(@Valid ExpressElectronicsheetPageReqVO pageVO) {
        PageResult<ExpressElectronicsheetDO> pageResult = expressElectronicsheetService.getExpressElectronicsheetPage(pageVO);
        return success(ExpressElectronicsheetConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电子面单 Excel")
    @PreAuthorize("@ss.hasPermission('deliver:express-electronicsheet:export')")
    @OperateLog(type = EXPORT)
    public void exportExpressElectronicsheetExcel(@Valid ExpressElectronicsheetExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ExpressElectronicsheetDO> list = expressElectronicsheetService.getExpressElectronicsheetList(exportReqVO);
        // 导出 Excel
        List<ExpressElectronicsheetExcelVO> datas = ExpressElectronicsheetConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "电子面单.xls", "数据", ExpressElectronicsheetExcelVO.class, datas);
    }

}
