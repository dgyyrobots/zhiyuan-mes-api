package com.dofast.module.pro.controller.admin.andonrecord;

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

import com.dofast.module.pro.controller.admin.andonrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.andonrecord.AndonRecordDO;
import com.dofast.module.pro.convert.andonrecord.AndonRecordConvert;
import com.dofast.module.pro.service.andonrecord.AndonRecordService;

@Tag(name = "管理后台 - 安灯呼叫记录")
@RestController
@RequestMapping("/pro/andon-record")
@Validated
public class AndonRecordController {

    @Resource
    private AndonRecordService andonRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建安灯呼叫记录")
    @PreAuthorize("@ss.hasPermission('pro:andon-record:create')")
    public CommonResult<Long> createAndonRecord(@Valid @RequestBody AndonRecordCreateReqVO createReqVO) {
        return success(andonRecordService.createAndonRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新安灯呼叫记录")
    @PreAuthorize("@ss.hasPermission('pro:andon-record:update')")
    public CommonResult<Boolean> updateAndonRecord(@Valid @RequestBody AndonRecordUpdateReqVO updateReqVO) {
        andonRecordService.updateAndonRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除安灯呼叫记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:andon-record:delete')")
    public CommonResult<Boolean> deleteAndonRecord(@RequestParam("id") Long id) {
        andonRecordService.deleteAndonRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得安灯呼叫记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:andon-record:query')")
    public CommonResult<AndonRecordRespVO> getAndonRecord(@RequestParam("id") Long id) {
        AndonRecordDO andonRecord = andonRecordService.getAndonRecord(id);
        return success(AndonRecordConvert.INSTANCE.convert(andonRecord));
    }

    @GetMapping("/list")
    @Operation(summary = "获得安灯呼叫记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:andon-record:query')")
    public CommonResult<List<AndonRecordRespVO>> getAndonRecordList(@RequestParam("ids") Collection<Long> ids) {
        List<AndonRecordDO> list = andonRecordService.getAndonRecordList(ids);
        return success(AndonRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得安灯呼叫记录分页")
    @PreAuthorize("@ss.hasPermission('pro:andon-record:query')")
    public CommonResult<PageResult<AndonRecordRespVO>> getAndonRecordPage(@Valid AndonRecordPageReqVO pageVO) {
        PageResult<AndonRecordDO> pageResult = andonRecordService.getAndonRecordPage(pageVO);
        return success(AndonRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出安灯呼叫记录 Excel")
    @PreAuthorize("@ss.hasPermission('pro:andon-record:export')")
    @OperateLog(type = EXPORT)
    public void exportAndonRecordExcel(@Valid AndonRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AndonRecordDO> list = andonRecordService.getAndonRecordList(exportReqVO);
        // 导出 Excel
        List<AndonRecordExcelVO> datas = AndonRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "安灯呼叫记录.xls", "数据", AndonRecordExcelVO.class, datas);
    }

}
