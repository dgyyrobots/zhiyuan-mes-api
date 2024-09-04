package com.dofast.module.pro.controller.admin.transconsume;

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

import com.dofast.module.pro.controller.admin.transconsume.vo.*;
import com.dofast.module.pro.dal.dataobject.transconsume.TransConsumeDO;
import com.dofast.module.pro.convert.transconsume.TransConsumeConvert;
import com.dofast.module.pro.service.transconsume.TransConsumeService;

@Tag(name = "生产管理 - 物料消耗记录")
@RestController
@RequestMapping("/mes/pro/trans-consume")
@Validated
public class TransConsumeController {

    @Resource
    private TransConsumeService transConsumeService;

    @PostMapping("/create")
    @Operation(summary = "创建物料消耗记录")
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:create')")
    public CommonResult<Long> createTransConsume(@Valid @RequestBody TransConsumeCreateReqVO createReqVO) {
        return success(transConsumeService.createTransConsume(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料消耗记录")
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:update')")
    public CommonResult<Boolean> updateTransConsume(@Valid @RequestBody TransConsumeUpdateReqVO updateReqVO) {
        transConsumeService.updateTransConsume(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料消耗记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:delete')")
    public CommonResult<Boolean> deleteTransConsume(@RequestParam("id") Long id) {
        transConsumeService.deleteTransConsume(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料消耗记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:query')")
    public CommonResult<TransConsumeRespVO> getTransConsume(@RequestParam("id") Long id) {
        TransConsumeDO transConsume = transConsumeService.getTransConsume(id);
        return success(TransConsumeConvert.INSTANCE.convert(transConsume));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料消耗记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:query')")
    public CommonResult<List<TransConsumeRespVO>> getTransConsumeList(@RequestParam("ids") Collection<Long> ids) {
        List<TransConsumeDO> list = transConsumeService.getTransConsumeList(ids);
        return success(TransConsumeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料消耗记录分页")
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:query')")
    public CommonResult<PageResult<TransConsumeRespVO>> getTransConsumePage(@Valid TransConsumePageReqVO pageVO) {
        PageResult<TransConsumeDO> pageResult = transConsumeService.getTransConsumePage(pageVO);
        return success(TransConsumeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料消耗记录 Excel")
    @PreAuthorize("@ss.hasPermission('pro:trans-consume:export')")
    @OperateLog(type = EXPORT)
    public void exportTransConsumeExcel(@Valid TransConsumeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<TransConsumeDO> list = transConsumeService.getTransConsumeList(exportReqVO);
        // 导出 Excel
        List<TransConsumeExcelVO> datas = TransConsumeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料消耗记录.xls", "数据", TransConsumeExcelVO.class, datas);
    }

}
