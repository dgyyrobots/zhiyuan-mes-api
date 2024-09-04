package com.dofast.module.member.controller.admin.loction;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.framework.tenant.core.aop.TenantIgnore;
import com.dofast.module.member.controller.admin.loction.vo.*;
import com.dofast.module.member.convert.location.LocationConvert;
import com.dofast.module.member.dal.dataobject.location.LocationDO;
import com.dofast.module.member.service.location.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


@Tag(name = "管理后台 - 三级位置信息")
@RestController
@RequestMapping("/locations/")
@Validated
public class LocationController {

    @Resource
    private LocationService Service;

    @PostMapping("/create")
    @Operation(summary = "创建三级位置信息")
    @PreAuthorize("@ss.hasPermission('locations::create')")
    @TenantIgnore
    public CommonResult<Integer> create(@Valid @RequestBody LocationCreateReqVO createReqVO) {
        return success(Service.create(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新三级位置信息")
    @PreAuthorize("@ss.hasPermission('locations::update')")
    @TenantIgnore
    public CommonResult<Boolean> update(@Valid @RequestBody LocationUpdateReqVO updateReqVO) {
        Service.update(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除三级位置信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('locations::delete')")
    @TenantIgnore
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        Service.delete(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得三级位置信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @TenantIgnore
    public CommonResult<LocationRespVO> get(@RequestParam("id") Integer id) {
        LocationDO locationDO = Service.get(id);
        return success(LocationConvert.INSTANCE.convert(locationDO));
    }

    @GetMapping("/list")
    @Operation(summary = "获得三级位置信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @TenantIgnore
    public CommonResult<List<LocationRespVO>> getList(@RequestParam("ids") Collection<Integer> ids) {
        List<LocationDO> list = Service.getList(ids);
        return success(LocationConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得三级位置信息分页")
    @TenantIgnore
    public CommonResult<PageResult<LocationRespVO>> getPage(@Valid LocationPageReqVO pageVO) {
        PageResult<LocationDO> pageResult = Service.getPage(pageVO);
        return success(LocationConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出三级位置信息 Excel")
    @PreAuthorize("@ss.hasPermission('locations::export')")
    @OperateLog(type = EXPORT)
    @TenantIgnore
    public void exportExcel(@Valid LocationExportReqVO exportReqVO,
                            HttpServletResponse response) throws IOException {
        List<LocationDO> list = Service.getList(exportReqVO);
        // 导出 Excel
        List<LocationExcelVO> datas = LocationConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "三级位置信息.xls", "数据", LocationExcelVO.class, datas);
    }

    @GetMapping("/get-location")
    @Operation(summary = "地址信息列表获取")
    @Parameter(name = "id", description = "编号", example = "1024")
    @TenantIgnore
    @PermitAll
    public CommonResult<List<LocationQueryId>> getLocation(@RequestParam(name ="id", required = false) Integer id) {
        List<LocationQueryId> list = Service.getLocationList(id);
        return success(list);
    }
}
