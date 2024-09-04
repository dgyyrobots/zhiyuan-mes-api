package com.dofast.module.wms.controller.admin.package1;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.wms.controller.admin.barcode.vo.BarcodeBaseVO;
import com.dofast.module.wms.controller.admin.barcode.vo.BarcodeCreateReqVO;
import com.dofast.module.wms.dal.dataobject.barcode.BarcodeDO;
import com.dofast.module.wms.enums.ErrorCodeConstants;
import com.dofast.module.wms.service.barcode.BarcodeService;
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

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.wms.controller.admin.package1.vo.*;
import com.dofast.module.wms.dal.dataobject.package1.PackageDO;
import com.dofast.module.wms.convert.package1.PackageConvert;
import com.dofast.module.wms.service.package1.PackageService;

@Tag(name = "仓储管理 - 装箱单")
@RestController
@RequestMapping("/mes/wms/package")
@Validated
public class PackageController {

    @Resource
    private PackageService packageService;

    @Resource
    private BarcodeService barcodeService;

    @PostMapping("/create")
    @Operation(summary = "创建装箱单")
    @PreAuthorize("@ss.hasPermission('wms:package:create')")
    public CommonResult<Long> createPackage(@Valid @RequestBody PackageCreateReqVO createReqVO) throws IOException{
        if(Constant.NOT_UNIQUE.equals(packageService.checkPackgeCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.PACKAGE_CODE_EXISTS);
        }
        if(createReqVO.getParentId() !=null){
            PackageDO parentPackage = packageService.selectWmPackageByPackageId(createReqVO.getParentId());
            if(StrUtils.isNotNull(parentPackage)){
                createReqVO.setAncestors(parentPackage.getAncestors()+","+parentPackage.getId());
            }
        }

        Long ret =packageService.createPackage(createReqVO);
        createReqVO.setId(ret);

        //装箱单保存成功就自动生成对应的箱条码
        BarcodeCreateReqVO wmBarcode = new BarcodeCreateReqVO();
        wmBarcode.setBussinessId(createReqVO.getId());
        wmBarcode.setBussinessCode(createReqVO.getPackageCode());
        wmBarcode.setBussinessName(createReqVO.getClientName());
        wmBarcode.setBarcodeType(Constant.BARCODE_TYPE_PACKAGE);//类型设置为箱条码
        wmBarcode.setBarcodeFormart(Constant.QR_CODE);//设置为二维码
        wmBarcode.setBarcodeContent(""+Constant.BARCODE_TYPE_PACKAGE+"-"+createReqVO.getPackageCode());
        String path =barcodeService.generateBarcode(wmBarcode);
        wmBarcode.setBarcodeUrl(path);
        barcodeService.createBarcode(wmBarcode);

        //将条码的URL更新上去
        createReqVO.setBarcodeId(wmBarcode.getId());
        createReqVO.setBarcodeContent(wmBarcode.getBarcodeContent());
        createReqVO.setBarcodeUrl(path);

        PackageUpdateReqVO updateVo = PackageConvert.INSTANCE.convert01(createReqVO);
        packageService.updatePackage(updateVo);
        return success(ret);
    }

    @PutMapping("/update")
    @Operation(summary = "更新装箱单")
    @PreAuthorize("@ss.hasPermission('wms:package:update')")
    public CommonResult<Boolean> updatePackage(@Valid @RequestBody PackageUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(packageService.checkPackgeCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.PACKAGE_CODE_EXISTS);
        }
        packageService.updatePackage(updateReqVO);
        return success(true);
    }

    /**
     * 添加子箱
     */
    @PreAuthorize("@ss.hasPermission('wms:package:update')")
    @PutMapping("/addsub")
    public CommonResult addSubPackage(@RequestBody PackageUpdateReqVO wmPackage){
        //不能添加自己
        if(wmPackage.getId().longValue() == wmPackage.getParentId().longValue()){
            return error(ErrorCodeConstants.PACKAGE_NOT_ADD_SELL);
        }

        //已经有父箱的不能再次添加
        PackageDO subPackage = packageService.selectWmPackageByPackageId(wmPackage.getId());
        if(!"0".equals(subPackage.getAncestors())){
            return error(ErrorCodeConstants.PACKAGE_CHILD_HAS_PARENT);
        }

        //更新当前子箱的父箱列表
        PackageDO parentPackage = packageService.selectWmPackageByPackageId(wmPackage.getParentId());
        if(StrUtils.isNotNull(parentPackage)){
            wmPackage.setAncestors(parentPackage.getAncestors()+","+parentPackage.getId());
        }
        packageService.updatePackage(wmPackage);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除装箱单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:package:delete')")
    public CommonResult<Boolean> deletePackage(@RequestParam("id") Long id) {
        packageService.deletePackage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得装箱单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:package:query')")
    public CommonResult<PackageRespVO> getPackage(@RequestParam("id") Long id) {
        PackageDO packageDO = packageService.getPackage(id);
        return success(PackageConvert.INSTANCE.convert(packageDO));
    }

    @GetMapping("/list")
    @Operation(summary = "获得装箱单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('wms:package:query')")
    public CommonResult<List<PackageRespVO>> getPackageList(@RequestParam("ids") Collection<Long> ids) {
        List<PackageDO> list = packageService.getPackageList(ids);
        return success(PackageConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得装箱单分页")
    @PreAuthorize("@ss.hasPermission('wms:package:query')")
    public CommonResult<PageResult<PackageRespVO>> getPackagePage(@Valid PackagePageReqVO pageVO) {
        PageResult<PackageDO> pageResult = packageService.getPackagePage(pageVO);
        return success(PackageConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出装箱单 Excel")
    @PreAuthorize("@ss.hasPermission('wms:package:export')")
    @OperateLog(type = EXPORT)
    public void exportPackageExcel(@Valid PackageExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PackageDO> list = packageService.getPackageList(exportReqVO);
        // 导出 Excel
        List<PackageExcelVO> datas = PackageConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "装箱单.xls", "数据", PackageExcelVO.class, datas);
    }

}
