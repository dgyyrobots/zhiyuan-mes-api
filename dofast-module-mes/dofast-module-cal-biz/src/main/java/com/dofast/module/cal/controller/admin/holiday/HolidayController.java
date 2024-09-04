package com.dofast.module.cal.controller.admin.holiday;

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

import com.dofast.module.cal.controller.admin.holiday.vo.*;
import com.dofast.module.cal.dal.dataobject.holiday.HolidayDO;
import com.dofast.module.cal.convert.holiday.HolidayConvert;
import com.dofast.module.cal.service.holiday.HolidayService;

@Tag(name = "管理后台 - 节假日设置")
@RestController
@RequestMapping("/cal/holiday")
@Validated
public class HolidayController {

    @Resource
    private HolidayService holidayService;

    @PostMapping("/create")
    @Operation(summary = "创建节假日设置")
    @PreAuthorize("@ss.hasPermission('cal:holiday:create')")
    public CommonResult<Long> createHoliday(@Valid @RequestBody HolidayCreateReqVO createReqVO) {
        return success(holidayService.createHoliday(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新节假日设置")
    @PreAuthorize("@ss.hasPermission('cal:holiday:update')")
    public CommonResult<Boolean> updateHoliday(@Valid @RequestBody HolidayUpdateReqVO updateReqVO) {
        holidayService.updateHoliday(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除节假日设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cal:holiday:delete')")
    public CommonResult<Boolean> deleteHoliday(@RequestParam("id") Long id) {
        holidayService.deleteHoliday(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得节假日设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cal:holiday:query')")
    public CommonResult<HolidayRespVO> getHoliday(@RequestParam("id") Long id) {
        HolidayDO holiday = holidayService.getHoliday(id);
        return success(HolidayConvert.INSTANCE.convert(holiday));
    }

    @GetMapping("/list")
    @Operation(summary = "获得节假日设置列表")
    @Parameter(name = "ids", description = "编号列表", example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cal:holiday:query')")
    public CommonResult<List<HolidayRespVO>> getHolidayList(@RequestParam(name = "ids", required = false) Collection<Long> ids) {
        List<HolidayDO> list = holidayService.getHolidayList(ids);
        return success(HolidayConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得节假日设置分页")
    @PreAuthorize("@ss.hasPermission('cal:holiday:query')")
    public CommonResult<PageResult<HolidayRespVO>> getHolidayPage(@Valid HolidayPageReqVO pageVO) {
        PageResult<HolidayDO> pageResult = holidayService.getHolidayPage(pageVO);
        return success(HolidayConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出节假日设置 Excel")
    @PreAuthorize("@ss.hasPermission('cal:holiday:export')")
    @OperateLog(type = EXPORT)
    public void exportHolidayExcel(@Valid HolidayExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<HolidayDO> list = holidayService.getHolidayList(exportReqVO);
        // 导出 Excel
        List<HolidayExcelVO> datas = HolidayConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "节假日设置.xls", "数据", HolidayExcelVO.class, datas);
    }

}
