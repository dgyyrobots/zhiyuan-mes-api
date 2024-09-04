package com.dofast.module.pro.controller.admin.routeproductbom;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.enums.ErrorCodeConstants;
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

import com.dofast.module.pro.controller.admin.routeproductbom.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproductbom.RouteProductBomDO;
import com.dofast.module.pro.convert.routeproductbom.RouteProductBomConvert;
import com.dofast.module.pro.service.routeproductbom.RouteProductBomService;

@Tag(name = "生产管理 - 产品制程物料BOM")
@RestController
@RequestMapping("/mes/pro/route-product-bom")
@Validated
public class RouteProductBomController {

    @Resource
    private RouteProductBomService routeProductBomService;

    @PostMapping("/create")
    @Operation(summary = "创建产品制程物料BOM")
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:create')")
    public CommonResult<Long> createRouteProductBom(@Valid @RequestBody RouteProductBomCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(routeProductBomService.checkUnique(createReqVO))){
            return error(ErrorCodeConstants.ROUTE_PRODUCT_BOM_HAS_IN_ROUTE);
        }
        return success(routeProductBomService.createRouteProductBom(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品制程物料BOM")
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:update')")
    public CommonResult<Boolean> updateRouteProductBom(@Valid @RequestBody RouteProductBomUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(routeProductBomService.checkUnique(updateReqVO))){
            return error(ErrorCodeConstants.ROUTE_PRODUCT_BOM_HAS_IN_ROUTE);
        }
        routeProductBomService.updateRouteProductBom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品制程物料BOM")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:delete')")
    public CommonResult<Boolean> deleteRouteProductBom(@RequestParam("id") Long id) {
        routeProductBomService.deleteRouteProductBom(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品制程物料BOM")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:query')")
    public CommonResult<RouteProductBomRespVO> getRouteProductBom(@RequestParam("id") Long id) {
        RouteProductBomDO routeProductBom = routeProductBomService.getRouteProductBom(id);
        return success(RouteProductBomConvert.INSTANCE.convert(routeProductBom));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品制程物料BOM列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:query')")
    public CommonResult<List<RouteProductBomRespVO>> getRouteProductBomList(@RequestParam("ids") Collection<Long> ids) {
        List<RouteProductBomDO> list = routeProductBomService.getRouteProductBomList(ids);
        return success(RouteProductBomConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品制程物料BOM分页")
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:query')")
    public CommonResult<PageResult<RouteProductBomRespVO>> getRouteProductBomPage(@Valid RouteProductBomPageReqVO pageVO) {
        PageResult<RouteProductBomDO> pageResult = routeProductBomService.getRouteProductBomPage(pageVO);
        return success(RouteProductBomConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品制程物料BOM Excel")
    @PreAuthorize("@ss.hasPermission('pro:route-product-bom:export')")
    @OperateLog(type = EXPORT)
    public void exportRouteProductBomExcel(@Valid RouteProductBomExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RouteProductBomDO> list = routeProductBomService.getRouteProductBomList(exportReqVO);
        // 导出 Excel
        List<RouteProductBomExcelVO> datas = RouteProductBomConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品制程物料BOM.xls", "数据", RouteProductBomExcelVO.class, datas);
    }

}
