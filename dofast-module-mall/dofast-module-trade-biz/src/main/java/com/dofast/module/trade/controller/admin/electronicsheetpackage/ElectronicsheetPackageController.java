package com.dofast.module.trade.controller.admin.electronicsheetpackage;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.electronicsheetpackage.vo.*;
import com.dofast.module.trade.convert.electronicsheetpackage.ElectronicsheetPackageConvert;
import com.dofast.module.trade.dal.dataobject.electronicsheetpackage.ElectronicsheetPackageDO;
import com.dofast.module.trade.service.electronicsheetpackage.ElectronicsheetPackageService;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 电子面单")
@RestController
@RequestMapping("/trade/electronicsheet-package")
@Validated
public class ElectronicsheetPackageController {

    @Resource
    private ElectronicsheetPackageService electronicsheetPackageService;

    @PostMapping("/create")
    @Operation(summary = "创建电子面单")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:create')")
    public CommonResult<Integer> createElectronicsheetPackage(@Valid @RequestBody ElectronicsheetPackageCreateReqVO createReqVO) {
        return success(electronicsheetPackageService.createElectronicsheetPackage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电子面单")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:update')")
    public CommonResult<Boolean> updateElectronicsheetPackage(@Valid @RequestBody ElectronicsheetPackageUpdateReqVO updateReqVO) {
        electronicsheetPackageService.updateElectronicsheetPackage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电子面单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:delete')")
    public CommonResult<Boolean> deleteElectronicsheetPackage(@RequestParam("id") Integer id) {
        electronicsheetPackageService.deleteElectronicsheetPackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电子面单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:query')")
    public CommonResult<ElectronicsheetPackageRespVO> getElectronicsheetPackage(@RequestParam("id") Integer id) {
        ElectronicsheetPackageDO electronicsheetPackage = electronicsheetPackageService.getElectronicsheetPackage(id);
        return success(ElectronicsheetPackageConvert.INSTANCE.convert(electronicsheetPackage));
    }

    @GetMapping("/get-by-order-no")
    @Operation(summary = "通过订单编号获得电子面单")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:query')")
    public CommonResult<List<ElectronicsheetPackageRespVO>> getElectronicsheetPackageByOrderNo(@RequestParam("orderNo") String orderNo) {
        List<ElectronicsheetPackageDO> list = electronicsheetPackageService.getElectronicsheetPackageListByOrderNo(orderNo);
        if (list == null){
            return success(new ArrayList<>());
        }
        return success(ElectronicsheetPackageConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list")
    @Operation(summary = "获得电子面单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:query')")
    public CommonResult<List<ElectronicsheetPackageRespVO>> getElectronicsheetPackageList(@RequestParam("ids") Collection<Integer> ids) {
        List<ElectronicsheetPackageDO> list = electronicsheetPackageService.getElectronicsheetPackageList(ids);
        return success(ElectronicsheetPackageConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电子面单分页")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:query')")
    public CommonResult<PageResult<ElectronicsheetPackageRespVO>> getElectronicsheetPackagePage(@Valid ElectronicsheetPackagePageReqVO pageVO) {
        PageResult<ElectronicsheetPackageDO> pageResult = electronicsheetPackageService.getElectronicsheetPackagePage(pageVO);
        return success(ElectronicsheetPackageConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电子面单 Excel")
    @PreAuthorize("@ss.hasPermission('trade:electronicsheet-package:export')")
    @OperateLog(type = EXPORT)
    public void exportElectronicsheetPackageExcel(@Valid ElectronicsheetPackageExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ElectronicsheetPackageDO> list = electronicsheetPackageService.getElectronicsheetPackageList(exportReqVO);
        // 导出 Excel
        List<ElectronicsheetPackageExcelVO> datas = ElectronicsheetPackageConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "电子面单.xls", "数据", ElectronicsheetPackageExcelVO.class, datas);
    }

    @GetMapping("/getByOrderNo")
    @Operation(summary = "获得电子面单")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    public List<ElectronicsheetPackageDO> getElectronicsheetPackageDOByOrderNo(String orderNo){
        List<ElectronicsheetPackageDO> electronicsheetPackageDOs=electronicsheetPackageService.getElectronicsheetByOrderNo(orderNo);
        return electronicsheetPackageDOs;
    }

}
