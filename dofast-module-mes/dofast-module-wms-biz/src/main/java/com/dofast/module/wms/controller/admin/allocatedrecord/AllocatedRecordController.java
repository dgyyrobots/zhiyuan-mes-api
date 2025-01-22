package com.dofast.module.wms.controller.admin.allocatedrecord;

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

import com.dofast.module.wms.controller.admin.allocatedrecord.vo.*;
import com.dofast.module.wms.dal.dataobject.allocatedrecord.AllocatedRecordDO;
import com.dofast.module.wms.convert.allocatedrecord.AllocatedRecordConvert;
import com.dofast.module.wms.service.allocatedrecord.AllocatedRecordService;

@Tag(name = "管理后台 - 调拨单身记录")
@RestController
@RequestMapping("/wms/allocated-record")
@Validated
public class AllocatedRecordController {

    @Resource
    private AllocatedRecordService allocatedRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建调拨单身记录")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:create')")
    public CommonResult<Long> createAllocatedRecord(@Valid @RequestBody AllocatedRecordCreateReqVO createReqVO) {
        return success(allocatedRecordService.createAllocatedRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新调拨单身记录")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:update')")
    public CommonResult<Boolean> updateAllocatedRecord(@Valid @RequestBody AllocatedRecordUpdateReqVO updateReqVO) {
        allocatedRecordService.updateAllocatedRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除调拨单身记录")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:delete')")
    public CommonResult<Boolean> deleteAllocatedRecord(@RequestParam("id") Long id) {
        allocatedRecordService.deleteAllocatedRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得调拨单身记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:query')")
    public CommonResult<AllocatedRecordRespVO> getAllocatedRecord(@RequestParam("id") Long id) {
        AllocatedRecordDO allocatedRecord = allocatedRecordService.getAllocatedRecord(id);
        return success(AllocatedRecordConvert.INSTANCE.convert(allocatedRecord));
    }

    @GetMapping("/getByHeaderId")
    @Operation(summary = "获得调拨单身记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:query')")
    public CommonResult<List<AllocatedRecordRespVO>> getAllocatedRecordByHeaderId(@RequestParam("id") Long id) {
        AllocatedRecordExportReqVO exportReqVO = new AllocatedRecordExportReqVO();
        exportReqVO.setAllocatedId(id);
        List<AllocatedRecordDO> allocatedRecord = allocatedRecordService.getAllocatedRecordList(exportReqVO);
        return success(AllocatedRecordConvert.INSTANCE.convertList(allocatedRecord));
    }

    @GetMapping("/list")
    @Operation(summary = "获得调拨单身记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:query')")
    public CommonResult<List<AllocatedRecordRespVO>> getAllocatedRecordList(@RequestParam("ids") Collection<Long> ids) {
        List<AllocatedRecordDO> list = allocatedRecordService.getAllocatedRecordList(ids);
        return success(AllocatedRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得调拨单身记录分页")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:query')")
    public CommonResult<PageResult<AllocatedRecordRespVO>> getAllocatedRecordPage(@Valid AllocatedRecordPageReqVO pageVO) {
        PageResult<AllocatedRecordDO> pageResult = allocatedRecordService.getAllocatedRecordPage(pageVO);
        return success(AllocatedRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出调拨单身记录 Excel")
    //@PreAuthorize("@ss.hasPermission('wms:allocated-record:export')")
    @OperateLog(type = EXPORT)
    public void exportAllocatedRecordExcel(@Valid AllocatedRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AllocatedRecordDO> list = allocatedRecordService.getAllocatedRecordList(exportReqVO);
        // 导出 Excel
        List<AllocatedRecordExcelVO> datas = AllocatedRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "调拨单身记录.xls", "数据", AllocatedRecordExcelVO.class, datas);
    }

}
