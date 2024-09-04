package com.dofast.module.iot.controller.admin.firmware;

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

import com.dofast.module.iot.controller.admin.firmware.vo.*;
import com.dofast.module.iot.dal.dataobject.firmware.FirmwareDO;
import com.dofast.module.iot.convert.firmware.FirmwareConvert;
import com.dofast.module.iot.service.firmware.FirmwareService;

@Tag(name = "管理后台 - 产品固件")
@RestController
@RequestMapping("/iot/firmware")
@Validated
public class FirmwareController {

    @Resource
    private FirmwareService firmwareService;

    @PostMapping("/create")
    @Operation(summary = "创建产品固件")
    @PreAuthorize("@ss.hasPermission('iot:firmware:create')")
    public CommonResult<Long> createFirmware(@Valid @RequestBody FirmwareCreateReqVO createReqVO) {
        return success(firmwareService.createFirmware(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品固件")
    @PreAuthorize("@ss.hasPermission('iot:firmware:update')")
    public CommonResult<Boolean> updateFirmware(@Valid @RequestBody FirmwareUpdateReqVO updateReqVO) {
        firmwareService.updateFirmware(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品固件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('iot:firmware:delete')")
    public CommonResult<Boolean> deleteFirmware(@RequestParam("id") Long id) {
        firmwareService.deleteFirmware(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品固件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('iot:firmware:query')")
    public CommonResult<FirmwareRespVO> getFirmware(@RequestParam("id") Long id) {
        FirmwareDO firmware = firmwareService.getFirmware(id);
        return success(FirmwareConvert.INSTANCE.convert(firmware));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品固件列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('iot:firmware:query')")
    public CommonResult<List<FirmwareRespVO>> getFirmwareList(@RequestParam("ids") Collection<Long> ids) {
        List<FirmwareDO> list = firmwareService.getFirmwareList(ids);
        return success(FirmwareConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品固件分页")
    @PreAuthorize("@ss.hasPermission('iot:firmware:query')")
    public CommonResult<PageResult<FirmwareRespVO>> getFirmwarePage(@Valid FirmwarePageReqVO pageVO) {
        PageResult<FirmwareDO> pageResult = firmwareService.getFirmwarePage(pageVO);
        return success(FirmwareConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品固件 Excel")
    @PreAuthorize("@ss.hasPermission('iot:firmware:export')")
    @OperateLog(type = EXPORT)
    public void exportFirmwareExcel(@Valid FirmwareExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FirmwareDO> list = firmwareService.getFirmwareList(exportReqVO);
        // 导出 Excel
        List<FirmwareExcelVO> datas = FirmwareConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品固件.xls", "数据", FirmwareExcelVO.class, datas);
    }

}
