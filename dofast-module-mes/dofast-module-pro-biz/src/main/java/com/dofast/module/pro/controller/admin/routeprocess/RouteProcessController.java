package com.dofast.module.pro.controller.admin.routeprocess;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.pro.controller.admin.route.vo.RouteExportReqVO;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductExportReqVO;
import com.dofast.module.pro.controller.admin.routeproduct.vo.RouteProductListVO;
import com.dofast.module.pro.convert.routeproduct.RouteProductConvert;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;
import com.dofast.module.pro.dal.dataobject.route.RouteDO;
import com.dofast.module.pro.dal.dataobject.routeproduct.RouteProductDO;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;
import com.dofast.module.pro.enums.ErrorCodeConstants;
import com.dofast.module.pro.service.process.ProcessService;
import com.dofast.module.pro.service.route.RouteService;
import com.dofast.module.pro.service.routeproduct.RouteProductService;
import com.dofast.module.pro.service.workorder.WorkorderService;
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

import com.dofast.module.pro.controller.admin.routeprocess.vo.*;
import com.dofast.module.pro.dal.dataobject.routeprocess.RouteProcessDO;
import com.dofast.module.pro.convert.routeprocess.RouteProcessConvert;
import com.dofast.module.pro.service.routeprocess.RouteProcessService;

@Tag(name = "管理后台 - 工艺组成")
@RestController
@RequestMapping("/mes/pro/route-process")
@Validated
public class RouteProcessController {

    @Resource
    private RouteProcessService routeProcessService;

    @Resource
    private RouteProductService routeProductService;

    @Resource
    private ProcessService processService;

    @Resource
    private RouteService routeService;

    @Resource
    private WorkorderService workorderService;

    /**
     * 查询指定产品的工艺组成
     *
     * @return
     */
    @Operation(summary = "查询指定产品的工艺组成")
    @PreAuthorize("@ss.hasPermission('pro:route-process:query')")
    @GetMapping("/listProductProcess/{productId}")
    public CommonResult<List<RouteProcessRespVO>> listProductProcess(@PathVariable("productId") Long workorderId) {
        // 已将参数修改为工单Id
        // 基于工单Id获取工艺路线
        WorkorderDO workorder = workorderService.getWorkorder(workorderId);
      /*  String routeCode = workorder.getRouteCode(); // 工艺编码
        String itemCode = workorder.getProductCode(); // 产品编码*/
        String uniRouteCode = workorder.getProductCode() + "-" + workorder.getRouteCode(); // 唯一工艺编码
        // 基于唯一工艺编码获取当前的工艺信息
        RouteExportReqVO expVO = new RouteExportReqVO();
        expVO.setRouteCode(uniRouteCode);
        List<RouteDO> routeList = routeService.getRouteList(expVO);
        if (!routeList.isEmpty()) {
            // 开始查找工艺路线信息
            RouteDO route = CollUtil.getFirst(routeList); // 获取工艺路线
            Long routeId = route.getId();
            // 基于工艺Id获取当前工艺-产品信息
            RouteProductExportReqVO reqVO = new RouteProductExportReqVO();
            reqVO.setRouteId(routeId);
            List<RouteProductDO> products = routeProductService.getRouteProductList(reqVO);
            if (CollUtil.isNotEmpty(products)) {
                RouteProductDO product = products.get(0);
                RouteProcessListVO param = new RouteProcessListVO();
                param.setRouteId(product.getRouteId());
                List<RouteProcessDO> list = routeProcessService.selectList(param);
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA:  "+ list);
                return success(RouteProcessConvert.INSTANCE.convertList(list));
            } else {
                return error(ErrorCodeConstants.ROUTE_PRODUCT_NOT_EXISTS_PROCESS);
            }
        } else {
            return error(ErrorCodeConstants.ROUTE_NOT_EXISTS);
        }
    }

