package com.dofast.module.qms.controller.admin.ipqcline;

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

import com.dofast.module.qms.controller.admin.ipqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqcline.IpqcLineDO;
import com.dofast.module.qms.convert.ipqcline.IpqcLineConvert;
import com.dofast.module.qms.service.ipqcline.IpqcLineService;

@Tag(name = "质量管理 - 过程检验单行")
@RestController
@RequestMapping("/mes/qms/ipqc-line")
@Validated
public class IpqcLineController {

    @Resource
    private IpqcLineService ipqcLineService;

    @PostMapping("/create")
    @Operation(summary = "创建过程检验单行")
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:create')")
    public CommonResult<Long> createIpqcLine(@Valid @RequestBody IpqcLineCreateReqVO createReqVO) {
        return success(ipqcLineService.createIpqcLine(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新过程检验单行")
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:update')")
    public CommonResult<Boolean> updateIpqcLine(@Valid @RequestBody IpqcLineUpdateReqVO updateReqVO) {
        ipqcLineService.updateIpqcLine(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除过程检验单行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:delete')")
    public CommonResult<Boolean> deleteIpqcLine(@RequestParam("id") Long id) {
        ipqcLineService.deleteIpqcLine(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得过程检验单行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:query')")
    public CommonResult<IpqcLineRespVO> getIpqcLine(@RequestParam("id") Long id) {
        IpqcLineDO ipqcLine = ipqcLineService.getIpqcLine(id);
        return success(IpqcLineConvert.INSTANCE.convert(ipqcLine));
    }

    @GetMapping("/list")
    @Operation(summary = "获得过程检验单行列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:query')")
    public CommonResult<List<IpqcLineRespVO>> getIpqcLineList(@RequestParam("ids") Collection<Long> ids) {
        List<IpqcLineDO> list = ipqcLineService.getIpqcLineList(ids);
        return success(IpqcLineConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得过程检验单行分页")
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:query')")
    public CommonResult<PageResult<IpqcLineRespVO>> getIpqcLinePage(@Valid IpqcLinePageReqVO pageVO) {
        PageResult<IpqcLineDO> pageResult = ipqcLineService.getIpqcLinePage(pageVO);
        return success(IpqcLineConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出过程检验单行 Excel")
    @PreAuthorize("@ss.hasPermission('qms:ipqc-line:export')")
    @OperateLog(type = EXPORT)
    public void exportIpqcLineExcel(@Valid IpqcLineExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IpqcLineDO> list = ipqcLineService.getIpqcLineList(exportReqVO);
        // 导出 Excel
        List<IpqcLineExcelVO> datas = IpqcLineConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "过程检验单行.xls", "数据", IpqcLineExcelVO.class, datas);
    }

}
