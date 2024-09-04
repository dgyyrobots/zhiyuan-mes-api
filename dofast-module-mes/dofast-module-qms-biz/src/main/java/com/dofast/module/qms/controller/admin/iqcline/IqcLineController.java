package com.dofast.module.qms.controller.admin.iqcline;

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

import com.dofast.module.qms.controller.admin.iqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.iqcline.IqcLineDO;
import com.dofast.module.qms.convert.iqcline.IqcLineConvert;
import com.dofast.module.qms.service.iqcline.IqcLineService;

@Tag(name = "质量管理 - 来料检验单行")
@RestController
@RequestMapping("/mes/qms/iqc-line")
@Validated
public class IqcLineController {

    @Resource
    private IqcLineService iqcLineService;

    @PostMapping("/create")
    @Operation(summary = "创建来料检验单行")
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:create')")
    public CommonResult<Long> createIqcLine(@Valid @RequestBody IqcLineCreateReqVO createReqVO) {
        return success(iqcLineService.createIqcLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新来料检验单行")
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:update')")
    public CommonResult<Boolean> updateIqcLine(@Valid @RequestBody IqcLineUpdateReqVO updateReqVO) {
        iqcLineService.updateIqcLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除来料检验单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:delete')")
    public CommonResult<Boolean> deleteIqcLine(@RequestParam("id") Long id) {
        iqcLineService.deleteIqcLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得来料检验单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:query')")
    public CommonResult<IqcLineRespVO> getIqcLine(@RequestParam("id") Long id) {
        IqcLineDO iqcLine = iqcLineService.getIqcLine(id);
        return success(IqcLineConvert.INSTANCE.convert(iqcLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得来料检验单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:query')")
    public CommonResult<List<IqcLineRespVO>> getIqcLineList(@RequestParam("ids") Collection<Long> ids) {
        List<IqcLineDO> list = iqcLineService.getIqcLineList(ids);
        return success(IqcLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得来料检验单行分页")
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:query')")
    public CommonResult<PageResult<IqcLineRespVO>> getIqcLinePage(@Valid IqcLinePageReqVO pageVO) {
        PageResult<IqcLineDO> pageResult = iqcLineService.getIqcLinePage(pageVO);
        return success(IqcLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出来料检验单行 Excel")
    @PreAuthorize("@ss.hasPermission('qms:iqc-line:export')")
    @OperateLog(type = EXPORT)
    public void exportIqcLineExcel(@Valid IqcLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IqcLineDO> list = iqcLineService.getIqcLineList(exportReqVO);
        // 导出 Excel
        List<IqcLineExcelVO> datas = IqcLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "来料检验单行.xls", "数据", IqcLineExcelVO.class, datas);
    }

}
