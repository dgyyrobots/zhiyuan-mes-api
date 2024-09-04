package com.dofast.module.qms.controller.admin.oqcline;

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

import com.dofast.module.qms.controller.admin.oqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.oqcline.OqcLineDO;
import com.dofast.module.qms.convert.oqcline.OqcLineConvert;
import com.dofast.module.qms.service.oqcline.OqcLineService;

@Tag(name = "质量管理 - 出货检验单行")
@RestController
@RequestMapping("/mes/qms/oqc-line")
@Validated
public class OqcLineController {

    @Resource
    private OqcLineService oqcLineService;

    @PostMapping("/create")
    @Operation(summary = "创建出货检验单行")
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:create')")
    public CommonResult<Long> createOqcLine(@Valid @RequestBody OqcLineCreateReqVO createReqVO) {
        return success(oqcLineService.createOqcLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新出货检验单行")
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:update')")
    public CommonResult<Boolean> updateOqcLine(@Valid @RequestBody OqcLineUpdateReqVO updateReqVO) {
        oqcLineService.updateOqcLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除出货检验单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:delete')")
    public CommonResult<Boolean> deleteOqcLine(@RequestParam("id") Long id) {
        oqcLineService.deleteOqcLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得出货检验单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:query')")
    public CommonResult<OqcLineRespVO> getOqcLine(@RequestParam("id") Long id) {
        OqcLineDO oqcLine = oqcLineService.getOqcLine(id);
        return success(OqcLineConvert.INSTANCE.convert(oqcLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得出货检验单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:query')")
    public CommonResult<List<OqcLineRespVO>> getOqcLineList(@RequestParam("ids") Collection<Long> ids) {
        List<OqcLineDO> list = oqcLineService.getOqcLineList(ids);
        return success(OqcLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得出货检验单行分页")
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:query')")
    public CommonResult<PageResult<OqcLineRespVO>> getOqcLinePage(@Valid OqcLinePageReqVO pageVO) {
        PageResult<OqcLineDO> pageResult = oqcLineService.getOqcLinePage(pageVO);
        return success(OqcLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出出货检验单行 Excel")
    @PreAuthorize("@ss.hasPermission('qms:oqc-line:export')")
    @OperateLog(type = EXPORT)
    public void exportOqcLineExcel(@Valid OqcLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OqcLineDO> list = oqcLineService.getOqcLineList(exportReqVO);
        // 导出 Excel
        List<OqcLineExcelVO> datas = OqcLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "出货检验单行.xls", "数据", OqcLineExcelVO.class, datas);
    }

}
