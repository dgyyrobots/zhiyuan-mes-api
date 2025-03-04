package com.dofast.module.pro.controller.admin.route;

import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductExportReqVO;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductRespVO;
import com.dofast.module.pro.controller.admin.workorder.vo.WorkorderUpdateReqVO;
import com.dofast.module.pro.convert.routeproduct.RouteProductConvert;
import com.dofast.module.pro.convert.workorder.WorkorderConvert;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
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
        /**
         * 修改时间节点: 2024-10-08 14:42:51
         * ERP中物料料号对应多个工艺路线
         * 允许多个工艺路线绑定同一个物料料号
         * 工艺编码规则: 物料料号-版本号, 例: 10001-V1
         * 工单信息页面追加下拉框, 管控工艺版本号
          */
        /*if(Constant.NOT_UNIQUE.equals(routeService.checkRouteCodeUnique(createReqVO))){
            return error(ErrorCodeConstants.ROUTE_CODE_EXITS);
        }*/
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

    @GetMapping("/initRouteCodeByItemCode")
    @Operation(summary = "获得产品所有工艺编码")
    @PreAuthorize("@ss.hasPermission('pro:route:initRouteCodeByItemCode')")
    public CommonResult<List<Map<String, Object>>> initRouteCodeByItemCode(@RequestParam("itemCode") String itemCode) {
        RouteExportReqVO exportReqVO = new RouteExportReqVO();
        exportReqVO.setProductCode(itemCode);
        List<RouteDO> routeProductList = routeService.getRouteList(exportReqVO);
        if(routeProductList.isEmpty()){
            return success();
        }
        List<Map<String, Object>> routeCode =  new ArrayList<>();
        for (int i = 0; i < routeProductList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            String uniRouteCode = routeProductList.get(i).getRouteCode();
            String uniCode = uniRouteCode.substring(uniRouteCode.indexOf("-") + 1);
            map.put("value", uniCode);
            map.put("label", uniCode);
            routeCode.add(map);
        }
        return success(routeCode);
    }

    @PutMapping("/updateFile")
    @Operation(summary = "更新工艺路线")
    @PreAuthorize("@ss.hasPermission('pro:route:update')")
    public CommonResult<Boolean> updateFile( @RequestBody RouteUpdateReqVO updateReqVO) {
        RouteDO route = routeService.getRoute(updateReqVO.getRouteCode());
        if(route == null){
            return success(true);
        }
        String url = updateReqVO.getFile();
        if(url==null){
            return success(true);
        }
        // 将url基于","进行拆分
        String[] urls = url.split(",");
        if (urls.length == 0) {
            return success(true);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < urls.length; i++) {
            String currentUrl = urls[i].trim();
            if (currentUrl.isEmpty()) {
                continue;
            }
            String[] parts = currentUrl.split("/");
            if( parts[parts.length - 1].contains("%")) {
                parts[parts.length - 1] = parts[parts.length - 1].substring(0, parts[parts.length - 1].indexOf("%"));
                if(parts[parts.length - 1].contains("?")){
                    parts[parts.length - 1] = parts[parts.length - 1].substring(0, parts[parts.length - 1].indexOf("?"));
                }
            }
            String finName = parts[parts.length - 1];
            System.out.println(finName);
            sb.append(finName);
            // 校验当前url是否为最后一个
            if (i != urls.length - 1) {
                sb.append(",");
            }
        }
        String file = Optional.ofNullable(sb.toString()).orElse("");
        if( file!="") {
            route.setFile(file);
        }
        routeService.updateRoute(RouteConvert.INSTANCE.convert01(route));
        return success(true);
    }


}
