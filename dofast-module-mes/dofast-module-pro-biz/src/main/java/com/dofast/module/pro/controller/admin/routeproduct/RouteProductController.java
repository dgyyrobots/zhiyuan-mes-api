package com.dofast.module.pro.controller.admin.routeproduct;

import cn.hutool.core.collection.CollUtil;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.routeproductbom.RouteProductBomService;
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

import com.dofast.module.pro.controller.admin.routeproduct.vo.*;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.module.pro.convert.routeproduct.RouteProductConvert;
import com.dofast.module.pro.service.routeproduct.RouteProductService;

@Tag(name = "生产管理 - 产品制程")
@RestController
@RequestMapping("/mes/pro/route-product")
@Validated
public class RouteProductController {

    @Resource
    private RouteProductService routeProductService;

    @Resource
    private RouteProductBomService routeProductBomService;

    @PostMapping("/create")
    @Operation(summary = "创建产品制程")
    @PreAuthorize("@ss.hasPermission('pro:route-product:create')")
    public CommonResult<Long> createRouteProduct(@Valid @RequestBody RouteProductCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(routeProductService.checkItemUnique(createReqVO))){
            return error(ErrorCodeConstants.ROUTE_PRODUCT_HAS_IN_ROUTE);
        }
        return success(routeProductService.createRouteProduct(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新产品制程")
    @PreAuthorize("@ss.hasPermission('pro:route-product:update')")
    public CommonResult<Boolean> updateRouteProduct(@Valid @RequestBody RouteProductUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(routeProductService.checkItemUnique(updateReqVO))){
            return error(ErrorCodeConstants.ROUTE_PRODUCT_HAS_IN_ROUTE);
        }
        routeProductService.updateRouteProduct(updateReqVO);
        return success(true);
    }

    /**
     * 更改产品的生产路线
     */
    @PreAuthorize("@ss.hasPermission('pro:route-product:update')")
    @PutMapping("/move")
    @Operation(summary = "更改产品的生产路线")
    public CommonResult move(@RequestBody RouteProductUpdateReqVO proRouteProduct){
        RouteProductListVO param = new RouteProductListVO();
        param.setItemId(proRouteProduct.getItemId());
        param.setRouteId(proRouteProduct.getRouteId());
        List<RouteProductDO> products = routeProductService.selectList(param);
        int ret =1;
        if(CollUtil.isNotEmpty(products)){
            RouteProductDO product = products.get(0);
            product.setRouteId(proRouteProduct.getRouteId());
            RouteProductUpdateReqVO updateReqVO = RouteProductConvert.INSTANCE.convert01(product);
            ret =routeProductService.updateRouteProduct(updateReqVO);
        }
        return success(ret>0);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除产品制程")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:route-product:delete')")
    public CommonResult<Boolean> deleteRouteProduct(@RequestParam("id") Long id) {

        RouteProductDO product = routeProductService.getRouteProduct(id);

        routeProductBomService.deleteByRouteIdAndProductId(product.getRouteId(),product.getId());

        routeProductService.deleteRouteProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得产品制程")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:route-product:query')")
    public CommonResult<RouteProductRespVO> getRouteProduct(@RequestParam("id") Long id) {
        RouteProductDO routeProduct = routeProductService.getRouteProduct(id);
        return success(RouteProductConvert.INSTANCE.convert(routeProduct));
    }

    @GetMapping("/list")
    @Operation(summary = "获得产品制程列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:route-product:query')")
    public CommonResult<List<RouteProductRespVO>> getRouteProductList(@RequestParam("ids") Collection<Long> ids) {
        List<RouteProductDO> list = routeProductService.getRouteProductList(ids);
        return success(RouteProductConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得产品制程分页")
    @PreAuthorize("@ss.hasPermission('pro:route-product:query')")
    public CommonResult<PageResult<RouteProductRespVO>> getRouteProductPage(@Valid RouteProductPageReqVO pageVO) {
        PageResult<RouteProductDO> pageResult = routeProductService.getRouteProductPage(pageVO);
        return success(RouteProductConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出产品制程 Excel")
    @PreAuthorize("@ss.hasPermission('pro:route-product:export')")
    @OperateLog(type = EXPORT)
    public void exportRouteProductExcel(@Valid RouteProductExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RouteProductDO> list = routeProductService.getRouteProductList(exportReqVO);
        // 导出 Excel
        List<RouteProductExcelVO> datas = RouteProductConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品制程.xls", "数据", RouteProductExcelVO.class, datas);
    }

}
