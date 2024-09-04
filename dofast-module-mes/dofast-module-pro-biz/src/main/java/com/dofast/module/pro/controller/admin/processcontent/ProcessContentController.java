package com.dofast.module.pro.controller.admin.processcontent;

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

import com.dofast.module.pro.controller.admin.processcontent.vo.*;
import com.dofast.module.pro.dal.dataobject.processcontent.ProcessContentDO;
import com.dofast.module.pro.convert.processcontent.ProcessContentConvert;
import com.dofast.module.pro.service.processcontent.ProcessContentService;

@Tag(name = "生产管理 - 生产工序内容")
@RestController
@RequestMapping("/mes/pro/cess-content")
@Validated
public class ProcessContentController {

    @Resource
    private ProcessContentService cessContentService;

    @PostMapping("/create")
    @Operation(summary = "创建生产工序内容")
    @PreAuthorize("@ss.hasPermission('pro:cess-content:create')")
    public CommonResult<Long> createcessContent(@Valid @RequestBody ProcessContentCreateReqVO createReqVO) {
        return success(cessContentService.createcessContent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新生产工序内容")
    @PreAuthorize("@ss.hasPermission('pro:cess-content:update')")
    public CommonResult<Boolean> updatecessContent(@Valid @RequestBody ProcessContentUpdateReqVO updateReqVO) {
        cessContentService.updatecessContent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除生产工序内容")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:cess-content:delete')")
    public CommonResult<Boolean> deletecessContent(@RequestParam("id") Long id) {
        cessContentService.deletecessContent(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得生产工序内容")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:cess-content:query')")
    public CommonResult<ProcessContentRespVO> getcessContent(@RequestParam("id") Long id) {
        ProcessContentDO cessContent = cessContentService.getcessContent(id);
        return success(ProcessContentConvert.INSTANCE.convert(cessContent));
    }

    @GetMapping("/list")
    @Operation(summary = "获得生产工序内容列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:cess-content:query')")
    public CommonResult<List<ProcessContentRespVO>> getcessContentList(@RequestParam("ids") Collection<Long> ids) {
        List<ProcessContentDO> list = cessContentService.getcessContentList(ids);
        return success(ProcessContentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得生产工序内容分页")
    @PreAuthorize("@ss.hasPermission('pro:cess-content:query')")
    public CommonResult<PageResult<ProcessContentRespVO>> getcessContentPage(@Valid ProcessContentPageReqVO pageVO) {
        PageResult<ProcessContentDO> pageResult = cessContentService.getcessContentPage(pageVO);
        return success(ProcessContentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出生产工序内容 Excel")
    @PreAuthorize("@ss.hasPermission('pro:cess-content:export')")
    @OperateLog(type = EXPORT)
    public void exportcessContentExcel(@Valid ProcessContentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProcessContentDO> list = cessContentService.getcessContentList(exportReqVO);
        // 导出 Excel
        List<ProcessContentExcelVO> datas = ProcessContentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "生产工序内容.xls", "数据", ProcessContentExcelVO.class, datas);
    }

}
