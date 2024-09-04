package com.dofast.module.trade.controller.admin.calculate;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.calculate.vo.*;
import com.dofast.module.trade.convert.calculate.CalculateRecordConvert;
import com.dofast.module.trade.dal.dataobject.calculate.CalculateRecordDO;
import com.dofast.module.trade.service.calculate.CalculateRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 计价记录")
@RestController
@RequestMapping("/trade/calculate-record")
@Validated
public class CalculateRecordController {

    @Resource
    private CalculateRecordService calculateRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建计价记录")
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:create')")
    public CommonResult<Long> createCalculateRecord(@Valid @RequestBody CalculateRecordCreateReqVO createReqVO) {
        return success(calculateRecordService.createCalculateRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新计价记录")
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:update')")
    public CommonResult<Boolean> updateCalculateRecord(@Valid @RequestBody CalculateRecordUpdateReqVO updateReqVO) {
        calculateRecordService.updateCalculateRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除计价记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:delete')")
    public CommonResult<Boolean> deleteCalculateRecord(@RequestParam("id") Long id) {
        calculateRecordService.deleteCalculateRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得计价记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:query')")
    public CommonResult<CalculateRecordRespVO> getCalculateRecord(@RequestParam("id") Long id) {
        CalculateRecordDO calculateRecord = calculateRecordService.getCalculateRecord(id);
        return success(CalculateRecordConvert.INSTANCE.convert(calculateRecord));
    }

    @GetMapping("/list")
    @Operation(summary = "获得计价记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:query')")
    public CommonResult<List<CalculateRecordRespVO>> getCalculateRecordList(@RequestParam("ids") Collection<Long> ids) {
        List<CalculateRecordDO> list = calculateRecordService.getCalculateRecordList(ids);
        return success(CalculateRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得计价记录分页")
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:query')")
    public CommonResult<PageResult<CalculateRecordRespVO>> getCalculateRecordPage(@Valid CalculateRecordPageReqVO pageVO) {
        PageResult<CalculateRecordDO> pageResult = calculateRecordService.getCalculateRecordPage(pageVO);
        return success(CalculateRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出计价记录 Excel")
    @PreAuthorize("@ss.hasPermission('trade:calculate-record:export')")
    @OperateLog(type = EXPORT)
    public void exportCalculateRecordExcel(@Valid CalculateRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CalculateRecordDO> list = calculateRecordService.getCalculateRecordList(exportReqVO);
        // 导出 Excel
        List<CalculateRecordExcelVO> datas = CalculateRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "计价记录.xls", "数据", CalculateRecordExcelVO.class, datas);
    }

}