    @PostMapping("/create")
    @Operation(summary = "创建工艺组成")
    @PreAuthorize("@ss.hasPermission('pro:route-process:create')")
    public CommonResult<Long> createRouteProcess(@Valid @RequestBody RouteProcessCreateReqVO createReqVO) {
        if (Constant.NOT_UNIQUE.equals(routeProcessService.checkOrderNumExists(createReqVO))) {
            return error(ErrorCodeConstants.ROUTE_CODE_EXISTS);
        }
        if (Constant.NOT_UNIQUE.equals(routeProcessService.checkProcessExists(createReqVO))) {
            return error(ErrorCodeConstants.ROUTE_NOT_REPEAT);
        }
        if (Constant.YES.equals(createReqVO.getKeyFlag()) && Constant.NOT_UNIQUE.equals(routeProcessService.checkUpdateFlagUnique(createReqVO))) {
            return error(ErrorCodeConstants.ROUTE_PROCESS_HAS_KEY);
        }

        ProcessDO process = processService.getcess(createReqVO.getProcessId());
        createReqVO.setProcessCode(process.getProcessCode());
        createReqVO.setProcessName(process.getProcessName());

        //更新上一个工序的nextProcess
        RouteProcessDO preProcess = routeProcessService.findPreProcess(createReqVO);
        if (StrUtils.isNotNull(preProcess)) {
            preProcess.setNextProcessId(createReqVO.getProcessId());
            preProcess.setNextProcessCode(createReqVO.getProcessCode());
            preProcess.setNextProcessName(createReqVO.getProcessName());

            routeProcessService.updateRouteProcess(RouteProcessConvert.INSTANCE.convert01(preProcess));
        }

        //设置当前工序的nextProcess
        RouteProcessDO nextProcess = routeProcessService.findNextProcess(createReqVO);
        if (StrUtils.isNotNull(nextProcess)) {
            createReqVO.setNextProcessId(nextProcess.getProcessId());
            createReqVO.setNextProcessCode(nextProcess.getProcessCode());
            createReqVO.setNextProcessName(nextProcess.getProcessName());
        } else {
            createReqVO.setNextProcessId(0L);
            createReqVO.setNextProcessName("无");
        }

        return success(routeProcessService.createRouteProcess(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工艺组成")
    @PreAuthorize("@ss.hasPermission('pro:route-process:update')")
    public CommonResult<Boolean> updateRouteProcess(@Valid @RequestBody RouteProcessUpdateReqVO updateReqVO) {
        if (Constant.NOT_UNIQUE.equals(routeProcessService.checkOrderNumExists(updateReqVO))) {
            return error(ErrorCodeConstants.ROUTE_CODE_EXISTS);
        }
        if (Constant.NOT_UNIQUE.equals(routeProcessService.checkProcessExists(updateReqVO))) {
            return error(ErrorCodeConstants.ROUTE_NOT_REPEAT);
        }
        if (Constant.YES.equals(updateReqVO.getKeyFlag()) && Constant.NOT_UNIQUE.equals(routeProcessService.checkUpdateFlagUnique(updateReqVO))) {
            return error(ErrorCodeConstants.ROUTE_PROCESS_HAS_KEY);
        }

        ProcessDO process = processService.getcess(updateReqVO.getProcessId());
        updateReqVO.setProcessCode(process.getProcessCode());
        updateReqVO.setProcessName(process.getProcessName());

        //更新上一个工序的nextProcess
        RouteProcessDO preProcess = routeProcessService.findPreProcess(updateReqVO);
        if (StrUtils.isNotNull(preProcess)) {
            preProcess.setNextProcessId(updateReqVO.getProcessId());
            preProcess.setNextProcessCode(updateReqVO.getProcessCode());
            preProcess.setNextProcessName(updateReqVO.getProcessName());

            routeProcessService.updateRouteProcess(RouteProcessConvert.INSTANCE.convert01(preProcess));
        }

        //设置当前工序的nextProcess
        RouteProcessDO nextProcess = routeProcessService.findNextProcess(updateReqVO);
        if (StrUtils.isNotNull(nextProcess)) {
            updateReqVO.setNextProcessId(nextProcess.getProcessId());
            updateReqVO.setNextProcessCode(nextProcess.getProcessCode());
            updateReqVO.setNextProcessName(nextProcess.getProcessName());
        } else {
            updateReqVO.setNextProcessId(0L);
            updateReqVO.setNextProcessName("无");
        }

        routeProcessService.updateRouteProcess(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工艺组成")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pro:route-process:delete')")
    public CommonResult<Boolean> deleteRouteProcess(@RequestParam("id") Long id) {
        routeProcessService.deleteRouteProcess(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工艺组成")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pro:route-process:query')")
    public CommonResult<RouteProcessRespVO> getRouteProcess(@RequestParam("id") Long id) {
        RouteProcessDO routeProcess = routeProcessService.getRouteProcess(id);
        return success(RouteProcessConvert.INSTANCE.convert(routeProcess));
    }

    @GetMapping("/list")
    @Operation(summary = "获得工艺组成列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('pro:route-process:query')")
    public CommonResult<List<RouteProcessRespVO>> getRouteProcessList(@RequestParam("ids") Collection<Long> ids) {
        List<RouteProcessDO> list = routeProcessService.getRouteProcessList(ids);
        return success(RouteProcessConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工艺组成分页")
    @PreAuthorize("@ss.hasPermission('pro:route-process:query')")
    public CommonResult<PageResult<RouteProcessRespVO>> getRouteProcessPage(@Valid RouteProcessPageReqVO pageVO) {
        PageResult<RouteProcessDO> pageResult = routeProcessService.getRouteProcessPage(pageVO);
        return success(RouteProcessConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工艺组成 Excel")
    @PreAuthorize("@ss.hasPermission('pro:route-process:export')")
    @OperateLog(type = EXPORT)
    public void exportRouteProcessExcel(@Valid RouteProcessExportReqVO exportReqVO,
                                        HttpServletResponse response) throws IOException {
        List<RouteProcessDO> list = routeProcessService.getRouteProcessList(exportReqVO);
        // 导出 Excel
        List<RouteProcessExcelVO> datas = RouteProcessConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "工艺组成.xls", "数据", RouteProcessExcelVO.class, datas);
    }

}
