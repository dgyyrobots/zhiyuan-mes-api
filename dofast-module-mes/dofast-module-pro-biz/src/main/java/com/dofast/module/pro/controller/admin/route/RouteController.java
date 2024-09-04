package com.dofast.module.pro.controller.admin.route;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.routeprocess.RouteProcessService;
import com.dofast.module.pro.service.routeproduct.RouteProductService;
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

import com.dofast.module.pro.controller.admin.route.vo.*;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;
import com.dofast.module.pro.convert.route.RouteConvert;
import com.dofast.module.pro.service.route.RouteService;

@Tag(name = "生产管理 - 工艺路线")
@RestController
@RequestMapping("/mes/pro/route")
@Validated
public class RouteController {

    @Resource
    private RouteService routeService;
    @Resource
    private RouteProcessService routeProcessService;
    @Resource
    private RouteProductService routeProductService;
    @Resource
    private RouteProductBomService routeProductBomService;

    @PostMapping("/create")
    @Operation(summary = "创建工艺路线")
    @PreAuthorize("@ss.hasPermission('pro:route:create')")
    public CommonResult<Long> createRoute(@Valid @RequestBody RouteCreateReqVO createReqVO) {
        if(Constant.NOT_UNIQUE.equals(routeService.checkRouteCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.ROUTE_CODE_EXITS);
        }
        return success(routeService.createRoute(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工艺路线")
    @PreAuthorize("@ss.hasPermission('pro:route:update')")
    public CommonResult<Boolean> updateRoute(@Valid @RequestBody RouteUpdateReqVO updateReqVO) {
        if(Constant.NOT_UNIQUE.equals(routeService.checkRouteCodeUnique(updateReqVO))){
            return error(ErrorCodeConstants.ROUTE_CODE_EXITS);
        }
        routeService.updateRoute(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工艺路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:route:delete')")
    public CommonResult<Boolean> deleteRoute(@RequestParam("id") Long id) {
        routeProcessService.deleteByRouteId(id);
        routeProductService.deleteByRouteId(id);
        routeProductBomService.deleteByRouteId(id);
        routeService.deleteRoute(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工艺路线")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:route:query')")
    public CommonResult<RouteRespVO> getRoute(@RequestParam("id") Long id) {
        RouteDO route = routeService.getRoute(id);
        return success(RouteConvert.INSTANCE.convert(route));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工艺路线列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:route:query')")
    public CommonResult<List<RouteRespVO>> getRouteList(@RequestParam("ids") Collection<Long> ids) {
        List<RouteDO> list = routeService.getRouteList(ids);
        return success(RouteConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工艺路线分页")
    @PreAuthorize("@ss.hasPermission('pro:route:query')")
    public CommonResult<PageResult<RouteRespVO>> getRoutePage(@Valid RoutePageReqVO pageVO) {
        PageResult<RouteDO> pageResult = routeService.getRoutePage(pageVO);
        return success(RouteConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工艺路线 Excel")
    @PreAuthorize("@ss.hasPermission('pro:route:export')")
    @OperateLog(type = EXPORT)
    public void exportRouteExcel(@Valid RouteExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RouteDO> list = routeService.getRouteList(exportReqVO);
        // 导出 Excel
        List<RouteExcelVO> datas = RouteConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工艺路线.xls", "数据", RouteExcelVO.class, datas);
    }

}
