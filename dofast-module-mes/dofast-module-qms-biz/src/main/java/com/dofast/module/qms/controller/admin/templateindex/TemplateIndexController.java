package com.dofast.module.qms.controller.admin.templateindex;

import com.dofast.module.qms.dal.dataobject.index.IndexDO;
import com.dofast.module.qms.service.index.IndexService;
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

import com.dofast.module.qms.controller.admin.templateindex.vo.*;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;
import com.dofast.module.qms.convert.templateindex.TemplateIndexConvert;
import com.dofast.module.qms.service.templateindex.TemplateIndexService;

@Tag(name = "质量管理 - 检测模板-检测项")
@RestController
@RequestMapping("/mes/qms/template-index")
@Validated
public class TemplateIndexController {

    @Resource
    private TemplateIndexService templateIndexService;
    @Resource
    private IndexService indexService;

    @PostMapping("/create")
    @Operation(summary = "创建检测模板-检测项")
    @PreAuthorize("@ss.hasPermission('qms:template-index:create')")
    public CommonResult<Long> createTemplateIndex(@Valid @RequestBody TemplateIndexCreateReqVO createReqVO) {
        IndexDO index =indexService.getIndex(createReqVO.getIndexId());
        createReqVO.setIndexCode(index.getIndexCode());
        createReqVO.setIndexName(index.getIndexName());
        createReqVO.setIndexType(index.getIndexType());
        createReqVO.setQcTool(index.getQcTool());
        return success(templateIndexService.createTemplateIndex(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测模板-检测项")
    @PreAuthorize("@ss.hasPermission('qms:template-index:update')")
    public CommonResult<Boolean> updateTemplateIndex(@Valid @RequestBody TemplateIndexUpdateReqVO updateReqVO) {
        IndexDO index =indexService.getIndex(updateReqVO.getIndexId());
        updateReqVO.setIndexCode(index.getIndexCode());
        updateReqVO.setIndexName(index.getIndexName());
        updateReqVO.setIndexType(index.getIndexType());
        updateReqVO.setQcTool(index.getQcTool());
        templateIndexService.updateTemplateIndex(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测模板-检测项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('qms:template-index:delete')")
    public CommonResult<Boolean> deleteTemplateIndex(@RequestParam("id") Long id) {
        templateIndexService.deleteTemplateIndex(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测模板-检测项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('qms:template-index:query')")
    public CommonResult<TemplateIndexRespVO> getTemplateIndex(@RequestParam("id") Long id) {
        TemplateIndexDO templateIndex = templateIndexService.getTemplateIndex(id);
        return success(TemplateIndexConvert.INSTANCE.convert(templateIndex));
    }

    @GetMapping("/list")
    @Operation(summary = "获得检测模板-检测项列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('qms:template-index:query')")
    public CommonResult<List<TemplateIndexRespVO>> getTemplateIndexList(@RequestParam("ids") Collection<Long> ids) {
        List<TemplateIndexDO> list = templateIndexService.getTemplateIndexList(ids);
        return success(TemplateIndexConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测模板-检测项分页")
    @PreAuthorize("@ss.hasPermission('qms:template-index:query')")
    public CommonResult<PageResult<TemplateIndexRespVO>> getTemplateIndexPage(@Valid TemplateIndexPageReqVO pageVO) {
        PageResult<TemplateIndexDO> pageResult = templateIndexService.getTemplateIndexPage(pageVO);
        return success(TemplateIndexConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测模板-检测项 Excel")
    @PreAuthorize("@ss.hasPermission('qms:template-index:export')")
    @OperateLog(type = EXPORT)
    public void exportTemplateIndexExcel(@Valid TemplateIndexExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TemplateIndexDO> list = templateIndexService.getTemplateIndexList(exportReqVO);
        // 导出 Excel
        List<TemplateIndexExcelVO> datas = TemplateIndexConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "检测模板-检测项.xls", "数据", TemplateIndexExcelVO.class, datas);
    }

}
